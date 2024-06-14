package com.mcomp.myrage.service;

import com.mcomp.myrage.controller.payload.DocumentCreationData;
import com.mcomp.myrage.model.Document;
import com.mcomp.myrage.model.UserData;
import com.mcomp.myrage.model.enums.DocumentStatus;
import com.mcomp.myrage.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> listDocumentsOwnedBy(UUID ownerId) {
        return documentRepository.findAllByOwner_Id(ownerId);
    }

    public Document createDocument(DocumentCreationData documentCreationData, UserData userData) throws IOException, NoSuchAlgorithmException {
        MultipartFile fileContent = documentCreationData.getFile();
        String fileHash = getFileHash(fileContent);
        fileContent.getContentType();
        var newDocument = Document.builder()
                .id(UUID.randomUUID())
                .owner(userData)
                .name(documentCreationData.getName())
                .status(DocumentStatus.QUEUED)
                .integrityHash(fileHash)
                .size((int) documentCreationData.getFile().getSize())
                .createdAt(Instant.now())
                .build();
        String storagePath = callStorageService(newDocument, fileContent);
        newDocument.setStoragePath(storagePath);
        return documentRepository.saveAndFlush(newDocument);
    }

    private String getFileHash(MultipartFile fileContent) throws IOException, NoSuchAlgorithmException {
        BufferedInputStream fileStream = new BufferedInputStream(fileContent.getInputStream());
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        DigestInputStream streamDigest = new DigestInputStream(fileStream, messageDigest);
        while (streamDigest.read() != -1){};
        streamDigest.close();
        String fileHash = messageDigest.digest().toString();
        return fileHash;
    }

    private String callStorageService(Document document, MultipartFile fileContent){
        // TODO: connect to storage
        return String.format("documents/%s/%s",
            document.getOwner().getId().toString(),
            document.getId().toString());
    }

    public void deleteDocument(UUID documentId) {
        documentRepository.deleteById(documentId);
    }

}
