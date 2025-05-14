package com.scholr.scholr_paltform.management.interfaces.rest.transform;

import com.scholr.scholr_paltform.management.domain.model.commands.CreateReportCommand;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.CreateReportResource;

public class CreateReportCommandFromResourceAssembler {

    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
        return new CreateReportCommand(
                resource.applicationId(),
                resource.apoderadoData(),
                resource.postulanteSnapshot(),
                resource.resolution(),
                resource.adminComments()
        );
    }
}
