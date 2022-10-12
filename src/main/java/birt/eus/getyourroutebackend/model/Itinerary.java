package birt.eus.getyourroutebackend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("itineraries")
public class Itinerary {

    @Id
    private String id;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String name;
    private String description;
    @DBRef
    private User user;
	   
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDateTime beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Itinerary(LocalDateTime beginDate, LocalDateTime endDate, String name, String description, User user) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.name = name;
		this.description = description;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", beginDate=" + beginDate + ", endDate=" + endDate + ", name=" + name
				+ ", description=" + description + ", user=" + user + "]";
	}
}
