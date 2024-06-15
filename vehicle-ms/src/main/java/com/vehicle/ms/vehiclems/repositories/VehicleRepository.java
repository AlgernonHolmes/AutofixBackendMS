package com.vehicle.ms.vehiclems.repositories;


import com.vehicle.ms.vehiclems.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    public VehicleEntity findByRegistrationPlate(String registrationPlate);

    @Transactional
    public void deleteByRegistrationPlate(String registrationPlate);

}
