package com.scholr.scholr_paltform.management.application.internal.commandServices;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.commands.CreateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.DeleteScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.model.commands.UpdateScholarshipCommand;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipCommandService;
import com.scholr.scholr_paltform.management.infrastructure.persistence.jpa.repositories.ScholarshipRepository;

import java.util.Optional;

public class ScholarshipCommandServiceImpl implements ScholarshipCommandService {
    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipCommandServiceImpl(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    @Override
    public Optional<Scholarship> handle(CreateScholarshipCommand command) {
        // Crear una nueva beca a partir del comando
        var scholarship = new Scholarship(
                command.name(),
                command.companyName(),
                command.requirements(),
                command.scholarshipType(),
                command.scholarshipStatus(),
                command.coordinatorId()
        );

        try {
            var saved = scholarshipRepository.save(scholarship);
            return Optional.of(saved);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating scholarship: " + e.getMessage());
        }
    }

    @Override
    public Optional<Scholarship> handle(UpdateScholarshipCommand command) {
        // Buscar la beca existente
        var scholarshipOpt = scholarshipRepository.findById(command.id());
        if (scholarshipOpt.isEmpty()) {
            throw new IllegalArgumentException("Scholarship not found");
        }

        var scholarship = scholarshipOpt.get();

        // Actualizar los campos de la beca
        scholarship.updateFromCommand(command);

        try {
            var updated = scholarshipRepository.save(scholarship);
            return Optional.of(updated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating scholarship: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteScholarshipCommand command) {
        // Eliminar la beca
        var scholarshipOpt = scholarshipRepository.findById(command.id());
        if (scholarshipOpt.isEmpty()) {
            throw new IllegalArgumentException("Scholarship not found");
        }

        var scholarship = scholarshipOpt.get();

        try {
            scholarshipRepository.delete(scholarship);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting scholarship: " + e.getMessage());
        }
    }
}
