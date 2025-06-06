package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateApplicationCommand;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.CreateApplicationResource;

public class CreateApplicationCommandFromResourceAssembler {
    public static CreateApplicationCommand toCommandFromResource(Long apoderadoId, CreateApplicationResource resource){
        return new CreateApplicationCommand(
                apoderadoId,
                resource.status(),
                resource.tipoBeca(),
                resource.postulante()
        );
    }
}
