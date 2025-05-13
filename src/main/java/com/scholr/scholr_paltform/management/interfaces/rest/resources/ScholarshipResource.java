package com.scholr.scholr_paltform.management.interfaces.rest.resources;

import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;

import java.util.List;

public record ScholarshipResource(
        Long id,
        String name,
        String companyName,
        List<Requirement> requirements,
        ScholarshipType scholarshipType,
        ScholarshipStatus scholarshipStatus,
        Long coordinatorId
) {}
