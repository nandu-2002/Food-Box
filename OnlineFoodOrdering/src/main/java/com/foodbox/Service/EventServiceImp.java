package com.foodbox.Service;

import com.foodbox.model.Event;
import com.foodbox.model.Restaurant;
import com.foodbox.repository.EventRepository;
import com.foodbox.repository.RestaurantRepository;
import com.foodbox.request.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImp implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Event createEvent(CreateEventRequest request, Long restaurantId) throws Exception {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (!restaurantOpt.isPresent()) {
            throw new Exception("Restaurant not found with id " + restaurantId);
        }

        Restaurant restaurant = restaurantOpt.get();

        Event event = new Event();
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        event.setImage(request.getImage());
        event.setStartDateTime(request.getStartDateTime());
        event.setEndDateTime(request.getEndDateTime());
        event.setRestaurant(restaurant);

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long eventId, CreateEventRequest request) throws Exception {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (!eventOpt.isPresent()) {
            throw new Exception("Event not found with id " + eventId);
        }

        Event event = eventOpt.get();
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        event.setImage(request.getImage());
        event.setStartDateTime(request.getStartDateTime());
        event.setEndDateTime(request.getEndDateTime());

        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) throws Exception {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (!eventOpt.isPresent()) {
            throw new Exception("Event not found with id " + eventId);
        }

        eventRepository.delete(eventOpt.get());
    }

    @Override
    public List<Event> getEventsByRestaurant(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (!restaurantOpt.isPresent()) {
            throw new Exception("Restaurant not found with id " + restaurantId);
        }

        return eventRepository.findByRestaurant(restaurantOpt.get());
    }
}
