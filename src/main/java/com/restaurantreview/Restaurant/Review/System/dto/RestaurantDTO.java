package com.restaurantreview.Restaurant.Review.System.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDTO {
    private Long id;
    private String name;
    private String cuisine;
    private String address;
    private String priceRange;
    private Double averageRating;
}
