package com.netsense.cloudfilemanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: rui
 * @Date: 2018/9/27 19:53
 * @Description:
 */
@Configuration
public class DefaultView implements WebMvcConfigurer {
    //支持跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
    //指定启示页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/index1.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }

}
