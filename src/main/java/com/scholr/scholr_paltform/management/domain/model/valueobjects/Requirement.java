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
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsMandatory() { return isMandatory; }
    public void setIsMandatory(Boolean isMandatory) { this.isMandatory = isMandatory; }

}
