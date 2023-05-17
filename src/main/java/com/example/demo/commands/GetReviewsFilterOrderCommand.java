package com.example.demo.commands;

import java.util.List;
import com.example.demo.dtos.GetReviewRequestDto;
import com.example.demo.entities.RatingOrder;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.services.ReviewService;

public class GetReviewsFilterOrderCommand implements ICommand {
    private final ReviewService reviewService;
    
    public GetReviewsFilterOrderCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void invoke(List<String> tokens) {
        //    GET_REVIEWS_FILTER_ORDER  <restaurant_id> <low> <high> <order>
        try {
            Long restaurantId = Long.parseLong(tokens.get(1));
            RatingOrder ratingOrder = RatingOrder.valueOf(tokens.get(4));
            Integer low = Integer.parseInt(tokens.get(2));
            Integer high = Integer.parseInt(tokens.get(3));
            GetReviewRequestDto getReviewRequestDto = new GetReviewRequestDto(restaurantId, ratingOrder);
            getReviewRequestDto.setRatingLow(low);
            getReviewRequestDto.setRatingHigh(high);
            List<Review> allReviews = reviewService.getAllReviews(getReviewRequestDto);
            System.out.println(allReviews);
        } catch (NumberFormatException | RestaurantNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
