package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.RestaurantService;

public class ListRestaurantsCommand implements ICommand {
    private final RestaurantService restaurantService;

    public ListRestaurantsCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            List<Restaurant> allRestaurants = restaurantService.getAllRestaurantsByRatingDescOrder();
            System.out.println(allRestaurants);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        
    }
}
