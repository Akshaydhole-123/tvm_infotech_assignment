package com.ProductService;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi productApi()    {

        return GroupedOpenApi.builder().group("product-service").pathsToMatch("/product/**").build();

    }

}
