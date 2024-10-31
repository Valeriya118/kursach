package com.ttnlr.planet.repository;

import com.ttnlr.planet.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long>, PagingAndSortingRepository<Planet, Long> {

}
