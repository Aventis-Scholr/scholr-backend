package com.scholr.scholr_paltform.iam.domain.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorValidationService {
    private final EntityManager entityManager;

    public ColaboradorValidationService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean validarColaborador(String compania, String dni, String codColaborador) {
        String tableName = compania.toLowerCase() + "_colaboradores";

        String sql = String.format("SELECT COUNT(*) FROM %s WHERE dni = :dni AND codigo_colaborador = :codigo", tableName);

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("dni", dni);
        query.setParameter("codigo", codColaborador);

        Number result = (Number) query.getSingleResult();
        return result.intValue() > 0;
    }
}
