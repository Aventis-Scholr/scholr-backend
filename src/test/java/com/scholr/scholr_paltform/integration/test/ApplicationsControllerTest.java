package com.scholr.scholr_paltform.integration.test;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.Application;
import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.commands.DeleteApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateReporteApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateStatusApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetAllApplicationsQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetApplicationByIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetDataApoderadoByIdQuery;
import com.scholr.scholr_paltform.applications.domain.model.queries.GetPendingApplicationsByApoderadoId;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.*;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationCommandService;
import com.scholr.scholr_paltform.applications.domain.services.ApplicationQueryService;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoQueryService;
import com.scholr.scholr_paltform.applications.interfaces.rest.ApplicationsController;
import com.scholr.scholr_paltform.applications.interfaces.rest.DataApoderadosController;
import com.scholr.scholr_paltform.applications.interfaces.rest.resources.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ApplicationsControllerTest {

    @Mock
    private ApplicationQueryService applicationQueryService;

    @InjectMocks
    private ApplicationsController applicationsController;

    //--------------------------------------------------------

    @Mock
    private DataApoderadoQueryService dataApoderadoQueryService;

    @InjectMocks
    private DataApoderadosController dataApoderadosController;

    //--------------------------------------------------------

    @Mock
    private ApplicationCommandService applicationCommandService;

    @Test
    void testGetAllApplicationsSuccess() {
        // Arrange
        var application1 = new Application(1L, Status.PENDIENTE, 1L, new Postulante("Carlos", "Diaz", 456123789, new Date(),
                new Contacto("carlos@gmail.com", 456789123),
                new CentroEstudios("ColegioXYZ", "Privado", "Secundaria", "Lima", "Lima", "Lima")) );

        var application2 = new Application(2L, Status.PENDIENTE, 2L, new Postulante("Luis", "Noriega", 45612669, new Date(),
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

    @Test
    void testUpdateApplicationByIdSuccess() {
        // Arrange
        Long applicationId = 1L;

        Postulante postulante = new Postulante(
                "Carlos", "Diaz", 456123789, new Date(),
                new Contacto("carlos@gmail.com", 456789123),
                new CentroEstudios("ColegioXYZ", "Privado", "Secundaria", "Lima", "Lima", "Lima")
        );

        UpdateApplicationResource resource = new UpdateApplicationResource(
                Status.APROBADO,
                TipoBeca.MERITO,
                postulante
        );

        Application updatedApplication = new Application(
                applicationId,
                resource.status(),
                resource.tipoBeca(),
                resource.postulante()
        );

        when(applicationCommandService.handle(any(UpdateApplicationCommand.class)))
                .thenReturn(Optional.of(updatedApplication));

        // Act
        ResponseEntity<ApplicationResource> response =
                applicationsController.updateApplication(applicationId, resource);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        verify(applicationCommandService, times(1)).handle(any(UpdateApplicationCommand.class));
    }

    @Test
    void testDeleteApplicationByIdSuccess() {
        // Arrange
        Long applicationId = 1L;
        doNothing().when(applicationCommandService).handle(any(DeleteApplicationCommand.class));

        // Act
        ResponseEntity<ApplicationResource> response = applicationsController.deleteApplication(applicationId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response); // opcional
        verify(applicationCommandService, times(1)).handle(any(DeleteApplicationCommand.class));
    }

    @Test
    void testUpdateApplicationStatusSuccess() {
        // Arrange
        Long applicationId = 1L;
        Status nuevoStatus = Status.APROBADO;
        var resource = new UpdateStatusApplicationResource(nuevoStatus);

        var updatedApplication = new Application(applicationId, nuevoStatus, TipoBeca.MERITO, null);

        when(applicationCommandService.handle(any(UpdateStatusApplicationCommand.class)))
                .thenReturn(Optional.of(updatedApplication));

        // Act
        ResponseEntity<ApplicationResource> response = applicationsController.updateStatus(applicationId, resource);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(applicationCommandService, times(1)).handle(any(UpdateStatusApplicationCommand.class));
    }

    @Test
    void testUpdateApplicationReporteSuccess() {
        // Arrange
        Long applicationId = 1L;
        String nuevoReporte = "NuevoReporteFinal";
        UpdateReporteApplicationResource resource = new UpdateReporteApplicationResource(nuevoReporte);

        Application updatedApplication = new Application(applicationId, Status.PENDIENTE, TipoBeca.MERITO, null);
        when(applicationCommandService.handle(any(UpdateReporteApplicationCommand.class)))
                .thenReturn(Optional.of(updatedApplication));

        // Act
        ResponseEntity<ApplicationResource> response = applicationsController.updateReporte(applicationId, resource);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(applicationCommandService, times(1)).handle(any(UpdateReporteApplicationCommand.class));
    }


    @Test
    void testUploadFileToApplicationSuccess() {
        // Arrange
        Long applicationId = 1L;

        MultipartFile mockDni = mock(MultipartFile.class);
        MultipartFile mockLibreta = mock(MultipartFile.class);
        MultipartFile mockConstancia = mock(MultipartFile.class);
        MultipartFile mockApoderadoDni = mock(MultipartFile.class);
        MultipartFile mockApoderadoDeclaracion = mock(MultipartFile.class);

        String fileUrl = "http://dummy.url/file.pdf";

        // Mock de subida de archivos
        when(applicationCommandService.handle(mockDni)).thenReturn(fileUrl);
        when(applicationCommandService.handle(mockLibreta)).thenReturn(fileUrl);
        when(applicationCommandService.handle(mockConstancia)).thenReturn(fileUrl);
        when(applicationCommandService.handle(mockApoderadoDni)).thenReturn(fileUrl);
        when(applicationCommandService.handle(mockApoderadoDeclaracion)).thenReturn(fileUrl);

        // Mock de búsqueda de aplicación
        Application mockApplication = new Application(applicationId, Status.PENDIENTE, TipoBeca.MERITO, null);
        when(applicationQueryService.handle(any(GetApplicationByIdQuery.class)))
                .thenReturn(Optional.of(mockApplication));

        // Mock de persistencia del nuevo estado
        doReturn(1L).when(applicationCommandService).handle(
                eq(applicationId),
                eq(fileUrl),
                eq(fileUrl),
                eq(fileUrl),
                eq(fileUrl),
                eq(fileUrl)
        );

        // Act
        ResponseEntity<?> response = applicationsController.subirDni(
                applicationId,
                mockDni,
                mockLibreta,
                mockConstancia,
                mockApoderadoDni,
                mockApoderadoDeclaracion
        );

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals(fileUrl, body.get("dni"));
        assertEquals(fileUrl, body.get("libreta_notas"));
        assertEquals(fileUrl, body.get("const_logro_aprendizaje"));

        verify(applicationCommandService, times(5)).handle(any(MultipartFile.class));
        verify(applicationQueryService, times(1)).handle(any(GetApplicationByIdQuery.class));
        verify(applicationCommandService, times(1)).handle(
                eq(applicationId),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString()
        );
    }

    @Test
    void testGetPendingApplicationsByApoderadoIdSuccess() {
        // Arrange
        Long apoderadoId = 1L;
        Application pendingApp = new Application(3L, Status.PENDIENTE, TipoBeca.MERITO, null);

        when(applicationQueryService.handle(any(GetPendingApplicationsByApoderadoId.class)))
                .thenReturn(List.of(pendingApp));

        // Act
        ResponseEntity<List<ApplicationResource>> response =
                applicationsController.getPendingApplicationsByApoderadoId(apoderadoId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        verify(applicationQueryService, times(1))
                .handle(new GetPendingApplicationsByApoderadoId(apoderadoId));
    }
}

