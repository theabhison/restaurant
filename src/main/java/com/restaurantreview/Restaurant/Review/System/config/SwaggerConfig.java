package com.restaurantreview.Restaurant.Review.System.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI restaurantReviewOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant Review API")
                        .description("API documentation for managing restaurants and reviews")
                        .version("1.0.0"));
    }
}
