package com.restaurantreview.Restaurant.Review.System.services;

import com.restaurantreview.Restaurant.Review.System.dto.ReviewDTO;

import java.util.List;

public interface IReviewService {

    ReviewDTO create(ReviewDTO dto);

    List<ReviewDTO> getByRestaurant(Long restaurantId);

    ReviewDTO updateReview(ReviewDTO reviewDTO);

    void deleteReviewById(Long id);
}