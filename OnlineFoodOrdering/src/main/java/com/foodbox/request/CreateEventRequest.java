package com.foodbox.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateEventRequest {

    private String name;
    private String location;
    private List<String> image;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


}
