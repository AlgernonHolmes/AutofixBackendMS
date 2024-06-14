package com.repairlist.ms.repairlistms.controllers;

import com.repairlist.ms.repairlistms.entities.RepairlistEntity;
import com.repairlist.ms.repairlistms.services.RepairlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms/listrepair")
@CrossOrigin("*")
public class RepairlistController {

    /* API endpoints */

    /* Service layer methods */

    @Autowired
    RepairlistService repairlistService;

    /* GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairlists: method to retrieve all repair lists from the database.
     *
     * @return - a list with all repair lists.
     --------------------------------------------------------------------------------------------------------*/

    @GetMapping
    public List<RepairlistEntity> getAllRepairlists() {
        return repairlistService.getAllRepairlists();
    }

    /*--------------------------------------------------------------------------------------------------------
     * getRepairlistById: method to retrieve a specific repair list by its ID.
     *
     * @param id - the ID of the repair list to search for.
     * @return - the repair list with the specified ID.
     --------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{id}")
    public RepairlistEntity getRepairlistById(@PathVariable Long id) {
        return repairlistService.getRepairlistById(id);
    }

    /*--------------------------------------------------------------------------------------------------------
     * getRepairlistByRepairType: endpoint to retrieve a specific repair list by its type;
     *
     * @param repairType - the type of repair list to retrieve;
     * @return - the repair list with the specified type, or 404 if not found;
     --------------------------------------------------------------------------------------------------------*/
    @GetMapping("/{repairType}")
    public ResponseEntity<RepairlistEntity> getRepairlistByRepairType(@PathVariable String repairType) {
        RepairlistEntity repairlist = repairlistService.getRepairlistByType(repairType);
        if (repairlist != null) {
            return ResponseEntity.ok(repairlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepairlist: method to create a new repair list in the database.
     *
     * @param repairlist - the repair list to create.
     * @return - the created repair list.
     --------------------------------------------------------------------------------------------------------*/

    @PostMapping
    public RepairlistEntity createRepairlist(@RequestBody RepairlistEntity repairlist) {
        return repairlistService.createRepairlist(repairlist);
    }

    /* UPDATE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * updateRepairlist: method to update an existing repair list in the database.
     *
     * @param id - the ID of the repair list to update.
     * @param updatedRepairlist - the updated repair list.
     * @return - the updated repair list.
     --------------------------------------------------------------------------------------------------------*/

    @PutMapping("/{id}")
    public RepairlistEntity updateRepairlist(@PathVariable Long id, @RequestBody RepairlistEntity updatedRepairlist) {
        updatedRepairlist.setId(id);
        return repairlistService.updateRepairlist(updatedRepairlist);
    }

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepairlist: method to delete a repair list from the database by its ID.
     *
     * @param id - the ID of the repair list to delete.
     --------------------------------------------------------------------------------------------------------*/

    @DeleteMapping("/{id}")
    public void deleteRepairlist(@PathVariable Long id) {
        repairlistService.deleteRepairlistById(id);
    }

}
