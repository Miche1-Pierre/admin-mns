package com.mns.admin.controller;

import com.mns.admin.dto.CandidatureDto;
import com.mns.admin.model.Inscription;
import com.mns.admin.repository.UserRepository;
import com.mns.admin.service.CandidatureService;
import com.mns.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
@CrossOrigin(origins = "http://admin-mns")
public class CandidatureController {
    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private UserService userService;

    @GetMapping("/candidature/{id}")
    public ResponseEntity<?> getCandidatureById(@PathVariable Long id) {
        try {
            CandidatureDto candidatureDto = candidatureService.getCandidatureById(id);
            return new ResponseEntity<>(candidatureDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Candidature non trouvée : " + e.getMessage());
        }
    }

    @PutMapping("/candidature/validate/{id}")
    public ResponseEntity<?> validateCandidature(@PathVariable Long id) {
        try {
            Inscription candidature = candidatureService.validateCandidature(id);

            Long userId = candidature.getStagiaire().getIdUtilisateur();
            userService.updateRoleToStagiaire(userId);

            return ResponseEntity.ok(candidature);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la validation de la candidature : " + e.getMessage());
        }
    }

    @PutMapping("/candidature/refuse/{id}")
    public ResponseEntity<?> refuseCandidature(@PathVariable Long id) {
        try {
            Inscription candidature = candidatureService.refuseCandidature(id);
            return ResponseEntity.ok(candidature);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors du refus de la candidature : " + e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCandidature(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("formationId") Long formationId,
            @RequestParam("cv") MultipartFile cv,
            @RequestParam("lettre") MultipartFile lettre,
            @RequestParam(value = "message", required = false) String message
    ) {
        try {
            CandidatureDto dto = new CandidatureDto();
            dto.setNom(nom);
            dto.setPrenom(prenom);
            dto.setEmail(email);
            dto.setFormationId(formationId);
            dto.setCv(cv);
            dto.setLettre(lettre);
            dto.setMessage(message);

            candidatureService.createCandidature(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Candidature créée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la création de la candidature : " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCandidature(@PathVariable Long id, @RequestBody CandidatureDto dto) {
        try {
            Inscription updatedCandidature = candidatureService.updateCandidature(id, dto);
            return ResponseEntity.ok(updatedCandidature);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la mise à jour de la candidature : " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCandidature(@PathVariable Long id) {
        try {
            candidatureService.deleteCandidature(id);
            return ResponseEntity.ok("Candidature supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la suppression de la candidature : " + e.getMessage());
        }
    }
}
