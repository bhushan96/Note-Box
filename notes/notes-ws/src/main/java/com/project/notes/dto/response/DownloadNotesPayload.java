package com.project.notes.dto.response;

import lombok.Data;

import java.io.ByteArrayInputStream;

@Data
public class DownloadNotesPayload {
    private String					fileName;
    private ByteArrayInputStream expPayload;
}
