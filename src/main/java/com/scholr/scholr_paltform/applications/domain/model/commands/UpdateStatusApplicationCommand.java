package com.scholr.scholr_paltform.applications.domain.model.commands;

import com.scholr.scholr_paltform.applications.domain.model.valueobjects.Status;

public record UpdateStatusApplicationCommand(Long id,Status status) {
}
