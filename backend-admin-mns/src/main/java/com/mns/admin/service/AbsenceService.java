package com.mns.admin.service;

import com.mns.admin.model.Absence;
import com.mns.admin.model.TypeAbsence;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.repository.AbsenceRepository;
import com.mns.admin.repository.TypeAbsenceRepository;
import com.mns.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AbsenceService {
    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeAbsenceRepository typeAbsenceRepository;

    @Autowired
    private UserRepository userRepository;

    private final String UPLOAD_DIR = "uploads/";

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public List<Absence> getAbsencesByStagiaire(Long stagiaireId) {
        return absenceRepository.findByStagiaireIdUtilisateur(stagiaireId);
    }

    public Absence createAbsence(Absence absence, MultipartFile justificatif) throws IOException {
        Utilisateur stagiaire = userRepository.findById(absence.getStagiaire().getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        TypeAbsence typeAbsence = typeAbsenceRepository.findById(absence.getTypeAbsence().getIdTypeAbsence())
                .orElseThrow(() -> new RuntimeException("Type d'absence introuvable"));

        absence.setStagiaire(stagiaire);
        absence.setTypeAbsence(typeAbsence);
        absence.setJustifieAbsence(justificatif != null);

        if (justificatif != null && !justificatif.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + justificatif.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.write(path, justificatif.getBytes());
            absence.setJustificatifPath(filename);
        }

        return absenceRepository.save(absence);
    }

    public void deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
    }
}
