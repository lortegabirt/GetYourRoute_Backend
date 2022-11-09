package birt.eus.getyourroutebackend.model.dto;

import java.util.List;

import birt.eus.getyourroutebackend.model.GeoLocation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageGeoLocationDTO {

	private int currentPage;
	private int totalItemsPage;
	private int totalPages;
	private long totalItems;
	private List<GeoLocation> geolocations;
	
	public PageGeoLocationDTO(int currentPage, int totalItemsPage, 
							long totalItems, int totalPages, List<GeoLocation> geolocations) {
		super();
		this.currentPage = currentPage;
		this.totalItemsPage = totalItemsPage;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.geolocations = geolocations;
	}

	@Override
	public String toString() {
		return "PageGeoLocationDTO [currentPage=" + currentPage + ", totalItemsPage=" + totalItemsPage + ", totalPages="
				+ totalPages + ", totalItems=" + totalItems + ", geolocations=" + geolocations + "]";
	}

}
