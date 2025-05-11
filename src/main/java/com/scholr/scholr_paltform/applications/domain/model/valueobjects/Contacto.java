package com.scholr.scholr_paltform.applications.domain.model.valueobjects;

import lombok.Getter;

@Getter
public class Contacto {

    private String correo;

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
