package com.mcomp.myrage.service;

import com.mcomp.myrage.model.Document;
import com.mcomp.myrage.model.enums.DocumentStatus;
import com.mcomp.myrage.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DocumentService {
    private DocumentRepository documentRepository;

    public List<Document> listDocumentsOwnedBy(Integer ownerId) {
        return documentRepository.findAllByOwnerId(ownerId);
    }

    public Document createDocument(Document document, Integer userId) {
        document.setOwnerId(userId);
        return createDocument(document);
    }

    public Document createDocument(Document document) {
        document.setStatus(DocumentStatus.QUEUED);
        document.setStoragePath("/placeholder/storage");
        return documentRepository.saveAndFlush(document);
    }

    public void deleteDocument(Integer documentId) {
        documentRepository.deleteById(documentId);
    }

}
