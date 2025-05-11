package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.DataApoderadoResource;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.UpdateDataApoderadoResource;

public class UpdateDataApoderadoCommandFromResourceAssembler {
    public static UpdateDataApoderadoCommand toCommandFromResource(Long id, DataApoderadoResource resource) {
        return new UpdateDataApoderadoCommand(
                id,
                resource.nombres(),
                resource.apellidos(),
                resource.dni(),
                resource.fechaNacimiento(),
                resource.contacto(),
                resource.domicilio(),
                resource.cuentaBancaria(),
                resource.informacionLaboral()
        );
    }
}