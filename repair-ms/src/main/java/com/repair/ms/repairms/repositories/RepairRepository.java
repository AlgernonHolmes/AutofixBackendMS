package com.repair.ms.repairms.repositories;

import com.example.autofix.autofix.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {


    public List<RepairEntity> findAllByVehiclePlate(String plate);
}
