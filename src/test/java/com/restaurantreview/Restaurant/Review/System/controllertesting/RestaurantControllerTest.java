package com.restaurantreview.Restaurant.Review.System.controllertesting;

import com.restaurantreview.Restaurant.Review.System.dto.RestaurantDTO;
import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import com.restaurantreview.Restaurant.Review.System.enums.RangePrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateRestaurant() {
        // Provide basic auth
        TestRestTemplate authRestTemplate = restTemplate.withBasicAuth("admin", "admin123");

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Spice Garden");
        restaurant.setCuisine("GOA");
        restaurant.setAddress("123 MG Road, Pune");
        restaurant.setPrice(RangePrice.MEDIUM);

        ResponseEntity<RestaurantDTO> response = authRestTemplate.postForEntity(
                "/api/restaurants",
                restaurant,
                RestaurantDTO.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
