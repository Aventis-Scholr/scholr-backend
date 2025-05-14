package com.scholr.scholr_paltform.management.domain.model.valueobjects;

import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.InformacionLaboral;
import jakarta.persistence.*;

@Embeddable
public class ApoderadoSnapshot {


    private String nameApoderado;
    private String lastNameApoderado;
    private String dni;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "celular", column = @Column(name = "celular_apoderado")),
            @AttributeOverride(name = "correo", column = @Column(name = "correo_apoderado"))
    })
    private Contacto contact;

    @Embedded
    private InformacionLaboral infoLaboral;

    public ApoderadoSnapshot() {}

    public ApoderadoSnapshot(String name, String lastName, String dni, Contacto contact, InformacionLaboral infoLaboral) {
        this.nameApoderado = name;
        this.lastNameApoderado = lastName;
        this.dni = dni;
        this.contact = contact;
        this.infoLaboral = infoLaboral;
    }
}
