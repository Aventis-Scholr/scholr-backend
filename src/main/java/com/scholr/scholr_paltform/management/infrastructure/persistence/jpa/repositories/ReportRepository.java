package com.scholr.scholr_paltform.management.infrastructure.persistence.jpa.repositories;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findById(Long id);
}
