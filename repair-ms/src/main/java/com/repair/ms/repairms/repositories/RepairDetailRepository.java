package com.repair.ms.repairms.repositories;

import com.repair.ms.repairms.entities.RepairDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairDetailRepository extends JpaRepository<RepairDetailEntity, Long> {

    public List<RepairDetailEntity> findAllByVehiclePlate(String VehiclePlate);
}
