package com.repair.ms.repairms.services;

import com.repair.ms.repairms.entities.RepairDetailEntity;
import com.repair.ms.repairms.models.RepairlistModel;
import com.repair.ms.repairms.models.VehicleModel;
import com.repair.ms.repairms.repositories.RepairDetailRepository;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RepairDetailService {

    @Autowired
    RepairDetailRepository repairDetailRepository;

    @Autowired
    RepairService repairService;

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
                "http://repairlist-ms/ms/repairlist/" + repairType,
                HttpMethod.GET,
                null,
                RepairlistModel.class
        );
        return responseEntity.getBody();
    }

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepairDetail: Method to create a new repair detail entity in the database;
     *
     * @param repairDetailEntity - the repair detail entity to be created;
     * @return - the created repair detail entity;
     --------------------------------------------------------------------------------------------------------*/
    public RepairDetailEntity createRepairDetail(RepairDetailEntity repairDetailEntity) {
        /*we get the repairType for the corresponding repairDetail*/
        String repairType = repairDetailEntity.getRepairType();
        RepairlistModel repairPrice = getRepairlistByRepairType(repairType);

        /* we get the vehicle and its engineType */
        String plate = repairDetailEntity.getVehiclePlate();
        VehicleModel vehicle = repairService.getVehicle(plate);
        String engineType = vehicle.getEngineType();

        /* we assign a price depending on the engineType */
        double price = 0.0;
        switch (engineType) {
            case "gasoline":
                price = repairPrice.getGasolinePrice();
                break;
            case "diesel":
                price = repairPrice.getDieselPrice();
                break;
            case "hybrid":
                price = repairPrice.getHybridPrice();
                break;
            case "electric":
                price = repairPrice.getElectricPrice();
                break;
            default:
                price = 0.0;
                break;
        }
        repairDetailEntity.setRepairCost(price);
        return repairDetailRepository.save(repairDetailEntity);
    }


    /* GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairDetails: Method to retrieve all repair detail entities from the database.
     *
     * @return - List of all repair detail entities.
     --------------------------------------------------------------------------------------------------------*/
    public List<RepairDetailEntity> getAllRepairDetails() {
        return repairDetailRepository.findAll();
    }

    /* UPDATE OPERATIONS */

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

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepairDetail: method to delete a repair detail entity from the database;
     *
     * @param id - the ID of the repair detail entity to delete;
     --------------------------------------------------------------------------------------------------------*/
    public void deleteRepairDetail(Long id) {
        repairDetailRepository.deleteById(id);
    }
}
