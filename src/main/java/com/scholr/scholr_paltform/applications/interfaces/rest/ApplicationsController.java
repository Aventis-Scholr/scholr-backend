package com.scholr.scholr_paltform.applications.interfaces.rest;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.commands.DeleteApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.RejectAllApplicationsByApoderadoId;
import com.scholr.scholr_paltform.applications.domain.model.queries.*;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationCommandService;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationQueryService;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.*;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.*;

import com.scholr.scholr_paltform.management.domain.model.queries.GetScholarshipByNameQuery;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/applications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Applications", description = "Application Endpoints")
public class ApplicationsController {
    private final ApplicationQueryService applicationsQueryService;
    private final ApplicationCommandService applicationsCommandService;
    private final ScholarshipQueryService scholarshipQueryService;

    public ApplicationsController(ApplicationQueryService applicationsQueryService, ApplicationCommandService applicationsCommandService, ScholarshipQueryService scholarshipQueryService) {
        this.applicationsQueryService = applicationsQueryService;
        this.applicationsCommandService = applicationsCommandService;
        this.scholarshipQueryService = scholarshipQueryService;
    }

    //post postulacion

    @PostMapping(value = "/apoderado/{apoderadoId}")
    public ResponseEntity<ApplicationResource> createApplication(
            @PathVariable Long apoderadoId, @RequestBody CreateApplicationResource resource){


        var scholarship = this.scholarshipQueryService.handle(new GetScholarshipByNameQuery(resource.scholarshipName()));

        if (scholarship == null || scholarship.isEmpty()) {
            System.out.println("scholarship no encontrada");
            return ResponseEntity.badRequest().body(null);
        }

        System.out.println("scholarship id:" + scholarship.get().getId());

        var createApplicationCommand = CreateApplicationCommandFromResourceAssembler.toCommandFromResource(apoderadoId, resource, scholarship.get().getId());
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

    //get applications
    @GetMapping
    public ResponseEntity<List<ApplicationResource>> getAllApplications() {
        var getAllApplicationsQuery = new GetAllApplicationsQuery();
        var applications = this.applicationsQueryService.handle(getAllApplicationsQuery);
        var applicationResponseResources = applications.stream()
                .map(ApplicationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(applicationResponseResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResource> getApplicationById(@PathVariable Long id) {
        var getApplicationByIdQuery = new GetApplicationByIdQuery(id);
        var optionalApplication = this.applicationsQueryService.handle(getApplicationByIdQuery);
        if (optionalApplication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return ResponseEntity.ok(applicationResource);
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

    @GetMapping("/pendingapplications/{apoderadoId}")
    public ResponseEntity<List<ApplicationResource>> getPendingApplicationsByApoderadoId(@PathVariable Long apoderadoId) {
        var getPendingApplicationsByApoderadoIdQuery = new GetPendingApplicationsByApoderadoId(apoderadoId);
        var applications = this.applicationsQueryService.handle(getPendingApplicationsByApoderadoIdQuery);
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

        var scholarship = this.scholarshipQueryService.handle(new GetScholarshipByNameQuery(resource.scholarshipName()));

        if (scholarship == null || scholarship.isEmpty()) {
            System.out.println("scholarship no encontrada");
            return ResponseEntity.badRequest().body(null);
        }

        var updateApplicationCommand = UpdateApplicationCommandFromResourceAssembler.toCommandFromResource(id, resource, scholarship.get().getId());
        var optionalApplication = this.applicationsCommandService.handle(updateApplicationCommand);

        if (optionalApplication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return ResponseEntity.ok(applicationResource);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResource> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusApplicationResource resource) {
        var command = UpdateStatusApplicationCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalApplication = applicationsCommandService.handle(command);

        if (optionalApplication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return ResponseEntity.ok(applicationResource);
    }

    @PutMapping("/{id}/reporte")
    public ResponseEntity<ApplicationResource> updateReporte(@PathVariable Long id, @RequestBody UpdateReporteApplicationResource resource) {
        var command = UpdateReporteApplicationCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalApplication = applicationsCommandService.handle(command);

        if (optionalApplication.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var applicationResource = ApplicationResourceFromEntityAssembler.toResourceFromEntity(optionalApplication.get());
        return ResponseEntity.ok(applicationResource);
    }



    //--------------------------------------------

    @PostMapping(value = "/{applicationId}/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Archivo subido exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<?> subirDni(
            @PathVariable Long applicationId,
            @RequestParam("postulante_dni") MultipartFile postulante_dni,
            @RequestParam("postulante_libreta_notas") MultipartFile postulante_libreta_notas,
            @RequestParam("postulante_const_logro_aprendizaje") MultipartFile postulante_const_logro_aprendizaje,

            @RequestParam("apoderado_dni") MultipartFile apoderado_dni,
            @RequestParam("apoderado_declaracion_jurada") MultipartFile apoderado_declaracion_jurada

    ) {

        try {
            String dni = applicationsCommandService.handle(postulante_dni);
            String libreta_notas = applicationsCommandService.handle(postulante_libreta_notas);
            String const_logro_aprendizaje = applicationsCommandService.handle(postulante_const_logro_aprendizaje);

            String apoderadoDni = applicationsCommandService.handle(apoderado_dni);
            String apoderadoDeclaracionJurada = applicationsCommandService.handle(apoderado_declaracion_jurada);


            var getApplicationByIdQuery = new GetApplicationByIdQuery(applicationId);
            Application application = applicationsQueryService.handle(getApplicationByIdQuery)
                    .orElseThrow(() -> new RuntimeException("No existe la postulación"));

            application.setPostulante_dni(dni);
            application.setPostulante_libreta_notas(libreta_notas);
            application.setPostulante_const_logro_aprendizaje(const_logro_aprendizaje);

            application.setApoderado_dni(apoderadoDni);
            application.setApoderado_declaracion_jurada(apoderadoDeclaracionJurada);

            applicationsCommandService.handle(applicationId,
                    dni,
                    libreta_notas,
                    const_logro_aprendizaje,
                    apoderadoDni,
                    apoderadoDeclaracionJurada);

            return ResponseEntity.ok().body(Map.of(
                    "dni", dni,
                    "libreta_notas", libreta_notas,
                    "const_logro_aprendizaje", const_logro_aprendizaje
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/rejectAllApplicationsByApoderadoId/{apoderadoId}")
    public ResponseEntity<Void> rejectAllApplicationsByApoderadoId(@PathVariable Long apoderadoId) {
        try {
            var command = new RejectAllApplicationsByApoderadoId(apoderadoId);
            applicationsCommandService.handle(command);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
