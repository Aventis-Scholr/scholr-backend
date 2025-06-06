package com.scholr.scholr_paltform.applications.interfaces.rest;

import com.scholr.scholr_paltform.applications.domain.model.commands.DeleteApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.queries.*;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationCommandService;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationQueryService;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.*;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.ApplicationResourceFromEntityAssembler;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.CreateApplicationCommandFromResourceAssembler;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.UpdateApplicationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/applications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Applications", description = "Application Endpoints")
public class ApplicationsController {
    private final ApplicationQueryService applicationsQueryService;
    private final ApplicationCommandService applicationsCommandService;

    public ApplicationsController(ApplicationQueryService applicationsQueryService, ApplicationCommandService applicationsCommandService) {
        this.applicationsQueryService = applicationsQueryService;
        this.applicationsCommandService = applicationsCommandService;

    }

    //post postulacion

    @PostMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<ApplicationResource> createApplication(@PathVariable Long apoderadoId, @RequestBody CreateApplicationResource resource) {


        var createApplicationCommand = CreateApplicationCommandFromResourceAssembler.toCommandFromResource(apoderadoId, resource);
        var applicationId = this.applicationsCommandService.handle(createApplicationCommand);

        if (applicationId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getApplicationByIdQuery = new GetApplicationByIdQuery(applicationId);
        var optionalApplication = this.applicationsQueryService.handle(getApplicationByIdQuery);

        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return new ResponseEntity<>(applicationResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApplicationResource> deleteApplication(@PathVariable Long id){
        var deleteApplicationCommand = new DeleteApplicationCommand(id);
        this.applicationsCommandService.handle(deleteApplicationCommand);
        return ResponseEntity.ok().build();
    }

    //post postulante
    /*
    @PostMapping("/{id}/postulante")
    public ResponseEntity<PostulanteResource> createPostulante(@PathVariable Long id, @RequestBody CreatePostulanteResource resource){
        var createPostulanteCommand = new CreatePostulanteCommand(
                resource.nombres(),
                resource.apellidos(),
                resource.dni(),
                resource.fechaNacimiento(),
                resource.contacto(),
                resource.centroEstudios()
                );

        var postulanteId = this.applicationsCommandService.handle(createPostulanteCommand);
    }*/

    //get applications
    @GetMapping
    public ResponseEntity<List<ApplicationResource>> getAllApplications() {
        var getAllApplicationsQuery = new GetAllApplicationsQuery();
        var applications = this.applicationsQueryService.handle(getAllApplicationsQuery);
        var applicationResources = applications.stream()
                .map(ApplicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(applicationResources);
    }

    @GetMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<List<ApplicationResource>> getApplicationsByApoderadoId(@PathVariable Long apoderadoId) {
        var getApplicationsByApoderadoIdQuery = new GetApplicationsByApoderadoIdQuery(apoderadoId);
        var applications = this.applicationsQueryService.handle(getApplicationsByApoderadoIdQuery);
        if (applications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var applicationResources = applications.stream()
                .map(ApplicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(applicationResources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResource> updateApplication(@PathVariable Long id, @RequestBody UpdateApplicationResource resource) {
        var updateApplicationCommand = UpdateApplicationCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalApplication = this.applicationsCommandService.handle(updateApplicationCommand);

        if (optionalApplication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return ResponseEntity.ok(applicationResource);
    }

    //getApplicationById
    /*
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResource> getApplicationById(@PathVariable Long id) {
        var getApplicationByIdQuery = new GetApplicationByIdQuery(id);
        var optionalApplication = this.applicationsQueryService.handle(getApplicationByIdQuery);
        if (optionalApplication.isEmpty())
            return ResponseEntity.badRequest().build();
        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return ResponseEntity.ok(applicationResource);
    }*/

    /*@PostMapping
    public ResponseEntity<ApplicationResource> createApplication(@RequestBody CreateApplicationResource resource) {
        // Crear la aplicación
        var createApplicationCommand = CreateApplicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var applicationId = this.applicationsCommandService.handle(createApplicationCommand);

        if (applicationId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        // Crear el postulante asociado
        var createPostulanteCommand = new CreatePostulanteCommand(
                resource.postulante().getNombres(),
                resource.postulante().getApellidos(),
                resource.postulante().getDni(),
                resource.postulante().getFechaNacimiento(),
                resource.postulante().getContacto(),
                resource.postulante().getCentroEstudios()
        );
        this.applicationsCommandService.handle(createPostulanteCommand);

        // Obtener la aplicación creada
        var getApplicationByIdQuery = new GetApplicationByIdQuery(applicationId);
        var optionalApplication = this.applicationsQueryService.handle(getApplicationByIdQuery);

        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return new ResponseEntity<>(applicationResource, HttpStatus.CREATED);
    }*/
}
