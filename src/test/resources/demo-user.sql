/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-06-23 14:25:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tbl_username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', '1@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('2', '2@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-2', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('3', '3@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-3', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('4', '4@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-4', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('5', '5@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-5', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('6', '6@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-6', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('7', '7@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-7', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('8', '8@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-8', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('9', '9@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-9', '1', '2017-06-23 14:24:23');
INSERT INTO `tbl_user` VALUES ('10', '10@qq.com', '1ee04e0b1cb5af7367c80c22e42efd8b', '会跳舞的机器人-10', '1', '2017-06-23 14:24:23');
SET FOREIGN_KEY_CHECKS=1;
