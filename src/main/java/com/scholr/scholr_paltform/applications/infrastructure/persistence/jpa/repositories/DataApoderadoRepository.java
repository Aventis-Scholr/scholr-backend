package com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataApoderadoRepository extends JpaRepository<DataApoderado, Long> {
    Optional<DataApoderado> findByApoderadoId(Long aLong);

    Optional<DataApoderado> findByApoderadoIdAndDataApoderadoId(Long aLong, Long aLong1);
    //restricciones

}
