/*
 Navicat Premium Data Transfer

 Source Server         : aliyunDB
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 47.97.27.239:3306
 Source Schema         : phoenix

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 02/03/2021 17:32:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_file`;
CREATE TABLE `sys_upload_file` (
  `uf_id` bigint(20) NOT NULL COMMENT 'id',
  `uf_original_file_name` varchar(255) NOT NULL COMMENT '原始文件名',
  `uf_saved_file_name` varchar(255) NOT NULL COMMENT '保存后文件名',
  `uf_ext` varchar(20) NOT NULL COMMENT '文件扩展名',
  `uf_size` bigint(20) DEFAULT NULL COMMENT '大小',
  `uf_related_business` varchar(255) DEFAULT NULL COMMENT '关联业务',
  `uf_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `uf_absolute_path` varchar(255) NOT NULL COMMENT '文件位置',
  `uf_public_link` varchar(255) DEFAULT NULL COMMENT '公网连接',
  `uf_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`uf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
