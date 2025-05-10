package com.scholr.scholr_paltform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username, String password,String compania,String dni,String cod_colaborador, List<String> roles) {
}

