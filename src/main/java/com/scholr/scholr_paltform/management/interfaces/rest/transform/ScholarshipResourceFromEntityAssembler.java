package com.scholr.scholr_paltform.management.interfaces.rest.transform;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.ScholarshipResource;

public class ScholarshipResourceFromEntityAssembler {
    public static ScholarshipResource toResourceFromEntity(Scholarship entity){
        return new ScholarshipResource(
                entity.getId(),
                entity.getName(),
                entity.getCompanyName(),
                entity.getRequirements(),
                entity.getScholarshipType(),
                entity.getScholarshipStatus(),
                entity.getCoordinatorId()
        );
    }
}
