package com.foodbox.controller;

import com.foodbox.Service.FoodService;
import com.foodbox.Service.RestaurantService;
import com.foodbox.Service.UserService;
import com.foodbox.model.Food;
import com.foodbox.model.Restaurant;
import com.foodbox.model.User;
import com.foodbox.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/search")
    public ResponseEntity<List<Food>>searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>>getRestaurantFood(
            @RequestParam (required = false)boolean vegetarian,
            @RequestParam (required = false)boolean seasonal,
            @RequestParam (required = false)boolean nonveg,
            @RequestParam (required = false)String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.getRestaurantFood(restaurantId,vegetarian,nonveg,seasonal,food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
