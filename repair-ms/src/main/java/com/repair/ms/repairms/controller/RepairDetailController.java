package com.repair.ms.repairms.controller;

import com.repair.ms.repairms.entities.RepairDetailEntity;
import com.repair.ms.repairms.services.RepairDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ms/repairdetail")
public class RepairDetailController {

    /* Service layer methods */

    @Autowired
    RepairDetailService repairDetailService;

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepairDetail: Method to create a new repair detail entity in the database.
     *
     * @param repairDetailEntity - the repair detail entity to be created.
     * @return - the created repair detail entity.
     --------------------------------------------------------------------------------------------------------*/
    @PostMapping
    public RepairDetailEntity createRepairDetail(@RequestBody RepairDetailEntity repairDetailEntity) {
        return repairDetailService.createRepairDetail(repairDetailEntity);
    }

    /*GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairDetails: Method to retrieve all repair detail entities from the database.
     *
     * @return - List of all repair detail entities.
     --------------------------------------------------------------------------------------------------------*/
    @GetMapping
    public List<RepairDetailEntity> getAllRepairDetails() {
        return repairDetailService.getAllRepairDetails();
    }


    /* UPDATE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * updateRepairDetail: Method to update an existing repair detail entity in the database.
     *
     * @param id - the ID of the repair detail entity to update.
     * @param updatedRepairDetail - the updated repair detail entity.
     * @return - the updated repair detail entity, or empty if not found.
     --------------------------------------------------------------------------------------------------------*/
    @PutMapping("/{id}")
    public Optional<RepairDetailEntity> updateRepairDetail(@PathVariable Long id, @RequestBody RepairDetailEntity updatedRepairDetail) {
        return repairDetailService.updateRepairDetail(id, updatedRepairDetail);
    }

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepairDetail: Method to delete a repair detail entity from the database.
     *
     * @param id - the ID of the repair detail entity to delete.
     --------------------------------------------------------------------------------------------------------*/
    @DeleteMapping("/{id}")
    public void deleteRepairDetail(@PathVariable Long id) {
        repairDetailService.deleteRepairDetail(id);
    }
}