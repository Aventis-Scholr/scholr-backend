package com.scholr.scholr_paltform.applications.interfaces.rest.transform;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CuentaBancaria;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Domicilio;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.InformacionLaboral;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.CreateDataApoderadoResource;

import java.util.Date;

public class CreateDataApoderadoCommandFromResourceAssembler {
    public static CreateDataApoderadoCommand toCommandFromResource(Long apoderadoId, CreateDataApoderadoResource resource){
        return new CreateDataApoderadoCommand(
                apoderadoId,
                resource.nombres(),
                resource.apellidos(),
                resource.dni(),
                resource.fechaNacimiento(),
                new Contacto(
                        resource.contacto().getCorreo(),
                        resource.contacto().getCelular()
                ),
                new Domicilio(
                        resource.domicilio().getDireccion(),
                        resource.domicilio().getDepartamento(),
                        resource.domicilio().getProvincia(),
                        resource.domicilio().getDistrito()
                ),
                new CuentaBancaria(
                        resource.cuentaBancaria().getEntidadBancaria(),
                        resource.cuentaBancaria().getNumeroCuenta(),
                        resource.cuentaBancaria().getCci()
                ),
                new InformacionLaboral(
                        resource.informacionLaboral().getTipoColaborador(),
                        resource.informacionLaboral().getCargo(),
                        resource.informacionLaboral().getSede(),
                        resource.informacionLaboral().getLocal(),
                        resource.informacionLaboral().getIngreso()
                )
        );
    }
}
