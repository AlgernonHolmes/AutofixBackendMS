package com.repairlist.ms.repairlistms.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repairlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairlistEntity {

    /* ATRIBUTES */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String repairType;
    private Double gasolinePrice;
    private Double dieselPrice;
    private Double hybridPrice;
    private Double electricPrice;

}
