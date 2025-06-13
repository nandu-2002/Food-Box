package com.foodbox.repository;

import com.foodbox.model.Event;
import com.foodbox.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByRestaurant(Restaurant restaurant);
}

