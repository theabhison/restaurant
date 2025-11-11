package com.restaurantreview.Restaurant.Review.System.services;

import com.restaurantreview.Restaurant.Review.System.dto.RestaurantDTO;
import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantService {

    RestaurantDTO create(Restaurant restaurant);

    RestaurantDTO getById(Long id);

    Page<RestaurantDTO> listByCuisine(String cuisine, int page, int size);

    List<RestaurantDTO> top3ByCuisine(String cuisine);

    RestaurantDTO update(Long id, Restaurant restaurant);

    void delete(Long id);
}