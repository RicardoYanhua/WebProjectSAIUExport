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
  `id_cliente` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fecha_compra` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `productores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (32,85,180.75,'2025-01-08 21:45:46');
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
  `id_producto` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `subtotal` double DEFAULT NULL,
  `fecha_compra` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd44nxst5k78y5nre2g1i9xpac` (`id_producto`),
  KEY `com_idx` (`id_compra`),
  CONSTRAINT `dc` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id`),
  CONSTRAINT `detalles_compra_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `semillas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_compra`
--

LOCK TABLES `detalles_compra` WRITE;
/*!40000 ALTER TABLE `detalles_compra` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productores`
--

LOCK TABLES `productores` WRITE;
/*!40000 ALTER TABLE `productores` DISABLE KEYS */;
INSERT INTO `productores` VALUES (85,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(86,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(87,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(88,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(89,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(90,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(91,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(92,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(93,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(94,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(95,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(96,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(97,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03'),(98,'12345678','Ricardo Humberto','Yanapa Huayta','ricardo@gmail.com','+51 949 674 479','18','1802','180211','Av.Direction #775','2025-01-13 15:23:03');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semillas`
--

LOCK TABLES `semillas` WRITE;
/*!40000 ALTER TABLE `semillas` DISABLE KEYS */;
INSERT INTO `semillas` VALUES (10,NULL,'Laptop HP','Laptop HP con 16GB RAM y 512GB SSD',1250.5,0,'2025-01-08 00:39:46'),(11,NULL,'Mouse Logitech','Mouse inalámbrico Logitech',25.99,49,'2025-01-08 00:39:46'),(12,NULL,'Teclado Mecánico','Teclado mecánico retroiluminado',75,29,'2025-01-08 00:39:46'),(13,NULL,'Monitor Samsung','Monitor LED de 24 pulgadas',180.75,16,'2025-01-08 00:39:46'),(14,NULL,'Disco Duro Externo','Disco duro externo de 1TB',65,37,'2025-01-08 00:39:46'),(15,NULL,'Memoria USB','Memoria USB 64GB Kingston',12.99,99,'2025-01-08 00:39:46'),(16,NULL,'Smartphone Xiaomi','Smartphone Xiaomi 128GB',300,24,'2025-01-08 00:39:46'),(17,NULL,'Tablet Samsung','Tablet Samsung Galaxy Tab 10\"',220,10,'2025-01-08 00:39:46'),(18,NULL,'Impresora Epson','Impresora multifuncional Epson',150,8,'2025-01-08 00:39:46'),(19,NULL,'Auriculares Sony','Auriculares inalámbricos Sony',90.5,35,'2025-01-08 00:39:46'),(20,NULL,'Cargador Portátil','Cargador portátil de 10,000mAh',35,60,'2025-01-08 00:39:46'),(22,NULL,'Router TP-Link','Router inalámbrico de alta velocidad',55,18,'2025-01-08 00:39:46'),(23,NULL,'Cámara Web','Cámara web Full HD Logitech',65.99,22,'2025-01-08 00:39:46'),(24,NULL,'Altavoces Bluetooth','Altavoces portátiles Bluetooth',45.5,12,'2025-01-08 00:39:46'),(25,NULL,'SSD Kingston','Unidad SSD Kingston 500GB',85,15,'2025-01-08 00:39:46'),(26,NULL,'Tarjeta de Video','Tarjeta gráfica NVIDIA GTX 1660',450,5,'2025-01-08 00:39:46'),(27,NULL,'Micro SD Sandisk','Tarjeta micro SD Sandisk 128GB',30.99,80,'2025-01-08 00:39:46'),(28,NULL,'Silla Gamer','Silla ergonómica para gaming',200,7,'2025-01-08 00:39:46'),(29,NULL,'Escritorio','Escritorio de oficina con soporte',150,9,'2025-01-08 00:39:46');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (9,'asdasdasd','$2a$10$ZRKvIcHcO/RXEFtvd.wsf.Ca.UiQkSq702CLn/PM2eWlVxUEErmLG','asdasdads','asdasdasd','asdasd@asdasd','','','Empleado','2025-01-13 22:27:07'),(13,'zxczxczxc','$2a$10$UmAowWDyIj7T8nYFQqDPhuEoK.Q1hj7mhCo4Al7qBBAt6DOZI8ovG','zxczxczxc','zxczxczxc','zxc@zxc','123 123 123','zxczxczxc','Administrador','2025-01-13 13:28:08'),(14,'qweqweqwe','$2a$10$N2IEXhO31ce5KnSbP.Lnme9zo8t/nUMZnU02XgVYtFP5VefQP9VIm','qweqweqwe','qweqweqwe','qwqweqwe@qweqweqwe','123 123 123','qweqweqwe','Cliente','2025-01-13 21:40:19'),(15,'JesusSAIU','$2a$10$pFapwqrs8Li1YwuAtC3fPuGyhWsZ9MGSKr.0cyrdNj11py9G7KOVO','Jesus ','Hernandez Gallegos','jesus_ru_14@hotmail.com','960 691 971','jr separador industrial mz c lt 20','Cliente','2025-01-13 21:56:56');
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

-- Dump completed on 2025-01-13 17:37:36
