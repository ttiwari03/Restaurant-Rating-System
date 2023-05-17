package com.example.demo.dtos;

import com.example.demo.Constants;
import com.example.demo.entities.FilterType;
import com.example.demo.entities.RatingOrder;

public class GetReviewRequestDto {
    private final Long restaurantId;
    private final RatingOrder ratingOrder;
    private FilterType filterType;
    private Integer ratingLow;
    private Integer ratingHigh;

    public GetReviewRequestDto(Long restaurantId, RatingOrder ratingOrder) {
        this.restaurantId = restaurantId;
        this.ratingOrder = ratingOrder;
        this.ratingLow = Constants.LOW_RATING;
        this.ratingHigh = Constants.HIGH_RATING;
        this.filterType = FilterType.RATING_RANGE;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public void setRatingLow(Integer ratingLow) {
        this.ratingLow = ratingLow;
    }

    public void setRatingHigh(Integer ratingHigh) {
        this.ratingHigh = ratingHigh;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public RatingOrder getRatingOrder() {
        return ratingOrder;
    }

    public Integer getRatingLow() {
        return ratingLow;
    }

    public Integer getRatingHigh() {
        return ratingHigh;
    }    
}
