-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.39-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para trucotrato
CREATE DATABASE IF NOT EXISTS `trucotrato` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `trucotrato`;

-- Volcando estructura para tabla trucotrato.disfraz
CREATE TABLE IF NOT EXISTS `disfraz` (
  `nombreNene` varchar(55) DEFAULT NULL,
  `codigoDisfraz` int(4) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `talla` varchar(2) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigoDisfraz`),
  KEY `FK_NENE_DISFRAZ` (`nombreNene`),
  CONSTRAINT `FK_NENE_DISFRAZ` FOREIGN KEY (`nombreNene`) REFERENCES `nene` (`nombreNene`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla trucotrato.disfraz: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `disfraz` DISABLE KEYS */;
INSERT INTO `disfraz` (`nombreNene`, `codigoDisfraz`, `nombre`, `talla`, `descripcion`) VALUES
	('Javier', 1, 'Pirata', 'XL', 'Disfraz de Jack Sparrow'),
	('Esteban', 2, 'Goma', 'L', 'Goma para la cabeza '),
	('Raquel', 3, 'Snorlax', 'L', 'Disfraz de Snorlax');
/*!40000 ALTER TABLE `disfraz` ENABLE KEYS */;

-- Volcando estructura para tabla trucotrato.nene
CREATE TABLE IF NOT EXISTS `nene` (
  `nombreNene` varchar(55) NOT NULL,
  `edad` int(2) DEFAULT NULL,
  `genero` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`nombreNene`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla trucotrato.nene: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `nene` DISABLE KEYS */;
INSERT INTO `nene` (`nombreNene`, `edad`, `genero`) VALUES
	('Esteban', 27, 'Hombre'),
	('Javier', 27, 'Hombre'),
	('Raquel', 26, 'Mujer');
/*!40000 ALTER TABLE `nene` ENABLE KEYS */;

-- Volcando estructura para tabla trucotrato.pieza
CREATE TABLE IF NOT EXISTS `pieza` (
  `codigoPieza` int(4) NOT NULL,
  `codigoDisfraz` int(4) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigoPieza`),
  KEY `FK_DISFRAZ_PIEZA` (`codigoDisfraz`),
  CONSTRAINT `FK_DISFRAZ_PIEZA` FOREIGN KEY (`codigoDisfraz`) REFERENCES `disfraz` (`codigoDisfraz`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla trucotrato.pieza: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `pieza` DISABLE KEYS */;
INSERT INTO `pieza` (`codigoPieza`, `codigoDisfraz`, `nombre`, `color`, `descripcion`) VALUES
	(1, 2, 'Goma', 'Blanca', 'Goma blanca'),
	(2, 1, 'Espada', 'Dorada', 'Espada de pirata'),
	(3, 3, 'Gorro', 'Negro', 'Gorro negro');
/*!40000 ALTER TABLE `pieza` ENABLE KEYS */;

-- Volcando estructura para tabla trucotrato.vecino
CREATE TABLE IF NOT EXISTS `vecino` (
  `nombreVecino` varchar(55) NOT NULL,
  `piso` int(1) DEFAULT NULL,
  `puerta` int(2) DEFAULT NULL,
  PRIMARY KEY (`nombreVecino`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla trucotrato.vecino: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `vecino` DISABLE KEYS */;
INSERT INTO `vecino` (`nombreVecino`, `piso`, `puerta`) VALUES
	('Daniel', 2, 5),
	('Jeroen', 1, 1),
	('Ramon', 4, 3);
/*!40000 ALTER TABLE `vecino` ENABLE KEYS */;

-- Volcando estructura para tabla trucotrato.visita
CREATE TABLE IF NOT EXISTS `visita` (
  `nombreNene` varchar(55) NOT NULL,
  `nombreVecino` varchar(55) NOT NULL,
  `chuches` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nombreNene`,`nombreVecino`),
  KEY `FK_VECINO_VISITA` (`nombreVecino`),
  CONSTRAINT `FK_NENE_VISITA` FOREIGN KEY (`nombreNene`) REFERENCES `nene` (`nombreNene`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VECINO_VISITA` FOREIGN KEY (`nombreVecino`) REFERENCES `vecino` (`nombreVecino`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla trucotrato.visita: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
INSERT INTO `visita` (`nombreNene`, `nombreVecino`, `chuches`) VALUES
	('Esteban', 'Jeroen', 'Fresas'),
	('Javier', 'Ramon', 'Patatas'),
	('Raquel', 'Daniel', 'Galletas');
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
