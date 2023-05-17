package com.example.demo;

import com.example.demo.commands.AddRatingCommand;
import com.example.demo.commands.AddRestaurantCommand;
import com.example.demo.commands.AddReviewCommand;
import com.example.demo.commands.AddUserCommand;
import com.example.demo.commands.CommandKeyword;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.DescribeRestaurantCommand;
import com.example.demo.commands.GetReviewCommand;
import com.example.demo.commands.GetReviewsFilterOrderCommand;
import com.example.demo.commands.ListRestaurantsCommand;
import com.example.demo.entities.FilterType;
import com.example.demo.entities.RatingOrder;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.ReviewService;
import com.example.demo.services.UserService;
import com.example.demo.strategy.FilterStrategyProvider;
import com.example.demo.strategy.OrderStrategyProvider;
import com.example.demo.strategy.ReviewFilterByRatingRange;
import com.example.demo.strategy.ReviewOrderByRatingAsc;
import com.example.demo.strategy.ReviewOrderByRatingDesc;

public class Configuration {
            // Singleton Pattern
            //create an object of Single Configuration Object
            private static Configuration instance = new Configuration();

            //make the constructor private so that this class cannot be
            //instantiated
            private Configuration(){}

            //Get the only object available
            public static Configuration getInstance(){
                if (instance == null) {
                    instance = new Configuration();
                }
                return instance;
            }

            public CommandRegistry getCommandRegistry(){
                registerCommands();
                registerStrategies();
                return commandRegistry;
            }

    		// Initialize repositories
            private final RestaurantRepository restaurantRepository = new RestaurantRepository();
            private final UserRepository userRepository = new UserRepository();
            private final ReviewRepository reviewRepository = new ReviewRepository();
           
            // Initialize services
            private final UserService userService = new UserService(userRepository);
            private final RestaurantService restaurantService = new RestaurantService(restaurantRepository);
            private final ReviewService reviewService = new ReviewService(reviewRepository, userService, restaurantService);
            
            // Initialize strategies
            ReviewFilterByRatingRange reviewFilterByRatingRange = new ReviewFilterByRatingRange();
            ReviewOrderByRatingAsc reviewOrderByRatingAsc = new ReviewOrderByRatingAsc();
            ReviewOrderByRatingDesc reviewOrderByRatingDesc = new ReviewOrderByRatingDesc();
            
            // Initialize commands
            private final AddUserCommand addUserCommand = new AddUserCommand(userService);
            private final AddRestaurantCommand addRestaurantCommand = new AddRestaurantCommand(restaurantService);
            private final AddRatingCommand addRatingCommand = new AddRatingCommand(reviewService);
            private final AddReviewCommand addReviewCommand = new AddReviewCommand(reviewService);
            private final GetReviewCommand getReviewCommand = new GetReviewCommand(reviewService);
            private final GetReviewsFilterOrderCommand getReviewsFilterOrderCommand = new GetReviewsFilterOrderCommand(reviewService);
            private final ListRestaurantsCommand listRestaurantsCommand = new ListRestaurantsCommand(restaurantService);
            private final DescribeRestaurantCommand describeRestaurantCommand = new DescribeRestaurantCommand(restaurantService);
            
            // Initialize commandRegistery
            private final CommandRegistry commandRegistry = new CommandRegistry();

            // Register commands 
            private void registerCommands(){
                commandRegistry.registerCommand(CommandKeyword.REGISTER_USER.name(), addUserCommand);
                commandRegistry.registerCommand(CommandKeyword.REGISTER_RESTAURANT.name(), addRestaurantCommand);
                commandRegistry.registerCommand(CommandKeyword.ADD_RATING.name(), addRatingCommand);
                commandRegistry.registerCommand(CommandKeyword.ADD_REVIEW.name(), addReviewCommand);
                commandRegistry.registerCommand(CommandKeyword.GET_REVIEWS.name(), getReviewCommand);
                commandRegistry.registerCommand(CommandKeyword.GET_REVIEWS_FILTER_ORDER.name(), getReviewsFilterOrderCommand);
                commandRegistry.registerCommand(CommandKeyword.DESCRIBE_RESTAURANT.name(), describeRestaurantCommand);
                commandRegistry.registerCommand(CommandKeyword.LIST_RESTAURANT.name(), listRestaurantsCommand);
            }
            
            private void registerStrategies() {
                FilterStrategyProvider.register(FilterType.RATING_RANGE.name(), reviewFilterByRatingRange);
                OrderStrategyProvider.register(RatingOrder.RATING_ASC.name(), reviewOrderByRatingAsc);
                OrderStrategyProvider.register(RatingOrder.RATING_DESC.name(), reviewOrderByRatingDesc);
            }
}
