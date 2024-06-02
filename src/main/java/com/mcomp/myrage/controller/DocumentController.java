package com.mcomp.myrage.controller;

import com.mcomp.myrage.controller.payload.DocumentCreationData;
import com.mcomp.myrage.controller.payload.DocumentListItem;
import com.mcomp.myrage.converter.DocumentConverter;
import com.mcomp.myrage.model.Document;
import com.mcomp.myrage.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

  @Autowired
  private DocumentConverter documentConverter;
  @Autowired
  private DocumentService documentService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<DocumentListItem>> listDocuments(@PathVariable Integer userId) {
    List<Document> documents = documentService.listDocumentsOwnedBy(userId);
    List<DocumentListItem> responseData = documents.stream()
            .map(documentConverter::entityToListItem)
            .toList();
    return new ResponseEntity<>(responseData, HttpStatus.OK);
  }

  @PostMapping("/{userId}")
  public ResponseEntity<DocumentListItem> uploadDocument(
          @PathVariable Integer userId,
          @Validated @RequestBody DocumentCreationData documentCreationData) {
    Document document = documentConverter.dataToEntity(documentCreationData);
    Document savedDocument = documentService.createDocument(document, userId);
    DocumentListItem responsePayload = documentConverter.entityToListItem(savedDocument);
    return new ResponseEntity<>(responsePayload, HttpStatus.CREATED);
  }

  @DeleteMapping("/{documentId}")
  public ResponseEntity<String> removeDocument(@PathVariable Integer documentId) {
    return new ResponseEntity<>("remoção de documento", HttpStatus.OK);
  }
}
