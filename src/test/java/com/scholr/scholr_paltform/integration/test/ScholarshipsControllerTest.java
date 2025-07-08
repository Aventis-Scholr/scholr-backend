package com.scholr.scholr_paltform.integration.test;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.queries.GetAllScholarshipsQuery;
import com.scholr.scholr_paltform.management.domain.model.queries.GetScholarshipsByCompanyNameQuery;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipCommandService;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipQueryService;
import com.scholr.scholr_paltform.management.interfaces.rest.ScholarshipsController;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.CreateScholarshipResource;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.ScholarshipResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScholarshipsControllerTest {

    @Mock
    private ScholarshipCommandService scholarshipCommandService;

    @Mock
    private ScholarshipQueryService scholarshipQueryService;

    @InjectMocks
    private ScholarshipsController scholarshipsController;

    @Test
    @DisplayName("✅ Debería registrar una nueva beca")
    void testCreateScholarship() {
        // Arrange
        CreateScholarshipResource resource = new CreateScholarshipResource(
                "Beca TechCorp",
                "TechCorp",
                List.of(new Requirement("Estudiante universitario", "Debe estar matriculado en una universidad", true)),
                ScholarshipType.TOTAL,
                ScholarshipStatus.PUBLISHED,
                100L
        );

        Scholarship scholarship = new Scholarship(
                resource.name(),
                resource.companyName(),
                resource.requirements(),
                resource.scholarshipType(),
                resource.scholarshipStatus(),
                resource.coordinatorId()
        );

        when(scholarshipCommandService.handle(any(CreateScholarshipCommand.class)))
                .thenReturn(Optional.of(scholarship));

        // Act
        ResponseEntity<ScholarshipResource> response = scholarshipsController.createScholarship(resource);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Beca TechCorp", response.getBody().name());
        assertEquals("TechCorp", response.getBody().companyName());
    }

    @Test
    @DisplayName("✅ Debería retornar todas las becas")
    void testGetAllScholarships() {
        // Arrange
        Scholarship scholarship1 = new Scholarship("Beca A", "Empresa A", List.of(), ScholarshipType.PARTIAL, ScholarshipStatus.DRAFT, 1L);
        Scholarship scholarship2 = new Scholarship("Beca B", "Empresa B", List.of(), ScholarshipType.TOTAL, ScholarshipStatus.PUBLISHED, 2L);

        when(scholarshipQueryService.handle(any(GetAllScholarshipsQuery.class)))
                .thenReturn(List.of(scholarship1, scholarship2));

        // Act
        ResponseEntity<List<ScholarshipResource>> response = scholarshipsController.getAllScholarships();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("✅ Debería obtener becas por nombre de empresa")
    void testGetScholarshipsByCompanyName() {
        // Arrange
        String companyName = "TechCorp";
        Scholarship scholarship = new Scholarship("Beca TechCorp", companyName, List.of(), ScholarshipType.TOTAL, ScholarshipStatus.PUBLISHED, 3L);

        when(scholarshipQueryService.handle(any(GetScholarshipsByCompanyNameQuery.class)))
                .thenReturn(List.of(scholarship));

        // Act
        ResponseEntity<List<ScholarshipResource>> response = scholarshipsController.getScholarshipsByCompanyName(companyName);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("TechCorp", response.getBody().get(0).companyName());
    }
}
