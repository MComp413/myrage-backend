package com.mcomp.myrage.repository;

import com.mcomp.myrage.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    Document saveAndFlush(Document document);

    List<Document> findAllByOwner_Id(UUID ownerId);

}
