package com.repair.ms.repairms.services;


import com.repair.ms.repairms.entities.RepairDetailEntity;
import com.repair.ms.repairms.entities.RepairEntity;
import com.repair.ms.repairms.models.VehicleModel;
import com.repair.ms.repairms.repositories.RepairDetailEntityRepository;
import com.repair.ms.repairms.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class RepairService {

    /* Repository layer methods */

    @Autowired
    RepairRepository repairRepository;

    @Autowired
    RepairDetailEntityRepository repairDetailEntityRepository;

    @Autowired
    RestTemplate restTemplate;


    /* Aux Methods */

    /* GET OPERATIONS FOR VEHICLE */

    /*--------------------------------------------------------------------------------------------------------
     * getVehicle: method to retrieve a vehicle model from another microservice by vehicle plate;
     *
     * @param vehiclePlate - the vehicle plate for which the vehicle model is requested;
     * @return - the retrieved vehicle model;
     --------------------------------------------------------------------------------------------------------*/
    public VehicleModel getVehicle(String vehiclePlate) {
        ResponseEntity<VehicleModel> responseEntity = restTemplate.exchange(
                "http://vehicle-ms/ms/vehicle/" + vehiclePlate,
                HttpMethod.GET,
                null,
                VehicleModel.class
        );
        return responseEntity.getBody();
    }

    /* POST OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * createRepair: method to create a new repair entity in the database;
     *
     * @param repairEntity - the repair entity to be created;
     * @return - the created repair entity;
     --------------------------------------------------------------------------------------------------------*/

    public RepairEntity createRepair(RepairEntity repairEntity) {
        /* we get the plate to collect the data necessary
           whether it is in another class or microservice
         */
        String vehiclePlate = repairEntity.getVehiclePlate();

        /* totalInitialCost calculation*/
        List<RepairDetailEntity> repairDetails = repairDetailEntityRepository.findAllByVehiclePlate(vehiclePlate);
        double initialTotalCost = 0;
        for (RepairDetailEntity repairDetail : repairDetails) {
            initialTotalCost += repairDetail.getRepairCost();
        }
        repairEntity.setTotalCost(initialTotalCost);

        VehicleModel vehicle = getVehicle(vehiclePlate);

        /* we set the data we need for the aux methods */

        repairEntity.setEngineType(vehicle.getEngineType());
        repairEntity.setVehicleBrand(vehicle.getBrand());
        repairEntity.setVehicleType(vehicle.getType());
        repairEntity.setFabricationYear(vehicle.getFabricationYear());
        repairEntity.setMilage(vehicle.getMilage());

        return repairRepository.save(repairEntity);
    }


    /* GET OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * getAllRepairs: method to retrieve all repair entities from the database;
     *
     * @return - a list containing all repair entities;
     --------------------------------------------------------------------------------------------------------*/
    public List<RepairEntity> getAllRepairs() {
        return repairRepository.findAll();
    }

    /*--------------------------------------------------------------------------------------------------------
     * getRepairByPlate: method to retrieve a repair entity by vehicle plate;
     *
     * @param vehiclePlate - the vehicle plate for which the repair entity is requested;
     * @return - the retrieved repair entity;
     --------------------------------------------------------------------------------------------------------*/
    public RepairEntity getRepairByPlate(String vehiclePlate){
        return repairRepository.findByVehiclePlate(vehiclePlate);
    }

    /* UPDATE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * updateRepair: method to update an existing repair entity in the database;
     *
     * @param id - the ID of the repair entity to be updated;
     * @param updatedRepair - the updated repair entity;
     * @return - an optional containing the updated repair entity, or empty if the repair was not found;
     --------------------------------------------------------------------------------------------------------*/
    public Optional<RepairEntity> updateRepair(Long id, RepairEntity updatedRepair) {
        return repairRepository.findById(id).map(repair -> {
            repair.setVehiclePlate(updatedRepair.getVehiclePlate());
            repair.setVehicleBrand(updatedRepair.getVehicleBrand());
            repair.setVehicleType(updatedRepair.getVehicleType());
            repair.setFabricationYear(updatedRepair.getFabricationYear());
            repair.setEngineType(updatedRepair.getEngineType());
            repair.setEntryVDate(updatedRepair.getEntryVDate());
            repair.setEntryVTime(updatedRepair.getEntryVTime());
            repair.setTotalCost(updatedRepair.getTotalCost());
            repair.setTotalSurcharge(updatedRepair.getTotalSurcharge());
            repair.setTotal_discount(updatedRepair.getTotal_discount());
            repair.setAmount_iva(updatedRepair.getAmount_iva());
            repair.setFinalPrice(updatedRepair.getFinalPrice());
            repair.setExitVDate(updatedRepair.getExitVDate());
            repair.setExitCDate(updatedRepair.getExitCDate());
            repair.setExitVTime(updatedRepair.getExitVTime());
            repair.setExitCTime(updatedRepair.getExitCTime());
            return repairRepository.save(repair);
        });
    }

    /* DELETE OPERATIONS */

    /*--------------------------------------------------------------------------------------------------------
     * deleteRepair: method to delete a repair entity from the database by its ID;
     *
     * @param id - the ID of the repair entity to be deleted;
     --------------------------------------------------------------------------------------------------------*/
    public void deleteRepair(Long id) {
        repairRepository.deleteById(id);
    }

    /* Business layer methods */

    /* SURCHARGES */

    /*--------------------------------------------------------------------------------------------------------
     * delaySurcharge: method to calculate the delay surcharge for a repair entity;
     *
     * @param initialCost - the initial cost of the repair;
     * @param repair - the repair entity for which the delay surcharge is calculated;
     * @return - the calculated delay surcharge;
     --------------------------------------------------------------------------------------------------------*/

    public double delaySurcharge(double initialCost, RepairEntity repair) {
        double maxDelaySurcharge = 0.0;
        /* in case required data is null */
        if (repair.getExitVDate() == null || repair.getExitCDate() == null) {
            return 0.0;
        }
        long diffInDays = ChronoUnit.DAYS.between(repair.getExitVDate(), repair.getExitCDate());
        if(diffInDays > 0){
            double delaySurchargePercentage = 0.05;
            double delaySurcharge = initialCost * diffInDays * delaySurchargePercentage;
            if(delaySurcharge > maxDelaySurcharge){
                maxDelaySurcharge = delaySurcharge;
            }
        }
        return maxDelaySurcharge;
    }

    /*--------------------------------------------------------------------------------------------------------
     * antiquitySurcharge: method to calculate antiquity surcharge;
     *
     * @param price - total price;
     * @param fabricationYear - fabrication year;
     * @param type - vehicle type;
     * @return - the amount to be surcharged;
     --------------------------------------------------------------------------------------------------------*/

    public double antiquitySurcharge(double price, RepairEntity repair) {
        int fabricationYear = repair.getFabricationYear();
        String type = repair.getEngineType().toLowerCase();
        int antiquity = 2024 - fabricationYear;
        double surchargePercentage = 0.0;

        if (antiquity >= 6 && antiquity <= 10) {
            if (type.equals("sedan") || type.equals("hatchback")) {
                surchargePercentage = 0.05;
            } else {
                surchargePercentage = 0.07;
            }
        } else if (antiquity >= 11 && antiquity <= 15) {
            if (type.equals("sedan") || type.equals("hatchback")) {
                surchargePercentage = 0.09;
            } else {
                surchargePercentage = 0.11;
            }
        } else if (antiquity >= 16) {
            if (type.equals("sedan") || type.equals("hatchback")) {
                surchargePercentage = 0.15;
            } else {
                surchargePercentage = 0.20;
            }
        }

        double surchargeValue = price * surchargePercentage;

        return surchargeValue;

    }


    /*--------------------------------------------------------------------------------------------------------
    * mileageSurcharge: method to calculate mileage surcharge;
    *
    * @param price - total price;
    * @param mileage - mileage of the vehicle;
    * @param type - type of the vehicle;
    * @return - the amount to be surcharged;
    --------------------------------------------------------------------------------------------------------*/
    public double mileageSurcharge(double price, RepairEntity repair) {
        double mileage = repair.getMilage();
        String type = repair.getVehicleType().toLowerCase();
        double surchargePercentage = 0.0;

        if (mileage >= 5001 && mileage <= 12000) {
            if (type.equals("sedan") || type.equals("hatchback")) {
                surchargePercentage = 0.03;
            } else {
                surchargePercentage = 0.05;
            }
        } else if (mileage >= 12001 && mileage <= 25000) {
            if (type.equals("sedan") || type.equals("hatchback")) {
                surchargePercentage = 0.07;
            } else {
                surchargePercentage = 0.09;
            }
        } else if (mileage >= 25001 && mileage <= 40000) {
            surchargePercentage = 0.12;
        } else {
            surchargePercentage = 0.20;
        }

        double surchargeValue = price * surchargePercentage;
        return surchargeValue;
    }



    /* DISCOUNTS */

    /*--------------------------------------------------------------------------------------------------------
     * dayDiscount: method to calculate day discount;
     *
     * @param price      - total price;
     * @param entryVDate - date of vehicle entry to the workshop;
     * @param entryVTime - time of vehicle entry to the workshop;
     * @return - the amount to be discounted;
     --------------------------------------------------------------------------------------------------------*/

    public double dayDiscount(double initialCost, RepairEntity repair) {

        boolean inRange = true;
        if (repair.getEntryVDate() == null || repair.getEntryVTime() == null) {
            return -1.0;
        } else if (!(repair.getEntryVDate().getDayOfWeek() == DayOfWeek.MONDAY || repair.getEntryVDate().getDayOfWeek() == DayOfWeek.THURSDAY)
                || !(repair.getEntryVTime().isAfter(LocalTime.of(9, 0)) && repair.getEntryVTime().isBefore(LocalTime.of(12, 0)))) {
            inRange = false;
        }
        if (inRange) {
            return initialCost * 0.1;
        } else {
            return 0.0;
        }
    }

    /*--------------------------------------------------------------------------------------------------------
     * repAmountDiscount: method to calculate repair amount discount;
     *
     * @param plate      - vehicle plate number;
     * @return - the discount percentage applied based on the repair quantity and engine type;
     --------------------------------------------------------------------------------------------------------*/
    public double repAmountDiscount(double initialCost, String plate) {
        VehicleModel vehicle = getVehicle(plate);
        String engineType = vehicle.getEngineType().toLowerCase();
        List<RepairDetailEntity> repairs = repairDetailEntityRepository.findAllByVehiclePlate(plate);
        LocalDate currentDate = LocalDate.now();
        int repairQuantity = 0;
        for (RepairDetailEntity repair : repairs) {
            if (ChronoUnit.MONTHS.between(repair.getRepairDate(), currentDate) <= 12) {
                repairQuantity = repairQuantity + 1;
            }
        }

        double discount = 0;

        if (repairQuantity >= 1 && repairQuantity <= 2) {
            if (engineType.equalsIgnoreCase("gasolina")) {
                discount = 0.05;
            } else if (engineType.equalsIgnoreCase("diesel")) {
                discount = 0.07;
            } else if (engineType.equalsIgnoreCase("hibrido")) {
                discount = 0.10;
            } else if (engineType.equalsIgnoreCase("electrico")) {
                discount = 0.08;
            }
        } else if (repairQuantity >= 3 && repairQuantity <= 5) {
            if (engineType.equalsIgnoreCase("gasolina")) {
                discount = 0.10;
            } else if (engineType.equalsIgnoreCase("diesel")) {
                discount = 0.12;
            } else if (engineType.equalsIgnoreCase("hibrido")) {
                discount = 0.15;
            } else if (engineType.equalsIgnoreCase("electrico")) {
                discount = 0.13;
            }
        } else if (repairQuantity >= 6 && repairQuantity <= 9) {
            if (engineType.equalsIgnoreCase("gasolina")) {
                discount = 0.15;
            } else if (engineType.equalsIgnoreCase("diesel")) {
                discount = 0.17;
            } else if (engineType.equalsIgnoreCase("hibrido")) {
                discount = 0.20;
            } else if (engineType.equalsIgnoreCase("electrico")) {
                discount = 0.18;
            }
        } else if (repairQuantity >= 10) {
            if (engineType.equalsIgnoreCase("gasolina")) {
                discount = 0.20;
            } else if (engineType.equalsIgnoreCase("diesel")) {
                discount = 0.22;
            } else if (engineType.equalsIgnoreCase("hibrido")) {
                discount = 0.25;
            } else if (engineType.equalsIgnoreCase("electrico")) {
                discount = 0.23;
            }
        }
        return (initialCost * discount);
    }


    /* DISCOUNT AND SURCHARGE APPLICATION */
    
    /*--------------------------------------------------------------------------------------------------------
     * applySurandDis: method to apply surcharges and discounts to a repair entity;
     *
     * @param vehiclePlate - vehicle plate number for which surcharges and discounts are applied;
     * @return - the updated repair entity after applying surcharges and discounts;
     --------------------------------------------------------------------------------------------------------*/
    public RepairEntity applySurandDis(String vehiclePlate) {

        /*we find the repair we want to apply the surcharges and
          discounts to
         */
        RepairEntity repair = getRepairByPlate(vehiclePlate);
        /*we calculate all surcharges */
        double cost = repair.getTotalCost();
        double delaySur = delaySurcharge(cost, repair);
        double antiSur = antiquitySurcharge(cost, repair);
        double milageSur = mileageSurcharge(cost,repair);
        repair.setTotalSurcharge(delaySur+antiSur+milageSur);
        /*we calculate all discounts*/
        double dayDiscount = dayDiscount(cost,repair);
        double repDiscount = repAmountDiscount(cost,vehiclePlate);
        repair.setTotal_discount(dayDiscount+repDiscount);
        /*we calculate the formula:
          finalCost = (initialCost + totalSur - totalDis) + IVA
         */
        repair.setAmount_iva(cost * 0.19);
        double finalCost = (cost + repair.getTotalSurcharge() -
                            repair.getTotal_discount()) + repair.getAmount_iva();
        repair.setFinalPrice(finalCost);
        return repairRepository.save(repair);
    }
}
