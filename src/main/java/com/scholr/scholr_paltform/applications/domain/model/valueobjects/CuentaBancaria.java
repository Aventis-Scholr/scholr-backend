package com.scholr.scholr_paltform.applications.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

public class CuentaBancaria {

    //faltan agregar restricciones

    @Getter
    @Setter
    private String entidadBancaria;

    @Getter
    @Setter
    private Long numeroCuenta;

    @Getter
    @Setter
    private Long cci;


    public CuentaBancaria(){}

    public CuentaBancaria(String entidadBancaria, Long numeroCuenta, Long cci) {
        this.entidadBancaria = entidadBancaria;
        this.numeroCuenta = numeroCuenta;
        this.cci = cci;
    }

    public void UpdateCuentaBancaria(String entidadBancaria, Long numeroCuenta, Long cci) {
        this.entidadBancaria = entidadBancaria;
        this.numeroCuenta = numeroCuenta;
        this.cci = cci;
    }

}
