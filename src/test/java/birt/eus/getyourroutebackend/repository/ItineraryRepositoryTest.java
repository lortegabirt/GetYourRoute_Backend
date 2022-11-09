package birt.eus.getyourroutebackend.repository;

import birt.eus.getyourroutebackend.model.Itinerary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class ItineraryRepositoryTest {

  @Autowired
  private ItineraryRepository itineraryRepository;

  @Test
  void testPagination() throws JsonProcessingException {
    Pageable pageable = PageRequest.of(1, 4);
    Page<Itinerary> page = itineraryRepository.findAll(pageable);
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).registerModule(new JavaTimeModule());
    System.out.println(mapper.writeValueAsString(page));
  }
}
