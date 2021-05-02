package com.project.notes.services;

import com.project.notes.cmn.TextOperations;
import com.project.notes.dto.request.EncryptDecryptNotes;
import com.project.notes.ety.NotesEncryptionDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Scope("prototype")
public class EncryptDecryptNotesService extends AbstractNotesService<EncryptDecryptNotes, Boolean> {

    @Override
    public Boolean processRequest(EncryptDecryptNotes dto) {

        if(dto.getEncryptionKey().length() < 5){
            throw new IllegalArgumentException("Password length must be minimum 5");
        }

        notes = notesRepo.findNotesById(dto.getNotesId());

        if (notes == null) {
            throw new IllegalArgumentException("No Notes Found!");
        }

        if (dto.getTextOperations().equals(TextOperations.ENCRYPT)) {

            if (notes.isEncrypted()) {
                throw new IllegalArgumentException("Note is already Encrypted!");
            }
            NotesEncryptionDetails encryptionDetails = NotesEncryptionDetails.of(UUID.randomUUID().toString(), notes.getId(), dto.getEncryptionKey());

            notes.encryptNotes(dto.getEncryptionKey(), encryptionDetails.getId());

            encryptRepo.saveEncryptDetails(encryptionDetails);

        }else if (dto.getTextOperations().equals(TextOperations.DECRYPT)) {
            if (!notes.isEncrypted()) {
                throw new IllegalArgumentException("Note is already Decrypted!");
            }
            NotesEncryptionDetails encryptionDetails = encryptRepo.findEncryptDetailsById(notes.getNoteEncryptId());

            if(!encryptionDetails.getEncryptionKey().equals(dto.getEncryptionKey())){
                throw new IllegalArgumentException("Wrong Password!");
            }
            notes.decryptNotes(dto.getEncryptionKey());

            //remove the mapping from encryption details
            encryptRepo.removeEncryptDetailsById(encryptionDetails.getId());
        }
        else {
            throw new IllegalArgumentException();
        }

        notesRepo.saveNotes(notes);

        return Boolean.TRUE;
    }
}
