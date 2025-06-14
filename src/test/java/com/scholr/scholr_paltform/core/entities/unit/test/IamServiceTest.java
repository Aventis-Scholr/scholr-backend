package com.scholr.scholr_paltform.core.entities.unit.test;

import com.scholr.scholr_paltform.iam.domain.model.commands.SignInCommand;
import com.scholr.scholr_paltform.iam.domain.model.commands.SignUpCommand;
import com.scholr.scholr_paltform.iam.domain.model.entities.Role;
import com.scholr.scholr_paltform.iam.domain.model.valueobjects.Roles;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IamServiceTest {
    @Test
    void testSignUp() {
        //Arrange
        String username = "Rodolfo";
        String password = "157896";
        String compania = "CompaniaX";
        String dni = "12345678";
        String cod_colaborador = "COL123";
        List<Role> roles = List.of(
                new Role(
                        Roles.ROLE_APODERADO
                )
        );

        // Act
        SignUpCommand signUpCommand = new SignUpCommand(username, password, compania, dni, cod_colaborador, roles);

        // Assert
        assertEquals(username, signUpCommand.username());
        assertEquals(password, signUpCommand.password());
        assertEquals(compania, signUpCommand.compania());
        assertEquals(dni, signUpCommand.dni());
        assertEquals(cod_colaborador, signUpCommand.cod_colaborador());
        assertEquals(roles, signUpCommand.roles());
    }

    @Test
    void testSignIn() {
        //Arrange
        String username = "María";
        String password = "157896";


        // Act
        SignInCommand signInCommand = new SignInCommand(username, password);

        // Assert
        assertEquals(username, signInCommand.username());
        assertEquals(password, signInCommand.password());
    }
}

