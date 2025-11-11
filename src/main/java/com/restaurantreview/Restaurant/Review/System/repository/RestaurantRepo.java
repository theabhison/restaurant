package com.restaurantreview.Restaurant.Review.System.repository;

import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    Page<Restaurant> findByCuisineContainingIgnoreCase(String cuisine, Pageable pageable);


    @Query("""
           SELECT r FROM Restaurant r
           JOIN r.reviews rv
           WHERE LOWER(r.cuisine) = LOWER(:cuisine)
           GROUP BY r.id
           ORDER BY AVG(rv.rating) DESC
           """)
    List<Restaurant> findTopByCuisineOrderByRatingDesc(String cuisine, Pageable pageable);
}
