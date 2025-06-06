package com.scholr.scholr_paltform.applications.interfaces.rest;

import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CuentaBancaria;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Domicilio;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.InformacionLaboral;
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

import java.util.Date;

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
    @PostMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<DataApoderadoResource> createDataApoderado(@PathVariable Long apoderadoId,@RequestBody CreateDataApoderadoResource resource){
        var createDataApoderadoCommand = CreateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(apoderadoId, resource);
                var dataApoderadoId = this.dataApoderadosCommandService.handle(createDataApoderadoCommand);

        if (dataApoderadoId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getDataApoderadoByIdQuery = new GetDataApoderadoByIdQuery(dataApoderadoId);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByIdQuery);

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return new ResponseEntity<>(dataApoderadoResource, HttpStatus.CREATED);
    }

    // OBTENER DataApoderado por dataApoderadoId
    @GetMapping("/id/{id}")
    public ResponseEntity<DataApoderadoResource> getDataApoderadoById(@PathVariable Long id){
        var getDataApoderadoByIdQuery = new GetDataApoderadoByIdQuery(id);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByIdQuery);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    // ACTUALIZAR DataApoderado por dataApoderadoId
    @PutMapping("/id/{id}")
    public ResponseEntity<DataApoderadoResource> updateDataApoderado(@PathVariable Long id, @RequestBody DataApoderadoResource resource){
        var updateDataApoderadoCommand = UpdateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalDataApoderado = this.dataApoderadosCommandService.handle(updateDataApoderadoCommand);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();
        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    //agregando el id del usuario (apoderado) ---------

    // ACTUALIZAR por apoderadoId
    @PutMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<DataApoderadoResource> updateDataApoderadoByApoderadoId(@PathVariable Long apoderadoId, @RequestBody DataApoderadoResource resource){
        var updateDataApoderadoCommand = UpdateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(apoderadoId, resource);
        var optionalDataApoderado = this.dataApoderadosCommandService.handle(updateDataApoderadoCommand);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    // OBTENER DataApoderado por apoderadoId
    @GetMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<DataApoderadoResource> getDataApoderadoByApoderadoId(@PathVariable Long apoderadoId){
        var getDataApoderadoByApoderadoIdQuery = new GetDataApoderadoByApoderadoIdQuery(apoderadoId);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByApoderadoIdQuery);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }


    // OBTENER DataApoderado por apoderadoId + dataApoderadoId (si necesitas este caso específico)
    @GetMapping("/apoderado/{apoderadoId}/data/{id}")
    public ResponseEntity<DataApoderadoResource> getDataApoderadoApoderadoIdAndId(@PathVariable Long apoderadoId, @PathVariable Long id){
        var getDataApoderadoByApoderadoIdAndId = new GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery(apoderadoId, id);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByApoderadoIdAndId);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }
}
