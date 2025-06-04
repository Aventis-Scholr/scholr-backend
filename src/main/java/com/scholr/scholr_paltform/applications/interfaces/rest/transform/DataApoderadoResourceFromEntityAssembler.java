package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.DataApoderadoResource;

public class DataApoderadoResourceFromEntityAssembler {
    public static DataApoderadoResource toResourceFromEntity(DataApoderado entity) {
        return new DataApoderadoResource(
                entity.getId(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getDni(),
                entity.getFechaNacimiento(),
                entity.getContacto(),
                entity.getDomicilio(),
                entity.getCuentaBancaria(),
                entity.getInformacionLaboral()
        );
    }
}
