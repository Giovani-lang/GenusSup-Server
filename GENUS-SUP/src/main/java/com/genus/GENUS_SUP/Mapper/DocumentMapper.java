package com.genus.GENUS_SUP.Mapper;


import com.genus.GENUS_SUP.Entity.Document;
import com.genus.GENUS_SUP.dto.document_dto.DocumentRequest;
import com.genus.GENUS_SUP.dto.document_dto.DocumentResponse;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface DocumentMapper {
    Document fromDocumentRequest (DocumentRequest documentRequest);

    List<DocumentResponse> fromDocument (List<Document> document);
}
