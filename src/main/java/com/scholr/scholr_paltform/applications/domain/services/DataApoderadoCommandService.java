package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.DeleteDeleteDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateDataApoderadoCommand;

import java.util.Optional;

public interface DataApoderadoCommandService {
    Long handle(CreateDataApoderadoCommand command);
    Optional<DataApoderado> handle(UpdateDataApoderadoCommand command);
    //void handle(DeleteDeleteDataApoderadoCommand command);
}
