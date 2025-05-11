package com.scholr.scholr_paltform.applications.domain.model.valueobjects;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InformacionLaboral {

    //faltan agregar restricciones

    private String tipoColaborador;

    private String cargo;

    private String sede;

    private String local;

    //salario
    private BigDecimal ingreso;

    public InformacionLaboral(){}

    public InformacionLaboral(String tipoColaborador, String cargo, String sede, String local, BigDecimal ingreso) {
        this.tipoColaborador = tipoColaborador;
        this.cargo = cargo;
        this.sede = sede;
        this.local = local;
        this.ingreso = ingreso;
    }

    public void UpdateInformacionLaboral(String tipoColaborador, String cargo, String sede, String local, BigDecimal ingreso) {
        this.tipoColaborador = tipoColaborador;
        this.cargo = cargo;
        this.sede = sede;
        this.local = local;
        this.ingreso = ingreso;
    }

}
