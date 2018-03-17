package contentmanager.config;

import contentmanager.model.service.PostService;
import contentmanager.util.PostDtoEntityUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "contentmanager.model.dao",
        "contentmanager.model.service",
        "contentmanager.config",
        "contentmanager.util",
        "contentmanager"})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public PostService postService(){
        return new PostService();
    }

    @Bean
    public PostDtoEntityUtil postDtoEntityUtil(){
        return new PostDtoEntityUtil();
    }
}
