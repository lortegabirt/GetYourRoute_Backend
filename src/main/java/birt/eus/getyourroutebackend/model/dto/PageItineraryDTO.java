package birt.eus.getyourroutebackend.model.dto;

import java.util.List;

import birt.eus.getyourroutebackend.model.Itinerary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageItineraryDTO {

	private int currentPage;
	private int totalItemsPage;
	private int totalPages;
	private long totalItems;
	private List<Itinerary> itinerarys;
	
	public PageItineraryDTO(int currentPage, int totalItemsPage, 
							long totalItems, int totalPages, List<Itinerary> itinerarys) {
		super();
		this.currentPage = currentPage;
		this.totalItemsPage = totalItemsPage;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.itinerarys = itinerarys;
	}

	@Override
	public String toString() {
		return "PageItineraryDTO [currentPage=" + currentPage + ", totalItemsPage=" + totalItemsPage + ", totalPages="
				+ totalPages + ", totalItems=" + totalItems + ", itinerarys=" + itinerarys + "]";
	}
}
