package birt.eus.getyourroutebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;


@RestController
@RequestMapping ("api/itinerarys")
public class ItineraryController  {

	@Autowired
	ItineraryRepository itineraryRepository;
	
	@GetMapping({"/",""})
	public List <Itinerary> index() {
		return itineraryRepository.findAll();
	}	
}
