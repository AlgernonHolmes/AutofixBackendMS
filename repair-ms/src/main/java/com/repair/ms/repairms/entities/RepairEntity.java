package com.repair.ms.repairms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "repairHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairEntity {
    /* ATTRIBUTES */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /* next data corresponds to the vehicle
       which is being repaired */
    private String vehiclePlate;
    private String vehicleBrand;
    private String vehicleType;
    private int fabricationYear;
    private String engineType;
    private double milage;
    /* next data corresponds to
       the entry of the vehicle
       and the surcharges,
       discounts and iva
       for the finalPrice
     */
    private LocalDate entryVDate;
    private LocalTime entryVTime;
    private double totalCost;
    private double totalSurcharge;
    private double total_discount;
    private double amount_iva;
    private double finalPrice;
    /* I refer to
    *  exitVDate: vehicle exit date;
    *  exitCDate: customer exit date; */
    private LocalDate exitVDate;
    private LocalDate exitCDate;
    /* I refer to
    * exitVTime: vehicle exit time;
    * exitCTime: customer exit time; */
    private LocalTime exitVTime;
    private LocalTime exitCTime;

}
