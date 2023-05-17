package com.example.demo.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.dtos.GetReviewRequestDto;
import com.example.demo.entities.Review;

public class ReviewFilterByRatingRange implements IFilterStrategy {


    @Override
    public List<Review> getReviewsByFilter(List<Review> allReviews,
            GetReviewRequestDto getReviewRequestDto) {
        if (allReviews != null) {        
            Integer low = getReviewRequestDto.getRatingLow();
            Integer high = getReviewRequestDto.getRatingHigh();
            List<Review> filteredReviews = allReviews.stream().filter(review -> review.getRating() >= low && review.getRating() <= high)
                .collect(Collectors.toList());        
            return filteredReviews;
        }
        return new ArrayList<>();
    }
    
}
