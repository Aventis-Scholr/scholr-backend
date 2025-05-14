package com.scholr.scholr_paltform.management.domain.services;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Report;
import com.scholr.scholr_paltform.management.domain.model.commands.*;

import java.util.Optional;

public interface ReportCommandService {
    Optional<Report> handle(CreateReportCommand command);
    Optional<Report> handle(UpdateReportCommand command);
    void handle(DeleteReportCommand command);
}
