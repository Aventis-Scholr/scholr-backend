package com.scholr.scholr_paltform.applications.interfaces.rest.resources;

import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;

public record ApplicationResource(
        Long id,
        Long idApoderado,
        Status status,
        Long scholarshipId,
        Postulante postulante,
        String postulante_dni,
        String postulante_libreta_notas,
        String postulante_const_logro_aprendizaje,
        String apoderado_dni,
        String apoderado_declaracion_jurada,
        String reporte
) {}
