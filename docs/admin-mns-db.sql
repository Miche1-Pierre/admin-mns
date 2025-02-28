-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 28 fév. 2025 à 15:53
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `admin-mns-db`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `justifie_absence` bit(1) NOT NULL,
  `date_debut_absence` datetime(6) NOT NULL,
  `date_fin_absence` datetime(6) DEFAULT NULL,
  `id_absence` bigint NOT NULL AUTO_INCREMENT,
  `id_stagiaire` bigint NOT NULL,
  `id_type_absence` bigint NOT NULL,
  `statut_absence` varchar(255) NOT NULL,
  PRIMARY KEY (`id_absence`),
  KEY `FKs2hdpvy73p9d53fylwhvn6532` (`id_stagiaire`),
  KEY `FKg4r3fy4pne5t798vicpi9oov7` (`id_type_absence`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`justifie_absence`, `date_debut_absence`, `date_fin_absence`, `id_absence`, `id_stagiaire`, `id_type_absence`, `statut_absence`) VALUES
(b'1', '2025-01-01 08:00:00.000000', '2025-01-01 17:00:00.000000', 1, 2, 1, 'Retard'),
(b'0', '2025-01-02 08:00:00.000000', '2025-01-02 17:00:00.000000', 2, 3, 2, 'Retard'),
(b'1', '2025-01-03 08:00:00.000000', '2025-01-03 17:00:00.000000', 3, 4, 1, 'Retard'),
(b'0', '2025-01-04 08:00:00.000000', '2025-01-04 17:00:00.000000', 4, 5, 2, 'Retard'),
(b'1', '2025-01-05 08:00:00.000000', '2025-01-05 17:00:00.000000', 5, 6, 1, 'Retard'),
(b'0', '2025-02-06 08:00:00.000000', '2025-02-06 17:00:00.000000', 6, 7, 2, 'Retard'),
(b'1', '2025-02-07 08:00:00.000000', '2025-02-07 17:00:00.000000', 7, 8, 1, 'Retard'),
(b'0', '2025-02-08 08:00:00.000000', '2025-02-08 17:00:00.000000', 8, 9, 2, 'Retard'),
(b'1', '2025-02-09 08:00:00.000000', '2025-02-09 17:00:00.000000', 9, 10, 1, 'Absence'),
(b'0', '2025-02-10 08:00:00.000000', '2025-02-10 17:00:00.000000', 10, 11, 2, 'Absence'),
(b'1', '2025-02-11 08:00:00.000000', '2025-02-11 17:00:00.000000', 11, 12, 1, 'Absence'),
(b'0', '2025-02-12 08:00:00.000000', '2025-02-12 17:00:00.000000', 12, 13, 2, 'Absence'),
(b'1', '2025-02-13 08:00:00.000000', '2025-02-13 17:00:00.000000', 13, 14, 1, 'Absence'),
(b'0', '2025-02-14 08:00:00.000000', '2025-02-14 17:00:00.000000', 14, 15, 2, 'Absence'),
(b'1', '2025-02-15 08:00:00.000000', '2025-02-15 17:00:00.000000', 15, 16, 1, 'Absence'),
(b'0', '2025-02-16 08:00:00.000000', '2025-02-16 17:00:00.000000', 16, 17, 2, 'Absence'),
(b'1', '2025-02-17 08:00:00.000000', '2025-02-17 17:00:00.000000', 17, 18, 1, 'Absence'),
(b'0', '2025-02-18 08:00:00.000000', '2025-02-18 17:00:00.000000', 18, 19, 2, 'Absence'),
(b'1', '2025-02-19 08:00:00.000000', '2025-02-19 17:00:00.000000', 19, 20, 1, 'Retard'),
(b'0', '2025-02-20 08:00:00.000000', '2025-02-20 17:00:00.000000', 20, 21, 2, 'Retard'),
(b'1', '2025-02-21 08:00:00.000000', '2025-02-21 17:00:00.000000', 21, 22, 1, 'Retard'),
(b'0', '2025-02-22 08:00:00.000000', '2025-02-22 17:00:00.000000', 22, 23, 2, 'Retard'),
(b'1', '2025-02-23 08:00:00.000000', '2025-02-23 17:00:00.000000', 23, 24, 1, 'Retard'),
(b'0', '2025-02-24 08:00:00.000000', '2025-02-24 17:00:00.000000', 24, 25, 2, 'Retard'),
(b'1', '2025-02-25 08:00:00.000000', '2025-02-25 17:00:00.000000', 25, 26, 1, 'Absence'),
(b'0', '2025-02-26 08:00:00.000000', '2025-02-26 17:00:00.000000', 26, 27, 2, 'Absence'),
(b'1', '2025-02-27 08:00:00.000000', '2025-02-27 17:00:00.000000', 27, 28, 1, 'Absence'),
(b'0', '2025-02-28 08:00:00.000000', '2025-02-28 17:00:00.000000', 28, 29, 2, 'Absence'),
(b'1', '2025-03-01 08:00:00.000000', '2025-03-01 17:00:00.000000', 29, 30, 1, 'Absence'),
(b'0', '2025-03-02 08:00:00.000000', '2025-03-02 17:00:00.000000', 30, 31, 2, 'Retard'),
(b'1', '2025-03-03 08:00:00.000000', '2025-03-03 17:00:00.000000', 31, 32, 1, 'Retard'),
(b'0', '2025-03-04 08:00:00.000000', '2025-03-04 17:00:00.000000', 32, 33, 2, 'Retard'),
(b'1', '2025-03-05 08:00:00.000000', '2025-03-05 17:00:00.000000', 33, 34, 1, 'Retard'),
(b'0', '2025-03-06 08:00:00.000000', '2025-03-06 17:00:00.000000', 34, 35, 2, 'Retard'),
(b'1', '2025-03-07 08:00:00.000000', '2025-03-07 17:00:00.000000', 35, 36, 1, 'Retard'),
(b'0', '2025-03-08 08:00:00.000000', '2025-03-08 17:00:00.000000', 36, 37, 2, 'Retard'),
(b'1', '2025-03-09 08:00:00.000000', '2025-03-09 17:00:00.000000', 37, 38, 1, 'Retard'),
(b'0', '2025-03-10 08:00:00.000000', '2025-03-10 17:00:00.000000', 38, 39, 2, 'Retard'),
(b'1', '2025-03-11 08:00:00.000000', '2025-03-11 17:00:00.000000', 39, 40, 1, 'Retard'),
(b'0', '2025-03-12 08:00:00.000000', '2025-03-12 17:00:00.000000', 40, 41, 2, 'Retard'),
(b'1', '2025-03-13 08:00:00.000000', '2025-03-13 17:00:00.000000', 41, 42, 1, 'Retard'),
(b'0', '2025-03-14 08:00:00.000000', '2025-03-14 17:00:00.000000', 42, 43, 2, 'Retard'),
(b'1', '2025-03-15 08:00:00.000000', '2025-03-15 17:00:00.000000', 43, 44, 1, 'Retard'),
(b'0', '2025-03-16 08:00:00.000000', '2025-03-16 17:00:00.000000', 44, 45, 2, 'Retard'),
(b'1', '2025-03-17 08:00:00.000000', '2025-03-17 17:00:00.000000', 45, 46, 1, 'Retard'),
(b'0', '2025-03-18 08:00:00.000000', '2025-03-18 17:00:00.000000', 46, 47, 2, 'Retard'),
(b'1', '2025-03-19 08:00:00.000000', '2025-03-19 17:00:00.000000', 47, 48, 1, 'Retard'),
(b'0', '2025-03-20 08:00:00.000000', '2025-03-20 17:00:00.000000', 48, 49, 2, 'Retard'),
(b'1', '2025-03-21 08:00:00.000000', '2025-03-21 17:00:00.000000', 49, 50, 1, 'Retard');

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `date_depot_document` datetime(6) NOT NULL,
  `date_limite_document` datetime(6) DEFAULT NULL,
  `id_document` bigint NOT NULL AUTO_INCREMENT,
  `id_dossier` bigint NOT NULL,
  `id_secret` bigint DEFAULT NULL,
  `id_statut` bigint NOT NULL,
  `id_type_document` bigint NOT NULL,
  `cle_chiffrement_document` varchar(255) NOT NULL,
  `contenu_chiffre_document` tinyblob NOT NULL,
  PRIMARY KEY (`id_document`),
  KEY `FK62eikoxh114ldyv7t6xkf2usn` (`id_dossier`),
  KEY `FKpnymn2radnb9not4eb5q0bhjg` (`id_secret`),
  KEY `FKn8qfw0ot70d510ai4xgn9jyw5` (`id_statut`),
  KEY `FK9bmhlm70xd6kmypoglxrun2qj` (`id_type_document`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`date_depot_document`, `date_limite_document`, `id_document`, `id_dossier`, `id_secret`, `id_statut`, `id_type_document`, `cle_chiffrement_document`, `contenu_chiffre_document`) VALUES
('2025-01-31 11:04:23.000000', '2025-02-10 11:04:23.000000', 1, 11, NULL, 4, 19, 'key001', 0x446f63756d656e7420636869666672c3a92031),
('2025-01-04 08:25:59.000000', '2025-01-14 08:25:59.000000', 2, 18, NULL, 1, 24, 'key002', 0x446f63756d656e7420636869666672c3a92032),
('2025-01-15 08:05:33.000000', '2025-01-25 08:05:33.000000', 3, 12, NULL, 3, 7, 'key003', 0x446f63756d656e7420636869666672c3a92033),
('2025-01-21 22:55:52.000000', '2025-01-31 22:55:52.000000', 4, 10, NULL, 1, 6, 'key004', 0x446f63756d656e7420636869666672c3a92034),
('2025-01-29 14:12:58.000000', '2025-02-08 14:12:58.000000', 5, 1, NULL, 3, 1, 'key005', 0x446f63756d656e7420636869666672c3a92035),
('2025-01-03 08:25:08.000000', '2025-01-13 08:25:08.000000', 6, 4, NULL, 1, 20, 'key006', 0x446f63756d656e7420636869666672c3a92036),
('2025-02-07 01:57:24.000000', '2025-02-17 01:57:24.000000', 7, 11, NULL, 4, 23, 'key007', 0x446f63756d656e7420636869666672c3a92037),
('2025-01-12 22:05:01.000000', '2025-01-22 22:05:01.000000', 8, 18, NULL, 3, 5, 'key008', 0x446f63756d656e7420636869666672c3a92038),
('2025-01-25 14:36:59.000000', '2025-02-04 14:36:59.000000', 9, 4, NULL, 1, 7, 'key009', 0x446f63756d656e7420636869666672c3a92039),
('2025-01-12 14:39:25.000000', '2025-01-22 14:39:25.000000', 10, 12, NULL, 1, 8, 'key010', 0x446f63756d656e7420636869666672c3a9203130),
('2025-01-11 02:52:10.000000', '2025-01-21 02:52:10.000000', 11, 17, NULL, 2, 9, 'key011', 0x446f63756d656e7420636869666672c3a9203131),
('2025-01-06 00:36:41.000000', '2025-01-16 00:36:41.000000', 12, 6, NULL, 1, 18, 'key012', 0x446f63756d656e7420636869666672c3a9203132),
('2025-01-10 23:37:29.000000', '2025-01-20 23:37:29.000000', 13, 6, NULL, 3, 12, 'key013', 0x446f63756d656e7420636869666672c3a9203133),
('2025-01-01 17:56:57.000000', '2025-01-11 17:56:57.000000', 14, 19, NULL, 3, 11, 'key014', 0x446f63756d656e7420636869666672c3a9203134),
('2025-02-01 05:21:14.000000', '2025-02-11 05:21:14.000000', 15, 3, NULL, 2, 24, 'key015', 0x446f63756d656e7420636869666672c3a9203135),
('2025-01-11 11:17:19.000000', '2025-01-21 11:17:19.000000', 16, 11, NULL, 3, 5, 'key016', 0x446f63756d656e7420636869666672c3a9203136),
('2025-02-03 22:25:38.000000', '2025-02-13 22:25:38.000000', 17, 14, NULL, 1, 6, 'key017', 0x446f63756d656e7420636869666672c3a9203137),
('2025-01-05 02:06:10.000000', '2025-01-15 02:06:10.000000', 18, 14, NULL, 4, 8, 'key018', 0x446f63756d656e7420636869666672c3a9203138),
('2025-02-01 23:00:58.000000', '2025-02-11 23:00:58.000000', 19, 10, NULL, 1, 15, 'key019', 0x446f63756d656e7420636869666672c3a9203139),
('2025-01-27 15:16:04.000000', '2025-02-06 15:16:04.000000', 20, 6, NULL, 2, 2, 'key020', 0x446f63756d656e7420636869666672c3a9203230),
('2025-01-27 12:51:50.000000', '2025-02-06 12:51:50.000000', 21, 12, NULL, 2, 1, 'key021', 0x446f63756d656e7420636869666672c3a9203231),
('2025-02-07 03:08:23.000000', '2025-02-17 03:08:23.000000', 22, 4, NULL, 3, 21, 'key022', 0x446f63756d656e7420636869666672c3a9203232),
('2025-01-24 04:01:20.000000', '2025-02-03 04:01:20.000000', 23, 7, NULL, 4, 9, 'key023', 0x446f63756d656e7420636869666672c3a9203233),
('2025-02-05 21:48:28.000000', '2025-02-15 21:48:28.000000', 24, 1, NULL, 4, 21, 'key024', 0x446f63756d656e7420636869666672c3a9203234),
('2025-01-10 05:09:14.000000', '2025-01-20 05:09:14.000000', 25, 7, NULL, 3, 17, 'key025', 0x446f63756d656e7420636869666672c3a9203235),
('2025-01-09 09:58:49.000000', '2025-01-19 09:58:49.000000', 26, 15, NULL, 3, 22, 'key026', 0x446f63756d656e7420636869666672c3a9203236),
('2025-01-11 05:07:18.000000', '2025-01-21 05:07:18.000000', 27, 16, NULL, 2, 2, 'key027', 0x446f63756d656e7420636869666672c3a9203237),
('2025-01-05 11:43:30.000000', '2025-01-15 11:43:30.000000', 28, 18, NULL, 1, 20, 'key028', 0x446f63756d656e7420636869666672c3a9203238),
('2025-01-04 15:55:37.000000', '2025-01-14 15:55:37.000000', 29, 4, NULL, 4, 11, 'key029', 0x446f63756d656e7420636869666672c3a9203239),
('2025-01-20 03:19:03.000000', '2025-01-30 03:19:03.000000', 30, 13, NULL, 4, 4, 'key030', 0x446f63756d656e7420636869666672c3a9203330),
('2025-01-03 09:53:33.000000', '2025-01-13 09:53:33.000000', 31, 17, NULL, 2, 5, 'key031', 0x446f63756d656e7420636869666672c3a9203331),
('2025-01-10 18:39:54.000000', '2025-01-20 18:39:54.000000', 32, 15, NULL, 2, 18, 'key032', 0x446f63756d656e7420636869666672c3a9203332),
('2025-02-02 04:15:35.000000', '2025-02-12 04:15:35.000000', 33, 16, NULL, 4, 11, 'key033', 0x446f63756d656e7420636869666672c3a9203333),
('2025-01-15 12:44:50.000000', '2025-01-25 12:44:50.000000', 34, 13, NULL, 1, 21, 'key034', 0x446f63756d656e7420636869666672c3a9203334),
('2025-01-31 11:44:17.000000', '2025-02-10 11:44:17.000000', 35, 13, NULL, 2, 15, 'key035', 0x446f63756d656e7420636869666672c3a9203335),
('2025-01-22 00:55:15.000000', '2025-02-01 00:55:15.000000', 36, 11, NULL, 1, 12, 'key036', 0x446f63756d656e7420636869666672c3a9203336),
('2025-01-05 03:54:43.000000', '2025-01-15 03:54:43.000000', 37, 3, NULL, 4, 2, 'key037', 0x446f63756d656e7420636869666672c3a9203337),
('2025-01-24 19:23:05.000000', '2025-02-03 19:23:05.000000', 38, 18, NULL, 4, 4, 'key038', 0x446f63756d656e7420636869666672c3a9203338),
('2025-01-27 00:33:15.000000', '2025-02-06 00:33:15.000000', 39, 15, NULL, 1, 1, 'key039', 0x446f63756d656e7420636869666672c3a9203339),
('2025-01-31 04:46:00.000000', '2025-02-10 04:46:00.000000', 40, 8, NULL, 1, 13, 'key040', 0x446f63756d656e7420636869666672c3a9203430),
('2025-01-28 04:19:02.000000', '2025-02-07 04:19:02.000000', 41, 4, NULL, 2, 5, 'key041', 0x446f63756d656e7420636869666672c3a9203431),
('2025-01-16 22:44:50.000000', '2025-01-26 22:44:50.000000', 42, 14, NULL, 3, 19, 'key042', 0x446f63756d656e7420636869666672c3a9203432),
('2025-02-09 12:56:17.000000', '2025-02-19 12:56:17.000000', 43, 8, NULL, 1, 20, 'key043', 0x446f63756d656e7420636869666672c3a9203433),
('2025-01-20 01:26:04.000000', '2025-01-30 01:26:04.000000', 44, 1, NULL, 2, 18, 'key044', 0x446f63756d656e7420636869666672c3a9203434),
('2025-01-08 09:47:00.000000', '2025-01-18 09:47:00.000000', 45, 5, NULL, 4, 25, 'key045', 0x446f63756d656e7420636869666672c3a9203435),
('2025-02-03 12:47:17.000000', '2025-02-13 12:47:17.000000', 46, 15, NULL, 2, 19, 'key046', 0x446f63756d656e7420636869666672c3a9203436),
('2025-01-23 17:42:46.000000', '2025-02-02 17:42:46.000000', 47, 15, NULL, 2, 3, 'key047', 0x446f63756d656e7420636869666672c3a9203437),
('2025-01-21 08:33:17.000000', '2025-01-31 08:33:17.000000', 48, 1, NULL, 4, 4, 'key048', 0x446f63756d656e7420636869666672c3a9203438),
('2025-01-03 21:48:07.000000', '2025-01-13 21:48:07.000000', 49, 19, NULL, 2, 22, 'key049', 0x446f63756d656e7420636869666672c3a9203439),
('2025-01-31 00:18:57.000000', '2025-02-10 00:18:57.000000', 50, 20, NULL, 3, 15, 'key050', 0x446f63756d656e7420636869666672c3a9203530),
('2025-01-19 06:14:43.000000', '2025-01-29 06:14:43.000000', 51, 2, NULL, 4, 18, 'key051', 0x446f63756d656e7420636869666672c3a9203531),
('2025-01-23 04:53:34.000000', '2025-02-02 04:53:34.000000', 52, 10, NULL, 4, 2, 'key052', 0x446f63756d656e7420636869666672c3a9203532),
('2025-01-16 08:15:36.000000', '2025-01-26 08:15:36.000000', 53, 15, NULL, 1, 14, 'key053', 0x446f63756d656e7420636869666672c3a9203533),
('2025-01-24 22:27:24.000000', '2025-02-03 22:27:24.000000', 54, 4, NULL, 4, 15, 'key054', 0x446f63756d656e7420636869666672c3a9203534),
('2025-01-11 05:14:08.000000', '2025-01-21 05:14:08.000000', 55, 13, NULL, 4, 4, 'key055', 0x446f63756d656e7420636869666672c3a9203535),
('2025-01-15 00:48:06.000000', '2025-01-25 00:48:06.000000', 56, 8, NULL, 4, 6, 'key056', 0x446f63756d656e7420636869666672c3a9203536),
('2025-01-31 00:27:32.000000', '2025-02-10 00:27:32.000000', 57, 4, NULL, 2, 22, 'key057', 0x446f63756d656e7420636869666672c3a9203537),
('2025-01-22 10:43:24.000000', '2025-02-01 10:43:24.000000', 58, 10, NULL, 3, 4, 'key058', 0x446f63756d656e7420636869666672c3a9203538),
('2025-01-10 13:10:20.000000', '2025-01-20 13:10:20.000000', 59, 20, NULL, 3, 20, 'key059', 0x446f63756d656e7420636869666672c3a9203539),
('2025-01-26 16:04:35.000000', '2025-02-05 16:04:35.000000', 60, 4, NULL, 1, 18, 'key060', 0x446f63756d656e7420636869666672c3a9203630),
('2025-01-28 00:09:58.000000', '2025-02-07 00:09:58.000000', 61, 8, NULL, 3, 18, 'key061', 0x446f63756d656e7420636869666672c3a9203631),
('2025-01-27 05:50:04.000000', '2025-02-06 05:50:04.000000', 62, 1, NULL, 4, 25, 'key062', 0x446f63756d656e7420636869666672c3a9203632),
('2025-02-05 21:24:58.000000', '2025-02-15 21:24:58.000000', 63, 12, NULL, 1, 6, 'key063', 0x446f63756d656e7420636869666672c3a9203633),
('2025-01-10 06:15:46.000000', '2025-01-20 06:15:46.000000', 64, 4, NULL, 3, 16, 'key064', 0x446f63756d656e7420636869666672c3a9203634),
('2025-01-09 23:06:31.000000', '2025-01-19 23:06:31.000000', 65, 13, NULL, 2, 25, 'key065', 0x446f63756d656e7420636869666672c3a9203635),
('2025-01-19 17:50:08.000000', '2025-01-29 17:50:08.000000', 66, 6, NULL, 2, 17, 'key066', 0x446f63756d656e7420636869666672c3a9203636),
('2025-02-06 23:02:12.000000', '2025-02-16 23:02:12.000000', 67, 20, NULL, 1, 10, 'key067', 0x446f63756d656e7420636869666672c3a9203637),
('2025-01-31 19:22:31.000000', '2025-02-10 19:22:31.000000', 68, 20, NULL, 4, 3, 'key068', 0x446f63756d656e7420636869666672c3a9203638),
('2025-01-11 11:17:46.000000', '2025-01-21 11:17:46.000000', 69, 3, NULL, 3, 7, 'key069', 0x446f63756d656e7420636869666672c3a9203639),
('2025-01-15 10:14:11.000000', '2025-01-25 10:14:11.000000', 70, 12, NULL, 3, 11, 'key070', 0x446f63756d656e7420636869666672c3a9203730),
('2025-01-20 15:42:37.000000', '2025-01-30 15:42:37.000000', 71, 5, NULL, 2, 23, 'key071', 0x446f63756d656e7420636869666672c3a9203731),
('2025-02-01 04:42:06.000000', '2025-02-11 04:42:06.000000', 72, 1, NULL, 1, 14, 'key072', 0x446f63756d656e7420636869666672c3a9203732),
('2025-01-06 14:03:52.000000', '2025-01-16 14:03:52.000000', 73, 14, NULL, 4, 18, 'key073', 0x446f63756d656e7420636869666672c3a9203733),
('2025-01-10 05:12:57.000000', '2025-01-20 05:12:57.000000', 74, 11, NULL, 3, 24, 'key074', 0x446f63756d656e7420636869666672c3a9203734),
('2025-02-08 17:07:14.000000', '2025-02-18 17:07:14.000000', 75, 17, NULL, 3, 7, 'key075', 0x446f63756d656e7420636869666672c3a9203735),
('2025-01-27 11:10:09.000000', '2025-02-06 11:10:09.000000', 76, 12, NULL, 2, 24, 'key076', 0x446f63756d656e7420636869666672c3a9203736),
('2025-01-14 05:30:08.000000', '2025-01-24 05:30:08.000000', 77, 11, NULL, 4, 17, 'key077', 0x446f63756d656e7420636869666672c3a9203737),
('2025-01-05 10:24:39.000000', '2025-01-15 10:24:39.000000', 78, 8, NULL, 1, 19, 'key078', 0x446f63756d656e7420636869666672c3a9203738),
('2025-01-31 21:50:57.000000', '2025-02-10 21:50:57.000000', 79, 9, NULL, 4, 10, 'key079', 0x446f63756d656e7420636869666672c3a9203739),
('2025-01-29 01:38:01.000000', '2025-02-08 01:38:01.000000', 80, 6, NULL, 2, 16, 'key080', 0x446f63756d656e7420636869666672c3a9203830),
('2025-01-23 23:45:18.000000', '2025-02-02 23:45:18.000000', 81, 20, NULL, 3, 24, 'key081', 0x446f63756d656e7420636869666672c3a9203831),
('2025-01-09 12:44:53.000000', '2025-01-19 12:44:53.000000', 82, 7, NULL, 1, 1, 'key082', 0x446f63756d656e7420636869666672c3a9203832),
('2025-01-15 01:42:44.000000', '2025-01-25 01:42:44.000000', 83, 19, NULL, 2, 15, 'key083', 0x446f63756d656e7420636869666672c3a9203833),
('2025-02-03 08:12:00.000000', '2025-02-13 08:12:00.000000', 84, 15, NULL, 3, 7, 'key084', 0x446f63756d656e7420636869666672c3a9203834),
('2025-02-04 18:25:44.000000', '2025-02-14 18:25:44.000000', 85, 11, NULL, 4, 18, 'key085', 0x446f63756d656e7420636869666672c3a9203835),
('2025-01-08 10:58:58.000000', '2025-01-18 10:58:58.000000', 86, 5, NULL, 4, 9, 'key086', 0x446f63756d656e7420636869666672c3a9203836),
('2025-01-02 02:50:15.000000', '2025-01-12 02:50:15.000000', 87, 18, NULL, 4, 22, 'key087', 0x446f63756d656e7420636869666672c3a9203837),
('2025-01-16 07:51:41.000000', '2025-01-26 07:51:41.000000', 88, 14, NULL, 2, 10, 'key088', 0x446f63756d656e7420636869666672c3a9203838),
('2025-01-11 09:19:53.000000', '2025-01-21 09:19:53.000000', 89, 6, NULL, 3, 17, 'key089', 0x446f63756d656e7420636869666672c3a9203839),
('2025-02-07 23:25:23.000000', '2025-02-17 23:25:23.000000', 90, 2, NULL, 2, 24, 'key090', 0x446f63756d656e7420636869666672c3a9203930),
('2025-01-06 17:23:33.000000', '2025-01-16 17:23:33.000000', 91, 20, NULL, 3, 24, 'key091', 0x446f63756d656e7420636869666672c3a9203931),
('2025-01-01 08:35:58.000000', '2025-01-11 08:35:58.000000', 92, 14, NULL, 1, 24, 'key092', 0x446f63756d656e7420636869666672c3a9203932),
('2025-01-09 03:03:46.000000', '2025-01-19 03:03:46.000000', 93, 4, NULL, 4, 15, 'key093', 0x446f63756d656e7420636869666672c3a9203933),
('2025-01-27 09:19:25.000000', '2025-02-06 09:19:25.000000', 94, 12, NULL, 2, 3, 'key094', 0x446f63756d656e7420636869666672c3a9203934),
('2025-01-12 18:18:26.000000', '2025-01-22 18:18:26.000000', 95, 17, NULL, 2, 22, 'key095', 0x446f63756d656e7420636869666672c3a9203935),
('2025-01-30 00:10:13.000000', '2025-02-09 00:10:13.000000', 96, 8, NULL, 4, 23, 'key096', 0x446f63756d656e7420636869666672c3a9203936),
('2025-01-30 09:41:15.000000', '2025-02-09 09:41:15.000000', 97, 3, NULL, 3, 6, 'key097', 0x446f63756d656e7420636869666672c3a9203937),
('2025-02-06 09:13:43.000000', '2025-02-16 09:13:43.000000', 98, 5, NULL, 3, 20, 'key098', 0x446f63756d656e7420636869666672c3a9203938),
('2025-02-03 09:01:08.000000', '2025-02-13 09:01:08.000000', 99, 2, NULL, 3, 11, 'key099', 0x446f63756d656e7420636869666672c3a9203939),
('2025-01-11 07:42:00.000000', '2025-01-21 07:42:00.000000', 100, 13, NULL, 2, 18, 'key100', 0x446f63756d656e7420636869666672c3a920313030);

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

DROP TABLE IF EXISTS `dossier`;
CREATE TABLE IF NOT EXISTS `dossier` (
  `date_creation_dossier` datetime(6) NOT NULL,
  `date_validation_dossier` datetime(6) DEFAULT NULL,
  `id_dossier` bigint NOT NULL AUTO_INCREMENT,
  `id_stagiaire` bigint NOT NULL,
  `id_statut` bigint NOT NULL,
  PRIMARY KEY (`id_dossier`),
  KEY `FKa4k6lher1yfqvhbraqot5q2uv` (`id_stagiaire`),
  KEY `FKslgox4j1wnoap58wa6pbmvlyq` (`id_statut`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `dossier`
--

INSERT INTO `dossier` (`date_creation_dossier`, `date_validation_dossier`, `id_dossier`, `id_stagiaire`, `id_statut`) VALUES
('2025-02-10 10:00:00.000000', '2025-02-12 10:00:00.000000', 1, 2, 1),
('2025-02-11 10:00:00.000000', '2025-02-13 10:00:00.000000', 2, 3, 2),
('2025-02-12 10:00:00.000000', '2025-02-14 10:00:00.000000', 3, 4, 1),
('2025-02-13 10:00:00.000000', '2025-02-15 10:00:00.000000', 4, 5, 2),
('2025-02-14 10:00:00.000000', '2025-02-16 10:00:00.000000', 5, 6, 1),
('2025-02-15 10:00:00.000000', '2025-02-17 10:00:00.000000', 6, 7, 2),
('2025-02-16 10:00:00.000000', '2025-02-18 10:00:00.000000', 7, 8, 1),
('2025-02-17 10:00:00.000000', '2025-02-19 10:00:00.000000', 8, 9, 2),
('2025-02-18 10:00:00.000000', '2025-02-20 10:00:00.000000', 9, 10, 1),
('2025-02-19 10:00:00.000000', '2025-02-21 10:00:00.000000', 10, 11, 2),
('2025-02-20 10:00:00.000000', '2025-02-22 10:00:00.000000', 11, 12, 1),
('2025-02-21 10:00:00.000000', '2025-02-23 10:00:00.000000', 12, 13, 2),
('2025-02-22 10:00:00.000000', '2025-02-24 10:00:00.000000', 13, 14, 1),
('2025-02-23 10:00:00.000000', '2025-02-25 10:00:00.000000', 14, 15, 2),
('2025-02-24 10:00:00.000000', '2025-02-26 10:00:00.000000', 15, 16, 1),
('2025-02-25 10:00:00.000000', '2025-02-27 10:00:00.000000', 16, 17, 2),
('2025-02-26 10:00:00.000000', '2025-02-28 10:00:00.000000', 17, 18, 1),
('2025-02-27 10:00:00.000000', '2025-03-01 10:00:00.000000', 18, 19, 2),
('2025-02-28 10:00:00.000000', '2025-03-02 10:00:00.000000', 19, 20, 1),
('2025-03-01 10:00:00.000000', '2025-03-03 10:00:00.000000', 20, 21, 2),
('2025-03-02 10:00:00.000000', '2025-03-04 10:00:00.000000', 21, 22, 1),
('2025-03-03 10:00:00.000000', '2025-03-05 10:00:00.000000', 22, 23, 2),
('2025-03-04 10:00:00.000000', '2025-03-06 10:00:00.000000', 23, 24, 1),
('2025-03-05 10:00:00.000000', '2025-03-07 10:00:00.000000', 24, 25, 2),
('2025-03-06 10:00:00.000000', '2025-03-08 10:00:00.000000', 25, 26, 1),
('2025-03-07 10:00:00.000000', '2025-03-09 10:00:00.000000', 26, 27, 2),
('2025-03-08 10:00:00.000000', '2025-03-10 10:00:00.000000', 27, 28, 1),
('2025-03-09 10:00:00.000000', '2025-03-11 10:00:00.000000', 28, 29, 2),
('2025-03-10 10:00:00.000000', '2025-03-12 10:00:00.000000', 29, 30, 1),
('2025-03-11 10:00:00.000000', '2025-03-13 10:00:00.000000', 30, 31, 2),
('2025-03-12 10:00:00.000000', '2025-03-14 10:00:00.000000', 31, 32, 1);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

DROP TABLE IF EXISTS `formation`;
CREATE TABLE IF NOT EXISTS `formation` (
  `duree_formation` int NOT NULL,
  `date_debut_formation` datetime(6) NOT NULL,
  `date_fin_formation` datetime(6) NOT NULL,
  `id_formation` bigint NOT NULL AUTO_INCREMENT,
  `nom_formation` varchar(100) NOT NULL,
  `description_formation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_formation`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`duree_formation`, `date_debut_formation`, `date_fin_formation`, `id_formation`, `nom_formation`, `description_formation`) VALUES
(3, '2025-09-01 09:00:00.000000', '2025-03-05 17:00:00.000000', 1, 'BSD', 'Formation complète en développement Java'),
(3, '2025-09-01 09:00:00.000000', '2025-04-03 17:00:00.000000', 2, 'BSRC', 'Formation sur le framework Spring Boot'),
(1, '2025-09-01 09:00:00.000000', '2025-05-04 17:00:00.000000', 3, 'CDA', 'Formation sur les bases de données relationnelles'),
(2, '2025-09-01 09:00:00.000000', '2025-06-02 17:00:00.000000', 4, 'DFS', 'Gestion de version avec Git et GitHub'),
(2, '2025-09-01 09:00:00.000000', '2025-07-03 17:00:00.000000', 5, 'M2I', 'Création de sites web avec HTML et CSS'),
(2, '2025-09-01 09:00:00.000000', '2025-08-05 17:00:00.000000', 6, 'TSSR', 'Maîtrise du langage JavaScript'),
(2, '2025-09-01 09:00:00.000000', '2025-09-04 17:00:00.000000', 7, 'MSRC', 'Développement frontend avec React'),
(2, '2025-09-01 09:00:00.000000', '2025-09-04 17:00:00.000000', 8, 'DEVWEB', 'Développement frontend avec React');

-- --------------------------------------------------------

--
-- Structure de la table `formation_type_document`
--

DROP TABLE IF EXISTS `formation_type_document`;
CREATE TABLE IF NOT EXISTS `formation_type_document` (
  `obligatoire_formation_type_document` bit(1) DEFAULT NULL,
  `id_formation` bigint NOT NULL,
  `id_formation_type_document` bigint NOT NULL AUTO_INCREMENT,
  `id_type_document` bigint NOT NULL,
  PRIMARY KEY (`id_formation_type_document`),
  KEY `FK6xb9k69ah53gcyv9q6rgvh0j3` (`id_formation`),
  KEY `FKrmv87xpw3wtidwnil5pd2tf18` (`id_type_document`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `formation_type_document`
--

INSERT INTO `formation_type_document` (`obligatoire_formation_type_document`, `id_formation`, `id_formation_type_document`, `id_type_document`) VALUES
(b'1', 1, 1, 1),
(b'1', 2, 2, 2),
(b'0', 3, 3, 3),
(b'1', 4, 4, 1),
(b'1', 5, 5, 2),
(b'0', 6, 6, 3),
(b'1', 7, 7, 1),
(b'1', 8, 8, 2),
(b'0', 9, 9, 3),
(b'1', 10, 10, 1);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `date_inscription` datetime(6) NOT NULL,
  `id_formation` bigint NOT NULL,
  `id_inscription` bigint NOT NULL AUTO_INCREMENT,
  `id_stagiaire` bigint NOT NULL,
  PRIMARY KEY (`id_inscription`),
  UNIQUE KEY `UKt6l8n3ggl2lyphs3mhtw7ls8f` (`id_stagiaire`,`id_formation`),
  KEY `FKfk3d6np0s31s329f3gyvjy84q` (`id_formation`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`date_inscription`, `id_formation`, `id_inscription`, `id_stagiaire`) VALUES
('2025-02-10 14:00:00.000000', 1, 1, 2),
('2025-02-12 14:00:00.000000', 2, 2, 3),
('2025-02-15 14:00:00.000000', 3, 3, 4),
('2025-02-18 10:00:00.000000', 4, 4, 5),
('2025-02-20 11:30:00.000000', 5, 5, 6),
('2025-02-22 09:00:00.000000', 6, 6, 7),
('2025-02-24 13:45:00.000000', 7, 7, 8),
('2025-02-26 16:20:00.000000', 8, 8, 9),
('2025-02-28 08:10:00.000000', 1, 9, 10),
('2025-03-01 14:00:00.000000', 2, 10, 11),
('2025-03-03 10:00:00.000000', 2, 11, 12),
('2025-03-05 15:00:00.000000', 1, 12, 13),
('2025-03-07 09:00:00.000000', 3, 13, 14),
('2025-03-09 11:30:00.000000', 8, 14, 15),
('2025-03-11 14:00:00.000000', 1, 15, 16),
('2025-03-13 10:00:00.000000', 2, 16, 17),
('2025-03-15 09:30:00.000000', 3, 17, 18),
('2025-03-17 16:45:00.000000', 8, 18, 19),
('2025-03-19 13:00:00.000000', 9, 19, 20),
('2025-03-21 14:00:00.000000', 1, 20, 21),
('2025-02-10 14:00:00.000000', 1, 21, 22),
('2025-02-12 14:00:00.000000', 2, 22, 23),
('2025-02-15 14:00:00.000000', 3, 23, 24),
('2025-02-18 10:00:00.000000', 4, 24, 25),
('2025-02-20 11:30:00.000000', 5, 25, 26),
('2025-02-22 09:00:00.000000', 6, 26, 27),
('2025-02-24 13:45:00.000000', 7, 27, 28),
('2025-02-26 16:20:00.000000', 8, 28, 29),
('2025-02-28 08:10:00.000000', 1, 29, 30),
('2025-03-01 14:00:00.000000', 2, 30, 31),
('2025-03-03 10:00:00.000000', 2, 31, 32),
('2025-03-05 15:00:00.000000', 1, 32, 33),
('2025-03-07 09:00:00.000000', 3, 33, 34),
('2025-03-09 11:30:00.000000', 8, 34, 35),
('2025-03-11 14:00:00.000000', 1, 35, 36),
('2025-03-13 10:00:00.000000', 2, 36, 37),
('2025-03-15 09:30:00.000000', 3, 37, 38),
('2025-03-17 16:45:00.000000', 8, 38, 39),
('2025-03-19 13:00:00.000000', 9, 39, 40),
('2025-03-21 14:00:00.000000', 1, 40, 41);

-- --------------------------------------------------------

--
-- Structure de la table `justificatif`
--

DROP TABLE IF EXISTS `justificatif`;
CREATE TABLE IF NOT EXISTS `justificatif` (
  `date_depot_justificatif` datetime(6) NOT NULL,
  `id_absence` bigint NOT NULL,
  `id_justificatif` bigint NOT NULL AUTO_INCREMENT,
  `id_statut` bigint NOT NULL,
  `type_document_justificatif` varchar(50) NOT NULL,
  PRIMARY KEY (`id_justificatif`),
  KEY `FKpj1rpy1e2iwwtabv7xb782h4o` (`id_absence`),
  KEY `FK83ctorraiqwae69db9x3x9vdf` (`id_statut`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `justificatif`
--

INSERT INTO `justificatif` (`date_depot_justificatif`, `id_absence`, `id_justificatif`, `id_statut`, `type_document_justificatif`) VALUES
('2025-02-02 10:00:00.000000', 1, 1, 1, 'Certificat médical'),
('2025-02-03 11:00:00.000000', 2, 2, 2, 'Justificatif de congé'),
('2025-02-04 12:00:00.000000', 3, 3, 1, 'Certificat médical'),
('2025-02-05 13:00:00.000000', 4, 4, 2, 'Attestation d urgence'),
('2025-02-06 14:00:00.000000', 5, 5, 1, 'Rapport d hospitalisation'),
('2025-02-07 15:00:00.000000', 6, 6, 2, 'Justificatif familial'),
('2025-02-08 16:00:00.000000', 7, 7, 1, 'Lettre d absence signée'),
('2025-02-09 17:00:00.000000', 8, 8, 2, 'Document officiel d absence'),
('2025-02-10 18:00:00.000000', 9, 9, 1, 'Justificatif médical pour enfant'),
('2025-02-11 19:00:00.000000', 10, 10, 2, 'Lettre de convocation');

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

DROP TABLE IF EXISTS `module`;
CREATE TABLE IF NOT EXISTS `module` (
  `id_module` bigint NOT NULL AUTO_INCREMENT,
  `nom_module` varchar(100) NOT NULL,
  `description_module` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_module`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`id_module`, `nom_module`, `description_module`) VALUES
(1, 'Module Java', 'Module de formation Java'),
(2, 'Module Spring Boot', 'Module de formation Spring Boot');

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `date_notification` datetime(6) NOT NULL,
  `id_notification` bigint NOT NULL AUTO_INCREMENT,
  `id_type_notification` bigint NOT NULL,
  `id_utilisateur` bigint NOT NULL,
  `statut_notification` varchar(50) NOT NULL,
  `contenu_notification` varchar(255) NOT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `FKe4wvltlnot45bdynvx4r2wcdk` (`id_type_notification`),
  KEY `FKg2ycjdo3tuawcelq7vxlp8pc4` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`date_notification`, `id_notification`, `id_type_notification`, `id_utilisateur`, `statut_notification`, `contenu_notification`) VALUES
('2025-02-18 10:00:00.000000', 1, 1, 1, 'EN_ATTENTE', 'Nouvelle inscription à valider'),
('2025-02-18 11:00:00.000000', 2, 2, 2, 'ACCEPTE', 'Votre dossier a été validé'),
('2025-02-18 12:00:00.000000', 3, 1, 3, 'EN_ATTENTE', 'Inscription en attente de validation'),
('2025-02-18 13:00:00.000000', 4, 2, 4, 'REFUSE', 'Votre dossier a été refusé'),
('2025-02-18 14:00:00.000000', 5, 1, 5, 'EN_ATTENTE', 'Nouvelle candidature reçue'),
('2025-02-18 15:00:00.000000', 6, 2, 6, 'ACCEPTE', 'Votre formation est confirmée'),
('2025-02-18 16:00:00.000000', 7, 1, 7, 'EN_ATTENTE', 'Vérification des documents en cours'),
('2025-02-18 17:00:00.000000', 8, 2, 8, 'ACCEPTE', 'Paiement validé, accès débloqué'),
('2025-02-18 18:00:00.000000', 9, 1, 9, 'EN_ATTENTE', 'Attente de validation par l administration'),
('2025-02-18 19:00:00.000000', 10, 2, 10, 'REFUSE', 'Votre candidature ne correspond pas aux critères');

-- --------------------------------------------------------

--
-- Structure de la table `part_secret`
--

DROP TABLE IF EXISTS `part_secret`;
CREATE TABLE IF NOT EXISTS `part_secret` (
  `date_creation_part_secret` datetime(6) NOT NULL,
  `id_part_secret` bigint NOT NULL AUTO_INCREMENT,
  `id_secret` bigint NOT NULL,
  `id_utilisateur` bigint NOT NULL,
  `statut_part_secret` varchar(50) NOT NULL,
  `part_part_secret` varchar(255) NOT NULL,
  PRIMARY KEY (`id_part_secret`),
  KEY `FK9atm7g5p2dc1pml626xq2v854` (`id_secret`),
  KEY `FKj4uccycb69av60jl09f3ko61c` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `part_secret`
--

INSERT INTO `part_secret` (`date_creation_part_secret`, `id_part_secret`, `id_secret`, `id_utilisateur`, `statut_part_secret`, `part_part_secret`) VALUES
('2025-02-18 10:00:00.000000', 1, 1, 1, 'ACTIF', 'Partie secrète 1'),
('2025-02-18 11:00:00.000000', 2, 2, 2, 'INACTIF', 'Partie secrète 2'),
('2025-02-18 12:00:00.000000', 3, 3, 3, 'ACTIF', 'Partie secrète 3'),
('2025-02-18 13:00:00.000000', 4, 4, 4, 'ACTIF', 'Partie secrète 4'),
('2025-02-18 14:00:00.000000', 5, 5, 5, 'INACTIF', 'Partie secrète 5'),
('2025-02-18 15:00:00.000000', 6, 6, 6, 'ACTIF', 'Partie secrète 6'),
('2025-02-18 16:00:00.000000', 7, 7, 7, 'ACTIF', 'Partie secrète 7'),
('2025-02-18 17:00:00.000000', 8, 8, 8, 'INACTIF', 'Partie secrète 8'),
('2025-02-18 18:00:00.000000', 9, 9, 9, 'ACTIF', 'Partie secrète 9'),
('2025-02-18 19:00:00.000000', 10, 10, 10, 'ACTIF', 'Partie secrète 10');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` bigint NOT NULL AUTO_INCREMENT,
  `nom_role` varchar(50) NOT NULL,
  `description_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `UKpkeodm13a32nsbrnpi69biytw` (`nom_role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id_role`, `nom_role`, `description_role`) VALUES
(1, 'Admin', 'Administrateur du système'),
(2, 'Stagiaire', 'Utilisateur stagiaire'),
(3, 'Formateur', 'Formateur en charge des formations'),
(4, 'Candidat', 'Utilisateur candidat');

-- --------------------------------------------------------

--
-- Structure de la table `role_module`
--

DROP TABLE IF EXISTS `role_module`;
CREATE TABLE IF NOT EXISTS `role_module` (
  `actif_role_module` bit(1) NOT NULL,
  `id_module` bigint NOT NULL,
  `id_role` bigint NOT NULL,
  `id_role_module` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_role_module`),
  KEY `FKhrkiisyx5gvfkm2t5hlw3q127` (`id_module`),
  KEY `FKmlswe1pd1ikw1icjmuib1pt56` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role_module`
--

INSERT INTO `role_module` (`actif_role_module`, `id_module`, `id_role`, `id_role_module`) VALUES
(b'1', 1, 1, 1),
(b'1', 2, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `secret`
--

DROP TABLE IF EXISTS `secret`;
CREATE TABLE IF NOT EXISTS `secret` (
  `date_creation_secret` datetime(6) NOT NULL,
  `id_secret` bigint NOT NULL AUTO_INCREMENT,
  `nom_secret` varchar(100) NOT NULL,
  `complete_hash_secret` varchar(255) NOT NULL,
  `description_secret` varchar(255) DEFAULT NULL,
  `statut_secret` varchar(255) NOT NULL,
  PRIMARY KEY (`id_secret`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `secret`
--

INSERT INTO `secret` (`date_creation_secret`, `id_secret`, `nom_secret`, `complete_hash_secret`, `description_secret`, `statut_secret`) VALUES
('2025-02-18 10:00:00.000000', 1, 'Secret 1', 'hashvalue123', 'Description du secret 1', 'ACTIF'),
('2025-02-18 11:00:00.000000', 2, 'Secret 2', 'hashvalue456', 'Description du secret 2', 'INACTIF'),
('2025-02-18 12:00:00.000000', 3, 'Secret 3', 'hashvalue789', 'Description du secret 3', 'ACTIF'),
('2025-02-18 13:00:00.000000', 4, 'Secret 4', 'hashvalue321', 'Description du secret 4', 'ACTIF'),
('2025-02-18 14:00:00.000000', 5, 'Secret 5', 'hashvalue654', 'Description du secret 5', 'INACTIF'),
('2025-02-18 15:00:00.000000', 6, 'Secret 6', 'hashvalue987', 'Description du secret 6', 'ACTIF'),
('2025-02-18 16:00:00.000000', 7, 'Secret 7', 'hashvalue741', 'Description du secret 7', 'ACTIF'),
('2025-02-18 17:00:00.000000', 8, 'Secret 8', 'hashvalue852', 'Description du secret 8', 'INACTIF'),
('2025-02-18 18:00:00.000000', 9, 'Secret 9', 'hashvalue963', 'Description du secret 9', 'ACTIF'),
('2025-02-18 19:00:00.000000', 10, 'Secret 10', 'hashvalue159', 'Description du secret 10', 'ACTIF');

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

DROP TABLE IF EXISTS `statut`;
CREATE TABLE IF NOT EXISTS `statut` (
  `id_statut` bigint NOT NULL AUTO_INCREMENT,
  `statut` enum('ACCEPTE','EN_ATTENTE','REFUSE') NOT NULL,
  PRIMARY KEY (`id_statut`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `statut`
--

INSERT INTO `statut` (`id_statut`, `statut`) VALUES
(1, 'ACCEPTE'),
(2, 'EN_ATTENTE'),
(3, 'REFUSE');

-- --------------------------------------------------------

--
-- Structure de la table `type_absence`
--

DROP TABLE IF EXISTS `type_absence`;
CREATE TABLE IF NOT EXISTS `type_absence` (
  `id_type_absence` bigint NOT NULL AUTO_INCREMENT,
  `nom_type_absence` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type_absence`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `type_absence`
--

INSERT INTO `type_absence` (`id_type_absence`, `nom_type_absence`) VALUES
(1, 'Maladie'),
(2, 'Congé payé'),
(3, 'Formation'),
(4, 'Autre');

-- --------------------------------------------------------

--
-- Structure de la table `type_document`
--

DROP TABLE IF EXISTS `type_document`;
CREATE TABLE IF NOT EXISTS `type_document` (
  `chiffrement_requis_type_document` bit(1) NOT NULL,
  `obligatoire_type_document` bit(1) NOT NULL,
  `id_type_document` bigint NOT NULL AUTO_INCREMENT,
  `nom_type_document` varchar(50) NOT NULL,
  `description_type_document` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type_document`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `type_document`
--

INSERT INTO `type_document` (`chiffrement_requis_type_document`, `obligatoire_type_document`, `id_type_document`, `nom_type_document`, `description_type_document`) VALUES
(b'1', b'1', 1, 'Contrat de stage', 'Contrat signé pour le stage'),
(b'1', b'1', 2, 'Contrat dapprentissage', 'Contrat officiel pour un apprentissage'),
(b'1', b'1', 3, 'Convention de formation', 'Convention signée entre létudiant et lécole'),
(b'1', b'1', 4, 'Accord de confidentialité', 'Engagement de confidentialité signé par létudiant'),
(b'0', b'1', 5, 'Autorisation parentale', 'Autorisation signée pour les mineurs'),
(b'0', b'1', 6, 'Diplôme', 'Diplôme requis pour la formation'),
(b'0', b'1', 7, 'Relevé de notes', 'Relevé des résultats scolaires du candidat'),
(b'0', b'1', 8, 'Attestation de réussite', 'Document prouvant l obtention d un diplôme ou certification'),
(b'0', b'1', 9, 'Lettre de recommandation', 'Lettre rédigée par un enseignant ou employeur'),
(b'0', b'1', 10, 'Certificat de scolarité', 'Attestation prouvant l inscription dans un établissement scolaire'),
(b'0', b'0', 11, 'Justificatif médical', 'Certificat médical pour justifier une absence'),
(b'0', b'1', 12, 'Justificatif de domicile', 'Facture ou attestation prouvant l adresse de résidence'),
(b'0', b'1', 13, 'Attestation d assurance', 'Justificatif de couverture assurance étudiant'),
(b'0', b'1', 14, 'Justificatif de paiement', 'Preuve du règlement des frais de scolarité'),
(b'0', b'0', 15, 'Avis d imposition', 'Document fiscal pour certaines aides financières'),
(b'0', b'1', 16, 'RIB', 'Relevé d identité bancaire pour les paiements'),
(b'0', b'1', 17, 'Accord de financement', 'Accord de prise en charge des frais de scolarité'),
(b'1', b'1', 18, 'Dossier de financement', 'Documents requis pour un financement scolaire'),
(b'0', b'1', 19, 'Carte d identité', 'Carte nationale didentité ou passeport en cours de validité'),
(b'0', b'1', 20, 'Passeport', 'Passeport valide pour les étudiants étrangers'),
(b'1', b'1', 21, 'Permis de séjour', 'Autorisation de séjour pour les étudiants internationaux'),
(b'0', b'1', 22, 'CV', 'Curriculum vitae du candidat'),
(b'0', b'1', 23, 'Lettre de motivation', 'Lettre expliquant les motivations du candidat'),
(b'0', b'0', 24, 'Portfolio', 'Exemples de travaux ou projets réalisés par le candidat'),
(b'0', b'0', 25, 'Attestation de participation', 'Document justifiant la participation à un événement'),
(b'0', b'0', 26, 'Déclaration sur l honneur', 'Déclaration signée pour diverses démarches');

-- --------------------------------------------------------

--
-- Structure de la table `type_notification`
--

DROP TABLE IF EXISTS `type_notification`;
CREATE TABLE IF NOT EXISTS `type_notification` (
  `id_type_notification` bigint NOT NULL AUTO_INCREMENT,
  `nom_type_notification` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type_notification`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `type_notification`
--

INSERT INTO `type_notification` (`id_type_notification`, `nom_type_notification`) VALUES
(1, 'Inscription'),
(2, 'Validation de dossier');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `date_creation_utilisateur` datetime(6) DEFAULT NULL,
  `date_mise_a_jour_utilisateur` datetime(6) DEFAULT NULL,
  `id_utilisateur` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `nom_utilisateur` varchar(50) NOT NULL,
  `prenom_utilisateur` varchar(50) NOT NULL,
  `email_utilisateur` varchar(100) NOT NULL,
  `mot_de_passe_utilisateur` varchar(255) NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `UKi96kh89a47nnoklhsjtnjswt` (`email_utilisateur`),
  KEY `FKaqe8xtajee4k0wlqrvh2pso4l` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`date_creation_utilisateur`, `date_mise_a_jour_utilisateur`, `id_utilisateur`, `role_id`, `nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `mot_de_passe_utilisateur`) VALUES
(NULL, NULL, 1, 1, 'Dupont', 'Jean', 'jean.dupont@example.com', 'password123'),
(NULL, NULL, 2, 1, 'Bernard', 'Sophie', 'sophie.bernard@example.com', 'password123'),
(NULL, NULL, 3, 1, 'Morel', 'Antoine', 'antoine.morel@example.com', 'password123'),
(NULL, NULL, 4, 1, 'Garcia', 'Elise', 'elise.garcia@example.com', 'password123'),
(NULL, NULL, 5, 3, 'Martin', 'Lucie', 'lucie.martin@example.com', 'password123'),
(NULL, NULL, 6, 3, 'Lemoine', 'Pierre', 'pierre.lemoine@example.com', 'password123'),
(NULL, NULL, 7, 3, 'Blanc', 'Isabelle', 'isabelle.blanc@example.com', 'password123'),
(NULL, NULL, 8, 3, 'Fischer', 'Hugo', 'hugo.fischer@example.com', 'password123'),
(NULL, NULL, 9, 3, 'Girard', 'Camille', 'camille.girard@example.com', 'password123'),
(NULL, NULL, 10, 3, 'Robin', 'Maxime', 'maxime.robin@example.com', 'password123'),
(NULL, NULL, 11, 3, 'Noel', 'Juliette', 'juliette.noel@example.com', 'password123'),
(NULL, NULL, 12, 3, 'Rey', 'Thomas', 'thomas.rey@example.com', 'password123'),
(NULL, NULL, 13, 3, 'Perrot', 'Chloe', 'chloe.perrot@example.com', 'password123'),
(NULL, NULL, 14, 3, 'Baron', 'Louis', 'louis.baron@example.com', 'password123'),
(NULL, NULL, 15, 3, 'Moulin', 'Emma', 'emma.moulin@example.com', 'password123'),
(NULL, NULL, 16, 2, 'Leroy', 'Nathan', 'nathan.leroy@example.com', 'password123'),
(NULL, NULL, 17, 2, 'David', 'Alice', 'alice.david@example.com', 'password123'),
(NULL, NULL, 18, 2, 'Moreau', 'Hugo', 'hugo.moreau@example.com', 'password123'),
(NULL, NULL, 19, 2, 'Simon', 'Jade', 'jade.simon@example.com', 'password123'),
(NULL, NULL, 20, 2, 'Roux', 'Leo', 'leo.roux@example.com', 'password123'),
(NULL, NULL, 21, 2, 'Vincent', 'Manon', 'manon.vincent@example.com', 'password123'),
(NULL, NULL, 22, 2, 'Bertrand', 'Ethan', 'ethan.bertrand@example.com', 'password123'),
(NULL, NULL, 23, 2, 'Lambert', 'Clara', 'clara.lambert@example.com', 'password123'),
(NULL, NULL, 24, 2, 'Henry', 'Gabriel', 'gabriel.henry@example.com', 'password123'),
(NULL, NULL, 25, 2, 'Masson', 'Lina', 'lina.masson@example.com', 'password123'),
(NULL, NULL, 26, 4, 'Garnier', 'Alexis', 'alexis.garnier@example.com', 'password123'),
(NULL, NULL, 27, 4, 'Chevalier', 'Sofia', 'sofia.chevalier@example.com', 'password123'),
(NULL, NULL, 28, 4, 'Lucas', 'Matteo', 'matteo.lucas@example.com', 'password123'),
(NULL, NULL, 29, 4, 'Bonnet', 'Louise', 'louise.bonnet@example.com', 'password123'),
(NULL, NULL, 30, 4, 'François', 'Evan', 'evan.francois@example.com', 'password123'),
(NULL, NULL, 31, 4, 'Dupuis', 'Elena', 'elena.dupuis@example.com', 'password123'),
(NULL, NULL, 32, 4, 'Berger', 'Oscar', 'oscar.berger@example.com', 'password123'),
(NULL, NULL, 33, 4, 'Gautier', 'Mila', 'mila.gautier@example.com', 'password123'),
(NULL, NULL, 34, 4, 'Roger', 'Arthur', 'arthur.roger@example.com', 'password123'),
(NULL, NULL, 35, 4, 'Collet', 'Léa', 'lea.collet@example.com', 'password123'),
(NULL, NULL, 36, 2, 'Guillot', 'Theo', 'theo.guillot@example.com', 'password123'),
(NULL, NULL, 37, 4, 'Barre', 'Emma', 'emma.barre@example.com', 'password123'),
(NULL, NULL, 38, 2, 'Perrin', 'Lucas', 'lucas.perrin@example.com', 'password123'),
(NULL, NULL, 39, 4, 'Renard', 'Elise', 'elise.renard@example.com', 'password123'),
(NULL, NULL, 40, 2, 'Marchal', 'Adam', 'adam.marchal@example.com', 'password123'),
(NULL, NULL, 41, 4, 'Leclerc', 'Camille', 'camille.leclerc@example.com', 'password123'),
(NULL, NULL, 42, 2, 'Bouvier', 'Noah', 'noah.bouvier@example.com', 'password123'),
(NULL, NULL, 43, 4, 'Giraud', 'Nina', 'nina.giraud@example.com', 'password123'),
(NULL, NULL, 44, 2, 'Leger', 'Enzo', 'enzo.leger@example.com', 'password123'),
(NULL, NULL, 45, 4, 'Besson', 'Sarah', 'sarah.besson@example.com', 'password123'),
(NULL, NULL, 46, 2, 'Durand', 'Mael', 'mael.durand@example.com', 'password123'),
(NULL, NULL, 47, 4, 'Fleury', 'Zoé', 'zoe.fleury@example.com', 'password123'),
(NULL, NULL, 48, 2, 'Benoit', 'Raphael', 'raphael.benoit@example.com', 'password123'),
(NULL, NULL, 49, 4, 'Poulain', 'Lola', 'lola.poulain@example.com', 'password123'),
(NULL, NULL, 50, 2, 'Germain', 'Eliott', 'eliott.germain@example.com', 'password123'),
(NULL, NULL, 51, 4, 'Barbier', 'Ava', 'ava.barbier@example.com', 'password123'),
(NULL, NULL, 52, 2, 'Chauvin', 'Timéo', 'timeo.chauvin@example.com', 'password123'),
(NULL, NULL, 53, 4, 'Pichon', 'Salomé', 'salome.pichon@example.com', 'password123'),
(NULL, NULL, 54, 2, 'Legrand', 'Mathis', 'mathis.legrand@example.com', 'password123'),
(NULL, NULL, 55, 4, 'Roy', 'Iris', 'iris.roy@example.com', 'password123'),
(NULL, NULL, 56, 2, 'Picard', 'Valentin', 'valentin.picard@example.com', 'password123'),
(NULL, NULL, 57, 4, 'Mallet', 'Lou', 'lou.mallet@example.com', 'password123'),
(NULL, NULL, 58, 2, 'Devaux', 'Paul', 'paul.devaux@example.com', 'password123'),
(NULL, NULL, 59, 4, 'Maillard', 'Sacha', 'sacha.maillard@example.com', 'password123'),
(NULL, NULL, 60, 2, 'Charpentier', 'Eden', 'eden.charpentier@example.com', 'password123'),
(NULL, NULL, 61, 4, 'Hoarau', 'Elsa', 'elsa.hoarau@example.com', 'password123'),
(NULL, NULL, 62, 2, 'Gosselin', 'Jules', 'jules.gosselin@example.com', 'password123'),
(NULL, NULL, 63, 4, 'Descamps', 'Ambre', 'ambre.descamps@example.com', 'password123'),
(NULL, NULL, 64, 2, 'Chapel', 'Esteban', 'esteban.chapel@example.com', 'password123'),
(NULL, NULL, 65, 4, 'Legendre', 'Luna', 'luna.legendre@example.com', 'password123');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `FKg4r3fy4pne5t798vicpi9oov7` FOREIGN KEY (`id_type_absence`) REFERENCES `type_absence` (`id_type_absence`),
  ADD CONSTRAINT `FKs2hdpvy73p9d53fylwhvn6532` FOREIGN KEY (`id_stagiaire`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `FK62eikoxh114ldyv7t6xkf2usn` FOREIGN KEY (`id_dossier`) REFERENCES `dossier` (`id_dossier`),
  ADD CONSTRAINT `FK9bmhlm70xd6kmypoglxrun2qj` FOREIGN KEY (`id_type_document`) REFERENCES `type_document` (`id_type_document`),
  ADD CONSTRAINT `FKn8qfw0ot70d510ai4xgn9jyw5` FOREIGN KEY (`id_statut`) REFERENCES `statut` (`id_statut`),
  ADD CONSTRAINT `FKpnymn2radnb9not4eb5q0bhjg` FOREIGN KEY (`id_secret`) REFERENCES `secret` (`id_secret`);

--
-- Contraintes pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD CONSTRAINT `FKa4k6lher1yfqvhbraqot5q2uv` FOREIGN KEY (`id_stagiaire`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `FKslgox4j1wnoap58wa6pbmvlyq` FOREIGN KEY (`id_statut`) REFERENCES `statut` (`id_statut`);

--
-- Contraintes pour la table `formation_type_document`
--
ALTER TABLE `formation_type_document`
  ADD CONSTRAINT `FK6xb9k69ah53gcyv9q6rgvh0j3` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id_formation`),
  ADD CONSTRAINT `FKrmv87xpw3wtidwnil5pd2tf18` FOREIGN KEY (`id_type_document`) REFERENCES `type_document` (`id_type_document`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `FK161nt21onlxwthmpda78tgsv7` FOREIGN KEY (`id_stagiaire`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `FKfk3d6np0s31s329f3gyvjy84q` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id_formation`);

--
-- Contraintes pour la table `justificatif`
--
ALTER TABLE `justificatif`
  ADD CONSTRAINT `FK83ctorraiqwae69db9x3x9vdf` FOREIGN KEY (`id_statut`) REFERENCES `statut` (`id_statut`),
  ADD CONSTRAINT `FKpj1rpy1e2iwwtabv7xb782h4o` FOREIGN KEY (`id_absence`) REFERENCES `absence` (`id_absence`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FKe4wvltlnot45bdynvx4r2wcdk` FOREIGN KEY (`id_type_notification`) REFERENCES `type_notification` (`id_type_notification`),
  ADD CONSTRAINT `FKg2ycjdo3tuawcelq7vxlp8pc4` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `part_secret`
--
ALTER TABLE `part_secret`
  ADD CONSTRAINT `FK9atm7g5p2dc1pml626xq2v854` FOREIGN KEY (`id_secret`) REFERENCES `secret` (`id_secret`),
  ADD CONSTRAINT `FKj4uccycb69av60jl09f3ko61c` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `role_module`
--
ALTER TABLE `role_module`
  ADD CONSTRAINT `FKhrkiisyx5gvfkm2t5hlw3q127` FOREIGN KEY (`id_module`) REFERENCES `module` (`id_module`),
  ADD CONSTRAINT `FKmlswe1pd1ikw1icjmuib1pt56` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKaqe8xtajee4k0wlqrvh2pso4l` FOREIGN KEY (`role_id`) REFERENCES `role` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
