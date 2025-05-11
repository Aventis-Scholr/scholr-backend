package com.scholr.scholr_paltform.applications.application.internal.queryServices;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetAllApplicationsQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetApplicationsByApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationQueryService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.ApplicationRepository;

import java.util.List;

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
    public List<Application> handle(GetApplicationsByApoderadoIdQuery query) {
        return this.applicationRepository.findByApoderadoId(query.apoderadoId());
    }

}
