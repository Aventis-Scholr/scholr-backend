package com.scholr.scholr_paltform.applications.interfaces.rest.resources;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.TipoBeca;

public record UpdateApplicationResource(
        Long idApoderado,
        Status status,
        TipoBeca tipoBeca,
        Postulante postulante
) {
}
