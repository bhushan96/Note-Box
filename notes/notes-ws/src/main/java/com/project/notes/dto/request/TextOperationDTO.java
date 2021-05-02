package com.project.notes.dto.request;

import com.project.notes.cmn.TextOperations;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class TextOperationDTO {
    @NotNull
    private TextOperations textOperation;
    @NotEmpty
    private String data;
    private String encryptionPassword;
    @PositiveOrZero
    private int startIdx;
    @PositiveOrZero
    private int endIdx;
}
