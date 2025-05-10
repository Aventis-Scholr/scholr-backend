package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoId;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdAndDataApoderadoId;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoById;

import java.util.List;
import java.util.Optional;

public interface DataApoderadoQueryService {
    Optional<DataApoderado> handle(GetDataApoderadoById query);
    Optional<DataApoderado> handle(GetDataApoderadoByApoderadoId query);
    Optional<DataApoderado> handle(GetDataApoderadoByApoderadoIdAndDataApoderadoId query);
}
