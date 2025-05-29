package com.mns.admin.repository;

import com.mns.admin.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByNomDocument(String nomDocument);
    Optional<Document> findByNomPhysique(String nomPhysique);
}