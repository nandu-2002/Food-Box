package com.foodbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @Column(length = 10000)
    @ElementCollection
    private List<String> image;
    @Column(name = "start_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_time")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


}

