package com.scholr.scholr_paltform.management.interfaces.rest;

import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.queries.GetAllScholarshipsQuery;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipCommandService;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipQueryService;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.CreateScholarshipResource;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.ScholarshipResource;
import com.scholr.scholr_paltform.management.interfaces.rest.transform.CreateScholarshipCommandFromResourceAssembler;
import com.scholr.scholr_paltform.management.interfaces.rest.transform.ScholarshipResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/scholarships", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Scholarships", description = "Scholarship Endpoints")
public class ScholarshipsController {
    private final ScholarshipQueryService scholarshipsQueryService;
    private final ScholarshipCommandService scholarshipsCommandService;

    public ScholarshipsController(ScholarshipQueryService queryService, ScholarshipCommandService commandService) {
        this.scholarshipsQueryService = queryService;
        this.scholarshipsCommandService = commandService;
    }

    @PostMapping
    public ResponseEntity<ScholarshipResource> createScholarship(@RequestBody CreateScholarshipResource resource) {
        CreateScholarshipCommand command = CreateScholarshipCommandFromResourceAssembler.toCommandFromResource(resource);
        var optionalScholarship = scholarshipsCommandService.handle(command);

        if (optionalScholarship.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var scholarshipResource = ScholarshipResourceFromEntityAssembler.toResourceFromEntity(optionalScholarship.get());
        return new ResponseEntity<>(scholarshipResource, HttpStatus.CREATED);
    }

    //get scholarships
    @GetMapping
    public ResponseEntity<List<ScholarshipResource>> getAllScholarships() {
        var getAllScholarshipsQuery = new GetAllScholarshipsQuery();
        var scholarships = this.scholarshipsQueryService.handle(getAllScholarshipsQuery);
        var scholarshipResources = scholarships.stream()
                .map(ScholarshipResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scholarshipResources);
    }
}
