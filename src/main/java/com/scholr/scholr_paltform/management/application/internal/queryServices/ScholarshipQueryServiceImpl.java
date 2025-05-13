package com.scholr.scholr_paltform.management.application.internal.queryServices;

import com.scholr.scholr_paltform.management.domain.model.aggregates.Scholarship;
import com.scholr.scholr_paltform.management.domain.model.queries.GetAllScholarshipsQuery;
import com.scholr.scholr_paltform.management.domain.model.queries.GetScholarshipByIdQuery;
import com.scholr.scholr_paltform.management.domain.model.queries.GetScholarshipsByCompanyNameQuery;
import com.scholr.scholr_paltform.management.domain.services.ScholarshipQueryService;
import com.scholr.scholr_paltform.management.infrastructure.persistence.jpa.repositories.ScholarshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipQueryServiceImpl implements ScholarshipQueryService {
    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipQueryServiceImpl(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    @Override
    public List<Scholarship> handle(GetAllScholarshipsQuery query) {
        return this.scholarshipRepository.findAll();
    }

    @Override
    public List<Scholarship> handle(GetScholarshipsByCompanyNameQuery query) {
        return this.scholarshipRepository.findByCompanyName(query.companyName());
    }

    @Override
    public Optional<Scholarship> handle(GetScholarshipByIdQuery query) {
        return this.scholarshipRepository.findById(query.id());
    }
}
