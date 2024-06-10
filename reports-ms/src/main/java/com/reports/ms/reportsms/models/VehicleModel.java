package com.reports.ms.reportsms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {

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
