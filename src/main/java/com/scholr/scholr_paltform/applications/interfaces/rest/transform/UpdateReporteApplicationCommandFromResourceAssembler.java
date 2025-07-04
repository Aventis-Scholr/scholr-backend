package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateReporteApplicationCommand;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.UpdateReporteApplicationResource;

public class UpdateReporteApplicationCommandFromResourceAssembler {
    public static UpdateReporteApplicationCommand toCommandFromResource(Long id, UpdateReporteApplicationResource resource) {
        return new UpdateReporteApplicationCommand(id, resource.reporte());
    }
}
