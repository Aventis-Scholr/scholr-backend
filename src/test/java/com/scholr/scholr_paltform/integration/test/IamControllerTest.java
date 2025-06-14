package com.scholr.scholr_paltform.integration.test;

import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;
import com.scholr.scholr_paltform.iam.domain.model.entities.Role;
import com.scholr.scholr_paltform.iam.domain.model.queries.GetAllUsersQuery;
import com.scholr.scholr_paltform.iam.domain.model.queries.GetUserByIdQuery;
import com.scholr.scholr_paltform.iam.domain.model.valueobjects.Roles;
import com.scholr.scholr_paltform.iam.domain.services.UserQueryService;
import com.scholr.scholr_paltform.iam.interfaces.rest.UsersController;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.UserResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)

public class IamControllerTest {

    @Mock
    private UserQueryService userQueryService;

    @InjectMocks
    private UsersController usersController;

    @Test
    void testGetAllUsersSuccess() {
        // Arrange
        var user1 = new User("user1", "pass1", "Compania1", "456789123", "Cod01", List.of(new Role(Roles.ROLE_APODERADO)));
        var user2 = new User("user2", "pass2", "Compania2", "123456789", "Cod02", List.of(new Role(Roles.ROLE_APODERADO)));
        var users = List.of(user1, user2);

        when(userQueryService.handle(any(GetAllUsersQuery.class))).thenReturn(users);

        // Act
        ResponseEntity<List<UserResource>> response = usersController.getAllUsers();

        // Assert
        assertNotNull(response);
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(userQueryService, times(1)).handle(any(GetAllUsersQuery.class));
    }


    @Test
    void testGetUserById_NotFound() {
        // Arrange
        Long userId = 99L;
        when(userQueryService.handle(new GetUserByIdQuery(userId))).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UserResource> response = usersController.getUserById(userId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(userQueryService, times(1)).handle(new GetUserByIdQuery(userId));
    }



}
