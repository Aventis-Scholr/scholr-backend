package com.scholr.scholr_paltform.applications.application.internal.commandServices;

import com.scholr.scholr_paltform.applications.domain.model.aggregates.DataApoderado;
import com.scholr.scholr_paltform.applications.domain.model.commands.CreateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.model.commands.UpdateDataApoderadoCommand;
import com.scholr.scholr_paltform.applications.domain.services.DataApoderadoCommandService;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.ApplicationRepository;
import com.scholr.scholr_paltform.applications.infrastructure.persistence.jpa.repositories.DataApoderadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataApoderadoCommandServiceImpl implements DataApoderadoCommandService {
    private final DataApoderadoRepository dataApoderadoRepository;

    public DataApoderadoCommandServiceImpl(DataApoderadoRepository dataApoderadoRepository) {
        this.dataApoderadoRepository = dataApoderadoRepository;
    }

    @Override
    public Long handle(CreateDataApoderadoCommand command){
        var dataApoderado = new DataApoderado(command);
        try{
            this.dataApoderadoRepository.save(dataApoderado);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while saving dataApoderado: " + e.getMessage());
        }
        return dataApoderado.getId();
    }

    @Override
    public Optional<DataApoderado> handle(UpdateDataApoderadoCommand command){
        var dataApoderadoId = command.dataApoderadoId();
        if(!this.dataApoderadoRepository.existsById(dataApoderadoId)){
            throw new IllegalArgumentException("DataApoderado does not exist");
        }

        var dataApoderadoToUpdate = this.dataApoderadoRepository.findById(dataApoderadoId).get();
        dataApoderadoToUpdate.UpdateDataApoderado(command.nombres(),
                command.apellidos(),
                command.dni(),
                command.fechaNacimiento(),
                command.contacto(),
                command.domicilio(),
                command.cuentaBancaria(),
                command.informacionLaboral());

        try{
            var updatedDataApoderado = this.dataApoderadoRepository.save(dataApoderadoToUpdate);
            return Optional.of(updatedDataApoderado);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating dataApoderado: " + e.getMessage());
        }
    }
}
