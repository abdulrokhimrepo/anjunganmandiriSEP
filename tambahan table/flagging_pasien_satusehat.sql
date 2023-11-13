/*
 Navicat Premium Data Transfer

 Source Server         : DB SERVER UBUNTU
 Source Server Type    : MariaDB
 Source Server Version : 100338
 Source Host           : 192.168.15.111:3306
 Source Schema         : SIMRS_INDRIATI

 Target Server Type    : MariaDB
 Target Server Version : 100338
 File Encoding         : 65001

 Date: 13/11/2023 10:27:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flagging_pasien_satusehat
-- ----------------------------
DROP TABLE IF EXISTS `flagging_pasien_satusehat`;
CREATE TABLE `flagging_pasien_satusehat` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `flagging` enum('yes','no') NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`no_rkm_medis`) USING BTREE,
  KEY `no_rkm_medis` (`no_rkm_medis`) USING BTREE,
  CONSTRAINT `fk_flagging` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
