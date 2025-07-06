package com.scholr.scholr_paltform.applications.application.internal.queryServices;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.queries.*;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationQueryService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationQueryServiceImpl implements ApplicationQueryService {
    private final ApplicationRepository applicationRepository;

    public ApplicationQueryServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Application> handle(GetAllApplicationsQuery query) {
        return this.applicationRepository.findAll();
    }

    @Override
    public List<Application> handle(GetPendingApplicationsByApoderadoId query) {
        return this.applicationRepository.findByIdApoderado(query.apoderadoId())
                .stream()
                .filter(application -> application.getStatus().equals(Status.PENDIENTE))
                .toList();
    }

    @Override
    public List<Application> handle(GetApplicationsByApoderadoIdQuery query) {
        return this.applicationRepository.findByIdApoderado(query.apoderadoId());
    }

    @Override
    public Optional<Application> handle (GetApplicationByIdQuery query){
        return this.applicationRepository.findById(query.applicationId());
    }

    @Override
    public List<Long> handle(GetApoderadosWIthPendingApplicationByScholarshipId query) {
        return this.applicationRepository.findByScholarshipId(query.scholarshipId())
                .stream()
                .filter(application -> application.getStatus().equals(Status.PENDIENTE))
                .map(Application::getIdApoderado)
                .distinct()
                .toList();
    }

}
