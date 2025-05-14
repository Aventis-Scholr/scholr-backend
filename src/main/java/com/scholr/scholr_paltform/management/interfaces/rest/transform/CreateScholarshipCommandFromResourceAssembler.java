package com.scholr.scholr_paltform.management.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreateApplicationCommand;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.CreateApplicationResource;
import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.CreateScholarshipResource;

public class CreateScholarshipCommandFromResourceAssembler {
    public static CreateScholarshipCommand toCommandFromResource(CreateScholarshipResource resource){
        return new CreateScholarshipCommand(
                resource.name(),
                resource.companyName(),
                resource.requirements(),
                resource.scholarshipType(),
                resource.scholarshipStatus(),
                resource.coordinatorId()
        );
    }
}
