package com.scholr.scholr_paltform.applications.interfaces.rest;

import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoCommandService;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoQueryService;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.CreateDataApoderadoResource;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.DataApoderadoResource;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.CreateDataApoderadoCommandFromResourceAssembler;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.DataApoderadoResourceFromEntityAssembler;
import com.scholr.scholr_paltform.applications.interfaces.rest.transform.UpdateDataApoderadoCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/data-apoderado", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Data apoderado", description = "Data Apoderado Endpoints")
public class DataApoderadosController {
    private final DataApoderadoQueryService dataApoderadosQueryService;
    private final DataApoderadoCommandService dataApoderadosCommandService;

    public DataApoderadosController(DataApoderadoQueryService dataApoderadosQueryService, DataApoderadoCommandService dataApoderadosCommandService) {
        this.dataApoderadosQueryService = dataApoderadosQueryService;
        this.dataApoderadosCommandService = dataApoderadosCommandService;

    }


    //crear data apoderado
    @PostMapping
    public ResponseEntity<DataApoderadoResource> createDataApoderado(@RequestBody CreateDataApoderadoResource resource){
        var createDataApoderadoCommand = CreateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(resource);
        var dataApoderadoId = this.dataApoderadosCommandService.handle(createDataApoderadoCommand);

        if (dataApoderadoId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getDataApoderadoByIdQuery = new GetDataApoderadoByIdQuery(dataApoderadoId);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByIdQuery);

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return new ResponseEntity<>(dataApoderadoResource, HttpStatus.CREATED);
    }

    //obtener data apoderado por id
    @GetMapping("/{id}")
    public ResponseEntity<DataApoderadoResource> getDataApoderadoById(@PathVariable Long id){
        var getDataApoderadoByIdQuery = new GetDataApoderadoByIdQuery(id);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByIdQuery);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }


    //modificar data apoderado por id
    @PutMapping("/{id}")
    public ResponseEntity<DataApoderadoResource> updateDataApoderado(@PathVariable Long id, @RequestBody DataApoderadoResource resource){
        var updateDataApoderadoCommand = UpdateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalDataApoderado = this.dataApoderadosCommandService.handle(updateDataApoderadoCommand);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();
        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    //agregando el id del usuario---------

    //obtener data apoderado por id de usuario


}
