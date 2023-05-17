package com.example.demo.entities;

public class Restaurant {
    private Long id;
    private String name;
    private Double averageRating;
    private Long totalReviewCount;
    private Long totalRating;

    public Restaurant(Long id, String name) {
        this.id = id;
        this.name = name;
        this.averageRating = 0.0;
        this.totalReviewCount = 0L;
        this.totalRating = 0L;
    }
    
    public Restaurant(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    private void setAverageRating() {
        this.averageRating = 1.0 * totalRating / totalReviewCount;
    }

    public void increaseTotalRating(Integer rating) {
        this.totalRating += rating;
        setAverageRating();
    }

    public Long getTotalReviewCount() {
        return totalReviewCount;
    }

    public void increaseReviewCount() {
        totalReviewCount++;
    }

    @Override
    public String toString() {
        return "Restaurant [id=" + id + "]";
    }

}
