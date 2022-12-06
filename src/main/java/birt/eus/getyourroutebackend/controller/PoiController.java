package birt.eus.getyourroutebackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import birt.eus.getyourroutebackend.exceptions.PointOfInterestNotFoundException;
import birt.eus.getyourroutebackend.helper.GetYourRouteHelper;
import birt.eus.getyourroutebackend.model.FeatureType;
import birt.eus.getyourroutebackend.model.PointOfInterest;
import birt.eus.getyourroutebackend.model.dto.PageDto;
import birt.eus.getyourroutebackend.model.dto.PointOfInterestQueryParams;
import birt.eus.getyourroutebackend.repository.PointOfInterestRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/points-of-interest")
public class PoiController {

  @Autowired
  PointOfInterestRepository pointOfInterestRepository;	

  @Autowired
  GetYourRouteHelper getYourRouteHelper;	

  /**
   * Lista todos los puntos de interes
   * 
   * @param pageable
   * @return  PageDto<PointOfInterest>
   */
  @GetMapping
  public PageDto<PointOfInterest> getAll(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable,
		  								 PointOfInterestQueryParams pointOfInterestQueryParams) {
    return new PageDto<>(pointOfInterestRepository.findFiltered(pointOfInterestQueryParams.getQuery(), pageable));
  }
  
  /**
   * Pasandole un id obtener un punto de interes 
   *
   * @param id String
   * @return Itinerary
   */
   @GetMapping("/id/{id}")
   public PointOfInterest showByID(@PathVariable("id") String id) {
	 PointOfInterest pointOfInterest = pointOfInterestRepository.findById(id).orElse(null);
	 if(pointOfInterest == null) throw new PointOfInterestNotFoundException(id);
	 return pointOfInterest;
   }
  
   /**
    * Pasandole un type obtener los puntos de interes de ese tipo 
    *
    * @param id String
    * @return Itinerary
    */
    @GetMapping("/type/{type}")
    public List<PointOfInterest> showByType(@PathVariable("type") String type) {
      List<PointOfInterest>  listPointOfInterest = pointOfInterestRepository.findByType(FeatureType.valueOf(type));
 	  if(listPointOfInterest.isEmpty()) throw new PointOfInterestNotFoundException(type, "type");
 	  return listPointOfInterest;
    }

    /**
     * Pasandole lat, long y distance obtener los puntos de interes.
     *
     * @param paramsLocationNear Map<String,String>
     * @return List<PointOfInterest>
     */
     @GetMapping("/locationNear/")
     public List<PointOfInterest> showByLocationNear(@RequestParam Map<String,String> paramsLocationNear) {
      getYourRouteHelper.validateParamsLocationNear(paramsLocationNear);
      GeoJsonPoint geoJsonPoint = new GeoJsonPoint(Double.valueOf(paramsLocationNear.get("lat")),
    		  									   Double.valueOf(paramsLocationNear.get("long"))); 
      Distance distance = new Distance(Double.valueOf(paramsLocationNear.get("distance")), Metrics.KILOMETERS);
      List<PointOfInterest>  listPointOfInterest = pointOfInterestRepository.findByLocationNear(geoJsonPoint, distance);
  	  if(listPointOfInterest.isEmpty()) throw new PointOfInterestNotFoundException(paramsLocationNear.toString(), "LocationNear");
  	  return listPointOfInterest;
     }


     /**
      * Pasandole lat, long y distance obtener los puntos de interes.
      *
      * @param paramsLocationNear Map<String,String>
      * @return List<PointOfInterest>
      */
      @GetMapping("/locationWithin/")
      public List<PointOfInterest> showByLocationWithin(@RequestParam Map<String,String> paramsLocationNear) {
       getYourRouteHelper.validateParamsLocationWithin(paramsLocationNear);
       Point geoJsonPointBottomLeftCoord = new Point(Double.valueOf(paramsLocationNear.get("bottomLeftCoorLat")),
				   									               Double.valueOf(paramsLocationNear.get("bottomLeftCoorLong")));
       
       Point geoJsonPointUpperRightCoord = new Point(Double.valueOf(paramsLocationNear.get("upperRightCoorLat")),
	                                                               Double.valueOf(paramsLocationNear.get("upperRightCoorLong"))); 

       Box box = new Box(geoJsonPointBottomLeftCoord, geoJsonPointUpperRightCoord);
       List<PointOfInterest>  listPointOfInterest = pointOfInterestRepository.findByLocationWithin(box);
   	   if(listPointOfInterest.isEmpty()) throw new PointOfInterestNotFoundException(paramsLocationNear.toString(), "LocationNear");
   	   return listPointOfInterest;
      }
     
}
