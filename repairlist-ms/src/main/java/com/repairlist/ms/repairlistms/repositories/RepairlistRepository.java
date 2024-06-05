package com.repairlist.ms.repairlistms.repositories;

import com.repairlist.ms.repairlistms.entities.RepairlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairlistRepository extends JpaRepository<RepairlistEntity, Long> {
}
