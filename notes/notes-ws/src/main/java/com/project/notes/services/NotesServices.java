package com.project.notes.services;

import com.project.notes.dto.request.*;
import com.project.notes.dto.response.DownloadNotesPayload;
import com.project.notes.dto.response.QueryNotesResponse;
import com.project.notes.dto.response.TextOperationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NotesServices {

    @Autowired
    private DownloadNotesService downloadNotesService;

    @Autowired
    private SaveOrUpdateNotesService saveOrUpdateNotesService;

    @Autowired
    private EncryptDecryptNotesService encryptDecryptNotesService;

    @Autowired
    private QueryNotesService queryNotesService;

    @Autowired
    private TextOpsService textOpsService;


    public DownloadNotesPayload downloadNotes(DownloadNotesDTO dto) {

        return downloadNotesService.processRequest(dto);
    }

    public Boolean saveOrUpdateNotes(SaveNotesDTO dto) {

        return saveOrUpdateNotesService.processRequest(dto);
    }

    public QueryNotesResponse queryNotes(QueryNotesDTO dto) {

        return queryNotesService.processRequest(dto);
    }

    public Boolean encryptDecryptNotes(EncryptDecryptNotes dto) {

        return encryptDecryptNotesService.processRequest(dto);
    }

    public TextOperationsResponse textOperations(TextOperationDTO dto) {

        return textOpsService.processRequest(dto);
    }

}
