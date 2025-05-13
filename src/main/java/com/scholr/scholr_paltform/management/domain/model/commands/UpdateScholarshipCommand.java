package com.scholr.scholr_paltform.management.domain.model.commands;

import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;

import java.util.List;

public record UpdateScholarshipCommand(
        Long id,
        String name,
        String companyName, // Nota para el futuro: Esencialmente queremos que no se pueda cambiar
        List<Requirement> requirements,
        ScholarshipType scholarshipType,
        ScholarshipStatus scholarshipStatus,
        Long coordinatorId
) {
}
