-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_saui
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_productor` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fecha_compra` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `compras_ibfk_1` (`id_productor`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_productor`) REFERENCES `productores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (94,91,138805.5,'2025-01-15 04:18:22'),(95,135,153811.5,'2025-01-15 04:19:02'),(96,91,2501,'2025-01-15 04:19:39'),(97,91,125050,'2025-01-15 04:19:53'),(101,91,2501,'2025-01-15 05:17:09'),(102,137,2501,'2025-01-15 05:42:05'),(104,135,180.75,'2025-01-15 20:04:45'),(105,135,35,'2025-01-15 21:39:38'),(107,85,471.5,'2025-01-15 22:22:52'),(108,85,245.75,'2025-01-15 22:30:02');
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_compra`
--

DROP TABLE IF EXISTS `detalles_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_compra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_compra` int DEFAULT NULL,
  `id_semilla` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `subtotal` double DEFAULT NULL,
  `fecha_compra` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbw3iwdx4mn8u98rc1tov4wr45` (`id_semilla`),
  KEY `com_idx` (`id_compra`),
  CONSTRAINT `com` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id`),
  CONSTRAINT `FKbw3iwdx4mn8u98rc1tov4wr45` FOREIGN KEY (`id_semilla`) REFERENCES `semillas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_compra`
--

LOCK TABLES `detalles_compra` WRITE;
/*!40000 ALTER TABLE `detalles_compra` DISABLE KEYS */;
INSERT INTO `detalles_compra` VALUES (153,94,10,111,138805.5,'2025-01-15 04:18:22'),(154,95,10,123,153811.5,'2025-01-15 04:19:02'),(155,96,10,2,2501,'2025-01-15 04:19:39'),(156,97,10,100,125050,'2025-01-15 04:19:53'),(160,101,10,2,2501,'2025-01-15 05:17:09'),(161,102,10,2,2501,'2025-01-15 05:42:05'),(163,104,13,1,180.75,'2025-01-15 20:04:45'),(164,105,20,1,35,'2025-01-15 21:39:38'),(166,107,13,1,180.75,'2025-01-15 22:22:52'),(167,107,22,2,110,'2025-01-15 22:22:56'),(168,107,13,1,180.75,'2025-01-15 22:22:59'),(169,108,13,1,180.75,'2025-01-15 22:30:02'),(170,108,14,1,65,'2025-01-15 22:30:14');
/*!40000 ALTER TABLE `detalles_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productores`
--

DROP TABLE IF EXISTS `productores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `id_departamento` varchar(255) DEFAULT NULL,
  `id_provincia` varchar(255) DEFAULT NULL,
  `id_distrito` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productores`
--

LOCK TABLES `productores` WRITE;
/*!40000 ALTER TABLE `productores` DISABLE KEYS */;
INSERT INTO `productores` VALUES (85,'1231412','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-15 18:46:06'),(86,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(87,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(88,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(89,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(90,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(91,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(92,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(93,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(94,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(95,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(96,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(97,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(98,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(135,'25252525','rafael','huayta','huayta@gmail.com','963852741','25','2501','250104','Masisea','2025-01-13 23:18:25'),(137,'1123','123','123','123','123123123','12','1204','120410','123123','2025-01-15 05:41:54');
/*!40000 ALTER TABLE `productores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semillas`
--

DROP TABLE IF EXISTS `semillas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semillas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_productor` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `cantidad` int NOT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `pr_idx` (`id_productor`),
  CONSTRAINT `id_productor` FOREIGN KEY (`id_productor`) REFERENCES `productores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semillas`
--

LOCK TABLES `semillas` WRITE;
/*!40000 ALTER TABLE `semillas` DISABLE KEYS */;
INSERT INTO `semillas` VALUES (10,85,'Laptop HP','Laptop HP con 16GB RAM y 512GB SSD',1250.5,362,'2025-01-14 20:05:59'),(13,85,'Monitor Samsung','Monitor LED de 24 pulgadas',180.75,25,'2025-01-08 00:39:46'),(14,85,'Disco Duro Externo','Disco duro externo de 1TB',65,39,'2025-01-08 00:39:46'),(15,85,'Memoria USB','Memoria USB 64GB Kingston',12.99,99,'2025-01-08 00:39:46'),(16,85,'Smartphone Xiaomi','Smartphone Xiaomi 128GB',300,24,'2025-01-08 00:39:46'),(17,85,'Tablet Samsung','Tablet Samsung Galaxy Tab 10\"',220,10,'2025-01-08 00:39:46'),(18,85,'Impresora Epson','Impresora multifuncional Epson',150,8,'2025-01-08 00:39:46'),(19,85,'Auriculares Sony','Auriculares inalámbricos Sony',90.5,35,'2025-01-08 00:39:46'),(20,85,'Cargador Portátil','Cargador portátil de 10,000mAh',35,89,'2025-01-08 00:39:46'),(22,85,'Router TP-Link','Router inalámbrico de alta velocidad',55,20,'2025-01-08 00:39:46'),(23,85,'Cámara Web','Cámara web Full HD Logitech',65.99,22,'2025-01-08 00:39:46'),(24,85,'Altavoces Bluetooth','Altavoces portátiles Bluetooth',45.5,12,'2025-01-08 00:39:46'),(25,85,'SSD Kingston','Unidad SSD Kingston 500GB',85,15,'2025-01-08 00:39:46'),(26,85,'Tarjeta de Video','Tarjeta gráfica NVIDIA GTX 1660',450,5,'2025-01-08 00:39:46'),(27,85,'Micro SD Sandisk','Tarjeta micro SD Sandisk 128GB',30.99,80,'2025-01-08 00:39:46'),(28,85,'Silla Gamer','Silla ergonómica para gaming',200,7,'2025-01-08 00:39:46'),(29,85,'Escritorio','Escritorio de oficina con soporte',150,9,'2025-01-08 00:39:46'),(30,85,'asdasd','asdasd',0,0,'2025-01-14 19:25:09'),(31,85,'asd','asd',0,0,'2025-01-14 22:25:57');
/*!40000 ALTER TABLE `semillas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(255) DEFAULT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `rol` enum('Administrador','Empleado','Cliente') NOT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (9,'ricardoyanhua','$2a$10$ZRKvIcHcO/RXEFtvd.wsf.Ca.UiQkSq702CLn/PM2eWlVxUEErmLG','Ricardo','Yanapa Huaya','Ricardoyanhua@gmail.com','','','Administrador','2025-01-15 20:43:50'),(17,'envyendyanhua','$2a$10$47BhhyLYU3Bz/YV1KYi8g.WcqmsfP2G5F5BijTy5baovfrjBDnmfa','Ricardo Humberto','Yanapa Huayta','ricardohumberto2001@gmail.com','949 674 479','Av.Bellavista ·775','Administrador','2025-01-15 22:02:20'),(19,'jesusnoel','$2a$10$iGT.ll4kZP2Xi1EV94BRn.iUB3rWWD.anMrXm8OxrOrna9pTWo6Jq','jesus','hernandez gallegos','jesus_14@gmail.com','','','Empleado','2025-01-15 22:18:40');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bd_saui'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-15 18:04:48
