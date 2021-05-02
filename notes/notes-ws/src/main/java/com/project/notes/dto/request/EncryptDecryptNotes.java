package com.project.notes.dto.request;

import com.project.notes.cmn.TextOperations;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class EncryptDecryptNotes extends CmnRequest{
    @NotNull
    private TextOperations textOperations;
    @NotEmpty
    private String encryptionKey;
}
