package com.scholr.scholr_paltform.management.interfaces.rest.transform;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Report;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {
    public static ReportResource toResourceFromEntity(Report entity) {
        return new ReportResource(
                entity.getId(),
                entity.getApplicationId(),
                entity.getApoderadoData(),
                entity.getPostulanteSnapshot(),
                entity.getResolution(),
                entity.getAdminComments()
        );
    }
}
