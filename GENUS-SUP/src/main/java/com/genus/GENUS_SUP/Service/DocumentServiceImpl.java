package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.*;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.DocumentMapper;
import com.genus.GENUS_SUP.Repository.DocumentRepository;
import com.genus.GENUS_SUP.Repository.EtudiantRepository;
import com.genus.GENUS_SUP.Service.Interface.IDocumentService;
import com.genus.GENUS_SUP.dto.document_dto.DocumentRequest;
import com.genus.GENUS_SUP.dto.document_dto.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements IDocumentService {
    private final DocumentRepository documentRepo;
    private final EtudiantRepository etudiantRepo;
    private final DocumentMapper documentMapper;
    @Override
    public List<DocumentResponse> addDocument(List<DocumentRequest> documentRequest) {
        List<Document> documents = new ArrayList<>();
        for (DocumentRequest document : documentRequest) {
            Document doc = new Document();
            Etudiant etudiant = this.etudiantRepo.findById(document.getEtudiantId())
                    .orElseThrow(()-> new RessourceNotFoundException("This ID :"+document.getEtudiantId()+"doesn't exist"));
            doc.setEtudiant(etudiant);
            doc.setName(document.getName());
            doc.setUrl(document.getUrl());
            documents.add(doc);
        }
        return this.documentMapper.fromDocument(documentRepo.saveAll(documents));
    }

    @Override
    public List<DocumentResponse> editDocument(List<Long> ids, List<DocumentRequest> documentRequests) {
        List<Document> documents = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            DocumentRequest documentRequest = documentRequests.get(i);

            Document document = documentRepo.findById(id)
                    .orElseThrow(() -> new RessourceNotFoundException("Document not found with id " + id));

            // Mise à jour des propriétés du document avec les valeurs de documentRequest
            document.setName(documentRequest.getName());
            document.setUrl(documentRequest.getUrl());

            // Ajouter le document mis à jour à la liste
            documents.add(document);
        }
        // Sauvegarder tous les documents mis à jour dans la base de données
    return this.documentMapper.fromDocument(  documentRepo.saveAll(documents));

    }

}
