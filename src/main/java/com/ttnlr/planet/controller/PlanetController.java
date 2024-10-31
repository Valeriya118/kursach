package com.ttnlr.planet.controller;

import com.ttnlr.planet.model.Planet;
import com.ttnlr.planet.service.PlanetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planet")
public class PlanetController {

    private final PlanetService planetService;

    @PostMapping
    public ResponseEntity<Void> addPlanet(@RequestBody @Valid Planet planet,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Обработка ошибок валидации
            return ResponseEntity.badRequest().build();
        }
        planetService.addPlanet(planet);
        return ResponseEntity.ok().build();

    }


    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets(){
        return ResponseEntity.ok(planetService.getAllPlanets());

    }


    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable Long id){
        Optional<Planet> planetOptional = planetService.getPlanetById(id);
        if (planetOptional.isPresent()) {
            return ResponseEntity.ok(planetOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Planet> updatePlanetById(@PathVariable Long id, @RequestBody Planet updatedPlanet) {
        Optional<Planet> updatedPlanetOptional = planetService.putPlanetById(id, updatedPlanet);
        if (updatedPlanetOptional.isPresent()) {
            return ResponseEntity.ok(updatedPlanetOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanetById(@PathVariable Long id){
        planetService.deletePlanetById(id);
        return ResponseEntity.ok().build();
    }

}
