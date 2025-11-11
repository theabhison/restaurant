package com.restaurantreview.Restaurant.Review.System.servicetesting;

import com.restaurantreview.Restaurant.Review.System.dto.RestaurantDTO;
import com.restaurantreview.Restaurant.Review.System.entity.Restaurant;
import com.restaurantreview.Restaurant.Review.System.errors.ResourceNotFoundException;
import com.restaurantreview.Restaurant.Review.System.repository.RestaurantRepo;
import com.restaurantreview.Restaurant.Review.System.services.RestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {
    @Mock
    private RestaurantRepo restaurantRepo;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_Success() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Spice Garden");

        when(restaurantRepo.findById(1L)).thenReturn(Optional.of(restaurant));

        RestaurantDTO dto = restaurantService.getById(1L);

        assertNotNull(dto);
        assertEquals("Spice Garden", dto.getName());
    }

    @Test
    void testGetById_NotFound() {
        when(restaurantRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restaurantService.getById(1L));
    }


}
