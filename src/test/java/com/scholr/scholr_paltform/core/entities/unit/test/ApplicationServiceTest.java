package com.scholr.scholr_paltform.core.entities.unit.test;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreateApplicationCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.entities.Postulante;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

public class ApplicationServiceTest {
    @Test
    void testCreateDataApoderado() {

        //Arrange
        Long apoderadoId = 1L;
        String nombres= "Juan";
        String apellidos = "Perez";
        int dni = 12345678;
        Date fechaNacimiento = new Date();
        Contacto contacto = new Contacto("juan@gmail.com", 965588245);
        Domicilio domicilio = new Domicilio("Aramburu", "Lima", "Lima", "Lima");
        CuentaBancaria cuentaBancaria = new CuentaBancaria("Banco A", 4568756541L, 456L);
        InformacionLaboral informacionLaboral = new InformacionLaboral("Trabajador", "Gerente", "Principal", "XY", new BigDecimal("8000.00"));

        //Act
        CreateDataApoderadoCommand createDataApoderadoCommand = new CreateDataApoderadoCommand(
                apoderadoId,
                nombres,
                apellidos,
                dni,
                fechaNacimiento,
                contacto,
                domicilio,
                cuentaBancaria,
                informacionLaboral
        );

        //Assert
        assertEquals(apoderadoId, createDataApoderadoCommand.apoderadoId());
        assertEquals(nombres, createDataApoderadoCommand.nombres());
        assertEquals(apellidos, createDataApoderadoCommand.apellidos());
        assertEquals(dni, createDataApoderadoCommand.dni());
        assertEquals(fechaNacimiento, createDataApoderadoCommand.fechaNacimiento());
        assertEquals(contacto, createDataApoderadoCommand.contacto());
        assertEquals(domicilio, createDataApoderadoCommand.domicilio());
        assertEquals(cuentaBancaria, createDataApoderadoCommand.cuentaBancaria());
        assertEquals(informacionLaboral, createDataApoderadoCommand.informacionLaboral());

    }


    @Test
    void testCreateApplication(){
        //Arrange
        Long idApoderado = 1L;
        Status status = Status.PENDIENTE;
        TipoBeca tipoBeca = TipoBeca.MERITO;
        Postulante postulante = new Postulante("Carlos", "Diaz", 456123789, new Date(),
                new Contacto("carlos@gmail.com", 456789123),
                new CentroEstudios("ColegioXYZ", "Privado", "Secundaria", "Lima", "Lima", "Lima"));

        //Act
        CreateApplicationCommand createApplicationCommand = new CreateApplicationCommand(
                idApoderado,
                status,
                tipoBeca,
                postulante
        );

        //Assert
        assertEquals(idApoderado , createApplicationCommand.idApoderado());
        assertEquals(status, createApplicationCommand.status());
        assertEquals(tipoBeca, createApplicationCommand.tipoBeca());
        assertEquals(postulante, createApplicationCommand.postulante());

    }


}
