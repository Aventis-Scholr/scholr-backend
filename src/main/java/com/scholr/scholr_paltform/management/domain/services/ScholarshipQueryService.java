package com.scholr.scholr_paltform.management.domain.services;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.queries.GetAllScholarshipsQuery;
import com.scholr.scholr_paltform.management.domain.model.queries.GetScholarshipByIdQuery;
import com.scholr.scholr_paltform.management.domain.model.queries.GetScholarshipsByCompanyNameQuery;

import java.util.List;
import java.util.Optional;

public interface ScholarshipQueryService {
    List<Scholarship> handle(GetAllScholarshipsQuery query);
    List<Scholarship> handle(GetScholarshipsByCompanyNameQuery query);
    Optional<Scholarship> handle(GetScholarshipByIdQuery query);
}
