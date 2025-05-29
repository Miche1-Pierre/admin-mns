package com.mns.admin.service;

import com.mns.admin.dto.DocumentDto;
import com.mns.admin.model.*;
import com.mns.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    public List<DocumentDto> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DocumentDto> getDocumentById(Long id) {
        return documentRepository.findById(id).map(this::convertToDTO);
    }

    public Document createDocument(String nomDocument,
                                   Long idDossier,
                                   Long idStatut,
                                   Long idTypeDocument,
                                   LocalDateTime dateLimite,
                                   MultipartFile fichier,
                                   Utilisateur utilisateur) throws Exception {

        Dossier dossier = dossierRepository.findById(idDossier)
                .orElseThrow(() -> new RuntimeException("Dossier introuvable"));
        Statut statut = statutRepository.findById(idStatut)
                .orElseThrow(() -> new RuntimeException("Statut introuvable"));
        TypeDocument typeDocument = typeDocumentRepository.findById(idTypeDocument)
                .orElseThrow(() -> new RuntimeException("Type de document introuvable"));


        // Simule une clé de chiffrement
        String cle = "cle-fake-" + System.currentTimeMillis();

        byte[] contenuOriginal = fichier.getBytes();
        byte[] contenuChiffre = chiffrerFichier(contenuOriginal, cle);

        Document document = new Document();
        document.setNomDocument(nomDocument);
        document.setDossier(dossier);
        document.setStatut(statut);
        document.setTypeDocument(typeDocument);
        document.setDateDepotDocument(LocalDateTime.now());
        document.setDateLimiteDocument(dateLimite);
        document.setCleChiffrement_document(cle);
        document.setContenuChiffreDocument(contenuChiffre);

        return documentRepository.save(document);
    }

    public Document updateDocument(Long id,
                                   String nomDocument,
                                   Long idStatut,
                                   LocalDateTime dateLimite,
                                   MultipartFile fichier,
                                   Utilisateur utilisateur) throws Exception {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document introuvable"));

        Statut statut = statutRepository.findById(idStatut)
                .orElseThrow(() -> new RuntimeException("Statut introuvable"));

        document.setNomDocument(nomDocument);
        document.setStatut(statut);
        document.setDateLimiteDocument(dateLimite);

        if (fichier != null && !fichier.isEmpty()) {
            String nouvelleCle = "cle-fake-update-" + System.currentTimeMillis();
            byte[] contenuChiffre = chiffrerFichier(fichier.getBytes(), nouvelleCle);
            document.setCleChiffrement_document(nouvelleCle);
            document.setContenuChiffreDocument(contenuChiffre);
        }

        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    private DocumentDto convertToDTO(Document document) {
        return new DocumentDto(
                document.getIdDocument(),
                document.getNomDocument(),
                document.getTypeDocument(),
                document.getDateDepotDocument(),
                document.getDateLimiteDocument()
        );
    }

    // Méthode de "chiffrement" (exemple simplifié)
    private byte[] chiffrerFichier(byte[] contenu, String cle) {
        // Exemple : XOR avec chaque byte de la clé répétée
        byte[] cleBytes = cle.getBytes();
        byte[] resultat = new byte[contenu.length];

        for (int i = 0; i < contenu.length; i++) {
            resultat[i] = (byte) (contenu[i] ^ cleBytes[i % cleBytes.length]);
        }

        return resultat;
    }
}
