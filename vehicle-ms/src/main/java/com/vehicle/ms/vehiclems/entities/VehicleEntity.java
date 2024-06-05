package com.vehicle.ms.vehiclems.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

    /* ATRIBUTES */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String registrationPlate;
    private String brand;
    private String model;
    private String type;
    private int fabricationYear;
    private String engineType;
    private Double numSeats;
    private Double milage;
}
