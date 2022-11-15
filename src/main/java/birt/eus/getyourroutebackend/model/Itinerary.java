package birt.eus.getyourroutebackend.model;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("itineraries")
public class Itinerary {

    @Id
    private String id;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String name;
    private String description;
    private String idUser;
    @DBRef
    private User user;

    public Itinerary(LocalDateTime beginDate, LocalDateTime endDate, String name, String description, User user) {
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
