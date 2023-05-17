package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.RestaurantService;

public class AddRestaurantCommand implements ICommand {
    private final RestaurantService restaurantService;

    public AddRestaurantCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String name = tokens.get(1);
        Restaurant restaurant = restaurantService.register(name);
        System.out.println(restaurant);
    }
    
}
