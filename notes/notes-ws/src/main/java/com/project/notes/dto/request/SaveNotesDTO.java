package com.project.notes.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaveNotesDTO extends CmnRequest{
    @NotEmpty
    private String note;
}
