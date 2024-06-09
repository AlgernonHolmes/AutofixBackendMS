package com.repair.ms.repairms.services;

import com.repair.ms.repairms.entities.RepairDetailEntity;
import com.repair.ms.repairms.models.RepairlistModel;
import com.repair.ms.repairms.repositories.RepairDetailRepository;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RepairDetailService {

    @Autowired
    RepairDetailRepository repairDetailRepository;

    @Autowired
    RestTemplate restTemplate;

    /* Aux methods */

    /*--------------------------------------------------------------------------------------------------------
     * getRepairlistByRepairType: method to retrieve a repair list by its repair type.
     *
     * @param repairType - The type of repair list to retrieve.
     * @return - The repair list with the specified type.
     --------------------------------------------------------------------------------------------------------*/
    public RepairlistModel getRepairlistByRepairType(String repairType) {
        ResponseEntity<RepairlistModel> responseEntity = restTemplate.exchange(
                "httpdsfsdf" + repairType,
                HttpMethod.GET,
                null,
                RepairlistModel.class
        );
        return responseEntity.getBody();
    }

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepairDetail: method to create a new repair detail entity in the database;
     *
     * @param repairDetailEntity - the repair detail entity to be created;
     * @return - the created repair detail entity;
     --------------------------------------------------------------------------------------------------------*/
    public RepairDetailEntity createRepairDetail(RepairDetailEntity repairDetailEntity) {
        return repairDetailRepository.save(repairDetailEntity);
    }


    /*--------------------------------------------------------------------------------------------------------
     * updateRepairDetail: method to update an existing repair detail entity in the database;
     *
     * @param id - the ID of the repair detail entity to update;
     * @param updatedRepairDetail - the updated repair detail entity;
     * @return - the updated repair detail entity, or empty if not found;
     --------------------------------------------------------------------------------------------------------*/
    public Optional<RepairDetailEntity> updateRepairDetail(Long id, RepairDetailEntity updatedRepairDetail) {
        return repairDetailRepository.findById(id).map(repairDetail -> {
            repairDetail.setVehiclePlate(updatedRepairDetail.getVehiclePlate());
            repairDetail.setRepairType(updatedRepairDetail.getRepairType());
            repairDetail.setRepairDate(updatedRepairDetail.getRepairDate());
            repairDetail.setRepairTime(updatedRepairDetail.getRepairTime());
            repairDetail.setRepairCost(updatedRepairDetail.getRepairCost());
            return repairDetailRepository.save(repairDetail);
        });
    }

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepairDetail: method to delete a repair detail entity from the database;
     *
     * @param id - the ID of the repair detail entity to delete;
     --------------------------------------------------------------------------------------------------------*/
    public void deleteRepairDetail(Long id) {
        repairDetailRepository.deleteById(id);
    }
}
