package com.entrega2.example.dto;

import com.entrega2.example.entities.Client;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer dni;
    private LocalDate birthDate;

    // Constructor vacío
    public ClientDTO() {}

    // Constructor para convertir de Client a ClientDTO
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.dni = client.getDni();
        this.birthDate = client.getBirthDate();
    }

    // Constructor con parámetros
    public ClientDTO(Long id, String firstName, String lastName, Integer dni, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.birthDate = birthDate;
    }

    // Método para convertir de ClientDTO a Client
    public Client toEntity() {
        Client client = new Client();
        client.setId(this.id);
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setDni(this.dni);
        client.setBirthDate(this.birthDate);
        return client;
    }
}
