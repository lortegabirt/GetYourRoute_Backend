package birt.eus.getyourroutebackend.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("itineraries")
public class Itinerary {

    @Id
    private String id;
    private Instant beginDate;
    private Instant endDate;
    private String name;
    private String description;
    private String idUser;
    @DBRef
    private User user;

    public Itinerary(Instant beginDate, Instant endDate, String name, String description, User user) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.name = name;
		this.description = description;
		this.user = user;
		this.idUser = user.getId();
	}

	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", beginDate=" + beginDate + ", endDate=" + endDate + ", name=" + name
				+ ", description=" + description + ", idUser=" + idUser + ", user=" + user + "]";
	}
}
