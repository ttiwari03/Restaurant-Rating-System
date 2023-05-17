package com.example.demo.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.repositories.RestaurantRepository;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant register(String name) {
        Restaurant restaurant = new Restaurant(name);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getById(Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(
                "Restaurant with given id " + id + " not found."));
    }

    public List<Restaurant> getAllRestaurantsByRatingDescOrder() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        Collections.sort(allRestaurants, getRestaurantComparator());
        return allRestaurants;
    }

    private Comparator<Restaurant> getRestaurantComparator() {
        Comparator<Restaurant> comparator = (res1, res2) -> 
            Double.compare(res2.getAverageRating(), res1.getAverageRating());
        return comparator;
    }
}
