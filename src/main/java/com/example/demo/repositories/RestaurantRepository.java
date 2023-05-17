package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.demo.entities.Restaurant;

public class RestaurantRepository implements CrudRepository<Restaurant, Long> {
    private final Map<Long, Restaurant> restaurantMap;
    private Long autoIncrementId;

    public RestaurantRepository() {
        restaurantMap = new HashMap<>();
        autoIncrementId = 1L;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Restaurant newRestaurant = new Restaurant(autoIncrementId, restaurant.getName());
        restaurantMap.put(autoIncrementId, newRestaurant);
        autoIncrementId++;
        return newRestaurant;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurantMap.values());
    }

    @Override
    public void delete(Restaurant restaurant) {
        if (restaurantMap.containsValue(restaurant)) {
            deleteById(restaurant.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        restaurantMap.remove(id);
    }

    @Override
    public Long count() {
        return (long) restaurantMap.size();
    }

    @Override
    public Boolean existById(Long id) {
        return restaurantMap.containsKey(id);
    }
    
}
