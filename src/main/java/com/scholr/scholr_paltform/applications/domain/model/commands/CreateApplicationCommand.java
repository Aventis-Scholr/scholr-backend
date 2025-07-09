package com.scholr.scholr_paltform.applications.domain.model.commands;

import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;

public record CreateApplicationCommand(
        Long idApoderado,
        Status status,
        Long scholarshipId,
        Postulante postulante
) {
}
