/*
Navicat MySQL Data Transfer

Source Server         : hmily
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : jfianlfish

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-01-26 10:21:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `fish_message`
-- ----------------------------
DROP TABLE IF EXISTS `fish_message`;
CREATE TABLE `fish_message` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `uid` mediumint(9) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `touid` mediumint(8) unsigned NOT NULL,
  `message` varchar(255) NOT NULL COMMENT '发送的消息',
  `pic` varchar(255) DEFAULT NULL COMMENT '发送的图片',
  `dateline` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_message
-- ----------------------------
INSERT INTO `fish_message` VALUES ('2', '1', 'dff', '1', 'ddd', null, '2016-01-25 18:49:21');
INSERT INTO `fish_message` VALUES ('3', '1', '生生世世', '1', '的订单', '', '2016-01-25 19:00:36');
INSERT INTO `fish_message` VALUES ('4', '1', 'ss', '1', 'ss', '', '2016-01-26 09:54:40');
INSERT INTO `fish_message` VALUES ('5', '1', 'sss', '1', 'ssss', 'upload/news/1453773402290.jpg', '2016-01-26 09:56:44');
