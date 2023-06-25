/*
 Navicat Premium Data Transfer

 Source Server         : hty8
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3307
 Source Schema         : myweb

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 25/06/2023 16:34:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer_info
-- ----------------------------
DROP TABLE IF EXISTS `answer_info`;
CREATE TABLE `answer_info`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `questionnaire_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `answer_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer_info
-- ----------------------------
INSERT INTO `answer_info` VALUES ('1672191013045583874', '1672162070196310018', '1668209171955138565', '2023-06-23 18:33:00');
INSERT INTO `answer_info` VALUES ('1672854394719129602', '1672162070196310018', '1668209171955138565', '2023-06-25 14:29:02');
INSERT INTO `answer_info` VALUES ('1672868319602892802', '1672867599277318146', '1668209171955138565', '2023-06-25 15:24:22');
INSERT INTO `answer_info` VALUES ('1672869350604730370', '1672867599277318146', '1668209171955138565', '2023-06-25 15:28:28');
INSERT INTO `answer_info` VALUES ('1672874644311355394', '1672867599277318146', '1668209171955138565', '2023-06-25 15:49:30');
INSERT INTO `answer_info` VALUES ('1672874950667513857', '1672867599277318146', '1668209171955138565', '2023-06-25 15:50:43');

-- ----------------------------
-- Table structure for answer_link
-- ----------------------------
DROP TABLE IF EXISTS `answer_link`;
CREATE TABLE `answer_link`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `question_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `answer_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer_link
-- ----------------------------
INSERT INTO `answer_link` VALUES ('1672191013083332610', '1672162078542974977', '1668209171955138565', '1672191013045583874');
INSERT INTO `answer_link` VALUES ('1672191013280464898', '1672162118766350338', '1668209171955138565', '1672191013045583874');
INSERT INTO `answer_link` VALUES ('1672869350667644930', '1672868198630776833', '1668209171955138565', '1672869350604730370');
INSERT INTO `answer_link` VALUES ('1672869350864777218', '1672868198756605953', '1668209171955138565', '1672869350604730370');
INSERT INTO `answer_link` VALUES ('1672874644395241474', '1672868198630776833', '1668209171955138565', '1672874644311355394');
INSERT INTO `answer_link` VALUES ('1672874644525264897', '1672868198756605953', '1668209171955138565', '1672874644311355394');
INSERT INTO `answer_link` VALUES ('1672874950730428417', '1672868198630776833', '1668209171955138565', '1672874950667513857');
INSERT INTO `answer_link` VALUES ('1672874950856257538', '1672868198756605953', '1668209171955138565', '1672874950667513857');

-- ----------------------------
-- Table structure for chosen_answer
-- ----------------------------
DROP TABLE IF EXISTS `chosen_answer`;
CREATE TABLE `chosen_answer`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `link_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `option_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chosen_answer
-- ----------------------------
INSERT INTO `chosen_answer` VALUES ('1672191013150441473', '1672191013083332610', '1672162102693777409');
INSERT INTO `chosen_answer` VALUES ('1672191013347573762', '1672191013280464898', '1672162142661300226');
INSERT INTO `chosen_answer` VALUES ('1672869350730559490', '1672869350667644930', '1672868207589810181');
INSERT INTO `chosen_answer` VALUES ('1672869350923497473', '1672869350864777218', '1672868216347516929');
INSERT INTO `chosen_answer` VALUES ('1672874644462350337', '1672874644395241474', '1672868207589810178');
INSERT INTO `chosen_answer` VALUES ('1672874644592373761', '1672874644525264897', '1672868216347516930');
INSERT INTO `chosen_answer` VALUES ('1672874950793342978', '1672874950730428417', '1672868207589810178');
INSERT INTO `chosen_answer` VALUES ('1672874950923366401', '1672874950856257538', '1672868216347516929');

-- ----------------------------
-- Table structure for option_info
-- ----------------------------
DROP TABLE IF EXISTS `option_info`;
CREATE TABLE `option_info`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `choose_term` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `question_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `score` int NULL DEFAULT NULL,
  `person_count` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of option_info
-- ----------------------------
INSERT INTO `option_info` VALUES ('1', '测试1', '1', NULL, 0);
INSERT INTO `option_info` VALUES ('1671556331501129730', 'aaa', '1671556305706160129', NULL, 0);
INSERT INTO `option_info` VALUES ('1672162102693777409', 'bbbb', '1672162078542974977', NULL, 0);
INSERT INTO `option_info` VALUES ('1672162142661300226', 'dddd', '1672162118766350338', NULL, 0);
INSERT INTO `option_info` VALUES ('1672276848659087362', 'aaa', '1672276830950735874', NULL, 0);
INSERT INTO `option_info` VALUES ('1672868207589810178', '测试1', '1672868198630776833', NULL, 0);
INSERT INTO `option_info` VALUES ('1672868207589810179', '测试2', '1672868198630776833', NULL, 0);
INSERT INTO `option_info` VALUES ('1672868207589810180', '测试3', '1672868198630776833', NULL, 0);
INSERT INTO `option_info` VALUES ('1672868207589810181', '测试4', '1672868198630776833', NULL, 0);
INSERT INTO `option_info` VALUES ('1672868216347516929', '西瓜', '1672868198756605953', NULL, 0);
INSERT INTO `option_info` VALUES ('1672868216347516930', '橘子', '1672868198756605953', NULL, 0);
INSERT INTO `option_info` VALUES ('2', '测试2', '1', NULL, 0);
INSERT INTO `option_info` VALUES ('3', '测试3', '1', NULL, 0);
INSERT INTO `option_info` VALUES ('4', '测试4', '1', NULL, 0);
INSERT INTO `option_info` VALUES ('5', '西瓜', '2', NULL, 0);
INSERT INTO `option_info` VALUES ('6', '橘子', '2', NULL, 0);

-- ----------------------------
-- Table structure for project_info
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目表主键',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id（没有用）',
  `project_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `project_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '项目说明',
  `created_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creation_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_info_user_info_id_fk`(`user_id` ASC) USING BTREE,
  CONSTRAINT `project_info_user_info_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of project_info
-- ----------------------------
INSERT INTO `project_info` VALUES ('1668226706322665474', '1668209171955138565', 'op', 'sssss', 'admin', '2023-06-12 20:00:16', 'admin', '2023-06-12 20:00:16');
INSERT INTO `project_info` VALUES ('1669323639506264066', '1668209171955138565', '66666', '666666', 'admin', '2023-06-15 20:39:05', 'admin', '2023-06-15 20:39:05');
INSERT INTO `project_info` VALUES ('1669324175701893121', '1668209171955138565', '777', '888', 'admin', '2023-06-15 20:41:13', 'admin', '2023-06-15 20:41:13');
INSERT INTO `project_info` VALUES ('283dcf241cf245aea824dc10bbb3d680', '1668209171955138565', '第一个项目', '第一个项目描述 问问', 'admin', '2020-09-23 20:27:42', 'admin', '2020-09-23 20:27:59');
INSERT INTO `project_info` VALUES ('4cd6ccb65c894eafaa70b12330f8c2f8', '1668209171955138565', '第一个项目', '第一个项目 的方法v', 'admin', '2020-09-27 16:23:20', 'admin', '2020-09-27 16:23:20');

-- ----------------------------
-- Table structure for question_bank
-- ----------------------------
DROP TABLE IF EXISTS `question_bank`;
CREATE TABLE `question_bank`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_bank
-- ----------------------------
INSERT INTO `question_bank` VALUES ('1', '测试', '1');
INSERT INTO `question_bank` VALUES ('2', '你喜欢什么', '2');

-- ----------------------------
-- Table structure for question_info
-- ----------------------------
DROP TABLE IF EXISTS `question_info`;
CREATE TABLE `question_info`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `answer_count` int NOT NULL DEFAULT 0,
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `questionnaire_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `is_must` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'true',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question_info_questionnaire_info_id_fk`(`questionnaire_id` ASC) USING BTREE,
  CONSTRAINT `question_info_questionnaire_info_id_fk` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '问题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_info
-- ----------------------------
INSERT INTO `question_info` VALUES ('1671555548017823745', 'aaa', '', 0, '1', '1671555535514603522', 'true');
INSERT INTO `question_info` VALUES ('1671556305706160129', 'aaa', NULL, 0, '1', '1671556299070771201', 'true');
INSERT INTO `question_info` VALUES ('1672162078542974977', 'bbb', NULL, 0, '1', '1672162070196310018', 'true');
INSERT INTO `question_info` VALUES ('1672162118766350338', 'aaaa', NULL, 0, '2', '1672162070196310018', 'true');
INSERT INTO `question_info` VALUES ('1672276670539579394', '你喜欢什么', NULL, 0, '1', '1672266692852334594', 'true');
INSERT INTO `question_info` VALUES ('1672276830950735874', 'aaa', NULL, 0, '2', '1672266692852334594', 'true');
INSERT INTO `question_info` VALUES ('1672277799839137793', '你喜欢什么', NULL, 0, '2', '1672266692852334594', 'true');
INSERT INTO `question_info` VALUES ('1672450926959382529', NULL, NULL, 0, '2', '1672450823183912961', 'true');
INSERT INTO `question_info` VALUES ('1672451094848983041', '测试', NULL, 0, '1', '1672450823183912961', 'true');
INSERT INTO `question_info` VALUES ('1672451603890688002', NULL, NULL, 0, '2', '1672450823183912961', 'true');
INSERT INTO `question_info` VALUES ('1672451851929137154', NULL, NULL, 0, '1', '1672450823183912961', 'true');
INSERT INTO `question_info` VALUES ('1672868198630776833', '测试', NULL, 3, '1', '1672867599277318146', 'true');
INSERT INTO `question_info` VALUES ('1672868198756605953', '你喜欢什么', NULL, 3, '2', '1672867599277318146', 'true');

-- ----------------------------
-- Table structure for questionnaire_info
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire_info`;
CREATE TABLE `questionnaire_info`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'name',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creation_date` datetime NULL DEFAULT NULL,
  `last_updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `last_update_date` datetime NULL DEFAULT NULL,
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0',
  `survey_object` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目外键',
  `start_time` datetime NULL DEFAULT NULL,
  `stop_time` datetime NULL DEFAULT NULL,
  `is_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '问卷' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questionnaire_info
-- ----------------------------
INSERT INTO `questionnaire_info` VALUES ('1671002420528758785', NULL, NULL, 'admin', '2023-06-20 11:49:57', 'admin', '2023-06-20 11:49:57', '0', '老师', '283dcf241cf245aea824dc10bbb3d680', NULL, NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671066945386909697', 'dadwad', NULL, 'admin', '2023-06-20 16:06:21', 'admin', '2023-06-20 16:06:21', '0', '老师', '1669324175701893121', '2023-06-27 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671067341866049537', '7894852', 'eqeqwew', 'admin', '2023-06-20 16:07:56', 'admin', '2023-06-20 16:07:56', '0', '老师', '1669324175701893121', '2023-06-27 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671071310596235265', 'hhhhhhhhh', 'hhhhhhhhhhhhh', 'admin', '2023-06-20 16:23:42', 'admin', '2023-06-20 16:23:42', '0', '学生', '1668226706322665474', '2023-06-20 08:00:00', NULL, '1');
INSERT INTO `questionnaire_info` VALUES ('1671072134189723650', '测试', 'sadsaadsa', 'admin', '2023-06-20 16:26:58', 'admin', '2023-06-20 19:57:11', '0', '学生', '1669323639506264066', '2023-06-20 19:55:51', '2023-06-20 19:57:11', '0');
INSERT INTO `questionnaire_info` VALUES ('1671077318685024258', 'ggggg', 'sadsaadsa', 'admin', '2023-06-20 16:47:35', 'admin', '2023-06-20 19:57:17', '0', '学生', '1669323639506264066', '2023-06-20 19:57:14', '2023-06-20 19:57:17', '0');
INSERT INTO `questionnaire_info` VALUES ('1671077911977713666', '7676756', '7567567567', 'admin', '2023-06-20 16:49:56', 'admin', '2023-06-23 16:38:27', '0', '学生', '1668226706322665474', '2023-06-20 19:48:17', '2023-06-23 16:38:27', '0');
INSERT INTO `questionnaire_info` VALUES ('1671080026674524161', '问卷标题', '问卷说明', 'admin', '2023-06-20 16:58:20', 'admin', '2023-06-20 16:58:25', '0', '学生', '1669324175701893121', '2023-06-21 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671546889917345793', 'adassadsadsa', 'dsadsadsadasd', 'admin', '2023-06-21 23:53:29', 'admin', '2023-06-21 23:53:29', '0', '学生', '283dcf241cf245aea824dc10bbb3d680', '2023-06-21 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671549694870728705', '测试', '79456', 'admin', '2023-06-22 00:04:38', 'admin', '2023-06-22 00:04:38', '0', '学生', '1669324175701893121', '2023-06-22 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671550937630461953', 'dadwad', 'sadddddddsa', 'admin', '2023-06-22 00:09:34', 'admin', '2023-06-22 00:09:34', '0', '学生', '283dcf241cf245aea824dc10bbb3d680', '2023-06-22 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671552586025492482', '测试sa', 'sadddddddddddd', 'admin', '2023-06-22 00:16:07', 'admin', '2023-06-22 00:16:07', '0', '学生', '1668226706322665474', '2023-06-22 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671553331441393666', '测试s', 'sadasdasds', 'admin', '2023-06-22 00:19:05', 'admin', '2023-06-22 00:19:05', '0', '学生', '1668226706322665474', '2023-06-22 08:00:00', NULL, '1');
INSERT INTO `questionnaire_info` VALUES ('1671555535514603522', '测试', 'sdada', 'admin', '2023-06-22 00:27:50', 'admin', '2023-06-22 00:27:50', '0', '学生', '283dcf241cf245aea824dc10bbb3d680', '2023-06-22 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1671556299070771201', '测试sads', 'sadasdad', 'admin', '2023-06-22 00:30:52', 'admin', '2023-06-22 00:30:52', '0', '学生', '283dcf241cf245aea824dc10bbb3d680', '2023-06-22 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1672162070196310018', 'sbssbs', 'sbsbsbsbs', 'admin', '2023-06-23 16:37:59', 'admin', '2023-06-25 15:24:11', '0', '学生', '1668226706322665474', '2023-06-23 16:38:31', '2023-06-25 15:24:11', '0');
INSERT INTO `questionnaire_info` VALUES ('1672249544750895106', '测试', '79456', 'admin', '2023-06-23 22:25:35', 'admin', '2023-06-23 22:25:35', '0', '学生', '1668226706322665474', '2023-06-23 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1672253656276066306', 'saffadfd', 'sfdsfdsfsdf', 'admin', '2023-06-23 22:41:55', 'admin', '2023-06-23 22:41:55', '0', '学生', '1669323639506264066', '2023-06-23 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1672266692852334594', 'sadasda', 'dasdasdasd', 'admin', '2023-06-23 23:33:43', 'admin', '2023-06-23 23:33:43', '0', '学生', '1668226706322665474', '2023-06-23 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1672450031395733505', 'sdasd', 'sadsasda', 'admin', '2023-06-24 11:42:15', 'admin', '2023-06-24 11:42:15', '0', '学生', '1668226706322665474', '2023-06-24 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1672450823183912961', 'sdafafa', 'sadsasdadfsdsfdfsd', 'admin', '2023-06-24 11:45:24', 'admin', '2023-06-24 11:45:24', '0', '学生', '1668226706322665474', '2023-06-24 08:00:00', NULL, '0');
INSERT INTO `questionnaire_info` VALUES ('1672867599277318146', '测试ggg', 'abcd', 'admin', '2023-06-25 15:21:31', 'admin', '2023-06-25 15:24:07', '1', '学生', '1668226706322665474', '2023-06-25 15:24:07', NULL, '0');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户表主键',
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime NULL DEFAULT NULL COMMENT '截止时间（时间戳）',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用（1启用，0不启用）',
  `created_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creation_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1665690267177410562', 'hhhh', '6666', '2023-06-22 19:06:58', '2023-06-12 17:06:58', '1', 'admin', '2023-06-05 20:01:21', 'admin', '2023-06-12 18:49:08');
INSERT INTO `user_info` VALUES ('1668209171955138561', '789956', '514615486', '2023-06-27 18:06:21', '2023-06-26 18:06:21', '1', 'admin', '2023-06-12 18:50:35', 'admin', '2023-06-12 18:50:35');
INSERT INTO `user_info` VALUES ('1668209171955138565', 'admin', '123', NULL, NULL, '1', 'admin', NULL, NULL, NULL);
INSERT INTO `user_info` VALUES ('1671522606004514818', 'hhhh', '6666', NULL, NULL, '1', NULL, '2023-06-21 22:16:59', NULL, '2023-06-21 22:16:59');
INSERT INTO `user_info` VALUES ('1671524555462500354', 'hhhh', '6666', NULL, NULL, '1', NULL, '2023-06-21 22:24:44', NULL, '2023-06-21 22:24:44');

SET FOREIGN_KEY_CHECKS = 1;
