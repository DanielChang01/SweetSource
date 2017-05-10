/*
 Navicat MySQL Data Transfer

 Source Server         : TestLocalConn
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : test_for_sweet

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 05/10/2017 10:00:06 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `device_info`
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `user_mac_addr` varchar(150) NOT NULL,
  `user_ip` varchar(50) NOT NULL,
  `user_mobile` varchar(20) NOT NULL,
  PRIMARY KEY (`user_mac_addr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户设备信息表，用于定位用户IP，发送信息';

-- ----------------------------
--  Table structure for `discount`
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `user_mobile` varchar(20) NOT NULL,
  `shop_id` varchar(30) NOT NULL,
  `consume_times` int(11) NOT NULL COMMENT '消费次数',
  `consume_sum` decimal(10,0) NOT NULL COMMENT '消费总金额',
  PRIMARY KEY (`user_mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺消费信息表';

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='菜品表';

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` varchar(30) NOT NULL COMMENT '订单编号，格式：order+日期',
  `order_from_mobile` varchar(20) NOT NULL COMMENT '用户',
  `order_to_mobile` varchar(20) NOT NULL COMMENT '订单去向',
  `order_money` decimal(10,0) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表，用于记录订单双方信息';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `user_pass` varchar(20) NOT NULL DEFAULT '''''' COMMENT '密码',
  `user_mobile` varchar(20) NOT NULL DEFAULT '''''',
  `user_shop_id` varchar(30) DEFAULT NULL COMMENT '店铺编号，格式：shop+日期',
  PRIMARY KEY (`user_mobile`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
