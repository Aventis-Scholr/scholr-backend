package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.commands.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ApplicationCommandService {
    Long handle(CreateApplicationCommand command);
    Optional<Application> handle(UpdateApplicationCommand command);
    Optional<Application> handle(UpdateStatusApplicationCommand command);
    Optional<Application> handle(UpdateReporteApplicationCommand command);
    void handle(DeleteApplicationCommand command);
    String handle(MultipartFile file);

    Long handle(Long applicationId, String dni_postulante,
                String postulante_libreta_notas,
                String postulante_const_logro_aprendizaje,
                String apoderadoDni,
                String apoderadoDeclaracionJurada);

    void handle(RejectAllApplicationsByApoderadoId command);
}
