package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.queries.*;
import com.scholr.scholr_paltform.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationQueryService {
    List<Application> handle (GetAllApplicationsQuery query);
    List<Application> handle (GetPendingApplicationsByApoderadoId query);
    List<Application> handle (GetApplicationsByApoderadoIdQuery query);
    Optional<Application> handle (GetApplicationByIdQuery query);

    List<Long> handle(GetApoderadosWIthPendingApplicationByScholarshipId query);
}
