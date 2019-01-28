CREATE DATABASE  IF NOT EXISTS `asdapi` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `asdapi`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: asdapi
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.16-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activo_fijo`
--

DROP TABLE IF EXISTS `activo_fijo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activo_fijo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alto` double DEFAULT NULL,
  `ancho` double DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `fecha_compra` date DEFAULT NULL,
  `largo` double DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numero_interno_inventario` varchar(255) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `serial` varchar(255) DEFAULT NULL,
  `valor_compra` bigint(20) DEFAULT NULL,
  `estado_actual_id` int(11) DEFAULT NULL,
  `tipo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_activo_fijo_estado_actual_id` (`estado_actual_id`),
  KEY `FK_activo_fijo_tipo_id` (`tipo_id`),
  CONSTRAINT `FK_activo_fijo_estado_actual_id` FOREIGN KEY (`estado_actual_id`) REFERENCES `estado_actual` (`id`),
  CONSTRAINT `FK_activo_fijo_tipo_id` FOREIGN KEY (`tipo_id`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activo_fijo`
--

LOCK TABLES `activo_fijo` WRITE;
/*!40000 ALTER TABLE `activo_fijo` DISABLE KEYS */;
INSERT INTO `activo_fijo` VALUES (1,1.3,1.4,'Activo para casos de prueba','2017-05-14','2017-05-14',1.5,'Prueba','123456',1.2,'836955425',50000,1,1),(2,1.3,1.4,'Activo para casos de prueba','2013-04-01','2017-05-14',1.5,'Prueba','44512255',1.2,'836955425',50000,1,1);
/*!40000 ALTER TABLE `activo_fijo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activo_fijo_area`
--

DROP TABLE IF EXISTS `activo_fijo_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activo_fijo_area` (
  `area_id` int(11) NOT NULL,
  `activo_fijo_id` int(11) NOT NULL,
  PRIMARY KEY (`area_id`,`activo_fijo_id`),
  KEY `FK_activo_fijo_area_activo_fijo_id` (`activo_fijo_id`),
  CONSTRAINT `FK_activo_fijo_area_activo_fijo_id` FOREIGN KEY (`activo_fijo_id`) REFERENCES `activo_fijo` (`id`),
  CONSTRAINT `FK_activo_fijo_area_area_id` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activo_fijo_area`
--

LOCK TABLES `activo_fijo_area` WRITE;
/*!40000 ALTER TABLE `activo_fijo_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `activo_fijo_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activo_fijo_persona`
--

DROP TABLE IF EXISTS `activo_fijo_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activo_fijo_persona` (
  `activo_fijo_id` int(11) NOT NULL,
  `persona_id` int(11) NOT NULL,
  PRIMARY KEY (`activo_fijo_id`,`persona_id`),
  KEY `FK_activo_fijo_persona_persona_id` (`persona_id`),
  CONSTRAINT `FK_activo_fijo_persona_activo_fijo_id` FOREIGN KEY (`activo_fijo_id`) REFERENCES `activo_fijo` (`id`),
  CONSTRAINT `FK_activo_fijo_persona_persona_id` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activo_fijo_persona`
--

LOCK TABLES `activo_fijo_persona` WRITE;
/*!40000 ALTER TABLE `activo_fijo_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `activo_fijo_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `ciudad_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_area_ciudad_id` (`ciudad_id`),
  CONSTRAINT `FK_area_ciudad_id` FOREIGN KEY (`ciudad_id`) REFERENCES `ciudad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Area1',1);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Bogota');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_actual`
--

DROP TABLE IF EXISTS `estado_actual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_actual` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_actual`
--

LOCK TABLES `estado_actual` WRITE;
/*!40000 ALTER TABLE `estado_actual` DISABLE KEYS */;
INSERT INTO `estado_actual` VALUES (1,'Activo');
/*!40000 ALTER TABLE `estado_actual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Juan');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (1,'maquinaria');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-28  0:03:04
