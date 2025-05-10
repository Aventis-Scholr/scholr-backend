package com.scholr.scholr_paltform.iam.application.internal.commandservices;

import com.scholr.scholr_paltform.iam.application.internal.outboundservices.hashing.HashingService;
import com.scholr.scholr_paltform.iam.application.internal.outboundservices.tokens.TokenService;
import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;
import com.scholr.scholr_paltform.iam.domain.model.commands.SignInCommand;
import com.scholr.scholr_paltform.iam.domain.model.commands.SignUpCommand;
import com.scholr.scholr_paltform.iam.domain.model.commands.UpdateProofingEntrepreneureCommand;
import com.scholr.scholr_paltform.iam.domain.services.ColaboradorValidationService;
import com.scholr.scholr_paltform.iam.domain.services.UserCommandService;
import com.scholr.scholr_paltform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.scholr.scholr_paltform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

  //Servicio validacion de colaborador
  private final ColaboradorValidationService colaboradorValidationService;

  private final UserRepository userRepository;
  private final HashingService hashingService;
  private final TokenService tokenService;
  private final RoleRepository roleRepository;

  public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
                                TokenService tokenService, RoleRepository roleRepository,ColaboradorValidationService colaboradorValidationService) {

    this.userRepository = userRepository;
    this.hashingService = hashingService;
    this.tokenService = tokenService;
    this.roleRepository = roleRepository;
    this.colaboradorValidationService = colaboradorValidationService;
  }

  @Override
  public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
    var user = userRepository.findByUsername(command.username());
    if (user.isEmpty())
      throw new RuntimeException("User not found");
    if (!hashingService.matches(command.password(), user.get().getPassword()))
      throw new RuntimeException("Invalid password");

    var token = tokenService.generateToken(user.get().getUsername());
    return Optional.of(ImmutablePair.of(user.get(), token));
  }

  @Override
  public Optional<User> handle(SignUpCommand command) {

    if (userRepository.existsByUsername(command.username()))
      throw new RuntimeException("Username already exists");
    var roles = command.roles().stream()
            .map(role ->
                    roleRepository.findByName(role.getName())
                            .orElseThrow(() -> new RuntimeException("Role name not found")))
            .toList();
    // Validar si dni y cod_colaborador coinciden en la tabla correspondiente a la compañia
    boolean esValido = colaboradorValidationService.validarColaborador(
            command.compania(),
            command.dni(),
            command.cod_colaborador()
    );

    if (!esValido) {
      throw new RuntimeException("DNI y código de colaborador no coinciden para la empresa proporcionada.");
    }

    //Aca tambien se añadio el compania,dni,cod_colaborador
    var user = new User(command.username(), hashingService.encode(command.password()), command.compania(), command.dni(), command.cod_colaborador(), roles);
    userRepository.save(user);
    return userRepository.findByUsername(command.username());
  }

  @Override
  @Transactional
  public void updateProofingEntrepreneure(UpdateProofingEntrepreneureCommand command) {
    // Validar si el usuario tiene el rol ROLE_ENTREPRENEUR
    if (!userRepository.hasEntrepreneurRole(command.userId())) {
      throw new IllegalArgumentException("Access denied: The user does not have the entrepreneur role.");
    }

    var userOptional = userRepository.findById(command.userId());
    if (userOptional.isEmpty()) {
      throw new IllegalArgumentException("User not found with ID: " + command.userId());
    }

    var user = userOptional.get();
    user.setProofingEntrepreneure(command.proofingEntrepreneure());
    userRepository.save(user);
  }
}