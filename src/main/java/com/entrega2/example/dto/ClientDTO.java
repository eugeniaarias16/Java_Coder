package com.entrega2.example.dto;

import com.entrega2.example.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer dni;
    private LocalDate birthDate;

    // Constructor para convertir de Client a ClientDTO
    public ClientDTO(Client client) {
        if (client != null) {
            this.id = client.getId();
            this.firstName = client.getFirstName();
            this.lastName = client.getLastName();
            this.dni = client.getDni();
            this.birthDate = client.getBirthDate();
        }
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


    // Método explícito para establecer el id (si Lombok no funciona)
    public void setId(Long id) {
        this.id = id;
    }
}
