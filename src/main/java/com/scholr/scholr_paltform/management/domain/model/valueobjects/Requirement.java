package com.scholr.scholr_paltform.management.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Requirement {
    private String name;
    private String description;
    private Boolean isMandatory;

    public Requirement() {}

    public Requirement(String name, String description, Boolean isMandatory) {
        this.name = name;
        this.description = description;
        this.isMandatory = isMandatory;
    }
}
