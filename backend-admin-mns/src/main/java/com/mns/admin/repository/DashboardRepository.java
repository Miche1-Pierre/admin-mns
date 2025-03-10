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

    public Map<String, Object> getAllModulesAbsences() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "a.id_absence AS id, " +
                        "ua.nom_utilisateur AS utilisateur, " +
                        "ta.nom_type_absence AS type, " +
                        "a.date_debut_absence AS debut, " +
                        "a.date_fin_absence AS fin, " +
                        "a.justifie_absence AS justifie, " +
                        "a.statut_absence AS statut " +
                        "FROM absence a " +
                        "JOIN utilisateur ua ON ua.id_utilisateur = a.id_stagiaire " +
                        "JOIN type_absence ta ON ta.id_type_absence = a.id_type_absence"
        );
        return Map.of("absences", data);
    }

    public Map<String, Object> getModulesAbsencesByUser(Long id) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT * FROM absence WHERE id_stagiaire = ?", id
        );
        return Map.of("absences", data);
    }

    public Map<String, Object> getAllModulesCandidatures() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "i.id_inscription AS id, " +
                        "i.id_stagiaire AS stagiaire, " +
                        "i.date_inscription AS date_inscription, " +
                        "f.id_formation AS formation " +
                        "FROM inscription i " +
                        "JOIN formation f ON f.id_formation = i.id_formation"

        );
        return Map.of("modules", data);
    }

    public Map<String, Object> getAllMessagingData() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT * FROM messaging"
        );
        return Map.of("messaging", data);
    }

    public Map<String, Object> getMessagingByUser(Long id) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT * FROM messaging WHERE utilisateur_id = ?", id
        );
        return Map.of("messaging", data);
    }

    public Map<String, Object> getAllUsers() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(
                "SELECT " +
                        "u.id_utilisateur AS id, " +
                        "u.nom_utilisateur AS nom, " +
                        "u.prenom_utilisateur AS prenom, " +
                        "u.email_utilisateur AS email, " +
                        "ru.nom_role AS role " +
                        "FROM utilisateur u " +
                        "JOIN role ru ON u.role_id = ru.id_role"
        );
        return Map.of("users", data);
    }
}
