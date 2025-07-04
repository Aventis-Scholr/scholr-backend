package com.scholr.scholr_paltform.applications.interfaces.rest.transform;


import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateStatusApplicationCommand;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.UpdateStatusApplicationResource;

public class UpdateStatusApplicationCommandFromResourceAssembler {
    public static UpdateStatusApplicationCommand toCommandFromResource(Long id, UpdateStatusApplicationResource resource) {
        return new UpdateStatusApplicationCommand(id, resource.status());
    }
}
