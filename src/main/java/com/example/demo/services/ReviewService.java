package com.example.demo.services;

import java.util.List;
import com.example.demo.Constants;
import com.example.demo.dtos.AddReviewRequestDto;
import com.example.demo.dtos.GetReviewRequestDto;
import com.example.demo.entities.FilterType;
import com.example.demo.entities.RatingOrder;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Review;
import com.example.demo.entities.User;
import com.example.demo.exceptions.InvalidRatingException;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.strategy.FilterStrategyProvider;
import com.example.demo.strategy.IFilterStrategy;
import com.example.demo.strategy.IOrderStrategy;
import com.example.demo.strategy.OrderStrategyProvider;


public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;

    public ReviewService(ReviewRepository reviewRepository,
        UserService userService, RestaurantService restaurantService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    private boolean isValidRating(Integer rating) {
        return rating >= Constants.LOW_RATING && rating <= Constants.HIGH_RATING;
    }

    public Review addReview(AddReviewRequestDto addReviewRequestDto) 
            throws UserNotFoundException, RestaurantNotFoundException, InvalidRatingException {
        Integer rating = addReviewRequestDto.getRating();  
        if (!isValidRating(rating)) {
            throw new InvalidRatingException("Invalid rating.");
        }
        User user = getUser(addReviewRequestDto.getUserId());
        Restaurant restaurant = getRestaurant(addReviewRequestDto.getRestaurantId());  
        Review review = new Review(rating, user, restaurant);
        review.setDescription(addReviewRequestDto.getDescription());
        review.setDishes(addReviewRequestDto.getDishes());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(GetReviewRequestDto getReviewRequestDto) 
        throws RestaurantNotFoundException {
        List<Review> allReviews = reviewRepository.findByRestaurantId(getReviewRequestDto.getRestaurantId());
        RatingOrder ratingOrder = getReviewRequestDto.getRatingOrder();
        FilterType filterType = getReviewRequestDto.getFilterType();
        IFilterStrategy filterStrategy = FilterStrategyProvider.getStrategy(filterType.name());    
        List<Review> filteredReviews = filterStrategy.getReviewsByFilter(allReviews, getReviewRequestDto);
        IOrderStrategy orderStrategy = OrderStrategyProvider.getStrategy(ratingOrder.name());
        List<Review> orderedReviews = orderStrategy.getReviewsByOrder(filteredReviews);
        return orderedReviews;
    }

    private Restaurant getRestaurant(Long restaurantId) throws RestaurantNotFoundException {
        return restaurantService.getById(restaurantId);
    }

    private User getUser(Long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }
    
}
