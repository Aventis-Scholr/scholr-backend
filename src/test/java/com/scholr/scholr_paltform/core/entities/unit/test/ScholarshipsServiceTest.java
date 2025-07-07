package com.scholr.scholr_paltform.core.entities.unit.test;

import com.scholr.scholr_paltform.management.application.internal.commandServices.ScholarshipCommandServiceImpl;
import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.Requirement;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipStatus;
import com.scholr.scholr_paltform.management.domain.model.valueobjects.ScholarshipType;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipCommandService;
import com.scholr.scholr_paltform.management.infrastructure.persistence.jpa.repositories.ScholarshipRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScholarshipsServiceTest {

    private ScholarshipRepository repository;
    private ScholarshipCommandService service;

    @BeforeEach
    void setUp() {
        repository = mock(ScholarshipRepository.class);
        service = new ScholarshipCommandServiceImpl(repository);
    }

    @Test
    void testCreateScholarshipSuccess() {
        // Arrange
        CreateScholarshipCommand command = new CreateScholarshipCommand(
                "Beca Excelencia",
                "Universidad X",
                List.of(new Requirement("Promedio mayor a 14", "Debe tener promedio académico mínimo", true)),
                ScholarshipType.TOTAL,
                ScholarshipStatus.PUBLISHED,
                99L
        );

        Scholarship scholarship = new Scholarship(
                command.name(),
                command.companyName(),
                command.requirements(),
                command.scholarshipType(),
                command.scholarshipStatus(),
                command.coordinatorId()
        );

        // Simular que se setea el ID luego de guardar
        ReflectionTestUtils.setField(scholarship, "id", 100L);

        when(repository.save(any(Scholarship.class))).thenAnswer(invocation -> {
            Scholarship saved = invocation.getArgument(0);
            ReflectionTestUtils.setField(saved, "id", 100L);
            return saved;
        });

        // Act
        Optional<Scholarship> result = service.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Beca Excelencia", result.get().getName());
        assertEquals(100L, result.get().getId());
        verify(repository, times(1)).save(any(Scholarship.class));
    }
}
