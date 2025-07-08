package com.scholr.scholr_paltform.integration.test;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByApoderadoIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.*;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoCommandService;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoQueryService;
import com.scholr.scholr_paltform.applications.interfaces.rest.DataApoderadosController;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.CreateDataApoderadoResource;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.DataApoderadoResource;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.UpdateDataApoderadoResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataApoderadoControllerTest {

    @Mock
    private DataApoderadoCommandService dataApoderadoCommandService;

    @Mock
    private DataApoderadoQueryService dataApoderadoQueryService;

    @InjectMocks
    private DataApoderadosController dataApoderadosController;

    @Test
    @DisplayName("✅ Debería registrar un nuevo DataApoderado")
    void testCreateDataApoderado() {
        // Arrange
        Long apoderadoId = 1L;
        Long dataApoderadoId = 10L;

        CreateDataApoderadoResource resource = new CreateDataApoderadoResource(
                "Juan", "Pérez", 12345678, new Date(),
                new Contacto("juan@gmail.com", 999999999),
                new Domicilio("Calle Falsa", "Lima", "Lima", "Lima"),
                new CuentaBancaria("BCP", 123456789L, 987L),
                new InformacionLaboral("Empleado", "Ingeniero", "Desarrollo", "TechCorp", new BigDecimal("5000"))
        );

        DataApoderado expectedEntity = new DataApoderado(
                apoderadoId,
                resource.nombres(),
                resource.apellidos(),
                resource.dni(),
                resource.fechaNacimiento(),
                resource.contacto(),
                resource.domicilio(),
                resource.cuentaBancaria(),
                resource.informacionLaboral()
        );

        when(dataApoderadoCommandService.handle(any(CreateDataApoderadoCommand.class)))
                .thenReturn(dataApoderadoId);
        when(dataApoderadoQueryService.handle(any(GetDataApoderadoByIdQuery.class)))
                .thenReturn(Optional.of(expectedEntity));

        // Act
        ResponseEntity<DataApoderadoResource> response = dataApoderadosController.createDataApoderado(apoderadoId, resource);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Juan", response.getBody().nombres());
    }

    @Test
    @DisplayName("✅ Debería actualizar DataApoderado por ID")
    void testUpdateDataApoderadoById() {
        // Arrange
        Long dataApoderadoId = 10L;

        UpdateDataApoderadoResource updateResource = new UpdateDataApoderadoResource(
                "Ana", "García", 87654321, new Date(),
                new Contacto("ana@gmail.com", 998877665),
                new Domicilio("Av. Siempre Viva", "Arequipa", "Arequipa", "Arequipa"),
                new CuentaBancaria("BBVA", 987654321L, 321L),
                new InformacionLaboral("Independiente", "Consultora", "Proyectos", "Freelance", new BigDecimal("7500"))
        );

        DataApoderado updatedEntity = new DataApoderado(
                2L,
                updateResource.nombres(),
                updateResource.apellidos(),
                updateResource.dni(),
                updateResource.fechaNacimiento(),
                updateResource.contacto(),
                updateResource.domicilio(),
                updateResource.cuentaBancaria(),
                updateResource.informacionLaboral()
        );

        when(dataApoderadoCommandService.handle(any(UpdateDataApoderadoCommand.class)))
                .thenReturn(Optional.of(updatedEntity));

        // Act
        ResponseEntity<DataApoderadoResource> response = dataApoderadosController.updateDataApoderado(dataApoderadoId, updateResource);

        // Assert
        assertNotNull(response.getBody());
        assertEquals("Ana", response.getBody().nombres());
    }

    @Test
    @DisplayName("🚫 Debería devolver 404 si DataApoderado no existe")
    void testGetDataApoderadoByApoderadoIdNotFound() {
        Long apoderadoId = 999L;

        when(dataApoderadoQueryService.handle(any(GetDataApoderadoByApoderadoIdQuery.class)))
                .thenReturn(Optional.empty());

        ResponseEntity<DataApoderadoResource> response = dataApoderadosController.getDataApoderadoByApoderadoId(apoderadoId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
