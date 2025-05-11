package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.PostulanteResource;

public class PostulanteResourceFromEntityAssembler {
    public static PostulanteResource toResourceFromEntity(Postulante entity) {
        return new PostulanteResource(
                entity.getId(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getDni(),
                entity.getFechaNacimiento(),
                entity.getContacto(),
                entity.getCentroEstudios()
        );
    }
}
