package com.scholr.scholr_paltform.iam.domain.services;

import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;
import com.scholr.scholr_paltform.iam.domain.model.commands.SignInCommand;
import com.scholr.scholr_paltform.iam.domain.model.commands.SignUpCommand;
import com.scholr.scholr_paltform.iam.domain.model.commands.UpdateProofingEntrepreneureCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);
  Optional<User> handle(SignUpCommand command);
  void updateProofingEntrepreneure(UpdateProofingEntrepreneureCommand command);
}