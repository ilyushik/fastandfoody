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

        List<MarkerDTO> markers = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            markers.add(MarkerDTO.builder()
                    .id(restaurant.getId())
                    .address(restaurant.getAddress())
                    .longitude(restaurant.getLongitude())
                    .latitude(restaurant.getLatitude())
                    .build());
        }

        return markers;
    }
}
