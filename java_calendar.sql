# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.14)
# Database: javaCalendar
# Generation Time: 2017-01-26 19:18:28 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table appointment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;

INSERT INTO `appointment` (`id`, `title`, `description`, `location`, `date`, `startTime`, `endTime`)
VALUES
	(79,'Event 1',NULL,NULL,'2016-12-21','09:00:00','10:00:00'),
	(80,'Event 2',NULL,NULL,'2016-12-21','10:00:00','11:00:00'),
	(81,'Event 3',NULL,NULL,'2016-12-21','12:00:00','13:00:00'),
	(82,'Event 4',NULL,NULL,'2016-12-21','13:00:00','14:00:00'),
	(83,'Event 5',NULL,NULL,'2016-12-21','15:00:00','16:00:00'),
	(84,'Event 6',NULL,NULL,'2016-12-21','16:00:00','17:00:00'),
	(85,'Event 7',NULL,NULL,'2016-12-21','17:00:00','18:00:00'),
	(86,'Event 8',NULL,NULL,'2016-12-21','18:00:00','19:00:00'),
	(87,'Event 9',NULL,NULL,'2016-12-21','19:00:00','20:00:00'),
	(88,'Event 10',NULL,NULL,'2016-12-21','20:00:00','21:00:00'),
	(89,'Event 11',NULL,NULL,'2016-12-21','21:00:00','22:00:00'),
	(90,'Event 1',NULL,NULL,'2016-12-09','09:00:00','10:00:00'),
	(91,'Event 2',NULL,NULL,'2016-12-09','10:00:00','11:00:00'),
	(92,'Event 3',NULL,NULL,'2016-12-09','12:00:00','13:00:00'),
	(93,'Event 4',NULL,NULL,'2016-12-09','13:00:00','14:00:00'),
	(94,'Event 5',NULL,NULL,'2016-12-09','15:00:00','16:00:00'),
	(95,'Event 6',NULL,NULL,'2016-12-09','16:00:00','17:00:00'),
	(96,'Event 7',NULL,NULL,'2016-12-09','17:00:00','18:00:00'),
	(97,'Event 1',NULL,NULL,'2016-12-26','09:00:00','10:00:00'),
	(98,'Event 2',NULL,NULL,'2016-12-26','10:00:00','11:00:00'),
	(99,'Event 3',NULL,NULL,'2016-12-26','12:00:00','13:00:00'),
	(100,'Event 4',NULL,NULL,'2016-12-26','13:00:00','14:00:00'),
	(101,'Event 1',NULL,NULL,'2016-11-30','09:00:00','10:00:00'),
	(103,'Event 1',NULL,NULL,'2016-11-28','09:00:00','10:00:00'),
	(104,'Event 2',NULL,NULL,'2016-11-28','10:00:00','11:00:00'),
	(105,'Event 1',NULL,NULL,'2017-01-04','09:00:00','10:00:00'),
	(107,'Event 1',NULL,NULL,'2017-01-05','09:00:00','10:00:00'),
	(108,'Event 2',NULL,NULL,'2017-01-05','10:00:00','11:00:00'),
	(109,'Event 3',NULL,NULL,'2017-01-05','11:00:00','12:00:00'),
	(110,'Event 4',NULL,NULL,'2017-01-05','12:00:00','13:00:00'),
	(111,'Event 5',NULL,NULL,'2017-01-05','13:00:00','14:00:00'),
	(112,'Event 6',NULL,NULL,'2017-01-05','14:00:00','15:00:00'),
	(113,'Event 7',NULL,NULL,'2017-01-05','15:00:00','16:00:00'),
	(114,'Event 8',NULL,NULL,'2017-01-05','16:00:00','17:00:00'),
	(115,'New event',NULL,NULL,'2016-11-17','09:00:00','10:00:00'),
	(118,'jty','rty','ghj','2017-01-02','10:00:00','11:00:00');

/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `color` varchar(6) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
