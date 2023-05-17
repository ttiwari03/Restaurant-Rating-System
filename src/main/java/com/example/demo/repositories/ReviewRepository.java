package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.Review;

public class ReviewRepository implements CrudRepository<Review, Long> {
    private final Map<Long, Review> reviewMap;
    private Long autoIncrementId;

    public ReviewRepository() {
        reviewMap = new HashMap<>();
        autoIncrementId = 1L;
    }
    
    @Override
    public Review save(Review review) {
        Review newReview = new Review(autoIncrementId, review.getRating(), review.getUser(), review.getRestaurant());
        newReview.setDescription(review.getDescription());
        newReview.setDishes(review.getDishes());
        newReview.getUser().incrementRatingCount();
        newReview.getRestaurant().increaseReviewCount();
        newReview.getRestaurant().increaseTotalRating(newReview.getRating());
        reviewMap.put(autoIncrementId, newReview);
        autoIncrementId++;
        return newReview; 
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(reviewMap.get(id));
    }

    @Override
    public List<Review> findAll() {
        return new ArrayList<>(reviewMap.values());
    }

    @Override
    public void delete(Review review) {
        if (reviewMap.containsValue(review)) {
            deleteById(review.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        reviewMap.remove(id);
    }

    @Override
    public Long count() {
        return (long) reviewMap.size();
    }

    @Override
    public Boolean existById(Long id) {
        return reviewMap.containsKey(id);
    }
    
    public List<Review> findByRestaurantId(Long restaurantId) {
        return reviewMap.values().stream().filter(review -> review.getRestaurant().getId() == restaurantId)
            .collect(Collectors.toList());
    }
}
