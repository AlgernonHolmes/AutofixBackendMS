package com.vehicle.ms.vehiclems.services;



import com.vehicle.ms.vehiclems.entities.VehicleEntity;
import com.vehicle.ms.vehiclems.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class VehicleService {

    /* Repository layer methods */

    @Autowired
    VehicleRepository vehicleRepository;


   /* GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getVehicles: method to find all vehicles in the DB;
     *
     * @return - a list with all vehicles;
     --------------------------------------------------------------------------------------------------------*/

    public List<VehicleEntity> getVehicles(){
        return (List<VehicleEntity>) vehicleRepository.findAll();
    }

    /*--------------------------------------------------------------------------------------------------------
     * getByPlate: method to find specific vehicle in DB by plate;
     *
     * @return - a vehicle with the specific plate;
     --------------------------------------------------------------------------------------------------------*/

    public VehicleEntity getByPlate(String rPlate){return (VehicleEntity) vehicleRepository.findByRegistrationPlate(rPlate);}

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createVehicle: method to find create a vehicle in the DB;
     *
     * @return - null;
     --------------------------------------------------------------------------------------------------------*/

    public VehicleEntity createVehicle(VehicleEntity Vehicle){
        return vehicleRepository.save(Vehicle);
    }

    /* UPDATE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * updateVehicle: method to update a vehicle in the DB;
     *
     * @param updatedVehicle - the updated vehicle;
     --------------------------------------------------------------------------------------------------------*/

    public VehicleEntity updateVehicle(VehicleEntity updatedVehicle){
        return vehicleRepository.save(updatedVehicle);
    }

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteVehicleByPlate: method to delete a vehicle in the DB;
     *
     * @param plate - the plate of the vehicle to delete;
     * @return - null
     --------------------------------------------------------------------------------------------------------*/

    public void deleteVehicleByPlate(String plate){
         vehicleRepository.deleteByRegistrationPlate(plate);
    }

}
