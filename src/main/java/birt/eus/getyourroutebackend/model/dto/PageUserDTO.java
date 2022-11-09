package birt.eus.getyourroutebackend.model.dto;

import java.util.List;

import birt.eus.getyourroutebackend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageUserDTO {

	private int currentPage;
	private int totalItemsPage;
	private int totalPages;
	private long totalItems;
	private List<User> users;
	
	public PageUserDTO(int currentPage, int totalItemsPage, 
							long totalItems, int totalPages, List<User> users) {
		super();
		this.currentPage = currentPage;
		this.totalItemsPage = totalItemsPage;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.users = users;
	}

	@Override
	public String toString() {
		return "PageUserDTO [currentPage=" + currentPage + ", totalItemsPage=" + totalItemsPage + ", totalPages="
				+ totalPages + ", totalItems=" + totalItems + ", users=" + users + "]";
	}
}
