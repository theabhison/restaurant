package com.restaurantreview.Restaurant.Review.System.entity;


import com.restaurantreview.Restaurant.Review.System.enums.RangePrice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    private  String name;

    private  String cuisine ;

    private  String address;

    @Enumerated(EnumType.STRING)
    private RangePrice price= RangePrice.MEDIUM;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}
