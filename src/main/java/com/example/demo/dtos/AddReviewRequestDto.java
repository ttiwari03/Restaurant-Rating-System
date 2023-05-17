package com.example.demo.dtos;

public class AddReviewRequestDto {
    private final Integer rating;
    private final Long restaurantId;
    private final Long userId;
    private String dishes;
    private String description;

    public AddReviewRequestDto(Integer rating, Long restaurantId, Long userId) {
        this.rating = rating;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.description = "";
        this.dishes = "";
    }

    
    public void setDishes(String dishes) {
        this.dishes = dishes;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getRating() {
        return rating;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDishes() {
        return dishes;
    }

    public String getDescription() {
        return description;
    }    
}
