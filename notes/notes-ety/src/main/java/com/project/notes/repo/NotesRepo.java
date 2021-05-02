package com.project.notes.repo;

import com.project.notes.ety.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class NotesRepo {

    @Autowired
    private MongoRepo mongoRepo;

    public Notes findNotesById(String notesId) {
        Criteria criteria = Criteria.where("_id").is(notesId);

        return mongoRepo.qryOneByCriteria(criteria, Notes.class);
    }

    public void saveNotes(Notes notes) {
        mongoRepo.save(notes);
    }
}
