package com.restaurantreview.Restaurant.Review.System.controller;

import com.restaurantreview.Restaurant.Review.System.dto.RestaurantDTO;
import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import com.restaurantreview.Restaurant.Review.System.services.RestaurantServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantServiceImpl restaurantServiceImpl;

    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@Valid @RequestBody Restaurant restaurant) {
        RestaurantDTO dto = restaurantServiceImpl.create(restaurant);
        return ResponseEntity.created(URI.create("/api/restaurants/" + dto.getId()))
                .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantServiceImpl.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantDTO>> list(
            @RequestParam(required = false) String cuisine,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(restaurantServiceImpl.listByCuisine(cuisine, page, size));
    }

    @GetMapping("/top3")
    public ResponseEntity<?> top3(@RequestParam String cuisine) {
        return ResponseEntity.ok(restaurantServiceImpl.top3ByCuisine(cuisine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> update(@PathVariable Long id,
                                                @Valid @RequestBody Restaurant restaurant) {
        RestaurantDTO updated = restaurantServiceImpl.update(id, restaurant);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}