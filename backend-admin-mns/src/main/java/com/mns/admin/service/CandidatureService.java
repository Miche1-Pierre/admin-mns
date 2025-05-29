package com.mns.admin.service;

import com.mns.admin.dto.CandidatureDto;
import com.mns.admin.model.*;
import com.mns.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CandidatureService {

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    @Value("${UPLOAD_DIR}")
    private String uploadDir;

    @Transactional
    public void createCandidature(CandidatureDto dto) {
        ensureUploadDirExists();

        // Création de l'utilisateur
        Utilisateur utilisateur = userService.createUserFromCandidature(
                dto.getNom(), dto.getPrenom(), dto.getEmail()
        );

        // Création du dossier
        Dossier dossier = new Dossier();
        dossier.setDateCreationDossier(LocalDateTime.now());
        dossier.setStatut(getStatut(Statut.StatutEnum.EN_ATTENTE));
        dossier.setStagiaire(utilisateur);
        dossierRepository.save(dossier);

        // Sauvegarde des documents
        if (dto.getCv() != null) {
            saveDocumentToDB(dto.getCv(), dossier, "CV");
        }

        if (dto.getLettre() != null) {
            saveDocumentToDB(dto.getLettre(), dossier, "Lettre de motivation");
        }

        // Création de l'inscription
        if (dto.getFormationId() != null) {
            Formation formation = formationRepository.findById(dto.getFormationId())
                    .orElseThrow(() -> new RuntimeException("Formation non trouvée"));

            Inscription inscription = new Inscription();
            inscription.setStagiaire(utilisateur);
            inscription.setFormation(formation);
            inscription.setDateInscription(LocalDateTime.now());
            inscription.setStatut(getStatut(Statut.StatutEnum.EN_ATTENTE));
            inscriptionRepository.save(inscription);
        }
    }

    private void saveDocumentToDB(MultipartFile file, Dossier dossier, String typeNom) {
        try {
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
            Path destinationPath = Paths.get(uploadDir).resolve(uniqueFilename);
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Document document = new Document();
            document.setNomDocument(originalFilename);
            document.setNomPhysique(uniqueFilename);
            document.setDateDepotDocument(LocalDateTime.now());
            document.setCleChiffrement_document(UUID.randomUUID().toString());
            document.setContenuChiffreDocument(file.getBytes());
            document.setDossier(dossier);
            document.setStatut(getStatut(Statut.StatutEnum.EN_ATTENTE));

            TypeDocument typeDocument = typeDocumentRepository.findByNomTypeDocument(typeNom)
                    .orElseThrow(() -> new RuntimeException("Type de document non trouvé : " + typeNom));
            document.setTypeDocument(typeDocument);

            documentRepository.save(document);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du document : " + file.getOriginalFilename(), e);
        }
    }

    private void ensureUploadDirExists() {
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Impossible de créer le dossier d'uploads", e);
            }
        }
    }

    private Statut getStatut(Statut.StatutEnum statutEnum) {
        return statutRepository.findByStatutEnum(statutEnum)
                .orElseThrow(() -> new RuntimeException("Statut non trouvé"));
    }

    public CandidatureDto getCandidatureById(Long id) {
        Inscription candidature = inscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidature non trouvée"));

        CandidatureDto candidatureDto = new CandidatureDto();
        candidatureDto.setId(candidature.getIdInscription());

        if (candidature.getStagiaire() != null) {
            candidatureDto.setNom(candidature.getStagiaire().getNomUtilisateur());
            candidatureDto.setPrenom(candidature.getStagiaire().getPrenomUtilisateur());
            candidatureDto.setEmail(candidature.getStagiaire().getEmailUtilisateur());
        }

        candidatureDto.setDateInscription(candidature.getDateInscription());
        candidatureDto.setFormationNom(candidature.getFormation().getNomFormation());
        candidatureDto.setInscriptionEtat(candidature.getEtatInscription());

        return candidatureDto;
    }

    @Transactional
    public Inscription updateCandidature(Long id, CandidatureDto dto) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));

        // Mise à jour de la formation si un nouvel identifiant est fourni
        if (dto.getFormationId() != null) {
            Formation formation = formationRepository.findById(dto.getFormationId())
                    .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
            inscription.setFormation(formation);
        }

        return inscriptionRepository.save(inscription);
    }

    // Supprimer une candidature
    @Transactional
    public void deleteCandidature(Long id) {
        inscriptionRepository.deleteById(id);
    }

    public Inscription validateCandidature(Long id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));

        if (inscription.getEtatInscription() == Inscription.InscriptionEtat.VALIDE) {
            throw new RuntimeException("Candidature déjà validée");
        }

        inscription.setEtatInscription(Inscription.InscriptionEtat.VALIDE);
        inscriptionRepository.save(inscription);
        return inscription;
    }

    public Inscription refuseCandidature(Long id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));

        if (inscription.getEtatInscription() == Inscription.InscriptionEtat.REFUSE) {
            throw new RuntimeException("Candidature déjà refusée");
        }

        inscription.setEtatInscription(Inscription.InscriptionEtat.REFUSE);
        inscriptionRepository.save(inscription);
        return inscription;
    }
}