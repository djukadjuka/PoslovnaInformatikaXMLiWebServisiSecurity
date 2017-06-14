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
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/tabela/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/role_permission/**");

        registry.addInterceptor(authorityIntercepter).addPathPatterns("/company/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/business_partner/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/fiscal_year/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/purchase_order/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/purchase_order_item/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/invoice/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/invoice_item/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/item/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/item_group/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/vat_type/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/vat_rate/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/units_of_measurement/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/price_list/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/price_list_item/**");
        registry.addInterceptor(authorityIntercepter).addPathPatterns("/currency/**");
    }
}
