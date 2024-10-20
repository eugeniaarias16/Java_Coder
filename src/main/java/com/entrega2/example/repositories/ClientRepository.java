package com.entrega2.example.repositories;

import com.entrega2.example.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> { }

