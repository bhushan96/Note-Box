package com.project.notes.services;

import com.project.notes.dto.request.QueryNotesDTO;
import com.project.notes.dto.response.QueryNotesResponse;
import com.project.notes.encryption.EncryptNotes;
import com.project.notes.ety.NotesEncryptionDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Scope("prototype")
public class QueryNotesService extends AbstractNotesService<QueryNotesDTO, QueryNotesResponse> {
    @Override
    public QueryNotesResponse processRequest(QueryNotesDTO dto) {

        notes = notesRepo.findNotesById(dto.getNotesId());

        if (notes == null) {
            return new QueryNotesResponse(false, null);
        }

        QueryNotesResponse response = new QueryNotesResponse();

        if (notes.isEncrypted()) {
            if (!StringUtils.isEmpty(dto.getEncryptionKey())) {
                NotesEncryptionDetails encryptionDetails = encryptRepo.findEncryptDetailsById(notes.getNoteEncryptId());

                if(!encryptionDetails.getEncryptionKey().equals(dto.getEncryptionKey())){
                    throw new IllegalArgumentException("Password Does not match!");
                }
                String decryptedNotes = EncryptNotes.decrypt(notes.getNote(), encryptionDetails.getEncryptionKey());

                QueryNotesResponse.NoteDetails noteDetails = response.new NoteDetails();

                BeanUtils.copyProperties(notes, noteDetails);

                noteDetails.setNote(decryptedNotes);

                response.setEncrypted(true);
                response.setNotes(noteDetails);

            } else {
                response.setEncrypted(true);
                response.setNotes(null);
            }
        } else {
            QueryNotesResponse.NoteDetails noteDetails = response.new NoteDetails();

            BeanUtils.copyProperties(notes, noteDetails);

            response.setEncrypted(false);
            response.setNotes(noteDetails);
        }

        return response;


    }
}
