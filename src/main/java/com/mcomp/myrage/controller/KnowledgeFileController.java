package com.mcomp.myrage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/documents")
public class KnowledgeFileController {

  @GetMapping()
  public ResponseEntity<String> listDocuments() {
    return new ResponseEntity<>("get de documentos", HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<String> uploadDocument() {
    return new ResponseEntity<>("criação de documento", HttpStatus.CREATED);
  }

  @DeleteMapping("/{documentId}")
  public ResponseEntity<String> removeDocument(@PathVariable Integer documentId) {
    return new ResponseEntity<>("remoção de documento", HttpStatus.OK);
  }
}
