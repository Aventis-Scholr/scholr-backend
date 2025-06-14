package com.scholr.scholr_paltform.integration.test;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetAllApplicationsQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetApplicationByIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.*;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationQueryService;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoQueryService;
import com.scholr.scholr_paltform.applications.interfaces.rest.ApplicationsController;
import com.scholr.scholr_paltform.applications.interfaces.rest.DataApoderadosController;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.ApplicationResource;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.DataApoderadoResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ApplicationsControllerTest {

    @Mock
    private ApplicationQueryService applicationQueryService;

    @Mock
    private ApplicationsController applicationsController;

    //--------------------------------------------------------

    @Mock
    private DataApoderadoQueryService dataApoderadoQueryService;

    @Mock
    private DataApoderadosController dataApoderadosController;

    //--------------------------------------------------------

    @Test
    void testGetAllApplicationsSuccess() {
        // Arrange
        var application1 = new Application(1L, Status.PENDIENTE, TipoBeca.MERITO, new Postulante("Carlos", "Diaz", 456123789, new Date(),
                new Contacto("carlos@gmail.com", 456789123),
                new CentroEstudios("ColegioXYZ", "Privado", "Secundaria", "Lima", "Lima", "Lima")) );

        var application2 = new Application(2L, Status.PENDIENTE, TipoBeca.MERITO, new Postulante("Luis", "Noriega", 45612669, new Date(),
                new Contacto("luis@gmail.com", 466689123),
                new CentroEstudios("ColegioYYY", "Privado", "Secundaria", "Lima", "Lima", "Lima")));

        var applications = List.of(application1, application2);
        when(applicationQueryService.handle(any(GetAllApplicationsQuery.class))).thenReturn(applications);

        // Act
        ResponseEntity<List<ApplicationResource>> response = applicationsController.getAllApplications();

        // Assert
        assertNotNull(response);
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(applicationQueryService, times(1)).handle(any(GetAllApplicationsQuery.class));
    }

    @Test
    void testGetApplicationByIdSuccess() {
        // Arrange
        Long applicationId = 1L;
        when(applicationQueryService.handle(new GetApplicationByIdQuery(applicationId))).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ApplicationResource> response = applicationsController.getApplicationById(applicationId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(applicationQueryService, times(1)).handle(new GetApplicationByIdQuery(applicationId));
    }

    //--------------------------------------------------

    @Test
    void testGetDataApoderadoByApoderadoIdSuccess(){
        // Arrange
        Long apoderadoId = 1L;
        when(dataApoderadoQueryService.handle(new GetDataApoderadoByIdQuery(apoderadoId)))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<DataApoderadoResource> response = dataApoderadosController.getDataApoderadoById(apoderadoId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(dataApoderadoQueryService, times(1)).handle(new GetDataApoderadoByIdQuery(apoderadoId));
    }
}

