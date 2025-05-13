package com.scholr.scholr_paltform.management.domain.services;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.DeleteScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.UpdateScholarshipCommand;

import java.util.Optional;

public interface ScholarshipCommandService {
    Optional<Scholarship> handle(CreateScholarshipCommand command);
    Optional<Scholarship> handle(UpdateScholarshipCommand command);
    void handle(DeleteScholarshipCommand command);
}
