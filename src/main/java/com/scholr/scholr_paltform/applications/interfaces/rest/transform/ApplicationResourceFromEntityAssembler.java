package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.ApplicationResource;

public class ApplicationResourceFromEntityAssembler {
    public static ApplicationResource toResourceFromEntity(Application entity) {
        return new ApplicationResource(
                entity.getId(),
                entity.getIdApoderado(),
                entity.getStatus(),
                entity.getTipoBeca(),
                entity.getPostulante()
        );
    }
}