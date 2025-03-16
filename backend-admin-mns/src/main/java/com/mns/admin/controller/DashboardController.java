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

    @GetMapping("/profil")
    public ResponseEntity<?> getProfil(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> profilData = dashboardService.getProfilData(user);
        return ResponseEntity.ok(profilData);
    }

    // Widgets
    @GetMapping("/widgets")
    public ResponseEntity<?> getAvailableWidgets(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> widgets = dashboardService.getAvailableWidgets(user);
        System.out.println("Widgets retournés : " + widgets);
        return ResponseEntity.ok(dashboardService.getAvailableWidgets(user));
    }

    // Menus
    @GetMapping("/menus")
    public ResponseEntity<?> getAvailableMenus(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> menus = dashboardService.getAvailableMenus(user);
        System.out.println("Menus retournés : " + menus);
        return ResponseEntity.ok(dashboardService.getAvailableMenus(user));
    }

    @GetMapping("/candidatures")
    public ResponseEntity<?> getCandidaturesMenuData(HttpServletRequest request) {
        getAuthenticatedUser(request);
        Map<String, Object> data = dashboardService.getCandidaturesMenuData();
        System.out.println("Candidatures data: " + data);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/absences")
    public ResponseEntity<?> getAbsencesMenuData(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> data = dashboardService.getAbsencesMenuData(user);
        System.out.println("Absences data: " + data);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/documents")
    public ResponseEntity<?> getDocumentsMenuData(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> data = dashboardService.getDocumentsMenuData(user);
        System.out.println("Documents data: " + data);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/messaging")
    public ResponseEntity<?> getMessagingMenuData(HttpServletRequest request) {
        Utilisateur user = getAuthenticatedUser(request);
        Map<String, Object> data = dashboardService.getMessagingMenuData(user);
        System.out.println("Messaging data: " + data);
        return ResponseEntity.ok(data);
    }
}
