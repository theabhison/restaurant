package com.restaurantreview.Restaurant.Review.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestaurantReviewSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReviewSystemApplication.class, args);
	}

}
