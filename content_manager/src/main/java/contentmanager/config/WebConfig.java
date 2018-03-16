package contentmanager.config;

import contentmanager.Main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {
        "contentmanager.model.dao",
        "contentmanager.model.service",
        "contentmanager.config",
        "contentmanager"})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Main getMain(){
        return new Main();
    }
}
