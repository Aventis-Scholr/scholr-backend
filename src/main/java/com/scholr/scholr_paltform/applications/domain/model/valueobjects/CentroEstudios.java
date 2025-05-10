package com.scholr.scholr_paltform.applications.domain.model.valueobjects;

import lombok.Getter;

@Getter
public class CentroEstudios {

    //faltan agregar restricciones

    private String nombre;

    private String tipo;

    private String nivel;

    private String departamento;

    private String provincia;

    private String distrito;

    public CentroEstudios(){}

    public CentroEstudios(String nombre, String tipo, String nivel, String departamento, String provincia, String distrito){
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public void UpdateCentroEstudios(String nombre, String tipo, String nivel, String departamento, String provincia, String distrito){
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

}
