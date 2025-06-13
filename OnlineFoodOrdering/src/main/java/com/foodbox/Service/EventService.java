package com.foodbox.Service;

import com.foodbox.model.Event;
import com.foodbox.request.CreateEventRequest;

import java.util.List;

public interface EventService {
    public Event createEvent(CreateEventRequest request, Long restaurantId) throws Exception;

    public Event updateEvent(Long eventId, CreateEventRequest request) throws Exception;

    public void deleteEvent(Long eventId) throws Exception;

    public List<Event> getEventsByRestaurant(Long restaurantId) throws Exception;
}
