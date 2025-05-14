package com.scholr.scholr_paltform.management.interfaces.rest;

import com.scholr.scholr_paltform.management.domain.model.commands.CreateReportCommand;
import com.scholr.scholr_paltform.management.domain.services.ReportCommandService;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.CreateReportResource;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.ReportResource;
import com.scholr.scholr_paltform.management.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.scholr.scholr_paltform.management.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Report Endpoints")
public class ReportController {

    private final ReportCommandService reportCommandService;

    public ReportController(ReportCommandService reportCommandService) {
        this.reportCommandService = reportCommandService;
    }

    @PostMapping
    public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource resource) {
        CreateReportCommand command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var optionalReport = reportCommandService.handle(command);

        if (optionalReport.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(optionalReport.get());
        return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
    }
}
