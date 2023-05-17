package com.example.demo.exceptions;

public class RestaurantNotFoundException extends Throwable {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
    
}
