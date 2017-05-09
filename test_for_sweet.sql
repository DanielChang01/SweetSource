/*
 Navicat MySQL Data Transfer

 Source Server         : TestLocalConn
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : test_for_sweet

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 05/09/2017 18:03:40 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `device_info`
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `user_mac_addr` varchar(255) NOT NULL,
  `user_ip` varchar(255) NOT NULL,
  `user_mobile` varchar(255) NOT NULL,
  PRIMARY KEY (`user_mac_addr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `dish`
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `dish_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(30) NOT NULL,
  `dish_name` varchar(40) DEFAULT NULL COMMENT '菜品名称',
  `dish_desc` varchar(100) DEFAULT NULL COMMENT '菜品描述',
  `dish_category` varchar(20) DEFAULT NULL COMMENT '菜品分类',
  `dish_pic` varchar(50) DEFAULT NULL COMMENT '菜品照片',
  `dish_pre_price` decimal(10,0) unsigned DEFAULT NULL COMMENT '现价',
  `dish_cut_price` decimal(10,0) DEFAULT NULL COMMENT '折后价',
  PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `dish`
-- ----------------------------
BEGIN;
INSERT INTO `dish` VALUES ('1', '2017/5/5', '西红柿超鸡蛋', 'i like it.', '甜食', null, '10', null);
COMMIT;

-- ----------------------------
--  Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shop_id` varchar(30) NOT NULL,
  `shop_name` varchar(50) NOT NULL DEFAULT '''''',
  `shop_addr` varchar(50) NOT NULL DEFAULT '''''',
  `shop_pic` varchar(100) NOT NULL DEFAULT '''''',
  `shop_point` text COMMENT '定位信息，格式：经度，纬度',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `shop`
-- ----------------------------
BEGIN;
INSERT INTO `shop` VALUES ('2107/5/5', '饭小二', '大宝路SOD号', '', null);
COMMIT;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(25) NOT NULL COMMENT '用户编号',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `user_pass` varchar(20) NOT NULL DEFAULT '''''' COMMENT '密码',
  `user_mobile` varchar(20) NOT NULL DEFAULT '''''',
  `user_shop_id` varchar(30) DEFAULT NULL COMMENT '店铺编号',
  PRIMARY KEY (`user_mobile`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('2', 'sweetshop', '123', '\'\'', '2017/5/5'), ('1', 'sweet', '123', '136', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
