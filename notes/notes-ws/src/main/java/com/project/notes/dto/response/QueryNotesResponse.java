package com.project.notes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryNotesResponse {

    private boolean encrypted;
    private NoteDetails notes;

    @Data
    public class NoteDetails{
        private String id;
        private String note;
        private Instant creationDate;
        private int wordCount;
        private int characterCount;
    }
}


