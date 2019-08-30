/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : web_demo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-25 14:00:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `SYS_USER_ID` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门负责人 外键：用户表 SYS_USER-->ID\r\n            ',
  `NAME` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `CHARGENAME` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '负责人姓名',
  `PHONE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门电话号码',
  `CODE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编码',
  `CDESC` text COLLATE utf8_unicode_ci COMMENT '部门描述',
  `CREATEUSER` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `CREATEDATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATEUSER` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '2019-06-21 14:29:47', null, null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `PARENT_ID` char(32) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '父ID //第一级菜单',
  `NAME` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `URL` longtext COLLATE utf8_unicode_ci COMMENT '权限url',
  `ICON` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图标',
  `SEQ` int(2) DEFAULT '0' COMMENT '排序',
  `IS_MENU` int(1) NOT NULL DEFAULT '0' COMMENT '0：按钮    1：菜单',
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `CREATEUSER` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `CREATEDATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATEUSER` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('0', '', '菜单1', '/xxx/xxx', null, '1', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('1', '0', 'add', '/xxx/xxx', null, '1', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('100', '', '菜单2', '/xxx/xxx', null, '2', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('101', '100', 'add', '/xxx/xxx', null, '1', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('102', '100', 'delete', '/xxx/xxx', null, '2', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('103', '100', 'update', '/xxx/xxx', null, '3', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('104', '100', 'select', '/xxx/xxx', null, '4', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('2', '0', 'delete', '/xxx/xxx', null, '2', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('200', '', '菜单3', '/xxx/xxx', null, '3', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('3', '0', 'update', '/xxx/xxx', null, '3', '1', null, '1111', '2019-06-20 10:22:42', null, null);
INSERT INTO `sys_permission` VALUES ('4', '0', 'select', '/xxx/xxx', null, '4', '1', null, '1111', '2019-06-20 10:22:42', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `NAME` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `CODE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编码',
  `CDESC` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `CREATEUSER` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `CREATEDATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATEUSER` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '测试角色', '1', '测试角色', '1111', '2019-06-20 10:28:31', null, null);

-- ----------------------------
-- Table structure for sys_role_ref_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_ref_sys_permission`;
CREATE TABLE `sys_role_ref_sys_permission` (
  `SYS_ROLE_ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色ID',
  `SYS_PERMISSION_ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限ID',
  KEY `FK_REF_P_RP` (`SYS_PERMISSION_ID`) USING BTREE,
  KEY `FK_REF_R_RP` (`SYS_ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色权限关系表';

-- ----------------------------
-- Records of sys_role_ref_sys_permission
-- ----------------------------
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '1');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '100');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '200');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '2');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '3');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '4');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '101');
INSERT INTO `sys_role_ref_sys_permission` VALUES ('1', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `SYS_DEPARTMENT_ID` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门id',
  `SYS_ROLE_ID` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '//角色id',
  `NAME` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `PASSWORD` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码MD5加密',
  `REAL_NAME` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '真实姓名',
  `PHONE` varchar(13) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户电话号码',
  `CODE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编码',
  `PHOTO` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `SEX` int(1) DEFAULT '0' COMMENT '性别  0：男 1：女\r\n            ',
  `WEIXIN` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'weixin',
  `QQ` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'qq',
  `EMAIL` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `ID_CARD` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号码',
  `BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `NATIVE_PLACE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '籍贯',
  `CURR_ADDRESS` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现居住地址',
  `LOGIN_IP` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最后登录IP',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '最后登录时间',
  `LOGIN_COUNT` bigint(20) DEFAULT '0' COMMENT '登录次数',
  `STATUS` int(1) NOT NULL DEFAULT '1' COMMENT '状态   0：禁用    1：启用  ',
  `CREATEUSER` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `CREATEDATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATEUSER` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, '1', 'admin', 'zdd755', 'zdd', null, null, null, '0', null, null, null, null, null, null, null, null, null, '0', '1', '1', '2019-06-21 14:31:02', null, null);
INSERT INTO `sys_user` VALUES ('3c1d27c355f24bf392da4675fdf63151', null, '1', 'zxx', 'xxx123', 'zxx', null, null, null, '0', null, null, null, null, null, null, null, null, null, '0', '1', '2', '2019-06-24 06:56:12', null, null);

-- ----------------------------
-- Table structure for sys_user_ref_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_ref_sys_permission`;
CREATE TABLE `sys_user_ref_sys_permission` (
  `SYSY_USER_ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色ID',
  `SYS_PERMISSION_ID` char(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户权限关系表';

-- ----------------------------
-- Records of sys_user_ref_sys_permission
-- ----------------------------
INSERT INTO `sys_user_ref_sys_permission` VALUES ('1', '0');
INSERT INTO `sys_user_ref_sys_permission` VALUES ('1', '1');
INSERT INTO `sys_user_ref_sys_permission` VALUES ('1', '2');
