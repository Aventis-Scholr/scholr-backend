package com.scholr.scholr_paltform.management.domain.model.commands;

import com.scholr.scholr_paltform.management.domain.model.valueobjects.ApoderadoSnapshot;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.PostulanteSnapshot;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ResolutionStatus;

public record CreateReportCommand(
        Long applicationId,
        ApoderadoSnapshot apoderadoData,
        PostulanteSnapshot postulanteSnapshot,
        ResolutionStatus resolution,
        String adminComments
) {}
