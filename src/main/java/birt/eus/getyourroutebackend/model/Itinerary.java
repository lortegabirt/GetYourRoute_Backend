package birt.eus.getyourroutebackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("itineraries")
public class Itinerary {
    @Id
    private String id;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String name;
    private String description;
    private User user;

}
