/*
Navicat MySQL Data Transfer

Source Server         : inging44
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : jfinal_fish

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-01-12 18:15:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fish_mall_teambuy
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_teambuy`;
CREATE TABLE `fish_mall_teambuy` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '团购活动id',
  `shopId` mediumint(8) unsigned NOT NULL COMMENT '团购发起商家',
  `goodsId` mediumint(8) unsigned NOT NULL COMMENT '参与团购的商品id',
  `totalCount` int(10) unsigned NOT NULL COMMENT '团购参与商品总数',
  `remain` int(10) unsigned NOT NULL COMMENT '还剩余多少商品',
  `teambuyPrice` decimal(15,5) unsigned NOT NULL COMMENT '团购价格',
  `isTop` tinyint(1) NOT NULL COMMENT '是否置顶',
  `createTime` datetime NOT NULL,
  `endTime` datetime NOT NULL COMMENT '截止时间',
  `isClosed` tinyint(1) NOT NULL DEFAULT '0',
  `isSuccess` tinyint(1) NOT NULL DEFAULT '0',
  `percent` varchar(255) NOT NULL DEFAULT '0%' COMMENT '参团百分比',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fish_mall_teambuy_order
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_teambuy_order`;
CREATE TABLE `fish_mall_teambuy_order` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `tid` mediumint(8) unsigned NOT NULL COMMENT '商品id',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `status` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '待付款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fish_mall_teambuy_user
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_teambuy_user`;
CREATE TABLE `fish_mall_teambuy_user` (
  `tid` mediumint(8) unsigned NOT NULL COMMENT '团购活动id',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '参与用户id',
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '参与团购的用户名',
  `datetime` time NOT NULL COMMENT '参与时间',
  PRIMARY KEY (`tid`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
