package com.foodbox.controller;

import com.foodbox.Service.EventService;
import com.foodbox.Service.RestaurantService;
import com.foodbox.Service.UserService;
import com.foodbox.model.Event;
import com.foodbox.model.Restaurant;
import com.foodbox.model.User;
import com.foodbox.request.CreateEventRequest;
import com.foodbox.request.CreateRestaurantRequest;
import com.foodbox.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @PostMapping()
    public ResponseEntity<Restaurant>createRestaurant(
        @RequestBody CreateRestaurantRequest req,
        @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.createRestaurant(req,user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Restaurant>updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>deleteRestaurant(

            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        restaurantService.deleteRestaurant(id);

        MessageResponse res=new MessageResponse();
        res.setMessage("restaurant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant>updateRestaurantStatus(

            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.updateRestaurantStatus(id);


        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant>findRestaurantByUserId(

            @RequestHeader("Authorization") String jwt

    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());


        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping("/{restaurantId}/event")
    public ResponseEntity<Event> createEvent(
            @RequestBody CreateEventRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long restaurantId
    ) throws Exception {
        // Assuming the user is authenticated, we fetch the restaurant and create the event.
        Event event = eventService.createEvent(req, restaurantId);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/event/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @RequestBody CreateEventRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long eventId
    ) throws Exception {
        Event event = eventService.updateEvent(eventId, req);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<MessageResponse> deleteEvent(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long eventId
    ) throws Exception {
        eventService.deleteEvent(eventId);
        MessageResponse response = new MessageResponse();
        response.setMessage("Event deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}/event")
    public ResponseEntity<List<Event>> getEventsByRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long restaurantId
    ) throws Exception {
        List<Event> events = eventService.getEventsByRestaurant(restaurantId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
