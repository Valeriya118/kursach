package com.ttnlr.planet.controller;

import com.ttnlr.planet.model.Planet;
import com.ttnlr.planet.repository.PlanetRepository;
import com.ttnlr.planet.service.PlanetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlanetControllerTest {

    @Mock
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetController planetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlanets_ReturnsOk() {
        List<Planet> planets = new ArrayList<>();
        when(planetService.getAllPlanets()).thenReturn(planets);

        ResponseEntity<List<Planet>> response = planetController.getAllPlanets();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planets, response.getBody());
    }

    @Test
    void getPlanetById_ReturnsOk() {
        Long id = 1L;
        Planet planet = new Planet();
        when(planetService.getPlanetById(id)).thenReturn(Optional.of(planet));

        ResponseEntity<Planet> response = planetController.getPlanetById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planet, response.getBody());
    }

    @Test
    void getPlanetById_ReturnsNotFound() {
        Long id = 1L;
        when(planetService.getPlanetById(id)).thenReturn(Optional.empty());

        ResponseEntity<Planet> response = planetController.getPlanetById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updatePlanetById_ReturnsOk() {
        Long id = 1L;
        Planet updatedPlanet = new Planet();
        when(planetService.putPlanetById(id, updatedPlanet)).thenReturn(Optional.of(updatedPlanet));

        ResponseEntity<Planet> response = planetController.updatePlanetById(id, updatedPlanet);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPlanet, response.getBody());
    }

    @Test
    void updatePlanetById_ReturnsNotFound() {
        Long id = 1L;
        Planet updatedPlanet = new Planet();
        when(planetService.putPlanetById(id, updatedPlanet)).thenReturn(Optional.empty());

        ResponseEntity<Planet> response = planetController.updatePlanetById(id, updatedPlanet);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deletePlanetById_ReturnsOk() {
        Long id = 1L;
        ResponseEntity<Void> response = planetController.deletePlanetById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(planetService, times(1)).deletePlanetById(id);
    }
}
