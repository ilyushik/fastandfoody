package org.example.fastandfoodyapp.GoogleAPI.Maps;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.GoogleAPI.Maps.Service.MapService;
import org.example.fastandfoodyapp.GoogleAPI.Maps.Service.MapServiceImpl;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MapController {

    MapService mapService;
    private RestaurantRepository restaurantRepository;

    @GetMapping("/maps")
    public List<MarkerDTO> getMarkers() {
        return mapService.getAllMarkerDTO();
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
}
