package com.scholr.scholr_paltform.applications.domain.model.commands;

import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CuentaBancaria;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Domicilio;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.InformacionLaboral;

import java.util.Date;

public record UpdateDataApoderadoCommand(
        Long dataApoderadoId,
        String nombres,
        String apellidos,
        int dni,
        Date fechaNacimiento,
        Contacto contacto,
        Domicilio domicilio,
        CuentaBancaria cuentaBancaria,
        InformacionLaboral informacionLaboral
) {
}
