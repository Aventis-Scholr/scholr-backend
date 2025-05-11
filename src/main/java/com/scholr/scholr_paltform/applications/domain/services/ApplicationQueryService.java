package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetAllApplicationsQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetApplicationsByApoderadoIdQuery;

import java.util.List;

public interface ApplicationQueryService {
    List<Application> handle (GetAllApplicationsQuery command);
    List<Application> handle (GetApplicationsByApoderadoIdQuery command);
    //Optional<Application> handle (GetApplicationById)
}
