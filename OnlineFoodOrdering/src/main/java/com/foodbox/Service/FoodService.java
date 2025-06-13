package com.foodbox.Service;

import com.foodbox.model.Category;
import com.foodbox.model.Food;
import com.foodbox.model.Restaurant;
import com.foodbox.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {


    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId)throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian,
                                        boolean isNonveg,
                                        boolean isSeasonal, String foodCategory);

    public List<Food>searchFood(String keyword);

    public Food findFoodById(Long foodId)throws Exception;

    public Food updateAvailabilityStatus(Long foodId)throws Exception;

}
