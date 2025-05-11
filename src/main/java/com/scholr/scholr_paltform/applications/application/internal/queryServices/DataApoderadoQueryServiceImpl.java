package com.scholr.scholr_paltform.applications.application.internal.queryServices;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoQueryService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.DataApoderadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataApoderadoQueryServiceImpl implements DataApoderadoQueryService {
    private final DataApoderadoRepository dataApoderadoQueryRepository;

    public DataApoderadoQueryServiceImpl(DataApoderadoRepository dataApoderadoQueryRepository) {
        this.dataApoderadoQueryRepository = dataApoderadoQueryRepository;
    }

    @Override
    public Optional<DataApoderado> handle(GetDataApoderadoByIdQuery query) {
        return this.dataApoderadoQueryRepository.findById(query.dataApoderadoId());
    }

    @Override
    public Optional<DataApoderado> handle(GetDataApoderadoByApoderadoIdQuery query) {
        return this.dataApoderadoQueryRepository.findByApoderadoId(query.apoderadoId());
    }

    @Override
    public Optional<DataApoderado> handle(GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery query) {
        return this.dataApoderadoQueryRepository.findByApoderadoIdAndId(query.apoderadoId(), query.dataApoderadoId());
    }
}
