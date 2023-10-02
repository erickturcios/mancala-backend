-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 02-10-2023 a las 18:38:56
-- Versión del servidor: 10.6.5-MariaDB
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `mancaladb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configurations`
--

DROP TABLE IF EXISTS `configurations`;
CREATE TABLE IF NOT EXISTS `configurations` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `game_session` varchar(36) NOT NULL,
  `number_of_stones` int(11) DEFAULT 0,
  `step_back_allowed` tinyint(1) DEFAULT 0,
  `auto_rotate` tinyint(1) DEFAULT 0,
  `alias1` varchar(120) DEFAULT 'Player 1',
  `alias2` varchar(120) DEFAULT 'Player 2',
  `created_on` datetime DEFAULT current_timestamp(),
  `updated_on` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game_boards`
--

DROP TABLE IF EXISTS `game_boards`;
CREATE TABLE IF NOT EXISTS `game_boards` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `game_session` varchar(36) NOT NULL,
  `p1_01` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_02` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_03` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_04` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_05` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_06` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_total` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_01` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_02` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_03` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_04` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_05` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_06` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_total` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `player_to_move_next` tinyint(1) DEFAULT NULL,
  `winner` tinyint(1) DEFAULT 0,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `game_boards_uidx_001` (`game_session`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game_boards_history`
--

DROP TABLE IF EXISTS `game_boards_history`;
CREATE TABLE IF NOT EXISTS `game_boards_history` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `game_session` varchar(36) NOT NULL,
  `p1_01` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_02` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_03` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_04` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_05` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_06` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p1_total` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_01` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_02` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_03` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_04` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_05` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_06` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `p2_total` smallint(3) UNSIGNED NOT NULL DEFAULT 0,
  `player_to_move_next` tinyint(1) DEFAULT NULL,
  `winner` tinyint(1) DEFAULT 0,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `game_boards_history_uidx_001` (`game_session`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sessions`
--

DROP TABLE IF EXISTS `sessions`;
CREATE TABLE IF NOT EXISTS `sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(36) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_on` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `session_id_UNIQUE` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
COMMIT;