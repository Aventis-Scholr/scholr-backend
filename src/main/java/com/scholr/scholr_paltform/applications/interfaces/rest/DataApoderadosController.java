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
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.UpdateDataApoderadoResource;
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
    @PostMapping("/{apoderadoId}")
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
    @PutMapping("/put/{id}")
    public ResponseEntity<DataApoderadoResource> updateDataApoderado(@PathVariable Long id, @RequestBody UpdateDataApoderadoResource resource){
        var updateDataApoderadoCommand = UpdateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalDataApoderado = this.dataApoderadosCommandService.handle(updateDataApoderadoCommand);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();
        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    //agregando el id del usuario (apoderado) ---------

    //obtener data apoderado por id de usuario
    @GetMapping("/{apoderadoId}/{id}")
    public ResponseEntity<DataApoderadoResource> getDataApoderadoApoderadoIdAndId(@PathVariable Long apoderadoId, @PathVariable Long id){
        var getDataApoderadoByApoderadoIdAndId = new GetDataApoderadoByApoderadoIdAndDataApoderadoIdQuery(apoderadoId, id);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByApoderadoIdAndId);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    //hacer update de data apoderado por id de usuario
    @PutMapping("/put/apoderado/{apoderadoId}")
    public ResponseEntity<DataApoderadoResource> updateDataApoderadoByApoderadoId(@PathVariable Long apoderadoId, @RequestBody UpdateDataApoderadoResource resource){
        var updateDataApoderadoCommand = UpdateDataApoderadoCommandFromResourceAssembler.toCommandFromResource(apoderadoId, resource);
        var optionalDataApoderado = this.dataApoderadosCommandService.handle(updateDataApoderadoCommand);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

    //get data apoderado by apoderado id
    @GetMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<DataApoderadoResource> getDataApoderadoByApoderadoId(@PathVariable Long apoderadoId){
        var getDataApoderadoByApoderadoIdQuery = new GetDataApoderadoByApoderadoIdQuery(apoderadoId);
        var optionalDataApoderado = this.dataApoderadosQueryService.handle(getDataApoderadoByApoderadoIdQuery);

        if (optionalDataApoderado.isEmpty())
            return ResponseEntity.notFound().build();

        var dataApoderadoResource = DataApoderadoResourceFromEntityAssembler.toResourceFromEntity(optionalDataApoderado.get());
        return ResponseEntity.ok(dataApoderadoResource);
    }

}
