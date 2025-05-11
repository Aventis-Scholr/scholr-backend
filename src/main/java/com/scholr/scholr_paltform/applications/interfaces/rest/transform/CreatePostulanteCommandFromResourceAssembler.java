package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreatePostulanteCommand;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.CreatePostulanteResource;

public class CreatePostulanteCommandFromResourceAssembler {
    public static CreatePostulanteCommand toCommandFromResource(CreatePostulanteResource resource){
        return new CreatePostulanteCommand(
                resource.nombres(),
                resource.apellidos(),
                resource.dni(),
                resource.fechaNacimiento(),
                resource.contacto(),
                resource.centroEstudios()
        );
    }
}
