package com.example.demo.strategy;

import java.util.HashMap;
import java.util.Map;

public class OrderStrategyProvider {
    private static final Map<String, IOrderStrategy> orderStrategies = new HashMap<>();

    public static void register(String name, IOrderStrategy strategy) {
        if (name != null && strategy != null) {
            orderStrategies.putIfAbsent(name, strategy);
        }
    }

    public static IOrderStrategy getStrategy(String name) {
        return orderStrategies.get(name);
    }
    
}
