package com.project.notes.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CmnRequest {
    @NotEmpty
    private String notesId;
}
