package com.mns.admin.controller;

import com.mns.admin.model.Utilisateur;
import com.mns.admin.security.JwtUtil;
import com.mns.admin.service.DashboardService;
import com.mns.admin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://admin-mns")
public class DashboardController {
    private final DashboardService dashboardService;
    private final UserService userService;

    public DashboardController(DashboardService dashboardService, UserService userService) {
        this.dashboardService = dashboardService;
        this.userService = userService;
    }

    private Utilisateur getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return userService.getUserFromToken(token);
    }

    @GetMapping("/widgets")
    public ResponseEntity<?> getAvailableWidgets(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> widgets = dashboardService.getAvailableWidgets(user);
        System.out.println("Widgets retournés : " + widgets);
        return ResponseEntity.ok(dashboardService.getAvailableWidgets(user));
    }

    @GetMapping("/absences")
    public ResponseEntity<?> getAbsences(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> absencesData = dashboardService.getAbsencesData(user);
        return ResponseEntity.ok(absencesData);
    }

    @GetMapping("/candidatures")
    public ResponseEntity<?> getCandidatures(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> candidaturesData = dashboardService.getCandidaturesData(user);
        return ResponseEntity.ok(candidaturesData);
    }

    @GetMapping("/documents")
    public ResponseEntity<?> getDocuments(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> documentsData = dashboardService.getDocumentsData(user);
        return ResponseEntity.ok(documentsData);
    }

    @GetMapping("/justificatifs")
    public ResponseEntity<?> getJustificatifs(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> justificatifsData = dashboardService.getJustificatifsData(user);
        return ResponseEntity.ok(justificatifsData);
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> messagesData = dashboardService.getMessagesData(user);
        return ResponseEntity.ok(messagesData);
    }

    @GetMapping("/profil")
    public ResponseEntity<?> getProfil(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> profilData = dashboardService.getProfilData(user);
        return ResponseEntity.ok(profilData);
    }

    @GetMapping("/modules/absences")
    public ResponseEntity<?> getModulesAbsences(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> modulesAbsencesData = dashboardService.getModulesAbsencesData(user);
        return ResponseEntity.ok(modulesAbsencesData);
    }

    @GetMapping("/modules/candidatures")
    public ResponseEntity<?> getModulesCandidatures(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> modulesCandidaturesData = dashboardService.getModulesCandidaturesData(user);
        return ResponseEntity.ok(modulesCandidaturesData);
    }

    @GetMapping("/messaging")
    public ResponseEntity<?> getMessaging(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> messagingData = dashboardService.getMessagingData(user);
        return ResponseEntity.ok(messagingData);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> usersData = dashboardService.getUsersData(user);
        return ResponseEntity.ok(usersData);
    }
}
