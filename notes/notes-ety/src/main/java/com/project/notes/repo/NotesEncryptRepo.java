package com.project.notes.repo;

import com.project.notes.ety.NotesEncryptionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class NotesEncryptRepo {

    @Autowired
    private MongoRepo mongoRepo;

    public NotesEncryptionDetails findEncryptDetailsById(String encryptionId) {
        Criteria criteria = Criteria.where("_id").is(encryptionId);

        return mongoRepo.qryOneByCriteria(criteria, NotesEncryptionDetails.class);
    }

    public void saveEncryptDetails(NotesEncryptionDetails notesEncrypt) {
        mongoRepo.save(notesEncrypt);
    }

    public long removeEncryptDetailsById(String encryptionId) {
        Criteria criteria = Criteria.where("_id").is(encryptionId);

        return mongoRepo.removeByCriteria(criteria, NotesEncryptionDetails.class);
    }
}
