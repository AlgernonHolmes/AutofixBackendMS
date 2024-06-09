package com.repair.ms.repairms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairlistModel {
    private Long id;
    private String repairType;
    private Double gasolinePrice;
    private Double dieselPrice;
    private Double hybridPrice;
    private Double electricPrice;
}
