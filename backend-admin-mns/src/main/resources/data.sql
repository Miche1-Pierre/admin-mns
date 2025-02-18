
-- Insertion des données dans la table "role"
INSERT IGNORE INTO role (nom_role, description_role) VALUES
                                                  ('Admin', 'Administrateur du système'),
                                                  ('Stagiaire', 'Utilisateur stagiaire'),
                                                  ('Formateur', 'Formateur en charge des formations'),
                                                  ('Manager', 'Responsable de la gestion des stagiaires');

-- Insertion des données dans la table "utilisateur"
INSERT IGNORE INTO utilisateur (role_id, nom_utilisateur, prenom_utilisateur, email_utilisateur, mot_de_passe_utilisateur) VALUES
                                                                                                                        (1, 'Dupont', 'Jean', 'jean.dupont@example.com', 'password123'),
                                                                                                                        (2, 'Martin', 'Lucie', 'lucie.martin@example.com', 'password123'),
                                                                                                                        (3, 'Lemoine', 'Pierre', 'pierre.lemoine@example.com', 'password123'),
                                                                                                                        (4, 'Blanc', 'Isabelle', 'isabelle.blanc@example.com', 'password123');

-- Insertion des données dans la table "statut"
INSERT IGNORE INTO statut (statut) VALUES
                                ('ACCEPTE'),
                                ('EN_ATTENTE'),
                                ('REFUSE');

-- Insertion des données dans la table "type_absence"
INSERT IGNORE INTO type_absence (nom_type_absence) VALUES
                                                ('Maladie'),
                                                ('Congé payé'),
                                                ('Formation'),
                                                ('Autre');

-- Insertion des données dans la table "type_document"
INSERT IGNORE INTO type_document (chiffrement_requis_type_document, obligatoire_type_document, nom_type_document, description_type_document) VALUES
                                                                                                                                          (1, 1, 'Contrat de stage', 'Contrat signé pour le stage'),
                                                                                                                                          (0, 1, 'Diplôme', 'Diplôme requis pour la formation'),
                                                                                                                                          (0, 0, 'Justificatif médical', 'Certificat médical pour justifier une absence');

-- Insertion des données dans la table "formation"
INSERT IGNORE INTO formation (duree_formation, date_debut_formation, date_fin_formation, nom_formation, description_formation) VALUES
                                                                                                                            (5, '2025-03-01 09:00:00', '2025-03-05 17:00:00', 'Formation Java', 'Formation complète en développement Java'),
                                                                                                                            (3, '2025-04-01 09:00:00', '2025-04-03 17:00:00', 'Formation Spring Boot', 'Formation sur le framework Spring Boot'),
                                                                                                                            (4, '2025-05-01 09:00:00', '2025-05-04 17:00:00', 'Formation Base de données', 'Formation sur les bases de données relationnelles');

-- Insertion des données dans la table "inscription"
INSERT IGNORE INTO inscription (id_formation, id_stagiaire, date_inscription) VALUES
                                                                           (1, 2, '2025-02-10 14:00:00'),
                                                                           (2, 3, '2025-02-12 14:00:00'),
                                                                           (3, 4, '2025-02-15 14:00:00');

-- Insertion des données dans la table "absence"
INSERT IGNORE INTO absence (justifie_absence, date_debut_absence, date_fin_absence, id_stagiaire, id_type_absence) VALUES
                                                                                                                (1, '2025-02-01 08:00:00', '2025-02-01 17:00:00', 2, 1),
                                                                                                                (0, '2025-02-02 08:00:00', '2025-02-02 17:00:00', 3, 2);

-- Insertion des données dans la table "dossier"
INSERT IGNORE INTO dossier (id_stagiaire, id_statut, date_creation_dossier, date_validation_dossier) VALUES
                                                                                                  (2, 1, '2025-02-10 10:00:00', '2025-02-12 10:00:00'),
                                                                                                  (3, 2, '2025-02-11 10:00:00', '2025-02-13 10:00:00');

-- Insertion des données dans la table "document"
INSERT IGNORE INTO document (date_depot_document, date_limite_document, id_dossier, id_statut, id_type_document, cle_chiffrement_document, contenu_chiffre_document) VALUES
                                                                                                                                                                  ('2025-02-10 10:00:00', '2025-02-20 12:00:00', 1, 1, 1, 'abc123', 'Document chiffré 1'),
                                                                                                                                                                  ('2025-02-11 11:00:00', '2025-02-21 12:00:00', 2, 2, 2, 'xyz789', 'Document chiffré 2');

-- Insertion des données dans la table "notification"
INSERT IGNORE INTO notification (date_notification, id_type_notification, id_utilisateur, statut_notification, contenu_notification) VALUES
                                                                                                                                  ('2025-02-18 10:00:00', 1, 1, 'EN_ATTENTE', 'Nouvelle inscription à valider'),
                                                                                                                                  ('2025-02-18 11:00:00', 2, 2, 'ACCEPTE', 'Votre dossier a été validé');

-- Insertion des données dans la table "type_notification"
INSERT IGNORE INTO type_notification (nom_type_notification) VALUES
                                                          ('Inscription'),
                                                          ('Validation de dossier');

-- Insertion des données dans la table "justificatif"
INSERT IGNORE INTO justificatif (date_depot_justificatif, id_absence, id_statut, type_document_justificatif) VALUES
                                                                                                          ('2025-02-02 10:00:00', 1, 1, 'Certificat médical'),
                                                                                                          ('2025-02-03 11:00:00', 2, 2, 'Justificatif de congé');

-- Insertion des données dans la table "module"
INSERT IGNORE INTO module (nom_module, description_module) VALUES
                                                        ('Module Java', 'Module de formation Java'),
                                                        ('Module Spring Boot', 'Module de formation Spring Boot');

-- Insertion des données dans la table "role_module"
INSERT IGNORE INTO role_module (actif_role_module, id_module, id_role) VALUES
                                                                    (1, 1, 1),
                                                                    (1, 2, 3);

-- Insertion des données dans la table "formation_type_document"
INSERT IGNORE INTO formation_type_document (id_formation, id_type_document, obligatoire_formation_type_document) VALUES
                                                                                                              (1, 1, 1),
                                                                                                              (2, 2, 1);

-- Insertion des données dans la table "secret"
INSERT IGNORE INTO secret (nom_secret, complete_hash_secret, description_secret, statut_secret) VALUES
                                                                                             ('Secret 1', 'hashvalue123', 'Description du secret 1', 'ACTIF'),
                                                                                             ('Secret 2', 'hashvalue456', 'Description du secret 2', 'INACTIF');

-- Insertion des données dans la table "part_secret"
INSERT IGNORE INTO part_secret (date_creation_part_secret, id_secret, id_utilisateur, statut_part_secret, part_part_secret) VALUES
                                                                                                                          ('2025-02-18 10:00:00', 1, 1, 'ACTIF', 'Partie secrète 1'),
                                                                                                                          ('2025-02-18 11:00:00', 2, 2, 'INACTIF', 'Partie secrète 2');
