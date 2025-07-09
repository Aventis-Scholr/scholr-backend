package com.scholr.scholr_paltform.applications.interfaces.rest.resources;

import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;

public record CreateApplicationResource(
        Status status,
        String scholarshipName,
        Postulante postulante) {
}
