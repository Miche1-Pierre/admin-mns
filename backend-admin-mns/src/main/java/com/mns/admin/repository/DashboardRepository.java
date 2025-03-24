package com.mns.admin.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepository {
    private final JdbcTemplate jdbcTemplate;

    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getAllAbsences() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT MONTH(date_debut_absence) AS mois, " +
                        "SUM(CASE WHEN id_type_absence = 1 THEN 1 ELSE 0 END) AS absences, " +
                        "SUM(CASE WHEN id_type_absence = 2 THEN 1 ELSE 0 END) AS retards " +
                        "FROM absence " +
                        "GROUP BY MONTH(date_debut_absence) " +
                        "ORDER BY MONTH(date_debut_absence)"
        );
        return Map.of("absences", data);
    }

    public Map<String, Object> getAbsencesByUser(Long userId) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT MONTH(date_debut_absence) AS mois, " +
                        "SUM(CASE WHEN id_type_absence = 1 THEN 1 ELSE 0 END) AS absences, " +
                        "SUM(CASE WHEN id_type_absence = 2 THEN 1 ELSE 0 END) AS retards " +
                        "FROM absence " +
                        "WHERE id_stagiaire = ? " +
                        "GROUP BY mois " +
                        "ORDER BY mois",
                userId
        );
        return Map.of("absences", data);
    }

    public Map<String, Object> getAllCandidatures() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT i.*, f.nom_formation " +
                        "FROM inscription i " +
                        "INNER JOIN formation f ON i.id_formation = f.id_formation"
        );
        return Map.of("candidatures", data);
    }

    public Map<String, Object> getAllDocuments() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT d.* FROM document d " +
                        "JOIN dossier ds ON d.id_dossier = ds.id_dossier " +
                        "ORDER BY d.date_depot_document DESC " +
                        "LIMIT 5"
        );
        return Map.of("documents", data);
    }

    public Map<String, Object> getDocumentsByUser(Long userId) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT d.* FROM document d " +
                        "JOIN dossier ds ON d.id_dossier = ds.id_dossier " +
                        "WHERE ds.id_stagiaire = ? " +
                        "ORDER BY d.date_depot_document DESC " +
                        "LIMIT 5", userId
        );
        return Map.of("documents", data);
    }

    public Map<String, Object> getMessagesByUser(Long userId) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT n.*, u.nom_utilisateur, u.prenom_utilisateur " +
                        "FROM notification n " +
                        "JOIN utilisateur u ON n.id_utilisateur = u.id_utilisateur " +
                        "WHERE n.id_destinataire = ? " +
                        "ORDER BY n.date_notification DESC " +
                        "LIMIT 5", userId
        );
        return Map.of("messages", data);
    }

    public Map<String, Object> getAllJustificatifs() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT j.*, u.nom_utilisateur, u.prenom_utilisateur FROM justificatif j " +
                        "JOIN utilisateur u ON j.id_utilisateur = u.id_utilisateur " +
                        "ORDER BY j.date_depot_justificatif DESC " +
                        "LIMIT 5"
        );
        return Map.of("justificatifs", data);
    }

    public Map<String, Object> getJustificatifsByUser(Long userId) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT j.*, u.nom_utilisateur, u.prenom_utilisateur FROM justificatif j " +
                        "JOIN utilisateur u ON j.id_utilisateur = u.id_utilisateur " +
                        "WHERE j.id_utilisateur = ? " +
                        "ORDER BY j.date_depot_justificatif DESC " +
                        "LIMIT 5", userId
        );
        return Map.of("justificatifs", data);
    }

    public Map<String, Object> getProfilByUser(Long userId) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT u.nom_utilisateur, u.prenom_utilisateur, r.nom_role " +
                        "FROM utilisateur u " +
                        "JOIN role r ON u.role_id = r.id_role " +
                        "WHERE u.id_utilisateur = ?", userId
        );
        return Map.of("profil", data);
    }

    public Map<String, Object> getAllAbsencesMenu() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "a.id_absence AS id, " +
                        "ua.nom_utilisateur AS utilisateur, " +
                        "ta.nom_type_absence AS type, " +
                        "a.date_debut_absence AS debut, " +
                        "a.date_fin_absence AS fin, " +
                        "a.justifie_absence AS justifie, " +
                        "a.statut_absence AS statut, " +
                        "a.etat_absence AS etat " +
                        "FROM absence a " +
                        "JOIN utilisateur ua ON ua.id_utilisateur = a.id_stagiaire " +
                        "JOIN type_absence ta ON ta.id_type_absence = a.id_type_absence " +
                        "ORDER BY a.date_debut_absence DESC"
        );
        return Map.of("absencesMenu", data);
    }

    public Map<String, Object> getAbsencesMenuByUser(Long id) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "a.id_absence AS id, " +
                        "ua.nom_utilisateur AS utilisateur, " +
                        "ta.nom_type_absence AS type, " +
                        "a.date_debut_absence AS debut, " +
                        "a.date_fin_absence AS fin, " +
                        "a.justifie_absence AS justifie, " +
                        "a.statut_absence AS statut," +
                        "a.etat_absence AS etat " +
                        "FROM absence a " +
                        "JOIN utilisateur ua ON ua.id_utilisateur = a.id_stagiaire " +
                        "JOIN type_absence ta ON ta.id_type_absence = a.id_type_absence " +
                        "WHERE ua.id_utilisateur = ? " +
                        "ORDER BY a.date_debut_absence DESC", id
        );
        return Map.of("absencesMenu", data);
    }

    public Map<String, Object> getAllCandidaturesMenu() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "i.id_inscription AS id, " +
                        "u.nom_utilisateur AS stagiaire, " +
                        "f.nom_formation AS formation, " +
                        "i.date_inscription AS date_inscription, " +
                        "s.statut AS statut " +
                        "FROM inscription i " +
                        "JOIN utilisateur u ON u.id_utilisateur = i.id_stagiaire " +
                        "JOIN formation f ON f.id_formation = i.id_formation " +
                        "JOIN statut s ON s.id_statut = i.id_statut " +
                        "ORDER BY i.date_inscription DESC"
        );
        return Map.of("candidaturesMenu", data);
    }

    public Map<String, Object> getAllDocumentsMenu() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "d.id_document AS id, " +
                        "d.nom_document AS nom, " +
                        "d.date_depot_document AS depot, " +
                        "d.date_limite_document AS limite, " +
                        "td.nom_type_document AS type, " +
                        "do.id_stagiaire AS stagiaire " +
                        "FROM document d " +
                        "JOIN dossier do ON do.id_dossier = d.id_dossier " +
                        "JOIN type_document td ON td.id_type_document = d.id_type_document " +
                        "ORDER BY d.date_depot_document DESC"
        );
        return Map.of("documentsMenu", data);
    }

    public Map<String, Object> getDocumentsMenuByUser(Long id) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "d.id_document AS id, " +
                        "d.nom_document AS nom, " +
                        "d.date_depot_document AS depot, " +
                        "d.date_limite_document AS limite, " +
                        "td.nom_type_document AS type " +
                        "FROM document d " +
                        "JOIN dossier do ON do.id_dossier = d.id_dossier " +
                        "JOIN type_document td ON td.id_type_document = d.id_type_document " +
                        "WHERE do.id_stagiaire = ? " +
                        "ORDER BY d.date_depot_document DESC", id
        );
        return Map.of("documentsMenu", data);
    }

    public Map<String, Object> getAllMessagingMenu() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "n.id_notification AS id, " +
                        "n.date_notification AS date, " +
                        "nt.nom_type_notification AS type, " +
                        "u.nom_utilisateur AS sender " +
                        "FROM notification n " +
                        "JOIN type_notification nt ON nt.id_type_notification = n.id_type_notification " +
                        "JOIN utilisateur u ON u.id_utilisateur = n.id_utilisateur " +
                        "ORDER BY n.date_notification DESC"
        );
        return Map.of("messagingMenu", data);
    }

    public Map<String, Object> getMessagesMenuByUser(Long id) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "n.id_notification AS id, " +
                        "n.date_notification AS date, " +
                        "nt.nom_type_notification AS type, " +
                        "u.nom_utilisateur AS sender, " +
                        "n.contenu_notification AS contenu " +
                        "FROM notification n " +
                        "JOIN type_notification nt ON nt.id_type_notification = n.id_type_notification " +
                        "JOIN utilisateur u ON u.id_utilisateur = n.id_utilisateur " +
                        "WHERE n.id_destinataire = ? " +
                        "ORDER BY n.date_notification DESC", id
        );
        return Map.of("messagingMenu", data);
    }

    public Map<String, Object> getAllUsersMenu() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "u.id_utilisateur AS id, " +
                        "u.nom_utilisateur AS nom, " +
                        "u.prenom_utilisateur AS prenom, " +
                        "u.email_utilisateur AS email, " +
                        "r.nom_role AS role " +
                        "FROM utilisateur u " +
                        "JOIN role r ON u.role_id = r.id_role"
        );
        return Map.of("usersMenu", data);
    }

    // public Map<String, Object> getAllStatsMenu() {
    // }

    // public Map<String, Object> getAllSettingsMenu() {
    // }
}
