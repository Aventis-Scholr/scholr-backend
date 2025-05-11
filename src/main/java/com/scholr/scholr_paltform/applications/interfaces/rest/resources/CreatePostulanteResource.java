package com.scholr.scholr_paltform.applications.interfaces.rest.resources;

import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CentroEstudios;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;

import java.util.Date;

public record CreatePostulanteResource(
        String nombres,
        String apellidos,
        int dni,
        Date fechaNacimiento,
        Contacto contacto,
        CentroEstudios centroEstudios) {
}
