package com.repair.ms.repairms.controller;


import com.repair.ms.repairms.entities.RepairEntity;
import com.repair.ms.repairms.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ms/repair")
public class RepairController {

    @Autowired
    RepairService repairService;

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepair: method to create a new repair entity in the database;
     *
     * @param repairEntity - the repair entity to be created;
     * @return - the created repair entity;
     --------------------------------------------------------------------------------------------------------*/
    @PostMapping("/create")
    public RepairEntity createRepair(@RequestBody RepairEntity repairEntity) {
        return repairService.createRepair(repairEntity);
    }

    /* GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairs: method to retrieve all repair entities from the database;
     *
     * @return - a list containing all repair entities;
     --------------------------------------------------------------------------------------------------------*/
    @GetMapping("/all")
    public List<RepairEntity> getAllRepairs() {
        return repairService.getAllRepairs();
    }

    /*--------------------------------------------------------------------------------------------------------
     * getRepairByPlate: method to retrieve a repair entity by vehicle plate;
     *
     * @param vehiclePlate - the vehicle plate for which the repair entity is requested;
     * @return - the retrieved repair entity;
     --------------------------------------------------------------------------------------------------------*/
    @GetMapping("/{vehiclePlate}")
    public RepairEntity getRepairByPlate(@PathVariable String vehiclePlate) {
        return repairService.getRepairByPlate(vehiclePlate);
    }

    /* UPDATE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * updateRepair: method to update an existing repair entity in the database;
     *
     * @param id - the ID of the repair entity to be updated;
     * @param updatedRepair - the updated repair entity;
     * @return - the updated repair entity;
     --------------------------------------------------------------------------------------------------------*/
    @PutMapping("/update/{id}")
    public Optional<RepairEntity> updateRepair(@PathVariable Long id, @RequestBody RepairEntity updatedRepair) {
        return repairService.updateRepair(id, updatedRepair);
    }

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepair: method to delete a repair entity from the database by its ID;
     *
     * @param id - the ID of the repair entity to be deleted;
     --------------------------------------------------------------------------------------------------------*/
    @DeleteMapping("/delete/{id}")
    public void deleteRepair(@PathVariable Long id) {
        repairService.deleteRepair(id);
    }
}

