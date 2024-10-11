-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: 13.124.169.106    Database: lineup
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `application_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `applicant_id` bigint DEFAULT NULL,
  `chat_room_id` bigint DEFAULT NULL,
  `recruit_id` bigint NOT NULL,
  PRIMARY KEY (`application_id`),
  UNIQUE KEY `UKstr1iba6l1af2ocfii37le5dm` (`recruit_id`,`applicant_id`),
  UNIQUE KEY `UK42pbwh4xs9bbgql28l6pph5qk` (`chat_room_id`),
  KEY `FK1vur8fl2uitglu5w2wbix94fi` (`applicant_id`),
  CONSTRAINT `FK1vur8fl2uitglu5w2wbix94fi` FOREIGN KEY (`applicant_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK8swfqiefow498cbrucxacoxh8` FOREIGN KEY (`recruit_id`) REFERENCES `recruit` (`recruit_id`),
  CONSTRAINT `FKndj233i82d2vdflrpt7wy1vhn` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`chat_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` (`application_id`, `created_at`, `deleted_at`, `modified_at`, `applicant_id`, `chat_room_id`, `recruit_id`) VALUES (1,'2024-10-11 12:05:23.094229',NULL,'2024-10-11 12:05:23.153328',3,1,1),(2,'2024-10-11 12:06:42.808208',NULL,'2024-10-11 12:06:42.864205',4,2,1),(3,'2024-10-11 12:10:13.718431',NULL,'2024-10-11 12:10:13.774080',5,3,1),(4,'2024-10-11 12:11:26.042933',NULL,'2024-10-11 12:11:26.099494',4,4,4);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_member`
--

DROP TABLE IF EXISTS `chat_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_member` (
  `chat_member_id` bigint NOT NULL AUTO_INCREMENT,
  `enter_at` datetime(6) DEFAULT NULL,
  `recent_exit_at` datetime(6) DEFAULT NULL,
  `chat_room_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chat_member_id`),
  KEY `FKp3ov6ys5mw1i7e9va4nniwa5q` (`chat_room_id`),
  KEY `FKeji2r41n0gs983m1m5p0bytkx` (`user_id`),
  CONSTRAINT `FKeji2r41n0gs983m1m5p0bytkx` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKp3ov6ys5mw1i7e9va4nniwa5q` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`chat_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_member`
--

LOCK TABLES `chat_member` WRITE;
/*!40000 ALTER TABLE `chat_member` DISABLE KEYS */;
INSERT INTO `chat_member` (`chat_member_id`, `enter_at`, `recent_exit_at`, `chat_room_id`, `user_id`) VALUES (1,'2024-10-11 12:05:23.124085',NULL,1,3),(2,'2024-10-11 12:05:23.136661',NULL,1,2),(3,'2024-10-11 12:06:42.833865',NULL,2,4),(4,'2024-10-11 12:06:42.849138',NULL,2,2),(5,'2024-10-11 12:10:13.742500',NULL,3,5),(6,'2024-10-11 12:10:13.754362',NULL,3,2),(7,'2024-10-11 12:11:26.065426',NULL,4,4),(8,'2024-10-11 12:11:26.078451',NULL,4,1);
/*!40000 ALTER TABLE `chat_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `chat_message_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `chat_room_id` bigint DEFAULT NULL,
  `content` mediumtext,
  `content_type` enum('IMAGE','SYSTEM','TEXT') DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chat_message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` (`chat_message_id`, `created_at`, `deleted_at`, `modified_at`, `chat_room_id`, `content`, `content_type`, `is_read`, `sender_id`) VALUES (1,'2024-10-11 12:11:28.774029',NULL,'2024-10-11 12:11:40.866971',4,'ㅎㅇ','TEXT',_binary '',4),(2,'2024-10-11 12:11:42.452795',NULL,'2024-10-11 12:11:42.490307',4,'ㅎㅇ','TEXT',_binary '',4),(3,'2024-10-11 12:11:44.650639',NULL,'2024-10-11 12:11:44.685700',4,'반갑습니다','TEXT',_binary '',1),(4,'2024-10-11 12:11:49.795470',NULL,'2024-10-11 12:11:49.837113',4,'싸피 다니시는 분 맞죠','TEXT',_binary '',1),(5,'2024-10-11 12:11:51.341573',NULL,'2024-10-11 12:11:51.377843',4,'ㅎㅇ','TEXT',_binary '',4),(6,'2024-10-11 12:11:54.126585',NULL,'2024-10-11 12:11:54.159237',4,'ㅎㅇ','TEXT',_binary '',4),(7,'2024-10-11 12:12:02.968918',NULL,'2024-10-11 12:13:26.168488',4,'ㅎㅇ','TEXT',_binary '',4),(8,'2024-10-11 12:12:04.306870',NULL,'2024-10-11 12:13:26.179966',4,'ㅎㅇ','TEXT',_binary '',4),(9,'2024-10-11 12:12:05.614503',NULL,'2024-10-11 12:13:26.192233',4,'ㅎㅇ','TEXT',_binary '',4),(10,'2024-10-11 12:12:06.709270',NULL,'2024-10-11 12:13:26.206477',4,'ㅎㅇ','TEXT',_binary '',4),(11,'2024-10-11 12:12:07.898032',NULL,'2024-10-11 12:13:26.217984',4,'ㅎㅇ','TEXT',_binary '',4),(12,'2024-10-11 12:12:10.323917',NULL,'2024-10-11 12:13:26.231987',4,'ㅎㅇ','TEXT',_binary '',4),(13,'2024-10-11 12:12:11.670080',NULL,'2024-10-11 12:13:26.246208',4,'ㅎㅇ','TEXT',_binary '',4),(14,'2024-10-11 12:13:35.333122',NULL,'2024-10-11 12:17:06.462668',4,'네 저도 반갑고요 ㅎㅎ;','TEXT',_binary '',1),(15,'2024-10-11 12:17:08.469730',NULL,'2024-10-11 12:17:30.222738',4,'gd','TEXT',_binary '',4),(16,'2024-10-11 12:17:32.900680',NULL,'2024-10-11 12:17:32.946750',4,'ㅎㅇ','TEXT',_binary '',1),(17,'2024-10-11 12:17:35.086166',NULL,'2024-10-11 12:17:35.130846',4,'gd','TEXT',_binary '',4),(18,'2024-10-11 12:18:28.868922',NULL,'2024-10-11 12:18:28.868922',4,'줄 서기 계약 가시죠 그럼','TEXT',_binary '\0',1);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `chat_room_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`chat_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` (`chat_room_id`, `created_at`, `deleted_at`, `modified_at`) VALUES (1,'2024-10-11 12:05:23.110693',NULL,'2024-10-11 12:05:23.110693'),(2,'2024-10-11 12:06:42.821553',NULL,'2024-10-11 12:06:42.821553'),(3,'2024-10-11 12:10:13.731438',NULL,'2024-10-11 12:10:13.731438'),(4,'2024-10-11 12:11:26.055005',NULL,'2024-10-11 12:11:26.055005');
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `contract_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `contract_status` enum('WORK_END','WORK_END_PURCHASE_VERIFICATION_SUCCESS','WORK_END_QUEUE_VERIFICATION_SUCCESS','WORK_START','WORK_START_BEFORE','WORK_START_VERIFICATION_SUCCESS') DEFAULT NULL,
  `contract_uuid` varchar(256) DEFAULT NULL,
  `contractee_signature_filename` varchar(64) DEFAULT NULL,
  `contractor_signature_filename` varchar(64) DEFAULT NULL,
  `work_end_verification_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `recruit_id` bigint NOT NULL,
  PRIMARY KEY (`contract_id`),
  UNIQUE KEY `UKb0nlsgmn8885jfsyavlkau3cl` (`user_id`),
  UNIQUE KEY `UKif6yw5g9bdakyjqktteor00lh` (`recruit_id`),
  CONSTRAINT `FK5s7r1nq49s36ndq7nlri6hxf7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKdp69tlmd9gkabj008stwhsgkx` FOREIGN KEY (`recruit_id`) REFERENCES `recruit` (`recruit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` (`contract_id`, `created_at`, `deleted_at`, `modified_at`, `contract_status`, `contract_uuid`, `contractee_signature_filename`, `contractor_signature_filename`, `work_end_verification_id`, `user_id`, `recruit_id`) VALUES (1,'2024-10-11 12:05:31.419017',NULL,'2024-10-11 12:05:31.539357','WORK_START_BEFORE','fc1da359-57b3-4098-95d1-9ec0028be6db',NULL,'63fa82c9-35a7-48c0-9462-f3ef110d90cf.png',NULL,3,1);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_request`
--

DROP TABLE IF EXISTS `contract_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_request` (
  `contract_request_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `contract_request_status` enum('ACCEPT','WAITING') DEFAULT NULL,
  `contractee_signature_filename` varchar(64) DEFAULT NULL,
  `application_id` bigint DEFAULT NULL,
  PRIMARY KEY (`contract_request_id`),
  UNIQUE KEY `UKsfkwc6nyu1v17dounyti90ft1` (`application_id`),
  CONSTRAINT `FK9kxoap4wlgmnoohn9b02ok8xk` FOREIGN KEY (`application_id`) REFERENCES `application` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_request`
--

LOCK TABLES `contract_request` WRITE;
/*!40000 ALTER TABLE `contract_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_recruit`
--

DROP TABLE IF EXISTS `like_recruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_recruit` (
  `like_recruit_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `recruit_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`like_recruit_id`),
  KEY `FKn4x7r1qh32rwbrd2y22egmugu` (`recruit_id`),
  KEY `FKs5ahu47q9jmfpifocsbltj0bo` (`user_id`),
  CONSTRAINT `FKn4x7r1qh32rwbrd2y22egmugu` FOREIGN KEY (`recruit_id`) REFERENCES `recruit` (`recruit_id`),
  CONSTRAINT `FKs5ahu47q9jmfpifocsbltj0bo` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_recruit`
--

LOCK TABLES `like_recruit` WRITE;
/*!40000 ALTER TABLE `like_recruit` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_recruit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `receiver_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmlidwdldgmdw67l7pbrval0un` (`receiver_id`),
  KEY `FKnbt1hengkgjqru2q44q8rlc2c` (`sender_id`),
  CONSTRAINT `FKmlidwdldgmdw67l7pbrval0un` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKnbt1hengkgjqru2q44q8rlc2c` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_end_verification`
--

DROP TABLE IF EXISTS `purchase_end_verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_end_verification` (
  `work_end_verification_id` bigint NOT NULL AUTO_INCREMENT,
  `exchange_at` datetime(6) DEFAULT NULL,
  `purchase_at` datetime(6) DEFAULT NULL,
  `work_end_img_filename` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`work_end_verification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_end_verification`
--

LOCK TABLES `purchase_end_verification` WRITE;
/*!40000 ALTER TABLE `purchase_end_verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_end_verification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queue_end_verification`
--

DROP TABLE IF EXISTS `queue_end_verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `queue_end_verification` (
  `queue_end_verification_id` bigint NOT NULL AUTO_INCREMENT,
  `queue_end_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`queue_end_verification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queue_end_verification`
--

LOCK TABLES `queue_end_verification` WRITE;
/*!40000 ALTER TABLE `queue_end_verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `queue_end_verification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruit`
--

DROP TABLE IF EXISTS `recruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruit` (
  `recruit_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `contractee_deposit` int DEFAULT NULL,
  `end_at` datetime(6) DEFAULT NULL,
  `fail_salary` int DEFAULT NULL,
  `like_count` int DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `street_address` varchar(64) DEFAULT NULL,
  `place_type` enum('CAFE','DEPARTMENT','ETC','POP_UP','RESTAURANT') DEFAULT NULL,
  `recruit_img` varchar(64) DEFAULT NULL,
  `recruit_status` enum('COMPLETED','EXPIRED','RECRUITING') DEFAULT NULL,
  `service_type` enum('PURCHASE','QUEUE') DEFAULT NULL,
  `start_at` datetime(6) DEFAULT NULL,
  `success_salary` int DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `view_count` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`recruit_id`),
  KEY `FKeje1e4a4i3y9fqrel4lfc3xra` (`user_id`),
  CONSTRAINT `FKeje1e4a4i3y9fqrel4lfc3xra` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruit`
--

LOCK TABLES `recruit` WRITE;
/*!40000 ALTER TABLE `recruit` DISABLE KEYS */;
INSERT INTO `recruit` (`recruit_id`, `created_at`, `deleted_at`, `modified_at`, `content`, `contractee_deposit`, `end_at`, `fail_salary`, `like_count`, `district`, `latitude`, `longitude`, `street_address`, `place_type`, `recruit_img`, `recruit_status`, `service_type`, `start_at`, `success_salary`, `title`, `view_count`, `user_id`) VALUES (1,'2024-10-11 12:05:13.939405',NULL,'2024-10-11 12:56:49.900831','50',50,'2024-10-25 16:00:00.000000',50,0,'강남구',37.4924,127.031,'서울특별시 강남구 강남대로 328 (역삼동)','POP_UP',NULL,'COMPLETED','QUEUE','2024-10-14 15:00:00.000000',50,'루피오픈런구해요',29,2),(2,'2024-10-11 12:05:59.421496',NULL,'2024-10-11 12:35:04.670683','강원도 조랑말 농장입니다.. 꼭 한번 가보고 싶어요!!',1000,'2024-10-20 00:00:00.000000',3000,0,'철원군',38.1885,127.337,'강원특별자치도 철원군 갈말읍 호국로 5312','POP_UP','147de2a6-1e94-4914-82b4-0c323d2fd9e4.jpg','RECRUITING','QUEUE','2024-10-19 21:00:00.000000',5000,'동물 농장 티케팅 줄 서 주세요!!',3,1),(3,'2024-10-11 12:06:54.437762',NULL,'2024-10-11 12:09:38.079174','오메가 시계 온라인 사전 예약 모집합니다. 자세한 내용은 채팅주세요.',30000,'2024-10-25 20:00:00.000000',50000,0,'강남구',37.5274,127.027,'서울특별시 강남구 압구정로 165 (압구정동)','RESTAURANT',NULL,'RECRUITING','QUEUE','2024-10-25 15:00:00.000000',499997,'오메가 시계 온라인 예약 모집합니다.',2,3),(4,'2024-10-11 12:09:18.481357',NULL,'2024-10-11 12:25:31.263373','점심에 6명짜리 테이블 잡아주실 분 구해요!!',500,'2024-10-15 22:00:00.000000',1000,0,'강남구',37.5013,127.04,'서울특별시 강남구 테헤란로 212 (역삼동)','RESTAURANT','3fafc131-21e9-4e26-a93a-f3cdd77d684b.jpg','RECRUITING','QUEUE','2024-10-14 21:00:00.000000',2000,'싸피 점심 때 자리 잡기 저만 힘드나요',25,1);
/*!40000 ALTER TABLE `recruit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer_detail`
--

DROP TABLE IF EXISTS `transfer_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer_detail` (
  `transfer_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `pay_at` datetime(6) DEFAULT NULL,
  `pay_status` enum('CASH_PAY','CASH_REFUND','MONEY_IN','MONEY_OUT') DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`transfer_detail_id`),
  KEY `FK88trxkc1n3mngcjqwtr9b3fkm` (`user_id`),
  CONSTRAINT `FK88trxkc1n3mngcjqwtr9b3fkm` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer_detail`
--

LOCK TABLES `transfer_detail` WRITE;
/*!40000 ALTER TABLE `transfer_detail` DISABLE KEYS */;
INSERT INTO `transfer_detail` (`transfer_detail_id`, `amount`, `pay_at`, `pay_status`, `user_id`) VALUES (1,50,'2024-10-11 12:05:31.425044','CASH_PAY',2);
/*!40000 ALTER TABLE `transfer_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `birth_year` date DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `profile_img_filename` varchar(64) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(64) DEFAULT NULL,
  `real_name` varchar(64) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKn4swgcf30j6bmtb4l4cjryuym` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `created_at`, `deleted_at`, `modified_at`, `birth_year`, `email`, `gender`, `nickname`, `profile_img_filename`, `provider`, `provider_id`, `real_name`, `role`) VALUES (1,'2024-10-11 12:04:13.434546',NULL,'2024-10-11 12:04:22.954766',NULL,'mark3473@naver.com',NULL,'동균짱','f9e3b3d6-7ed1-4c70-9d50-725d4176574c.png','kakao-3736822452',NULL,NULL,'ROLE_USER'),(2,'2024-10-11 12:04:37.276953',NULL,'2024-10-11 12:04:37.276953',NULL,'greencloud23@naver.com',NULL,'김싸피','0311fb67-2e5c-4e1a-9a0c-0cb60d63795d.png','naver-scy3b-VtDQwYE1QzqvnShrpTomQ_3p5M-XsF6j0QYco',NULL,NULL,NULL),(3,'2024-10-11 12:04:42.668879',NULL,'2024-10-11 12:05:11.179114',NULL,'c880910@naver.com',NULL,'뭉기','0311fb67-2e5c-4e1a-9a0c-0cb60d63795d.png','kakao-3728572470',NULL,NULL,'ROLE_USER'),(4,'2024-10-11 12:06:24.229459',NULL,'2024-10-11 12:06:36.517760',NULL,'jangsy1207@naver.com',NULL,'안녕하세여','cd511105-63b8-4bae-bd61-d54f1e4afab0.png','naver-MKoCE1w5j2S61ASB_9K42X9oijY7Qwuuxwr_BIlHMqA',NULL,NULL,'ROLE_USER'),(5,'2024-10-11 12:08:27.638722',NULL,'2024-10-11 12:08:27.638722',NULL,'hanol98@naver.com',NULL,'춘배','0311fb67-2e5c-4e1a-9a0c-0cb60d63795d.png','kakao-3736333142',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_cash`
--

DROP TABLE IF EXISTS `user_cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_cash` (
  `user_cash_id` bigint NOT NULL,
  `cash` int DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_cash_id`),
  UNIQUE KEY `UKnv3posgp68yjy3us4y33lfvgy` (`user_id`),
  CONSTRAINT `FKrlu2tfme2mt67kjwmf2l0q769` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_cash`
--

LOCK TABLES `user_cash` WRITE;
/*!40000 ALTER TABLE `user_cash` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_cash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_service_statistic`
--

DROP TABLE IF EXISTS `user_service_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_service_statistic` (
  `user_service_statistic_id` bigint NOT NULL AUTO_INCREMENT,
  `fail_count` int DEFAULT NULL,
  `success_count` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_service_statistic_id`),
  UNIQUE KEY `UKn7bibcnasguxk4g3cw6hhj6o6` (`user_id`),
  CONSTRAINT `FKnm4e2d78ys92207wh5qt1ph4i` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_service_statistic`
--

LOCK TABLES `user_service_statistic` WRITE;
/*!40000 ALTER TABLE `user_service_statistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_service_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification`
--

DROP TABLE IF EXISTS `verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification` (
  `verification_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `gpsverified` tinyint(1) DEFAULT '0',
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `photo_verified` tinyint(1) DEFAULT '0',
  `qr_code_content` varchar(255) DEFAULT NULL,
  `service_type` enum('PURCHASE','QUEUE') DEFAULT NULL,
  `verification_type` enum('CHECK_IN','CHECK_OUT','PURCHASE') DEFAULT NULL,
  `verified` tinyint(1) DEFAULT '0',
  `contract_id` bigint NOT NULL,
  PRIMARY KEY (`verification_id`),
  KEY `FK6lbq29ajyanscw7rk35lmqioa` (`contract_id`),
  CONSTRAINT `FK6lbq29ajyanscw7rk35lmqioa` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification`
--

LOCK TABLES `verification` WRITE;
/*!40000 ALTER TABLE `verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_start_verification`
--

DROP TABLE IF EXISTS `work_start_verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_start_verification` (
  `work_start_verification_id` bigint NOT NULL AUTO_INCREMENT,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `work_start_at` datetime(6) DEFAULT NULL,
  `work_start_img_filename` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`work_start_verification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_start_verification`
--

LOCK TABLES `work_start_verification` WRITE;
/*!40000 ALTER TABLE `work_start_verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_start_verification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-11 13:00:10
