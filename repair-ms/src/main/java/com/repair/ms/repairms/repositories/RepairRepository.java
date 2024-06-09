package com.repair.ms.repairms.repositories;


import com.repair.ms.repairms.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {

    public RepairEntity findByVehiclePlate(String vehiclePlate);



}
