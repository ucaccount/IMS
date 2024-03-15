-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	8.0.21


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema inventory
--

CREATE DATABASE IF NOT EXISTS inventory;
USE inventory;

--
-- Definition of table `tblcustomer`
--

DROP TABLE IF EXISTS `tblcustomer`;
CREATE TABLE `tblcustomer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(35) DEFAULT NULL,
  `mobile` varchar(14) DEFAULT NULL,
  `mailid` varchar(40) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `addr` varchar(150) DEFAULT NULL,
  `dor` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblcustomer`
--

/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` (`id`,`name`,`mobile`,`mailid`,`dob`,`addr`,`dor`) VALUES 
 (1,'Arif Chaudhary','9211223344','arif123@gmail.com','1976-10-20','Hno - 23, Sector - 9,\nVijay Nagar Ghaziabad - 201001,\n Near Water Tank','2020-08-20 08:20:58'),
 (2,'Amit Kr Gupta S/o Mr. Ram Kr Gupta','9211999999','amitgupta23@gmail.com','1987-10-20','R2/33 Raj Nagar Ghaziabad, \nUttar Pradesh - 201001\nNear Sec 2 Mandir ','2020-08-20 08:50:29'),
 (3,'Ram Singh','9292929292','ramu123@gmail.com','2000-10-20','11/3445 Mohan Nagar\nGhaziabad -\nUttar Pradesh - 201001','2020-08-20 09:07:43'),
 (5,'Ms Sapna','+91-8292929292','sapna@gmail.com','1987-10-20','R-2/34 Raj Nagar\nGhaziabad - 201001','2020-08-22 18:27:59'),
 (6,'Rihit Veram','+91-8282828212','rihitverma@gmail.com','1995-10-23','N-23, Sector ( \nVijay Nagar \nGhaziabad - 2010001','2020-09-22 11:58:31'),
 (7,'mayank Tyagi','+91-9292929292','mayanktyagi@gmail.com','1998-12-24','R2/23, Raj nagar\nGhaziabad\nUP - 201001','2020-09-29 17:40:51'),
 (8,'Ram Kumar Sharma','+91-9211223344','ram@gmail.com','1999-10-23','R-2/439 Raj Nagar Ghaziabad\nUttar pradesh - 201001 ','2020-10-12 16:43:48'),
 (9,'Ayush tyagi','+91-9211887722','tyagiayush@gmail.com','1994-07-01','Vill: Kapsua, Post:Kapsua, \nDistt: Amroha, Uttar Pradesh - 302918','2020-10-28 21:16:51'),
 (10,'Farhan Saifi','9211223221','farhan12345@gmail.com','1995-05-10','C-12, Sector-23 \nNew Arya Nagar Dadri. \nGreator Noida - 203001','2020-11-26 08:43:19');
/*!40000 ALTER TABLE `tblcustomer` ENABLE KEYS */;


--
-- Definition of table `tbllogin`
--

DROP TABLE IF EXISTS `tbllogin`;
CREATE TABLE `tbllogin` (
  `userid` varchar(20) NOT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `wmode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tbllogin`
--

/*!40000 ALTER TABLE `tbllogin` DISABLE KEYS */;
INSERT INTO `tbllogin` (`userid`,`pass`,`wmode`) VALUES 
 ('manoj','gzb@123','Emp'),
 ('manojkr','chaudhary@#$','Admin'),
 ('yash','goel@#$','Emp');
/*!40000 ALTER TABLE `tbllogin` ENABLE KEYS */;


--
-- Definition of table `tblmaster`
--

DROP TABLE IF EXISTS `tblmaster`;
CREATE TABLE `tblmaster` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `Descp` varchar(50) DEFAULT NULL,
  `Qty` int DEFAULT NULL,
  `mrp` decimal(7,2) DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `mfgdate` date DEFAULT NULL,
  `expdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblmaster`
--

/*!40000 ALTER TABLE `tblmaster` DISABLE KEYS */;
INSERT INTO `tblmaster` (`id`,`Name`,`Descp`,`Qty`,`mrp`,`price`,`mfgdate`,`expdate`) VALUES 
 (5,'Surf','5 KG',0,'500.00','200.00','2020-05-10','2021-05-10'),
 (6,'Run 250gm','Run 250 gm Bar	',5,'25.00','20.00','2020-10-19','2022-10-19'),
 (7,'Lux 450 GM','Lux 3 * 150 GM = 450 GM ',6,'110.00','75.00','2020-01-07','2022-01-07'),
 (8,'Hair & Care 300GM','Hair & Care 300GM Botle 	',20,'150.00','110.00','2020-05-10','2022-10-20'),
 (9,'Colgate 150 GM','Colgate 150 GM Fresh Gel	',50,'89.00','67.00','2020-07-10','2022-10-10'),
 (10,'Pears 350GM','Pears 350 GM ( Buy 3 + Get 1 Free)',70,'170.00','140.00','2020-06-20','2020-10-23'),
 (11,'Nirma','Nirma 1 KG		',100,'100.00','60.00','2019-10-20','2020-10-20'),
 (12,'Harpik 750GM','Harpik 750GM',50,'90.00','50.00','2019-10-23','2021-10-23'),
 (13,'Surf Excel 5KG','Surf Excel 5KG	',100,'609.00','489.00','2020-05-20','2022-10-20'),
 (14,'Vim Bar 500GM','VIM Bar 500 GM 	',20,'60.00','40.00','2020-07-01','2022-07-31'),
 (15,'Jogan Bidi','Bidi ka bandal',80,'15.00','10.00','2020-10-01','2021-10-28');
/*!40000 ALTER TABLE `tblmaster` ENABLE KEYS */;


--
-- Definition of table `tblpurchase`
--

DROP TABLE IF EXISTS `tblpurchase`;
CREATE TABLE `tblpurchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `Descp` varchar(50) DEFAULT NULL,
  `salesman` int DEFAULT NULL,
  `Qty` int DEFAULT NULL,
  `mrp` decimal(7,2) DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `mfgdate` date DEFAULT NULL,
  `expdate` date DEFAULT NULL,
  `purdate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblpurchase`
--

/*!40000 ALTER TABLE `tblpurchase` DISABLE KEYS */;
INSERT INTO `tblpurchase` (`id`,`Name`,`Descp`,`salesman`,`Qty`,`mrp`,`price`,`mfgdate`,`expdate`,`purdate`) VALUES 
 (1,'CloseUp','CloseUp 300 GM - 150 + 150',4,100,'150.00','90.00','2020-06-20','2023-06-20','2019-08-20 00:00:00'),
 (2,'Surf Excel','Surf Excel 5KG Bag + 2 KG Free',2,50,'750.00','450.00','2020-04-10','2022-04-10','2019-10-20 00:00:00'),
 (3,'Surf','5 KG',2,60,'500.00','200.00','2020-05-10','2021-05-10','2019-10-20 00:00:00'),
 (4,'Surf','5 KG',2,100,'500.00','200.00','2020-05-10','2021-05-10','2020-08-19 09:31:49'),
 (5,'Surf','5 KG',2,100,'500.00','200.00','2020-05-10','2021-05-10','2020-08-19 09:31:53'),
 (6,'Run 250gm','Run 250 gm Bar	',1,100,'25.00','20.00','2020-10-19','2022-10-19','2020-08-19 14:35:16'),
 (7,'Run 250gm','Run 250 gm Bar	',1,100,'25.00','20.00','2020-10-19','2022-10-19','2020-08-19 14:35:23'),
 (8,'Lux 450 GM','Lux 3 * 150 GM = 450 GM ',2,100,'110.00','75.00','2020-01-07','2022-01-07','2020-08-20 07:59:08'),
 (9,'Lux 450 GM','Lux 3 * 150 GM = 450 GM ',2,50,'110.00','75.00','2020-01-07','2022-01-07','2020-08-20 08:00:20'),
 (10,'Lux 450 GM','Lux 3 * 150 GM = 450 GM ',2,150,'110.00','75.00','2020-01-07','2022-01-07','2020-08-20 08:01:38'),
 (11,'Hair & Care 300GM','Hair & Care 300GM Botle 	',2,100,'150.00','110.00','2020-05-10','2022-10-20','2020-08-20 08:10:33'),
 (12,'Hair & Care 300GM','Hair & Care 300GM Botle 	',2,100,'150.00','110.00','2020-05-10','2022-10-20','2020-08-20 08:10:41'),
 (13,'Colgate 150 GM','Colgate 150 GM Fresh Gel	',4,100,'89.00','67.00','2020-07-10','2022-10-10','2020-08-29 09:05:48'),
 (14,'Pears 350GM','Pears 350 GM ( Buy 3 + Get 1 Free)',2,100,'170.00','140.00','2020-06-20','2020-10-23','2020-08-29 09:13:55'),
 (15,'Nirma','Nirma 1 KG		',2,100,'100.00','60.00','2019-10-20','2020-10-20','2020-08-29 09:28:20'),
 (16,'Harpik 750GM','Harpik 750GM',2,50,'90.00','50.00','2019-10-23','2021-10-23','2020-08-29 09:35:11'),
 (17,'Surf Excel 5KG','Surf Excel 5KG	',2,100,'609.00','489.00','2020-05-20','2022-10-20','2020-08-31 18:11:20'),
 (18,'Vim Bar 500GM','VIM Bar 500 GM 	',2,100,'60.00','40.00','2020-07-01','2022-07-31','2020-09-02 09:33:51'),
 (19,'Jogan Bidi','Bidi ka bandal',5,100,'15.00','10.00','2020-10-01','2021-10-28','2020-10-28 21:26:03');
/*!40000 ALTER TABLE `tblpurchase` ENABLE KEYS */;


--
-- Definition of table `tblsales`
--

DROP TABLE IF EXISTS `tblsales`;
CREATE TABLE `tblsales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prid` int DEFAULT NULL,
  `prName` varchar(30) DEFAULT NULL,
  `prdesc` varchar(50) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `total` decimal(7,2) NOT NULL,
  `custDetails` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `saledate` datetime DEFAULT NULL,
  `paidamount` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblsales`
--

/*!40000 ALTER TABLE `tblsales` DISABLE KEYS */;
INSERT INTO `tblsales` (`id`,`prid`,`prName`,`prdesc`,`qty`,`price`,`total`,`custDetails`,`saledate`,`paidamount`) VALUES 
 (1,6,' Run 250gm','Run 250 gm Bar	',50,'23.00','1150.00',' Ram Singh\nContact No : +91-9292929292\n11/3445 Mohan Nagar\nGhaziabad -\nUttar Pradesh - 201001','2020-08-22 08:35:47',NULL),
 (2,6,' Run 250gm','Run 250 gm Bar	',100,'23.00','2300.00',' Arif Chaudhary\nContact No : +91-9211223344\nHno - 23, Sector - 9,\nVijay Nagar Ghaziabad - 201001,\n Near Water Tank','2020-08-22 08:36:40',NULL),
 (3,7,' Lux 450 GM','Lux 3 * 150 GM = 450 GM ',5,'86.25','431.25','3 - Ram Singh\nContact No : +91-9292929292\n11/3445 Mohan Nagar\nGhaziabad -\nUttar Pradesh - 201001','2020-08-23 09:35:46','0.00'),
 (4,8,' Hair & Care 300GM','Hair & Care 300GM Botle 	',20,'126.50','2530.00','1 - Arif Chaudhary\nContact No : +91-9211223344\nHno - 23, Sector - 9,\nVijay Nagar Ghaziabad - 201001,\n Near Water Tank','2020-08-22 09:44:21',NULL),
 (5,7,' Lux 450 GM','Lux 3 * 150 GM = 450 GM ',5,'86.25','431.25','3 - Ram Singh\nContact No : +91-9292929292\n11/3445 Mohan Nagar\nGhaziabad -\nUttar Pradesh - 201001','2020-08-23 09:02:50',NULL),
 (7,7,' Lux 450 GM','Lux 3 * 150 GM = 450 GM ',50,'86.25','4312.50','5 - Ms Sapna\nContact No : +91-8292929292\nR-2/34 Raj Nagar\nGhaziabad - 201001','2020-08-23 09:37:53','1293.75'),
 (12,7,' Lux 450 GM','Lux 3 * 150 GM = 450 GM ',30,'86.25','2587.50','5 - Ms Sapna\nContact No : +91-8292929292\nR-2/34 Raj Nagar\nGhaziabad - 201001','2020-08-25 09:07:47','2587.50'),
 (13,6,' Run 250gm','Run 250 gm Bar	',25,'23.00','575.00','5 - Ms Sapna\nContact No : +91-8292929292\nR-2/34 Raj Nagar\nGhaziabad - 201001','2020-08-25 09:34:59','575.00'),
 (14,7,' Lux 450 GM','Lux 3 * 150 GM = 450 GM ',29,'86.25','2501.25','3 - Ram Singh\nContact No : +91-9292929292\n11/3445 Mohan Nagar\nGhaziabad -\nUttar Pradesh - 201001','2020-08-25 10:54:38','0.00'),
 (15,5,' Surf','5 KG',10,'230.00','2300.00','1 - Arif Chaudhary\nContact No : +91-9211223344\nHno - 23, Sector - 9,\nVijay Nagar Ghaziabad - 201001,\n Near Water Tank','2020-08-27 09:20:53','-2300.00'),
 (16,14,' Vim Bar 500GM','VIM Bar 500 GM 	',80,'46.00','3680.00','1 - Arif Chaudhary\nContact No : +91-9211223344\nHno - 23, Sector - 9,\nVijay Nagar Ghaziabad - 201001,\n Near Water Tank','2020-09-02 09:35:07','3680.00'),
 (17,6,' Run 250gm','Run 250 gm Bar	',10,'23.00','230.00','6 - Rihit Veram\nContact No : +91-8282828212\nN-23, Sector ( \nVijay Nagar \nGhaziabad - 2010001','2020-09-22 11:59:46','-230.00'),
 (18,6,' Run 250gm','Run 250 gm Bar	',10,'23.00','230.00','7 - mayank Tyagi\nContact No : +91-9292929292\nR2/23, Raj nagar\nGhaziabad\nUP - 201001','2020-09-29 17:41:39','230.00'),
 (19,9,' Colgate 150 GM','Colgate 150 GM Fresh Gel	',50,'77.05','3852.50','8 - Ram Kumar Sharma\nContact No : +91-9211223344\nR-2/439 Raj Nagar Ghaziabad\nUttar pradesh - 201001 ','2020-10-12 16:46:11','1926.25'),
 (20,10,' Pears 350GM','Pears 350 GM ( Buy 3 + Get 1 Free)',30,'161.00','4830.00','9 - Ayush tyagi\nContact No : +91-9211887722\nVill: Kapsua, Post:Kapsua, \nDistt: Amroha, Uttar Pradesh - 302918','2020-10-28 21:19:27','-1610.00'),
 (21,15,' Jogan Bidi','Bidi ka bandal',20,'11.50','230.00','9 - Ayush tyagi\nContact No : +91-9211887722\nVill: Kapsua, Post:Kapsua, \nDistt: Amroha, Uttar Pradesh - 302918','2020-10-28 21:27:01','230.00');
/*!40000 ALTER TABLE `tblsales` ENABLE KEYS */;


--
-- Definition of table `tblsalesman`
--

DROP TABLE IF EXISTS `tblsalesman`;
CREATE TABLE `tblsalesman` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `mob` varchar(14) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mailid` varchar(40) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL,
  `Addr` varchar(100) DEFAULT NULL,
  `dor` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblsalesman`
--

/*!40000 ALTER TABLE `tblsalesman` DISABLE KEYS */;
INSERT INTO `tblsalesman` (`id`,`Name`,`mob`,`dob`,`mailid`,`company`,`Addr`,`dor`) VALUES 
 (1,'Yash Goel','+91-9292929292','1987-10-23','yashgoel@gmail.com','Hindustan Ind. Pvt Ltd.','G-125, Patel Nagar 3rd,\nGhaziabad , Uttar Pradesh\nPin - 201001','2020-08-18'),
 (2,'Manish Kr','+91-8282828282','1999-12-20','manish@gmail.com','ABC Pvt Ltd.','D-12, Lohia Nagar C Block,\nGhaziabad, UP, \n201001','2020-08-18'),
 (4,'Pankaj Singh','+91-8261616161','1999-10-23','pankajsingh@gmail.com','Health Care Pvt Ltd','C-34, C Block Lohia Nagar\nGhaziabad , 201001','2020-08-19');
/*!40000 ALTER TABLE `tblsalesman` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
