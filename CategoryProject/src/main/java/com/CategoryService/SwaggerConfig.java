package com.CategoryService;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi categoryApi()    {

        return GroupedOpenApi.builder().group("category-service").pathsToMatch("/category/**").build();

    }

}
