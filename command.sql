-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
-- ------------------------------------------------------
-- Server version	8.0.32

--
-- Table structure for table `users`
--
CREATE Database edusphere;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `roll` varchar(45) DEFAULT NULL,
  `registration` varchar(45) DEFAULT NULL,
  `about` longtext,
  `note` longtext,
  `time` varchar(45) DEFAULT NULL,
  `time2` varchar(45) DEFAULT NULL,
  `Sub1` varchar(45) DEFAULT NULL,
  `Sub2` varchar(45) DEFAULT NULL,
  `Sub3` varchar(45) DEFAULT NULL,
  `Sub4` varchar(45) DEFAULT NULL,
  `Sub5` varchar(45) DEFAULT NULL,
  `sub1Class` varchar(45) DEFAULT NULL,
  `sub2Class` varchar(45) DEFAULT NULL,
  `sub3Class` varchar(45) DEFAULT NULL,
  `sub4Class` varchar(45) DEFAULT NULL,
  `sub5Class` varchar(45) DEFAULT NULL,
  `sub1At` varchar(45) DEFAULT NULL,
  `sub2At` varchar(45) DEFAULT NULL,
  `sub3At` varchar(45) DEFAULT NULL,
  `sub4At` varchar(45) DEFAULT NULL,
  `sub5At` varchar(45) DEFAULT NULL,
  `pdf1` longtext,
  `pdf2` longtext,
  `pdf3` longtext,
  `pdf4` longtext,
  `pdf5` longtext,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
