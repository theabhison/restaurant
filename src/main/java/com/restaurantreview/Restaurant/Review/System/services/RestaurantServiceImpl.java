package com.restaurantreview.Restaurant.Review.System.services;

import com.restaurantreview.Restaurant.Review.System.dto.RestaurantDTO;
import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import com.restaurantreview.Restaurant.Review.System.errors.ResourceNotFoundException;
import com.restaurantreview.Restaurant.Review.System.repository.RestaurantRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl {
    private final RestaurantRepo restaurantRepository;

    public RestaurantDTO create(Restaurant restaurant) {
        Restaurant saved = restaurantRepository.save(restaurant);
        return toDTO(saved);
    }

    public RestaurantDTO getById(Long id) {
        Restaurant r = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + id));
        return toDTO(r);
    }

    public Page<RestaurantDTO> listByCuisine(String cuisine, int page, int size) {
        PageRequest pr = PageRequest.of(page, size);
        return restaurantRepository.findByCuisineContainingIgnoreCase(cuisine == null ? "" : cuisine, pr)
                .map(this::toDTO);
    }

    public List<RestaurantDTO> top3ByCuisine(String cuisine) {
        var list = restaurantRepository.findTopByCuisineOrderByRatingDesc(cuisine, PageRequest.of(0, 3));
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RestaurantDTO update(Long id, Restaurant restaurant) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + id));

        if (restaurant.getName() != null) existing.setName(restaurant.getName());
        if (restaurant.getCuisine() != null) existing.setCuisine(restaurant.getCuisine());
        if (restaurant.getAddress() != null) existing.setAddress(restaurant.getAddress());
        if (restaurant.getPrice() != null) existing.setPrice(restaurant.getPrice());

        Restaurant updated = restaurantRepository.save(existing);
        return toDTO(updated);
    }

    public void delete(Long id) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + id));
        restaurantRepository.delete(existing);
    }

    private RestaurantDTO toDTO(Restaurant r) {
        double avg = r.getReviews().stream().mapToInt(v -> v.getRating()).average().orElse(0.0);
        return RestaurantDTO.builder()
                .id(r.getId())
                .name(r.getName())
                .cuisine(r.getCuisine())
                .address(r.getAddress())
                .priceRange(String.valueOf(r.getPrice()))
                .averageRating(avg)
                .build();
    }
}