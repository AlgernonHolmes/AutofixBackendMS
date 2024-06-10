package com.reports.ms.reportsms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportOne")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportOneEntity {

    /* ATTRIBUTES */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String repairType;
    private double sedanAmount;
    private double hatchbackAmount;
    private double SUVAmount;
    private double pickupAmount;
    private double furgonetaAmount;
    private double totalAmount;
}
