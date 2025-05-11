package com.scholr.scholr_paltform.applications.domain.services;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;

import java.util.Optional;

public interface DataApoderadoQueryService {
    Optional<DataApoderado> handle(GetDataApoderadoByIdQuery query);
    Optional<DataApoderado> handle(GetDataApoderadoByApoderadoIdQuery query);
    Optional<DataApoderado> handle(GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery query);
}
