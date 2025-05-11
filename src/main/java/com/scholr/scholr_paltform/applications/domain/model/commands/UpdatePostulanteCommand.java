package com.scholr.scholr_paltform.applications.domain.model.commands;

import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CentroEstudios;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;

import java.util.Date;

public record UpdatePostulanteCommand(
        Long postulanteId,
        String nombres,
        String apellidos,
        int dni,
        Date fechaNacimiento,
        Contacto contacto,
        CentroEstudios centroEstudios
) {
}
