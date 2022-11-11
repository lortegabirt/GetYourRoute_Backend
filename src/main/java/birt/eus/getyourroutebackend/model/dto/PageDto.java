package birt.eus.getyourroutebackend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDto<T> {
  private int currentPage;
  private int totalItemsPage;
  private int totalPages;
  private long totalItems;
  private List<T> content = new ArrayList<>();

  public PageDto(Page<T> page) {
    this.currentPage = page.getNumber() + 1;
    this.totalItemsPage = page.getNumberOfElements();
    this.totalItems = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.content = page.getContent();
  }
}
