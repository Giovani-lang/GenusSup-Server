package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.document_dto.DocumentRequest;
import com.genus.GENUS_SUP.dto.document_dto.DocumentResponse;

import java.util.List;

public interface IDocumentService {

    List<DocumentResponse> addDocument (List<DocumentRequest> document);
    List<DocumentResponse> editDocument (List<Long> id,List<DocumentRequest> document);
}
