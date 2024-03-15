package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.City;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.CityRepository;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CityRepository cityRepository;

    private RestaurantDTO getRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setPhone(restaurant.getPhone());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setEmail(restaurant.getEmail());
        restaurantDTO.setCity_id(restaurant.getCity_id());
        restaurantDTO.setLongitude(restaurantDTO.getLongitude());
        restaurantDTO.setLatitude(restaurantDTO.getLatitude());
        return restaurantDTO;
    }

    public List<RestaurantDTO> restaurantsDTO() {
        return restaurantRepository.findAll(Sort.by("id")).stream()
                .map(this::getRestaurantDTO).collect(Collectors.toList());
    }

    // НЕ РАБОТАЕТ
    public List<RestaurantDTO> findByCity(String city) {
        City city1 = cityRepository.findCityByName(city);
        int cityId = city1.getId();
        return restaurantRepository.findByCity_id(cityId).stream()
                .map(this::getRestaurantDTO).collect(Collectors.toList());
    }

    public RestaurantDTO findDTOById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        return getRestaurantDTO(restaurant);
    }

    public List<RestaurantDTO> byCity() {
        City city = cityRepository.findCityByName("Mariupol");
        return restaurantRepository.findByCity_id(city.getId()).stream()
                .map(this::getRestaurantDTO).collect(Collectors.toList());
    }
}
