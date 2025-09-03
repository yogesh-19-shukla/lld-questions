package org.example.foodDelivery;

import java.util.List;

public interface RestaurantSearchStrategy {
    List<Restaurant> filter(List<Restaurant> allRestaurants);
}