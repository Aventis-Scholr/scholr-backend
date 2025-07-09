package com.scholr.scholr_paltform.applications.application.internal.commandServices;

import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.commands.*;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationCommandService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import com.cloudinary.Cloudinary;

@Service
public class ApplicationCommandServiceImpl implements ApplicationCommandService {
    private final ApplicationRepository applicationRepository;

    private final Cloudinary cloudinary;

    public ApplicationCommandServiceImpl(ApplicationRepository applicationRepository, Cloudinary cloudinary) {
        this.applicationRepository = applicationRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public Long handle(CreateApplicationCommand command) {
        var application = new Application(command);
        try {
            this.applicationRepository.save(application);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving application: " + e.getMessage());
        }
        return application.getId();
    }

    @Override
    public Optional<Application> handle(UpdateApplicationCommand command) {
        var applicationId = command.applicationId();

        var applicationToUpdate = this.applicationRepository.findById(applicationId).get();
        applicationToUpdate.UpdateApplication(
                command.status(),
                command.scholarshipId(),
                command.postulante());
        try {
            var updatedApplication = this.applicationRepository.save(applicationToUpdate);
            return Optional.of(updatedApplication);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating application: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteApplicationCommand command) {
        if (!this.applicationRepository.existsById(command.applicationId())) {
            throw new IllegalArgumentException("Application does not exist");
        }

        try {
            this.applicationRepository.deleteById(command.applicationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting application: " + e.getMessage());
        }
    }

    //-----------------------------------------------

    @Override
    public String handle(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto", // detecta PDF, imagen, video
                            "folder", "pdfs"         // opcional: subcarpeta
                    )
            );

            String publicId = (String) uploadResult.get("public_id");

            // ⚠️ Usa resourceType raw para PDFs
            String downloadUrl = cloudinary.url()
                    .resourceType("image")
                    .secure(true)
                    .transformation(new Transformation().flags("attachment"))
                    .generate(publicId);

            return downloadUrl;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while uploading file: " + e.getMessage(), e);
        }
    }


    //-----------------------------------------------


    @Override
    public Long handle(Long applicationId, String dni_postulante, String postulante_libreta_notas, String postulante_const_logro_aprendizaje, String apoderadoDni,
                       String apoderadoDeclaracionJurada) {
        var applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new IllegalArgumentException("Application not found for ID: " + applicationId);
        }

        var application = applicationOptional.get();
        application.setPostulante_dni(dni_postulante);
        application.setPostulante_libreta_notas(postulante_libreta_notas);
        application.setPostulante_const_logro_aprendizaje(postulante_const_logro_aprendizaje);

        application.setApoderado_dni(apoderadoDni);
        application.setApoderado_declaracion_jurada(apoderadoDeclaracionJurada);

        try {
            this.applicationRepository.save(application);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving application with postulante: " + e.getMessage());
        }

        return application.getId();
    }

    @Override
    public Optional<Application> handle(UpdateStatusApplicationCommand command) {
        var applicationOptional = this.applicationRepository.findById(command.id());
        if (applicationOptional.isEmpty()) {
            return Optional.empty();
        }

        var application = applicationOptional.get();
        application.setStatus(command.status());

        try {
            applicationRepository.save(application);
            return Optional.of(application);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating status: " + e.getMessage());
        }
    }

    @Override
    public Optional<Application> handle(UpdateReporteApplicationCommand command) {
        var applicationOptional = this.applicationRepository.findById(command.id());
        if (applicationOptional.isEmpty()) {
            return Optional.empty();
        }

        var application = applicationOptional.get();
        application.setReporte(command.reporte());

        try {
            applicationRepository.save(application);
            return Optional.of(application);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating report: " + e.getMessage());
        }
    }

    //reject all applications by apoderado id

    @Override
    public void handle(RejectAllApplicationsByApoderadoId command) {
        var applications = applicationRepository.findByIdApoderado(command.apoderadoId());
        if (applications.isEmpty()) {
            throw new IllegalArgumentException("No applications found for apoderadoId: " + command.apoderadoId());
        }

        applications.forEach(application -> application.setStatus(Status.RECHAZADO));

        try {
            applicationRepository.saveAll(applications);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while rejecting applications: " + e.getMessage());
        }
    }

}