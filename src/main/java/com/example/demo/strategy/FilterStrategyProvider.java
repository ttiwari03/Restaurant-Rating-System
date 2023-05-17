package com.example.demo.strategy;

import java.util.HashMap;
import java.util.Map;

public class FilterStrategyProvider {
    private static final Map<String, IFilterStrategy> filterStrategies = new HashMap<>();

    public static void register(String strategyName, IFilterStrategy strategy) {
        if (strategyName != null && strategy != null) {
            filterStrategies.putIfAbsent(strategyName, strategy);
        }
    }

    public static IFilterStrategy getStrategy(String name) {
        return filterStrategies.get(name);
    }
}
