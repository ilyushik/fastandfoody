package org.example.fastandfoodyapp.GoogleAPI.Maps;

import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {
    @Autowired
    RestaurantRepository restaurantRepository;

    public List<MarkerDTO> markerDTO() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<MarkerDTO> markers = restaurants.stream().map(r -> MarkerDTO.builder().id(r.getId())
                .address(r.getAddress())
                .longitude(r.getLongitude())
                .latitude(r.getLatitude()).build())
                .toList();

        return markers;
    }
}
