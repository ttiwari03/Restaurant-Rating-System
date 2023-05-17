package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.services.RestaurantService;

public class DescribeRestaurantCommand implements ICommand {
    private final RestaurantService restaurantService;

    public DescribeRestaurantCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            Long restaurantId = Long.parseLong(tokens.get(1));
            Restaurant restaurant = restaurantService.getById(restaurantId);
            System.out.printf("Restaurant [id=%d, name=%s, rating=%.1f]\n", restaurant.getId(), restaurant.getName(), restaurant.getAverageRating());
        } catch (RestaurantNotFoundException | NumberFormatException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
