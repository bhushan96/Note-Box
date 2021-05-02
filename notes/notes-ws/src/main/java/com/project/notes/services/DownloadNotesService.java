package com.project.notes.services;

import com.project.notes.dto.request.DownloadNotesDTO;
import com.project.notes.dto.response.DownloadNotesPayload;
import com.project.notes.encryption.EncryptNotes;
import com.project.notes.ety.NotesEncryptionDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Scope("prototype")
public class DownloadNotesService extends AbstractNotesService<DownloadNotesDTO, DownloadNotesPayload> {


    @Override
    public DownloadNotesPayload processRequest(DownloadNotesDTO dto) {
        notes = notesRepo.findNotesById(dto.getNotesId());

        if (notes == null) {
           throw new IllegalArgumentException("No Notes Found for Downloading!");
        }


        String note = null;
        if (notes.isEncrypted()) {
            NotesEncryptionDetails encryptionDetails = encryptRepo.findEncryptDetailsById(notes.getNoteEncryptId());
            note = EncryptNotes.decrypt(notes.getNote(), encryptionDetails.getEncryptionKey());

        } else {
            note = notes.getNote();
        }

        ByteArrayInputStream byIs = new ByteArrayInputStream(note.getBytes());

        DownloadNotesPayload payload = new DownloadNotesPayload();
        payload.setFileName(notes.getId() + notes.getFileExtension());
        payload.setExpPayload(byIs);

        return payload;
    }
}
