package com.ttnlr.planet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer")
@Entity(name = "customer")
public class Customer {

    @Id
    @Column(name="id_customer")
    @GeneratedValue(generator = "id_customer_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id_customer_seq", sequenceName = "id_customer_seq", initialValue = 1, allocationSize = 1)
    private Long idCustomer;

    @NotEmpty(message = "Sorry, empty")
    @Min(value = 2, message = "Нереальное имя")
    @Max(value = 15, message = "Нереальное имя")
    @Column(name="name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    @NotEmpty(message = "Sorry, empty")
    @Min(value = 2, message = "Нереальное имя")
    @Max(value = 15, message = "Нереальное имя")
    @Column(name="surname")
    private String surname;

    @Min(value = 5, message = "телефон некорректный ")
    @NotEmpty(message = "Sorry, empty")
    @Column(name="telephone_number")
    private String telNumber;
}
