package com.mns.admin.service;

import com.mns.admin.model.Utilisateur;
import com.mns.admin.repository.DashboardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Map<String, Object> getAvailableWidgets(Utilisateur user) {
        Map<String, Object> widgets = new HashMap<>();
        String role = user.getRole().getNomRole();

        Set<String> roleWidgets = switch (role) {
            case "Stagiaire" -> Set.of("absences", "documents", "justificatifs", "messages");
            case "Admin" -> Set.of("absences", "justificatifs", "candidatures", "documents", "messages");
            case "Formateur" -> Set.of("absences", "documents", "candidatures", "justificatifs", "messages");
            default -> Collections.emptySet();
        };

        System.out.println("Widgets autorisés pour " + role + " : " + roleWidgets);

        if (roleWidgets.contains("absences")) widgets.put("absences", getAbsencesData(user));
        if (roleWidgets.contains("documents")) widgets.put("documents", getDocumentsData(user));
        if (roleWidgets.contains("justificatifs")) widgets.put("justificatifs", getJustificatifsData(user));
        if (roleWidgets.contains("messages")) widgets.put("messages", getMessagesData(user));
        if (roleWidgets.contains("candidatures")) widgets.put("candidatures", getCandidaturesData(user));

        System.out.println("Widgets envoyés : " + widgets);

        return widgets;
    }

    public Map<String, Object> getAbsencesData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllAbsences();
        }
        return dashboardRepository.getAbsencesByUser(user.getId());
    }

    public Map<String, Object> getCandidaturesData(Utilisateur user) {
        Map<String, Object> candidatures = dashboardRepository.getAllCandidatures();
        return candidatures;
    }

    public Map<String, Object> getDocumentsData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllDocuments();
        }
        return dashboardRepository.getDocumentsByUser(user.getId());
    }

    public Map<String, Object> getJustificatifsData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllJustificatifs();
        }
        return dashboardRepository.getJustificatifsByUser(user.getId());
    }

    public Map<String, Object> getMessagesData(Utilisateur user) {
        return dashboardRepository.getMessagesByUser(user.getId());
    }

    public Map<String, Object> getProfilData(Utilisateur user) {
        return dashboardRepository.getProfilByUser(user.getId());
    }

    public Map<String, Object> getModulesAbsencesData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllModulesAbsences();
        }
        return dashboardRepository.getModulesAbsencesByUser(user.getId());
    }

    public Map<String, Object> getModulesCandidaturesData(Utilisateur user) {
        if (!"Admin".equals(user.getRole().getNomRole().trim())) {
            return Map.of();
        }
        return dashboardRepository.getAllModulesCandidatures();
    }

    public Map<String, Object> getMessagingData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllMessagingData();
        }
        return dashboardRepository.getMessagingByUser(user.getId());
    }

    public Map<String, Object> getUsersData(Utilisateur user) {
        if (!"Admin".equals(user.getRole().getNomRole().trim())) {
            return Map.of();
        }
        return dashboardRepository.getAllUsers();
    }
}
