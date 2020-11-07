CREATE DATABASE  IF NOT EXISTS `ssafymeal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssafymeal`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafymeal
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `complist`
--

DROP TABLE IF EXISTS `complist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_no` int NOT NULL,
  `user_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `complist_user_no_food_no_35be2387_uniq` (`user_no`,`food_no`),
  KEY `complist_food_no_7fc37bf6_fk_food_food_no` (`food_no`),
  CONSTRAINT `complist_food_no_7fc37bf6_fk_food_food_no` FOREIGN KEY (`food_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE,
  CONSTRAINT `complist_user_no_81e57108_fk_user_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complist`
--

LOCK TABLES `complist` WRITE;
/*!40000 ALTER TABLE `complist` DISABLE KEYS */;
/*!40000 ALTER TABLE `complist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diethistory`
--

DROP TABLE IF EXISTS `diethistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diethistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `eat_date` datetime(6) NOT NULL,
  `eat_type` varchar(1) NOT NULL,
  `nutrient` varchar(20) NOT NULL,
  `eat_amount` int NOT NULL,
  `user_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `diethistory_user_no_eat_date_d32a7ac2_uniq` (`user_no`,`eat_date`),
  CONSTRAINT `diethistory_user_no_f4cd5b42_fk_user_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diethistory`
--

LOCK TABLES `diethistory` WRITE;
/*!40000 ALTER TABLE `diethistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `diethistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flavor`
--

DROP TABLE IF EXISTS `flavor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flavor` (
  `flavor_no` int NOT NULL,
  `flavor_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`flavor_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flavor`
--

LOCK TABLES `flavor` WRITE;
/*!40000 ALTER TABLE `flavor` DISABLE KEYS */;
/*!40000 ALTER TABLE `flavor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flavorpref`
--

DROP TABLE IF EXISTS `flavorpref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flavorpref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pref_score` double NOT NULL,
  `flavor_no` int NOT NULL,
  `user_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `flavorpref_user_no_flavor_no_0ccc7e1b_uniq` (`user_no`,`flavor_no`),
  KEY `flavorpref_flavor_no_2ff24585_fk_flavor_flavor_no` (`flavor_no`),
  CONSTRAINT `flavorpref_flavor_no_2ff24585_fk_flavor_flavor_no` FOREIGN KEY (`flavor_no`) REFERENCES `flavor` (`flavor_no`) ON DELETE CASCADE,
  CONSTRAINT `flavorpref_user_no_c81175a7_fk_user_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flavorpref`
--

LOCK TABLES `flavorpref` WRITE;
/*!40000 ALTER TABLE `flavorpref` DISABLE KEYS */;
/*!40000 ALTER TABLE `flavorpref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `food_no` int NOT NULL AUTO_INCREMENT,
  `food_name` varchar(50) NOT NULL,
  `food_type` varchar(10) NOT NULL,
  `price` int DEFAULT NULL,
  `cal` int DEFAULT NULL,
  `manufacturer` varchar(50) DEFAULT NULL,
  `group_no` int DEFAULT NULL,
  `store_no` int DEFAULT NULL,
  PRIMARY KEY (`food_no`),
  KEY `food_group_no_f19d7194_fk_food_food_no` (`group_no`),
  KEY `food_store_no_361d8f51_fk_store_store_no` (`store_no`),
  CONSTRAINT `food_group_no_f19d7194_fk_food_food_no` FOREIGN KEY (`group_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE,
  CONSTRAINT `food_store_no_361d8f51_fk_store_store_no` FOREIGN KEY (`store_no`) REFERENCES `store` (`store_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodflavor`
--

DROP TABLE IF EXISTS `foodflavor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodflavor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flavor_no` int NOT NULL,
  `food_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `foodflavor_food_no_flavor_no_f35b7819_uniq` (`food_no`,`flavor_no`),
  KEY `foodflavor_flavor_no_e62f27f2_fk_flavor_flavor_no` (`flavor_no`),
  CONSTRAINT `foodflavor_flavor_no_e62f27f2_fk_flavor_flavor_no` FOREIGN KEY (`flavor_no`) REFERENCES `flavor` (`flavor_no`) ON DELETE CASCADE,
  CONSTRAINT `foodflavor_food_no_40aceb4a_fk_food_food_no` FOREIGN KEY (`food_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodflavor`
--

LOCK TABLES `foodflavor` WRITE;
/*!40000 ALTER TABLE `foodflavor` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodflavor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodnutrient`
--

DROP TABLE IF EXISTS `foodnutrient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodnutrient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nutrient` varchar(20) NOT NULL,
  `content` int NOT NULL,
  `food_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `foodnutrient_food_no_nutrient_fbd477d3_uniq` (`food_no`,`nutrient`),
  CONSTRAINT `foodnutrient_food_no_57f4cbfb_fk_food_food_no` FOREIGN KEY (`food_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodnutrient`
--

LOCK TABLES `foodnutrient` WRITE;
/*!40000 ALTER TABLE `foodnutrient` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodnutrient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodpref`
--

DROP TABLE IF EXISTS `foodpref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodpref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pref_score` double NOT NULL,
  `food_no` int NOT NULL,
  `user_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `foodpref_user_no_food_no_5d400d39_uniq` (`user_no`,`food_no`),
  KEY `foodpref_food_no_1702c5ed_fk_food_food_no` (`food_no`),
  CONSTRAINT `foodpref_food_no_1702c5ed_fk_food_food_no` FOREIGN KEY (`food_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE,
  CONSTRAINT `foodpref_user_no_e834fe79_fk_user_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodpref`
--

LOCK TABLES `foodpref` WRITE;
/*!40000 ALTER TABLE `foodpref` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodpref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preftest`
--

DROP TABLE IF EXISTS `preftest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preftest` (
  `test_no` int NOT NULL AUTO_INCREMENT,
  `test_date` datetime(6) NOT NULL,
  `user_no` int NOT NULL,
  PRIMARY KEY (`test_no`),
  KEY `preftest_user_no_b1222dbb_fk_user_user_no` (`user_no`),
  CONSTRAINT `preftest_user_no_b1222dbb_fk_user_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preftest`
--

LOCK TABLES `preftest` WRITE;
/*!40000 ALTER TABLE `preftest` DISABLE KEYS */;
/*!40000 ALTER TABLE `preftest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preftestdetail`
--

DROP TABLE IF EXISTS `preftestdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preftestdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` double NOT NULL,
  `food_no` int NOT NULL,
  `test_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `preftestdetail_test_no_food_no_969c1969_uniq` (`test_no`,`food_no`),
  KEY `preftestdetail_food_no_1fa41f34_fk_food_food_no` (`food_no`),
  CONSTRAINT `preftestdetail_food_no_1fa41f34_fk_food_food_no` FOREIGN KEY (`food_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE,
  CONSTRAINT `preftestdetail_test_no_e59bd376_fk_preftest_test_no` FOREIGN KEY (`test_no`) REFERENCES `preftest` (`test_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preftestdetail`
--

LOCK TABLES `preftestdetail` WRITE;
/*!40000 ALTER TABLE `preftestdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `preftestdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_no` int NOT NULL AUTO_INCREMENT,
  `write_date` datetime(6) NOT NULL,
  `score` double NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `food_no` int NOT NULL,
  `user_no` int NOT NULL,
  PRIMARY KEY (`review_no`),
  KEY `review_food_no_bc317920_fk_food_food_no` (`food_no`),
  KEY `review_user_no_72d59262_fk_user_user_no` (`user_no`),
  CONSTRAINT `review_food_no_bc317920_fk_food_food_no` FOREIGN KEY (`food_no`) REFERENCES `food` (`food_no`) ON DELETE CASCADE,
  CONSTRAINT `review_user_no_72d59262_fk_user_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `store_no` int NOT NULL AUTO_INCREMENT,
  `store_name` varchar(30) NOT NULL,
  `branch_name` varchar(30) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `tel_no` varchar(20) DEFAULT NULL,
  `address` varchar(150) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  PRIMARY KEY (`store_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storecategory`
--

DROP TABLE IF EXISTS `storecategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storecategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(30) NOT NULL,
  `store_no` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `storecategory_store_no_category_1037d980_uniq` (`store_no`,`category`),
  CONSTRAINT `storecategory_store_no_799d3cbf_fk_store_store_no` FOREIGN KEY (`store_no`) REFERENCES `store` (`store_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storecategory`
--

LOCK TABLES `storecategory` WRITE;
/*!40000 ALTER TABLE `storecategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `storecategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `user_pw` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `nick_name` varchar(20) NOT NULL,
  `gender` varchar(1) DEFAULT 'm',
  `address` varchar(130) NOT NULL,
  `height` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `born_year` int DEFAULT NULL,
  `plan_carb` int DEFAULT NULL,
  `plan_protein` int DEFAULT NULL,
  `plan_fat` int DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ssafymeal'
--

--
-- Dumping routines for database 'ssafymeal'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-22  0:21:13
alter table user add column weight_type varchar(1) not null;