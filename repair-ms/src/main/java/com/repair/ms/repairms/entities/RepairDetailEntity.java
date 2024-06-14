package com.repair.ms.repairms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "repairdetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairDetailEntity {
    /* ATTRIBUTES */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehiclePlate;
    private String repairType;
    private LocalDate repairDate;
    private LocalTime repairTime;
    private double repairCost;

}
