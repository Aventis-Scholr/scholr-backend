package com.scholr.scholr_paltform.management.domain.model.aggregates;

import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.UpdateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;
import com.scholr.scholr_paltform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Scholarship extends AuditableAbstractAggregateRoot<Scholarship> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String companyName;

    @ElementCollection
    private List<Requirement> requirements;

    private ScholarshipType scholarshipType;

    private ScholarshipStatus scholarshipStatus;

    private Long coordinatorId;

    public Scholarship() {}

    public Scholarship(String name, String companyName, List<Requirement> requirements, ScholarshipType scholarshipType, ScholarshipStatus scholarshipStatus, Long coordinatorId) {
        this.name = name;
        this.companyName = companyName;
        this.requirements = requirements;
        this.scholarshipType = scholarshipType;
        this.scholarshipStatus = scholarshipStatus;
        this.coordinatorId = coordinatorId;
    }

    public Scholarship(CreateScholarshipCommand command) {
        this.name = command.name();
        this.companyName = command.companyName();
        this.requirements = command.requirements();
        this.scholarshipType = command.scholarshipType();
        this.scholarshipStatus = command.scholarshipStatus();
        this.coordinatorId = command.coordinatorId();
    }

    public void updateFromCommand(UpdateScholarshipCommand command) {
        // Actualiza solo los campos permitidos (según la lógica del negocio)
        if (command.name() != null) {
            this.name = command.name();
        }
        if (command.requirements() != null) {
            this.requirements = command.requirements();
        }
        if (command.scholarshipType() != null) {
            this.scholarshipType = command.scholarshipType();
        }
        if (command.scholarshipStatus() != null) {
            this.scholarshipStatus = command.scholarshipStatus();
        }
        if (command.coordinatorId() != null) {
            this.coordinatorId = command.coordinatorId();
        }
        // El campo 'companyName' no se actualiza según lo indicado
    }
}
