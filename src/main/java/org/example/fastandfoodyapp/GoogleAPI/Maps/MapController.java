package org.example.fastandfoodyapp.GoogleAPI.Maps;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.GoogleAPI.Maps.Service.MapService;
import org.example.fastandfoodyapp.GoogleAPI.Maps.Service.MapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MapController {

    MapService mapService;

    @GetMapping("/maps")
    public List<MarkerDTO> getMarkers() {
        return mapService.getAllMarkerDTO();
    }
}
