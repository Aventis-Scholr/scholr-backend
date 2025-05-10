package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.DeleteApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateApplicationCommand;

import java.util.Optional;

public interface ApplicationCommandService {
    Long handle(CreateApplicationCommand command);
    Optional<Application> handle(UpdateApplicationCommand command);
    void handle(DeleteApplicationCommand command);
}
