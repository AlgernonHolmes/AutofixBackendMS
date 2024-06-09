package com.repair.ms.repairms.repositories;

import com.repair.ms.repairms.entities.RepairDetailEntity;
import com.repair.ms.repairms.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairDetailEntityRepository extends JpaRepository<RepairDetailEntity, Long> {

    public List<RepairDetailEntity> findAllByVehiclePlate(String VehiclePlate);
}
