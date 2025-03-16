package com.mns.admin.service;

import com.mns.admin.model.Utilisateur;
import com.mns.admin.repository.DashboardRepository;
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

    public Map<String, Object> getAvailableMenus(Utilisateur user) {
        Map<String, Object> menus = new HashMap<>();
        String role = user.getRole().getNomRole();

        Set<String> roleMenus = switch (role) {
            case "Stagiaire" -> Set.of("home", "absences", "documents", "messaging");
            case "Admin" -> Set.of("home", "candidatures", "absences", "users", "documents", "messaging", "stats", "settings");
            case "Formateur" -> Set.of("home", "candidatures", "absences", "users", "documents", "messaging");
            default -> Collections.emptySet();
        };

        System.out.println("Menus autorisés pour " + role + " : " + roleMenus);

        if (roleMenus.contains("home")) {
            menus.put("home", getDashboardMenu());
        }
        if (roleMenus.contains("candidatures")) {
            menus.put("candidatures", getCandidaturesMenu());
        }
        if (roleMenus.contains("absences")) {
            menus.put("absences", getAbsencesMenu(user));
        }
        if (roleMenus.contains("documents")) {
            menus.put("documents", getDocumentsMenu());
        }
        if (roleMenus.contains("messaging")) {
            menus.put("messaging", getMessagingMenu());
        }
        if (roleMenus.contains("users")) {
            menus.put("users", getUsersMenu());
        }
        // if (roleMenus.contains("stats")) {
        //     menus.put("stats", getStatsMenu());
        // }
        // if (roleMenus.contains("settings")) {
        //     menus.put("settings", getSettingsMenu());
        // }

        System.out.println("Menus envoyés : " + menus);
        return menus;
    }

    public Map<String, Object> getAbsencesData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllAbsences();
        }
        return dashboardRepository.getAbsencesByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getCandidaturesData(Utilisateur user) {
        Map<String, Object> candidatures = dashboardRepository.getAllCandidatures();
        return candidatures;
    }

    public Map<String, Object> getDocumentsData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllDocuments();
        }
        return dashboardRepository.getDocumentsByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getJustificatifsData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllJustificatifs();
        }
        return dashboardRepository.getJustificatifsByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getMessagesData(Utilisateur user) {
        return dashboardRepository.getMessagesByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getProfilData(Utilisateur user) {
        return dashboardRepository.getProfilByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getDashboardMenu() {
        Map<String, Object> menu = new HashMap<>();
        menu.put("title", "Dashboard");
        menu.put("icon", "bx bx-home-alt");
        menu.put("link", "/frontend-admin-mns/views/dashboard.php");
        return menu;
    }

    public Map<String, Object> getCandidaturesMenu() {
        Map<String, Object> menu = new HashMap<>();
        menu.put("title", "Candidatures");
        menu.put("icon", "bx bx-grid-alt");
        menu.put("link", "/frontend-admin-mns/components/modules/candidatures.php");
        return menu;
    }

    public Map<String, Object> getCandidaturesMenuData() {
        return dashboardRepository.getAllCandidaturesMenu();
    }

    public Map<String, Object> getAbsencesMenu(Utilisateur user) {
        Map<String, Object> menu = new HashMap<>();
        menu.put("title", "Absences");
        menu.put("icon", "bx bx-timer");
        menu.put("link", "/frontend-admin-mns/components/modules/absences.php");
        return menu;
    }

    public Map<String, Object> getAbsencesMenuData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllAbsencesMenu();
        }
        return dashboardRepository.getAbsencesMenuByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getDocumentsMenu() {
        Map<String, Object> menu = new HashMap<>();
        menu.put("title", "Documents (GED)");
        menu.put("icon", "bx bx-folder");
        menu.put("link", "/frontend-admin-mns/components/documents/documents.php");
        return menu;
    }

    public Map<String, Object> getDocumentsMenuData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllDocumentsMenu();
        }
        return dashboardRepository.getDocumentsMenuByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getMessagingMenu() {
        Map<String, Object> menu = new HashMap<>();
        menu.put("title", "Messaging");
        menu.put("icon", "bx bx-mail-send");
        menu.put("link", "/frontend-admin-mns/components/messaging/messaging.php");
        return menu;
    }

    public Map<String, Object> getMessagingMenuData(Utilisateur user) {
        if ("Admin".equals(user.getRole().getNomRole().trim())) {
            return dashboardRepository.getAllMessagingMenu();
        }
        return dashboardRepository.getMessagesMenuByUser(user.getIdUtilisateur());
    }

    public Map<String, Object> getUsersMenu() {
        Map<String, Object> menu = new HashMap<>();
        menu.put("title", "Users");
        menu.put("icon", "bx bx-user");
        menu.put("link", "/frontend-admin-mns/components/users/users.php");
        return menu;
    }

    public Map<String, Object> getUsersMenuData() {
        return dashboardRepository.getAllUsersMenu();
    }

    // public Map<String, Object> getStatsMenu() {
    //     Map<String, Object> menu = new HashMap<>();
    //     menu.put("title", "Stats");
    //     menu.put("icon", "bx bx-line-chart");
    //     menu.put("link", "/frontend-admin-mns/components/more/stats.php");
    //     return menu;
    // }

    // public Map<String, Object> getStatsMenuData() {
    //     return dashboardRepository.getAllStatsMenu();
    // }

    // public Map<String, Object> getSettingsMenu() {
    //     Map<String, Object> menu = new HashMap<>();
    //     menu.put("title", "Settings");
    //     menu.put("icon", "bx bx-cog");
    //     menu.put("link", "/frontend-admin-mns/components/more/settings.php");
    //     return menu;
    // }

    // public Map<String, Object> getSettingsMenuData() {
    //     return dashboardRepository.getAllSettingsMenu();
    // }
}
