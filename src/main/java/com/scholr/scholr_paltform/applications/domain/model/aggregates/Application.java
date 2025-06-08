package com.scholr.scholr_paltform.applications.domain.model.aggregates;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.TipoBeca;
import com.scholr.scholr_paltform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Application extends AuditableAbstractAggregateRoot<Application> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //id del usuario
    private Long idApoderado;

    private Status status;

    private TipoBeca tipoBeca;

    //@ManyToOne
    //vamos a usar como value object
    @Embedded
    private Postulante postulante;

    public Application() {}

    public Application(Long idApoderado, Status status, TipoBeca tipoBeca, Postulante postulante) {
        this.idApoderado = idApoderado;
        this.status = status;
        this.tipoBeca = tipoBeca;
        this.postulante = postulante;
    }

    public Application(CreateApplicationCommand command){
        this();
        this.idApoderado = command.idApoderado();
        this.status = command.status();
        this.tipoBeca = command.tipoBeca();
        this.postulante = command.postulante();
    }

    public void UpdateApplication(Status status, TipoBeca tipoBeca, Postulante postulante) {
        this.status = status;
        this.tipoBeca = tipoBeca;
        this.postulante = postulante;
    }

}
