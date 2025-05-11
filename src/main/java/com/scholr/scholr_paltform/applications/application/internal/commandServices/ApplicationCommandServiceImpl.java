package com.scholr.scholr_paltform.applications.application.internal.commandServices;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreatePostulanteCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.DeleteApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationCommandService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ApplicationCommandServiceImpl implements ApplicationCommandService {
    private final ApplicationRepository applicationRepository;

    public ApplicationCommandServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Long handle(CreateApplicationCommand command) {
        var application = new Application(command);
        try{
            this.applicationRepository.save(application);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while saving application: " + e.getMessage());
        }
        return application.getId();
    }

    @Override
    public Optional<Application> handle(UpdateApplicationCommand command){
        var applicationId = command.applicationId();

        var applicationToUpdate = this.applicationRepository.findById(applicationId).get();
        applicationToUpdate.UpdateApplication(command.idApoderado(),
                command.dataApoderado(),
                command.status(),
                command.tipoBeca(),
                command.postulante());
        try {
            var updatedApplication = this.applicationRepository.save(applicationToUpdate);
            return Optional.of(updatedApplication);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating application: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteApplicationCommand command){
        if (!this.applicationRepository.existsById(command.applicationId())){
            throw new IllegalArgumentException("Application does not exist");
        }

        try{
            this.applicationRepository.deleteById(command.applicationId());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting application: " + e.getMessage());
        }
    }


    //creacion de postulante
    // Creación de postulante
    /*@Override
    public Long handle(CreatePostulanteCommand command) {
        // Buscar la aplicación asociada
        var applicationOptional = this.applicationRepository.findById(command.getApplicationId());
        if (applicationOptional.isEmpty()) {
            throw new IllegalArgumentException("Application not found for ID: " + command.getApplicationId());
        }

        var application = applicationOptional.get();

        // Crear y agregar el postulante al agregado Application
        var postulante = new Postulante(command);
        application.addPostulante(postulante);

        try {
            // Guardar el agregado completo
            this.applicationRepository.save(application);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving application with postulante: " + e.getMessage());
        }

        return postulante.getId();
    }*/
}

