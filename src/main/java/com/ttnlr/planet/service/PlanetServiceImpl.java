package com.ttnlr.planet.service;

import com.ttnlr.planet.exception.ExceptionHandler;
import com.ttnlr.planet.model.Planet;
import com.ttnlr.planet.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final ExceptionHandler exceptionHandler;

    public PlanetServiceImpl(PlanetRepository planetRepository, ExceptionHandler exceptionHandler) {
        this.planetRepository = planetRepository;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void addPlanet(Planet planet) {
        planetRepository.save(planet);
    }

    @Override
    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    @Override
    public Optional<Planet> getPlanetById(Long id) {
        return planetRepository.findById(id);
    }

    @Override
    public Optional<Planet> putPlanetById(Long id, Planet updatedPlanet) {
        Optional<Planet> existingPlanet = planetRepository.findById(id);
        if (existingPlanet.isPresent()) {
            Planet planetToUpdate = existingPlanet.get();

            if (updatedPlanet.getPlanetName() != null) {
                planetToUpdate.setPlanetName(updatedPlanet.getPlanetName());
            }
            if (updatedPlanet.getPrice() != null) {
                planetToUpdate.setPrice(updatedPlanet.getPrice());
            }
            if (updatedPlanet.getCountAvailable() != null) {
                planetToUpdate.setCountAvailable(updatedPlanet.getCountAvailable());
            }

            planetRepository.save(planetToUpdate);
        }
        return existingPlanet;
    }

    @Override
    public void deletePlanetById(Long id) {
        planetRepository.deleteById(id);

    }
}
