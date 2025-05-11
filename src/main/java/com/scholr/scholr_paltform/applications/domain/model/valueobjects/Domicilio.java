package com.scholr.scholr_paltform.applications.domain.model.valueobjects;

import lombok.Getter;

@Getter
public class Domicilio {

    //faltan agregar restricciones

    private String direccion;

    private String departamento;

    private String provincia;

    private String distrito;

    public Domicilio() {}

    public Domicilio(String direccion, String departamento, String provincia, String distrito) {
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public void UpdateDomicilio(String direccion, String departamento, String provincia, String distrito) {
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

}
