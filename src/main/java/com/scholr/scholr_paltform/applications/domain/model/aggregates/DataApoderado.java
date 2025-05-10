package com.scholr.scholr_paltform.applications.domain.model.aggregates;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CuentaBancaria;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Domicilio;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.InformacionLaboral;
import com.scholr.scholr_paltform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class DataApoderado extends AuditableAbstractAggregateRoot<DataApoderado> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //id del apoderado
    private Long apoderadoId;

    private String nombres;

    private String apellidos;

    private int dni;

    private Date fechaNacimiento;

    @Embedded
    private Contacto contacto;

    @Embedded
    private Domicilio domicilio;

    @Embedded
    private CuentaBancaria cuentaBancaria;

    @Embedded
    private InformacionLaboral informacionLaboral;

    public DataApoderado() {}

    public DataApoderado(CreateDataApoderadoCommand command) {
        this.apoderadoId = command.apoderadoId();
        this.nombres = command.nombres();
        this.apellidos = command.apellidos();
        this.dni = command.dni();
        this.fechaNacimiento = command.fechaNacimiento();
        this.contacto = command.contacto();
        this.domicilio = command.domicilio();
        this.cuentaBancaria = command.cuentaBancaria();
        this.informacionLaboral = command.informacionLaboral();
    }

    public DataApoderado(Long apoderadoId, String nombres, String apellidos, int dni, Date fechaNacimiento,
                        Contacto contacto, Domicilio domicilio, CuentaBancaria cuentaBancaria,
                        InformacionLaboral informacionLaboral) {
        this.apoderadoId = apoderadoId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.cuentaBancaria = cuentaBancaria;
        this.informacionLaboral = informacionLaboral;
    }

    public void UpdateDataApoderado(String nombres, String apellidos, int dni, Date fechaNacimiento,
                                    Contacto contacto, Domicilio domicilio, CuentaBancaria cuentaBancaria,
                                    InformacionLaboral informacionLaboral) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.cuentaBancaria = cuentaBancaria;
        this.informacionLaboral = informacionLaboral;
    }

}
