package com.scholr.scholr_paltform.iam.interfaces.rest.transform;

import com.scholr.scholr_paltform.iam.domain.model.entities.Role;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
