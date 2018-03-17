package contentmanager.config;

import com.googlecode.flyway.core.Flyway;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp2.PoolingDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-postgresql.properties"})
@ComponentScan({"contentmanager.model"})
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(hikariDataSource());
        sessionFactory.setPackagesToScan(
                new String[] {"contentmanager.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    @Autowired
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory){
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(){
        final Flyway flyway = new Flyway();
        flyway.setDataSource(this.hikariDataSource());
        flyway.setLocations("contentmanager.model.migration", "filesystem:src/main/resources/db/migration");

        return flyway;
    }

    @Bean
    @DependsOn(value = {"flyway"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(this.hikariDataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("contentmanager.model");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(this.jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(this.jpaProperties());

        return localContainerEntityManagerFactoryBean;
    }

    @Bean(destroyMethod = "close")
    public HikariDataSource hikariDataSource(){
        /*HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setDataSource(this.restDataSource());
        hikariConfig.setPoolName("cmPool");*/

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDataSource(this.restDataSource());
        hikariDataSource.setPoolName(env.getProperty("hibernate.poolName"));
        hikariDataSource.addDataSourceProperty("hibernate.connection.provider_class",
                env.getProperty("hibernate.connection.provider_class"));
        hikariDataSource.addDataSourceProperty("hibernate.hikari.minimumIdle",
                env.getProperty("hibernate.hikari.minimumIdle"));
        hikariDataSource.addDataSourceProperty("hibernate.hikari.maximumPoolSize",
                env.getProperty("hibernate.hikari.maximumPoolSize"));
        hikariDataSource.addDataSourceProperty("hibernate.hikari.idleTimeout",
                env.getProperty("hibernate.hikari.idleTimeout"));

        return hikariDataSource;
    }

    @Bean
    public DataSource restDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor excptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties(){
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }

    private Map<String, Object> jpaProperties() {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("eclipselink.weaving", "false");

        return map;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        final JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter() {
            {
                setShowSql(true);
                setDatabase(Database.POSTGRESQL);
                setGenerateDdl(false);
            }
        };

        return jpaVendorAdapter;
    }
}
