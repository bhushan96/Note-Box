package com.project.notes.ety;


import com.project.notes.encryption.EncryptNotes;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Document("notes_box")
public class Notes implements Serializable {
    private static final long serialVersionUID = 7948377445258415020L;
    @Id
    private String id;
    private String note;
    private boolean encrypted;
    private Instant creationDate;
    private String fileExtension;
    private int wordCount;
    private int characterCount;
    private String noteEncryptId;
    private boolean createdFromFile;

    private Notes() {

    }

    private Notes(String notesId) {
        this.id = notesId;
    }

    public static Notes of(String notesId) {
        Notes notes = new Notes(notesId);

        notes.creationDate = Instant.now();

        return notes;
    }

    public Notes addNote(String note) {

        this.note = note;
        this.characterCount = note.length();
        this.wordCount = note.split(" ").length;
        this.createdFromFile = false;
        this.fileExtension = ".txt";

        return this;

    }

    public Notes encryptNotes(String encryptionKey, String noteEncryptId) {

        this.note = EncryptNotes.encrypt(this.note, encryptionKey);
        this.encrypted = true;
        this.noteEncryptId = noteEncryptId;

        return this;
    }

    public Notes decryptNotes(String encryptionKey) {

        this.note = EncryptNotes.decrypt(this.note, encryptionKey);
        this.encrypted = false;
        this.noteEncryptId = null;

        return this;
    }

}