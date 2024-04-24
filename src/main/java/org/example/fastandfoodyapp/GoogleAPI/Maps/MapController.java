package org.example.fastandfoodyapp.GoogleAPI.Maps;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.GoogleAPI.Maps.Service.MapService;
import org.example.fastandfoodyapp.GoogleAPI.Maps.Service.MapServiceImpl;
import org.example.fastandfoodyapp.Model.City;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.CityRepository;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class MapController {

    MapService mapService;
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MapServiceImpl mapServiceI;

    @GetMapping("/maps")
    public List<MarkerDTO> getMarkers() {
        List<MarkerDTO> markers = mapService.getAllMarkerDTO();
        return markers;
    }

    @GetMapping("/maps/{id}")
    public MarkerDTO showMarkerById(@PathVariable("id") int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        MarkerDTO markerDTO = new MarkerDTO();
        markerDTO.setAddress(restaurant.getAddress());
        markerDTO.setLatitude(restaurant.getLatitude());
        markerDTO.setLongitude(restaurant.getLongitude());
        return markerDTO;
    }

    @GetMapping("/maps/filter")
    public List<MarkerDTO> markers(@RequestParam(value = "city", required = true, defaultValue = "Київ") String city) {
        City city1 = cityRepository.findCityByName(city);
        int cityId = city1.getId();
//        List<Restaurant> restaurants = restaurantRepository.findByCityId(cityId);
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getCityId().getId() == cityId) {
                filteredRestaurants.add(r);
            }
        }
        List<MarkerDTO> markers = mapServiceI.restaurantToMarkerDTO(filteredRestaurants);
        return markers;
    }
}
