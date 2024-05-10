-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: pms
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `properties`
--

DROP TABLE IF EXISTS `properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `properties` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Description` text,
  `PricePerMonth` decimal(10,2) NOT NULL,
  `AvailableFrom` date NOT NULL,
  `AvailableUntil` date DEFAULT NULL,
  `TenantName` varchar(255) DEFAULT NULL,
  `TenantNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `properties_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `properties`
--

LOCK TABLES `properties` WRITE;
/*!40000 ALTER TABLE `properties` DISABLE KEYS */;
INSERT INTO `properties` VALUES (1,1,'Cozy Apartment in Downtown','123 Main Street, City Center','Modern apartment with city views',1500.00,'2024-06-01','2024-12-31','John Doe','+1 123-456-7890'),(2,2,'Family House in Suburbia','456 Oak Avenue, Suburb Town','Spacious house with a backyard',2000.00,'2024-07-15','2025-07-15','Jane Smith','+1 987-654-3210'),(3,3,'Luxury Condo by the Beach','789 Ocean Boulevard, Beach City','Oceanfront condo with resort amenities',3000.00,'2024-08-01',NULL,NULL,NULL),(4,2,'Cozy Apartment in Downtown','123 Main Street, City Center','Modern apartment with city views',1500.00,'2024-06-01','2024-12-31','John Doe','+1 123-456-7890'),(5,1,'Family House in Suburbia','456 Oak Avenue, Suburb Town','Spacious house with a backyard',2000.00,'2024-07-15','2025-07-15','Jane Smith','+1 987-654-3210'),(6,2,'Luxury Condo by the Beach','789 Ocean Boulevard, Beach City','Oceanfront condo with resort amenities',3000.00,'2024-08-01',NULL,NULL,NULL),(7,1,'Studio Apartment in Arts District','321 Gallery Street, Arts District','Charming studio with artistic vibes',1200.00,'2024-06-15','2025-06-15','Alice Johnson','+1 234-567-8901'),(8,3,'Suburban Villa with Garden','789 Garden Lane, Suburbia','Spacious villa with a beautiful garden',2500.00,'2024-07-01','2025-07-01','Michael Brown','+1 345-678-9012'),(9,2,'Downtown Loft with City Views','567 Urban Avenue, City Center','Modern loft with panoramic city views',1800.00,'2024-08-15',NULL,NULL,NULL);
/*!40000 ALTER TABLE `properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `role` varchar(50) DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'John Doe','johndoe@example.com','123','123-456-7890','user'),(2,'Jane Smith','janesmith@example.com','123','987-654-3210','user'),(3,'Alice Johnson','alicejohnson@example.com','123','555-123-4567','user'),(4,'fares','fares','123','555555','ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 17:56:12
