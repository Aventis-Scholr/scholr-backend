package com.scholr.scholr_paltform.iam.domain.model.commands;

import com.scholr.scholr_paltform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password,String compania,String dni,String cod_colaborador, List<Role> roles) {
}
