package com.project.notes.ety;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("notes_encrypt")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class NotesEncryptionDetails implements Serializable {

    private static final long serialVersionUID = 107338363880130627L;
    @Id
    private String id;
    private String notesId;
    private String encryptionKey;

}
