package com.scholr.scholr_paltform.core.entities.unit.test;

import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.*;
import com.scholr.scholr_paltform.management.interfaces.rest.resources.CreateReportResource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagementServiceTest {

    @Test
    void TestCreateReport(){
        //Arrange
        Long applicationId = 1L;
        ApoderadoSnapshot apoderadoData = new ApoderadoSnapshot();
        PostulanteSnapshot postulanteSnapshot = new PostulanteSnapshot();
        ResolutionStatus resolution = ResolutionStatus.DRAFT;
        String adminComment = "Initial review";

        //Act
        CreateReportResource createReportResource = new CreateReportResource(
            applicationId,
            apoderadoData,
            postulanteSnapshot,
            resolution,
            adminComment
        );

        //Assert
        assertEquals(applicationId, createReportResource.applicationId());
        assertEquals(apoderadoData, createReportResource.apoderadoData());
        assertEquals(postulanteSnapshot, createReportResource.postulanteSnapshot());
        assertEquals(resolution, createReportResource.resolution());
        assertEquals(adminComment, createReportResource.adminComments());
    }


    @Test
    void TestCreateScholarship(){
        //Arrange
        String name = "Scholarship for Excellence";
        String companyName = "TechCorp";
        List<Requirement> requirements = List.of(
            new Requirement("High GPA", "Minimum GPA of 3.5", true),
            new Requirement("Community Service", "At least 50 hours of community service", true)
        );
        ScholarshipType scholarshipType = ScholarshipType.PARTIAL;
        ScholarshipStatus scholarshipStatus = ScholarshipStatus.PUBLISHED;
        Long coordinatorId = 2L;


        //Act
        CreateScholarshipCommand createScholarshipCommand = new CreateScholarshipCommand(
            name,
            companyName,
            requirements,
            scholarshipType,
            scholarshipStatus,
            coordinatorId
        );

        //Assert
        assertEquals(name, createScholarshipCommand.name());
        assertEquals(companyName, createScholarshipCommand.companyName());
        assertEquals(requirements, createScholarshipCommand.requirements());
        assertEquals(scholarshipType, createScholarshipCommand.scholarshipType());
        assertEquals(scholarshipStatus, createScholarshipCommand.scholarshipStatus());
        assertEquals(coordinatorId, createScholarshipCommand.coordinatorId());
    }
}
