package com.mns.admin.service;

import com.mns.admin.dto.CandidatureDto;
import com.mns.admin.model.*;
import com.mns.admin.repository.DossierRepository;
import com.mns.admin.repository.FormationRepository;
import com.mns.admin.repository.InscriptionRepository;
import com.mns.admin.repository.StatutRepository;
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

    @Value("${UPLOAD_DIR}")
    private String uploadDir;

    @Transactional
    public void createCandidature(CandidatureDto dto) {
        ensureUploadDirExists();

        String cvFilename = saveFile(dto.getCv());
        String lettreFilename = saveFile(dto.getLettre());

        Dossier dossier = new Dossier();
        dossier.setDateCreationDossier(LocalDateTime.now());
        dossier.setStatut(getStatut(Statut.StatutEnum.EN_ATTENTE));
        Utilisateur utilisateur = userService.createUserFromCandidature(dto.getNom(), dto.getPrenom(), dto.getEmail());
        dossier.setStagiaire(utilisateur);

        dossierRepository.save(dossier);

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

    private String saveFile(MultipartFile file) {
        try {
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String filename = UUID.randomUUID() + "_" + originalFilename;
            Path destinationPath = Paths.get(uploadDir).resolve(filename);
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier: " + file.getOriginalFilename(), e);
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

        // Vous pouvez ajouter ici la mise à jour d'autres informations,
        // par exemple si vous souhaitez mettre à jour des fichiers (CV, lettre)
        // ou d'autres champs liés au dossier associé.

        return inscriptionRepository.save(inscription);
    }

    // Supprimer une candidature
    @Transactional
    public void deleteCandidature(Long id) {
        // Vous pouvez ajouter ici une logique pour supprimer également le dossier associé si nécessaire.
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