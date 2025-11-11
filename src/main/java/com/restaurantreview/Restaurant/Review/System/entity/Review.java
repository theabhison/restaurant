package com.restaurantreview.Restaurant.Review.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurantreview.Restaurant.Review.System.enums.StatusReview;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Min(1) @Max(5)
    private int rating;

    @Column(length = 2000)
    private  String comment;

    private LocalDate visitDate;

    @Enumerated(EnumType.STRING)
    private StatusReview status ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id" , nullable=false)
    @JsonBackReference
    private  Restaurant restaurant;


}
