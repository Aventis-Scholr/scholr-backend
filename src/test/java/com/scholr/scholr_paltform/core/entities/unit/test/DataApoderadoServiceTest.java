package com.scholr.scholr_paltform.core.entities.unit.test;

import com.scholr.scholr_paltform.applications.application.internal.commandServices.DataApoderadoCommandServiceImpl;
import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.*;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoCommandService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.DataApoderadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DataApoderadoServiceTest {

    private DataApoderadoRepository repository;
    private DataApoderadoCommandService service;

    @BeforeEach
    void setUp() {
        repository = mock(DataApoderadoRepository.class);
        service = new DataApoderadoCommandServiceImpl(repository);
    }

    @Test
    void testCreateDataApoderadoSuccess() {
        // Arrange
        CreateDataApoderadoCommand command = new CreateDataApoderadoCommand(
                1L,
                "Luis",
                "Ramirez",
                12345678,
                new Date(),
                new Contacto("luis@gmail.com", 999888777),
                new Domicilio("Jr. Lima", "Lima", "Lima", "Lima"),
                new CuentaBancaria("Interbank", 1234567890L, 123L),
                new InformacionLaboral("Empleado", "Ingeniero", "Desarrollo", "EmpresaX", new BigDecimal("4500"))
        );

        DataApoderado apoderado = new DataApoderado(
                command.apoderadoId(),
                command.nombres(),
                command.apellidos(),
                command.dni(),
                command.fechaNacimiento(),
                command.contacto(),
                command.domicilio(),
                command.cuentaBancaria(),
                command.informacionLaboral()
        );

        ReflectionTestUtils.setField(apoderado, "id", 10L);

        when(repository.save(any(DataApoderado.class))).thenAnswer(invocation -> {
            DataApoderado saved = invocation.getArgument(0);
            ReflectionTestUtils.setField(saved, "id", 10L);
            return saved;
        });
        // Act
        Long result = service.handle(command);

        // Assert
        assertNotNull(result); // <- esto fallaba antes porque el mock devolvía null
        assertEquals(10L, result);
        verify(repository, times(1)).save(any(DataApoderado.class));
    }

    @Test
    void testUpdateDataApoderadoSuccess() {
        // Arrange
        Long dataApoderadoId = 100L;

        UpdateDataApoderadoCommand command = new UpdateDataApoderadoCommand(
                dataApoderadoId,
                "Ana",
                "Lopez",
                87654321,
                new Date(),
                new Contacto("ana@gmail.com", 987654321),
                new Domicilio("Av. Peru", "Cusco", "Cusco", "Cusco"),
                new CuentaBancaria("BCP", 1122334455L, 456L),
                new InformacionLaboral("Freelancer", "Consultora", "TI", "Freelance", new BigDecimal("6000"))
        );

        // Simulamos una entidad existente
        DataApoderado existing = new DataApoderado(
                1L,
                "Luis",
                "Ramirez",
                12345678,
                new Date(),
                new Contacto("luis@gmail.com", 999888777),
                new Domicilio("Jr. Lima", "Lima", "Lima", "Lima"),
                new CuentaBancaria("Interbank", 1234567890L, 123L),
                new InformacionLaboral("Empleado", "Ingeniero", "Desarrollo", "EmpresaX", new BigDecimal("4500"))
        );
        // Simula que el ID existe y devuelve el existente
        when(repository.existsById(dataApoderadoId)).thenReturn(true);
        when(repository.findById(dataApoderadoId)).thenReturn(Optional.of(existing));
        when(repository.save(any(DataApoderado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<DataApoderado> result = service.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Ana", result.get().getNombres());
        assertEquals("Lopez", result.get().getApellidos());
        assertEquals(87654321, result.get().getDni());

        verify(repository, times(1)).existsById(dataApoderadoId);
        verify(repository, times(1)).findById(dataApoderadoId);
        verify(repository, times(1)).save(any(DataApoderado.class));
    }
}
