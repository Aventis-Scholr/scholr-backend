package com.scholr.scholr_paltform.iam.domain.services;

import com.scholr.scholr_paltform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
