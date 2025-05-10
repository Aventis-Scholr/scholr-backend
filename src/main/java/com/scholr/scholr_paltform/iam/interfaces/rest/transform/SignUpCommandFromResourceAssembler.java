package com.scholr.scholr_paltform.iam.interfaces.rest.transform;

import com.scholr.scholr_paltform.iam.domain.model.commands.SignUpCommand;
import com.scholr.scholr_paltform.iam.domain.model.entities.Role;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {

  public static SignUpCommand toCommandFromResource(SignUpResource resource) {
    var roles = resource.roles() != null
        ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList()
        : new ArrayList<Role>();
    return new SignUpCommand(resource.username(), resource.password(), resource.compania(), resource.dni(), resource.cod_colaborador(),roles);
  }
}
