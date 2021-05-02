package com.project.notes.controller;

import com.project.notes.Urls;
import com.project.notes.dto.request.*;
import com.project.notes.dto.response.DownloadNotesPayload;
import com.project.notes.dto.response.QueryNotesResponse;
import com.project.notes.dto.response.TextOperationsResponse;
import com.project.notes.services.NotesServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = Urls.BASE_URL)
public class NoteBoxController {

    @Autowired
    private NotesServices notesServices;


    @ApiOperation(value = "Generate Unique notes id", tags = {"Notes API"})
    @GetMapping(path = Urls.GENERATE_NOTES_ID)
    public ResponseEntity<String> generateUniqueNoteId() {

        return new ResponseEntity<>(UUID.randomUUID().toString(), HttpStatus.OK);
    }

    @ApiOperation(value = "Query Notes", tags = {"Notes API"})
    @GetMapping(path = Urls.FIND_NOTES,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QueryNotesResponse> queryNotes(@PathVariable("id") String id, @RequestParam(required = false) String encryptionKey) {
        QueryNotesDTO dto = new QueryNotesDTO();
        dto.setNotesId(id);
        dto.setEncryptionKey(encryptionKey);

        QueryNotesResponse resp = notesServices.queryNotes(dto);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ApiOperation(value = "Save or Update Notes", tags = {"Notes API"})
    @PostMapping(path = Urls.SAVE_NOTES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> saveNotes(@Valid @RequestBody SaveNotesDTO saveNotesDTO) {
        Boolean resp = notesServices.saveOrUpdateNotes(saveNotesDTO);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ApiOperation(value = "Encrypt/Decrypt Note", tags = {"Notes API"})
    @PutMapping(path = Urls.ENCRYPT_DECRYPT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> encryptOrDecryptNote(@Valid @RequestBody EncryptDecryptNotes reqDTO) {
        Boolean resp = notesServices.encryptDecryptNotes(reqDTO);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @ApiOperation(value = "Download Notes as File", tags = {"Notes API"})
    @GetMapping(path = Urls.DOWNLOAD_NOTES)
    public ResponseEntity<Resource> downloadNotes(@Valid DownloadNotesDTO reqDTO) {

        DownloadNotesPayload expPayload = notesServices.downloadNotes(reqDTO);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + expPayload.getFileName())
                .contentType(MediaType.parseMediaType(MediaType.TEXT_PLAIN_VALUE)).body(new InputStreamResource(expPayload.getExpPayload()));
    }


    @ApiOperation(value = "Text Operations", tags = {"Notes API"})
    @GetMapping(path = Urls.TEXT_OPS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TextOperationsResponse> performTextOperationOnTheFly(@Valid TextOperationDTO reqDTO) {

        TextOperationsResponse resp = notesServices.textOperations(reqDTO);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
