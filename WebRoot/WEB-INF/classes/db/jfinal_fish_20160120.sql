/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : jfinal_fish

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2016-01-20 17:46:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fish_fubi_detail
-- ----------------------------
DROP TABLE IF EXISTS `fish_fubi_detail`;
CREATE TABLE `fish_fubi_detail` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '富币详情记录自增长id',
  `uid` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `type` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '富币收入类型',
  `fubi` mediumint(8) NOT NULL DEFAULT '0' COMMENT '赚取或消费的富币数量',
  `dateline` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_fubi_detail
-- ----------------------------
INSERT INTO `fish_fubi_detail` VALUES ('1', '3', '4', '5', '2016-01-11 15:26:16');
INSERT INTO `fish_fubi_detail` VALUES ('2', '3', '4', '5', '2016-01-11 16:14:11');
INSERT INTO `fish_fubi_detail` VALUES ('5', '3', '3', '50', '2016-01-11 16:47:36');
INSERT INTO `fish_fubi_detail` VALUES ('6', '3', '3', '50', '2016-01-11 16:55:18');
INSERT INTO `fish_fubi_detail` VALUES ('7', '3', '1', '10', '2016-01-12 09:22:47');
INSERT INTO `fish_fubi_detail` VALUES ('8', '21', '1', '10', '2016-01-13 14:43:11');
INSERT INTO `fish_fubi_detail` VALUES ('9', '21', '3', '50', '2016-01-20 13:03:15');
INSERT INTO `fish_fubi_detail` VALUES ('10', '21', '4', '5', '2016-01-20 13:19:11');

-- ----------------------------
-- Table structure for fish_fubi_mall_banner
-- ----------------------------
DROP TABLE IF EXISTS `fish_fubi_mall_banner`;
CREATE TABLE `fish_fubi_mall_banner` (
  `id` mediumint(8) NOT NULL,
  `pic` varchar(1024) NOT NULL DEFAULT '' COMMENT '图片地址',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '提交的商家id',
  `gid` mediumint(8) unsigned NOT NULL COMMENT '链接的商品id',
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1：开启使用、0：禁止',
  `createTime` datetime DEFAULT NULL COMMENT '提交时间',
  `passTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='富币商城顶部banner图片表';

-- ----------------------------
-- Records of fish_fubi_mall_banner
-- ----------------------------
INSERT INTO `fish_fubi_mall_banner` VALUES ('0', 'qeqrqwe', '15', '1', '1', '2016-01-12 15:31:42', '2016-01-28 15:31:52');
INSERT INTO `fish_fubi_mall_banner` VALUES ('2', 'twerwetw', '15', '2', '1', '2016-01-12 15:33:36', '2016-01-28 15:33:40');
INSERT INTO `fish_fubi_mall_banner` VALUES ('3', 'wdfwdg', '15', '6', '1', '2016-01-11 15:34:07', '2016-01-27 15:34:10');

-- ----------------------------
-- Table structure for fish_fubi_type
-- ----------------------------
DROP TABLE IF EXISTS `fish_fubi_type`;
CREATE TABLE `fish_fubi_type` (
  `id` mediumint(8) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '名称',
  `points` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '增加的富币数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fish_fubi_type
-- ----------------------------
INSERT INTO `fish_fubi_type` VALUES ('1', '签到', '10');
INSERT INTO `fish_fubi_type` VALUES ('2', '商品购买', '0');
INSERT INTO `fish_fubi_type` VALUES ('3', '发表资讯文章', '50');
INSERT INTO `fish_fubi_type` VALUES ('4', '咨询文章评论', '5');
INSERT INTO `fish_fubi_type` VALUES ('5', '资讯文章点赞', '1');

-- ----------------------------
-- Table structure for fish_goods
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods`;
CREATE TABLE `fish_goods` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `shopId` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '商品发布企业id',
  `priceRange` decimal(15,2) NOT NULL COMMENT '商品价格区间',
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '展示的title',
  `enable` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0待审核，1审核通过，2审核未通过',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '商品状态：0（正常）、1（缺货）、2（下架）',
  `selled` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '销量，用于排序',
  `comment` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
  `favorite` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `cover` varchar(256) NOT NULL DEFAULT '' COMMENT '商品封面',
  `dateline` datetime NOT NULL COMMENT '上架时间',
  `catIdParent` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '商品所属的一级类目',
  `catId` mediumint(8) NOT NULL COMMENT '商品类目',
  `qualityassurance` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否品质保证',
  `give` int(10) unsigned NOT NULL COMMENT '赠送富币',
  `replacement` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '七天包换不',
  `expressfee` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '快递费用',
  `displayorder` int(11) NOT NULL DEFAULT '0' COMMENT '综合排序，用于筛选栏',
  `remain` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '库存剩余量',
  `isClosed` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `fk_trade` (`shopId`),
  CONSTRAINT `fish_goods_ibfk_1` FOREIGN KEY (`shopId`) REFERENCES `sec_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of fish_goods
-- ----------------------------
INSERT INTO `fish_goods` VALUES ('1', '1', '30.00', '科迈雷三色多款三竿稍枪柄路亚竿套装水滴轮钓鱼竿', '1', '0', '200', '0', '0', 'upload/news/1451365713693.JPG', '2015-12-11 10:35:11', '1', '5', '1', '10', '0', '10', '1', '50', '0');
INSERT INTO `fish_goods` VALUES ('2', '1', '1.00', '科迈雷四色多款两竿稍枪柄路亚竿套装水滴轮钓鱼竿', '1', '0', '10', '1', '2', 'upload/news/1451365713693.JPG', '2015-12-27 18:17:07', '1', '2', '1', '12', '1', '12', '0', '100', '0');
INSERT INTO `fish_goods` VALUES ('6', '1', '123.00', '鱼线', '1', '0', '73', '0', '0', 'upload/news/1451359181209.jpg', '2016-01-10 14:19:04', '1', '5', '1', '12', '1', '10', '0', '3333', '0');
INSERT INTO `fish_goods` VALUES ('7', '1', '22.00', '东莞打工', '1', '1', '30', '0', '0', 'upload/news/1451365536963.jpg', '2015-12-29 13:05:40', '1', '2', '0', '21', '0', '12', '0', '12', '0');
INSERT INTO `fish_goods` VALUES ('8', '1', '12.00', '粉丝房顶上', '1', '1', '67', '0', '0', 'upload/news/1451365713693.JPG', '2015-12-29 13:09:28', '6', '7', '0', '111', '0', '5', '0', '333', '0');
INSERT INTO `fish_goods` VALUES ('9', '1', '12.00', '科迈雷五色多款两竿稍枪柄路亚竿套装水滴轮钓鱼竿', '1', '0', '20', '0', '0', 'upload/news/1451457298443.jpg', '2015-12-30 14:35:42', '1', '5', '0', '21', '0', '11', '0', '33', '0');
INSERT INTO `fish_goods` VALUES ('10', '1', '111.00', '21313131', '1', '0', '26', '0', '0', 'upload/news/1452163358378.jpg', '2016-01-07 18:42:54', '6', '8', '1', '12', '1', '21', '0', '23', '0');
INSERT INTO `fish_goods` VALUES ('13', '1', '1111.00', 'fdxsbvgfbccfv', '1', '0', '26', '0', '0', 'upload/news/1452163705160.JPG', '2016-01-07 18:52:11', '6', '8', '1', '12', '1', '31', '0', '444', '0');
INSERT INTO `fish_goods` VALUES ('14', '1', '1212.00', '2312342ewdsfdsfd', '1', '0', '104', '0', '0', 'upload/news/1452234594510.jpg', '2016-01-10 14:13:16', '6', '9', '1', '12', '1', '222', '0', '3131', '1');
INSERT INTO `fish_goods` VALUES ('15', '1', '12.00', 'yugan', '1', '0', '100', '0', '0', 'upload/news/1452669524816.png', '2016-01-13 15:19:31', '1', '2', '1', '12', '1', '11', '0', '490', '0');
INSERT INTO `fish_goods` VALUES ('16', '22', '1231.00', '新款鱼饵s', '1', '0', '0', '0', '0', 'upload/news/1452822758911.jpg', '2016-01-20 14:33:56', '6', '7', '1', '40', '0', '5', '0', '100', '0');
INSERT INTO `fish_goods` VALUES ('17', '1', '1000.00', '科迈雷三色多款两竿稍枪柄台钓竿套装水滴轮钓鱼竿', '1', '0', '0', '0', '0', 'upload/news/1453281737543.png', '2016-01-20 17:23:17', '1', '2', '1', '40', '1', '10', '0', '6000', '0');

-- ----------------------------
-- Table structure for fish_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_category`;
CREATE TABLE `fish_goods_category` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品类目自增长id',
  `parentId` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '父类目id,如果没有父类目，则为0，没有父类目的为一级类目',
  `hasChild` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有子类目，没有：0，有1',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '类目名称',
  `level` smallint(6) unsigned NOT NULL DEFAULT '1' COMMENT '级',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_category
-- ----------------------------
INSERT INTO `fish_goods_category` VALUES ('1', '0', '1', '鱼竿', '1', 'upload/news/1453274560387.png');
INSERT INTO `fish_goods_category` VALUES ('2', '1', '0', '台钓竿', '2', 'upload/news/1453274616594.png');
INSERT INTO `fish_goods_category` VALUES ('5', '1', '1', '鲤竿', '2', '');
INSERT INTO `fish_goods_category` VALUES ('6', '0', '1', '鱼饵', '1', 'upload/news/1453274568592.png');
INSERT INTO `fish_goods_category` VALUES ('7', '6', '0', '草鱼饵', '2', '');
INSERT INTO `fish_goods_category` VALUES ('8', '6', '0', '鲤鱼饵', '2', '');

-- ----------------------------
-- Table structure for fish_goods_count_month
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_count_month`;
CREATE TABLE `fish_goods_count_month` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '月统计记录自增长id',
  `gid` int(10) unsigned NOT NULL COMMENT '商品id',
  `dateline` datetime NOT NULL COMMENT '统计时间',
  `selled` int(10) unsigned NOT NULL COMMENT '月销售量',
  `groupby` int(10) unsigned NOT NULL COMMENT '商品统计第几个月，按组分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_icelandic_ci;

-- ----------------------------
-- Records of fish_goods_count_month
-- ----------------------------
INSERT INTO `fish_goods_count_month` VALUES ('1', '6', '2015-11-01 10:16:42', '123', '1');
INSERT INTO `fish_goods_count_month` VALUES ('2', '6', '2015-12-01 10:17:01', '321', '2');

-- ----------------------------
-- Table structure for fish_goods_keyt_params
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_keyt_params`;
CREATE TABLE `fish_goods_keyt_params` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '特征量自增长id',
  `catId` mediumint(8) NOT NULL DEFAULT '0' COMMENT '类目id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '特征量',
  `displayorder` mediumint(8) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_keyt_params
-- ----------------------------
INSERT INTO `fish_goods_keyt_params` VALUES ('1', '2', '颜色', '1');
INSERT INTO `fish_goods_keyt_params` VALUES ('2', '2', '长度', '5');
INSERT INTO `fish_goods_keyt_params` VALUES ('3', '7', '重量', '3');
INSERT INTO `fish_goods_keyt_params` VALUES ('4', '7', '颜色', '4');
INSERT INTO `fish_goods_keyt_params` VALUES ('5', '5', '长度', '5');
INSERT INTO `fish_goods_keyt_params` VALUES ('6', '5', '数量', '6');
INSERT INTO `fish_goods_keyt_params` VALUES ('8', '8', '大小', '0');

-- ----------------------------
-- Table structure for fish_goods_keyt_params_value
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_keyt_params_value`;
CREATE TABLE `fish_goods_keyt_params_value` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `paramsId` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '特征量id',
  `value` varchar(64) NOT NULL DEFAULT '' COMMENT '特征值',
  `displayorder` mediumint(8) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_keyt_params_value
-- ----------------------------
INSERT INTO `fish_goods_keyt_params_value` VALUES ('1', '1', '红', '1');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('2', '1', '蓝', '2');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('4', '1', '白', '4');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('5', '2', '3.0m', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('6', '2', '4.5m', '2');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('7', '2', '6.5m', '3');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('8', '8', '大', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('14', '5', '6.5m', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('15', '5', '5m', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('16', '6', '1包', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('17', '6', '2包', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('18', '3', '0.2kg', '1');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('19', '3', '0.3kg', '5');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('21', '4', 'afasfasdfasd', '0');
INSERT INTO `fish_goods_keyt_params_value` VALUES ('22', '8', '小', '0');

-- ----------------------------
-- Table structure for fish_goods_keyt_params_value_goods
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_keyt_params_value_goods`;
CREATE TABLE `fish_goods_keyt_params_value_goods` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `goodsId` mediumint(8) NOT NULL COMMENT '商品id',
  `pvId_1` mediumint(8) NOT NULL COMMENT '特征值1id',
  `pvId_2` mediumint(8) NOT NULL COMMENT '特征值2id',
  `totalCount` mediumint(8) NOT NULL COMMENT '商品总量',
  `remain` mediumint(8) NOT NULL COMMENT '剩余商品数量',
  `price` decimal(15,2) NOT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_keyt_params_value_goods
-- ----------------------------
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('5', '7', '7', '1', '12', '12', '222.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('6', '8', '21', '18', '111', '111', '1.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('7', '8', '21', '19', '222', '222', '2.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('8', '9', '14', '16', '12', '12', '111.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('9', '9', '15', '16', '21', '21', '222.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('10', '13', '8', '0', '111', '111', '222.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('11', '13', '22', '0', '333', '333', '444.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('14', '14', '0', '0', '3131', '3131', '3333.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('15', '6', '14', '16', '1212', '1212', '1212.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('16', '6', '15', '16', '2121', '2121', '1212.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('17', '15', '5', '1', '121', '121', '122.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('18', '15', '5', '2', '122', '122', '122.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('19', '15', '6', '1', '123', '123', '111.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('20', '15', '6', '2', '124', '124', '211.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('23', '16', '21', '18', '100', '100', '120.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('24', '17', '5', '1', '1000', '1000', '999.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('25', '17', '5', '2', '1000', '1000', '888.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('26', '17', '5', '4', '1000', '1000', '777.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('27', '17', '6', '1', '1000', '1000', '1000.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('28', '17', '6', '2', '1000', '1000', '1000.00');
INSERT INTO `fish_goods_keyt_params_value_goods` VALUES ('29', '17', '6', '4', '1000', '1000', '1000.00');

-- ----------------------------
-- Table structure for fish_goods_nonkeyt_params
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_nonkeyt_params`;
CREATE TABLE `fish_goods_nonkeyt_params` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '类型对应的参数自增长id',
  `catId` mediumint(8) NOT NULL COMMENT '类别id',
  `name` varchar(64) NOT NULL COMMENT '参数名',
  `displayorder` smallint(6) NOT NULL COMMENT '展示顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_nonkeyt_params
-- ----------------------------
INSERT INTO `fish_goods_nonkeyt_params` VALUES ('1', '1', '长度', '1');
INSERT INTO `fish_goods_nonkeyt_params` VALUES ('2', '1', '颜色', '2');
INSERT INTO `fish_goods_nonkeyt_params` VALUES ('3', '2', '材质', '0');
INSERT INTO `fish_goods_nonkeyt_params` VALUES ('4', '2', '版型', '0');
INSERT INTO `fish_goods_nonkeyt_params` VALUES ('5', '8', '货号', '0');
INSERT INTO `fish_goods_nonkeyt_params` VALUES ('11', '2', '品牌', '0');

-- ----------------------------
-- Table structure for fish_goods_nonkeyt_params_value
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_nonkeyt_params_value`;
CREATE TABLE `fish_goods_nonkeyt_params_value` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `paramsId` mediumint(8) NOT NULL DEFAULT '0' COMMENT '参数id',
  `goodsId` mediumint(8) NOT NULL DEFAULT '0' COMMENT '商品id',
  `value` varchar(255) NOT NULL DEFAULT '' COMMENT '参数内容',
  `name` varchar(255) NOT NULL DEFAULT '',
  `displayorder` smallint(6) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_nonkeyt_params_value
-- ----------------------------
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('5', '3', '7', '嗯嗯嗯', '', '1');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('7', '0', '9', '富士', '品牌', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('8', '0', '9', '你猜', '材质', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('9', '5', '10', 'qeqw', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('10', '5', '13', '555', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('14', '0', '14', '1222', '12', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('17', '15', '6', '2mi', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('18', '16', '6', '富士', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('19', '3', '15', 'm', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('20', '4', '15', 'n', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('21', '11', '15', 'm', '', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('26', '0', '16', '富士', '品牌', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('27', '0', '16', '鲤鱼', '对象', '0');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('28', '3', '17', '金属', '', '1');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('29', '4', '17', '直', '', '2');
INSERT INTO `fish_goods_nonkeyt_params_value` VALUES ('30', '11', '17', '富士', '', '3');

-- ----------------------------
-- Table structure for fish_goods_pic
-- ----------------------------
DROP TABLE IF EXISTS `fish_goods_pic`;
CREATE TABLE `fish_goods_pic` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `goodsId` mediumint(8) unsigned NOT NULL,
  `pic` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_goods_pic
-- ----------------------------
INSERT INTO `fish_goods_pic` VALUES ('1', '9', 'upload/news/1451457306361.JPG');
INSERT INTO `fish_goods_pic` VALUES ('2', '9', 'upload/news/1451457309444.jpg');
INSERT INTO `fish_goods_pic` VALUES ('3', '13', 'upload/news/1452163363813.JPG');
INSERT INTO `fish_goods_pic` VALUES ('6', '13', 'upload/news/1452163707936.jpg');
INSERT INTO `fish_goods_pic` VALUES ('8', '14', 'upload/news/1452405608757.JPG');
INSERT INTO `fish_goods_pic` VALUES ('9', '15', 'upload/news/1452669537131.jpg');
INSERT INTO `fish_goods_pic` VALUES ('10', '15', 'upload/news/1452669542355.png');
INSERT INTO `fish_goods_pic` VALUES ('15', '16', 'upload/news/1452822765670.JPG');
INSERT INTO `fish_goods_pic` VALUES ('16', '16', 'upload/news/1452822771021.jpg');
INSERT INTO `fish_goods_pic` VALUES ('17', '17', 'upload/news/1453281743170.png');
INSERT INTO `fish_goods_pic` VALUES ('18', '17', 'upload/news/1453281751569.png');

-- ----------------------------
-- Table structure for fish_harvest
-- ----------------------------
DROP TABLE IF EXISTS `fish_harvest`;
CREATE TABLE `fish_harvest` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `uid` mediumint(8) NOT NULL DEFAULT '0' COMMENT '发布人id',
  `content` varchar(2048) NOT NULL DEFAULT '' COMMENT '内容',
  `favorites` int(10) NOT NULL DEFAULT '0' COMMENT '赞的个数',
  `comments` int(10) NOT NULL DEFAULT '0' COMMENT '评论数',
  `dateline` datetime DEFAULT NULL COMMENT '发布日期',
  `address` varchar(255) DEFAULT '' COMMENT '发布地址',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '0,有效，1无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_harvest
-- ----------------------------
INSERT INTO `fish_harvest` VALUES ('1', '1', 'dgrdhrtfyhu6rtjhtgfjnghfkmhjmdg', '1', '0', '2016-01-18 16:39:42', '', '1');
INSERT INTO `fish_harvest` VALUES ('2', '1', '法大使馆的乳房和高峰会觉得他已经', '0', '0', '2016-01-18 16:39:38', '', '1');
INSERT INTO `fish_harvest` VALUES ('3', '1', '个人特色的优惠点人头分红具体费用将福建体育', '0', '0', '2016-01-18 16:39:00', '成都', '1');
INSERT INTO `fish_harvest` VALUES ('4', '1', '地方如果对方不会官方机构和看韩剧客户将考核结果', '0', '0', '2016-01-18 16:38:53', '杭州', '1');
INSERT INTO `fish_harvest` VALUES ('5', '1', '发多少个地方很多符合规范化股份结构和机构和模拟结果还没回家', '0', '0', '2016-01-18 16:38:50', '阿尔卑斯山脉', '1');
INSERT INTO `fish_harvest` VALUES ('6', '1', '今天天气不错，终于调到了大鱼哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '1', '0', '2016-01-12 10:56:36', '南海', '1');
INSERT INTO `fish_harvest` VALUES ('7', '1', '啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦', '0', '0', '2016-01-12 10:57:29', '南湖', '1');
INSERT INTO `fish_harvest` VALUES ('8', '1', '测试鱼获1', '0', '0', '2016-01-12 11:18:47', '青藏高原', '1');
INSERT INTO `fish_harvest` VALUES ('9', '1', '爱对方是否打算打算打算多少个地方', '0', '0', '2016-01-18 16:38:56', '北京', '1');
INSERT INTO `fish_harvest` VALUES ('10', '3', '测试鱼获2', '0', '2', '2016-01-12 15:01:58', '四川 绵阳', '1');
INSERT INTO `fish_harvest` VALUES ('11', '21', '傍晚的钓鱼的瞬间', '0', '0', '2016-01-20 14:50:00', '四川 绵阳', '1');

-- ----------------------------
-- Table structure for fish_harvest_comments
-- ----------------------------
DROP TABLE IF EXISTS `fish_harvest_comments`;
CREATE TABLE `fish_harvest_comments` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `uid` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '发布人id',
  `harvestid` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '渔获id',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '评论内容',
  `dateline` datetime DEFAULT NULL,
  `touid` mediumint(8) unsigned DEFAULT NULL COMMENT '被评论的用户的id',
  PRIMARY KEY (`id`),
  KEY `FK_fish_harvest` (`harvestid`),
  KEY `FK_sec_user` (`uid`),
  CONSTRAINT `FK_fish_harvest` FOREIGN KEY (`harvestid`) REFERENCES `fish_harvest` (`id`),
  CONSTRAINT `FK_sec_user` FOREIGN KEY (`uid`) REFERENCES `sec_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_harvest_comments
-- ----------------------------
INSERT INTO `fish_harvest_comments` VALUES ('1', '1', '6', 'ddd', '2016-01-12 15:39:29', null);
INSERT INTO `fish_harvest_comments` VALUES ('2', '1', '6', ' vv', '2016-01-13 15:39:42', null);
INSERT INTO `fish_harvest_comments` VALUES ('3', '1', '6', 'kkkkk', '2016-01-12 17:07:31', '1');
INSERT INTO `fish_harvest_comments` VALUES ('4', '1', '6', 'kkkkk', '2016-01-12 17:43:05', '1');
INSERT INTO `fish_harvest_comments` VALUES ('5', '3', '10', '测试鱼获2', '2016-01-12 17:48:36', null);

-- ----------------------------
-- Table structure for fish_harvest_favorites
-- ----------------------------
DROP TABLE IF EXISTS `fish_harvest_favorites`;
CREATE TABLE `fish_harvest_favorites` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `uid` mediumint(8) NOT NULL DEFAULT '0' COMMENT '点赞用户id',
  `harvestid` mediumint(8) NOT NULL DEFAULT '0' COMMENT '渔获id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_harvest_favorites
-- ----------------------------
INSERT INTO `fish_harvest_favorites` VALUES ('1', '3', '6');
INSERT INTO `fish_harvest_favorites` VALUES ('2', '21', '1');

-- ----------------------------
-- Table structure for fish_harvest_pictures
-- ----------------------------
DROP TABLE IF EXISTS `fish_harvest_pictures`;
CREATE TABLE `fish_harvest_pictures` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `hid` mediumint(8) unsigned NOT NULL,
  `picture` varchar(1024) NOT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_harvest_pictures
-- ----------------------------
INSERT INTO `fish_harvest_pictures` VALUES ('4', '7', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('5', '8', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('6', '8', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('7', '9', 'upload/avatar/1452569924665.png');
INSERT INTO `fish_harvest_pictures` VALUES ('8', '9', 'upload/avatar/1452569924665.png');
INSERT INTO `fish_harvest_pictures` VALUES ('9', '9', 'upload/avatar/1452569924665.png');
INSERT INTO `fish_harvest_pictures` VALUES ('10', '10', 'upload/avatar/1452569924665.png');
INSERT INTO `fish_harvest_pictures` VALUES ('11', '10', 'upload/avatar/1452569924665.png');
INSERT INTO `fish_harvest_pictures` VALUES ('12', '10', 'upload/avatar/1452569934163.png');
INSERT INTO `fish_harvest_pictures` VALUES ('13', '10', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('14', '1', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('15', '2', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('16', '3', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('17', '4', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('18', '5', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('19', '6', 'upload/avatar/1452582106333.png');
INSERT INTO `fish_harvest_pictures` VALUES ('20', '11', 'upload/avatar/1453272535526.png');
INSERT INTO `fish_harvest_pictures` VALUES ('21', '11', 'upload/avatar/1453272550343.png');

-- ----------------------------
-- Table structure for fish_mall_limited
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_limited`;
CREATE TABLE `fish_mall_limited` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '限时特惠活动自增长id',
  `startTime` time NOT NULL COMMENT '开始时间',
  `endTime` time NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_mall_limited
-- ----------------------------
INSERT INTO `fish_mall_limited` VALUES ('1', '09:00:00', '12:00:00');
INSERT INTO `fish_mall_limited` VALUES ('2', '12:00:00', '15:00:00');
INSERT INTO `fish_mall_limited` VALUES ('3', '15:00:00', '18:00:00');
INSERT INTO `fish_mall_limited` VALUES ('4', '18:00:00', '21:00:00');

-- ----------------------------
-- Table structure for fish_mall_limited_goods
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_limited_goods`;
CREATE TABLE `fish_mall_limited_goods` (
  `limitedId` mediumint(8) unsigned NOT NULL COMMENT '限时特惠活动id',
  `goodsId` mediumint(8) unsigned NOT NULL COMMENT '商品id',
  `totalCount` int(10) unsigned NOT NULL COMMENT '商品总数量',
  `remain` int(10) unsigned NOT NULL COMMENT '商品剩余数量',
  `limitedPrice` int(10) unsigned NOT NULL COMMENT '限时特惠价格',
  PRIMARY KEY (`limitedId`,`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_mall_limited_goods
-- ----------------------------
INSERT INTO `fish_mall_limited_goods` VALUES ('2', '15', '12', '12', '8888');
INSERT INTO `fish_mall_limited_goods` VALUES ('4', '2', '12', '12', '11');

-- ----------------------------
-- Table structure for fish_mall_seckill
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_seckill`;
CREATE TABLE `fish_mall_seckill` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '秒杀活动id',
  `time` time NOT NULL COMMENT '整点秒杀时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_mall_seckill
-- ----------------------------
INSERT INTO `fish_mall_seckill` VALUES ('1', '10:00:00');
INSERT INTO `fish_mall_seckill` VALUES ('2', '12:00:00');
INSERT INTO `fish_mall_seckill` VALUES ('3', '14:00:00');
INSERT INTO `fish_mall_seckill` VALUES ('4', '16:00:00');
INSERT INTO `fish_mall_seckill` VALUES ('5', '18:00:00');
INSERT INTO `fish_mall_seckill` VALUES ('6', '20:00:00');

-- ----------------------------
-- Table structure for fish_mall_seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_seckill_goods`;
CREATE TABLE `fish_mall_seckill_goods` (
  `seckillId` mediumint(8) unsigned NOT NULL COMMENT '秒杀id',
  `goodsId` mediumint(8) unsigned NOT NULL COMMENT '商品id',
  `totalCount` int(10) unsigned NOT NULL COMMENT '商品总数',
  `remain` int(10) unsigned NOT NULL COMMENT '剩余数量',
  `seckillPrice` int(10) unsigned NOT NULL COMMENT '秒杀价格',
  PRIMARY KEY (`seckillId`,`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_mall_seckill_goods
-- ----------------------------
INSERT INTO `fish_mall_seckill_goods` VALUES ('1', '1', '45', '45', '555');
INSERT INTO `fish_mall_seckill_goods` VALUES ('3', '15', '66', '66', '877');

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
  `createTime` datetime NOT NULL,
  `endTime` datetime DEFAULT NULL COMMENT '截止时间',
  `isClosed` tinyint(1) NOT NULL DEFAULT '0',
  `isSuccess` tinyint(1) NOT NULL DEFAULT '0',
  `percent` double(3,2) NOT NULL DEFAULT '0.00' COMMENT '参团百分比',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_mall_teambuy
-- ----------------------------
INSERT INTO `fish_mall_teambuy` VALUES ('1', '1', '1', '1210', '1210', '11.00000', '2016-01-15 15:59:40', '2016-01-30 15:59:17', '0', '0', '0.00');
INSERT INTO `fish_mall_teambuy` VALUES ('2', '1', '1', '55', '55', '3000.00000', '2016-01-15 16:00:09', '2016-01-29 15:54:48', '0', '0', '0.00');
INSERT INTO `fish_mall_teambuy` VALUES ('3', '1', '2', '100', '100', '4000.00000', '2016-01-15 16:00:33', '2016-01-29 15:59:24', '0', '0', '0.00');
INSERT INTO `fish_mall_teambuy` VALUES ('4', '1', '8', '40', '40', '33333.00000', '2016-01-15 16:18:59', '2016-01-30 16:17:52', '0', '0', '0.00');
INSERT INTO `fish_mall_teambuy` VALUES ('5', '1', '9', '50', '50', '1300.00000', '2016-01-18 10:09:58', '2016-01-30 10:09:09', '0', '0', '0.00');

-- ----------------------------
-- Table structure for fish_mall_teambuy_user
-- ----------------------------
DROP TABLE IF EXISTS `fish_mall_teambuy_user`;
CREATE TABLE `fish_mall_teambuy_user` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `goodsDetailId` mediumint(8) unsigned NOT NULL,
  `tid` mediumint(8) unsigned NOT NULL COMMENT '商品id',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `nickname` varchar(255) NOT NULL DEFAULT '',
  `status` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '待付款',
  `createTime` datetime NOT NULL,
  `price` decimal(15,0) NOT NULL,
  `isClosed` tinyint(1) NOT NULL DEFAULT '0',
  `address` mediumint(8) unsigned DEFAULT NULL,
  `pay` tinyint(3) unsigned DEFAULT NULL COMMENT '付款方式',
  `payTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_mall_teambuy_user
-- ----------------------------
INSERT INTO `fish_mall_teambuy_user` VALUES ('1', '8', '5', '21', 'nico', '0', '2016-01-18 16:31:34', '1300', '0', '15', null, null);
INSERT INTO `fish_mall_teambuy_user` VALUES ('2', '15', '1', '21', 'nico', '0', '2016-01-19 09:21:27', '11', '0', '1', null, null);

-- ----------------------------
-- Table structure for fish_news
-- ----------------------------
DROP TABLE IF EXISTS `fish_news`;
CREATE TABLE `fish_news` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '新闻自增id',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '发布人id',
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '标题',
  `picture` varchar(10240) NOT NULL DEFAULT '' COMMENT '图片地址',
  `pictures` varchar(1024) NOT NULL DEFAULT '' COMMENT '详情中包括的多张图片',
  `summary` varchar(1024) NOT NULL DEFAULT '' COMMENT '摘要',
  `content` varchar(8192) NOT NULL DEFAULT '' COMMENT '新闻详情',
  `verify` tinyint(3) NOT NULL DEFAULT '0' COMMENT '审核：2（未通过）、0：待审核、1：审核通过',
  `dateline` datetime NOT NULL COMMENT '创建时间',
  `comments` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
  `favorite` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `catid` mediumint(8) unsigned NOT NULL COMMENT '分类id',
  `recommend` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '0 不推荐，1 推荐',
  `up` int(10) unsigned NOT NULL DEFAULT '0',
  `down` int(10) unsigned NOT NULL DEFAULT '0',
  `starttime` datetime DEFAULT NULL,
  `view` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '阅读量,作为热点新闻的依据',
  `endtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_news` (`uid`),
  KEY `FK_fish_news_category` (`catid`),
  CONSTRAINT `fish_news_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sec_user` (`id`),
  CONSTRAINT `FK_fish_news_category` FOREIGN KEY (`catid`) REFERENCES `fish_news_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='自媒体';

-- ----------------------------
-- Records of fish_news
-- ----------------------------
INSERT INTO `fish_news` VALUES ('2', '1', 'hhhh', '', '', 'hhh', 'uuu', '1', '2015-12-17 19:52:41', '16', '0', '1', '1', '1', '0', null, '11', null);
INSERT INTO `fish_news` VALUES ('4', '1', '标题', '', '', '', '内容', '1', '2015-12-17 20:21:44', '8', '0', '1', '0', '1', '1', null, '54', null);
INSERT INTO `fish_news` VALUES ('6', '1', '测试', '', '', '新闻摘要', '美女垂钓', '1', '2015-12-17 20:25:05', '0', '0', '4', '0', '2', '1', null, '21', null);
INSERT INTO `fish_news` VALUES ('8', '1', 'sdfsdf', '', '', '', '	                    	dfdsfdsfdsf', '1', '2015-12-21 17:37:26', '0', '0', '2', '0', '0', '0', '2015-12-21 00:00:00', '0', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('9', '1', 'dsfdsfdsf', '', '', '', 'dfdsfdsfdsfds', '1', '2015-12-20 11:31:33', '0', '0', '5', '1', '0', '0', null, '0', null);
INSERT INTO `fish_news` VALUES ('12', '1', '美女测试', '', '', '', '萨塞打死哦到敬爱是道具爱上分局噢is到哈佛；i<br />', '1', '2015-12-21 17:23:02', '0', '0', '4', '1', '0', '0', null, '0', null);
INSERT INTO `fish_news` VALUES ('13', '1', '标题测试', '', '', '', '第三方大师法大声道', '1', '2015-12-21 17:26:54', '0', '0', '2', '1', '0', '0', '2015-12-22 00:00:00', '4', '2015-12-25 00:00:00');
INSERT INTO `fish_news` VALUES ('14', '1', 'title1', '', '', '', '	                    	<img src=\"/swust_fish/upload/kindeditor/1451044393601.png\" alt=\"\" height=\"100\" width=\"100\" />', '2', '2015-12-27 15:00:45', '0', '0', '2', '0', '0', '0', '2015-12-21 00:00:00', '0', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('15', '1', 'dfgdfgfg', '', '', '', 'rfergdfgdfgdf', '2', '2015-12-31 09:29:38', '0', '0', '2', '0', '0', '0', '2015-12-21 00:00:00', '4', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('18', '15', '测试1', '', '', '', '山东黄金阿红就阿斯顿哈哈镜<strong>库房哈hd声卡拉房间卡交水电费了空间阿发开发哪款减肥哈看男方家里卡教室里的空间啊快速将阿里肯定将阿里库房尽快按时间段卡拉胶的辣椒水库放假阿</strong>克苏飞讲啊第三季卡很激动哈的空间阿斯咖啡拉开空间法尽快放假啊刷卡缴费拉开经发局空间按浪费空间哦啊接骄傲了福晶科技安家费奥卡福尽快阿飞了解爱疯佳佳放辣椒粉阿拉法卡；；饭量大理发放辣椒粉家居服啊快睡觉饭卡健身房卡卡空间的卡机饭卡即可讲啊尽快打款积分啊空间打开按揭付款安杰丽卡的骄傲开发垃圾快速将对方阿克江安康点击拉克将罚款安静的卡建档立卡就打卡机看得见啊可视对讲阿克苏加拉看觉得阿卡是否卡视角饭卡上飞机阿克苏放假啊开放的骄傲了就卡卡了水井坊阿拉卡发卡机发卡机浪费空间啊了<img src=\"/swust_fish/upload/kindeditor/1452569554618.png\" width=\"1600\" height=\"859\" alt=\"\" />', '1', '2016-01-12 11:33:05', '0', '0', '2', '0', '0', '0', '2015-12-21 00:00:00', '0', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('19', '15', '富士周年庆', '', '', '', '富士周年庆，钓者的聚会<img src=\"/swust_fish/upload/kindeditor/1452741852111.jpg\" width=\"1280\" height=\"853\" alt=\"\" />', '1', '2016-01-14 11:24:22', '0', '0', '1', '0', '0', '0', '2015-12-21 00:00:00', '1', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('20', '15', '享受钓鱼的乐趣', '', '', '', '<p>\r\n	<img src=\"/swust_fish/upload/kindeditor/1452742214008.jpg\" width=\"670\" height=\"991\" alt=\"\" />\r\n</p>\r\n<p>\r\n	享受钓鱼的乐趣\r\n</p>\r\n<p>\r\n	<img src=\"/swust_fish/upload/kindeditor/1452742247180.jpg\" alt=\"\" />\r\n</p>', '1', '2016-01-14 11:30:55', '0', '0', '4', '1', '0', '0', '2015-12-21 00:00:00', '1', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('21', '15', '菜鸟怎么学钓鱼？', '', '', '', '菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？菜鸟怎么学钓鱼？<img src=\"/swust_fish/upload/kindeditor/1452742879840.jpg\" width=\"1280\" height=\"1707\" alt=\"\" /><img src=\"upload/kindeditor/1452742911933.jpg\" alt=\"\" /><img src=\"upload/kindeditor/1452742913946.jpg\" alt=\"\" /><img src=\"upload/kindeditor/1452742915881.jpg\" alt=\"\" /><img src=\"upload/kindeditor/1452742917300.jpg\" alt=\"\" />', '1', '2016-01-14 11:42:05', '0', '0', '6', '1', '0', '0', '2015-12-21 00:00:00', '2', '2015-12-22 00:00:00');
INSERT INTO `fish_news` VALUES ('28', '15', '钓鱼英雄赛', '', '', '', '<h1 style=\"text-align:center;\">\r\n	<span>钓鱼英雄赛</span>\r\n</h1>\r\n<p>\r\n	<span>&nbsp; &nbsp; &nbsp;想上2016年钓鱼英雄榜吗？你终于等到了，为了庆祝富士渔具10周年庆典，我们特举办了这次钓鱼赛事！欢迎广大钓友参加！钓鱼英雄非你莫属@！想上2016年钓鱼英雄榜吗？你终于等到了，为了庆祝富士渔具10周年庆典，我们特举办了这次钓鱼赛事！欢迎广大钓友参加！钓鱼英雄非你莫属@！想上2016年钓鱼英雄榜吗？你终于等到了，为了庆祝富士渔具10周年庆典，我们特举办了这次钓鱼赛事！欢迎广大钓友参加！钓鱼英雄非你莫属@！想上2016年钓鱼英雄榜吗？你终于等到了，为了庆祝富士渔具10周年庆典，我们特举办了这次钓鱼赛事！欢迎广大钓友参加！钓鱼英雄非你莫属@！</span>\r\n</p>\r\n<p>\r\n	<span><br />\r\n</span>\r\n</p>\r\n<p>\r\n	<span><img src=\"/swust_fish/upload/kindeditor/1452762152050.jpg\" width=\"1280\" height=\"960\" alt=\"\" /><br />\r\n</span>\r\n</p>', '1', '2016-01-14 17:02:37', '3', '0', '1', '1', '1', '0', '2015-12-21 00:00:00', '11', '2015-12-22 00:00:00');

-- ----------------------------
-- Table structure for fish_news_category
-- ----------------------------
DROP TABLE IF EXISTS `fish_news_category`;
CREATE TABLE `fish_news_category` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `catname` varchar(255) NOT NULL DEFAULT '' COMMENT '新闻类型',
  `displayorder` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_news_category
-- ----------------------------
INSERT INTO `fish_news_category` VALUES ('1', '推广', '1');
INSERT INTO `fish_news_category` VALUES ('2', '赛事', '2');
INSERT INTO `fish_news_category` VALUES ('4', '美女垂钓', '4');
INSERT INTO `fish_news_category` VALUES ('5', '野外垂钓', '5');
INSERT INTO `fish_news_category` VALUES ('6', '菜鸟教学', '6');

-- ----------------------------
-- Table structure for fish_news_comment
-- ----------------------------
DROP TABLE IF EXISTS `fish_news_comment`;
CREATE TABLE `fish_news_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `newsid` int(11) unsigned NOT NULL COMMENT '对应新闻id',
  `touid` mediumint(8) DEFAULT NULL COMMENT '表示对该用户id的评论',
  `uid` int(11) unsigned NOT NULL COMMENT '发表评论人id',
  `content` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '评论内容',
  `dateline` datetime NOT NULL COMMENT '发表评论的时间',
  `favorite` int(9) unsigned NOT NULL DEFAULT '0' COMMENT '评论的赞',
  `comments` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '评论数量',
  `down` int(10) unsigned NOT NULL DEFAULT '0',
  `up` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '顶的次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_icelandic_ci;

-- ----------------------------
-- Records of fish_news_comment
-- ----------------------------
INSERT INTO `fish_news_comment` VALUES ('26', '4', null, '1', '这条评论', '2015-12-27 14:35:21', '0', '0', '0', '1');
INSERT INTO `fish_news_comment` VALUES ('27', '4', null, '1', '这条评论', '2015-12-27 14:35:21', '0', '0', '0', '0');
INSERT INTO `fish_news_comment` VALUES ('28', '2', null, '1', '第二条评论', '2015-12-27 14:59:12', '0', '0', '0', '1');
INSERT INTO `fish_news_comment` VALUES ('29', '4', null, '3', '测试评论2', '2016-01-11 16:47:36', '0', '0', '0', '0');
INSERT INTO `fish_news_comment` VALUES ('30', '4', null, '3', '测试评论3', '2016-01-11 16:53:20', '0', '0', '0', '0');
INSERT INTO `fish_news_comment` VALUES ('31', '28', null, '21', '测试评论1', '2016-01-20 13:03:15', '0', '0', '0', '1');
INSERT INTO `fish_news_comment` VALUES ('32', '28', '21', '21', '测试评论2', '2016-01-20 13:10:38', '0', '0', '0', '1');
INSERT INTO `fish_news_comment` VALUES ('33', '28', null, '21', '测试评论3', '2016-01-20 13:20:21', '0', '0', '0', '1');

-- ----------------------------
-- Table structure for fish_news_comment_updown
-- ----------------------------
DROP TABLE IF EXISTS `fish_news_comment_updown`;
CREATE TABLE `fish_news_comment_updown` (
  `newsCommentId` mediumint(8) NOT NULL COMMENT '资讯评论的id',
  `uid` mediumint(8) NOT NULL COMMENT '用户id',
  `ud` tinyint(2) NOT NULL,
  PRIMARY KEY (`newsCommentId`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_news_comment_updown
-- ----------------------------
INSERT INTO `fish_news_comment_updown` VALUES ('1', '1', '1');
INSERT INTO `fish_news_comment_updown` VALUES ('3', '1', '1');
INSERT INTO `fish_news_comment_updown` VALUES ('4', '1', '2');
INSERT INTO `fish_news_comment_updown` VALUES ('26', '3', '1');
INSERT INTO `fish_news_comment_updown` VALUES ('28', '3', '1');
INSERT INTO `fish_news_comment_updown` VALUES ('31', '21', '1');
INSERT INTO `fish_news_comment_updown` VALUES ('32', '21', '1');
INSERT INTO `fish_news_comment_updown` VALUES ('33', '21', '1');

-- ----------------------------
-- Table structure for fish_news_report
-- ----------------------------
DROP TABLE IF EXISTS `fish_news_report`;
CREATE TABLE `fish_news_report` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `newsid` int(11) unsigned NOT NULL COMMENT '被举报新闻的id',
  `uid` int(11) unsigned NOT NULL COMMENT '举报人的id',
  `content` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '举报内容',
  `dateline` datetime NOT NULL COMMENT '举报时间',
  `isRead` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '1，未查看  2，已查看',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_icelandic_ci COMMENT='举报新闻的列表';

-- ----------------------------
-- Records of fish_news_report
-- ----------------------------
INSERT INTO `fish_news_report` VALUES ('3', '2', '1', 'w', '2015-12-18 19:13:35', '2');
INSERT INTO `fish_news_report` VALUES ('4', '2', '1', 'zdasfa', '2015-12-21 09:21:32', '2');
INSERT INTO `fish_news_report` VALUES ('5', '4', '3', '举报举报', '2016-01-11 17:19:30', '1');
INSERT INTO `fish_news_report` VALUES ('6', '4', '3', '举报举报', '2016-01-11 17:21:57', '1');

-- ----------------------------
-- Table structure for fish_news_updown
-- ----------------------------
DROP TABLE IF EXISTS `fish_news_updown`;
CREATE TABLE `fish_news_updown` (
  `newsid` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '新闻id',
  `uid` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '顶踩用户id',
  `ud` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:未顶踩 1:顶 2:踩',
  KEY `FK_fish_news` (`newsid`),
  CONSTRAINT `FK_fish_news` FOREIGN KEY (`newsid`) REFERENCES `fish_news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_news_updown
-- ----------------------------
INSERT INTO `fish_news_updown` VALUES ('6', '1', '-1');
INSERT INTO `fish_news_updown` VALUES ('4', '3', '1');
INSERT INTO `fish_news_updown` VALUES ('2', '3', '1');
INSERT INTO `fish_news_updown` VALUES ('28', '21', '1');

-- ----------------------------
-- Table structure for fish_order
-- ----------------------------
DROP TABLE IF EXISTS `fish_order`;
CREATE TABLE `fish_order` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单自增长id',
  `goodsDetailId` mediumint(8) unsigned NOT NULL COMMENT '订单对应商品id',
  `dateline` datetime NOT NULL COMMENT '创建时间',
  `price` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '订单价格',
  `count` mediumint(8) unsigned NOT NULL DEFAULT '1' COMMENT '商品数量',
  `uid` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '订单状态，-1：取消订单;0：待付款；1：待发货；2：待签收；3：已确认收货;',
  `isClosed` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '订单删除',
  `address` mediumint(8) NOT NULL DEFAULT '0' COMMENT '送货地址',
  `pay` tinyint(3) NOT NULL DEFAULT '0' COMMENT '付款方式,0：支付宝，1：微信，',
  `invoice` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否需要发票，0：否，1：是',
  `invoiceTitle` varchar(128) DEFAULT NULL COMMENT '发票抬头',
  `tuiStatus` smallint(6) NOT NULL DEFAULT '0' COMMENT '0:订单正常；1：用户申请退款；2：商家退款成功；3：商家拒绝退款',
  `isReminder` tinyint(1) NOT NULL DEFAULT '0' COMMENT '提醒发货',
  `payTime` datetime DEFAULT NULL COMMENT '付款时间',
  `shopId` mediumint(8) unsigned NOT NULL COMMENT '商家id',
  `isFubi` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of fish_order
-- ----------------------------
INSERT INTO `fish_order` VALUES ('1', '9', '2016-01-04 19:26:33', '11111.00', '1', '21', '-1', '0', '0', '0', '0', '', '0', '1', null, '1', '0');
INSERT INTO `fish_order` VALUES ('2', '5', '2016-01-04 19:47:11', '11111.00', '1', '21', '1', '0', '0', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('3', '5', '2016-01-04 19:53:42', '11111.00', '1', '21', '2', '0', '0', '0', '0', '', '1', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('4', '5', '2016-01-04 19:54:21', '11111.00', '1', '21', '1', '0', '0', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('5', '8', '2016-01-04 20:06:32', '1212.00', '1', '21', '2', '0', '0', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('6', '8', '2016-01-04 21:01:07', '99.00', '1', '21', '3', '0', '1', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('7', '8', '2016-01-05 15:39:35', '88.00', '1', '21', '2', '0', '0', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('8', '8', '2016-01-05 15:50:29', '77.00', '1', '21', '1', '0', '0', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('9', '8', '2016-01-05 15:50:51', '66.00', '1', '21', '2', '0', '0', '0', '0', '', '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('10', '8', '2016-01-05 16:38:29', '55.00', '1', '21', '2', '0', '0', '0', '0', null, '2', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('11', '8', '2016-01-05 16:39:25', '444.00', '1', '21', '2', '0', '0', '0', '0', null, '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('12', '8', '2016-01-05 16:40:03', '123.00', '1', '21', '0', '0', '0', '0', '0', null, '0', '0', null, '1', '0');
INSERT INTO `fish_order` VALUES ('13', '10', '2016-01-07 18:54:46', '1212.00', '1', '21', '0', '0', '0', '0', '0', null, '0', '0', null, '1', '0');

-- ----------------------------
-- Table structure for fish_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `fish_order_goods`;
CREATE TABLE `fish_order_goods` (
  `orderId` mediumint(8) NOT NULL DEFAULT '0' COMMENT '订单id',
  `goodsId` mediumint(8) NOT NULL DEFAULT '0' COMMENT '商品id',
  `status` mediumint(8) NOT NULL DEFAULT '0' COMMENT '状态，0：未完成；1：待评价；2：已评价',
  PRIMARY KEY (`orderId`,`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_order_goods
-- ----------------------------

-- ----------------------------
-- Table structure for fish_order_package
-- ----------------------------
DROP TABLE IF EXISTS `fish_order_package`;
CREATE TABLE `fish_order_package` (
  `orderId` mediumint(8) unsigned NOT NULL,
  `orderPackageId` mediumint(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_order_package
-- ----------------------------
INSERT INTO `fish_order_package` VALUES ('6', '1');
INSERT INTO `fish_order_package` VALUES ('7', '1');
INSERT INTO `fish_order_package` VALUES ('8', '1');
INSERT INTO `fish_order_package` VALUES ('9', '2');
INSERT INTO `fish_order_package` VALUES ('10', '2');
INSERT INTO `fish_order_package` VALUES ('11', '2');
INSERT INTO `fish_order_package` VALUES ('12', '2');
INSERT INTO `fish_order_package` VALUES ('13', '3');

-- ----------------------------
-- Table structure for fish_package
-- ----------------------------
DROP TABLE IF EXISTS `fish_package`;
CREATE TABLE `fish_package` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `shopId` mediumint(8) unsigned NOT NULL,
  `totalPrice` decimal(15,2) NOT NULL,
  `size` mediumint(8) unsigned NOT NULL DEFAULT '1' COMMENT '商品数',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `hasPostage` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否包邮(0：不包邮、1：包邮）',
  `address` mediumint(8) unsigned DEFAULT NULL COMMENT '地址id',
  `dateline` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_package
-- ----------------------------
INSERT INTO `fish_package` VALUES ('1', '1', '333.00', '3', '1', '0', '1', '2016-01-07 16:26:13');
INSERT INTO `fish_package` VALUES ('2', '1', '333.00', '4', '1', '0', '2', '2016-01-07 16:26:16');
INSERT INTO `fish_package` VALUES ('3', '1', '333.00', '1', '1', '0', '3', '2016-01-07 18:56:56');

-- ----------------------------
-- Table structure for fish_postage
-- ----------------------------
DROP TABLE IF EXISTS `fish_postage`;
CREATE TABLE `fish_postage` (
  `id` mediumint(8) DEFAULT NULL COMMENT '包邮活动自增长id',
  `shopId` mediumint(8) DEFAULT NULL COMMENT '商家id',
  `postagePrice` int(10) DEFAULT NULL COMMENT '包邮价格起点'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_postage
-- ----------------------------

-- ----------------------------
-- Table structure for fish_secret_code
-- ----------------------------
DROP TABLE IF EXISTS `fish_secret_code`;
CREATE TABLE `fish_secret_code` (
  `tel` varchar(32) NOT NULL DEFAULT '',
  `secretcode` varchar(64) NOT NULL DEFAULT '' COMMENT '产生的随机码',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_secret_code
-- ----------------------------
INSERT INTO `fish_secret_code` VALUES ('18144253098', '123536', '2016-01-20 17:37:31');
INSERT INTO `fish_secret_code` VALUES ('18181743097', '017646', '2016-01-20 17:35:06');

-- ----------------------------
-- Table structure for fish_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `fish_shopping_cart`;
CREATE TABLE `fish_shopping_cart` (
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `goodsDetailId` mediumint(8) unsigned NOT NULL COMMENT '商品id',
  `goodsTitle` varchar(64) NOT NULL COMMENT '商品标题',
  `goodsCover` varchar(128) NOT NULL COMMENT '商品封面',
  `goodsPrice` decimal(15,5) unsigned NOT NULL COMMENT '商品价格',
  `goodsPostage` decimal(15,5) unsigned NOT NULL COMMENT '邮费',
  `shopId` mediumint(8) unsigned NOT NULL COMMENT '商品所属的商家id',
  `shopAvatar` varchar(128) NOT NULL COMMENT '商家头像',
  `shopName` varchar(64) NOT NULL COMMENT '商家名字',
  `count` int(10) unsigned NOT NULL COMMENT '数量',
  `datetime` datetime NOT NULL COMMENT '加入购物车时间',
  PRIMARY KEY (`uid`,`goodsDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_shopping_cart
-- ----------------------------
INSERT INTO `fish_shopping_cart` VALUES ('1', '5', '东莞打工', 'upload/news/1451365536963.jpg', '222.00000', '0.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '3', '2016-01-04 14:36:14');
INSERT INTO `fish_shopping_cart` VALUES ('1', '8', '1212', 'upload/news/1451457298443.jpg', '111.00000', '0.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '1', '2016-01-04 14:41:18');
INSERT INTO `fish_shopping_cart` VALUES ('1', '9', '1212', 'upload/news/1451457298443.jpg', '222.00000', '0.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '1', '2016-01-04 14:36:46');
INSERT INTO `fish_shopping_cart` VALUES ('14', '15', '鱼线', 'upload/news/1451359181209.jpg', '1212.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '1', '2016-01-16 11:11:00');
INSERT INTO `fish_shopping_cart` VALUES ('14', '20', 'yugan', 'upload/news/1452669524816.png', '211.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '2', '2016-01-16 15:47:06');
INSERT INTO `fish_shopping_cart` VALUES ('21', '15', '鱼线', 'upload/news/1451359181209.jpg', '1212.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '6', '2016-01-20 14:11:47');
INSERT INTO `fish_shopping_cart` VALUES ('21', '20', 'yugan', 'upload/news/1452669524816.png', '211.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '3', '2016-01-20 14:44:16');
INSERT INTO `fish_shopping_cart` VALUES ('26', '15', '鱼线', 'upload/news/1451359181209.jpg', '1212.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '2', '2016-01-19 15:47:23');
INSERT INTO `fish_shopping_cart` VALUES ('26', '17', 'yugan', 'upload/news/1452669524816.png', '122.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '1', '2016-01-19 15:48:20');
INSERT INTO `fish_shopping_cart` VALUES ('27', '5', '东莞打工', 'upload/news/1451365536963.jpg', '222.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '2', '2016-01-20 17:44:33');
INSERT INTO `fish_shopping_cart` VALUES ('27', '16', '鱼线', 'upload/news/1451359181209.jpg', '1212.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '4', '2016-01-20 17:44:29');
INSERT INTO `fish_shopping_cart` VALUES ('27', '20', 'yugan', 'upload/news/1452669524816.png', '211.00000', '12.00000', '1', 'upload/news/14513423423309444.jpg', '富士户外旗舰店', '5', '2016-01-20 17:44:25');

-- ----------------------------
-- Table structure for fish_shop_count
-- ----------------------------
DROP TABLE IF EXISTS `fish_shop_count`;
CREATE TABLE `fish_shop_count` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `uid` mediumint(8) NOT NULL DEFAULT '0' COMMENT '用户id',
  `shopName` varchar(64) NOT NULL DEFAULT '' COMMENT '商铺名字',
  `picture` varchar(255) NOT NULL DEFAULT '' COMMENT '商铺头像',
  `goodsNum` mediumint(8) NOT NULL DEFAULT '0',
  `decription` varchar(255) NOT NULL DEFAULT '' COMMENT '商店描述',
  `orderNum` mediumint(8) NOT NULL DEFAULT '0' COMMENT '订单数量',
  `nonPostage` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '包邮价格',
  `postagePrice` decimal(15,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '邮费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_shop_count
-- ----------------------------
INSERT INTO `fish_shop_count` VALUES ('1', '1', '富士户外旗舰店', 'upload/news/14513423423309444.jpg', '5', '', '0', '2000.00', '12.00');

-- ----------------------------
-- Table structure for fish_user_address
-- ----------------------------
DROP TABLE IF EXISTS `fish_user_address`;
CREATE TABLE `fish_user_address` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '姓名',
  `tel` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '电话',
  `province` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '市',
  `district` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '区',
  `street` varchar(128) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '街道',
  `zipCode` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `isDefault` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否设为默认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf32 COMMENT='收货地址表';

-- ----------------------------
-- Records of fish_user_address
-- ----------------------------
INSERT INTO `fish_user_address` VALUES ('1', '孙悟空', '13990122270', '四川省', '绵阳市', '涪城区', '西南科技大学', '621000', '1', '0');
INSERT INTO `fish_user_address` VALUES ('2', '猪八戒', '15100000000', '四川省', '绵阳市', '游仙区', '呵呵哈哈', '621020', '1', '0');
INSERT INTO `fish_user_address` VALUES ('3', 'sdfa', '123', '四川省', '达州市', '大大', 'da', '12345', '1', '1');
INSERT INTO `fish_user_address` VALUES ('4', 'sdfa', '15882781032', '四川省', '达州市', '大大', 'da', '12345', '3', '0');
INSERT INTO `fish_user_address` VALUES ('6', 'sdfa', '123', '四川省', '达州市', '大大', 'da', '12', '1', '0');
INSERT INTO `fish_user_address` VALUES ('7', '朝阳区群众', '18144253098', '北京市', '市辖区', '朝阳区', '群众聚集地', '100000', '3', '1');
INSERT INTO `fish_user_address` VALUES ('9', '瞿子朋', '15882781032', '四川省', '绵阳市', '涪城区', '西科大', '621000', '21', '1');
INSERT INTO `fish_user_address` VALUES ('10', '周杰', '18114444445', '北京市', '市辖区', '朝阳区', '群众聚集地', '100000', '14', '0');
INSERT INTO `fish_user_address` VALUES ('12', '青峰大辉', '18144255555', '辽宁省', '鞍山市', '铁西区', '呵呵呵呵', '114000', '14', '1');
INSERT INTO `fish_user_address` VALUES ('15', '周杰伦', '18144253098', '四川省', '雅安市', '雨城区', '在那遥远的地方', '625000', '21', '0');
INSERT INTO `fish_user_address` VALUES ('18', '我', '18181743097', '四川省', '阿坝藏族羌族自治州', '金川县', '我', '624100', '26', '0');

-- ----------------------------
-- Table structure for fish_user_change_email
-- ----------------------------
DROP TABLE IF EXISTS `fish_user_change_email`;
CREATE TABLE `fish_user_change_email` (
  `uid` mediumint(8) unsigned NOT NULL,
  `newEmail` varchar(255) NOT NULL DEFAULT '' COMMENT '待更换邮箱',
  `createTime` datetime DEFAULT NULL,
  `changed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否更换成功',
  `verify` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_user_change_email
-- ----------------------------
INSERT INTO `fish_user_change_email` VALUES ('2', 'ltinghuang@163.com', '2015-12-23 19:48:10', '0', '703407');
INSERT INTO `fish_user_change_email` VALUES ('27', '475061976@qq.com', '2016-01-19 17:52:11', '1', '644841');

-- ----------------------------
-- Table structure for fish_user_collect
-- ----------------------------
DROP TABLE IF EXISTS `fish_user_collect`;
CREATE TABLE `fish_user_collect` (
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `tid` mediumint(8) unsigned NOT NULL COMMENT '文章id',
  `dateline` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`uid`,`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯收藏';

-- ----------------------------
-- Records of fish_user_collect
-- ----------------------------
INSERT INTO `fish_user_collect` VALUES ('1', '1', '2015-12-05 21:08:24');
INSERT INTO `fish_user_collect` VALUES ('1', '2', '2015-12-19 10:59:28');
INSERT INTO `fish_user_collect` VALUES ('3', '4', '2016-01-11 17:28:24');

-- ----------------------------
-- Table structure for fish_user_count
-- ----------------------------
DROP TABLE IF EXISTS `fish_user_count`;
CREATE TABLE `fish_user_count` (
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `follow` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '我关注的人数量',
  `fans` mediumint(8) DEFAULT '0' COMMENT '粉丝人数',
  `topic` mediumint(8) NOT NULL DEFAULT '0' COMMENT '发表文章数',
  `collect` mediumint(8) NOT NULL DEFAULT '0' COMMENT '收藏的文章数',
  `remainfubi` mediumint(8) NOT NULL DEFAULT '0' COMMENT '剩余富币数',
  `totalfubi` mediumint(8) NOT NULL DEFAULT '0' COMMENT '总富币数量，积分',
  `order` mediumint(8) DEFAULT '0' COMMENT '我的订单数量',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fish_user_count
-- ----------------------------
INSERT INTO `fish_user_count` VALUES ('1', '12', '22', '12', '22', '22222', '323232', '1');
INSERT INTO `fish_user_count` VALUES ('3', '3', '2', '0', '0', '0', '0', null);
INSERT INTO `fish_user_count` VALUES ('11', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('12', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('13', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('14', '1', '1', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('17', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('18', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('20', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('21', '0', '0', '0', '0', '999999', '999999', '0');
INSERT INTO `fish_user_count` VALUES ('26', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `fish_user_count` VALUES ('27', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for fish_user_emailcode
-- ----------------------------
DROP TABLE IF EXISTS `fish_user_emailcode`;
CREATE TABLE `fish_user_emailcode` (
  `email` varchar(128) NOT NULL DEFAULT '',
  `code` varchar(8) NOT NULL DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `secretCode` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fish_user_emailcode
-- ----------------------------

-- ----------------------------
-- Table structure for fish_user_follow
-- ----------------------------
DROP TABLE IF EXISTS `fish_user_follow`;
CREATE TABLE `fish_user_follow` (
  `uid` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  `fuid` mediumint(8) unsigned NOT NULL COMMENT '被关注人id',
  `dateline` datetime DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`uid`,`fuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注表';

-- ----------------------------
-- Records of fish_user_follow
-- ----------------------------
INSERT INTO `fish_user_follow` VALUES ('1', '2', '2015-12-19 11:50:24');
INSERT INTO `fish_user_follow` VALUES ('1', '3', '2015-12-16 11:50:26');
INSERT INTO `fish_user_follow` VALUES ('2', '1', '2015-12-19 11:50:30');
INSERT INTO `fish_user_follow` VALUES ('3', '1', '2016-01-12 13:39:24');
INSERT INTO `fish_user_follow` VALUES ('3', '3', '2016-01-12 10:47:23');
INSERT INTO `fish_user_follow` VALUES ('3', '14', '2016-01-12 13:41:49');
INSERT INTO `fish_user_follow` VALUES ('14', '3', '2016-01-12 11:08:46');

-- ----------------------------
-- Table structure for sec_authority
-- ----------------------------
DROP TABLE IF EXISTS `sec_authority`;
CREATE TABLE `sec_authority` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=utf8 COMMENT='授权';

-- ----------------------------
-- Records of sec_authority
-- ----------------------------
INSERT INTO `sec_authority` VALUES ('401', '用户查询', 'USERLIST');
INSERT INTO `sec_authority` VALUES ('402', '部门查询', 'ORGLIST');
INSERT INTO `sec_authority` VALUES ('403', '角色查询', 'ROLELIST');
INSERT INTO `sec_authority` VALUES ('404', '权限查询', 'AUTHORITYLIST');
INSERT INTO `sec_authority` VALUES ('405', '资源查询', 'RESOURCELIST');
INSERT INTO `sec_authority` VALUES ('406', '菜单查询', 'MENULIST');
INSERT INTO `sec_authority` VALUES ('411', '用户查看', 'USERVIEW');
INSERT INTO `sec_authority` VALUES ('412', '部门查看', 'ORGVIEW');
INSERT INTO `sec_authority` VALUES ('413', '角色查看', 'ROLEVIEW');
INSERT INTO `sec_authority` VALUES ('414', '权限查看', 'AUTHORITYVIEW');
INSERT INTO `sec_authority` VALUES ('415', '资源查看', 'RESOURCEVIEW');
INSERT INTO `sec_authority` VALUES ('416', '菜单查看', 'MENUVIEW');
INSERT INTO `sec_authority` VALUES ('421', '用户删除', 'USERDELETE');
INSERT INTO `sec_authority` VALUES ('422', '部门删除', 'ORGDELETE');
INSERT INTO `sec_authority` VALUES ('423', '角色删除', 'ROLEDELETE');
INSERT INTO `sec_authority` VALUES ('424', '权限删除', 'AUTHORITYDELETE');
INSERT INTO `sec_authority` VALUES ('425', '资源删除', 'RESOURCEDELETE');
INSERT INTO `sec_authority` VALUES ('426', '菜单删除', 'MENUDELETE');
INSERT INTO `sec_authority` VALUES ('431', '用户编辑', 'USEREDIT');
INSERT INTO `sec_authority` VALUES ('432', '部门编辑', 'ORGEDIT');
INSERT INTO `sec_authority` VALUES ('433', '角色编辑', 'ROLEEDIT');
INSERT INTO `sec_authority` VALUES ('434', '权限编辑', 'AUTHORITYEDIT');
INSERT INTO `sec_authority` VALUES ('435', '资源编辑', 'RESOURCEEDIT');
INSERT INTO `sec_authority` VALUES ('436', '菜单编辑', 'MENUEDIT');
INSERT INTO `sec_authority` VALUES ('444', '新闻分类', 'NewsCategory');
INSERT INTO `sec_authority` VALUES ('448', '新闻查看', 'NewsView');
INSERT INTO `sec_authority` VALUES ('451', '新闻手机', 'Mnews');
INSERT INTO `sec_authority` VALUES ('463', '新闻编辑', 'NewsReview');
INSERT INTO `sec_authority` VALUES ('467', '学校管理员', 'schoolManager');
INSERT INTO `sec_authority` VALUES ('469', '学院管理员', 'academyManager');
INSERT INTO `sec_authority` VALUES ('470', '学生申请', 'student');
INSERT INTO `sec_authority` VALUES ('471', '比赛类型', 'gametype');
INSERT INTO `sec_authority` VALUES ('472', '两级联动所用', 'gameTypeLevel2');
INSERT INTO `sec_authority` VALUES ('473', '获取二级部门', 'getOrgLevel2');
INSERT INTO `sec_authority` VALUES ('474', 'announcement', 'announcement');
INSERT INTO `sec_authority` VALUES ('475', '管理用户提交的举报内容', 'NewsReportM');
INSERT INTO `sec_authority` VALUES ('476', '新闻审核', 'NewsCheck');
INSERT INTO `sec_authority` VALUES ('477', '渔获管理', 'HarvestM');
INSERT INTO `sec_authority` VALUES ('478', '对限时特惠时间设置', 'LimitTimeM');
INSERT INTO `sec_authority` VALUES ('479', '限时特惠商品管理', 'LimitGoodsM');
INSERT INTO `sec_authority` VALUES ('480', '整点秒杀时间管理', 'SeckillTimeM');
INSERT INTO `sec_authority` VALUES ('481', '整点秒杀商品管理', 'SeckillGoodsM');
INSERT INTO `sec_authority` VALUES ('482', '富币获取方式', 'FubitypeM');
INSERT INTO `sec_authority` VALUES ('483', '富币商城广告Banner', 'BannerM');
INSERT INTO `sec_authority` VALUES ('484', '富币订单管理', 'FubiOrder');
INSERT INTO `sec_authority` VALUES ('485', '商品类别', '商品类别');
INSERT INTO `sec_authority` VALUES ('486', '商品属性管理', '商品属性管理');
INSERT INTO `sec_authority` VALUES ('487', '商品管理', '商品管理');
INSERT INTO `sec_authority` VALUES ('488', '商品审核', '商品审核');
INSERT INTO `sec_authority` VALUES ('489', '订单管理', '订单管理');
INSERT INTO `sec_authority` VALUES ('490', '用户添加新闻', '添加新闻');
INSERT INTO `sec_authority` VALUES ('491', '团购管理', '团购管理');

-- ----------------------------
-- Table structure for sec_authority_resource
-- ----------------------------
DROP TABLE IF EXISTS `sec_authority_resource`;
CREATE TABLE `sec_authority_resource` (
  `authority_id` mediumint(8) unsigned NOT NULL,
  `resource_id` mediumint(8) unsigned NOT NULL,
  KEY `FK_AUTHORITY_RESOURCE1` (`authority_id`),
  KEY `FK_AUTHORITY_RESOURCE2` (`resource_id`),
  CONSTRAINT `sec_authority_resource_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `sec_authority` (`id`),
  CONSTRAINT `sec_authority_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `sec_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限对应的资源';

-- ----------------------------
-- Records of sec_authority_resource
-- ----------------------------
INSERT INTO `sec_authority_resource` VALUES ('401', '401');
INSERT INTO `sec_authority_resource` VALUES ('402', '402');
INSERT INTO `sec_authority_resource` VALUES ('403', '403');
INSERT INTO `sec_authority_resource` VALUES ('404', '404');
INSERT INTO `sec_authority_resource` VALUES ('405', '405');
INSERT INTO `sec_authority_resource` VALUES ('406', '406');
INSERT INTO `sec_authority_resource` VALUES ('411', '411');
INSERT INTO `sec_authority_resource` VALUES ('412', '412');
INSERT INTO `sec_authority_resource` VALUES ('413', '413');
INSERT INTO `sec_authority_resource` VALUES ('414', '414');
INSERT INTO `sec_authority_resource` VALUES ('415', '415');
INSERT INTO `sec_authority_resource` VALUES ('416', '416');
INSERT INTO `sec_authority_resource` VALUES ('421', '421');
INSERT INTO `sec_authority_resource` VALUES ('422', '422');
INSERT INTO `sec_authority_resource` VALUES ('423', '423');
INSERT INTO `sec_authority_resource` VALUES ('424', '424');
INSERT INTO `sec_authority_resource` VALUES ('425', '425');
INSERT INTO `sec_authority_resource` VALUES ('426', '426');
INSERT INTO `sec_authority_resource` VALUES ('431', '431');
INSERT INTO `sec_authority_resource` VALUES ('432', '432');
INSERT INTO `sec_authority_resource` VALUES ('433', '433');
INSERT INTO `sec_authority_resource` VALUES ('434', '434');
INSERT INTO `sec_authority_resource` VALUES ('435', '435');
INSERT INTO `sec_authority_resource` VALUES ('436', '436');
INSERT INTO `sec_authority_resource` VALUES ('451', '452');
INSERT INTO `sec_authority_resource` VALUES ('401', '401');
INSERT INTO `sec_authority_resource` VALUES ('402', '402');
INSERT INTO `sec_authority_resource` VALUES ('403', '403');
INSERT INTO `sec_authority_resource` VALUES ('404', '404');
INSERT INTO `sec_authority_resource` VALUES ('405', '405');
INSERT INTO `sec_authority_resource` VALUES ('406', '406');
INSERT INTO `sec_authority_resource` VALUES ('411', '411');
INSERT INTO `sec_authority_resource` VALUES ('412', '412');
INSERT INTO `sec_authority_resource` VALUES ('413', '413');
INSERT INTO `sec_authority_resource` VALUES ('414', '414');
INSERT INTO `sec_authority_resource` VALUES ('415', '415');
INSERT INTO `sec_authority_resource` VALUES ('416', '416');
INSERT INTO `sec_authority_resource` VALUES ('421', '421');
INSERT INTO `sec_authority_resource` VALUES ('422', '422');
INSERT INTO `sec_authority_resource` VALUES ('423', '423');
INSERT INTO `sec_authority_resource` VALUES ('424', '424');
INSERT INTO `sec_authority_resource` VALUES ('425', '425');
INSERT INTO `sec_authority_resource` VALUES ('426', '426');
INSERT INTO `sec_authority_resource` VALUES ('431', '431');
INSERT INTO `sec_authority_resource` VALUES ('432', '432');
INSERT INTO `sec_authority_resource` VALUES ('433', '433');
INSERT INTO `sec_authority_resource` VALUES ('434', '434');
INSERT INTO `sec_authority_resource` VALUES ('435', '435');
INSERT INTO `sec_authority_resource` VALUES ('436', '436');
INSERT INTO `sec_authority_resource` VALUES ('470', '495');
INSERT INTO `sec_authority_resource` VALUES ('471', '483');
INSERT INTO `sec_authority_resource` VALUES ('471', '499');
INSERT INTO `sec_authority_resource` VALUES ('472', '500');
INSERT INTO `sec_authority_resource` VALUES ('469', '485');
INSERT INTO `sec_authority_resource` VALUES ('469', '501');
INSERT INTO `sec_authority_resource` VALUES ('469', '502');
INSERT INTO `sec_authority_resource` VALUES ('473', '505');
INSERT INTO `sec_authority_resource` VALUES ('467', '447');
INSERT INTO `sec_authority_resource` VALUES ('467', '479');
INSERT INTO `sec_authority_resource` VALUES ('467', '480');
INSERT INTO `sec_authority_resource` VALUES ('467', '481');
INSERT INTO `sec_authority_resource` VALUES ('467', '482');
INSERT INTO `sec_authority_resource` VALUES ('467', '493');
INSERT INTO `sec_authority_resource` VALUES ('467', '494');
INSERT INTO `sec_authority_resource` VALUES ('467', '496');
INSERT INTO `sec_authority_resource` VALUES ('467', '503');
INSERT INTO `sec_authority_resource` VALUES ('467', '504');
INSERT INTO `sec_authority_resource` VALUES ('467', '506');
INSERT INTO `sec_authority_resource` VALUES ('474', '507');
INSERT INTO `sec_authority_resource` VALUES ('444', '445');
INSERT INTO `sec_authority_resource` VALUES ('444', '508');
INSERT INTO `sec_authority_resource` VALUES ('475', '509');
INSERT INTO `sec_authority_resource` VALUES ('475', '510');
INSERT INTO `sec_authority_resource` VALUES ('463', '447');
INSERT INTO `sec_authority_resource` VALUES ('463', '511');
INSERT INTO `sec_authority_resource` VALUES ('476', '512');
INSERT INTO `sec_authority_resource` VALUES ('476', '513');
INSERT INTO `sec_authority_resource` VALUES ('476', '514');
INSERT INTO `sec_authority_resource` VALUES ('477', '515');
INSERT INTO `sec_authority_resource` VALUES ('477', '516');
INSERT INTO `sec_authority_resource` VALUES ('478', '517');
INSERT INTO `sec_authority_resource` VALUES ('478', '518');
INSERT INTO `sec_authority_resource` VALUES ('479', '519');
INSERT INTO `sec_authority_resource` VALUES ('479', '520');
INSERT INTO `sec_authority_resource` VALUES ('480', '521');
INSERT INTO `sec_authority_resource` VALUES ('480', '522');
INSERT INTO `sec_authority_resource` VALUES ('481', '523');
INSERT INTO `sec_authority_resource` VALUES ('481', '524');
INSERT INTO `sec_authority_resource` VALUES ('482', '525');
INSERT INTO `sec_authority_resource` VALUES ('482', '526');
INSERT INTO `sec_authority_resource` VALUES ('483', '527');
INSERT INTO `sec_authority_resource` VALUES ('483', '528');
INSERT INTO `sec_authority_resource` VALUES ('484', '529');
INSERT INTO `sec_authority_resource` VALUES ('484', '530');
INSERT INTO `sec_authority_resource` VALUES ('485', '531');
INSERT INTO `sec_authority_resource` VALUES ('486', '532');
INSERT INTO `sec_authority_resource` VALUES ('486', '533');
INSERT INTO `sec_authority_resource` VALUES ('487', '534');
INSERT INTO `sec_authority_resource` VALUES ('488', '535');
INSERT INTO `sec_authority_resource` VALUES ('488', '536');
INSERT INTO `sec_authority_resource` VALUES ('489', '537');
INSERT INTO `sec_authority_resource` VALUES ('448', '447');
INSERT INTO `sec_authority_resource` VALUES ('448', '511');
INSERT INTO `sec_authority_resource` VALUES ('490', '538');
INSERT INTO `sec_authority_resource` VALUES ('490', '540');
INSERT INTO `sec_authority_resource` VALUES ('490', '541');
INSERT INTO `sec_authority_resource` VALUES ('490', '542');
INSERT INTO `sec_authority_resource` VALUES ('490', '543');
INSERT INTO `sec_authority_resource` VALUES ('490', '447');
INSERT INTO `sec_authority_resource` VALUES ('490', '511');
INSERT INTO `sec_authority_resource` VALUES ('491', '544');

-- ----------------------------
-- Table structure for sec_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu`;
CREATE TABLE `sec_menu` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `parent_menu` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `iconcss` varchar(64) NOT NULL DEFAULT '' COMMENT '图标的样式',
  `ref` varchar(32) NOT NULL DEFAULT '' COMMENT '菜单的DWZ tabID',
  `orderby` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='菜单资源';

-- ----------------------------
-- Records of sec_menu
-- ----------------------------
INSERT INTO `sec_menu` VALUES ('4', '', '系统管理', '0', '', '', '1');
INSERT INTO `sec_menu` VALUES ('41', '登录界面、管理员查看用户信息', '用户管理', '4', '', 'userList', '10');
INSERT INTO `sec_menu` VALUES ('42', '部门管理', '部门管理', '4', '', 'orgList', '11');
INSERT INTO `sec_menu` VALUES ('43', '用户的角色管理。', '角色管理', '4', '', 'roleList', '12');
INSERT INTO `sec_menu` VALUES ('44', '权限管理菜单，只有管理员才具备', '权限管理', '4', '', 'authorityList', '13');
INSERT INTO `sec_menu` VALUES ('45', '管理员：具备的资源管理菜单', '资源管理', '4', '', 'resourceList', '14');
INSERT INTO `sec_menu` VALUES ('46', '菜单列表', '菜单管理', '4', '', 'menuList', '15');
INSERT INTO `sec_menu` VALUES ('57', '新闻的分类', '新闻分类', '61', '', 'newsCategoryList', '71');
INSERT INTO `sec_menu` VALUES ('58', '查看新闻', '查看新闻', '61', '', 'newsList', '71');
INSERT INTO `sec_menu` VALUES ('60', '', '统计信息', '0', '', '', '3');
INSERT INTO `sec_menu` VALUES ('61', '', '新闻管理', '0', '', '', '4');
INSERT INTO `sec_menu` VALUES ('62', '获奖级别', '等级管理', '56', '', 'rankList', '20');
INSERT INTO `sec_menu` VALUES ('63', '比赛级别，国家级，省级', '级别管理', '56', '', 'levellist', '21');
INSERT INTO `sec_menu` VALUES ('64', '国家一等奖', '分数设置', '56', '', 'levelrank', '23');
INSERT INTO `sec_menu` VALUES ('65', '用于学院管理员添加比赛', '比赛类型', '56', '', 'gamelist', '24');
INSERT INTO `sec_menu` VALUES ('67', '学院赛前申请', '竞赛申请', '56', '', '', '26');
INSERT INTO `sec_menu` VALUES ('70', '学院提交的赛前审核列表, 等待审核', '待审核', '72', '', 'prewaitlist', '50');
INSERT INTO `sec_menu` VALUES ('71', '学院提交的赛前申请，已审核通过的', '审核通过', '72', '', 'prepasslist', '51');
INSERT INTO `sec_menu` VALUES ('73', '获奖学生录入信息', '我的成果', '56', '', 'recordwinList', '60');
INSERT INTO `sec_menu` VALUES ('75', '等待学院审核', '待学院审核', '69', '', 'waitAcademyList', '61');
INSERT INTO `sec_menu` VALUES ('76', '学院审核通过', '学院审核通过', '69', '', 'passAcademyList', '62');
INSERT INTO `sec_menu` VALUES ('77', '', '待学校审核', '74', '', 'waitSchoolList', '71');
INSERT INTO `sec_menu` VALUES ('78', '学校审核通过', '学校审核通过', '74', '', 'passSchoolList', '72');
INSERT INTO `sec_menu` VALUES ('79', '学校管理员查看各个学院审核情况', '待学院审核', '74', '', 'schoolfindWaitAcademyList', '74');
INSERT INTO `sec_menu` VALUES ('80', '', '公告', '61', '', 'announcementList', '1');
INSERT INTO `sec_menu` VALUES ('81', '查看用户提交的举报内容', '新闻举报', '61', '', 'newsrReport', '73');
INSERT INTO `sec_menu` VALUES ('82', '新闻的审核', '新闻审核', '0', '', 'newscheck', '8');
INSERT INTO `sec_menu` VALUES ('83', '待审核的新闻列表', '待审核新闻', '82', '', 'newswaitingcheck', '81');
INSERT INTO `sec_menu` VALUES ('84', '已审核新闻', '已审核新闻', '82', '', 'alreadycheck', '82');
INSERT INTO `sec_menu` VALUES ('85', '渔获模块', '渔获模块', '0', '', 'harvest', '9');
INSERT INTO `sec_menu` VALUES ('86', '对用户发布的渔获进行管理', '渔获管理', '85', '', 'harvestList', '91');
INSERT INTO `sec_menu` VALUES ('87', '富币商城模块', '富币商城', '0', '', '', '5');
INSERT INTO `sec_menu` VALUES ('88', '限时特惠时间设置', '限时特惠时间', '87', '', 'limitTime', '51');
INSERT INTO `sec_menu` VALUES ('89', '限时商品的添加修改等', '限时特惠商品', '87', '', 'limitgoods', '52');
INSERT INTO `sec_menu` VALUES ('90', '整点秒杀时间设置', '整点秒杀时间', '87', '', 'seckill', '53');
INSERT INTO `sec_menu` VALUES ('91', '整点秒杀商品管理', '整点秒杀商品', '87', '', 'seckillgoods', '54');
INSERT INTO `sec_menu` VALUES ('92', '对获取富币类型设置', '富币管理', '87', '', 'fubitype', '55');
INSERT INTO `sec_menu` VALUES ('93', '对富币商城中的Banner广告条进行设置', '富币Banner', '87', '', 'fubibanner', '56');
INSERT INTO `sec_menu` VALUES ('94', '', '富币商品订单', '87', '', 'fubiorder', '57');
INSERT INTO `sec_menu` VALUES ('95', '', '商城管理', '0', '', '', '2');
INSERT INTO `sec_menu` VALUES ('96', '', '商品类别', '95', '', 'categoryList', '21');
INSERT INTO `sec_menu` VALUES ('97', '', '关键属性', '95', '', 'keytAttrList', '22');
INSERT INTO `sec_menu` VALUES ('98', '', '非关键属性', '95', '', 'nonkeytAttrList', '23');
INSERT INTO `sec_menu` VALUES ('99', '', '商品管理', '95', '', 'goodsList', '24');
INSERT INTO `sec_menu` VALUES ('100', '', '未审核商品', '95', '', 'waitingCheckGoodsList', '25');
INSERT INTO `sec_menu` VALUES ('101', '', '已审核商品', '95', '', 'alreadyCheckedGoodsList', '26');
INSERT INTO `sec_menu` VALUES ('102', '', '订单管理', '95', '', 'orderList', '27');
INSERT INTO `sec_menu` VALUES ('103', '', '团购模块', '0', '', '', '6');
INSERT INTO `sec_menu` VALUES ('104', '', '团购商品', '103', '', 'teamBuyList', '61');

-- ----------------------------
-- Table structure for sec_org
-- ----------------------------
DROP TABLE IF EXISTS `sec_org`;
CREATE TABLE `sec_org` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `description` varchar(500) NOT NULL DEFAULT '',
  `parent_org` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `displayorder` smallint(6) unsigned NOT NULL DEFAULT '0',
  `level` tinyint(3) unsigned DEFAULT '0' COMMENT '0:全部，1：学院，2：班级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1262 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sec_org
-- ----------------------------
INSERT INTO `sec_org` VALUES ('1', '全部', '全部', '0', '1', '0');
INSERT INTO `sec_org` VALUES ('2', '系统管理员', '系统管理员', '0', '1', '1');
INSERT INTO `sec_org` VALUES ('4', '其他', '其他部门', '0', '2', '1');
INSERT INTO `sec_org` VALUES ('6', '信息工程学院', '', '0', '3', '1');
INSERT INTO `sec_org` VALUES ('7', '计算机科学与技术学院', '', '0', '3', '1');
INSERT INTO `sec_org` VALUES ('13', '学校管理员', '', '0', '3', '1');
INSERT INTO `sec_org` VALUES ('14', '校团委', '', '13', '11', '2');
INSERT INTO `sec_org` VALUES ('15', '制造科学与工程学院', '', '0', '12', '1');
INSERT INTO `sec_org` VALUES ('16', '土木工程与建筑学院', '', '0', '13', '1');
INSERT INTO `sec_org` VALUES ('17', '环境与资源学院', '', '0', '14', '1');
INSERT INTO `sec_org` VALUES ('18', '材料科学与工程学院', '', '0', '15', '1');
INSERT INTO `sec_org` VALUES ('19', '生命科学与工程学院', '', '0', '16', '1');
INSERT INTO `sec_org` VALUES ('20', '理学院', '', '0', '17', '1');
INSERT INTO `sec_org` VALUES ('21', '经济管理学院', '', '0', '18', '1');
INSERT INTO `sec_org` VALUES ('22', '文学与艺术学院', '', '0', '19', '1');
INSERT INTO `sec_org` VALUES ('23', '法学院', '', '0', '20', '1');
INSERT INTO `sec_org` VALUES ('24', '马克思主义学院', '', '0', '21', '1');
INSERT INTO `sec_org` VALUES ('25', '外国语学院', '', '0', '22', '1');
INSERT INTO `sec_org` VALUES ('26', '体育学科部', '', '0', '23', '1');
INSERT INTO `sec_org` VALUES ('27', '成人教育学院、网络教育学院', '', '0', '24', '1');
INSERT INTO `sec_org` VALUES ('28', '应用技术学院', '', '0', '25', '1');
INSERT INTO `sec_org` VALUES ('29', '国防科技学院', '', '0', '26', '1');
INSERT INTO `sec_org` VALUES ('30', '拉美研究院', '', '0', '27', '1');
INSERT INTO `sec_org` VALUES ('31', '党委学工部', '', '0', '22', '1');
INSERT INTO `sec_org` VALUES ('32', '科技园', '', '0', '23', '1');
INSERT INTO `sec_org` VALUES ('33', '工程技术中心', '', '0', '30', '1');
INSERT INTO `sec_org` VALUES ('35', '计科学院团委', '', '7', '28', '2');
INSERT INTO `sec_org` VALUES ('38', '经管学院团委', '', '21', '29', '2');
INSERT INTO `sec_org` VALUES ('40', '信息学院团委', '', '6', '28', '2');
INSERT INTO `sec_org` VALUES ('41', '制造学院团委', '', '15', '29', '2');
INSERT INTO `sec_org` VALUES ('42', '土建学院团委', '', '16', '30', '2');
INSERT INTO `sec_org` VALUES ('43', '环资学院团委', '', '17', '31', '2');
INSERT INTO `sec_org` VALUES ('44', '材料学院团委', '', '18', '32', '2');
INSERT INTO `sec_org` VALUES ('45', '生命学院团委', '', '19', '33', '2');
INSERT INTO `sec_org` VALUES ('46', '理学院团委', '', '20', '34', '2');
INSERT INTO `sec_org` VALUES ('47', '文艺学院团委', '', '22', '35', '2');
INSERT INTO `sec_org` VALUES ('48', '法学院团委', '', '23', '36', '2');
INSERT INTO `sec_org` VALUES ('49', '马克思主义学院团委', '', '24', '37', '2');
INSERT INTO `sec_org` VALUES ('50', '外语学院团委', '', '25', '38', '2');
INSERT INTO `sec_org` VALUES ('51', '体育学科部团委', '', '26', '39', '2');
INSERT INTO `sec_org` VALUES ('52', '计算机1201', '', '7', '40', '2');
INSERT INTO `sec_org` VALUES ('53', '计算机1202', '', '7', '41', '2');
INSERT INTO `sec_org` VALUES ('54', '计算机1203', '', '7', '42', '2');
INSERT INTO `sec_org` VALUES ('55', '计算机1204', '', '7', '43', '2');
INSERT INTO `sec_org` VALUES ('56', '计算机1301', '', '7', '44', '2');
INSERT INTO `sec_org` VALUES ('57', '计算机1302', '', '7', '45', '2');
INSERT INTO `sec_org` VALUES ('58', '计算机1303', '', '7', '46', '2');
INSERT INTO `sec_org` VALUES ('59', '计算机1304', '', '7', '47', '2');
INSERT INTO `sec_org` VALUES ('60', '计算机1401', '', '7', '48', '2');
INSERT INTO `sec_org` VALUES ('61', '计算机1402', '', '7', '49', '2');
INSERT INTO `sec_org` VALUES ('62', '计算机1403', '', '7', '50', '2');
INSERT INTO `sec_org` VALUES ('63', '计算机1404', '', '7', '51', '2');
INSERT INTO `sec_org` VALUES ('64', '计算机1501', '', '7', '52', '2');
INSERT INTO `sec_org` VALUES ('65', '计算机1502', '', '7', '53', '2');
INSERT INTO `sec_org` VALUES ('66', '计算机1503', '', '7', '54', '2');
INSERT INTO `sec_org` VALUES ('67', '计算机1504', '', '7', '55', '2');
INSERT INTO `sec_org` VALUES ('68', '计算机1505', '', '7', '56', '2');
INSERT INTO `sec_org` VALUES ('69', '计科卓越1301', '', '7', '57', '2');
INSERT INTO `sec_org` VALUES ('70', '计科卓越1401', '', '7', '58', '2');
INSERT INTO `sec_org` VALUES ('71', '软件1201', '', '7', '59', '2');
INSERT INTO `sec_org` VALUES ('72', '软件1202', '', '7', '60', '2');
INSERT INTO `sec_org` VALUES ('73', '软件1203', '', '7', '61', '2');
INSERT INTO `sec_org` VALUES ('74', '软件1301', '', '7', '62', '2');
INSERT INTO `sec_org` VALUES ('75', '软件1302', '', '7', '63', '2');
INSERT INTO `sec_org` VALUES ('76', '软件1401', '', '7', '64', '2');
INSERT INTO `sec_org` VALUES ('77', '软件1402', '', '7', '65', '2');
INSERT INTO `sec_org` VALUES ('78', '软件1403', '', '7', '66', '2');
INSERT INTO `sec_org` VALUES ('79', '软件1501', '', '7', '67', '2');
INSERT INTO `sec_org` VALUES ('80', '软件1502', '', '7', '68', '2');
INSERT INTO `sec_org` VALUES ('81', '软件1503', '', '7', '69', '2');
INSERT INTO `sec_org` VALUES ('82', '软件1504', '', '7', '70', '2');
INSERT INTO `sec_org` VALUES ('83', '软件1505', '', '7', '71', '2');
INSERT INTO `sec_org` VALUES ('84', '软件卓越1301', '', '7', '72', '2');
INSERT INTO `sec_org` VALUES ('85', '软件卓越1201', '', '7', '73', '2');
INSERT INTO `sec_org` VALUES ('86', '软件卓越1401', '', '7', '74', '2');
INSERT INTO `sec_org` VALUES ('87', '信安1201', '', '7', '75', '2');
INSERT INTO `sec_org` VALUES ('88', '信安1202', '', '7', '76', '2');
INSERT INTO `sec_org` VALUES ('89', '信安1203', '', '7', '77', '2');
INSERT INTO `sec_org` VALUES ('90', '信安1204', '', '7', '78', '2');
INSERT INTO `sec_org` VALUES ('91', '信安1205', '', '7', '79', '2');
INSERT INTO `sec_org` VALUES ('92', '信安1301', '', '7', '80', '2');
INSERT INTO `sec_org` VALUES ('93', '信安1302', '', '7', '81', '2');
INSERT INTO `sec_org` VALUES ('94', '信安1303', '', '7', '82', '2');
INSERT INTO `sec_org` VALUES ('95', '信安1304', '', '7', '83', '2');
INSERT INTO `sec_org` VALUES ('96', '信安1305', '', '7', '84', '2');
INSERT INTO `sec_org` VALUES ('97', '信安1401', '', '7', '85', '2');
INSERT INTO `sec_org` VALUES ('98', '信安1402', '', '7', '86', '2');
INSERT INTO `sec_org` VALUES ('99', '信安1403', '', '7', '87', '2');
INSERT INTO `sec_org` VALUES ('100', '信安1404', '', '7', '88', '2');
INSERT INTO `sec_org` VALUES ('101', '信安1405', '', '7', '89', '2');
INSERT INTO `sec_org` VALUES ('102', '信安1501', '', '7', '90', '2');
INSERT INTO `sec_org` VALUES ('103', '信安1502', '', '7', '91', '2');
INSERT INTO `sec_org` VALUES ('104', '信安1503', '', '7', '92', '2');
INSERT INTO `sec_org` VALUES ('105', '信安1504', '', '7', '93', '2');
INSERT INTO `sec_org` VALUES ('106', '信安1505', '', '7', '94', '2');
INSERT INTO `sec_org` VALUES ('107', '影像1301', '', '7', '95', '2');
INSERT INTO `sec_org` VALUES ('108', '影像1302', '', '7', '96', '2');
INSERT INTO `sec_org` VALUES ('109', '影像1401', '', '7', '97', '2');
INSERT INTO `sec_org` VALUES ('110', '影像1402', '', '7', '98', '2');
INSERT INTO `sec_org` VALUES ('111', '影像1501', '', '7', '99', '2');
INSERT INTO `sec_org` VALUES ('112', '影像1502', '', '7', '100', '2');
INSERT INTO `sec_org` VALUES ('113', '计研2013级', '', '7', '101', '2');
INSERT INTO `sec_org` VALUES ('114', '计研2014级', '', '7', '102', '2');
INSERT INTO `sec_org` VALUES ('115', '计研2015级', '', '7', '103', '2');
INSERT INTO `sec_org` VALUES ('116', '社体1501', '', '26', '40', '2');
INSERT INTO `sec_org` VALUES ('117', '社体1502', '', '26', '41', '2');
INSERT INTO `sec_org` VALUES ('118', '社体1503', '', '26', '42', '2');
INSERT INTO `sec_org` VALUES ('119', '社体1504', '', '26', '43', '2');
INSERT INTO `sec_org` VALUES ('120', '社体1401', '', '26', '44', '2');
INSERT INTO `sec_org` VALUES ('121', '社体1402', '', '26', '45', '2');
INSERT INTO `sec_org` VALUES ('122', '社体1403', '', '26', '46', '2');
INSERT INTO `sec_org` VALUES ('123', '社体1301', '', '26', '47', '2');
INSERT INTO `sec_org` VALUES ('124', '社体1302', '', '26', '48', '2');
INSERT INTO `sec_org` VALUES ('125', '社体1201', '', '26', '49', '2');
INSERT INTO `sec_org` VALUES ('126', '社体1202', '', '26', '50', '2');
INSERT INTO `sec_org` VALUES ('127', '教育信息化推进办公室', '信推办', '0', '3', '1');
INSERT INTO `sec_org` VALUES ('128', '数学1501', '', '20', '116', '2');
INSERT INTO `sec_org` VALUES ('129', '数学1502', '', '20', '117', '2');
INSERT INTO `sec_org` VALUES ('130', '计算1501', '', '20', '118', '2');
INSERT INTO `sec_org` VALUES ('131', '计算1502', '', '20', '119', '2');
INSERT INTO `sec_org` VALUES ('132', '物理1501', '', '20', '120', '2');
INSERT INTO `sec_org` VALUES ('133', '物理1502', '', '20', '121', '2');
INSERT INTO `sec_org` VALUES ('134', '物理1503', '', '20', '122', '2');
INSERT INTO `sec_org` VALUES ('135', '光信1501', '', '20', '123', '2');
INSERT INTO `sec_org` VALUES ('136', '光信1502', '', '20', '124', '2');
INSERT INTO `sec_org` VALUES ('137', '光信1503', '', '20', '125', '2');
INSERT INTO `sec_org` VALUES ('138', '光信1504', '', '20', '126', '2');
INSERT INTO `sec_org` VALUES ('139', '数学1401', '', '20', '127', '2');
INSERT INTO `sec_org` VALUES ('140', '数学1402', '', '20', '128', '2');
INSERT INTO `sec_org` VALUES ('141', '数学1301', '', '20', '129', '2');
INSERT INTO `sec_org` VALUES ('142', '数学1302', '', '20', '130', '2');
INSERT INTO `sec_org` VALUES ('143', '数学1201', '', '20', '131', '2');
INSERT INTO `sec_org` VALUES ('144', '数学1202', '', '20', '132', '2');
INSERT INTO `sec_org` VALUES ('145', '计算1401', '', '20', '133', '2');
INSERT INTO `sec_org` VALUES ('146', '计算1402', '', '20', '134', '2');
INSERT INTO `sec_org` VALUES ('147', '计算1301', '', '20', '135', '2');
INSERT INTO `sec_org` VALUES ('148', '计算1302', '', '20', '136', '2');
INSERT INTO `sec_org` VALUES ('149', '计算1201', '', '20', '137', '2');
INSERT INTO `sec_org` VALUES ('150', '物理1401', '', '20', '138', '2');
INSERT INTO `sec_org` VALUES ('151', '物理1402', '', '20', '139', '2');
INSERT INTO `sec_org` VALUES ('152', '物理1403', '', '20', '140', '2');
INSERT INTO `sec_org` VALUES ('153', '物理1301', '', '20', '141', '2');
INSERT INTO `sec_org` VALUES ('154', '物理1302', '', '20', '142', '2');
INSERT INTO `sec_org` VALUES ('155', '物理1201', '', '20', '143', '2');
INSERT INTO `sec_org` VALUES ('156', '物理1202', '', '20', '144', '2');
INSERT INTO `sec_org` VALUES ('157', '光信1401', '', '20', '145', '2');
INSERT INTO `sec_org` VALUES ('158', '光信1402', '', '20', '146', '2');
INSERT INTO `sec_org` VALUES ('159', '光信1403', '', '20', '147', '2');
INSERT INTO `sec_org` VALUES ('160', '光信1301', '', '20', '148', '2');
INSERT INTO `sec_org` VALUES ('161', '光信1302', '', '20', '149', '2');
INSERT INTO `sec_org` VALUES ('162', '光信1303', '', '20', '150', '2');
INSERT INTO `sec_org` VALUES ('163', '光信1201', '', '20', '151', '2');
INSERT INTO `sec_org` VALUES ('164', '光信1202', '', '20', '152', '2');
INSERT INTO `sec_org` VALUES ('165', '光信1203', '', '20', '153', '2');
INSERT INTO `sec_org` VALUES ('166', '光学研2015', '', '20', '154', '2');
INSERT INTO `sec_org` VALUES ('167', '光学研2014', '', '20', '155', '2');
INSERT INTO `sec_org` VALUES ('168', '光学研2013', '', '20', '156', '2');
INSERT INTO `sec_org` VALUES ('169', '凝聚态物理研2015', '', '20', '157', '2');
INSERT INTO `sec_org` VALUES ('170', '凝聚态物理研2014', '', '20', '158', '2');
INSERT INTO `sec_org` VALUES ('171', '凝聚态物理研2013', '', '20', '159', '2');
INSERT INTO `sec_org` VALUES ('172', '电子1501', '', '6', '51', '2');
INSERT INTO `sec_org` VALUES ('173', '电子1502', '', '6', '52', '2');
INSERT INTO `sec_org` VALUES ('174', '电子1503', '', '6', '53', '2');
INSERT INTO `sec_org` VALUES ('175', '电子1504', '', '6', '54', '2');
INSERT INTO `sec_org` VALUES ('176', '电子1505', '', '6', '55', '2');
INSERT INTO `sec_org` VALUES ('177', '通信1501', '', '6', '56', '2');
INSERT INTO `sec_org` VALUES ('179', '通信1503', '', '6', '58', '2');
INSERT INTO `sec_org` VALUES ('180', '通信1502', '', '6', '57', '2');
INSERT INTO `sec_org` VALUES ('181', '通信1504', '', '6', '59', '2');
INSERT INTO `sec_org` VALUES ('182', '通信1505', '', '6', '60', '2');
INSERT INTO `sec_org` VALUES ('183', '物联1501', '', '6', '61', '2');
INSERT INTO `sec_org` VALUES ('184', '物联1502', '', '6', '62', '2');
INSERT INTO `sec_org` VALUES ('185', '物联1503', '', '6', '63', '2');
INSERT INTO `sec_org` VALUES ('186', '自动化1501', '', '6', '64', '2');
INSERT INTO `sec_org` VALUES ('187', '自动化1502', '', '6', '65', '2');
INSERT INTO `sec_org` VALUES ('188', '自动化1503', '', '6', '66', '2');
INSERT INTO `sec_org` VALUES ('189', '自动化1504', '', '6', '67', '2');
INSERT INTO `sec_org` VALUES ('190', '自动化1505', '', '6', '68', '2');
INSERT INTO `sec_org` VALUES ('191', '电气1501', '', '6', '69', '2');
INSERT INTO `sec_org` VALUES ('192', '电气1502', '', '6', '70', '2');
INSERT INTO `sec_org` VALUES ('193', '电气1503', '', '6', '71', '2');
INSERT INTO `sec_org` VALUES ('194', '电气1504', '', '6', '72', '2');
INSERT INTO `sec_org` VALUES ('195', '电气1505', '', '6', '73', '2');
INSERT INTO `sec_org` VALUES ('196', '生医1501', '', '6', '74', '2');
INSERT INTO `sec_org` VALUES ('197', '生医1502', '', '6', '75', '2');
INSERT INTO `sec_org` VALUES ('198', '生医1503', '', '6', '76', '2');
INSERT INTO `sec_org` VALUES ('199', '通信1401', '', '6', '77', '2');
INSERT INTO `sec_org` VALUES ('201', '通信1402', '', '6', '78', '2');
INSERT INTO `sec_org` VALUES ('202', '通信1403', '', '6', '79', '2');
INSERT INTO `sec_org` VALUES ('203', '通信1404', '', '6', '80', '2');
INSERT INTO `sec_org` VALUES ('204', '物联1401', '', '6', '81', '2');
INSERT INTO `sec_org` VALUES ('205', '物联1402', '', '6', '82', '2');
INSERT INTO `sec_org` VALUES ('206', '物联1403', '', '6', '83', '2');
INSERT INTO `sec_org` VALUES ('207', '自动化1401', '', '6', '84', '2');
INSERT INTO `sec_org` VALUES ('208', '自动化1402', '', '6', '85', '2');
INSERT INTO `sec_org` VALUES ('209', '自动化1403', '', '6', '86', '2');
INSERT INTO `sec_org` VALUES ('210', '自动化1404', '', '6', '87', '2');
INSERT INTO `sec_org` VALUES ('211', '自动化1405', '', '6', '88', '2');
INSERT INTO `sec_org` VALUES ('212', '自动卓越1401', '', '6', '89', '2');
INSERT INTO `sec_org` VALUES ('213', '电气1401', '', '6', '90', '2');
INSERT INTO `sec_org` VALUES ('214', '电子1402', '', '6', '91', '2');
INSERT INTO `sec_org` VALUES ('215', '电子1403', '', '6', '92', '2');
INSERT INTO `sec_org` VALUES ('216', '电子卓越1401', '', '6', '93', '2');
INSERT INTO `sec_org` VALUES ('217', '电气1401', '', '6', '94', '2');
INSERT INTO `sec_org` VALUES ('218', '电气1402', '', '6', '95', '2');
INSERT INTO `sec_org` VALUES ('219', '电气1403', '', '6', '96', '2');
INSERT INTO `sec_org` VALUES ('220', '生医1401', '', '6', '97', '2');
INSERT INTO `sec_org` VALUES ('221', '生医1402', '', '6', '98', '2');
INSERT INTO `sec_org` VALUES ('222', '通信1301', '', '6', '99', '2');
INSERT INTO `sec_org` VALUES ('223', '通信1302', '', '6', '100', '2');
INSERT INTO `sec_org` VALUES ('224', '通信1303', '', '6', '101', '2');
INSERT INTO `sec_org` VALUES ('225', '通信1304', '', '6', '102', '2');
INSERT INTO `sec_org` VALUES ('226', '物联1301', '', '6', '103', '2');
INSERT INTO `sec_org` VALUES ('227', '物联1302', '', '6', '104', '2');
INSERT INTO `sec_org` VALUES ('228', '物联1303', '', '6', '105', '2');
INSERT INTO `sec_org` VALUES ('229', '电气1301', '', '6', '106', '2');
INSERT INTO `sec_org` VALUES ('230', '电气1302', '', '6', '107', '2');
INSERT INTO `sec_org` VALUES ('231', '电气1303', '', '6', '108', '2');
INSERT INTO `sec_org` VALUES ('232', '电气1304', '', '6', '109', '2');
INSERT INTO `sec_org` VALUES ('233', '生医1301', '', '6', '110', '2');
INSERT INTO `sec_org` VALUES ('234', '自动化1301', '', '6', '111', '2');
INSERT INTO `sec_org` VALUES ('235', '自动化1302', '', '6', '112', '2');
INSERT INTO `sec_org` VALUES ('236', '自动化1303', '', '6', '113', '2');
INSERT INTO `sec_org` VALUES ('237', '自动化1304', '', '6', '114', '2');
INSERT INTO `sec_org` VALUES ('238', '自动卓越1301', '', '6', '115', '2');
INSERT INTO `sec_org` VALUES ('239', '自动卓越1302', '', '6', '116', '2');
INSERT INTO `sec_org` VALUES ('240', '电子1301', '', '6', '117', '2');
INSERT INTO `sec_org` VALUES ('241', '电子1302', '', '6', '118', '2');
INSERT INTO `sec_org` VALUES ('242', '电子1303', '', '6', '119', '2');
INSERT INTO `sec_org` VALUES ('243', '通信1201', '', '6', '120', '2');
INSERT INTO `sec_org` VALUES ('244', '通信1202', '', '6', '121', '2');
INSERT INTO `sec_org` VALUES ('245', '通信1203', '', '6', '122', '2');
INSERT INTO `sec_org` VALUES ('246', '通信1204', '', '6', '123', '2');
INSERT INTO `sec_org` VALUES ('247', '物联1201', '', '6', '124', '2');
INSERT INTO `sec_org` VALUES ('248', '物联1202', '', '6', '125', '2');
INSERT INTO `sec_org` VALUES ('249', '电子1201', '', '6', '126', '2');
INSERT INTO `sec_org` VALUES ('250', '电子1202', '', '6', '127', '2');
INSERT INTO `sec_org` VALUES ('251', '电子1203', '', '6', '128', '2');
INSERT INTO `sec_org` VALUES ('252', '自动化1201', '', '6', '129', '2');
INSERT INTO `sec_org` VALUES ('253', '自动化1202', '', '6', '130', '2');
INSERT INTO `sec_org` VALUES ('254', '自动化1203', '', '6', '131', '2');
INSERT INTO `sec_org` VALUES ('255', '自动化1204', '', '6', '132', '2');
INSERT INTO `sec_org` VALUES ('256', '电气1201', '', '6', '133', '2');
INSERT INTO `sec_org` VALUES ('257', '电气1202', '', '6', '134', '2');
INSERT INTO `sec_org` VALUES ('258', '电气1203', '', '6', '135', '2');
INSERT INTO `sec_org` VALUES ('259', '电气1204', '', '6', '136', '2');
INSERT INTO `sec_org` VALUES ('260', '电路与系统研1501', '', '6', '137', '2');
INSERT INTO `sec_org` VALUES ('261', '电子与通信工程研1501', '', '6', '138', '2');
INSERT INTO `sec_org` VALUES ('262', '集成电路工程研1501', '', '6', '139', '2');
INSERT INTO `sec_org` VALUES ('263', '控制工程研1501', '', '6', '140', '2');
INSERT INTO `sec_org` VALUES ('264', '控制科学与工程1501', '', '6', '141', '2');
INSERT INTO `sec_org` VALUES ('265', '信息与通信工程研1501', '', '6', '142', '2');
INSERT INTO `sec_org` VALUES ('266', '电路与系统研1401', '', '6', '143', '2');
INSERT INTO `sec_org` VALUES ('267', '电子与通信工程研1401', '', '6', '144', '2');
INSERT INTO `sec_org` VALUES ('268', '集成电路工程研1402', '', '6', '144', '2');
INSERT INTO `sec_org` VALUES ('269', '控制工程研1401', '', '6', '145', '2');
INSERT INTO `sec_org` VALUES ('270', '控制科学与工程研1401', '', '6', '146', '2');
INSERT INTO `sec_org` VALUES ('271', '通信与信息系统研1401', '', '6', '147', '2');
INSERT INTO `sec_org` VALUES ('272', '信息与通信工程研1401', '', '6', '148', '2');
INSERT INTO `sec_org` VALUES ('273', '电路与系统1301', '', '6', '149', '2');
INSERT INTO `sec_org` VALUES ('274', '电子与通信工程研1301', '', '6', '150', '2');
INSERT INTO `sec_org` VALUES ('275', '集成电路工程1301', '', '6', '151', '2');
INSERT INTO `sec_org` VALUES ('276', '信息与通信工程研1301', '', '6', '152', '2');
INSERT INTO `sec_org` VALUES ('277', '控制工程研1301', '', '6', '153', '2');
INSERT INTO `sec_org` VALUES ('278', '控制工程与科学研1301', '', '6', '154', '2');
INSERT INTO `sec_org` VALUES ('279', '通信与信息系统研1301', '', '6', '155', '2');
INSERT INTO `sec_org` VALUES ('280', '控制科学与工程博1501', '', '6', '156', '2');
INSERT INTO `sec_org` VALUES ('281', '控制科学与工程研1401', '', '6', '157', '2');
INSERT INTO `sec_org` VALUES ('282', '国防学院团委', '', '29', '269', '2');
INSERT INTO `sec_org` VALUES ('283', '应技学院团委', '', '28', '270', '2');
INSERT INTO `sec_org` VALUES ('284', '城规1101', '', '16', '51', '2');
INSERT INTO `sec_org` VALUES ('285', '城规1102', '', '16', '52', '2');
INSERT INTO `sec_org` VALUES ('286', '工程中心科技竞赛负责人', '', '33', '271', '2');
INSERT INTO `sec_org` VALUES ('287', '建筑1101', '', '16', '53', '2');
INSERT INTO `sec_org` VALUES ('288', '建筑1102', '', '16', '54', '2');
INSERT INTO `sec_org` VALUES ('289', '工管1201', '', '16', '55', '2');
INSERT INTO `sec_org` VALUES ('290', '工管1202', '', '16', '56', '2');
INSERT INTO `sec_org` VALUES ('291', '工管1203', '', '16', '57', '2');
INSERT INTO `sec_org` VALUES ('292', '工管1204', '', '16', '58', '2');
INSERT INTO `sec_org` VALUES ('293', '工管1205', '', '16', '59', '2');
INSERT INTO `sec_org` VALUES ('294', '工程1201', '', '16', '60', '2');
INSERT INTO `sec_org` VALUES ('295', '工程1202', '', '16', '61', '2');
INSERT INTO `sec_org` VALUES ('296', '工程1203', '', '16', '62', '2');
INSERT INTO `sec_org` VALUES ('297', '工程1204', '', '16', '63', '2');
INSERT INTO `sec_org` VALUES ('298', '工程1205', '', '16', '64', '2');
INSERT INTO `sec_org` VALUES ('299', '工程1206', '', '16', '65', '2');
INSERT INTO `sec_org` VALUES ('300', '工程1207', '', '16', '66', '2');
INSERT INTO `sec_org` VALUES ('301', '工程1208', '', '16', '67', '2');
INSERT INTO `sec_org` VALUES ('302', '工力1201', '', '16', '68', '2');
INSERT INTO `sec_org` VALUES ('303', '城规1201', '', '16', '69', '2');
INSERT INTO `sec_org` VALUES ('304', '城规1202', '', '16', '70', '2');
INSERT INTO `sec_org` VALUES ('305', '建筑1201', '', '16', '71', '2');
INSERT INTO `sec_org` VALUES ('306', '建筑1202', '', '16', '72', '2');
INSERT INTO `sec_org` VALUES ('307', '建环1201', '', '16', '73', '2');
INSERT INTO `sec_org` VALUES ('308', '建环1202', '', '16', '74', '2');
INSERT INTO `sec_org` VALUES ('309', '建环1203', '', '16', '75', '2');
INSERT INTO `sec_org` VALUES ('310', '建环1204', '', '16', '76', '2');
INSERT INTO `sec_org` VALUES ('311', '工管1301', '', '16', '77', '2');
INSERT INTO `sec_org` VALUES ('312', '工管1302', '', '16', '78', '2');
INSERT INTO `sec_org` VALUES ('313', '工管1303', '', '16', '79', '2');
INSERT INTO `sec_org` VALUES ('314', '工管1304', '', '16', '80', '2');
INSERT INTO `sec_org` VALUES ('315', '工管1305', '', '16', '81', '2');
INSERT INTO `sec_org` VALUES ('316', '工管1306', '', '16', '82', '2');
INSERT INTO `sec_org` VALUES ('317', '工管1307', '', '16', '83', '2');
INSERT INTO `sec_org` VALUES ('318', '土木1301', '', '16', '84', '2');
INSERT INTO `sec_org` VALUES ('319', '土木1302', '', '16', '85', '2');
INSERT INTO `sec_org` VALUES ('320', '土木1303', '', '16', '86', '2');
INSERT INTO `sec_org` VALUES ('321', '土木1304', '', '16', '87', '2');
INSERT INTO `sec_org` VALUES ('322', '土木1305', '', '16', '88', '2');
INSERT INTO `sec_org` VALUES ('323', '土木1306', '', '16', '89', '2');
INSERT INTO `sec_org` VALUES ('324', '土木1307', '', '16', '90', '2');
INSERT INTO `sec_org` VALUES ('325', '土木1308', '', '16', '91', '2');
INSERT INTO `sec_org` VALUES ('326', '土木1309', '', '16', '92', '2');
INSERT INTO `sec_org` VALUES ('327', '土木1310', '', '16', '93', '2');
INSERT INTO `sec_org` VALUES ('328', '工力1301', '', '16', '94', '2');
INSERT INTO `sec_org` VALUES ('329', '城规1301', '', '16', '95', '2');
INSERT INTO `sec_org` VALUES ('330', '城规1302', '', '16', '96', '2');
INSERT INTO `sec_org` VALUES ('331', '建筑1301', '', '16', '97', '2');
INSERT INTO `sec_org` VALUES ('332', '建筑1302', '', '16', '98', '2');
INSERT INTO `sec_org` VALUES ('333', '建环1301', '', '16', '99', '2');
INSERT INTO `sec_org` VALUES ('334', '建环1302', '', '16', '100', '2');
INSERT INTO `sec_org` VALUES ('335', '建环1303', '', '16', '101', '2');
INSERT INTO `sec_org` VALUES ('336', '建环1304', '', '16', '102', '2');
INSERT INTO `sec_org` VALUES ('337', '工管1401', '', '16', '103', '2');
INSERT INTO `sec_org` VALUES ('338', '工管1402', '', '16', '104', '2');
INSERT INTO `sec_org` VALUES ('339', '工管1403', '', '16', '105', '2');
INSERT INTO `sec_org` VALUES ('340', '工造1401', '', '16', '106', '2');
INSERT INTO `sec_org` VALUES ('341', '工造1402', '', '16', '107', '2');
INSERT INTO `sec_org` VALUES ('342', '工造1403', '', '16', '108', '2');
INSERT INTO `sec_org` VALUES ('343', '工造1404', '', '16', '109', '2');
INSERT INTO `sec_org` VALUES ('344', '土木1401', '', '16', '110', '2');
INSERT INTO `sec_org` VALUES ('345', '土木1402', '', '16', '111', '2');
INSERT INTO `sec_org` VALUES ('346', '土木1403', '', '16', '112', '2');
INSERT INTO `sec_org` VALUES ('347', '土木1404', '', '16', '113', '2');
INSERT INTO `sec_org` VALUES ('348', '土木1405', '', '16', '114', '2');
INSERT INTO `sec_org` VALUES ('349', '土木1406', '', '16', '115', '2');
INSERT INTO `sec_org` VALUES ('350', '土木1407', '', '16', '116', '2');
INSERT INTO `sec_org` VALUES ('351', '土木1408', '', '16', '117', '2');
INSERT INTO `sec_org` VALUES ('352', '土木1409', '', '16', '118', '2');
INSERT INTO `sec_org` VALUES ('353', '土木1410', '', '16', '119', '2');
INSERT INTO `sec_org` VALUES ('354', '土木1411', '', '16', '120', '2');
INSERT INTO `sec_org` VALUES ('355', '工力1401', '', '16', '121', '2');
INSERT INTO `sec_org` VALUES ('356', '工力1402', '', '16', '122', '2');
INSERT INTO `sec_org` VALUES ('357', '城规1401', '', '16', '123', '2');
INSERT INTO `sec_org` VALUES ('358', '城规1402', '', '16', '124', '2');
INSERT INTO `sec_org` VALUES ('359', '建筑1401', '', '16', '125', '2');
INSERT INTO `sec_org` VALUES ('360', '建筑1402', '', '16', '126', '2');
INSERT INTO `sec_org` VALUES ('361', '建环1401', '', '16', '127', '2');
INSERT INTO `sec_org` VALUES ('362', '建环1402', '', '16', '128', '2');
INSERT INTO `sec_org` VALUES ('363', '建环1403', '', '16', '129', '2');
INSERT INTO `sec_org` VALUES ('364', '建环1404', '', '16', '130', '2');
INSERT INTO `sec_org` VALUES ('365', '工管1501', '', '16', '131', '2');
INSERT INTO `sec_org` VALUES ('366', '工管1502', '', '16', '132', '2');
INSERT INTO `sec_org` VALUES ('367', '工管1503', '', '16', '133', '2');
INSERT INTO `sec_org` VALUES ('368', '工管1504', '', '16', '134', '2');
INSERT INTO `sec_org` VALUES ('369', '工造1501', '', '16', '135', '2');
INSERT INTO `sec_org` VALUES ('370', '工造1502', '', '16', '136', '2');
INSERT INTO `sec_org` VALUES ('371', '工造1503', '', '16', '137', '2');
INSERT INTO `sec_org` VALUES ('372', '工造1504', '', '16', '138', '2');
INSERT INTO `sec_org` VALUES ('373', '土木1501', '', '16', '139', '2');
INSERT INTO `sec_org` VALUES ('374', '土木1502', '', '16', '140', '2');
INSERT INTO `sec_org` VALUES ('375', '土木1503', '', '16', '141', '2');
INSERT INTO `sec_org` VALUES ('376', '土木1504', '', '16', '142', '2');
INSERT INTO `sec_org` VALUES ('377', '土木1505', '', '16', '143', '2');
INSERT INTO `sec_org` VALUES ('378', '土木1506', '', '16', '144', '2');
INSERT INTO `sec_org` VALUES ('379', '土木1507', '', '16', '145', '2');
INSERT INTO `sec_org` VALUES ('380', '土木1508', '', '16', '146', '2');
INSERT INTO `sec_org` VALUES ('381', '土木1509', '', '16', '147', '2');
INSERT INTO `sec_org` VALUES ('382', '土木1510', '', '16', '148', '2');
INSERT INTO `sec_org` VALUES ('383', '土木1511', '', '16', '149', '2');
INSERT INTO `sec_org` VALUES ('384', '土木1512', '', '16', '150', '2');
INSERT INTO `sec_org` VALUES ('385', '土木1513', '', '16', '151', '2');
INSERT INTO `sec_org` VALUES ('386', '工力1501', '', '16', '152', '2');
INSERT INTO `sec_org` VALUES ('387', '工力1502', '', '16', '153', '2');
INSERT INTO `sec_org` VALUES ('388', '城规1501', '', '16', '154', '2');
INSERT INTO `sec_org` VALUES ('389', '城规1502', '', '16', '155', '2');
INSERT INTO `sec_org` VALUES ('390', '城规1503', '', '16', '156', '2');
INSERT INTO `sec_org` VALUES ('391', '建筑1501', '', '16', '157', '2');
INSERT INTO `sec_org` VALUES ('392', '建筑1502', '', '16', '158', '2');
INSERT INTO `sec_org` VALUES ('393', '建筑1503', '', '16', '159', '2');
INSERT INTO `sec_org` VALUES ('394', '建环1501', '', '16', '160', '2');
INSERT INTO `sec_org` VALUES ('395', '建环1502', '', '16', '161', '2');
INSERT INTO `sec_org` VALUES ('396', '城规研1501', '', '16', '162', '2');
INSERT INTO `sec_org` VALUES ('397', '工业设计研1501', '', '16', '163', '2');
INSERT INTO `sec_org` VALUES ('398', '土木研1501', '', '16', '164', '2');
INSERT INTO `sec_org` VALUES ('399', '建筑与土木工程研1501', '', '16', '165', '2');
INSERT INTO `sec_org` VALUES ('400', '法学1501', '', '23', '1', '2');
INSERT INTO `sec_org` VALUES ('401', '法学1502', '', '23', '2', '2');
INSERT INTO `sec_org` VALUES ('402', '法学1503', '', '23', '3', '2');
INSERT INTO `sec_org` VALUES ('403', '法学1504', '', '23', '4', '2');
INSERT INTO `sec_org` VALUES ('404', '法学1505', '', '23', '5', '2');
INSERT INTO `sec_org` VALUES ('405', '产权1501', '', '23', '6', '2');
INSERT INTO `sec_org` VALUES ('406', '产权1502', '', '23', '7', '2');
INSERT INTO `sec_org` VALUES ('407', '产权1503', '', '23', '8', '2');
INSERT INTO `sec_org` VALUES ('408', '心理1501', '', '23', '9', '2');
INSERT INTO `sec_org` VALUES ('409', '心理1502', '', '23', '10', '2');
INSERT INTO `sec_org` VALUES ('410', '心理1503', '', '23', '11', '2');
INSERT INTO `sec_org` VALUES ('411', '心理1504', '', '23', '12', '2');
INSERT INTO `sec_org` VALUES ('412', '法学1401', '', '23', '13', '2');
INSERT INTO `sec_org` VALUES ('413', '法学1402', '', '23', '14', '2');
INSERT INTO `sec_org` VALUES ('414', '法学1403', '', '23', '15', '2');
INSERT INTO `sec_org` VALUES ('415', '产权1401', '', '23', '16', '2');
INSERT INTO `sec_org` VALUES ('416', '产权1402', '', '23', '17', '2');
INSERT INTO `sec_org` VALUES ('417', '产权1403', '', '23', '18', '2');
INSERT INTO `sec_org` VALUES ('418', '心理1401', '', '23', '19', '2');
INSERT INTO `sec_org` VALUES ('419', '心理1402', '', '23', '20', '2');
INSERT INTO `sec_org` VALUES ('420', '法学1301', '', '23', '21', '2');
INSERT INTO `sec_org` VALUES ('421', '法学1302', '', '23', '22', '2');
INSERT INTO `sec_org` VALUES ('422', '法学1303', '', '23', '23', '2');
INSERT INTO `sec_org` VALUES ('423', '产权1301', '', '23', '24', '2');
INSERT INTO `sec_org` VALUES ('424', '产权1302', '', '23', '25', '2');
INSERT INTO `sec_org` VALUES ('425', '心理1301', '', '23', '26', '2');
INSERT INTO `sec_org` VALUES ('426', '心理1302', '', '23', '27', '2');
INSERT INTO `sec_org` VALUES ('427', '法学1201', '', '23', '28', '2');
INSERT INTO `sec_org` VALUES ('428', '法学1202', '', '23', '29', '2');
INSERT INTO `sec_org` VALUES ('429', '法学1203', '', '23', '30', '2');
INSERT INTO `sec_org` VALUES ('430', '法学1204', '', '23', '31', '2');
INSERT INTO `sec_org` VALUES ('431', '法学1205', '', '23', '32', '2');
INSERT INTO `sec_org` VALUES ('432', '心理1201', '', '23', '33', '2');
INSERT INTO `sec_org` VALUES ('433', '心理1202', '', '23', '34', '2');
INSERT INTO `sec_org` VALUES ('434', '经济法学研1501', '', '23', '35', '2');
INSERT INTO `sec_org` VALUES ('435', '经济法学研1401', '', '23', '36', '2');
INSERT INTO `sec_org` VALUES ('436', '经济法学研1301', '', '23', '37', '2');
INSERT INTO `sec_org` VALUES ('437', '法硕（法学）1501', '', '23', '38', '2');
INSERT INTO `sec_org` VALUES ('438', '法硕（法学）1401', '', '23', '39', '2');
INSERT INTO `sec_org` VALUES ('439', '法硕（法学）1301', '', '23', '40', '2');
INSERT INTO `sec_org` VALUES ('440', '法硕（非法学）1501', '', '23', '41', '2');
INSERT INTO `sec_org` VALUES ('441', '法硕（非法学）1401', '', '23', '42', '2');
INSERT INTO `sec_org` VALUES ('442', '法硕（非法学）1301', '', '23', '42', '2');
INSERT INTO `sec_org` VALUES ('443', '刑法研1501', '', '23', '43', '2');
INSERT INTO `sec_org` VALUES ('444', '刑法研1401', '', '23', '44', '2');
INSERT INTO `sec_org` VALUES ('445', '刑法研1301', '', '23', '45', '2');
INSERT INTO `sec_org` VALUES ('446', '城规研1401', '', '16', '166', '2');
INSERT INTO `sec_org` VALUES ('447', '城规研1301', '', '16', '167', '2');
INSERT INTO `sec_org` VALUES ('448', '工业设计研1401', '', '16', '168', '2');
INSERT INTO `sec_org` VALUES ('449', '工业设计研1301', '', '16', '169', '2');
INSERT INTO `sec_org` VALUES ('450', '土木研1401', '', '16', '170', '2');
INSERT INTO `sec_org` VALUES ('451', '土木研1301', '', '16', '171', '2');
INSERT INTO `sec_org` VALUES ('452', '建筑与土木工程研1401', '', '16', '172', '2');
INSERT INTO `sec_org` VALUES ('453', '建筑与土木工程研1301', '', '16', '173', '2');
INSERT INTO `sec_org` VALUES ('454', '思政1501', '', '24', '440', '2');
INSERT INTO `sec_org` VALUES ('455', '思政1502', '', '24', '441', '2');
INSERT INTO `sec_org` VALUES ('456', '思政1503', '', '24', '442', '2');
INSERT INTO `sec_org` VALUES ('457', '思政1504', '', '24', '443', '2');
INSERT INTO `sec_org` VALUES ('458', '思政1505', '', '24', '444', '2');
INSERT INTO `sec_org` VALUES ('459', '思政1401', '', '24', '445', '2');
INSERT INTO `sec_org` VALUES ('460', '思政1402', '', '24', '446', '2');
INSERT INTO `sec_org` VALUES ('461', '思政1403', '', '24', '447', '2');
INSERT INTO `sec_org` VALUES ('462', '思政1404', '', '24', '448', '2');
INSERT INTO `sec_org` VALUES ('463', '思政1301', '', '24', '449', '2');
INSERT INTO `sec_org` VALUES ('464', '思政1302', '', '24', '450', '2');
INSERT INTO `sec_org` VALUES ('465', '思政1303', '', '24', '451', '2');
INSERT INTO `sec_org` VALUES ('466', '思政1304', '', '24', '452', '2');
INSERT INTO `sec_org` VALUES ('467', '思政1201', '', '24', '453', '2');
INSERT INTO `sec_org` VALUES ('468', '思政硕2013', '', '24', '454', '2');
INSERT INTO `sec_org` VALUES ('469', '思政硕2014', '', '24', '455', '2');
INSERT INTO `sec_org` VALUES ('470', '思政硕2015', '', '24', '456', '2');
INSERT INTO `sec_org` VALUES ('471', '马克思主义基本原理研2013', '', '24', '457', '2');
INSERT INTO `sec_org` VALUES ('472', '马克思主义基本原理研2014', '', '24', '458', '2');
INSERT INTO `sec_org` VALUES ('473', '马克思主义基本原理研2015', '', '24', '459', '2');
INSERT INTO `sec_org` VALUES ('474', '马克思主义发展史研2013', '', '24', '460', '2');
INSERT INTO `sec_org` VALUES ('475', '马克思主义发展史研2014', '', '24', '461', '2');
INSERT INTO `sec_org` VALUES ('476', '马克思主义发展史研2015', '', '24', '462', '2');
INSERT INTO `sec_org` VALUES ('477', '马克思主义中国化研究研2013', '', '24', '463', '2');
INSERT INTO `sec_org` VALUES ('478', '马克思主义中国化研究研2014', '', '24', '464', '2');
INSERT INTO `sec_org` VALUES ('479', '马克思主义中国化研究研2015', '', '24', '465', '2');
INSERT INTO `sec_org` VALUES ('480', '中国近现代史基本问题研究研2013', '', '24', '466', '2');
INSERT INTO `sec_org` VALUES ('481', '中国近现代史基本问题研究研2014', '', '24', '467', '2');
INSERT INTO `sec_org` VALUES ('482', '中国近现代史基本问题研究研2015', '', '24', '468', '2');
INSERT INTO `sec_org` VALUES ('483', '辐射1501', '', '29', '469', '2');
INSERT INTO `sec_org` VALUES ('484', '辐射1502', '', '29', '470', '2');
INSERT INTO `sec_org` VALUES ('485', '辐射1401', '', '29', '471', '2');
INSERT INTO `sec_org` VALUES ('486', '辐射1402', '', '29', '472', '2');
INSERT INTO `sec_org` VALUES ('487', '辐射1301', '', '29', '473', '2');
INSERT INTO `sec_org` VALUES ('488', '辐射1302', '', '29', '474', '2');
INSERT INTO `sec_org` VALUES ('489', '辐射1201', '', '29', '475', '2');
INSERT INTO `sec_org` VALUES ('490', '辐射1202', '', '29', '476', '2');
INSERT INTO `sec_org` VALUES ('491', '核工1501', '', '29', '477', '2');
INSERT INTO `sec_org` VALUES ('492', '核工1502', '', '29', '478', '2');
INSERT INTO `sec_org` VALUES ('493', '核工1503', '', '29', '479', '2');
INSERT INTO `sec_org` VALUES ('494', '核工1401', '', '29', '480', '2');
INSERT INTO `sec_org` VALUES ('495', '核工1402', '', '29', '481', '2');
INSERT INTO `sec_org` VALUES ('496', '核工1301', '', '29', '482', '2');
INSERT INTO `sec_org` VALUES ('497', '核工1302', '', '29', '483', '2');
INSERT INTO `sec_org` VALUES ('498', '核工1201', '', '29', '484', '2');
INSERT INTO `sec_org` VALUES ('499', '核工1202', '', '29', '485', '2');
INSERT INTO `sec_org` VALUES ('500', '核工1203', '', '29', '486', '2');
INSERT INTO `sec_org` VALUES ('501', '对抗1501', '', '29', '487', '2');
INSERT INTO `sec_org` VALUES ('502', '对抗1502', '', '29', '488', '2');
INSERT INTO `sec_org` VALUES ('503', '对抗1401', '', '29', '489', '2');
INSERT INTO `sec_org` VALUES ('504', '对抗1402', '', '29', '490', '2');
INSERT INTO `sec_org` VALUES ('505', '对抗1301', '', '29', '491', '2');
INSERT INTO `sec_org` VALUES ('506', '对抗1302', '', '29', '492', '2');
INSERT INTO `sec_org` VALUES ('507', '对抗1201', '', '29', '493', '2');
INSERT INTO `sec_org` VALUES ('508', '对抗1202', '', '29', '494', '2');
INSERT INTO `sec_org` VALUES ('509', '特能1501', '', '29', '495', '2');
INSERT INTO `sec_org` VALUES ('510', '特能1502', '', '29', '496', '2');
INSERT INTO `sec_org` VALUES ('511', '特能1503', '', '29', '497', '2');
INSERT INTO `sec_org` VALUES ('512', '特能1504', '', '29', '498', '2');
INSERT INTO `sec_org` VALUES ('513', '特能1401', '', '29', '499', '2');
INSERT INTO `sec_org` VALUES ('514', '特能1402', '', '29', '500', '2');
INSERT INTO `sec_org` VALUES ('515', '特能1403', '', '29', '501', '2');
INSERT INTO `sec_org` VALUES ('516', '特能1301', '', '29', '502', '2');
INSERT INTO `sec_org` VALUES ('517', '特能1302', '', '29', '504', '2');
INSERT INTO `sec_org` VALUES ('518', '特能1303', '', '29', '505', '2');
INSERT INTO `sec_org` VALUES ('519', '特能1201', '', '29', '506', '2');
INSERT INTO `sec_org` VALUES ('520', '特能1202', '', '29', '507', '2');
INSERT INTO `sec_org` VALUES ('521', '核燃1501', '', '29', '508', '2');
INSERT INTO `sec_org` VALUES ('522', '核燃1502', '', '29', '509', '2');
INSERT INTO `sec_org` VALUES ('523', '核燃1503', '', '29', '510', '2');
INSERT INTO `sec_org` VALUES ('524', '核燃1401', '', '29', '511', '2');
INSERT INTO `sec_org` VALUES ('525', '核燃1402', '', '29', '512', '2');
INSERT INTO `sec_org` VALUES ('526', '核燃1301', '', '29', '513', '2');
INSERT INTO `sec_org` VALUES ('527', '核燃1302', '', '29', '514', '2');
INSERT INTO `sec_org` VALUES ('528', '核燃1201', '', '29', '515', '2');
INSERT INTO `sec_org` VALUES ('529', '核燃1202', '', '29', '516', '2');
INSERT INTO `sec_org` VALUES ('530', '日语1501', '', '25', '517', '2');
INSERT INTO `sec_org` VALUES ('531', '日语1502', '', '25', '518', '2');
INSERT INTO `sec_org` VALUES ('532', '日语1503', '', '25', '519', '2');
INSERT INTO `sec_org` VALUES ('533', '日语1504', '', '25', '520', '2');
INSERT INTO `sec_org` VALUES ('534', '日语1401', '', '25', '521', '2');
INSERT INTO `sec_org` VALUES ('535', '日语1402', '', '25', '522', '2');
INSERT INTO `sec_org` VALUES ('536', '日语1301', '', '25', '523', '2');
INSERT INTO `sec_org` VALUES ('537', '日语1302', '', '25', '524', '2');
INSERT INTO `sec_org` VALUES ('538', '日语1201', '', '25', '525', '2');
INSERT INTO `sec_org` VALUES ('539', '西语1501', '', '25', '526', '2');
INSERT INTO `sec_org` VALUES ('540', '西语1502', '', '25', '527', '2');
INSERT INTO `sec_org` VALUES ('541', '西语1503', '', '25', '528', '2');
INSERT INTO `sec_org` VALUES ('542', '西语1504', '', '25', '529', '2');
INSERT INTO `sec_org` VALUES ('543', '西语1401', '', '25', '530', '2');
INSERT INTO `sec_org` VALUES ('544', '西语1402', '', '25', '531', '2');
INSERT INTO `sec_org` VALUES ('545', '西语1301', '', '25', '532', '2');
INSERT INTO `sec_org` VALUES ('546', '西语1302', '', '25', '533', '2');
INSERT INTO `sec_org` VALUES ('547', '西语1303', '', '25', '534', '2');
INSERT INTO `sec_org` VALUES ('548', '西语1201', '', '25', '535', '2');
INSERT INTO `sec_org` VALUES ('549', '西语1202', '', '25', '536', '2');
INSERT INTO `sec_org` VALUES ('550', '英语1501', '', '25', '537', '2');
INSERT INTO `sec_org` VALUES ('551', '英语1502', '', '25', '538', '2');
INSERT INTO `sec_org` VALUES ('552', '英语1503', '', '25', '539', '2');
INSERT INTO `sec_org` VALUES ('553', '英语1504', '', '25', '540', '2');
INSERT INTO `sec_org` VALUES ('554', '英语1401', '', '25', '541', '2');
INSERT INTO `sec_org` VALUES ('555', '英语1402', '', '25', '542', '2');
INSERT INTO `sec_org` VALUES ('556', '英语1403', '', '25', '543', '2');
INSERT INTO `sec_org` VALUES ('557', '英语1301', '', '25', '544', '2');
INSERT INTO `sec_org` VALUES ('558', '英语1302', '', '25', '545', '2');
INSERT INTO `sec_org` VALUES ('559', '英语1303', '', '25', '546', '2');
INSERT INTO `sec_org` VALUES ('560', '英语1201', '', '25', '547', '2');
INSERT INTO `sec_org` VALUES ('561', '英语1202', '', '25', '548', '2');
INSERT INTO `sec_org` VALUES ('562', '英语1203', '', '25', '549', '2');
INSERT INTO `sec_org` VALUES ('563', '英语1204', '', '25', '550', '2');
INSERT INTO `sec_org` VALUES ('564', '商英1501', '', '25', '551', '2');
INSERT INTO `sec_org` VALUES ('565', '商英1502', '', '25', '552', '2');
INSERT INTO `sec_org` VALUES ('566', '商英1503', '', '25', '553', '2');
INSERT INTO `sec_org` VALUES ('567', '商英1504', '', '25', '554', '2');
INSERT INTO `sec_org` VALUES ('568', '商英1401', '', '25', '555', '2');
INSERT INTO `sec_org` VALUES ('569', '商英1402', '', '25', '556', '2');
INSERT INTO `sec_org` VALUES ('570', '商英1403', '', '25', '557', '2');
INSERT INTO `sec_org` VALUES ('571', '商英1301', '', '25', '558', '2');
INSERT INTO `sec_org` VALUES ('572', '商英1302', '', '25', '559', '2');
INSERT INTO `sec_org` VALUES ('573', '商英1201', '', '25', '560', '2');
INSERT INTO `sec_org` VALUES ('574', '商英1202', '', '25', '561', '2');
INSERT INTO `sec_org` VALUES ('575', '商英1203', '', '25', '562', '2');
INSERT INTO `sec_org` VALUES ('576', '翻译1501', '', '25', '563', '2');
INSERT INTO `sec_org` VALUES ('577', '翻译1502', '', '25', '564', '2');
INSERT INTO `sec_org` VALUES ('578', '翻译1503', '', '25', '565', '2');
INSERT INTO `sec_org` VALUES ('579', '翻译1401', '', '25', '566', '2');
INSERT INTO `sec_org` VALUES ('580', '翻译1402', '', '25', '567', '2');
INSERT INTO `sec_org` VALUES ('581', '翻译1301', '', '25', '568', '2');
INSERT INTO `sec_org` VALUES ('582', '翻译1302', '', '25', '569', '2');
INSERT INTO `sec_org` VALUES ('583', '英语语言文学研2015', '', '25', '570', '2');
INSERT INTO `sec_org` VALUES ('584', '英语语言文学研2014', '', '25', '571', '2');
INSERT INTO `sec_org` VALUES ('585', '英语语言文学研2013', '', '25', '572', '2');
INSERT INTO `sec_org` VALUES ('586', 'MTI笔译研2015', '', '25', '573', '2');
INSERT INTO `sec_org` VALUES ('587', 'MTI笔译研2014', '', '25', '574', '2');
INSERT INTO `sec_org` VALUES ('588', 'MTI笔译研2013', '', '25', '575', '2');
INSERT INTO `sec_org` VALUES ('589', 'MTI口译研2015', '', '25', '576', '2');
INSERT INTO `sec_org` VALUES ('590', 'MTI口译研2014', '', '25', '576', '2');
INSERT INTO `sec_org` VALUES ('591', 'MTI口译研2013', '', '25', '577', '2');
INSERT INTO `sec_org` VALUES ('592', '环设1501', '', '22', '578', '2');
INSERT INTO `sec_org` VALUES ('593', '环设1502', '', '22', '579', '2');
INSERT INTO `sec_org` VALUES ('594', '环设1503', '', '22', '580', '2');
INSERT INTO `sec_org` VALUES ('595', '环设1504', '', '22', '581', '2');
INSERT INTO `sec_org` VALUES ('596', '环设1401', '', '22', '582', '2');
INSERT INTO `sec_org` VALUES ('597', '环设1402', '', '22', '583', '2');
INSERT INTO `sec_org` VALUES ('598', '环设1403', '', '22', '584', '2');
INSERT INTO `sec_org` VALUES ('599', '环设1404', '', '22', '585', '2');
INSERT INTO `sec_org` VALUES ('600', '环设1301', '', '22', '586', '2');
INSERT INTO `sec_org` VALUES ('601', '环设1302', '', '22', '587', '2');
INSERT INTO `sec_org` VALUES ('602', '环设1201', '', '22', '588', '2');
INSERT INTO `sec_org` VALUES ('603', '环设1202', '', '22', '589', '2');
INSERT INTO `sec_org` VALUES ('604', '视传1501', '', '22', '590', '2');
INSERT INTO `sec_org` VALUES ('605', '视传1502', '', '22', '591', '2');
INSERT INTO `sec_org` VALUES ('606', '视传1503', '', '22', '592', '2');
INSERT INTO `sec_org` VALUES ('607', '视传1504', '', '22', '593', '2');
INSERT INTO `sec_org` VALUES ('608', '视传1401', '', '22', '594', '2');
INSERT INTO `sec_org` VALUES ('609', '视传1402', '', '22', '595', '2');
INSERT INTO `sec_org` VALUES ('610', '视传1403', '', '22', '596', '2');
INSERT INTO `sec_org` VALUES ('611', '视传1404', '', '22', '597', '2');
INSERT INTO `sec_org` VALUES ('612', '视传1301', '', '22', '598', '2');
INSERT INTO `sec_org` VALUES ('613', '视传1302', '', '22', '599', '2');
INSERT INTO `sec_org` VALUES ('614', '视传1201', '', '22', '600', '2');
INSERT INTO `sec_org` VALUES ('615', '视传1202', '', '22', '601', '2');
INSERT INTO `sec_org` VALUES ('616', '音乐1501', '', '22', '602', '2');
INSERT INTO `sec_org` VALUES ('617', '音乐1401', '', '22', '603', '2');
INSERT INTO `sec_org` VALUES ('618', '音乐1402', '', '22', '604', '2');
INSERT INTO `sec_org` VALUES ('619', '音乐1301', '', '22', '605', '2');
INSERT INTO `sec_org` VALUES ('620', '音乐1201', '', '22', '606', '2');
INSERT INTO `sec_org` VALUES ('621', '广电1501', '', '22', '607', '2');
INSERT INTO `sec_org` VALUES ('622', '广电1502', '', '22', '608', '2');
INSERT INTO `sec_org` VALUES ('623', '广电1503', '', '22', '609', '2');
INSERT INTO `sec_org` VALUES ('624', '广电1401', '', '22', '610', '2');
INSERT INTO `sec_org` VALUES ('625', '广电1402', '', '22', '611', '2');
INSERT INTO `sec_org` VALUES ('628', '广电1301', '', '22', '614', '2');
INSERT INTO `sec_org` VALUES ('629', '广电1302', '', '22', '615', '2');
INSERT INTO `sec_org` VALUES ('632', '广电1201', '', '22', '618', '2');
INSERT INTO `sec_org` VALUES ('633', '广电1202', '', '22', '619', '2');
INSERT INTO `sec_org` VALUES ('636', '广电研2015', '', '22', '622', '2');
INSERT INTO `sec_org` VALUES ('637', '广电研2014', '', '22', '623', '2');
INSERT INTO `sec_org` VALUES ('638', '广电研2013', '', '22', '624', '2');
INSERT INTO `sec_org` VALUES ('639', '汉语1501', '', '22', '625', '2');
INSERT INTO `sec_org` VALUES ('640', '汉语1502', '', '22', '626', '2');
INSERT INTO `sec_org` VALUES ('641', '汉语1503', '', '22', '627', '2');
INSERT INTO `sec_org` VALUES ('642', '汉语1401', '', '22', '628', '2');
INSERT INTO `sec_org` VALUES ('643', '汉语1402', '', '22', '629', '2');
INSERT INTO `sec_org` VALUES ('644', '汉语1403', '', '22', '630', '2');
INSERT INTO `sec_org` VALUES ('645', '汉语1301', '', '22', '631', '2');
INSERT INTO `sec_org` VALUES ('646', '汉语1302', '', '22', '632', '2');
INSERT INTO `sec_org` VALUES ('647', '汉语1303', '', '22', '633', '2');
INSERT INTO `sec_org` VALUES ('648', '汉语1201', '', '22', '634', '2');
INSERT INTO `sec_org` VALUES ('649', '汉语1202', '', '22', '635', '2');
INSERT INTO `sec_org` VALUES ('650', '汉语1203', '', '22', '636', '2');
INSERT INTO `sec_org` VALUES ('651', '汉语研2015', '', '22', '637', '2');
INSERT INTO `sec_org` VALUES ('652', '汉语研2014', '', '22', '638', '2');
INSERT INTO `sec_org` VALUES ('653', '汉语研2013', '', '22', '639', '2');
INSERT INTO `sec_org` VALUES ('654', '对外1501', '', '22', '640', '2');
INSERT INTO `sec_org` VALUES ('655', '对外1502', '', '22', '641', '2');
INSERT INTO `sec_org` VALUES ('656', '对外1503', '', '22', '642', '2');
INSERT INTO `sec_org` VALUES ('657', '对外1401', '', '22', '643', '2');
INSERT INTO `sec_org` VALUES ('658', '对外1402', '', '22', '644', '2');
INSERT INTO `sec_org` VALUES ('659', '对外1301', '', '22', '645', '2');
INSERT INTO `sec_org` VALUES ('660', '对外1302', '', '22', '646', '2');
INSERT INTO `sec_org` VALUES ('661', '对外1201', '', '22', '647', '2');
INSERT INTO `sec_org` VALUES ('662', '对外1202', '', '22', '648', '2');
INSERT INTO `sec_org` VALUES ('663', '对外研2015', '', '22', '649', '2');
INSERT INTO `sec_org` VALUES ('664', '对外研2014', '', '22', '650', '2');
INSERT INTO `sec_org` VALUES ('665', '对外研2013', '', '22', '651', '2');
INSERT INTO `sec_org` VALUES ('666', '国贸1501', '', '21', '652', '2');
INSERT INTO `sec_org` VALUES ('667', '国贸1502', '', '21', '653', '2');
INSERT INTO `sec_org` VALUES ('668', '国贸1503', '', '21', '654', '2');
INSERT INTO `sec_org` VALUES ('669', '国贸1504', '', '21', '655', '2');
INSERT INTO `sec_org` VALUES ('670', '国贸1505', '', '21', '656', '2');
INSERT INTO `sec_org` VALUES ('671', '国贸1401', '', '21', '657', '2');
INSERT INTO `sec_org` VALUES ('672', '国贸1402', '', '21', '658', '2');
INSERT INTO `sec_org` VALUES ('673', '国贸1403', '', '21', '659', '2');
INSERT INTO `sec_org` VALUES ('674', '国贸1301', '', '21', '660', '2');
INSERT INTO `sec_org` VALUES ('675', '国贸1302', '', '21', '661', '2');
INSERT INTO `sec_org` VALUES ('676', '国贸1303', '', '21', '662', '2');
INSERT INTO `sec_org` VALUES ('677', '国贸1201', '', '21', '663', '2');
INSERT INTO `sec_org` VALUES ('678', '国贸1202', '', '21', '664', '2');
INSERT INTO `sec_org` VALUES ('679', '工商1501', '', '21', '665', '2');
INSERT INTO `sec_org` VALUES ('680', '工商1502', '', '21', '666', '2');
INSERT INTO `sec_org` VALUES ('681', '工商1503', '', '21', '667', '2');
INSERT INTO `sec_org` VALUES ('682', '工商1504', '', '21', '668', '2');
INSERT INTO `sec_org` VALUES ('683', '工商1505', '', '21', '669', '2');
INSERT INTO `sec_org` VALUES ('684', '工商1401', '', '21', '670', '2');
INSERT INTO `sec_org` VALUES ('685', '工商1402', '', '21', '671', '2');
INSERT INTO `sec_org` VALUES ('686', '工商1403', '', '21', '672', '2');
INSERT INTO `sec_org` VALUES ('687', '工商1301', '', '21', '673', '2');
INSERT INTO `sec_org` VALUES ('688', '工商1302', '', '21', '674', '2');
INSERT INTO `sec_org` VALUES ('689', '工商1303', '', '21', '675', '2');
INSERT INTO `sec_org` VALUES ('690', '工商1201', '', '21', '676', '2');
INSERT INTO `sec_org` VALUES ('691', '工商1202', '', '21', '677', '2');
INSERT INTO `sec_org` VALUES ('692', '工商研2015', '', '21', '678', '2');
INSERT INTO `sec_org` VALUES ('693', '工商研2014', '', '21', '679', '2');
INSERT INTO `sec_org` VALUES ('694', '经济1501', '', '21', '680', '2');
INSERT INTO `sec_org` VALUES ('695', '经济1502', '', '21', '681', '2');
INSERT INTO `sec_org` VALUES ('696', '经济1503', '', '21', '682', '2');
INSERT INTO `sec_org` VALUES ('697', '经济1504', '', '21', '683', '2');
INSERT INTO `sec_org` VALUES ('698', '经济1505', '', '21', '684', '2');
INSERT INTO `sec_org` VALUES ('699', '经济1401', '', '21', '685', '2');
INSERT INTO `sec_org` VALUES ('700', '经济1402', '', '21', '686', '2');
INSERT INTO `sec_org` VALUES ('701', '经济1403', '', '21', '687', '2');
INSERT INTO `sec_org` VALUES ('702', '经济1301', '', '21', '688', '2');
INSERT INTO `sec_org` VALUES ('703', '经济1302', '', '21', '689', '2');
INSERT INTO `sec_org` VALUES ('704', '经济1303', '', '21', '690', '2');
INSERT INTO `sec_org` VALUES ('705', '经济1304', '', '21', '691', '2');
INSERT INTO `sec_org` VALUES ('706', '经济1201', '', '21', '692', '2');
INSERT INTO `sec_org` VALUES ('707', '经济1202', '', '21', '693', '2');
INSERT INTO `sec_org` VALUES ('708', '经济1203', '', '21', '694', '2');
INSERT INTO `sec_org` VALUES ('709', '信管1501', '', '21', '695', '2');
INSERT INTO `sec_org` VALUES ('710', '信管1502', '', '21', '696', '2');
INSERT INTO `sec_org` VALUES ('711', '信管1503', '', '21', '697', '2');
INSERT INTO `sec_org` VALUES ('712', '信管1401', '', '21', '698', '2');
INSERT INTO `sec_org` VALUES ('713', '信管1402', '', '21', '699', '2');
INSERT INTO `sec_org` VALUES ('714', '信管1301', '', '21', '700', '2');
INSERT INTO `sec_org` VALUES ('715', '信管1201', '', '21', '701', '2');
INSERT INTO `sec_org` VALUES ('716', '信管1202', '', '21', '702', '2');
INSERT INTO `sec_org` VALUES ('717', '营销1501', '', '21', '703', '2');
INSERT INTO `sec_org` VALUES ('718', '营销1502', '', '21', '704', '2');
INSERT INTO `sec_org` VALUES ('719', '营销1503', '', '21', '705', '2');
INSERT INTO `sec_org` VALUES ('720', '营销1504', '', '21', '706', '2');
INSERT INTO `sec_org` VALUES ('721', '营销1401', '', '21', '707', '2');
INSERT INTO `sec_org` VALUES ('722', '营销1402', '', '21', '708', '2');
INSERT INTO `sec_org` VALUES ('723', '营销1403', '', '21', '709', '2');
INSERT INTO `sec_org` VALUES ('724', '营销1301', '', '21', '710', '2');
INSERT INTO `sec_org` VALUES ('725', '营销1302', '', '21', '711', '2');
INSERT INTO `sec_org` VALUES ('726', '营销1303', '', '21', '712', '2');
INSERT INTO `sec_org` VALUES ('727', '营销1201', '', '21', '713', '2');
INSERT INTO `sec_org` VALUES ('728', '营销1202', '', '21', '714', '2');
INSERT INTO `sec_org` VALUES ('729', '营销1203', '', '21', '715', '2');
INSERT INTO `sec_org` VALUES ('730', '会计1501', '', '21', '716', '2');
INSERT INTO `sec_org` VALUES ('731', '会计1502', '', '21', '717', '2');
INSERT INTO `sec_org` VALUES ('732', '会计1503', '', '21', '718', '2');
INSERT INTO `sec_org` VALUES ('733', '会计1504', '', '21', '719', '2');
INSERT INTO `sec_org` VALUES ('734', '会计1505', '', '21', '720', '2');
INSERT INTO `sec_org` VALUES ('735', '会计1401', '', '21', '721', '2');
INSERT INTO `sec_org` VALUES ('736', '会计1402', '', '21', '722', '2');
INSERT INTO `sec_org` VALUES ('737', '会计1403', '', '21', '723', '2');
INSERT INTO `sec_org` VALUES ('738', '会计1404', '', '21', '724', '2');
INSERT INTO `sec_org` VALUES ('739', '会计1405', '', '21', '725', '2');
INSERT INTO `sec_org` VALUES ('740', '会计1301', '', '21', '726', '2');
INSERT INTO `sec_org` VALUES ('741', '会计1302', '', '21', '727', '2');
INSERT INTO `sec_org` VALUES ('742', '会计1303', '', '21', '728', '2');
INSERT INTO `sec_org` VALUES ('743', '会计1304', '', '21', '729', '2');
INSERT INTO `sec_org` VALUES ('744', '会计1305', '', '21', '730', '2');
INSERT INTO `sec_org` VALUES ('745', '会计1306', '', '21', '731', '2');
INSERT INTO `sec_org` VALUES ('746', '会计1201', '', '21', '732', '2');
INSERT INTO `sec_org` VALUES ('747', '会计1202', '', '21', '733', '2');
INSERT INTO `sec_org` VALUES ('748', '会计1203', '', '21', '734', '2');
INSERT INTO `sec_org` VALUES ('749', '会计1204', '', '21', '735', '2');
INSERT INTO `sec_org` VALUES ('750', '会计1205', '', '21', '736', '2');
INSERT INTO `sec_org` VALUES ('751', '会计研2013', '', '21', '737', '2');
INSERT INTO `sec_org` VALUES ('754', '商务1501', '', '21', '740', '2');
INSERT INTO `sec_org` VALUES ('755', '商务1502', '', '21', '741', '2');
INSERT INTO `sec_org` VALUES ('756', '商务1503', '', '21', '742', '2');
INSERT INTO `sec_org` VALUES ('757', '商务1401', '', '21', '743', '2');
INSERT INTO `sec_org` VALUES ('758', '商务1402', '', '21', '744', '2');
INSERT INTO `sec_org` VALUES ('759', '商务1301', '', '21', '745', '2');
INSERT INTO `sec_org` VALUES ('760', '商务1201', '', '21', '746', '2');
INSERT INTO `sec_org` VALUES ('761', '商务1202', '', '21', '747', '2');
INSERT INTO `sec_org` VALUES ('762', '公管1501', '', '21', '748', '2');
INSERT INTO `sec_org` VALUES ('763', '公管1502', '', '21', '749', '2');
INSERT INTO `sec_org` VALUES ('764', '公管1503', '', '21', '750', '2');
INSERT INTO `sec_org` VALUES ('765', '公管1401', '', '21', '751', '2');
INSERT INTO `sec_org` VALUES ('766', '公管1402', '', '21', '752', '2');
INSERT INTO `sec_org` VALUES ('767', '公管1403', '', '21', '753', '2');
INSERT INTO `sec_org` VALUES ('768', '公管1301', '', '21', '754', '2');
INSERT INTO `sec_org` VALUES ('769', '公管1201', '', '21', '755', '2');
INSERT INTO `sec_org` VALUES ('770', '公管1202', '', '21', '756', '2');
INSERT INTO `sec_org` VALUES ('772', '物流1501', '', '21', '757', '2');
INSERT INTO `sec_org` VALUES ('773', '物流1502', '', '21', '758', '2');
INSERT INTO `sec_org` VALUES ('774', '物流1503', '', '21', '759', '2');
INSERT INTO `sec_org` VALUES ('775', '物流1504', '', '21', '760', '2');
INSERT INTO `sec_org` VALUES ('776', '物流1505', '', '21', '761', '2');
INSERT INTO `sec_org` VALUES ('777', '物流1401', '', '21', '762', '2');
INSERT INTO `sec_org` VALUES ('778', '物流1402', '', '21', '763', '2');
INSERT INTO `sec_org` VALUES ('779', '物流1403', '', '21', '764', '2');
INSERT INTO `sec_org` VALUES ('780', '物流1301', '', '21', '765', '2');
INSERT INTO `sec_org` VALUES ('781', '物流1302', '', '21', '766', '2');
INSERT INTO `sec_org` VALUES ('782', '物流1303', '', '21', '767', '2');
INSERT INTO `sec_org` VALUES ('783', '物流1201', '', '21', '768', '2');
INSERT INTO `sec_org` VALUES ('784', '物流1202', '', '21', '769', '2');
INSERT INTO `sec_org` VALUES ('785', 'ACCA1501', '', '21', '770', '2');
INSERT INTO `sec_org` VALUES ('786', 'ACCA1401', '', '21', '771', '2');
INSERT INTO `sec_org` VALUES ('787', 'ACCA1301', '', '21', '772', '2');
INSERT INTO `sec_org` VALUES ('788', 'ACCA1201', '', '21', '773', '2');
INSERT INTO `sec_org` VALUES ('789', '情报学研13级', '', '21', '774', '2');
INSERT INTO `sec_org` VALUES ('790', '情报学研14级', '', '21', '775', '2');
INSERT INTO `sec_org` VALUES ('791', '情报学研15级', '', '21', '776', '2');
INSERT INTO `sec_org` VALUES ('792', '金融研15级', '', '21', '777', '2');
INSERT INTO `sec_org` VALUES ('793', '技术经济及管理研2013', '', '21', '778', '2');
INSERT INTO `sec_org` VALUES ('794', '旅游管理研2013', '', '21', '779', '2');
INSERT INTO `sec_org` VALUES ('795', '企业管理研2013', '', '21', '780', '2');
INSERT INTO `sec_org` VALUES ('797', '营销1401', '', '28', '781', '2');
INSERT INTO `sec_org` VALUES ('798', '营销1301', '', '28', '782', '2');
INSERT INTO `sec_org` VALUES ('799', '营销1302', '', '28', '783', '2');
INSERT INTO `sec_org` VALUES ('800', '法律1401', '', '28', '784', '2');
INSERT INTO `sec_org` VALUES ('801', '法律1301', '', '28', '785', '2');
INSERT INTO `sec_org` VALUES ('802', '园林1401', '', '28', '786', '2');
INSERT INTO `sec_org` VALUES ('803', '园林1301', '', '28', '787', '2');
INSERT INTO `sec_org` VALUES ('804', '机械1401', '', '28', '788', '2');
INSERT INTO `sec_org` VALUES ('805', '机械1402', '', '28', '789', '2');
INSERT INTO `sec_org` VALUES ('806', '机械1301', '', '28', '790', '2');
INSERT INTO `sec_org` VALUES ('807', '机械1302', '', '28', '791', '2');
INSERT INTO `sec_org` VALUES ('808', '建工1401', '', '28', '792', '2');
INSERT INTO `sec_org` VALUES ('809', '建工1402', '', '28', '793', '2');
INSERT INTO `sec_org` VALUES ('810', '建工1301', '', '28', '794', '2');
INSERT INTO `sec_org` VALUES ('811', '建工1302', '', '28', '795', '2');
INSERT INTO `sec_org` VALUES ('812', '建工1303', '', '28', '796', '2');
INSERT INTO `sec_org` VALUES ('813', '建工1304', '', '28', '797', '2');
INSERT INTO `sec_org` VALUES ('814', '应电1401', '', '28', '798', '2');
INSERT INTO `sec_org` VALUES ('815', '应电1301', '', '28', '799', '2');
INSERT INTO `sec_org` VALUES ('816', '计应1401', '', '28', '800', '2');
INSERT INTO `sec_org` VALUES ('817', '计应1402', '', '28', '801', '2');
INSERT INTO `sec_org` VALUES ('818', '计应1301', '', '28', '803', '2');
INSERT INTO `sec_org` VALUES ('819', '计应1302', '', '28', '804', '2');
INSERT INTO `sec_org` VALUES ('820', '会计1401', '', '28', '805', '2');
INSERT INTO `sec_org` VALUES ('821', '会计1402', '', '28', '806', '2');
INSERT INTO `sec_org` VALUES ('822', '会计1403', '', '28', '807', '2');
INSERT INTO `sec_org` VALUES ('823', '会计1404', '', '28', '808', '2');
INSERT INTO `sec_org` VALUES ('824', '会计1405', '', '28', '809', '2');
INSERT INTO `sec_org` VALUES ('825', '会计1406', '', '28', '810', '2');
INSERT INTO `sec_org` VALUES ('826', '会计1301', '', '28', '811', '2');
INSERT INTO `sec_org` VALUES ('827', '会计1302', '', '28', '812', '2');
INSERT INTO `sec_org` VALUES ('828', '会计1303', '', '28', '813', '2');
INSERT INTO `sec_org` VALUES ('829', '会计1304', '', '28', '814', '2');
INSERT INTO `sec_org` VALUES ('830', '会计1305', '', '28', '815', '2');
INSERT INTO `sec_org` VALUES ('831', '会计1306', '', '28', '816', '2');
INSERT INTO `sec_org` VALUES ('833', '会计1307', '', '28', '817', '2');
INSERT INTO `sec_org` VALUES ('834', '会计1308', '', '28', '818', '2');
INSERT INTO `sec_org` VALUES ('835', '会计1309', '', '28', '819', '2');
INSERT INTO `sec_org` VALUES ('836', '会计1310', '', '28', '820', '2');
INSERT INTO `sec_org` VALUES ('837', '制药1501', '', '19', '821', '2');
INSERT INTO `sec_org` VALUES ('838', '制药1502', '', '19', '822', '2');
INSERT INTO `sec_org` VALUES ('839', '制药1503', '', '19', '823', '2');
INSERT INTO `sec_org` VALUES ('840', '制药1504', '', '19', '824', '2');
INSERT INTO `sec_org` VALUES ('841', '制药1505', '', '19', '825', '2');
INSERT INTO `sec_org` VALUES ('842', '制药1401', '', '19', '826', '2');
INSERT INTO `sec_org` VALUES ('843', '制药1402', '', '19', '827', '2');
INSERT INTO `sec_org` VALUES ('844', '制药1403', '', '19', '828', '2');
INSERT INTO `sec_org` VALUES ('845', '制药1404', '', '19', '829', '2');
INSERT INTO `sec_org` VALUES ('846', '制药1301', '', '19', '830', '2');
INSERT INTO `sec_org` VALUES ('847', '制药1302', '', '19', '831', '2');
INSERT INTO `sec_org` VALUES ('848', '制药1303', '', '19', '832', '2');
INSERT INTO `sec_org` VALUES ('849', '制药1304', '', '19', '833', '2');
INSERT INTO `sec_org` VALUES ('850', '生物工程1501', '', '19', '834', '2');
INSERT INTO `sec_org` VALUES ('851', '生物工程1502', '', '19', '835', '2');
INSERT INTO `sec_org` VALUES ('852', '生物工程1503', '', '19', '836', '2');
INSERT INTO `sec_org` VALUES ('853', '生物工程1504', '', '19', '837', '2');
INSERT INTO `sec_org` VALUES ('854', '生物工程1401', '', '19', '839', '2');
INSERT INTO `sec_org` VALUES ('855', '生物工程1402', '', '19', '840', '2');
INSERT INTO `sec_org` VALUES ('856', '生物工程1403', '', '19', '841', '2');
INSERT INTO `sec_org` VALUES ('857', '生物工程1404', '', '19', '842', '2');
INSERT INTO `sec_org` VALUES ('858', '生物工程1301', '', '19', '843', '2');
INSERT INTO `sec_org` VALUES ('859', '生物工程1302', '', '19', '844', '2');
INSERT INTO `sec_org` VALUES ('860', '生物工程1303', '', '19', '845', '2');
INSERT INTO `sec_org` VALUES ('861', '生物工程1304', '', '19', '846', '2');
INSERT INTO `sec_org` VALUES ('862', '食品1501', '', '19', '847', '2');
INSERT INTO `sec_org` VALUES ('863', '食品1502', '', '19', '848', '2');
INSERT INTO `sec_org` VALUES ('864', '食品1503', '', '19', '849', '2');
INSERT INTO `sec_org` VALUES ('865', '食品1504', '', '19', '850', '2');
INSERT INTO `sec_org` VALUES ('866', '食品1505', '', '19', '851', '2');
INSERT INTO `sec_org` VALUES ('867', '食品1401', '', '19', '852', '2');
INSERT INTO `sec_org` VALUES ('868', '食品1402', '', '19', '853', '2');
INSERT INTO `sec_org` VALUES ('869', '食品1403', '', '19', '854', '2');
INSERT INTO `sec_org` VALUES ('870', '食品1404', '', '19', '855', '2');
INSERT INTO `sec_org` VALUES ('871', '食品1405', '', '19', '856', '2');
INSERT INTO `sec_org` VALUES ('872', '食品1301', '', '19', '857', '2');
INSERT INTO `sec_org` VALUES ('873', '食品1302', '', '19', '858', '2');
INSERT INTO `sec_org` VALUES ('874', '食品1303', '', '19', '859', '2');
INSERT INTO `sec_org` VALUES ('875', '食品1304', '', '19', '860', '2');
INSERT INTO `sec_org` VALUES ('876', '园艺1501', '', '19', '861', '2');
INSERT INTO `sec_org` VALUES ('877', '园艺1502', '', '19', '862', '2');
INSERT INTO `sec_org` VALUES ('878', '园艺1503', '', '19', '863', '2');
INSERT INTO `sec_org` VALUES ('879', '园艺1504', '', '19', '864', '2');
INSERT INTO `sec_org` VALUES ('880', '园艺1401', '', '19', '865', '2');
INSERT INTO `sec_org` VALUES ('881', '园艺1402', '', '19', '866', '2');
INSERT INTO `sec_org` VALUES ('882', '园艺1403', '', '19', '867', '2');
INSERT INTO `sec_org` VALUES ('883', '园艺1404', '', '19', '868', '2');
INSERT INTO `sec_org` VALUES ('884', '园艺1301', '', '19', '869', '2');
INSERT INTO `sec_org` VALUES ('885', '园艺1302', '', '19', '870', '2');
INSERT INTO `sec_org` VALUES ('886', '园艺1303', '', '19', '871', '2');
INSERT INTO `sec_org` VALUES ('887', '动科1501', '', '19', '872', '2');
INSERT INTO `sec_org` VALUES ('888', '动科1502', '', '19', '873', '2');
INSERT INTO `sec_org` VALUES ('889', '动科1503', '', '19', '874', '2');
INSERT INTO `sec_org` VALUES ('890', '动科1504', '', '19', '875', '2');
INSERT INTO `sec_org` VALUES ('891', '动科1401', '', '19', '876', '2');
INSERT INTO `sec_org` VALUES ('892', '动科1402', '', '19', '877', '2');
INSERT INTO `sec_org` VALUES ('893', '动科1403', '', '19', '878', '2');
INSERT INTO `sec_org` VALUES ('894', '动科1404', '', '19', '879', '2');
INSERT INTO `sec_org` VALUES ('895', '动科1301', '', '19', '880', '2');
INSERT INTO `sec_org` VALUES ('896', '动科1302', '', '19', '881', '2');
INSERT INTO `sec_org` VALUES ('897', '动科1303', '', '19', '882', '2');
INSERT INTO `sec_org` VALUES ('898', '生技1501', '', '19', '883', '2');
INSERT INTO `sec_org` VALUES ('899', '生技1502', '', '19', '884', '2');
INSERT INTO `sec_org` VALUES ('900', '生技1503', '', '19', '885', '2');
INSERT INTO `sec_org` VALUES ('901', '生技1401', '', '19', '886', '2');
INSERT INTO `sec_org` VALUES ('902', '生技1402', '', '19', '887', '2');
INSERT INTO `sec_org` VALUES ('903', '生技1301', '', '19', '888', '2');
INSERT INTO `sec_org` VALUES ('904', '生技1201', '', '19', '889', '2');
INSERT INTO `sec_org` VALUES ('905', '农学1501', '', '19', '890', '2');
INSERT INTO `sec_org` VALUES ('906', '农学1502', '', '19', '891', '2');
INSERT INTO `sec_org` VALUES ('907', '农学1503', '', '19', '892', '2');
INSERT INTO `sec_org` VALUES ('908', '农学1504', '', '19', '893', '2');
INSERT INTO `sec_org` VALUES ('909', '农学1401', '', '19', '894', '2');
INSERT INTO `sec_org` VALUES ('910', '农学1402', '', '19', '895', '2');
INSERT INTO `sec_org` VALUES ('911', '农学1403', '', '19', '896', '2');
INSERT INTO `sec_org` VALUES ('912', '农学1404', '', '19', '897', '2');
INSERT INTO `sec_org` VALUES ('913', '农学1301', '', '19', '898', '2');
INSERT INTO `sec_org` VALUES ('914', '农学1302', '', '19', '899', '2');
INSERT INTO `sec_org` VALUES ('915', '农学1303', '', '19', '900', '2');
INSERT INTO `sec_org` VALUES ('916', '材技1501', '', '18', '1', '2');
INSERT INTO `sec_org` VALUES ('917', '材技1502', '', '18', '2', '2');
INSERT INTO `sec_org` VALUES ('918', '材工1501', '', '18', '3', '2');
INSERT INTO `sec_org` VALUES ('919', '材工1502', '', '18', '4', '2');
INSERT INTO `sec_org` VALUES ('920', '材工1503', '', '18', '5', '2');
INSERT INTO `sec_org` VALUES ('921', '材工1504', '', '18', '6', '2');
INSERT INTO `sec_org` VALUES ('922', '材工1505', '', '18', '7', '2');
INSERT INTO `sec_org` VALUES ('923', '材工1506', '', '18', '8', '2');
INSERT INTO `sec_org` VALUES ('924', '材工1507', '', '18', '9', '2');
INSERT INTO `sec_org` VALUES ('925', '材工1508', '', '18', '10', '2');
INSERT INTO `sec_org` VALUES ('926', '材工1509', '', '18', '11', '2');
INSERT INTO `sec_org` VALUES ('927', '材工1510', '', '18', '12', '2');
INSERT INTO `sec_org` VALUES ('928', '材工1511', '', '18', '13', '2');
INSERT INTO `sec_org` VALUES ('929', '材工1512', '', '18', '14', '2');
INSERT INTO `sec_org` VALUES ('930', '应化1501', '', '18', '15', '2');
INSERT INTO `sec_org` VALUES ('931', '应化1502', '', '18', '16', '2');
INSERT INTO `sec_org` VALUES ('932', '应化1503', '', '18', '17', '2');
INSERT INTO `sec_org` VALUES ('933', '能化1501', '', '18', '18', '2');
INSERT INTO `sec_org` VALUES ('934', '能化1502', '', '18', '19', '2');
INSERT INTO `sec_org` VALUES ('935', '能化1503', '', '18', '20', '2');
INSERT INTO `sec_org` VALUES ('936', '应化1504', '', '18', '21', '2');
INSERT INTO `sec_org` VALUES ('937', '功材1501', '', '18', '22', '2');
INSERT INTO `sec_org` VALUES ('938', '功材1502', '', '18', '23', '2');
INSERT INTO `sec_org` VALUES ('939', '功材1503', '', '18', '24', '2');
INSERT INTO `sec_org` VALUES ('940', '材料1501', '', '18', '25', '2');
INSERT INTO `sec_org` VALUES ('941', '材料1502', '', '18', '26', '2');
INSERT INTO `sec_org` VALUES ('942', '材工1401', '', '18', '27', '2');
INSERT INTO `sec_org` VALUES ('943', '材工1402', '', '18', '28', '2');
INSERT INTO `sec_org` VALUES ('944', '材工1403', '', '18', '29', '2');
INSERT INTO `sec_org` VALUES ('945', '材工1404', '', '18', '30', '2');
INSERT INTO `sec_org` VALUES ('946', '材工1405', '', '18', '31', '2');
INSERT INTO `sec_org` VALUES ('947', '材工1406', '', '18', '32', '2');
INSERT INTO `sec_org` VALUES ('948', '材工1407', '', '18', '33', '2');
INSERT INTO `sec_org` VALUES ('949', '材工1408', '', '18', '34', '2');
INSERT INTO `sec_org` VALUES ('950', '应化1401', '', '18', '35', '2');
INSERT INTO `sec_org` VALUES ('951', '应化1402', '', '18', '36', '2');
INSERT INTO `sec_org` VALUES ('952', '应化1403', '', '18', '37', '2');
INSERT INTO `sec_org` VALUES ('953', '能化1401', '', '18', '38', '2');
INSERT INTO `sec_org` VALUES ('954', '能化1402', '', '18', '39', '2');
INSERT INTO `sec_org` VALUES ('955', '功材1401', '', '18', '40', '2');
INSERT INTO `sec_org` VALUES ('956', '功材1402', '', '18', '41', '2');
INSERT INTO `sec_org` VALUES ('957', '材料1401', '', '18', '42', '2');
INSERT INTO `sec_org` VALUES ('958', '材料1402', '', '18', '43', '2');
INSERT INTO `sec_org` VALUES ('959', '材技1401', '', '18', '44', '2');
INSERT INTO `sec_org` VALUES ('960', '材技1402', '', '18', '45', '2');
INSERT INTO `sec_org` VALUES ('961', '材工1301', '', '18', '46', '2');
INSERT INTO `sec_org` VALUES ('962', '材工1302', '', '18', '47', '2');
INSERT INTO `sec_org` VALUES ('963', '材工1303', '', '18', '48', '2');
INSERT INTO `sec_org` VALUES ('964', '材工1304', '', '18', '49', '2');
INSERT INTO `sec_org` VALUES ('965', '材工1305', '', '18', '50', '2');
INSERT INTO `sec_org` VALUES ('966', '材工1306', '', '18', '51', '2');
INSERT INTO `sec_org` VALUES ('967', '材工1307', '', '18', '52', '2');
INSERT INTO `sec_org` VALUES ('968', '应化1301', '', '18', '53', '2');
INSERT INTO `sec_org` VALUES ('969', '应化1302', '', '18', '54', '2');
INSERT INTO `sec_org` VALUES ('970', '功材1301', '', '18', '55', '2');
INSERT INTO `sec_org` VALUES ('971', '功材1302', '', '18', '56', '2');
INSERT INTO `sec_org` VALUES ('972', '功材1303', '', '18', '57', '2');
INSERT INTO `sec_org` VALUES ('973', '材料1301', '', '18', '58', '2');
INSERT INTO `sec_org` VALUES ('974', '材料1302', '', '18', '59', '2');
INSERT INTO `sec_org` VALUES ('975', '材工1201', '', '18', '60', '2');
INSERT INTO `sec_org` VALUES ('976', '材工1202', '', '18', '61', '2');
INSERT INTO `sec_org` VALUES ('977', '材工1203', '', '18', '62', '2');
INSERT INTO `sec_org` VALUES ('978', '材工1204', '', '18', '63', '2');
INSERT INTO `sec_org` VALUES ('979', '材工1205', '', '18', '64', '2');
INSERT INTO `sec_org` VALUES ('980', '材工1206', '', '18', '65', '2');
INSERT INTO `sec_org` VALUES ('981', '材工1207', '', '18', '66', '2');
INSERT INTO `sec_org` VALUES ('982', '材工1208', '', '18', '67', '2');
INSERT INTO `sec_org` VALUES ('983', '应化1201', '', '18', '68', '2');
INSERT INTO `sec_org` VALUES ('984', '应化1202', '', '18', '69', '2');
INSERT INTO `sec_org` VALUES ('985', '应化1203', '', '18', '70', '2');
INSERT INTO `sec_org` VALUES ('986', '功材1201', '', '18', '71', '2');
INSERT INTO `sec_org` VALUES ('987', '功材1202', '', '18', '72', '2');
INSERT INTO `sec_org` VALUES ('988', '材料1201', '', '18', '73', '2');
INSERT INTO `sec_org` VALUES ('989', '材料1202', '', '18', '74', '2');
INSERT INTO `sec_org` VALUES ('990', '化学硕1501', '', '18', '75', '2');
INSERT INTO `sec_org` VALUES ('991', '化学硕1401', '', '18', '76', '2');
INSERT INTO `sec_org` VALUES ('992', '化学硕1301', '', '18', '77', '2');
INSERT INTO `sec_org` VALUES ('993', '化工硕1501', '', '18', '78', '2');
INSERT INTO `sec_org` VALUES ('994', '化工硕1401', '', '18', '79', '2');
INSERT INTO `sec_org` VALUES ('995', '化工硕1301', '', '18', '80', '2');
INSERT INTO `sec_org` VALUES ('996', '化技硕1501', '', '18', '81', '2');
INSERT INTO `sec_org` VALUES ('997', '化技硕1401', '', '18', '82', '2');
INSERT INTO `sec_org` VALUES ('998', '化技硕1301', '', '18', '83', '2');
INSERT INTO `sec_org` VALUES ('999', '材工硕1501', '', '18', '84', '2');
INSERT INTO `sec_org` VALUES ('1000', '材工硕1401', '', '18', '85', '2');
INSERT INTO `sec_org` VALUES ('1001', '材工硕1301', '', '18', '86', '2');
INSERT INTO `sec_org` VALUES ('1002', '材料硕1501', '', '18', '87', '2');
INSERT INTO `sec_org` VALUES ('1003', '材料硕1401', '', '18', '88', '2');
INSERT INTO `sec_org` VALUES ('1004', '材料硕1301', '', '18', '89', '2');
INSERT INTO `sec_org` VALUES ('1005', '材工博1501', '', '18', '90', '2');
INSERT INTO `sec_org` VALUES ('1006', '材工博1401', '', '18', '91', '2');
INSERT INTO `sec_org` VALUES ('1007', '材工博1301', '', '18', '92', '2');
INSERT INTO `sec_org` VALUES ('1008', '安全1201', '', '17', '1', '2');
INSERT INTO `sec_org` VALUES ('1009', '安全1202', '', '17', '2', '2');
INSERT INTO `sec_org` VALUES ('1010', '安全1203', '', '17', '3', '2');
INSERT INTO `sec_org` VALUES ('1011', '采矿1201', '', '17', '4', '2');
INSERT INTO `sec_org` VALUES ('1012', '采矿1202', '', '17', '5', '2');
INSERT INTO `sec_org` VALUES ('1013', '采矿1203', '', '17', '6', '2');
INSERT INTO `sec_org` VALUES ('1014', '测绘1201', '', '17', '7', '2');
INSERT INTO `sec_org` VALUES ('1015', '测绘1202', '', '17', '8', '2');
INSERT INTO `sec_org` VALUES ('1016', '测绘1203', '', '17', '9', '2');
INSERT INTO `sec_org` VALUES ('1017', '地理信息1201', '', '17', '10', '2');
INSERT INTO `sec_org` VALUES ('1018', '地理信息1202', '', '17', '11', '2');
INSERT INTO `sec_org` VALUES ('1019', '地质1201', '', '17', '12', '2');
INSERT INTO `sec_org` VALUES ('1020', '地质1202', '', '17', '13', '2');
INSERT INTO `sec_org` VALUES ('1021', '地质1203', '', '17', '14', '2');
INSERT INTO `sec_org` VALUES ('1022', '环境1201', '', '17', '15', '2');
INSERT INTO `sec_org` VALUES ('1023', '环境1202', '', '17', '16', '2');
INSERT INTO `sec_org` VALUES ('1024', '交通1201', '', '17', '17', '2');
INSERT INTO `sec_org` VALUES ('1025', '交通1202', '', '17', '18', '2');
INSERT INTO `sec_org` VALUES ('1026', '交通1203', '', '17', '19', '2');
INSERT INTO `sec_org` VALUES ('1027', '矿物加工1201', '', '17', '20', '2');
INSERT INTO `sec_org` VALUES ('1028', '矿物加工1202', '', '17', '21', '2');
INSERT INTO `sec_org` VALUES ('1029', '安全1301', '', '17', '22', '2');
INSERT INTO `sec_org` VALUES ('1030', '安全1302', '', '17', '23', '2');
INSERT INTO `sec_org` VALUES ('1031', '安全1303', '', '17', '24', '2');
INSERT INTO `sec_org` VALUES ('1032', '采矿1301', '', '17', '25', '2');
INSERT INTO `sec_org` VALUES ('1033', '采矿1302', '', '17', '26', '2');
INSERT INTO `sec_org` VALUES ('1034', '采矿1303', '', '17', '27', '2');
INSERT INTO `sec_org` VALUES ('1035', '测绘1301', '', '17', '28', '2');
INSERT INTO `sec_org` VALUES ('1036', '测绘1302', '', '17', '29', '2');
INSERT INTO `sec_org` VALUES ('1037', '测绘1303', '', '17', '30', '2');
INSERT INTO `sec_org` VALUES ('1038', '地理信息1301', '', '17', '31', '2');
INSERT INTO `sec_org` VALUES ('1039', '地理信息1302', '', '17', '32', '2');
INSERT INTO `sec_org` VALUES ('1040', '地质1301', '', '17', '33', '2');
INSERT INTO `sec_org` VALUES ('1041', '地质1302', '', '17', '34', '2');
INSERT INTO `sec_org` VALUES ('1042', '环境1301', '', '17', '35', '2');
INSERT INTO `sec_org` VALUES ('1043', '环境1302', '', '17', '36', '2');
INSERT INTO `sec_org` VALUES ('1044', '交通1301', '', '17', '37', '2');
INSERT INTO `sec_org` VALUES ('1045', '交通1302', '', '17', '38', '2');
INSERT INTO `sec_org` VALUES ('1046', '交通1303', '', '17', '39', '2');
INSERT INTO `sec_org` VALUES ('1047', '矿物加工1301', '', '17', '40', '2');
INSERT INTO `sec_org` VALUES ('1048', '矿物加工1302', '', '17', '41', '2');
INSERT INTO `sec_org` VALUES ('1049', '安全1501', '', '17', '42', '2');
INSERT INTO `sec_org` VALUES ('1050', '安全1502', '', '17', '43', '2');
INSERT INTO `sec_org` VALUES ('1051', '安全1503', '', '17', '44', '2');
INSERT INTO `sec_org` VALUES ('1052', '安全1504', '', '17', '45', '2');
INSERT INTO `sec_org` VALUES ('1053', '采矿1501', '', '17', '46', '2');
INSERT INTO `sec_org` VALUES ('1054', '采矿1502', '', '17', '47', '2');
INSERT INTO `sec_org` VALUES ('1055', '采矿1503', '', '17', '48', '2');
INSERT INTO `sec_org` VALUES ('1056', '采矿1504', '', '17', '49', '2');
INSERT INTO `sec_org` VALUES ('1057', '测绘1501', '', '17', '50', '2');
INSERT INTO `sec_org` VALUES ('1058', '测绘1502', '', '17', '51', '2');
INSERT INTO `sec_org` VALUES ('1059', '测绘1503', '', '17', '52', '2');
INSERT INTO `sec_org` VALUES ('1060', '测绘1504', '', '17', '53', '2');
INSERT INTO `sec_org` VALUES ('1061', '地理信息1501', '', '17', '54', '2');
INSERT INTO `sec_org` VALUES ('1062', '地理信息1502', '', '17', '55', '2');
INSERT INTO `sec_org` VALUES ('1063', '地质1501', '', '17', '56', '2');
INSERT INTO `sec_org` VALUES ('1064', '地质1502', '', '17', '57', '2');
INSERT INTO `sec_org` VALUES ('1065', '地质1503', '', '17', '58', '2');
INSERT INTO `sec_org` VALUES ('1066', '地质1503', '', '17', '59', '2');
INSERT INTO `sec_org` VALUES ('1067', '地质1504', '', '17', '60', '2');
INSERT INTO `sec_org` VALUES ('1068', '环境1501', '', '17', '61', '2');
INSERT INTO `sec_org` VALUES ('1069', '环境1502', '', '17', '62', '2');
INSERT INTO `sec_org` VALUES ('1070', '环境1503', '', '17', '63', '2');
INSERT INTO `sec_org` VALUES ('1071', '环境1504', '', '17', '64', '2');
INSERT INTO `sec_org` VALUES ('1072', '交通1501', '', '17', '65', '2');
INSERT INTO `sec_org` VALUES ('1073', '交通1502', '', '17', '66', '2');
INSERT INTO `sec_org` VALUES ('1074', '交通1503', '', '17', '67', '2');
INSERT INTO `sec_org` VALUES ('1075', '交通1504', '', '17', '68', '2');
INSERT INTO `sec_org` VALUES ('1076', '矿物加工1501', '', '17', '69', '2');
INSERT INTO `sec_org` VALUES ('1077', '矿物加工1502', '', '17', '70', '2');
INSERT INTO `sec_org` VALUES ('1078', '矿物加工1503', '', '17', '71', '2');
INSERT INTO `sec_org` VALUES ('1080', '矿物加工1504', '', '17', '73', '2');
INSERT INTO `sec_org` VALUES ('1081', '安全1401', '', '17', '74', '2');
INSERT INTO `sec_org` VALUES ('1082', '安全1402', '', '17', '75', '2');
INSERT INTO `sec_org` VALUES ('1083', '安全1403', '', '17', '76', '2');
INSERT INTO `sec_org` VALUES ('1084', '采矿1401', '', '17', '77', '2');
INSERT INTO `sec_org` VALUES ('1085', '采矿1402', '', '17', '78', '2');
INSERT INTO `sec_org` VALUES ('1086', '采矿1403', '', '17', '79', '2');
INSERT INTO `sec_org` VALUES ('1087', '测绘1401', '', '17', '80', '2');
INSERT INTO `sec_org` VALUES ('1088', '测绘1402', '', '17', '81', '2');
INSERT INTO `sec_org` VALUES ('1089', '测绘1403', '', '17', '82', '2');
INSERT INTO `sec_org` VALUES ('1090', '地理信息1401', '', '17', '83', '2');
INSERT INTO `sec_org` VALUES ('1091', '地理信息1402', '', '17', '84', '2');
INSERT INTO `sec_org` VALUES ('1092', '地质1401', '', '17', '85', '2');
INSERT INTO `sec_org` VALUES ('1093', '地质1402', '', '17', '86', '2');
INSERT INTO `sec_org` VALUES ('1094', '地质1403', '', '17', '87', '2');
INSERT INTO `sec_org` VALUES ('1095', '环境1401', '', '17', '88', '2');
INSERT INTO `sec_org` VALUES ('1096', '环境1402', '', '17', '89', '2');
INSERT INTO `sec_org` VALUES ('1097', '交通1401', '', '17', '90', '2');
INSERT INTO `sec_org` VALUES ('1098', '交通1402', '', '17', '91', '2');
INSERT INTO `sec_org` VALUES ('1099', '交通1403', '', '17', '92', '2');
INSERT INTO `sec_org` VALUES ('1100', '矿物加工1401', '', '17', '93', '2');
INSERT INTO `sec_org` VALUES ('1101', '矿物加工1402', '', '17', '94', '2');
INSERT INTO `sec_org` VALUES ('1102', '地质研1501', '', '17', '95', '2');
INSERT INTO `sec_org` VALUES ('1103', '地质研1401', '', '17', '95', '2');
INSERT INTO `sec_org` VALUES ('1104', '地质研1301', '', '17', '96', '2');
INSERT INTO `sec_org` VALUES ('1105', '环境研1501', '', '17', '97', '2');
INSERT INTO `sec_org` VALUES ('1106', '环境研1401', '', '17', '98', '2');
INSERT INTO `sec_org` VALUES ('1107', '环境研1301', '', '17', '99', '2');
INSERT INTO `sec_org` VALUES ('1111', '生物学研13级', '', '19', '1089', '2');
INSERT INTO `sec_org` VALUES ('1112', '生物学研14级', '', '19', '1090', '2');
INSERT INTO `sec_org` VALUES ('1113', '生物学研15级', '', '19', '1091', '2');
INSERT INTO `sec_org` VALUES ('1114', '化学工程与技术研13级', '', '19', '1092', '2');
INSERT INTO `sec_org` VALUES ('1115', '化学工程与技术研14级', '', '19', '1093', '2');
INSERT INTO `sec_org` VALUES ('1116', '化学工程与技术研15级', '', '19', '1094', '2');
INSERT INTO `sec_org` VALUES ('1117', '应用化学研13级', '', '19', '1095', '2');
INSERT INTO `sec_org` VALUES ('1118', '应用化学研14级', '', '19', '1096', '2');
INSERT INTO `sec_org` VALUES ('1119', '应用化学研15级', '', '19', '1097', '2');
INSERT INTO `sec_org` VALUES ('1120', '环境工程研13级', '', '29', '1098', '2');
INSERT INTO `sec_org` VALUES ('1121', '控制工程研13级', '', '29', '1099', '2');
INSERT INTO `sec_org` VALUES ('1122', '物理学研13级', '', '29', '1100', '2');
INSERT INTO `sec_org` VALUES ('1123', '环境工程研14级', '', '29', '1101', '2');
INSERT INTO `sec_org` VALUES ('1124', '控制工程研14级', '', '29', '1102', '2');
INSERT INTO `sec_org` VALUES ('1125', '环境工程研15级', '', '29', '1103', '2');
INSERT INTO `sec_org` VALUES ('1126', '控制工程研15级', '', '29', '1104', '2');
INSERT INTO `sec_org` VALUES ('1127', '物理学研15级', '', '29', '1105', '2');
INSERT INTO `sec_org` VALUES ('1128', '材料工程研15级', '', '29', '1106', '2');
INSERT INTO `sec_org` VALUES ('1129', '安全工程研15级', '', '29', '1107', '2');
INSERT INTO `sec_org` VALUES ('1130', '信息与通信工程研15级', '', '29', '1108', '2');
INSERT INTO `sec_org` VALUES ('1131', '成型1201', '', '15', '1107', '2');
INSERT INTO `sec_org` VALUES ('1132', '成型1202', '', '15', '1108', '2');
INSERT INTO `sec_org` VALUES ('1133', '成型1203', '', '15', '1109', '2');
INSERT INTO `sec_org` VALUES ('1134', '成型1301', '', '15', '1110', '2');
INSERT INTO `sec_org` VALUES ('1135', '成型1302', '', '15', '1111', '2');
INSERT INTO `sec_org` VALUES ('1136', '成型1303', '', '15', '1112', '2');
INSERT INTO `sec_org` VALUES ('1137', '成型1401', '', '15', '1113', '2');
INSERT INTO `sec_org` VALUES ('1138', '成型1402', '', '15', '1114', '2');
INSERT INTO `sec_org` VALUES ('1139', '成型1403', '', '15', '1115', '2');
INSERT INTO `sec_org` VALUES ('1140', '成型1501', '', '15', '1116', '2');
INSERT INTO `sec_org` VALUES ('1141', '成型1502', '', '15', '1117', '2');
INSERT INTO `sec_org` VALUES ('1142', '成型1503', '', '15', '1118', '2');
INSERT INTO `sec_org` VALUES ('1143', '成型1504', '', '0', '1119', '1');
INSERT INTO `sec_org` VALUES ('1144', '过控1201', '', '15', '1120', '2');
INSERT INTO `sec_org` VALUES ('1145', '过控1202', '', '15', '1121', '2');
INSERT INTO `sec_org` VALUES ('1146', '过控1203', '', '15', '1122', '2');
INSERT INTO `sec_org` VALUES ('1147', '过控1301', '', '15', '1124', '2');
INSERT INTO `sec_org` VALUES ('1148', '过控1302', '', '15', '1125', '2');
INSERT INTO `sec_org` VALUES ('1149', '过控1401', '', '15', '1126', '2');
INSERT INTO `sec_org` VALUES ('1150', '过控1402', '', '15', '1127', '2');
INSERT INTO `sec_org` VALUES ('1151', '过控1501', '', '15', '1127', '2');
INSERT INTO `sec_org` VALUES ('1152', '过控1502', '', '15', '1128', '2');
INSERT INTO `sec_org` VALUES ('1153', '过控1503', '', '15', '1129', '2');
INSERT INTO `sec_org` VALUES ('1154', '工设1201', '', '15', '1130', '2');
INSERT INTO `sec_org` VALUES ('1155', '过控1202', '', '15', '1131', '2');
INSERT INTO `sec_org` VALUES ('1156', '工设1203', '', '15', '1132', '2');
INSERT INTO `sec_org` VALUES ('1157', '工设1301', '', '15', '1133', '2');
INSERT INTO `sec_org` VALUES ('1158', '工设1302', '', '15', '1134', '2');
INSERT INTO `sec_org` VALUES ('1159', '工设1303', '', '15', '1135', '2');
INSERT INTO `sec_org` VALUES ('1160', '工设1401', '', '15', '1136', '2');
INSERT INTO `sec_org` VALUES ('1161', '工设1402', '', '15', '1137', '2');
INSERT INTO `sec_org` VALUES ('1162', '工设1501', '', '15', '1138', '2');
INSERT INTO `sec_org` VALUES ('1163', '工设1502', '', '15', '1139', '2');
INSERT INTO `sec_org` VALUES ('1164', '工业1201', '', '15', '1140', '2');
INSERT INTO `sec_org` VALUES ('1165', '工业1202', '', '15', '1141', '2');
INSERT INTO `sec_org` VALUES ('1166', '工业1203', '', '15', '1142', '2');
INSERT INTO `sec_org` VALUES ('1167', '工业1301', '', '15', '1143', '2');
INSERT INTO `sec_org` VALUES ('1168', '工业1302', '', '15', '1144', '2');
INSERT INTO `sec_org` VALUES ('1169', '工业1303', '', '15', '1145', '2');
INSERT INTO `sec_org` VALUES ('1170', '工业1401', '', '15', '1146', '2');
INSERT INTO `sec_org` VALUES ('1171', '工业1402', '', '15', '1147', '2');
INSERT INTO `sec_org` VALUES ('1172', '工业1403', '', '15', '1148', '2');
INSERT INTO `sec_org` VALUES ('1173', '工业1501', '', '15', '1149', '2');
INSERT INTO `sec_org` VALUES ('1174', '工业1502', '', '15', '1150', '2');
INSERT INTO `sec_org` VALUES ('1175', '机械1201', '', '15', '1151', '2');
INSERT INTO `sec_org` VALUES ('1176', '机械1202', '', '15', '1152', '2');
INSERT INTO `sec_org` VALUES ('1177', '机械1203', '', '15', '1153', '2');
INSERT INTO `sec_org` VALUES ('1178', '机械1204', '', '15', '1154', '2');
INSERT INTO `sec_org` VALUES ('1179', '机械1205', '', '15', '1155', '2');
INSERT INTO `sec_org` VALUES ('1180', '机械1206', '', '15', '1156', '2');
INSERT INTO `sec_org` VALUES ('1181', '机械1207', '', '15', '1157', '2');
INSERT INTO `sec_org` VALUES ('1182', '机械1208', '', '15', '1158', '2');
INSERT INTO `sec_org` VALUES ('1183', '机械1209', '', '15', '1159', '2');
INSERT INTO `sec_org` VALUES ('1184', '机械1210', '', '15', '1160', '2');
INSERT INTO `sec_org` VALUES ('1185', '机械1211', '', '15', '1161', '2');
INSERT INTO `sec_org` VALUES ('1186', '机械1301', '', '15', '1162', '2');
INSERT INTO `sec_org` VALUES ('1187', '机械1302', '', '15', '1163', '2');
INSERT INTO `sec_org` VALUES ('1188', '机械1303', '', '15', '1164', '2');
INSERT INTO `sec_org` VALUES ('1189', '机械1304', '', '15', '1165', '2');
INSERT INTO `sec_org` VALUES ('1190', '机械1305', '', '15', '1166', '2');
INSERT INTO `sec_org` VALUES ('1191', '机械1306', '', '15', '1167', '2');
INSERT INTO `sec_org` VALUES ('1192', '机械1307', '', '15', '1168', '2');
INSERT INTO `sec_org` VALUES ('1193', '机械1308', '', '15', '1169', '2');
INSERT INTO `sec_org` VALUES ('1194', '机械1309', '', '15', '1170', '2');
INSERT INTO `sec_org` VALUES ('1195', '机械技能1301', '', '15', '1171', '2');
INSERT INTO `sec_org` VALUES ('1196', '机械技能1302', '', '15', '1172', '2');
INSERT INTO `sec_org` VALUES ('1197', '机械1401', '', '15', '1173', '2');
INSERT INTO `sec_org` VALUES ('1198', '机械1402', '', '15', '1174', '2');
INSERT INTO `sec_org` VALUES ('1199', '机械1403', '', '15', '1175', '2');
INSERT INTO `sec_org` VALUES ('1200', '机械1404', '', '15', '1176', '2');
INSERT INTO `sec_org` VALUES ('1201', '机械1405', '', '15', '1177', '2');
INSERT INTO `sec_org` VALUES ('1202', '机械1406', '', '15', '1178', '2');
INSERT INTO `sec_org` VALUES ('1203', '机械1407', '', '15', '1179', '2');
INSERT INTO `sec_org` VALUES ('1204', '机械1408', '', '15', '1180', '2');
INSERT INTO `sec_org` VALUES ('1205', '机械1409', '', '15', '1181', '2');
INSERT INTO `sec_org` VALUES ('1206', '机械1410', '', '15', '1182', '2');
INSERT INTO `sec_org` VALUES ('1207', '机械技能1401', '', '15', '1183', '2');
INSERT INTO `sec_org` VALUES ('1208', '机械技能1402', '', '15', '1184', '2');
INSERT INTO `sec_org` VALUES ('1209', '机械1501', '', '15', '1185', '2');
INSERT INTO `sec_org` VALUES ('1210', '机械1502', '', '15', '1186', '2');
INSERT INTO `sec_org` VALUES ('1211', '机械1503', '', '15', '1187', '2');
INSERT INTO `sec_org` VALUES ('1212', '机械1504', '', '15', '1188', '2');
INSERT INTO `sec_org` VALUES ('1213', '机械1505', '', '15', '1189', '2');
INSERT INTO `sec_org` VALUES ('1214', '机械1506', '', '15', '1190', '2');
INSERT INTO `sec_org` VALUES ('1215', '机械1507', '', '15', '1191', '2');
INSERT INTO `sec_org` VALUES ('1216', '机械1508', '', '15', '1192', '2');
INSERT INTO `sec_org` VALUES ('1217', '机械1509', '', '15', '1193', '2');
INSERT INTO `sec_org` VALUES ('1218', '机械1510', '', '15', '1194', '2');
INSERT INTO `sec_org` VALUES ('1219', '机械1511', '', '15', '1195', '2');
INSERT INTO `sec_org` VALUES ('1220', '机械1512', '', '15', '1196', '2');
INSERT INTO `sec_org` VALUES ('1221', '机械1513', '', '15', '1197', '2');
INSERT INTO `sec_org` VALUES ('1222', '机械1514', '', '15', '1198', '2');
INSERT INTO `sec_org` VALUES ('1223', '机械1515', '', '15', '1199', '2');
INSERT INTO `sec_org` VALUES ('1224', '机械技能1501', '', '15', '1200', '2');
INSERT INTO `sec_org` VALUES ('1225', '机械技能1502', '', '15', '1201', '2');
INSERT INTO `sec_org` VALUES ('1226', '四川省非金属复合与功能材料重点实验室', '', '0', '1198', '1');
INSERT INTO `sec_org` VALUES ('1227', '研2013', '', '15', '1199', '2');
INSERT INTO `sec_org` VALUES ('1228', '研2014', '', '15', '1200', '2');
INSERT INTO `sec_org` VALUES ('1229', '研2015', '', '15', '1201', '2');
INSERT INTO `sec_org` VALUES ('1230', '安全研1501', '', '17', '100', '2');
INSERT INTO `sec_org` VALUES ('1231', '安全研1401', '', '17', '101', '2');
INSERT INTO `sec_org` VALUES ('1232', '安全研1301', '', '17', '102', '2');
INSERT INTO `sec_org` VALUES ('1233', '安全与科学研1501', '', '17', '103', '2');
INSERT INTO `sec_org` VALUES ('1234', '安全与科学研1401', '', '17', '104', '2');
INSERT INTO `sec_org` VALUES ('1235', '安全与科学研1301', '', '17', '105', '2');
INSERT INTO `sec_org` VALUES ('1236', '环境科学研1501', '', '17', '106', '2');
INSERT INTO `sec_org` VALUES ('1237', '环境科学研1401', '', '17', '107', '2');
INSERT INTO `sec_org` VALUES ('1238', '环境科学研1301', '', '17', '108', '2');
INSERT INTO `sec_org` VALUES ('1239', '矿产普查与勘探研1501', '', '17', '109', '2');
INSERT INTO `sec_org` VALUES ('1240', '矿产普查与勘探研1401', '', '17', '110', '2');
INSERT INTO `sec_org` VALUES ('1241', '矿产普查与勘探研1301', '', '17', '111', '2');
INSERT INTO `sec_org` VALUES ('1242', '矿业研1501', '', '17', '112', '2');
INSERT INTO `sec_org` VALUES ('1243', '矿业研1401', '', '17', '113', '2');
INSERT INTO `sec_org` VALUES ('1244', '矿业研1301', '', '17', '114', '2');
INSERT INTO `sec_org` VALUES ('1245', '生技1202', '', '19', '1217', '2');
INSERT INTO `sec_org` VALUES ('1246', '国贸1101', '', '21', '1219', '2');
INSERT INTO `sec_org` VALUES ('1247', '计划财务处', '', '0', '1219', '1');
INSERT INTO `sec_org` VALUES ('1248', '校外单位指导教师', '', '0', '1220', '1');
INSERT INTO `sec_org` VALUES ('1249', '土木1201', '', '16', '1220', '2');
INSERT INTO `sec_org` VALUES ('1250', '土木1202', '', '16', '1221', '2');
INSERT INTO `sec_org` VALUES ('1251', '土木1203', '', '16', '1222', '2');
INSERT INTO `sec_org` VALUES ('1252', '土木1205', '', '16', '1224', '2');
INSERT INTO `sec_org` VALUES ('1253', '土木1206', '', '16', '1225', '2');
INSERT INTO `sec_org` VALUES ('1254', '土木1207', '', '16', '1226', '2');
INSERT INTO `sec_org` VALUES ('1255', '土木1207', '', '16', '1226', '2');
INSERT INTO `sec_org` VALUES ('1256', '土木1208', '', '16', '1227', '2');
INSERT INTO `sec_org` VALUES ('1257', '土木1209', '', '16', '1228', '2');
INSERT INTO `sec_org` VALUES ('1258', '校团委', '', '0', '28', '1');
INSERT INTO `sec_org` VALUES ('1259', '发展规划处', '', '0', '29', '1');
INSERT INTO `sec_org` VALUES ('1260', '土木1204', '', '16', '1223', '2');
INSERT INTO `sec_org` VALUES ('1261', '制药1204', '', '19', '1232', '2');

-- ----------------------------
-- Table structure for sec_resource
-- ----------------------------
DROP TABLE IF EXISTS `sec_resource`;
CREATE TABLE `sec_resource` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `source` varchar(200) DEFAULT NULL,
  `menu` mediumint(8) unsigned DEFAULT NULL,
  `displayorder` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK_RESOURCE_MENU` (`menu`),
  CONSTRAINT `sec_resource_ibfk_1` FOREIGN KEY (`menu`) REFERENCES `sec_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8 COMMENT='具体资源';

-- ----------------------------
-- Records of sec_resource
-- ----------------------------
INSERT INTO `sec_resource` VALUES ('401', '用户查询', '/security/user', '41', '0');
INSERT INTO `sec_resource` VALUES ('402', '部门查询', '/security/org', '42', '0');
INSERT INTO `sec_resource` VALUES ('403', '角色查询', '/security/role', '43', '0');
INSERT INTO `sec_resource` VALUES ('404', '权限查询', '/security/authority', '44', '0');
INSERT INTO `sec_resource` VALUES ('405', '资源查询', '/security/resource', '45', '0');
INSERT INTO `sec_resource` VALUES ('406', '菜单查询', '/security/menu', '46', '0');
INSERT INTO `sec_resource` VALUES ('411', '用户查看', '/security/user/view/**', null, '0');
INSERT INTO `sec_resource` VALUES ('412', '部门查看', '/security/org/view/**', null, '0');
INSERT INTO `sec_resource` VALUES ('413', '角色查看', '/security/role/view/**', null, '0');
INSERT INTO `sec_resource` VALUES ('414', '权限查看', '/security/authority/view/**', null, '0');
INSERT INTO `sec_resource` VALUES ('415', '资源查看', '/security/resource/view/**', null, '0');
INSERT INTO `sec_resource` VALUES ('416', '菜单查看', '/security/menu/view/**', null, '0');
INSERT INTO `sec_resource` VALUES ('421', '用户删除', '/security/user/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('422', '部门删除', '/security/org/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('423', '角色删除', '/security/role/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('424', '权限删除', '/security/authority/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('425', '资源删除', '/security/resource/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('426', '菜单删除', '/security/menu/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('431', '用户编辑', '/security/user/update/**;/security/user/save/**;/security/user/add/**;/security/user/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('432', '部门编辑', '/security/org/update/**;/security/org/save/**;/security/org/add/**;/security/org/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('433', '角色编辑', '/security/role/update/**;/security/role/save/**;/security/role/add/**;/security/role/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('434', '权限编辑', '/security/authority/update/**;/security/authority/save/**;/security/authority/add/**;/security/authority/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('435', '资源编辑', '/security/resource/update/**;/security/resource/save/**;/security/resource/add/**;/security/resource/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('436', '菜单编辑', '/security/menu/update/**;/security/menu/save/**;/security/menu/add/**;/security/menu/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('445', '新闻分类', '/fish/newsCategory', '57', '0');
INSERT INTO `sec_resource` VALUES ('447', '查看新闻', '/fish/news', '58', '0');
INSERT INTO `sec_resource` VALUES ('450', '评论查看', '/oa/newscomment/viewAll/**;/oa/newscomment/add/**;/oa/newscomment/save/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('451', '评论管理', '/oa/newscomment/edit/**;/oa/newscomment/delete/**;/oa/newscomment/update/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('452', 'M新闻', '/api/news/**;/api/newscomment/**', null, '0');
INSERT INTO `sec_resource` VALUES ('453', '个人信息编辑', '/oa/profile/update/**', null, '0');
INSERT INTO `sec_resource` VALUES ('457', '密码更新', '/oa/password/update/**', null, '0');
INSERT INTO `sec_resource` VALUES ('479', 'ranklist', '/tech/rank', '62', '0');
INSERT INTO `sec_resource` VALUES ('480', 'levellist', '/tech/level', '63', '0');
INSERT INTO `sec_resource` VALUES ('481', 'rankManage', '/tech/rank/add/**;/tech/rank/save/**;/tech/rank/delete/**;/tech/rank/update/**;/tech/rank/edit/**', null, '0');
INSERT INTO `sec_resource` VALUES ('482', 'levelranklist', '/tech/levelrank', '64', '0');
INSERT INTO `sec_resource` VALUES ('483', 'gameType', '/tech/game', '65', '0');
INSERT INTO `sec_resource` VALUES ('485', '学院赛前申请', '/tech/recordPreApply', '67', '0');
INSERT INTO `sec_resource` VALUES ('493', '赛前已审核', '/tech/recordPreVerify', '71', '0');
INSERT INTO `sec_resource` VALUES ('494', '赛前待审核', '/tech/recordPreVerify/waitIndex', '70', '0');
INSERT INTO `sec_resource` VALUES ('495', '学生获奖录入', '/tech/recordwin', '73', '0');
INSERT INTO `sec_resource` VALUES ('496', 'newsView', 'tech/news/add/**;tech/news/edit/**;tech/news/view/**;tech/news/save/**;tech/news/update/**;tech/news/delete/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('499', '比赛类型管理', '/tech/game/add/**;/tech/game/save/**;/tech/game/edit/**;/tech/game/update/**;/tech/game/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('500', '比赛类型联动', '/tech/game/getGame/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('501', '待学院审核', '/tech/winverify/waitAcademyIndex', '75', '0');
INSERT INTO `sec_resource` VALUES ('502', '学院审核通过', '/tech/winverify/passAcademyIndex', '76', '0');
INSERT INTO `sec_resource` VALUES ('503', '待学校审核', '/tech/winverify/waitSchoolIndex', '77', '0');
INSERT INTO `sec_resource` VALUES ('504', '学校审核通过', '/tech/winverify/passSchoolIndex', '78', '0');
INSERT INTO `sec_resource` VALUES ('505', '二级部门', '/security/org/getGradeByAcademy/**', null, '0');
INSERT INTO `sec_resource` VALUES ('506', '学校查看等待学院审核', '/tech/winverify/schoolfindWaitAcademyIndex', '79', '0');
INSERT INTO `sec_resource` VALUES ('507', 'announcement', '/fish/announcement', '80', '0');
INSERT INTO `sec_resource` VALUES ('508', 'Newscategary', '/fish/newsCategory/add/**;/fish/newsCategory/save/**;/fish/newsCategory/update/**;/fish/newsCategory/delete/**;/fish/newsCategory/edit/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('509', '新闻举报', '/fish/newsReport', '81', '0');
INSERT INTO `sec_resource` VALUES ('510', 'NewsReport', '/fish/newsReport/view/**;/fish/newsReport/delete/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('511', '新闻编辑', '/fish/news/review/**;/fish/news/update/**;/fish/news/edit/**;/fish/news/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('512', '新闻审核', '/fish/newsCheck/waitingCheck', '83', '0');
INSERT INTO `sec_resource` VALUES ('513', 'NewsCheck', '/fish/newsCheck/doCheck/**;/fish/news/review/**;/fish/news/update/**;/fish/news/edit/**;/fish/news/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('514', '已审核新闻', '/fish/newsCheck/alreadyCheck', '84', '0');
INSERT INTO `sec_resource` VALUES ('515', '渔获列表', '/fish/harvest', '86', '0');
INSERT INTO `sec_resource` VALUES ('516', '渔获管理', '/fish/harvest/view/**;/fish/harvest/enable/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('517', '限时特惠时间', '/fish/fubi/limittime', '88', '0');
INSERT INTO `sec_resource` VALUES ('518', '限时特惠时间资源', '/fish/fubi/limittime/add/**;/fish/fubi/limittime/delete/**;/fish/fubi/limittime/edit/**;/fish/fubi/limittime/update/**;/fish/fubi/limittime/save/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('519', '限时特惠商品', '/fish/fubi/limitgoods', '89', '0');
INSERT INTO `sec_resource` VALUES ('520', '限时特惠商品资源', '/fish/fubi/limitgoods/add/**;/fish/fubi/limitgoods/view/**;/fish/fubi/limitgoods/delete/**;/fish/fubi/limitgoods/edit/**;/fish/fubi/limitgoods/update/**;/fish/fubi/limitgoods/save/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('521', '整点秒杀时间', '/fish/fubi/seckilltime', '90', '0');
INSERT INTO `sec_resource` VALUES ('522', '整点秒杀时间资源', '/fish/fubi/seckilltime/add/**;/fish/fubi/seckilltime/delete/**;/fish/fubi/seckilltime/save/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('523', '整点秒杀商品', '/fish/fubi/seckillgoods', '91', '0');
INSERT INTO `sec_resource` VALUES ('524', '整点秒杀商品资源', '/fish/fubi/seckillgoods/add/**;/fish/fubi/seckillgoods/delete/**;/fish/fubi/seckillgoods/update/**;/fish/fubi/seckillgoods/save/**;/fish/fubi/seckillgoods/edit/**;/fish/fubi/seckillgoods/view/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('525', '富币类型', '/fish/fubi/type', '92', '0');
INSERT INTO `sec_resource` VALUES ('526', '富币类型资源', '/fish/fubi/type/add/**;/fish/fubi/type/save/**;/fish/fubi/type/update/**;/fish/fubi/type/edit/**;/fish/fubi/type/delete/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('527', '富币Banner', '/fish/fubi/banner', '93', '0');
INSERT INTO `sec_resource` VALUES ('528', '富币Banner资源', '/fish/fubi/banner/add/**;/fish/fubi/banner/save/**;/fish/fubi/banner/edit/**;/fish/fubi/banner/update/**;/fish/fubi/banner/delete/**;/fish/fubi/banner/view/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('529', '富币商城订单', '/fish/fubi/order', '94', '0');
INSERT INTO `sec_resource` VALUES ('530', 'fubiOrder', '/fish/fubi/order/sendOut/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('531', '商品类别管理', '/fish/goodsCategory', '96', '0');
INSERT INTO `sec_resource` VALUES ('532', '关键属性', '/fish/keytAttr', '97', '0');
INSERT INTO `sec_resource` VALUES ('533', '非关键属性', '/fish/nonkeytAttr', '98', '0');
INSERT INTO `sec_resource` VALUES ('534', '商品管理', '/fish/goods', '99', '0');
INSERT INTO `sec_resource` VALUES ('535', '待审核商品', '/fish/goodsCheck/waitingCheckList', '100', '0');
INSERT INTO `sec_resource` VALUES ('536', '已审核商品', '/fish/goodsCheck/alreadyCheckedList', '101', '0');
INSERT INTO `sec_resource` VALUES ('537', '订单管理', '/fish/order/', '102', '0');
INSERT INTO `sec_resource` VALUES ('538', '添加新闻', '/fish/news/add/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('540', '编辑新闻', '/fish/news/edit/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('541', '新闻更新', '/fish/news/update/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('542', '删除新闻', '/fish/news/delete/**', null, '0');
INSERT INTO `sec_resource` VALUES ('543', '保存新闻', '/fish/news/save/**;', null, '0');
INSERT INTO `sec_resource` VALUES ('544', '团购管理', '/fish/teamBuy', '104', '0');

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL COMMENT '角色名字：用英文',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述：用中文',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sec_role
-- ----------------------------
INSERT INTO `sec_role` VALUES ('1', 'Admin', 'admin');
INSERT INTO `sec_role` VALUES ('2', 'nomaluser', 'nomal user');
INSERT INTO `sec_role` VALUES ('4', '学生', '录入信息，查询信息');
INSERT INTO `sec_role` VALUES ('5', '老师', '录入，添加科技活动，查询');
INSERT INTO `sec_role` VALUES ('8', '商家', '商家');
INSERT INTO `sec_role` VALUES ('9', '新闻添加', '添加新闻');
INSERT INTO `sec_role` VALUES ('10', '新闻审核', '审核新闻');
INSERT INTO `sec_role` VALUES ('11', '渔获审核', '对渔获管理');
INSERT INTO `sec_role` VALUES ('12', '商品审核', '商品审核');

-- ----------------------------
-- Table structure for sec_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_authority`;
CREATE TABLE `sec_role_authority` (
  `role_id` mediumint(8) unsigned NOT NULL,
  `authority_id` mediumint(8) unsigned NOT NULL,
  KEY `FK_ROLE_AUTHORITY1` (`authority_id`),
  KEY `FK_ROLE_AUTHORITY2` (`role_id`),
  CONSTRAINT `sec_role_authority_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `sec_authority` (`id`),
  CONSTRAINT `sec_role_authority_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色对应权限';

-- ----------------------------
-- Records of sec_role_authority
-- ----------------------------
INSERT INTO `sec_role_authority` VALUES ('2', '451');
INSERT INTO `sec_role_authority` VALUES ('2', '448');
INSERT INTO `sec_role_authority` VALUES ('5', '473');
INSERT INTO `sec_role_authority` VALUES ('4', '473');
INSERT INTO `sec_role_authority` VALUES ('4', '472');
INSERT INTO `sec_role_authority` VALUES ('4', '470');
INSERT INTO `sec_role_authority` VALUES ('10', '476');
INSERT INTO `sec_role_authority` VALUES ('11', '477');
INSERT INTO `sec_role_authority` VALUES ('9', '490');
INSERT INTO `sec_role_authority` VALUES ('1', '491');
INSERT INTO `sec_role_authority` VALUES ('1', '489');
INSERT INTO `sec_role_authority` VALUES ('1', '488');
INSERT INTO `sec_role_authority` VALUES ('1', '487');
INSERT INTO `sec_role_authority` VALUES ('1', '486');
INSERT INTO `sec_role_authority` VALUES ('1', '485');
INSERT INTO `sec_role_authority` VALUES ('1', '484');
INSERT INTO `sec_role_authority` VALUES ('1', '483');
INSERT INTO `sec_role_authority` VALUES ('1', '482');
INSERT INTO `sec_role_authority` VALUES ('1', '481');
INSERT INTO `sec_role_authority` VALUES ('1', '480');
INSERT INTO `sec_role_authority` VALUES ('1', '479');
INSERT INTO `sec_role_authority` VALUES ('1', '478');
INSERT INTO `sec_role_authority` VALUES ('1', '477');
INSERT INTO `sec_role_authority` VALUES ('1', '476');
INSERT INTO `sec_role_authority` VALUES ('1', '475');
INSERT INTO `sec_role_authority` VALUES ('1', '471');
INSERT INTO `sec_role_authority` VALUES ('1', '467');
INSERT INTO `sec_role_authority` VALUES ('1', '463');
INSERT INTO `sec_role_authority` VALUES ('1', '451');
INSERT INTO `sec_role_authority` VALUES ('1', '448');
INSERT INTO `sec_role_authority` VALUES ('1', '444');
INSERT INTO `sec_role_authority` VALUES ('1', '436');
INSERT INTO `sec_role_authority` VALUES ('1', '435');
INSERT INTO `sec_role_authority` VALUES ('1', '434');
INSERT INTO `sec_role_authority` VALUES ('1', '433');
INSERT INTO `sec_role_authority` VALUES ('1', '432');
INSERT INTO `sec_role_authority` VALUES ('1', '431');
INSERT INTO `sec_role_authority` VALUES ('1', '426');
INSERT INTO `sec_role_authority` VALUES ('1', '425');
INSERT INTO `sec_role_authority` VALUES ('1', '424');
INSERT INTO `sec_role_authority` VALUES ('1', '423');
INSERT INTO `sec_role_authority` VALUES ('1', '422');
INSERT INTO `sec_role_authority` VALUES ('1', '421');
INSERT INTO `sec_role_authority` VALUES ('1', '416');
INSERT INTO `sec_role_authority` VALUES ('1', '415');
INSERT INTO `sec_role_authority` VALUES ('1', '414');
INSERT INTO `sec_role_authority` VALUES ('1', '413');
INSERT INTO `sec_role_authority` VALUES ('1', '412');
INSERT INTO `sec_role_authority` VALUES ('1', '411');
INSERT INTO `sec_role_authority` VALUES ('1', '406');
INSERT INTO `sec_role_authority` VALUES ('1', '405');
INSERT INTO `sec_role_authority` VALUES ('1', '404');
INSERT INTO `sec_role_authority` VALUES ('1', '403');
INSERT INTO `sec_role_authority` VALUES ('1', '402');
INSERT INTO `sec_role_authority` VALUES ('1', '401');
INSERT INTO `sec_role_authority` VALUES ('8', '491');
INSERT INTO `sec_role_authority` VALUES ('8', '489');
INSERT INTO `sec_role_authority` VALUES ('8', '487');
INSERT INTO `sec_role_authority` VALUES ('8', '481');
INSERT INTO `sec_role_authority` VALUES ('8', '479');
INSERT INTO `sec_role_authority` VALUES ('12', '488');
INSERT INTO `sec_role_authority` VALUES ('12', '486');
INSERT INTO `sec_role_authority` VALUES ('12', '485');
INSERT INTO `sec_role_authority` VALUES ('12', '480');
INSERT INTO `sec_role_authority` VALUES ('12', '478');

-- ----------------------------
-- Table structure for sec_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_user`;
CREATE TABLE `sec_role_user` (
  `user_id` mediumint(8) unsigned NOT NULL,
  `role_id` mediumint(8) unsigned NOT NULL,
  KEY `FK_ROLE_USER1` (`user_id`),
  KEY `FK_ROLE_USER2` (`role_id`),
  CONSTRAINT `sec_role_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`),
  CONSTRAINT `sec_role_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户所对应的角色';

-- ----------------------------
-- Records of sec_role_user
-- ----------------------------
INSERT INTO `sec_role_user` VALUES ('1', '1');
INSERT INTO `sec_role_user` VALUES ('3', '2');
INSERT INTO `sec_role_user` VALUES ('11', '2');
INSERT INTO `sec_role_user` VALUES ('19', '11');
INSERT INTO `sec_role_user` VALUES ('20', '2');
INSERT INTO `sec_role_user` VALUES ('21', '2');
INSERT INTO `sec_role_user` VALUES ('15', '9');
INSERT INTO `sec_role_user` VALUES ('22', '8');
INSERT INTO `sec_role_user` VALUES ('23', '12');
INSERT INTO `sec_role_user` VALUES ('16', '10');
INSERT INTO `sec_role_user` VALUES ('16', '11');
INSERT INTO `sec_role_user` VALUES ('26', '2');
INSERT INTO `sec_role_user` VALUES ('27', '2');

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `grouptype` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0：其他, 1:普通用户，2:经销商,3:商家',
  `username` varchar(32) NOT NULL COMMENT '手机',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '加密密码',
  `plainPassword` varchar(64) NOT NULL DEFAULT '' COMMENT '未加密密码',
  `org` mediumint(8) NOT NULL DEFAULT '0',
  `age` smallint(6) unsigned NOT NULL DEFAULT '0' COMMENT '年龄',
  `telephone` varchar(255) NOT NULL DEFAULT '' COMMENT '手机号',
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:未设置, 1:男 2：女  3：保密',
  `identify` char(18) NOT NULL DEFAULT '' COMMENT '身份证',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `qqOpenid` varchar(255) NOT NULL DEFAULT '',
  `alipay` varchar(255) NOT NULL DEFAULT '' COMMENT '支付宝帐号',
  `tenpay` varchar(255) NOT NULL DEFAULT '' COMMENT '财付通帐号',
  `wepay` varchar(255) NOT NULL DEFAULT '' COMMENT '微信支付',
  `verify` varchar(1024) NOT NULL DEFAULT '' COMMENT '认证身份',
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁止用户',
  `salt` varchar(255) NOT NULL DEFAULT '',
  `fromwhere` tinyint(3) NOT NULL DEFAULT '0' COMMENT '用户登录方式:0：WEB,1：andorid客户端，2：iphone客户端',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `logintime` datetime DEFAULT NULL COMMENT '登录时间',
  `online` tinyint(3) NOT NULL DEFAULT '0',
  `address` varchar(64) NOT NULL DEFAULT '' COMMENT '所在地',
  `intrest` varchar(1024) NOT NULL DEFAULT '' COMMENT '兴趣爱好',
  `birthday` date DEFAULT NULL,
  `weiboToken` varchar(255) NOT NULL DEFAULT '',
  `wechatOpenid` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='通用用户表';

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES ('1', '0', 'admin', '系统管理员', '管理员', '', 'bd48158b988549c58e613aed13b592872e9a6bc5', 'admin', '0', '100', '', '1', '510228196325146579', 'lkjfdjsfjdifjd', '', '', '', '', '', '1', 'bc99db56c2c46c52', '0', '2015-12-31 09:51:24', '2016-01-20 16:43:39', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('3', '1', '158827810321', 'woshiceshi', 'nico', '', 'bd48158b988549c58e613aed13b592872e9a6bc5', 'admin', '0', '0', '', '0', '510228196325123213', 'upload/avatar/1452650865791.png', '', '', '', '', '', '1', 'bc99db56c2c46c52', '0', '2016-01-11 09:51:21', null, '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('11', '1', '13990122270', '', '', '', '08da15c70e2bebc1cb3cb89e1cb70e840c3afea1', '111111', '0', '0', '', '0', '', '', '', '', '', '', '', '1', '750fca38c5cc1b46', '0', '2016-01-11 17:39:45', '2016-01-11 17:57:18', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('15', '3', 'newsadd', 'newsadd', 'newsadd', '111111', '951ec9efebbb5914fd100bcfacb963856c493d1e', '111111', '0', '0', '', '0', '', '', '', '', '', '', '', '1', 'ff8611b71949527f', '0', '2016-01-12 09:31:24', '2016-01-20 16:08:46', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('16', '0', 'newscheck', 'newscheck', 'newscheck', '111111v', 'c401a4d3a8c68658c851d1d03992952b480133da', '111111', '0', '0', '', '0', '', '', '', '', '', '', '', '1', '440ea5fdd03251f3', '0', '2016-01-12 09:32:46', '2016-01-20 16:01:22', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('19', '0', 'harvest', 'harvest', 'harvest', 'harvest', '75d1c361ccad03aa47f00887d048111751feda92', '111111', '0', '0', '', '0', '', '', '', '', '', '', '', '1', '534d2e95bd7d5547', '0', '2016-01-12 14:45:48', '2016-01-12 14:46:33', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('20', '1', '15182471312', '', '', '', '876fe14e7e1442ac9ee9715be2b9e1e6550b9ee9', '123', '0', '0', '', '0', '', '', '', '', '', '', '', '1', '7dc56ee57cf1dd0e', '0', '2016-01-13 11:17:22', null, '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('21', '1', '15882781032', '', 'nico', '', 'c056083e1e7b11b1e232f2befb2b7e0c66bf1602', '123', '0', '0', '', '1', '', 'upload/avatar/1453101372821.png', 'EF0A458DC48FFD9F1B92DCF6B5C8EB3B', '', '', '', '', '1', '06a6024af5706870', '0', '2016-01-13 11:27:22', '2016-01-19 19:45:00', '1', 'asdad', 'qwqwq', '2012-01-23', '', '');
INSERT INTO `sec_user` VALUES ('22', '1', '13300000000', '商家测试账号~', '富士旗舰店', 'ltinghuang@163.com', '89d46d23c76273fa205d88ef1994ac459782227f', '123456', '0', '0', '', '1', '', '', '', '', '', '', '', '1', '38bbac47bb8d3b3f', '0', '2016-01-15 09:50:05', '2016-01-20 16:11:38', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('23', '1', '12200000000', '我是商城管理员', '商城管理员', '1234', '3099e5ef9e6cd99f70fcbef05d3c743df63b2a10', '123456', '0', '0', '', '1', '', '', '', '', '', '', '', '1', 'bb6facc789efa5df', '0', '2016-01-15 09:51:24', '2016-01-15 09:54:46', '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('26', '1', '18181743097', '', '', '6995673@qq.com', '8c9b3851c0279b0756976f4e2c53be314164f076', '666666', '0', '0', '', '0', '', '', '', '', '', '', '', '1', 'ae6c0891673154d0', '0', '2016-01-18 15:34:58', null, '1', '', '', null, '', '');
INSERT INTO `sec_user` VALUES ('27', '1', '18144253098', '', '', '475061976@qq.com', 'e4533e6b393d15535bcdfe3290b5d65bc1e44e06', '123456', '0', '0', '', '0', '', '', '', '', '', '', '', '1', 'd5f070990ab9dd14', '0', '2016-01-18 15:43:58', null, '1', '', '', null, '', '');
