package com.mns.admin.controller;

import com.mns.admin.dto.DocumentDto;
import com.mns.admin.model.Document;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.service.DocumentService;
import com.mns.admin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://admin-mns")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // GET all documents
    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @GetMapping("/preview/{filename:.+}")
    public ResponseEntity<Resource> previewFile(@PathVariable String filename) throws MalformedURLException {
        Path filePath = Paths.get(uploadDir).resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    // GET document by ID
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id) {
        Optional<DocumentDto> document = documentService.getDocumentById(id);
        return document.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create document with file
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createDocument(
            @RequestParam("nomDocument") String nomDocument,
            @RequestParam("idDossier") Long idDossier,
            @RequestParam("idStatut") Long idStatut,
            @RequestParam("idTypeDocument") Long idTypeDocument,
            @RequestParam("dateLimiteDocument") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateLimiteDocument,
            @RequestParam("fichier") MultipartFile fichier,
            @RequestHeader("Authorization") String token
    ) {
        try {
            Utilisateur utilisateur = userService.getUserFromToken(token.replace("Bearer ", ""));

            Document document = documentService.createDocument(nomDocument, idDossier, idStatut, idTypeDocument, dateLimiteDocument, fichier, utilisateur);

            return ResponseEntity.status(HttpStatus.CREATED).body(document);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
        }
    }

    // PUT update document
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDocument(
            @PathVariable Long id,
            @RequestParam("nomDocument") String nomDocument,
            @RequestParam("idStatut") Long idStatut,
            @RequestParam("dateLimiteDocument") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateLimiteDocument,
            @RequestParam(value = "fichier", required = false) MultipartFile fichier,
            @RequestHeader("Authorization") String token
    ) {
        try {
            Utilisateur utilisateur = userService.getUserFromToken(token.replace("Bearer ", ""));
            Document updated = documentService.updateDocument(id, nomDocument, idStatut, dateLimiteDocument, fichier, utilisateur);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
        }
    }

    // DELETE document
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
