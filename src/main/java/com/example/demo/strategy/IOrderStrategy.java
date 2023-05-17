package com.example.demo.strategy;

import java.util.List;
import com.example.demo.entities.Review;

public interface IOrderStrategy {
    List<Review> getReviewsByOrder(List<Review> allReviews);
}
