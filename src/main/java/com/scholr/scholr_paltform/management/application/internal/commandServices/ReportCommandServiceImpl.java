package com.scholr.scholr_paltform.management.application.internal.commandServices;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Report;
import com.scholr.scholr_paltform.management.domain.model.commands.CreateReportCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.UpdateReportCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.DeleteReportCommand;
import com.scholr.scholr_paltform.management.domain.services.ReportCommandService;
import com.scholr.scholr_paltform.management.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {

    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        var report = new Report(
                command.applicationId(),
                command.apoderadoData(),
                command.postulanteSnapshot(),
                command.resolution(),
                command.adminComments()
        );
        try {
            var saved = reportRepository.save(report);
            return Optional.of(saved);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving report: " + e.getMessage());
        }
    }

    @Override
    public Optional<Report> handle(UpdateReportCommand command) {
        var reportOpt = reportRepository.findById(command.reportId());
        if (reportOpt.isEmpty()) throw new IllegalArgumentException("Report not found");

        var report = reportOpt.get();
        report.updateFromCommand(command); // crea un método en Report para actualizar sus campos
        try {
            var updated = reportRepository.save(report);
            return Optional.of(updated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating report: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteReportCommand command) {
        var reportOpt = reportRepository.findById(command.reportId());
        if (reportOpt.isEmpty()) {
            throw new IllegalArgumentException("Report not found");
        }

        try {
            reportRepository.deleteById(command.reportId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting report: " + e.getMessage());
        }
    }
}
