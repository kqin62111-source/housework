/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80046 (8.0.46)
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 80046 (8.0.46)
 File Encoding         : 65001

 Date: 13/06/2026 11:43:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学生ID',
  `attendance_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考勤日期',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态（出勤/迟到/早退/请假/旷课）',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原因',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 1, '2024-01-08', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (2, 1, '2024-01-09', '迟到', '交通拥堵', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (3, 1, '2024-01-10', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (4, 1, '2024-01-11', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (5, 2, '2024-01-08', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (6, 2, '2024-01-09', '请假', '生病', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (7, 2, '2024-01-10', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (8, 3, '2024-01-08', '早退', '家中有事', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (9, 3, '2024-01-09', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (10, 3, '2024-01-10', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (11, 4, '2024-01-08', '旷课', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (12, 4, '2024-01-09', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (13, 5, '2024-01-08', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (14, 5, '2024-01-09', '出勤', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `attendance` VALUES (15, 5, '2024-01-10', '迟到', NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
  `grade_id` bigint NULL DEFAULT NULL COMMENT '所属年级ID',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `grade_id`(`grade_id` ASC) USING BTREE,
  CONSTRAINT `clazz_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (1, '初一(1)班', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (2, '初一(2)班', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (3, '初一(3)班', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (4, '初二(1)班', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (5, '初二(2)班', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (6, '初二(3)班', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (7, '初三(1)班', 3, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (8, '初三(2)班', 3, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (9, '初三(3)班', 3, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (10, '高一(1)班', 4, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (11, '高一(2)班', 4, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (12, '高二(1)班', 5, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (13, '高二(2)班', 5, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (14, '高三(1)班', 6, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `clazz` VALUES (15, '高三(2)班', 6, '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程代码',
  `credit` int NULL DEFAULT 1 COMMENT '学分',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '语文', 'CHN001', 4, '基础课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (2, '数学', 'MATH001', 4, '基础课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (3, '英语', 'ENG001', 4, '基础课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (4, '物理', 'PHY001', 3, '理科课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (5, '化学', 'CHE001', 3, '理科课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (6, '生物', 'BIO001', 3, '理科课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (7, '历史', 'HIS001', 3, '文科课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (8, '地理', 'GEO001', 3, '文科课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (9, '政治', 'POL001', 2, '文科课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (10, '体育', 'PE001', 2, '体育课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (11, '音乐', 'MUS001', 1, '艺术课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `course` VALUES (12, '美术', 'ART001', 1, '艺术课程', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门编号',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '教务处', 'JW', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (2, '政教处', 'ZZ', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (3, '总务处', 'ZW', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (4, '财务处', 'CW', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (5, '办公室', 'BG', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (6, '信息技术部', 'IT', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (7, '学生处', 'XS', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (8, '团委', 'TW', '2026-06-12 20:09:23', '2026-06-12 20:09:23');
INSERT INTO `department` VALUES (9, '鏁欏姟澶�', 'JW', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (10, '鏀挎暀澶�', 'ZZ', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (11, '鎬诲姟澶�', 'ZW', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (12, '璐㈠姟澶�', 'CW', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (13, '鍔炲叕瀹�', 'BG', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (14, '淇℃伅鎶�鏈儴', 'IT', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (15, '瀛︾敓澶�', 'XS', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (16, '鍥㈠', 'TW', '2026-06-12 20:28:24', '2026-06-12 20:28:24');
INSERT INTO `department` VALUES (17, '教务处', 'JW', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (18, '政教处', 'ZZ', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (19, '总务处', 'ZW', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (20, '财务处', 'CW', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (21, '办公室', 'BG', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (22, '信息技术部', 'IT', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (23, '学生处', 'XS', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (24, '团委', 'TW', '2026-06-12 20:33:16', '2026-06-12 20:33:16');
INSERT INTO `department` VALUES (25, '教务处', 'JW', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (26, '政教处', 'ZZ', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (27, '总务处', 'ZW', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (28, '财务处', 'CW', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (29, '办公室', 'BG', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (30, '信息技术部', 'IT', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (31, '学生处', 'XS', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (32, '团委', 'TW', '2026-06-12 21:01:17', '2026-06-12 21:01:17');
INSERT INTO `department` VALUES (33, '教务处', 'JW', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (34, '政教处', 'ZZ', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (35, '总务处', 'ZW', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (36, '财务处', 'CW', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (37, '办公室', 'BG', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (38, '信息技术部', 'IT', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (39, '学生处', 'XS', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `department` VALUES (40, '团委', 'TW', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师姓名',
  `sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `department_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职位',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department_id`(`department_id` ASC) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '张老师', 'T001', '13800138001', 'zhang@school.com', 1, '语文老师', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (2, '李老师', 'T002', '13800138002', 'li@school.com', 1, '数学老师', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (3, '王老师', 'T003', '13800138003', 'wang@school.com', 1, '英语老师', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (4, '赵老师', 'T004', '13800138004', 'zhao@school.com', 1, '物理老师', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (5, '刘老师', 'T005', '13800138005', 'liu@school.com', 1, '化学老师', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (6, '陈老师', 'T006', '13800138006', 'chen@school.com', 2, '班主任', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (7, '杨老师', 'T007', '13800138007', 'yang@school.com', 5, '办公室主任', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `employee` VALUES (8, '黄老师', 'T008', '13800138008', 'huang@school.com', 6, '信息技术老师', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年级名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '年级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '初一', '初中一年级', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `grade` VALUES (2, '初二', '初中二年级', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `grade` VALUES (3, '初三', '初中三年级', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `grade` VALUES (4, '高一', '高中一年级', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `grade` VALUES (5, '高二', '高中二年级', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `grade` VALUES (6, '高三', '高中三年级', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限编码',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'URL',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int NULL DEFAULT 1 COMMENT '类型（1菜单/2按钮）',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父ID',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '学生管理', 'STUDENT', '/student/list', 'fa-users', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (2, '班级管理', 'CLASS', '/class/list', 'fa-building', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (3, '年级管理', 'GRADE', '/grade/list', 'fa-graduation-cap', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (4, '课程管理', 'COURSE', '/course/list', 'fa-book', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (5, '成绩管理', 'SCORE', '/score/list', 'fa-bar-chart', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (6, '教师管理', 'EMPLOYEE', '/employee/list', 'fa-user', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (7, '学籍管理', 'STUDENT_STATUS', '/studentStatus/list', 'fa-file-text', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (8, '角色管理', 'ROLE', '/role/list', 'fa-key', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (9, '权限管理', 'PERMISSION', '/permission/list', 'fa-shield', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (10, '数据字典', 'DICTIONARY', '/systemDictionary/list', 'fa-list', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (11, '课程表', 'SCHEDULE', '/schedule/list', 'fa-calendar', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `permission` VALUES (12, '考勤管理', 'ATTENDANCE', '/attendance/list', 'fa-clock-o', 1, NULL, '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', 'ADMIN', '系统最高权限', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `role` VALUES (2, '教务管理员', 'EDU_ADMIN', '教务管理权限', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `role` VALUES (3, '班主任', 'CLASS_TEACHER', '班级管理权限', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `role` VALUES (4, '任课教师', 'TEACHER', '教学管理权限', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `role` VALUES (5, '学生', 'STUDENT', '学生权限', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_id` bigint NULL DEFAULT NULL COMMENT '班级ID',
  `course_id` bigint NULL DEFAULT NULL COMMENT '课程ID',
  `teacher_id` bigint NULL DEFAULT NULL COMMENT '教师ID',
  `day_of_week` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '星期',
  `period` int NULL DEFAULT NULL COMMENT '节次',
  `classroom` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教室',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `class_id`(`class_id` ASC) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  INDEX `teacher_id`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `clazz` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程安排表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (1, 1, 1, 1, '周一', 1, '101', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (2, 1, 2, 2, '周一', 2, '101', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (3, 1, 3, 3, '周一', 3, '102', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (4, 1, 4, 4, '周二', 1, '101', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (5, 1, 5, 5, '周二', 2, '103', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (6, 2, 1, 1, '周一', 1, '102', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (7, 2, 2, 2, '周一', 2, '102', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (8, 2, 3, 3, '周三', 1, '102', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (9, 4, 1, 1, '周一', 4, '201', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (10, 4, 2, 2, '周二', 4, '201', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (11, 4, 3, 3, '周三', 4, '201', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (12, 5, 1, 1, '周二', 1, '202', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `schedule` VALUES (13, 5, 2, 2, '周三', 1, '202', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学生ID',
  `course_id` bigint NULL DEFAULT NULL COMMENT '课程ID',
  `score` int NULL DEFAULT NULL COMMENT '成绩',
  `exam_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试类型',
  `exam_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试日期',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '成绩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 1, 1, 95, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (2, 1, 2, 88, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (3, 1, 3, 92, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (4, 2, 1, 89, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (5, 2, 2, 93, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (6, 2, 3, 87, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (7, 3, 1, 91, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (8, 3, 2, 95, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (9, 3, 4, 88, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (10, 4, 1, 85, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (11, 4, 2, 90, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (12, 4, 5, 92, '期中考试', '2024-04-15', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (13, 5, 1, 87, '期末考试', '2024-06-20', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (14, 5, 2, 91, '期末考试', '2024-06-20', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `score` VALUES (15, 5, 3, 88, '期末考试', '2024-06-20', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `className` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级',
  `grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年级',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '张三', 12, '男', '初一(1)班', '初一', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (2, '李四', 13, '女', '初一(2)班', '初一', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (3, '王五', 14, '男', '初二(1)班', '初二', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (4, '赵六', 13, '女', '初二(2)班', '初二', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (5, '钱七', 15, '男', '初三(1)班', '初三', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (6, '孙八', 14, '女', '初三(2)班', '初三', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (7, '周九', 12, '男', '初一(1)班', '初一', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (8, '吴十', 13, '女', '初一(2)班', '初一', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (9, '郑十一', 14, '男', '初二(1)班', '初二', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (10, '王小明', 15, '女', '初三(1)班', '初三', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (11, '李晓红', 12, '女', '初一(1)班', '初一', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (12, '张俊杰', 13, '男', '初一(2)班', '初一', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (13, '刘诗雨', 14, '女', '初二(1)班', '初二', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (14, '陈思远', 13, '男', '初二(2)班', '初二', '2026-06-13 10:36:26', '2026-06-13 10:36:26');
INSERT INTO `student` VALUES (15, '张雨萱', 15, '女', '初三(1)班', '初三', '2026-06-13 10:36:26', '2026-06-13 10:36:26');

-- ----------------------------
-- Table structure for student_status
-- ----------------------------
DROP TABLE IF EXISTS `student_status`;
CREATE TABLE `student_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学生ID',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态（入学/转学/毕业）',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原因',
  `operate_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作日期',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作员',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  CONSTRAINT `student_status_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学籍状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_status
-- ----------------------------
INSERT INTO `student_status` VALUES (1, 1, '入学', '新生入学', '2024-09-01', '管理员', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `student_status` VALUES (2, 2, '入学', '新生入学', '2024-09-01', '管理员', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `student_status` VALUES (3, 3, '入学', '新生入学', '2023-09-01', '管理员', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `student_status` VALUES (4, 4, '转学', '转入本校', '2024-02-15', '管理员', '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `student_status` VALUES (5, 5, '入学', '新生入学', '2022-09-01', '管理员', '2026-06-13 10:36:27', '2026-06-13 10:36:27');

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `key_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '键',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '值',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序号',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------
INSERT INTO `system_dictionary` VALUES (1, 'gender', '性别', '男', '男', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (2, 'gender', '性别', '女', '女', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (3, 'exam_type', '考试类型', '期中考试', '期中考试', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (4, 'exam_type', '考试类型', '期末考试', '期末考试', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (5, 'exam_type', '考试类型', '月考', '月考', 3, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (6, 'exam_type', '考试类型', '周测', '周测', 4, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (7, 'status', '学籍状态', '入学', '入学', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (8, 'status', '学籍状态', '转学', '转学', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (9, 'status', '学籍状态', '毕业', '毕业', 3, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (10, 'attendance_status', '考勤状态', '出勤', '出勤', 1, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (11, 'attendance_status', '考勤状态', '迟到', '迟到', 2, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (12, 'attendance_status', '考勤状态', '早退', '早退', 3, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (13, 'attendance_status', '考勤状态', '请假', '请假', 4, '2026-06-13 10:36:27', '2026-06-13 10:36:27');
INSERT INTO `system_dictionary` VALUES (14, 'attendance_status', '考勤状态', '旷课', '旷课', 5, '2026-06-13 10:36:27', '2026-06-13 10:36:27');

SET FOREIGN_KEY_CHECKS = 1;
