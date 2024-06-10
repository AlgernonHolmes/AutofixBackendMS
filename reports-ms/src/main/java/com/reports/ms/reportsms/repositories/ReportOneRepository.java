package com.reports.ms.reportsms.repositories;

import com.reports.ms.reportsms.entities.ReportOneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportOneRepository extends JpaRepository<ReportOneEntity, Long> {
}
