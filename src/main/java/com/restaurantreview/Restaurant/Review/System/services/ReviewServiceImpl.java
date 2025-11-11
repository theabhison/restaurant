package com.restaurantreview.Restaurant.Review.System.services;

import com.restaurantreview.Restaurant.Review.System.dto.ReviewDTO;
import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import com.restaurantreview.Restaurant.Review.System.entity.Review;
import com.restaurantreview.Restaurant.Review.System.enums.StatusReview;
import com.restaurantreview.Restaurant.Review.System.errors.ResourceNotFoundException;
import com.restaurantreview.Restaurant.Review.System.repository.RestaurantRepo;
import com.restaurantreview.Restaurant.Review.System.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService{
    private final ReviewRepo reviewRepository;
    private final RestaurantRepo restaurantRepository;


    public ReviewDTO create(ReviewDTO dto) {
        Restaurant r = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + dto.getRestaurantId()));


        Review rev = Review.builder()
                .rating(dto.getRating())
                .comment(dto.getComment())
                .visitDate(dto.getVisitDate() == null ? LocalDate.now() : dto.getVisitDate())
                .status(dto.getStatus() == null ? StatusReview.PENDING : StatusReview.valueOf(dto.getStatus()))
                .restaurant(r)
                .build();


        Review saved = reviewRepository.save(rev);
        dto.setId(saved.getId());
        return dto;
    }


    public List<ReviewDTO> getByRestaurant(Long restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    private ReviewDTO toDTO(Review r) {
        return ReviewDTO.builder()
                .id(r.getId())
                .rating(r.getRating())
                .comment(r.getComment())
                .visitDate(r.getVisitDate())
                .status(String.valueOf(r.getStatus()))
                .restaurantId(r.getRestaurant().getId())
                .build();
    }

    public  void  deleteReviewById(Long id){
        reviewRepository.deleteById(id);
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Review not found with id: " + reviewDTO.getId()));

        if (reviewDTO.getComment() != null) {
            review.setComment(reviewDTO.getComment());
        }

        if (reviewDTO.getRating() != null) {
            review.setRating(reviewDTO.getRating());
        }

        if (reviewDTO.getStatus() != null) {
            review.setStatus(StatusReview.valueOf(reviewDTO.getStatus()));
        }

        review.setVisitDate(LocalDate.now());

        Review updatedReview = reviewRepository.save(review);

        return ReviewDTO.builder()
                .id(updatedReview.getId())
                .comment(updatedReview.getComment())
                .rating(updatedReview.getRating())
                .status(updatedReview.getStatus().name())
                .visitDate(updatedReview.getVisitDate())
                .build();
    }

}
