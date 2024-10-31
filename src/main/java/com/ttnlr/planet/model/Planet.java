package com.ttnlr.planet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="planet")
@Table(name="planet")
public class Planet {

    @Id
    @Column(name="id_planet")
    @GeneratedValue(generator = "id_planet_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id_planet_seq", sequenceName = "id_planet_seq", initialValue = 1, allocationSize = 1)
    private Long idPlanet;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="planet_name")
    private String planetName;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="planet_available")
    private Long countAvailable;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="price")
    private Long price;

    @JsonIgnore
    @OneToMany(mappedBy = "planet")
    private List<Purchase> purchased;

}
