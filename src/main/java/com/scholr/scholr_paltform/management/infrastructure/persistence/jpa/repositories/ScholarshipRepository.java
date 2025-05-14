package com.scholr.scholr_paltform.management.infrastructure.persistence.jpa.repositories;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    Optional<Scholarship> findById(Long id);
    List<Scholarship> findByCompanyName(String companyName);
}
