package com.project.notes.services;

import com.project.notes.ety.Notes;
import com.project.notes.repo.NotesEncryptRepo;
import com.project.notes.repo.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNotesService<T,R> {

    @Autowired
    protected NotesRepo notesRepo;

    @Autowired
    protected NotesEncryptRepo encryptRepo;

    protected Notes notes;

    public abstract R processRequest(T dto);
}
