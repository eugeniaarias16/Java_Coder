package com.entrega2.example.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    private String firstName;

    @NotNull(message = "El apellido no puede ser nulo")
    @Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50 caracteres")
    private String lastName;

    @NotNull(message = "El DNI no puede ser nulo")
    @Min(value = 0, message = "El DNI debe ser un número positivo")
    private Integer dni;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sale> sales = new ArrayList<>();
}