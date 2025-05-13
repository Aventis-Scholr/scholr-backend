package com.scholr.scholr_paltform.management.domain.model.commands;

import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;

import java.util.List;

public record CreateScholarshipCommand(
        String name,
        String companyName,
        List<Requirement> requirements,
        ScholarshipType scholarshipType,
        ScholarshipStatus scholarshipStatus,
        Long coordinatorId
) {
}
