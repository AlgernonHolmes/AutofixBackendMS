package com.repairlist.ms.repairlistms.services;

import com.repairlist.ms.repairlistms.repositories.RepairlistRepository;
import com.repairlist.ms.repairlistms.entities.RepairlistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairlistService {

    /* Repository layer methods */

    @Autowired
    RepairlistRepository repairlistRepository;

    /* GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairlists: method to find all repair lists in the DB;
     *
     * @return - a list with all repair lists;
     --------------------------------------------------------------------------------------------------------*/

    public List<RepairlistEntity> getAllRepairlists(){
        return (List<RepairlistEntity>) repairlistRepository.findAll();
    }

    /*--------------------------------------------------------------------------------------------------------
     * getRepairlistById: method to find a specific repair list in DB by its ID;
     *
     * @param id - the ID of the repair list to find;
     * @return - the repair list with the specified ID;
     --------------------------------------------------------------------------------------------------------*/

    public RepairlistEntity getRepairlistById(Long id){
        return repairlistRepository.findById(id).orElse(null);
    }

    /*--------------------------------------------------------------------------------------------------------
     * getRepairlistByType: method to find a specific repair list in the database by its type;
     *
     * @param repairType - the type of repair to find;
     * @return - the repair list with the specified type;
     --------------------------------------------------------------------------------------------------------*/
    public RepairlistEntity getRepairlistByType(String repairType){
        return repairlistRepository.findByRepairType(repairType);
    }


    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepairlist: method to create a repair list in the DB;
     *
     * @param repairlist - the repair list to create;
     * @return - the created repair list;
     --------------------------------------------------------------------------------------------------------*/

    public RepairlistEntity createRepairlist(RepairlistEntity repairlist){
        String repairType = repairlist.getRepairType().toLowerCase();
        repairlist.setRepairType(repairType);
        return repairlistRepository.save(repairlist);
    }

    /* UPDATE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * updateRepairlist: method to update a repair list in the DB;
     *
     * @param updatedRepairlist - the updated repair list;
     * @return - the updated repair list;
     --------------------------------------------------------------------------------------------------------*/

    public RepairlistEntity updateRepairlist(RepairlistEntity updatedRepairlist){
        return repairlistRepository.save(updatedRepairlist);
    }

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepairlistById: method to delete a repair list in the DB by its ID;
     *
     * @param id - the ID of the repair list to delete;
     --------------------------------------------------------------------------------------------------------*/

    public void deleteRepairlistById(Long id){
        repairlistRepository.deleteById(id);
    }

}
