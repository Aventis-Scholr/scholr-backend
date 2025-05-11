package com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    //restricciones
    List<Application> findByIdApoderado(Long idApoderado);
}
