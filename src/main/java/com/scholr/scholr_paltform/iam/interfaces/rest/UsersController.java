package com.scholr.scholr_paltform.iam.interfaces.rest;

import com.scholr.scholr_paltform.iam.domain.model.queries.GetAllUsersQuery;
import com.scholr.scholr_paltform.iam.domain.model.queries.GetUserByIdQuery;
import com.scholr.scholr_paltform.iam.domain.services.UserCommandService;
import com.scholr.scholr_paltform.iam.domain.services.UserQueryService;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.UpdateProofingEntrepreneureResource;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.UserResource;
import com.scholr.scholr_paltform.iam.interfaces.rest.transform.UpdateProofingEntrepreneureCommandFromResourceAssembler;
import com.scholr.scholr_paltform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

  private final UserQueryService userQueryService;
  private final UserCommandService userCommandService;
  public UsersController(UserQueryService userQueryService, UserCommandService userCommandService)
  {
    this.userCommandService = userCommandService;
    this.userQueryService = userQueryService;
  }

  /**
   * This method returns all the users.
   *
   * @return a list of user resources.
   * @see UserResource
   */
  @GetMapping
  public ResponseEntity<List<UserResource>> getAllUsers() {
    var getAllUsersQuery = new GetAllUsersQuery();
    var users = userQueryService.handle(getAllUsersQuery);
    var userResources = users.stream()
        .map(UserResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(userResources);
  }

  /**
   * This method returns the user with the given id.
   *
   * @param userId the user id.
   * @return the user resource with the given id
   * @throws RuntimeException if the user is not found
   * @see UserResource
   */
  @GetMapping(value = "/{userId}")
  public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var user = userQueryService.handle(getUserByIdQuery);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return ResponseEntity.ok(userResource);
  }

  @PutMapping("/{userId}/update-proofing")
  public ResponseEntity<String> updateProofingEntrepreneure(
          @PathVariable Long userId,
          @RequestBody @Valid UpdateProofingEntrepreneureResource resource) {
    // Convertir el recurso en un comando
    var command = UpdateProofingEntrepreneureCommandFromResourceAssembler.toCommandFromResource(userId, resource);

    // Ejecutar la lógica de negocio
    userCommandService.updateProofingEntrepreneure(command);

    return ResponseEntity.ok("ProofingEntrepreneure updated successfully.");
  }

}
