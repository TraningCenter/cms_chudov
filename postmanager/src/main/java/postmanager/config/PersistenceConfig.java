package postmanager.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("postmanager.mapper")
@PropertySource({"classpath:persistence-postgresql.properties"})
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public HikariDataSource hikariDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDataSource(dataSource());
        dataSource.setPoolName(env.getProperty("mybatis.poolName"));
        dataSource.setMinimumIdle(Integer.parseInt(env.getProperty("mybatis.hikari.minimumIdle")));
        dataSource.setMaximumPoolSize(Integer.parseInt(env.getProperty("mybatis.hikari.maximumPoolSize")));
        dataSource.setIdleTimeout(Integer.parseInt(env.getProperty("mybatis.hikari.idleTimeout")));

        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(){
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(hikariDataSource());
        sessionFactory.setTypeAliasesPackage("postmanager.model.entity");

        return sessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(hikariDataSource());
    }
}
