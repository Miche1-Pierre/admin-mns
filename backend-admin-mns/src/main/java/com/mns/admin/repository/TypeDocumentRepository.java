package com.mns.admin.repository;

import com.mns.admin.model.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
    Optional<TypeDocument> findByNomTypeDocument(String nomTypeDocument);
}
