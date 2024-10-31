package com.ttnlr.planet.service;

import com.ttnlr.planet.model.Planet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlanetService {

    void addPlanet(Planet planet);

    List<Planet> getAllPlanets();

    Optional<Planet> getPlanetById(Long id);

    Optional<Planet> putPlanetById(Long id, Planet updatedPlanet);

    void deletePlanetById(Long id);

}