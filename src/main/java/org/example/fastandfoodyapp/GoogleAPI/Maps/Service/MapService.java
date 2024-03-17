package org.example.fastandfoodyapp.GoogleAPI.Maps.Service;

import org.example.fastandfoodyapp.GoogleAPI.Maps.MarkerDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MapService {

    List<MarkerDTO> getAllMarkerDTO();
}
