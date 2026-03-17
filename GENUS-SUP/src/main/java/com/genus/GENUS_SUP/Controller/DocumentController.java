package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IDocumentService;
import com.genus.GENUS_SUP.dto.document_dto.DocumentRequest;
import com.genus.GENUS_SUP.dto.document_dto.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final IDocumentService documentService;

    @PostMapping("/add")
    public ResponseEntity<List<DocumentResponse>> addDocument(@RequestBody List<DocumentRequest> document){
        return new ResponseEntity<>(this.documentService.addDocument(document), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{ids}")
    public ResponseEntity<List<DocumentResponse>> editDocument(@PathVariable(name = "ids") List<Long> ids,
                                                               @RequestBody List<DocumentRequest> document){
        return new ResponseEntity<>(this.documentService.editDocument(ids,document), HttpStatus.ACCEPTED);
    }
}
