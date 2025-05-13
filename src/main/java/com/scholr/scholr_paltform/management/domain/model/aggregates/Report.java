package com.scholr.scholr_paltform.management.domain.model.aggregates;

import com.scholr.scholr_paltform.management.domain.model.commands.UpdateReportCommand;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ApoderadoSnapshot;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.PostulanteSnapshot;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ResolutionStatus;
import com.scholr.scholr_paltform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Report extends AuditableAbstractAggregateRoot<Report> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long applicationId;

    @Embedded
    private ApoderadoSnapshot apoderadoData;

    @Embedded
    private PostulanteSnapshot postulanteSnapshot;

    private ResolutionStatus resolution;

    private String adminComments;

    public Report() {}

    public Report(Long applicationId, ApoderadoSnapshot apoderadoData, PostulanteSnapshot postulanteSnapshot, ResolutionStatus resolution, String adminComments) {
        this.applicationId = applicationId;
        this.apoderadoData = apoderadoData;
        this.postulanteSnapshot = postulanteSnapshot;
        this.resolution = resolution;
        this.adminComments = adminComments;
    }

    public void updateFromCommand(UpdateReportCommand command) {
        this.applicationId = command.applicationId();
        this.apoderadoData = command.apoderadoData();
        this.postulanteSnapshot = command.postulanteSnapshot();
        this.resolution = command.resolution();
        this.adminComments = command.adminComments();
    }
}
