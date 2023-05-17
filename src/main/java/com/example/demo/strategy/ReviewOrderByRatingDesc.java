package com.example.demo.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.example.demo.entities.Review;

public class ReviewOrderByRatingDesc implements IOrderStrategy {

    @Override
    public List<Review> getReviewsByOrder(List<Review> allReviews) {
        if (allReviews != null) {
            Collections.sort(allReviews, getReviewComparator());
            return allReviews;
        }
        return new ArrayList<>();
    }

    private Comparator<Review> getReviewComparator() {
        Comparator<Review> comparator = (review1, review2) -> Integer.compare(review2.getRating(), review1.getRating());
        return comparator;
    }
    
}
