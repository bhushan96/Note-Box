package com.project.notes.services;

import com.project.notes.dto.request.SaveNotesDTO;
import com.project.notes.ety.Notes;
import com.project.notes.ety.NotesEncryptionDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class SaveOrUpdateNotesService extends AbstractNotesService<SaveNotesDTO, Boolean> {


    @Override
    public Boolean processRequest(SaveNotesDTO dto) {

        notes = notesRepo.findNotesById(dto.getNotesId());

        if (notes == null) {
            notes = createNewNotes(dto);
        } else {
            notes = updateNotes(dto, notes);
        }

        notesRepo.saveNotes(notes);

        return Boolean.TRUE;
    }

    private Notes createNewNotes(SaveNotesDTO dto) {
        return Notes.of(dto.getNotesId()).addNote(dto.getNote());
    }

    private Notes updateNotes(SaveNotesDTO dto, Notes notes) {

        if (notes.isEncrypted()) {
            NotesEncryptionDetails notesEncryptionDetails = encryptRepo.findEncryptDetailsById(notes.getNoteEncryptId());

            notes.addNote(dto.getNote()).encryptNotes(notesEncryptionDetails.getEncryptionKey(),notesEncryptionDetails.getId());
        } else {
            notes.addNote(dto.getNote());
        }

        return notes;

    }
}
