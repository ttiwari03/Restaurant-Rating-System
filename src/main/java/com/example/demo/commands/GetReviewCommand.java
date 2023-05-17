package com.example.demo.commands;

import java.util.List;
import com.example.demo.dtos.GetReviewRequestDto;
import com.example.demo.entities.RatingOrder;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.services.ReviewService;

public class GetReviewCommand implements ICommand {
    private final ReviewService reviewService;

    public GetReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            Long restaurantId = Long.parseLong(tokens.get(1));
            RatingOrder ratingOrder = RatingOrder.valueOf(tokens.get(2));
            GetReviewRequestDto getReviewRequestDto = new GetReviewRequestDto(restaurantId, ratingOrder);
            List<Review> allReviews = reviewService.getAllReviews(getReviewRequestDto);
            System.out.println(allReviews);
        } catch (NumberFormatException | RestaurantNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
