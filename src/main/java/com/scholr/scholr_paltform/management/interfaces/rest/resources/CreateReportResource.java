package com.scholr.scholr_paltform.management.interfaces.rest.resources;

import com.scholr.scholr_paltform.management.domain.model.valueobjects.ApoderadoSnapshot;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.PostulanteSnapshot;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ResolutionStatus;

public record CreateReportResource(
        Long applicationId,
        ApoderadoSnapshot apoderadoData,
        PostulanteSnapshot postulanteSnapshot,
        ResolutionStatus resolution,
        String adminComments
) {
}
