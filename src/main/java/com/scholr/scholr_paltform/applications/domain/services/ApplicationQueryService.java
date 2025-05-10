package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetAllApplications;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetApplicationsByApoderadoId;

import java.util.List;
import java.util.Optional;

public interface ApplicationQueryService {
    List<Application> handle (GetAllApplications command);
    List<Application> handle (GetApplicationsByApoderadoId command);
    //Optional<Application> handle (GetApplicationById)
}
