package com.project.notes.dto.request;

import lombok.Data;

@Data
public class QueryNotesDTO extends CmnRequest{

    private String encryptionKey;
}
