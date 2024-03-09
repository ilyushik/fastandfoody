package org.example.fastandfoodyapp.GoogleAPI.Maps.Service;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.GoogleAPI.Maps.MarkerDTO;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MapServiceImpl implements MapService{

    RestaurantRepository restaurantRepository;

    public List<MarkerDTO> getAllMarkerDTO() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<MarkerDTO> markers = restaurants.stream().map(r -> MarkerDTO.builder().id(r.getId())
                .address(r.getAddress())
                .longitude(r.getLongitude())
                .latitude(r.getLatitude()).build())
                .toList();

        return markers;
    }
}
