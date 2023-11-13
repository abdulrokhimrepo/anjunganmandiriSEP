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

 Date: 09/11/2023 13:21:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for referensi_mobilejkn_bpjs_taskid_status200
-- ----------------------------
DROP TABLE IF EXISTS `referensi_mobilejkn_bpjs_taskid_status200`;
CREATE TABLE `referensi_mobilejkn_bpjs_taskid_status200` (
  `no_rawat` varchar(17) NOT NULL,
  `taskid1` datetime NOT NULL,
  `taskid2` datetime NOT NULL,
  `taskid3` datetime NOT NULL,
  `taskid4` datetime NOT NULL,
  `taskid5` datetime NOT NULL,
  `taskid6` datetime NOT NULL,
  `taskid7` datetime NOT NULL,
  PRIMARY KEY (`no_rawat`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
