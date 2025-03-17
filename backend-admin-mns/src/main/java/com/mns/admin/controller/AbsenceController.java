package com.mns.admin.controller;

import com.mns.admin.model.Absence;
import com.mns.admin.model.TypeAbsence;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.repository.TypeAbsenceRepository;
import com.mns.admin.repository.UserRepository;
import com.mns.admin.service.AbsenceService;
import com.mns.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/absences")
@CrossOrigin(origins = "http://admin-mns")
public class AbsenceController {
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TypeAbsenceRepository typeAbsenceRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Absence>> getAllAbsences() {
        return ResponseEntity.ok(absenceService.getAllAbsences());
    }

    @GetMapping("/{stagiaireId}")
    public ResponseEntity<List<Absence>> getAbsencesByStagiaire(@PathVariable Long idStagiaire) {
        return ResponseEntity.ok(absenceService.getAbsencesByStagiaire(idStagiaire));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Absence> createAbsence(
            @RequestParam("idTypeAbsence") Long idTypeAbsence,
            @RequestParam("statutAbsence") String statut,
            @RequestParam("dateDebutAbsence") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
            @RequestParam("dateFinAbsence") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFin,
            @RequestParam(value = "justificatif", required = false) MultipartFile justificatif,
            @RequestHeader("Authorization") String token
    ) {
        try {
            Utilisateur stagiaire = userService.getUserFromToken(token.replace("Bearer ", ""));

            TypeAbsence typeAbsence = typeAbsenceRepository.findById(idTypeAbsence)
                    .orElseThrow(() -> new RuntimeException("Type d'absence introuvable"));

            Absence absence = new Absence();
            absence.setStagiaire(stagiaire);
            absence.setTypeAbsence(typeAbsence);
            absence.setStatutAbsence(statut);
            absence.setDateDebutAbsence(dateDebut);
            absence.setDateFinAbsence(dateFin);

            Absence createdAbsence = absenceService.createAbsence(absence, justificatif);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAbsence);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
        return ResponseEntity.noContent().build();
    }
}
