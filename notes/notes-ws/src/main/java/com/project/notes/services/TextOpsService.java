package com.project.notes.services;

import com.project.notes.dto.request.TextOperationDTO;
import com.project.notes.dto.response.TextOperationsResponse;
import com.project.notes.encryption.EncryptNotes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Locale;

@Service
@Scope("prototype")
public class TextOpsService extends AbstractNotesService<TextOperationDTO, TextOperationsResponse> {

    @Override
    public TextOperationsResponse processRequest(TextOperationDTO dto) {

        Serializable response = null;

        switch (dto.getTextOperation()) {

            case LEN:
                response = dto.getData().length();
                break;
            case SUBSTRING:
                if (dto.getStartIdx() > dto.getEndIdx()) {
                    throw new IllegalArgumentException("Start Index cannot be greater than End Index!");
                }
                if (dto.getEndIdx() > dto.getData().length()) {
                    throw new IllegalArgumentException("End Index is greater than the length of data!");
                }

                response = dto.getData().substring(dto.getStartIdx(), dto.getEndIdx());
                break;
            case LOWERCASE:
                response = dto.getData().toLowerCase(Locale.ROOT);
                break;
            case UPPERCASE:
                response = dto.getData().toUpperCase(Locale.ROOT);
                break;
            case ENCRYPT:
                if (StringUtils.isEmpty(dto.getEncryptionPassword())) {
                    throw new IllegalArgumentException("Encryption Key is Required!");
                }
                response = EncryptNotes.encrypt(dto.getData(), dto.getEncryptionPassword());
                break;
            case DECRYPT:
                if (StringUtils.isEmpty(dto.getEncryptionPassword())) {
                    throw new IllegalArgumentException("Encryption Key is Required!");
                }
                response = EncryptNotes.decrypt(dto.getData(), dto.getEncryptionPassword());
                break;

            default:
                //sonar issue
                break;
        }

        TextOperationsResponse textOperationsResponse = new TextOperationsResponse();
        textOperationsResponse.setResponse(response);

        return textOperationsResponse;
    }
}
