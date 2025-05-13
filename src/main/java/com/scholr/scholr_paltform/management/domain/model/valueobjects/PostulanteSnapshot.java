package com.scholr.scholr_paltform.management.domain.model.valueobjects;

import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CentroEstudios;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import jakarta.persistence.*;

@Embeddable
public class PostulanteSnapshot {
    private String namePostulante;
    private String lastNamePostulante;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "celular", column = @Column(name = "celular_postulante")),
            @AttributeOverride(name = "correo", column = @Column(name = "correo_postulante"))
    })
    private Contacto contact;

    @Embedded
    private CentroEstudios infoCE;

    public PostulanteSnapshot() {}

    public PostulanteSnapshot(String name, String lastName, Contacto contact, CentroEstudios infoCE) {
        this.namePostulante = name;
        this.lastNamePostulante = lastName;
        this.contact = contact;
        this.infoCE = infoCE;
    }
}

