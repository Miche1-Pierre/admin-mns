SET FOREIGN_KEY_CHECKS = 0;

-- Insertion des données dans la table "role"
INSERT IGNORE INTO role (nom_role, description_role) VALUES
('Admin', 'Administrateur du système'),
('Stagiaire', 'Utilisateur stagiaire'),
('Formateur', 'Formateur en charge des formations'),
('Candidat', 'Utilisateur candidat');

-- Insertion des données dans la table "utilisateur"
INSERT IGNORE INTO utilisateur (role_id, nom_utilisateur, prenom_utilisateur, email_utilisateur, mot_de_passe_utilisateur) VALUES
-- Admins
(1, 'Dupont', 'Jean', 'jean.dupont@example.com', 'password123'),
(1, 'Bernard', 'Sophie', 'sophie.bernard@example.com', 'password123'),
(1, 'Morel', 'Antoine', 'antoine.morel@example.com', 'password123'),
(1, 'Garcia', 'Elise', 'elise.garcia@example.com', 'password123'),

-- Formateurs
(3, 'Martin', 'Lucie', 'lucie.martin@example.com', 'password123'),
(3, 'Lemoine', 'Pierre', 'pierre.lemoine@example.com', 'password123'),
(3, 'Blanc', 'Isabelle', 'isabelle.blanc@example.com', 'password123'),
(3, 'Fischer', 'Hugo', 'hugo.fischer@example.com', 'password123'),
(3, 'Girard', 'Camille', 'camille.girard@example.com', 'password123'),
(3, 'Robin', 'Maxime', 'maxime.robin@example.com', 'password123'),
(3, 'Noel', 'Juliette', 'juliette.noel@example.com', 'password123'),
(3, 'Rey', 'Thomas', 'thomas.rey@example.com', 'password123'),
(3, 'Perrot', 'Chloe', 'chloe.perrot@example.com', 'password123'),
(3, 'Baron', 'Louis', 'louis.baron@example.com', 'password123'),
(3, 'Moulin', 'Emma', 'emma.moulin@example.com', 'password123'),

-- Stagiaires et candidats (répartition aléatoire)
(2, 'Leroy', 'Nathan', 'nathan.leroy@example.com', 'password123'),
(2, 'David', 'Alice', 'alice.david@example.com', 'password123'),
(2, 'Moreau', 'Hugo', 'hugo.moreau@example.com', 'password123'),
(2, 'Simon', 'Jade', 'jade.simon@example.com', 'password123'),
(2, 'Roux', 'Leo', 'leo.roux@example.com', 'password123'),
(2, 'Vincent', 'Manon', 'manon.vincent@example.com', 'password123'),
(2, 'Bertrand', 'Ethan', 'ethan.bertrand@example.com', 'password123'),
(2, 'Lambert', 'Clara', 'clara.lambert@example.com', 'password123'),
(2, 'Henry', 'Gabriel', 'gabriel.henry@example.com', 'password123'),
(2, 'Masson', 'Lina', 'lina.masson@example.com', 'password123'),
(4, 'Garnier', 'Alexis', 'alexis.garnier@example.com', 'password123'),
(4, 'Chevalier', 'Sofia', 'sofia.chevalier@example.com', 'password123'),
(4, 'Lucas', 'Matteo', 'matteo.lucas@example.com', 'password123'),
(4, 'Bonnet', 'Louise', 'louise.bonnet@example.com', 'password123'),
(4, 'François', 'Evan', 'evan.francois@example.com', 'password123'),
(4, 'Dupuis', 'Elena', 'elena.dupuis@example.com', 'password123'),
(4, 'Berger', 'Oscar', 'oscar.berger@example.com', 'password123'),
(4, 'Gautier', 'Mila', 'mila.gautier@example.com', 'password123'),
(4, 'Roger', 'Arthur', 'arthur.roger@example.com', 'password123'),
(4, 'Collet', 'Léa', 'lea.collet@example.com', 'password123'),
(2, 'Guillot', 'Theo', 'theo.guillot@example.com', 'password123'),
(4, 'Barre', 'Emma', 'emma.barre@example.com', 'password123'),
(2, 'Perrin', 'Lucas', 'lucas.perrin@example.com', 'password123'),
(4, 'Renard', 'Elise', 'elise.renard@example.com', 'password123'),
(2, 'Marchal', 'Adam', 'adam.marchal@example.com', 'password123'),
(4, 'Leclerc', 'Camille', 'camille.leclerc@example.com', 'password123'),
(2, 'Bouvier', 'Noah', 'noah.bouvier@example.com', 'password123'),
(4, 'Giraud', 'Nina', 'nina.giraud@example.com', 'password123'),
(2, 'Leger', 'Enzo', 'enzo.leger@example.com', 'password123'),
(4, 'Besson', 'Sarah', 'sarah.besson@example.com', 'password123'),
(2, 'Durand', 'Mael', 'mael.durand@example.com', 'password123'),
(4, 'Fleury', 'Zoé', 'zoe.fleury@example.com', 'password123'),
(2, 'Benoit', 'Raphael', 'raphael.benoit@example.com', 'password123'),
(4, 'Poulain', 'Lola', 'lola.poulain@example.com', 'password123'),
(2, 'Germain', 'Eliott', 'eliott.germain@example.com', 'password123'),
(4, 'Barbier', 'Ava', 'ava.barbier@example.com', 'password123'),
(2, 'Chauvin', 'Timéo', 'timeo.chauvin@example.com', 'password123'),
(4, 'Pichon', 'Salomé', 'salome.pichon@example.com', 'password123'),
(2, 'Legrand', 'Mathis', 'mathis.legrand@example.com', 'password123'),
(4, 'Roy', 'Iris', 'iris.roy@example.com', 'password123'),
(2, 'Picard', 'Valentin', 'valentin.picard@example.com', 'password123'),
(4, 'Mallet', 'Lou', 'lou.mallet@example.com', 'password123'),
(2, 'Devaux', 'Paul', 'paul.devaux@example.com', 'password123'),
(4, 'Maillard', 'Sacha', 'sacha.maillard@example.com', 'password123'),
(2, 'Charpentier', 'Eden', 'eden.charpentier@example.com', 'password123'),
(4, 'Hoarau', 'Elsa', 'elsa.hoarau@example.com', 'password123'),
(2, 'Gosselin', 'Jules', 'jules.gosselin@example.com', 'password123'),
(4, 'Descamps', 'Ambre', 'ambre.descamps@example.com', 'password123'),
(2, 'Chapel', 'Esteban', 'esteban.chapel@example.com', 'password123'),
(4, 'Legendre', 'Luna', 'luna.legendre@example.com', 'password123');

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
-- Documents administratifs
(1, 1, 'Contrat de stage', 'Contrat signé pour le stage'),
(1, 1, 'Contrat dapprentissage', 'Contrat officiel pour un apprentissage'),
(1, 1, 'Convention de formation', 'Convention signée entre létudiant et lécole'),
(1, 1, 'Accord de confidentialité', 'Engagement de confidentialité signé par létudiant'),
(0, 1, 'Autorisation parentale', 'Autorisation signée pour les mineurs'),

-- Documents académiques
(0, 1, 'Diplôme', 'Diplôme requis pour la formation'),
(0, 1, 'Relevé de notes', 'Relevé des résultats scolaires du candidat'),
(0, 1, 'Attestation de réussite', 'Document prouvant l obtention d un diplôme ou certification'),
(0, 1, 'Lettre de recommandation', 'Lettre rédigée par un enseignant ou employeur'),
(0, 1, 'Certificat de scolarité', 'Attestation prouvant l inscription dans un établissement scolaire'),

-- Justificatifs
(0, 0, 'Justificatif médical', 'Certificat médical pour justifier une absence'),
(0, 1, 'Justificatif de domicile', 'Facture ou attestation prouvant l adresse de résidence'),
(0, 1, 'Attestation d assurance', 'Justificatif de couverture assurance étudiant'),
(0, 1, 'Justificatif de paiement', 'Preuve du règlement des frais de scolarité'),
(0, 0, 'Avis d imposition', 'Document fiscal pour certaines aides financières'),

-- Documents financiers
(0, 1, 'RIB', 'Relevé d identité bancaire pour les paiements'),
(0, 1, 'Accord de financement', 'Accord de prise en charge des frais de scolarité'),
(1, 1, 'Dossier de financement', 'Documents requis pour un financement scolaire'),

-- Pièces d identité
(0, 1, 'Carte d identité', 'Carte nationale didentité ou passeport en cours de validité'),
(0, 1, 'Passeport', 'Passeport valide pour les étudiants étrangers'),
(1, 1, 'Permis de séjour', 'Autorisation de séjour pour les étudiants internationaux'),

-- Divers
(0, 1, 'CV', 'Curriculum vitae du candidat'),
(0, 1, 'Lettre de motivation', 'Lettre expliquant les motivations du candidat'),
(0, 0, 'Portfolio', 'Exemples de travaux ou projets réalisés par le candidat'),
(0, 0, 'Attestation de participation', 'Document justifiant la participation à un événement'),
(0, 0, 'Déclaration sur l honneur', 'Déclaration signée pour diverses démarches');

-- Insertion des données dans la table "formation"
INSERT IGNORE INTO formation (duree_formation, date_debut_formation, date_fin_formation, nom_formation, description_formation) VALUES
(3,  '2025-09-01 09:00:00', '2025-03-05 17:00:00', 'BSD', 'Formation complète en développement Java'),
(3,  '2025-09-01 09:00:00', '2025-04-03 17:00:00', 'BSRC', 'Formation sur le framework Spring Boot'),
(1,  '2025-09-01 09:00:00', '2025-05-04 17:00:00', 'CDA', 'Formation sur les bases de données relationnelles'),
(2,  '2025-09-01 09:00:00', '2025-06-02 17:00:00', 'DFS', 'Gestion de version avec Git et GitHub'),
(2,  '2025-09-01 09:00:00', '2025-07-03 17:00:00', 'M2I', 'Création de sites web avec HTML et CSS'),
(2,  '2025-09-01 09:00:00', '2025-08-05 17:00:00', 'TSSR', 'Maîtrise du langage JavaScript'),
(2,  '2025-09-01 09:00:00', '2025-09-04 17:00:00', 'MSRC', 'Développement frontend avec React'),
(2,  '2025-09-01 09:00:00', '2025-09-04 17:00:00', 'DEVWEB', 'Développement frontend avec React');

-- Insertion des données dans la table "inscription"
INSERT IGNORE INTO inscription (id_formation, id_stagiaire, date_inscription) VALUES
(1, 2, '2025-02-10 14:00:00'),
(2, 3, '2025-02-12 14:00:00'),
(3, 4, '2025-02-15 14:00:00'),
(4, 5, '2025-02-18 10:00:00'),
(5, 6, '2025-02-20 11:30:00'),
(6, 7, '2025-02-22 09:00:00'),
(7, 8, '2025-02-24 13:45:00'),
(8, 9, '2025-02-26 16:20:00'),
(1, 10, '2025-02-28 08:10:00'),
(2, 11, '2025-03-01 14:00:00'),
(2, 12, '2025-03-03 10:00:00'),
(1, 13, '2025-03-05 15:00:00'),
(3, 14, '2025-03-07 09:00:00'),
(8, 15, '2025-03-09 11:30:00'),
(1, 16, '2025-03-11 14:00:00'),
(2, 17, '2025-03-13 10:00:00'),
(3, 18, '2025-03-15 09:30:00'),
(8, 19, '2025-03-17 16:45:00'),
(9, 20, '2025-03-19 13:00:00'),
(1, 21, '2025-03-21 14:00:00'),
(1, 22, '2025-02-10 14:00:00'),
(2, 23, '2025-02-12 14:00:00'),
(3, 24, '2025-02-15 14:00:00'),
(4, 25, '2025-02-18 10:00:00'),
(5, 26, '2025-02-20 11:30:00'),
(6, 27, '2025-02-22 09:00:00'),
(7, 28, '2025-02-24 13:45:00'),
(8, 29, '2025-02-26 16:20:00'),
(1, 30, '2025-02-28 08:10:00'),
(2, 31, '2025-03-01 14:00:00'),
(2, 32, '2025-03-03 10:00:00'),
(1, 33, '2025-03-05 15:00:00'),
(3, 34, '2025-03-07 09:00:00'),
(8, 35, '2025-03-09 11:30:00'),
(1, 36, '2025-03-11 14:00:00'),
(2, 37, '2025-03-13 10:00:00'),
(3, 38, '2025-03-15 09:30:00'),
(8, 39, '2025-03-17 16:45:00'),
(9, 40, '2025-03-19 13:00:00'),
(1, 41, '2025-03-21 14:00:00');

-- Insertion des données dans la table "absence"
INSERT IGNORE INTO absence (justifie_absence, date_debut_absence, date_fin_absence, id_stagiaire, id_type_absence, statut_absence) VALUES
(1, '2025-01-01 08:00:00', '2025-01-01 17:00:00', 2, 1, 'Retard'),
(0, '2025-01-02 08:00:00', '2025-01-02 17:00:00', 3, 2, 'Retard'),
(1, '2025-01-03 08:00:00', '2025-01-03 17:00:00', 4, 1, 'Retard'),
(0, '2025-01-04 08:00:00', '2025-01-04 17:00:00', 5, 2, 'Retard'),
(1, '2025-01-05 08:00:00', '2025-01-05 17:00:00', 6, 1, 'Retard'),
(0, '2025-02-06 08:00:00', '2025-02-06 17:00:00', 7, 2, 'Retard'),
(1, '2025-02-07 08:00:00', '2025-02-07 17:00:00', 8, 1, 'Retard'),
(0, '2025-02-08 08:00:00', '2025-02-08 17:00:00', 9, 2, 'Retard'),
(1, '2025-02-09 08:00:00', '2025-02-09 17:00:00', 10, 1, 'Absence'),
(0, '2025-02-10 08:00:00', '2025-02-10 17:00:00', 11, 2, 'Absence'),
(1, '2025-02-11 08:00:00', '2025-02-11 17:00:00', 12, 1, 'Absence'),
(0, '2025-02-12 08:00:00', '2025-02-12 17:00:00', 13, 2, 'Absence'),
(1, '2025-02-13 08:00:00', '2025-02-13 17:00:00', 14, 1, 'Absence'),
(0, '2025-02-14 08:00:00', '2025-02-14 17:00:00', 15, 2, 'Absence'),
(1, '2025-02-15 08:00:00', '2025-02-15 17:00:00', 16, 1, 'Absence'),
(0, '2025-02-16 08:00:00', '2025-02-16 17:00:00', 17, 2, 'Absence'),
(1, '2025-02-17 08:00:00', '2025-02-17 17:00:00', 18, 1, 'Absence'),
(0, '2025-02-18 08:00:00', '2025-02-18 17:00:00', 19, 2, 'Absence'),
(1, '2025-02-19 08:00:00', '2025-02-19 17:00:00', 20, 1, 'Retard'),
(0, '2025-02-20 08:00:00', '2025-02-20 17:00:00', 21, 2, 'Retard'),
(1, '2025-02-21 08:00:00', '2025-02-21 17:00:00', 22, 1, 'Retard'),
(0, '2025-02-22 08:00:00', '2025-02-22 17:00:00', 23, 2, 'Retard'),
(1, '2025-02-23 08:00:00', '2025-02-23 17:00:00', 24, 1, 'Retard'),
(0, '2025-02-24 08:00:00', '2025-02-24 17:00:00', 25, 2, 'Retard'),
(1, '2025-02-25 08:00:00', '2025-02-25 17:00:00', 26, 1, 'Absence'),
(0, '2025-02-26 08:00:00', '2025-02-26 17:00:00', 27, 2, 'Absence'),
(1, '2025-02-27 08:00:00', '2025-02-27 17:00:00', 28, 1, 'Absence'),
(0, '2025-02-28 08:00:00', '2025-02-28 17:00:00', 29, 2, 'Absence'),
(1, '2025-03-01 08:00:00', '2025-03-01 17:00:00', 30, 1, 'Absence'),
(0, '2025-03-02 08:00:00', '2025-03-02 17:00:00', 31, 2, 'Retard'),
(1, '2025-03-03 08:00:00', '2025-03-03 17:00:00', 32, 1, 'Retard'),
(0, '2025-03-04 08:00:00', '2025-03-04 17:00:00', 33, 2, 'Retard'),
(1, '2025-03-05 08:00:00', '2025-03-05 17:00:00', 34, 1, 'Retard'),
(0, '2025-03-06 08:00:00', '2025-03-06 17:00:00', 35, 2, 'Retard'),
(1, '2025-03-07 08:00:00', '2025-03-07 17:00:00', 36, 1, 'Retard'),
(0, '2025-03-08 08:00:00', '2025-03-08 17:00:00', 37, 2, 'Retard'),
(1, '2025-03-09 08:00:00', '2025-03-09 17:00:00', 38, 1, 'Retard'),
(0, '2025-03-10 08:00:00', '2025-03-10 17:00:00', 39, 2, 'Retard'),
(1, '2025-03-11 08:00:00', '2025-03-11 17:00:00', 40, 1, 'Retard'),
(0, '2025-03-12 08:00:00', '2025-03-12 17:00:00', 41, 2, 'Retard'),
(1, '2025-03-13 08:00:00', '2025-03-13 17:00:00', 42, 1, 'Retard'),
(0, '2025-03-14 08:00:00', '2025-03-14 17:00:00', 43, 2, 'Retard'),
(1, '2025-03-15 08:00:00', '2025-03-15 17:00:00', 44, 1, 'Retard'),
(0, '2025-03-16 08:00:00', '2025-03-16 17:00:00', 45, 2, 'Retard'),
(1, '2025-03-17 08:00:00', '2025-03-17 17:00:00', 46, 1, 'Retard'),
(0, '2025-03-18 08:00:00', '2025-03-18 17:00:00', 47, 2, 'Retard'),
(1, '2025-03-19 08:00:00', '2025-03-19 17:00:00', 48, 1, 'Retard'),
(0, '2025-03-20 08:00:00', '2025-03-20 17:00:00', 49, 2, 'Retard'),
(1, '2025-03-21 08:00:00', '2025-03-21 17:00:00', 50, 1, 'Retard');

-- Insertion des données dans la table "dossier"
INSERT IGNORE INTO dossier (id_stagiaire, id_statut, date_creation_dossier, date_validation_dossier) VALUES
(2, 1, '2025-02-10 10:00:00', '2025-02-12 10:00:00'),
(3, 2, '2025-02-11 10:00:00', '2025-02-13 10:00:00'),
(4, 1, '2025-02-12 10:00:00', '2025-02-14 10:00:00'),
(5, 2, '2025-02-13 10:00:00', '2025-02-15 10:00:00'),
(6, 1, '2025-02-14 10:00:00', '2025-02-16 10:00:00'),
(7, 2, '2025-02-15 10:00:00', '2025-02-17 10:00:00'),
(8, 1, '2025-02-16 10:00:00', '2025-02-18 10:00:00'),
(9, 2, '2025-02-17 10:00:00', '2025-02-19 10:00:00'),
(10, 1, '2025-02-18 10:00:00', '2025-02-20 10:00:00'),
(11, 2, '2025-02-19 10:00:00', '2025-02-21 10:00:00'),
(12, 1, '2025-02-20 10:00:00', '2025-02-22 10:00:00'),
(13, 2, '2025-02-21 10:00:00', '2025-02-23 10:00:00'),
(14, 1, '2025-02-22 10:00:00', '2025-02-24 10:00:00'),
(15, 2, '2025-02-23 10:00:00', '2025-02-25 10:00:00'),
(16, 1, '2025-02-24 10:00:00', '2025-02-26 10:00:00'),
(17, 2, '2025-02-25 10:00:00', '2025-02-27 10:00:00'),
(18, 1, '2025-02-26 10:00:00', '2025-02-28 10:00:00'),
(19, 2, '2025-02-27 10:00:00', '2025-03-01 10:00:00'),
(20, 1, '2025-02-28 10:00:00', '2025-03-02 10:00:00'),
(21, 2, '2025-03-01 10:00:00', '2025-03-03 10:00:00'),
(22, 1, '2025-03-02 10:00:00', '2025-03-04 10:00:00'),
(23, 2, '2025-03-03 10:00:00', '2025-03-05 10:00:00'),
(24, 1, '2025-03-04 10:00:00', '2025-03-06 10:00:00'),
(25, 2, '2025-03-05 10:00:00', '2025-03-07 10:00:00'),
(26, 1, '2025-03-06 10:00:00', '2025-03-08 10:00:00'),
(27, 2, '2025-03-07 10:00:00', '2025-03-09 10:00:00'),
(28, 1, '2025-03-08 10:00:00', '2025-03-10 10:00:00'),
(29, 2, '2025-03-09 10:00:00', '2025-03-11 10:00:00'),
(30, 1, '2025-03-10 10:00:00', '2025-03-12 10:00:00'),
(31, 2, '2025-03-11 10:00:00', '2025-03-13 10:00:00'),
(32, 1, '2025-03-12 10:00:00', '2025-03-14 10:00:00');

-- Insertion des données dans la table "document"
INSERT IGNORE INTO document (date_depot_document, date_limite_document, id_dossier, id_statut, id_type_document, cle_chiffrement_document, contenu_chiffre_document) VALUES
('2025-01-31 11:04:23', '2025-02-10 11:04:23', 11, 4, 19, 'key001', 'Document chiffré 1'),
('2025-01-04 08:25:59', '2025-01-14 08:25:59', 18, 1, 24, 'key002', 'Document chiffré 2'),
('2025-01-15 08:05:33', '2025-01-25 08:05:33', 12, 3, 7, 'key003', 'Document chiffré 3'),
('2025-01-21 22:55:52', '2025-01-31 22:55:52', 10, 1, 6, 'key004', 'Document chiffré 4'),
('2025-01-29 14:12:58', '2025-02-08 14:12:58', 1, 3, 1, 'key005', 'Document chiffré 5'),
('2025-01-03 08:25:08', '2025-01-13 08:25:08', 4, 1, 20, 'key006', 'Document chiffré 6'),
('2025-02-07 01:57:24', '2025-02-17 01:57:24', 11, 4, 23, 'key007', 'Document chiffré 7'),
('2025-01-12 22:05:01', '2025-01-22 22:05:01', 18, 3, 5, 'key008', 'Document chiffré 8'),
('2025-01-25 14:36:59', '2025-02-04 14:36:59', 4, 1, 7, 'key009', 'Document chiffré 9'),
('2025-01-12 14:39:25', '2025-01-22 14:39:25', 12, 1, 8, 'key010', 'Document chiffré 10'),
('2025-01-11 02:52:10', '2025-01-21 02:52:10', 17, 2, 9, 'key011', 'Document chiffré 11'),
('2025-01-06 00:36:41', '2025-01-16 00:36:41', 6, 1, 18, 'key012', 'Document chiffré 12'),
('2025-01-10 23:37:29', '2025-01-20 23:37:29', 6, 3, 12, 'key013', 'Document chiffré 13'),
('2025-01-01 17:56:57', '2025-01-11 17:56:57', 19, 3, 11, 'key014', 'Document chiffré 14'),
('2025-02-01 05:21:14', '2025-02-11 05:21:14', 3, 2, 24, 'key015', 'Document chiffré 15'),
('2025-01-11 11:17:19', '2025-01-21 11:17:19', 11, 3, 5, 'key016', 'Document chiffré 16'),
('2025-02-03 22:25:38', '2025-02-13 22:25:38', 14, 1, 6, 'key017', 'Document chiffré 17'),
('2025-01-05 02:06:10', '2025-01-15 02:06:10', 14, 4, 8, 'key018', 'Document chiffré 18'),
('2025-02-01 23:00:58', '2025-02-11 23:00:58', 10, 1, 15, 'key019', 'Document chiffré 19'),
('2025-01-27 15:16:04', '2025-02-06 15:16:04', 6, 2, 2, 'key020', 'Document chiffré 20'),
('2025-01-27 12:51:50', '2025-02-06 12:51:50', 12, 2, 1, 'key021', 'Document chiffré 21'),
('2025-02-07 03:08:23', '2025-02-17 03:08:23', 4, 3, 21, 'key022', 'Document chiffré 22'),
('2025-01-24 04:01:20', '2025-02-03 04:01:20', 7, 4, 9, 'key023', 'Document chiffré 23'),
('2025-02-05 21:48:28', '2025-02-15 21:48:28', 1, 4, 21, 'key024', 'Document chiffré 24'),
('2025-01-10 05:09:14', '2025-01-20 05:09:14', 7, 3, 17, 'key025', 'Document chiffré 25'),
('2025-01-09 09:58:49', '2025-01-19 09:58:49', 15, 3, 22, 'key026', 'Document chiffré 26'),
('2025-01-11 05:07:18', '2025-01-21 05:07:18', 16, 2, 2, 'key027', 'Document chiffré 27'),
('2025-01-05 11:43:30', '2025-01-15 11:43:30', 18, 1, 20, 'key028', 'Document chiffré 28'),
('2025-01-04 15:55:37', '2025-01-14 15:55:37', 4, 4, 11, 'key029', 'Document chiffré 29'),
('2025-01-20 03:19:03', '2025-01-30 03:19:03', 13, 4, 4, 'key030', 'Document chiffré 30'),
('2025-01-03 09:53:33', '2025-01-13 09:53:33', 17, 2, 5, 'key031', 'Document chiffré 31'),
('2025-01-10 18:39:54', '2025-01-20 18:39:54', 15, 2, 18, 'key032', 'Document chiffré 32'),
('2025-02-02 04:15:35', '2025-02-12 04:15:35', 16, 4, 11, 'key033', 'Document chiffré 33'),
('2025-01-15 12:44:50', '2025-01-25 12:44:50', 13, 1, 21, 'key034', 'Document chiffré 34'),
('2025-01-31 11:44:17', '2025-02-10 11:44:17', 13, 2, 15, 'key035', 'Document chiffré 35'),
('2025-01-22 00:55:15', '2025-02-01 00:55:15', 11, 1, 12, 'key036', 'Document chiffré 36'),
('2025-01-05 03:54:43', '2025-01-15 03:54:43', 3, 4, 2, 'key037', 'Document chiffré 37'),
('2025-01-24 19:23:05', '2025-02-03 19:23:05', 18, 4, 4, 'key038', 'Document chiffré 38'),
('2025-01-27 00:33:15', '2025-02-06 00:33:15', 15, 1, 1, 'key039', 'Document chiffré 39'),
('2025-01-31 04:46:00', '2025-02-10 04:46:00', 8, 1, 13, 'key040', 'Document chiffré 40'),
('2025-01-28 04:19:02', '2025-02-07 04:19:02', 4, 2, 5, 'key041', 'Document chiffré 41'),
('2025-01-16 22:44:50', '2025-01-26 22:44:50', 14, 3, 19, 'key042', 'Document chiffré 42'),
('2025-02-09 12:56:17', '2025-02-19 12:56:17', 8, 1, 20, 'key043', 'Document chiffré 43'),
('2025-01-20 01:26:04', '2025-01-30 01:26:04', 1, 2, 18, 'key044', 'Document chiffré 44'),
('2025-01-08 09:47:00', '2025-01-18 09:47:00', 5, 4, 25, 'key045', 'Document chiffré 45'),
('2025-02-03 12:47:17', '2025-02-13 12:47:17', 15, 2, 19, 'key046', 'Document chiffré 46'),
('2025-01-23 17:42:46', '2025-02-02 17:42:46', 15, 2, 3, 'key047', 'Document chiffré 47'),
('2025-01-21 08:33:17', '2025-01-31 08:33:17', 1, 4, 4, 'key048', 'Document chiffré 48'),
('2025-01-03 21:48:07', '2025-01-13 21:48:07', 19, 2, 22, 'key049', 'Document chiffré 49'),
('2025-01-31 00:18:57', '2025-02-10 00:18:57', 20, 3, 15, 'key050', 'Document chiffré 50'),
('2025-01-19 06:14:43', '2025-01-29 06:14:43', 2, 4, 18, 'key051', 'Document chiffré 51'),
('2025-01-23 04:53:34', '2025-02-02 04:53:34', 10, 4, 2, 'key052', 'Document chiffré 52'),
('2025-01-16 08:15:36', '2025-01-26 08:15:36', 15, 1, 14, 'key053', 'Document chiffré 53'),
('2025-01-24 22:27:24', '2025-02-03 22:27:24', 4, 4, 15, 'key054', 'Document chiffré 54'),
('2025-01-11 05:14:08', '2025-01-21 05:14:08', 13, 4, 4, 'key055', 'Document chiffré 55'),
('2025-01-15 00:48:06', '2025-01-25 00:48:06', 8, 4, 6, 'key056', 'Document chiffré 56'),
('2025-01-31 00:27:32', '2025-02-10 00:27:32', 4, 2, 22, 'key057', 'Document chiffré 57'),
('2025-01-22 10:43:24', '2025-02-01 10:43:24', 10, 3, 4, 'key058', 'Document chiffré 58'),
('2025-01-10 13:10:20', '2025-01-20 13:10:20', 20, 3, 20, 'key059', 'Document chiffré 59'),
('2025-01-26 16:04:35', '2025-02-05 16:04:35', 4, 1, 18, 'key060', 'Document chiffré 60'),
('2025-01-28 00:09:58', '2025-02-07 00:09:58', 8, 3, 18, 'key061', 'Document chiffré 61'),
('2025-01-27 05:50:04', '2025-02-06 05:50:04', 1, 4, 25, 'key062', 'Document chiffré 62'),
('2025-02-05 21:24:58', '2025-02-15 21:24:58', 12, 1, 6, 'key063', 'Document chiffré 63'),
('2025-01-10 06:15:46', '2025-01-20 06:15:46', 4, 3, 16, 'key064', 'Document chiffré 64'),
('2025-01-09 23:06:31', '2025-01-19 23:06:31', 13, 2, 25, 'key065', 'Document chiffré 65'),
('2025-01-19 17:50:08', '2025-01-29 17:50:08', 6, 2, 17, 'key066', 'Document chiffré 66'),
('2025-02-06 23:02:12', '2025-02-16 23:02:12', 20, 1, 10, 'key067', 'Document chiffré 67'),
('2025-01-31 19:22:31', '2025-02-10 19:22:31', 20, 4, 3, 'key068', 'Document chiffré 68'),
('2025-01-11 11:17:46', '2025-01-21 11:17:46', 3, 3, 7, 'key069', 'Document chiffré 69'),
('2025-01-15 10:14:11', '2025-01-25 10:14:11', 12, 3, 11, 'key070', 'Document chiffré 70'),
('2025-01-20 15:42:37', '2025-01-30 15:42:37', 5, 2, 23, 'key071', 'Document chiffré 71'),
('2025-02-01 04:42:06', '2025-02-11 04:42:06', 1, 1, 14, 'key072', 'Document chiffré 72'),
('2025-01-06 14:03:52', '2025-01-16 14:03:52', 14, 4, 18, 'key073', 'Document chiffré 73'),
('2025-01-10 05:12:57', '2025-01-20 05:12:57', 11, 3, 24, 'key074', 'Document chiffré 74'),
('2025-02-08 17:07:14', '2025-02-18 17:07:14', 17, 3, 7, 'key075', 'Document chiffré 75'),
('2025-01-27 11:10:09', '2025-02-06 11:10:09', 12, 2, 24, 'key076', 'Document chiffré 76'),
('2025-01-14 05:30:08', '2025-01-24 05:30:08', 11, 4, 17, 'key077', 'Document chiffré 77'),
('2025-01-05 10:24:39', '2025-01-15 10:24:39', 8, 1, 19, 'key078', 'Document chiffré 78'),
('2025-01-31 21:50:57', '2025-02-10 21:50:57', 9, 4, 10, 'key079', 'Document chiffré 79'),
('2025-01-29 01:38:01', '2025-02-08 01:38:01', 6, 2, 16, 'key080', 'Document chiffré 80'),
('2025-01-23 23:45:18', '2025-02-02 23:45:18', 20, 3, 24, 'key081', 'Document chiffré 81'),
('2025-01-09 12:44:53', '2025-01-19 12:44:53', 7, 1, 1, 'key082', 'Document chiffré 82'),
('2025-01-15 01:42:44', '2025-01-25 01:42:44', 19, 2, 15, 'key083', 'Document chiffré 83'),
('2025-02-03 08:12:00', '2025-02-13 08:12:00', 15, 3, 7, 'key084', 'Document chiffré 84'),
('2025-02-04 18:25:44', '2025-02-14 18:25:44', 11, 4, 18, 'key085', 'Document chiffré 85'),
('2025-01-08 10:58:58', '2025-01-18 10:58:58', 5, 4, 9, 'key086', 'Document chiffré 86'),
('2025-01-02 02:50:15', '2025-01-12 02:50:15', 18, 4, 22, 'key087', 'Document chiffré 87'),
('2025-01-16 07:51:41', '2025-01-26 07:51:41', 14, 2, 10, 'key088', 'Document chiffré 88'),
('2025-01-11 09:19:53', '2025-01-21 09:19:53', 6, 3, 17, 'key089', 'Document chiffré 89'),
('2025-02-07 23:25:23', '2025-02-17 23:25:23', 2, 2, 24, 'key090', 'Document chiffré 90'),
('2025-01-06 17:23:33', '2025-01-16 17:23:33', 20, 3, 24, 'key091', 'Document chiffré 91'),
('2025-01-01 08:35:58', '2025-01-11 08:35:58', 14, 1, 24, 'key092', 'Document chiffré 92'),
('2025-01-09 03:03:46', '2025-01-19 03:03:46', 4, 4, 15, 'key093', 'Document chiffré 93'),
('2025-01-27 09:19:25', '2025-02-06 09:19:25', 12, 2, 3, 'key094', 'Document chiffré 94'),
('2025-01-12 18:18:26', '2025-01-22 18:18:26', 17, 2, 22, 'key095', 'Document chiffré 95'),
('2025-01-30 00:10:13', '2025-02-09 00:10:13', 8, 4, 23, 'key096', 'Document chiffré 96'),
('2025-01-30 09:41:15', '2025-02-09 09:41:15', 3, 3, 6, 'key097', 'Document chiffré 97'),
('2025-02-06 09:13:43', '2025-02-16 09:13:43', 5, 3, 20, 'key098', 'Document chiffré 98'),
('2025-02-03 09:01:08', '2025-02-13 09:01:08', 2, 3, 11, 'key099', 'Document chiffré 99'),
('2025-01-11 07:42:00', '2025-01-21 07:42:00', 13, 2, 18, 'key100', 'Document chiffré 100');

-- Insertion des données dans la table "notification"
INSERT IGNORE INTO notification (date_notification, id_type_notification, id_utilisateur, statut_notification, contenu_notification) VALUES
('2025-02-18 10:00:00', 1, 1, 'EN_ATTENTE', 'Nouvelle inscription à valider'),
('2025-02-18 11:00:00', 2, 2, 'ACCEPTE', 'Votre dossier a été validé'),
('2025-02-18 12:00:00', 1, 3, 'EN_ATTENTE', 'Inscription en attente de validation'),
('2025-02-18 13:00:00', 2, 4, 'REFUSE', 'Votre dossier a été refusé'),
('2025-02-18 14:00:00', 1, 5, 'EN_ATTENTE', 'Nouvelle candidature reçue'),
('2025-02-18 15:00:00', 2, 6, 'ACCEPTE', 'Votre formation est confirmée'),
('2025-02-18 16:00:00', 1, 7, 'EN_ATTENTE', 'Vérification des documents en cours'),
('2025-02-18 17:00:00', 2, 8, 'ACCEPTE', 'Paiement validé, accès débloqué'),
('2025-02-18 18:00:00', 1, 9, 'EN_ATTENTE', 'Attente de validation par l administration'),
('2025-02-18 19:00:00', 2, 10, 'REFUSE', 'Votre candidature ne correspond pas aux critères');

-- Insertion des données dans la table "type_notification"
INSERT IGNORE INTO type_notification (nom_type_notification) VALUES
('Inscription'),
('Validation de dossier');

-- Insertion des données dans la table "justificatif"
INSERT IGNORE INTO justificatif (date_depot_justificatif, id_absence, id_statut, type_document_justificatif) VALUES
('2025-02-02 10:00:00', 1, 1, 'Certificat médical'),
('2025-02-03 11:00:00', 2, 2, 'Justificatif de congé'),
('2025-02-04 12:00:00', 3, 1, 'Certificat médical'),
('2025-02-05 13:00:00', 4, 2, 'Attestation d urgence'),
('2025-02-06 14:00:00', 5, 1, 'Rapport d hospitalisation'),
('2025-02-07 15:00:00', 6, 2, 'Justificatif familial'),
('2025-02-08 16:00:00', 7, 1, 'Lettre d absence signée'),
('2025-02-09 17:00:00', 8, 2, 'Document officiel d absence'),
('2025-02-10 18:00:00', 9, 1, 'Justificatif médical pour enfant'),
('2025-02-11 19:00:00', 10, 2, 'Lettre de convocation');


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
(2, 2, 1),
(3, 3, 0),
(4, 1, 1),
(5, 2, 1),
(6, 3, 0),
(7, 1, 1),
(8, 2, 1),
(9, 3, 0),
(10, 1, 1);

-- Insertion des données dans la table "secret"
INSERT IGNORE INTO secret (nom_secret, complete_hash_secret, date_creation_secret, description_secret, statut_secret) VALUES
('Secret 1', 'hashvalue123', '2025-02-18 10:00:00','Description du secret 1', 'ACTIF'),
('Secret 2', 'hashvalue456', '2025-02-18 11:00:00','Description du secret 2', 'INACTIF'),
('Secret 3', 'hashvalue789', '2025-02-18 12:00:00','Description du secret 3', 'ACTIF'),
('Secret 4', 'hashvalue321', '2025-02-18 13:00:00','Description du secret 4', 'ACTIF'),
('Secret 5', 'hashvalue654', '2025-02-18 14:00:00','Description du secret 5', 'INACTIF'),
('Secret 6', 'hashvalue987', '2025-02-18 15:00:00','Description du secret 6', 'ACTIF'),
('Secret 7', 'hashvalue741', '2025-02-18 16:00:00','Description du secret 7', 'ACTIF'),
('Secret 8', 'hashvalue852', '2025-02-18 17:00:00','Description du secret 8', 'INACTIF'),
('Secret 9', 'hashvalue963', '2025-02-18 18:00:00','Description du secret 9', 'ACTIF'),
('Secret 10', 'hashvalue159', '2025-02-18 19:00:00','Description du secret 10', 'ACTIF');

-- Insertion des données dans la table "part_secret"
INSERT IGNORE INTO part_secret (date_creation_part_secret, id_secret, id_utilisateur, statut_part_secret, part_part_secret) VALUES
('2025-02-18 10:00:00', 1, 1, 'ACTIF', 'Partie secrète 1'),
('2025-02-18 11:00:00', 2, 2, 'INACTIF', 'Partie secrète 2'),
('2025-02-18 12:00:00', 3, 3, 'ACTIF', 'Partie secrète 3'),
('2025-02-18 13:00:00', 4, 4, 'ACTIF', 'Partie secrète 4'),
('2025-02-18 14:00:00', 5, 5, 'INACTIF', 'Partie secrète 5'),
('2025-02-18 15:00:00', 6, 6, 'ACTIF', 'Partie secrète 6'),
('2025-02-18 16:00:00', 7, 7, 'ACTIF', 'Partie secrète 7'),
('2025-02-18 17:00:00', 8, 8, 'INACTIF', 'Partie secrète 8'),
('2025-02-18 18:00:00', 9, 9, 'ACTIF', 'Partie secrète 9'),
('2025-02-18 19:00:00', 10, 10, 'ACTIF', 'Partie secrète 10');


SET FOREIGN_KEY_CHECKS = 1;