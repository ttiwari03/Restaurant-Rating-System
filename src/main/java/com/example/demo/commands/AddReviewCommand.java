package com.example.demo.commands;

import java.util.List;
import com.example.demo.dtos.AddReviewRequestDto;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.InvalidRatingException;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.ReviewService;

public class AddReviewCommand implements ICommand {
    private final ReviewService reviewService;

    public AddReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        //ADD_REVIEW <rating>  <userId> <restaurantId> <list of dish_names> <description>
        try {
            Integer rating = Integer.parseInt(tokens.get(1));
            Long userId = Long.parseLong(tokens.get(2));
            Long restaurantId = Long.parseLong(tokens.get(3));
            String dishes = tokens.get(4);
            String description = tokens.get(5);
            AddReviewRequestDto addReviewRequestDto = new AddReviewRequestDto(rating, restaurantId, userId);
            addReviewRequestDto.setDescription(description);
            addReviewRequestDto.setDishes(dishes);
            Review review = reviewService.addReview(addReviewRequestDto);
            System.out.printf("%s added successfully for %s by %s!\n", review, review.getRestaurant(), review.getUser());
        } catch (NumberFormatException | UserNotFoundException | RestaurantNotFoundException
            | InvalidRatingException exception) {
                System.out.println(exception.getMessage());
            }
        
    }
    
}
