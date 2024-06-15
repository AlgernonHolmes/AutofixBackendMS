package com.vehicle.ms.vehiclems.controller;

import com.vehicle.ms.vehiclems.entities.VehicleEntity;
import com.vehicle.ms.vehiclems.services.VehicleService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ms/vehicle/")
public class VehicleController {

    /* API endpoints */

    /* Service layer methods */

    @Autowired
    VehicleService vehicleService;


    /*--------------------------------------------------------------------------------------------------------
     * getAllVehicles: Method to retrieve all vehicles from the database.
     *
     * @return A list with all vehicles.
     --------------------------------------------------------------------------------------------------------*/
    @GetMapping()
    public List<VehicleEntity> getAllVehicles() {
        return vehicleService.getVehicles();
    }

    /*--------------------------------------------------------------------------------------------------------
     * getVehicleByPlate: Method to retrieve a specific vehicle by its registration plate.
     *
     * @param registrationPlate The registration plate of the vehicle to search for.
     * @return The vehicle with the specified plate.
     --------------------------------------------------------------------------------------------------------*/
    @GetMapping("{registrationPlate}")
    public VehicleEntity getVehicleByPlate(@PathVariable String registrationPlate) {
        return vehicleService.getByPlate(registrationPlate);
    }

    /*--------------------------------------------------------------------------------------------------------
     * createVehicle: Method to create a new vehicle in the database.
     *
     * @param vehicle The vehicle to create.
     * @return The created vehicle.
     --------------------------------------------------------------------------------------------------------*/
    @PostMapping("")
    public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    /*--------------------------------------------------------------------------------------------------------
     * updateVehicle: Method to update an existing vehicle in the database.
     *
     * @param registrationPlate The plate of the vehicle to update.
     * @param updatedVehicle The updated vehicle.
     * @return The updated vehicle.
     --------------------------------------------------------------------------------------------------------*/
    @PutMapping("{registrationPlate}")
    public VehicleEntity updateVehicle(@PathVariable String registrationPlate, @RequestBody VehicleEntity updatedVehicle) {
        updatedVehicle.setRegistrationPlate(registrationPlate); // Ensure that the plate of the updated vehicle is the same as provided in the URL
        return vehicleService.updateVehicle(updatedVehicle);
    }

    /*--------------------------------------------------------------------------------------------------------
     * deleteVehicle: Method to delete a vehicle from the database by its registration plate.
     *
     * @param registrationPlate The plate of the vehicle to delete.
     --------------------------------------------------------------------------------------------------------*/
    @DeleteMapping("{registrationPlate}")
    public void deleteVehicle(@PathVariable String registrationPlate) {
        vehicleService.deleteVehicleByPlate(registrationPlate);
    }
}

