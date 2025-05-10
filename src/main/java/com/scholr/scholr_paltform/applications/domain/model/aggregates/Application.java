package com.scholr.scholr_paltform.applications.domain.model.aggregates;

import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Application extends AuditableAbstractAggregateRoot<Application> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private apoderadoId

    @ManyToOne
    private Postulante postulante;

    public Application() {}

}
