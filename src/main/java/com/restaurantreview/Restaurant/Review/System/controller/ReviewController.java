package com.restaurantreview.Restaurant.Review.System.controller;

import com.restaurantreview.Restaurant.Review.System.dto.ReviewDTO;
import com.restaurantreview.Restaurant.Review.System.services.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewServiceImpl;

    @PostMapping
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO dto) {
        ReviewDTO saved = reviewServiceImpl.create(dto);
        return ResponseEntity.created(URI.create("/api/reviews/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewDTO>> getByRestaurant(@PathVariable Long restaurantId) {
        List<ReviewDTO> reviews = reviewServiceImpl.getByRestaurant(restaurantId);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id,
                                            @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setId(id);
        ReviewDTO updated = reviewServiceImpl.updateReview(reviewDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewServiceImpl.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }

}
