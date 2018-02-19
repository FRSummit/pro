-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: hrm
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `absent` int(11) DEFAULT NULL,
  `link1` varchar(255) DEFAULT NULL,
  `link2` varchar(255) DEFAULT NULL,
  `link3` varchar(255) DEFAULT NULL,
  `link4` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `late_by` datetime DEFAULT NULL,
  `out_time` datetime DEFAULT NULL,
  `over_time` datetime DEFAULT NULL,
  `performance` double DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  `work_duration` varchar(255) DEFAULT NULL,
  `work_hr_day` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_user`
--

DROP TABLE IF EXISTS `attendance_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance_user` (
  `attend_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`attend_id`,`user_id`),
  KEY `FKmq1kc386m1rj5pc3i2k5krww3` (`user_id`),
  CONSTRAINT `FKb0xuwxi07sy14u4hx3pe985jc` FOREIGN KEY (`attend_id`) REFERENCES `attendance` (`id`),
  CONSTRAINT `FKmq1kc386m1rj5pc3i2k5krww3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_user`
--

LOCK TABLES `attendance_user` WRITE;
/*!40000 ALTER TABLE `attendance_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_record`
--

DROP TABLE IF EXISTS `hr_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` int(11) NOT NULL,
  `balance_maternity` int(11) NOT NULL,
  `balance_other` int(11) NOT NULL,
  `balance_personal` int(11) NOT NULL,
  `balance_planned` int(11) NOT NULL,
  `balance_sick` int(11) NOT NULL,
  `balance_vacation` int(11) NOT NULL,
  `total_leave` int(11) NOT NULL,
  `total_leave_maternity` int(11) NOT NULL,
  `total_leave_other` int(11) NOT NULL,
  `total_leave_personal` int(11) NOT NULL,
  `total_leave_planned` int(11) NOT NULL,
  `total_leave_sick` int(11) NOT NULL,
  `taken` int(11) NOT NULL,
  `taken_maternity` int(11) NOT NULL,
  `taken_other` int(11) NOT NULL,
  `taken_personal` int(11) NOT NULL,
  `taken_planned` int(11) NOT NULL,
  `taken_sick` int(11) NOT NULL,
  `taken_vacation` int(11) NOT NULL,
  `total_leave_vacation` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_record`
--

LOCK TABLES `hr_record` WRITE;
/*!40000 ALTER TABLE `hr_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `hr_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_record_user`
--

DROP TABLE IF EXISTS `hr_record_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_record_user` (
  `hr_record_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`hr_record_id`,`user_id`),
  KEY `FKawqvajloqffdq3wpw2bo6t0wq` (`user_id`),
  CONSTRAINT `FKawqvajloqffdq3wpw2bo6t0wq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlo0bjk40dqh6dqv6psldjgcr2` FOREIGN KEY (`hr_record_id`) REFERENCES `hr_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_record_user`
--

LOCK TABLES `hr_record_user` WRITE;
/*!40000 ALTER TABLE `hr_record_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `hr_record_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_user`
--

DROP TABLE IF EXISTS `leave_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_user` (
  `leave_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`leave_id`,`user_id`),
  KEY `FKinxpmu31urc9keopa7pq7y03f` (`user_id`),
  CONSTRAINT `FKcdif9f76mmvdgdguilpwa9ut0` FOREIGN KEY (`leave_id`) REFERENCES `leaves` (`id`),
  CONSTRAINT `FKinxpmu31urc9keopa7pq7y03f` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_user`
--

LOCK TABLES `leave_user` WRITE;
/*!40000 ALTER TABLE `leave_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaves`
--

DROP TABLE IF EXISTS `leaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leaves` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_whom` varchar(255) NOT NULL,
  `action_by` varchar(255) NOT NULL,
  `apply_date` datetime NOT NULL,
  `apply_from` datetime NOT NULL,
  `apply_to` datetime NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `modify_to` varchar(255) NOT NULL,
  `total_leave_day` int(11) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaves`
--

LOCK TABLES `leaves` WRITE;
/*!40000 ALTER TABLE `leaves` DISABLE KEYS */;
/*!40000 ALTER TABLE `leaves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adv_loan` double NOT NULL,
  `adv_loan_ded` double DEFAULT NULL,
  `arrears` double DEFAULT NULL,
  `basic` double NOT NULL,
  `bonus` double DEFAULT NULL,
  `child` double DEFAULT NULL,
  `conveyance` double NOT NULL,
  `edu_all` double DEFAULT NULL,
  `house` double NOT NULL,
  `incentive` double DEFAULT NULL,
  `interesr` double DEFAULT NULL,
  `interest_ded` double DEFAULT NULL,
  `leave_ded` double DEFAULT NULL,
  `leave_encash` double DEFAULT NULL,
  `medical` double NOT NULL,
  `other` double DEFAULT NULL,
  `other_ded` double DEFAULT NULL,
  `overtime` double DEFAULT NULL,
  `perquisite` double DEFAULT NULL,
  `pf` double DEFAULT NULL,
  `professional_tax_ded` double DEFAULT NULL,
  `reimbursement` double DEFAULT NULL,
  `security_deposite_ded` double DEFAULT NULL,
  `special` double DEFAULT NULL,
  `stipen` double DEFAULT NULL,
  `tds` double DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll_user`
--

DROP TABLE IF EXISTS `payroll_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroll_user` (
  `pay_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`pay_id`,`user_id`),
  KEY `FKkn4v76b5du1rqdfr3xwtcfq1j` (`user_id`),
  CONSTRAINT `FKkde627ln0529n4aa868lcikvr` FOREIGN KEY (`pay_id`) REFERENCES `payroll` (`id`),
  CONSTRAINT `FKkn4v76b5du1rqdfr3xwtcfq1j` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll_user`
--

LOCK TABLES `payroll_user` WRITE;
/*!40000 ALTER TABLE `payroll_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `role_chain` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','ADMIN'),(2,'VC','VC'),(3,'PRO-VC','VC'),(4,'DIN','PRO-VC VC'),(5,'CHAIRMAN','DIN PRO-VC VC'),(6,'CO-ORDINATOR','CHAIRMAN DIN PRO-VC VC'),(7,'FACULTY','CO-ORDINATOR CHAIRMAN DIN PRO-VC VC'),(8,'USER','FACULTY CO-ORDINATOR CHAIRMAN DIN PRO-VC VC');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `f_name` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `income_tex_no` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) NOT NULL,
  `m_name` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `my_role` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `nid` varchar(255) DEFAULT NULL,
  `passport_no` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `permanent_city` varchar(255) DEFAULT NULL,
  `permanent_country` varchar(255) DEFAULT NULL,
  `permanent_districe` varchar(255) DEFAULT NULL,
  `permanent_house` varchar(255) DEFAULT NULL,
  `permanent_post_office` varchar(255) DEFAULT NULL,
  `permanent_street` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `present_city` varchar(255) DEFAULT NULL,
  `present_country` varchar(255) DEFAULT NULL,
  `present_districe` varchar(255) DEFAULT NULL,
  `present_house` varchar(255) DEFAULT NULL,
  `present_post_office` varchar(255) DEFAULT NULL,
  `present_street` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('121',1,NULL,'CSE','Student',NULL,'f@r',NULL,'F',NULL,NULL,'R',NULL,NULL,'ADMIN',NULL,NULL,NULL,'$2a$10$t1SJW73l6WKtiSnUr8mryOJmnp8mAWVsRP0uVb1kNKSjG7YWM1Hpe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1231',1,NULL,'CSE','Student',NULL,'f@l',NULL,'F',NULL,NULL,'L','M',NULL,'USER',NULL,NULL,NULL,'$2a$10$tqzm72xKM6H8EVvPnLc0zuVX8VI8.6zZEh3VJmAqzGy6NKOW/Kc7G',NULL,NULL,NULL,NULL,NULL,NULL,'0123547869',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1598753',1,NULL,'CSE','Student',NULL,'r@t',NULL,'R',NULL,NULL,'Y','T',NULL,'USER',NULL,NULL,NULL,'$2a$10$hTleMOv7EZbvSUk2XnreR.KY/fRE1pEHm7u4Ecyne1jkWy0jTFRE6',NULL,NULL,NULL,NULL,NULL,NULL,'01254789634',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2014200000071',1,NULL,'CSE','Student',NULL,'frsummit@gmail.com',NULL,'Fayazur',NULL,NULL,'Summit','Rahman',NULL,'ADMIN',NULL,NULL,NULL,'$2a$10$h.su08OimEKJFskyDIE8QuPk3R9Znfl3s2xMhcropM9oJvaTwq9FO',NULL,NULL,NULL,NULL,NULL,NULL,'01687858300',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('333',1,'A+','CSE','','','e@r','','w',NULL,NULL,'e','','','DIN',NULL,'',NULL,'$2a$10$FA2lUI8CpyX6yaqtteyxyeQWV8mHUevUsMY0TGKityJfPLOSyBdjS',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,'Male');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('121',1),('2014200000071',1),('333',4),('1231',8),('1598753',8);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-19 14:19:41
