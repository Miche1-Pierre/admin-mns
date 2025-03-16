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

import java.io.File;
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

    @Value("${file.upload-dir:uploads}")
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
}