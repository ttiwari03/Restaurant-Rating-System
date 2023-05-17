package com.example.demo.strategy;

import java.util.List;
import com.example.demo.dtos.GetReviewRequestDto;
import com.example.demo.entities.Review;

public interface IFilterStrategy {
    List<Review> getReviewsByFilter(List<Review> allReviews, GetReviewRequestDto getReviewRequestDto);
}
