package com.scholr.scholr_paltform.applications.domain.model.entities;

import com.scholr.scholr_paltform.applications.domain.model.commands.CreatePostulanteCommand;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.CentroEstudios;
import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Contacto;
import com.scholr.scholr_paltform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
//@Entity
//vamos a usar como value object
public class Postulante //extends AuditableModel
{
    /* porque se va a usar como value object
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */
    private String nombres;

    private String apellidos;

    private int dni;

    private Date fechaNacimiento;

    @Embedded
    private Contacto contacto;

    @Embedded
    private CentroEstudios centroEstudios;

    public Postulante() {}

    public Postulante(String nombres, String apellidos, int dni, Date fechaNacimiento, Contacto contacto, CentroEstudios centroEstudios)
    {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.contacto = contacto;
        this.centroEstudios = centroEstudios;
    }

    public void UpdateInformation(String nombres, String apellidos, int dni, Date fechaNacimiento, Contacto contacto, CentroEstudios centroEstudios)
    {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.contacto = contacto;
        this.centroEstudios = centroEstudios;
    }

    public Postulante(CreatePostulanteCommand command){
        this.nombres = command.nombres();
        this.apellidos = command.apellidos();
        this.dni = command.dni();
        this.fechaNacimiento = command.fechaNacimiento();
        this.contacto = command.contacto();
        this.centroEstudios = command.centroEstudios();
    }

}
