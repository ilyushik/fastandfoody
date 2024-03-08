package org.example.fastandfoodyapp.GoogleAPI.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
    @Autowired
    MapService mapService;

    @GetMapping("/maps")
    public List<MarkerDTO> getMarkers() {
        return mapService.markerDTO();
    }
}
