package com.scholr.scholr_paltform.integration.test;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.queries.GetAllScholarshipsQuery;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipQueryService;
import com.scholr.scholr_paltform.management.interfaces.rest.ReportController;
import com.scholr.scholr_paltform.management.interfaces.rest.ScholarshipsController;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.ScholarshipResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ManagementControllerTest {

    @InjectMocks
    private ScholarshipsController scholarshipsController;

    @Mock
    private ScholarshipQueryService scholarshipQueryService;

    //-------------------------------------------

    @Test
    void testGetAllScholarshipsSuccess() {
        //Arrange
        var scholarship1 = new Scholarship("Scholarship for Excellence", "TechCorp",
        List.of(
                new Requirement("High GPA", "Minimum GPA of 3.5", true),
                new Requirement("Community Service", "At least 50 hours of community service", true)
        ),
        ScholarshipType.PARTIAL,
        ScholarshipStatus.PUBLISHED,
        2L);

        var scholarship2 = new Scholarship("Innovation Grant", "Innovate Inc.",
        List.of(
                new Requirement("Project Proposal", "Submit a detailed project proposal", true),
                new Requirement("Team Collaboration", "Work in a team of at least 3 members", false)
        ),
        ScholarshipType.PARTIAL,
        ScholarshipStatus.PUBLISHED,
        3L);

        var scholarships = List.of(scholarship1, scholarship2);

        when(scholarshipQueryService.handle(any(GetAllScholarshipsQuery.class))).thenReturn(scholarships);

        // Act
        ResponseEntity<List<ScholarshipResource>> response = scholarshipsController.getAllScholarships();

        // Assert
        assertNotNull(response);
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(scholarshipQueryService, times(1)).handle(any(GetAllScholarshipsQuery.class));
    }

}
