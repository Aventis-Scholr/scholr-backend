package com.scholr.scholr_paltform.iam.interfaces.rest.transform;

import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
  }
}
