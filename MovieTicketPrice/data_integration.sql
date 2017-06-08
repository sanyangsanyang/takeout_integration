/*
Navicat MySQL Data Transfer

Source Server         : danian1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : data_integration

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-08 15:00:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cinema_info
-- ----------------------------
DROP TABLE IF EXISTS `cinema_info`;
CREATE TABLE `cinema_info` (
  `cinema_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `cinema_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cinema_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cinema_info
-- ----------------------------
INSERT INTO `cinema_info` VALUES ('1', '11', 'danian');

-- ----------------------------
-- Table structure for movie_info
-- ----------------------------
DROP TABLE IF EXISTS `movie_info`;
CREATE TABLE `movie_info` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_info
-- ----------------------------

-- ----------------------------
-- Table structure for platform_info
-- ----------------------------
DROP TABLE IF EXISTS `platform_info`;
CREATE TABLE `platform_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfx3ha9rlwwqj964udtpf7kj7o` (`movie_id`),
  KEY `FKq5s9gxpp4eddld7bpym84lifn` (`cinema_id`),
  CONSTRAINT `FKfx3ha9rlwwqj964udtpf7kj7o` FOREIGN KEY (`movie_id`) REFERENCES `movie_info` (`movie_id`),
  CONSTRAINT `FKq5s9gxpp4eddld7bpym84lifn` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_info` (`cinema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_info
-- ----------------------------
