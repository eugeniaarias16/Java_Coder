package edu.coderhouse.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "STREET")
  private String street;

  @Column(name = "NUMBER")
  private int number;

  @Column(name = "FLOOR")
  private int floor;

  @Column(name = "APARTMENT")
  private String apartment;

  @Column(name = "CP")
  private String cp;

  @Column(name = "PROVINCE")
  private String province;

  @Column(name = "CITY")
  private String city;
}
