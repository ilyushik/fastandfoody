package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.City;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.CityRepository;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        restaurantDTO.setCityId(restaurant.getCityId());
        restaurantDTO.setLongitude(restaurantDTO.getLongitude());
        restaurantDTO.setLatitude(restaurantDTO.getLatitude());
        return restaurantDTO;
    }

    public List<RestaurantDTO> restaurantsDTO() {
        return restaurantRepository.findAll(Sort.by("id")).stream()
                .map(this::getRestaurantDTO).collect(Collectors.toList());
    }

    public RestaurantDTO findDTOById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        return getRestaurantDTO(restaurant);
    }

    public List<RestaurantDTO> byCity() {
        City city = cityRepository.findCityByName("Mariupol");
        return restaurantRepository.findByCityId(city.getId()).stream()
                .map(this::getRestaurantDTO).collect(Collectors.toList());
    }

    public List<RestaurantDTO> findRestaurantByCity(String city) {
        List<RestaurantDTO> allRestaurants = restaurantsDTO();
        List<RestaurantDTO> restaurantsByCity = new ArrayList<>();
        for (RestaurantDTO r : allRestaurants) {
            if (r.getCityId().getName().equals(city)) {
                restaurantsByCity.add(r);
            }
        }
        return restaurantsByCity;
    }

    public Restaurant byId(int id) {
        return restaurantRepository.findById(id).orElseThrow();
    }

    public void editRestaurant(int id, Restaurant restaurant) {
        Restaurant restaurant1 = byId(id);
        restaurant1.setEmail(restaurant.getEmail());
        restaurant1.setPhone(restaurant.getPhone());
        restaurantRepository.save(restaurant1);
    }

    public void deleteById(int id) {
        restaurantRepository.deleteById(id);
    }
}
