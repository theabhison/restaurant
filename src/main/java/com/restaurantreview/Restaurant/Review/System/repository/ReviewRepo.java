package com.restaurantreview.Restaurant.Review.System.repository;

import com.restaurantreview.Restaurant.Review.System.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findByRestaurantId(Long restaurantId);
}
