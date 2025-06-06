package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.UpdateApplicationResource;

public class UpdateApplicationCommandFromResourceAssembler {
    public static UpdateApplicationCommand toCommandFromResource(Long id, UpdateApplicationResource resource) {
        return new UpdateApplicationCommand(
                id,
                resource.idApoderado(),
                resource.status(),
                resource.tipoBeca(),
                new Postulante(
                        resource.postulante().getNombres(),
                        resource.postulante().getApellidos(),
                        resource.postulante().getDni(),
                        resource.postulante().getFechaNacimiento(),
                        resource.postulante().getContacto(),
                        resource.postulante().getCentroEstudios()
                )
        );
    }
}