package com.example.demo.commands;

import java.util.List;
import com.example.demo.dtos.AddReviewRequestDto;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.InvalidRatingException;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.ReviewService;

public class AddRatingCommand implements ICommand {
    private final ReviewService reviewService;

    public AddRatingCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // ADD_RATING <rating> <userId> <restaurantId>
        try {
            Integer rating = Integer.parseInt(tokens.get(1));
            Long userId = Long.parseLong(tokens.get(2));
            Long restaurantId = Long.parseLong(tokens.get(3));
            AddReviewRequestDto addReviewRequestDto = new AddReviewRequestDto(rating, restaurantId, userId);
            Review review = reviewService.addReview(addReviewRequestDto);
            System.out.printf("%s added successfully for %s by %s!\n", review, review.getRestaurant(), review.getUser());
        } catch (UserNotFoundException | RestaurantNotFoundException 
            | InvalidRatingException | NumberFormatException exception) {
            System.out.println(exception.getMessage());
        }
        
    }
    
}
