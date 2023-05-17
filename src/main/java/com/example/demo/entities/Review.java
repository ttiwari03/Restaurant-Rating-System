package com.example.demo.entities;


public class Review {
    private Long id;
    private Integer rating;
    private String description;
    private User user;
    private Restaurant restaurant;
    private String dishes;

    public Review(Integer rating, User user, Restaurant restaurant) {
        this.rating = rating;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Review(Long id, Integer rating, User user, Restaurant restaurant) {
        this(rating, user, restaurant);
        this.id = id;
        this.description = "";
        this.dishes = "";
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + "]";
    }       
}
