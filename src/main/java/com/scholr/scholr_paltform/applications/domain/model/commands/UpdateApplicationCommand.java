package com.scholr.scholr_paltform.applications.domain.model.commands;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.TipoBeca;

public record UpdateApplicationCommand(
        Long applicationId,
        Status status,
        TipoBeca tipoBeca,
        Postulante postulante
) {
}
