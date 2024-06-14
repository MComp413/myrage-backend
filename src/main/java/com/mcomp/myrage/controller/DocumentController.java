package com.mcomp.myrage.controller;

import com.mcomp.myrage.controller.payload.DocumentCreationData;
import com.mcomp.myrage.controller.payload.DocumentListItem;
import com.mcomp.myrage.converter.DocumentConverter;
import com.mcomp.myrage.model.Document;
import com.mcomp.myrage.model.UserData;
import com.mcomp.myrage.service.DocumentService;
import com.mcomp.myrage.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
public class DocumentController {

  @Autowired
  private DocumentConverter documentConverter;
  @Autowired
  private DocumentService documentService;
  @Autowired
  private UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<DocumentListItem>> listDocuments(@PathVariable UUID userId) {
    List<Document> documents = documentService.listDocumentsOwnedBy(userId);
    List<DocumentListItem> responseData = documents.stream()
            .map(documentConverter::entityToListItem)
            .toList();
    return new ResponseEntity<>(responseData, HttpStatus.OK);
  }

  @Transactional
  @PostMapping
  public ResponseEntity<DocumentListItem> uploadDocument(
          @ModelAttribute @Validated DocumentCreationData documentCreationData) {
    try {
      UserData ownerData = userService.retrieveOrCreate(documentCreationData.getOwnerEmail());
      Document savedDocument = documentService.createDocument(documentCreationData, ownerData);
      DocumentListItem responsePayload = documentConverter.entityToListItem(savedDocument);
      return new ResponseEntity<>(responsePayload, HttpStatus.CREATED);
    } catch (Exception exception) {
      System.out.println(exception.toString());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional
  @DeleteMapping("/{documentId}")
  public ResponseEntity<String> removeDocument(@PathVariable Integer documentId) {
    return new ResponseEntity<>("remoção de documento", HttpStatus.OK);
  }
}
