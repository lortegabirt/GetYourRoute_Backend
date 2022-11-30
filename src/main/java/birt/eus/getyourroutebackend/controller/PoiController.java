package birt.eus.getyourroutebackend.controller;

import birt.eus.getyourroutebackend.model.PointOfInterest;
import birt.eus.getyourroutebackend.model.dto.PageDto;
import birt.eus.getyourroutebackend.repository.PointOfInterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/points-of-interest")
public class PoiController {

  private final PointOfInterestRepository pointOfInterestRepository;

  @GetMapping
  public PageDto<PointOfInterest> getAll(@PageableDefault(size = Integer.MAX_VALUE)Pageable pageable) {
    return new PageDto<>(pointOfInterestRepository.findAll(pageable));
  }
}
