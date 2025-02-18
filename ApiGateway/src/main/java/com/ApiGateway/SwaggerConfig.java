package com.ApiGateway;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI publicApi()    {

        return new OpenAPI().info(new Info().title("Api Documentation")
                .description("API Gateway").version("1.0"));

    }
    @Bean
    public GroupedOpenApi categoryApi()    {

        return GroupedOpenApi.builder().group("category-service").pathsToMatch("/category/**").build();

    }
    @Bean
    public GroupedOpenApi productApi()    {

        return GroupedOpenApi.builder().group("product-service").pathsToMatch("/product/**").build();

    }

}
