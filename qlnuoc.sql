-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlynuoc
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int unsigned NOT NULL,
  `id_staff` int DEFAULT NULL,
  `id_water_money` int DEFAULT NULL,
  `sum_money` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hd_nv_idx` (`id_staff`),
  KEY `fk_hd_tn_idx` (`id_water_money`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,1,20);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_register`
--

DROP TABLE IF EXISTS `customer_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_register` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_house` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `data_birth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `status` int DEFAULT '0',
  `id_supplier` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idsupplier_idx` (`id_supplier`),
  CONSTRAINT `idsupplier` FOREIGN KEY (`id_supplier`) REFERENCES `water_supplier` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_register`
--

LOCK TABLES `customer_register` WRITE;
/*!40000 ALTER TABLE `customer_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `household`
--

DROP TABLE IF EXISTS `household`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `household` (
  `code_house` varchar(255) NOT NULL,
  `name_house` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_birth` date DEFAULT NULL,
  `id_supplier` int DEFAULT NULL,
  PRIMARY KEY (`code_house`),
  KEY `fk_hgd_ncc_idx` (`id_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `household`
--

LOCK TABLES `household` WRITE;
/*!40000 ALTER TABLE `household` DISABLE KEYS */;
INSERT INTO `household` VALUES ('MDB01','Nguyễn Viết Cường','Phường Phúc Xá, Quận Ba Đình, Thành phố Hà Nội','1999-12-14',2),('MDB02','Đặng Tiến Đạt','SN53, Phường Phúc Xá, Quận Ba Đình, Thành phố Hà Nội','2000-12-27',1);
/*!40000 ALTER TABLE `household` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_user`
--

DROP TABLE IF EXISTS `m_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_user` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `authority` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `delete_flg` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_user`
--

LOCK TABLES `m_user` WRITE;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
INSERT INTO `m_user` VALUES (2,'admin','$2a$10$xj2H2p/Vn2Kdk0WeJqfosOqMT7fWT4jXSccg8bY233prfcykoVy26','A','0');
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `diachi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Dang Tien Dat','Ha Noi','0528129662',20);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water_money`
--

DROP TABLE IF EXISTS `water_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `water_money` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_water` date DEFAULT NULL,
  `number_water` int DEFAULT NULL,
  `code_house` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkhgd_idx` (`code_house`),
  CONSTRAINT `fk_tn_hgd` FOREIGN KEY (`code_house`) REFERENCES `household` (`code_house`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water_money`
--

LOCK TABLES `water_money` WRITE;
/*!40000 ALTER TABLE `water_money` DISABLE KEYS */;
INSERT INTO `water_money` VALUES (1,'2022-03-12',15,'MDB01'),(2,'2022-03-13',16,'MDB01'),(15,'2022-03-16',15,'MDB01');
/*!40000 ALTER TABLE `water_money` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water_supplier`
--

DROP TABLE IF EXISTS `water_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `water_supplier` (
  `id` int NOT NULL,
  `name_supplier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water_supplier`
--

LOCK TABLES `water_supplier` WRITE;
/*!40000 ALTER TABLE `water_supplier` DISABLE KEYS */;
INSERT INTO `water_supplier` VALUES (1,'Cấp nước Viwaco - Hà Nội','17 Nguyễn Thị Thập, Trung Hoà, Thanh Xuân, Hà Nội','0246251520','info@viwaco.vn','024.6251.1524'),(2,'Công ty Nước sạch Hà Nội (HAWACOM)','Số 44 Đường Yên phụ, Ba Đình, TP Hà Nội','19004600',NULL,'024.48 29 20 69'),(3,'Cấp Nước Sơn Tây','Số 193 Đường Lê Lợi, Phường Lê Lợi, TX Sơn Tây, TP. Hà Nội',' (024) 3383 2462','nuocsachsontay@gmail.com','(024) 3383 246');
/*!40000 ALTER TABLE `water_supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-18 21:37:38
