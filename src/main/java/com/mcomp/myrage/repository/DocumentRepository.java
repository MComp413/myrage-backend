package com.mcomp.myrage.repository;

import com.mcomp.myrage.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    Document saveAndFlush(Document document);

    List<Document> findAllByOwnerId(Integer ownerId);

}
