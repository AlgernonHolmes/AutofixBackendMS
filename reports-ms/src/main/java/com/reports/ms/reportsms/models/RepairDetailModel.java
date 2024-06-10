package com.reports.ms.reportsms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairDetailModel {
    /* ATTRIBUTES */
    private Long id;
    private String vehiclePlate;
    private String repairType;
    private LocalDate repairDate;
    private LocalTime repairTime;
    private double repairCost;

}
