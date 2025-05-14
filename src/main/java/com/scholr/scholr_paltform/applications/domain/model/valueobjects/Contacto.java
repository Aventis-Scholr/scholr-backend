package com.scholr.scholr_paltform.applications.domain.model.valueobjects;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class Contacto {

    @Column(name = "correo")
    private String correo;

    @Column(name = "celular")
    private int celular;

    public Contacto(){}

    public Contacto(String correo, int celular) {
        this.correo = correo;
        this.celular = celular;
    }

    public void UpdateInformation(String correo, int celular) {
        this.correo = correo;
        this.celular = celular;
    }

}
