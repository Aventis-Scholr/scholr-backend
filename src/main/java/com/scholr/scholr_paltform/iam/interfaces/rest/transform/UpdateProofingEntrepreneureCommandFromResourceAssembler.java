package com.scholr.scholr_paltform.iam.interfaces.rest.transform;

import com.scholr.scholr_paltform.iam.domain.model.commands.UpdateProofingEntrepreneureCommand;
import com.scholr.scholr_paltform.iam.interfaces.rest.resources.UpdateProofingEntrepreneureResource;

public class UpdateProofingEntrepreneureCommandFromResourceAssembler {
    public static UpdateProofingEntrepreneureCommand toCommandFromResource(Long userId, UpdateProofingEntrepreneureResource resource) {
        return new UpdateProofingEntrepreneureCommand(
                userId,
                resource.proofingEntrepreneure()
        );
    }
}