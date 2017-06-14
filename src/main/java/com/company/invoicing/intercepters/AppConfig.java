package com.company.invoicing.intercepters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by User on 6/1/2017.
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorityIntercepter authorityIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/user/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/role/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/permission/**");
        //registry.addInterceptor(requestInterceptor).addPathPatterns("/roles/**");
        //registry.addInterceptor(requestInterceptor).addPathPatterns("/permissions/**");
    }
}
