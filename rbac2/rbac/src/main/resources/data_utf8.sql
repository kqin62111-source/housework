-- 学生测试数据
INSERT IGNORE INTO student (name, age, gender, className, grade) VALUES 
('张三', 12, '男', '初一(1)班', '初一'),
('李四', 13, '女', '初一(2)班', '初一'),
('王五', 14, '男', '初二(1)班', '初二'),
('赵六', 13, '女', '初二(2)班', '初二'),
('钱七', 15, '男', '初三(1)班', '初三'),
('孙八', 14, '女', '初三(2)班', '初三'),
('周九', 12, '男', '初一(1)班', '初一'),
('吴十', 13, '女', '初一(2)班', '初一'),
('郑十一', 14, '男', '初二(1)班', '初二'),
('王小明', 15, '女', '初三(1)班', '初三'),
('李晓红', 12, '女', '初一(1)班', '初一'),
('张俊杰', 13, '男', '初一(2)班', '初一'),
('刘诗雨', 14, '女', '初二(1)班', '初二'),
('陈思远', 13, '男', '初二(2)班', '初二'),
('张雨萱', 15, '女', '初三(1)班', '初三');

-- 部门测试数据
INSERT IGNORE INTO department (name, sn) VALUES 
('教务处', 'JW'),
('政教处', 'ZZ'),
('总务处', 'ZW'),
('财务处', 'CW'),
('办公室', 'BG'),
('信息技术部', 'IT'),
('学生处', 'XS'),
('团委', 'TW');

-- 年级测试数据
INSERT IGNORE INTO grade (name, description) VALUES 
('初一', '初中一年级'),
('初二', '初中二年级'),
('初三', '初中三年级'),
('高一', '高中一年级'),
('高二', '高中二年级'),
('高三', '高中三年级');

-- 班级测试数据
INSERT IGNORE INTO clazz (name, grade_id) VALUES 
('初一(1)班', 1),
('初一(2)班', 1),
('初一(3)班', 1),
('初二(1)班', 2),
('初二(2)班', 2),
('初二(3)班', 2),
('初三(1)班', 3),
('初三(2)班', 3),
('初三(3)班', 3),
('高一(1)班', 4),
('高一(2)班', 4),
('高二(1)班', 5),
('高二(2)班', 5),
('高三(1)班', 6),
('高三(2)班', 6);

-- 课程测试数据
INSERT IGNORE INTO course (name, code, credit, description) VALUES 
('语文', 'CHN001', 4, '基础课程'),
('数学', 'MATH001', 4, '基础课程'),
('英语', 'ENG001', 4, '基础课程'),
('物理', 'PHY001', 3, '理科课程'),
('化学', 'CHE001', 3, '理科课程'),
('生物', 'BIO001', 3, '理科课程'),
('历史', 'HIS001', 3, '文科课程'),
('地理', 'GEO001', 3, '文科课程'),
('政治', 'POL001', 2, '文科课程'),
('体育', 'PE001', 2, '体育课程'),
('音乐', 'MUS001', 1, '艺术课程'),
('美术', 'ART001', 1, '艺术课程');

-- 成绩测试数据
INSERT IGNORE INTO score (student_id, course_id, score, exam_type, exam_date) VALUES 
(1, 1, 95, '期中考试', '2024-04-15'),
(1, 2, 88, '期中考试', '2024-04-15'),
(1, 3, 92, '期中考试', '2024-04-15'),
(2, 1, 89, '期中考试', '2024-04-15'),
(2, 2, 93, '期中考试', '2024-04-15'),
(2, 3, 87, '期中考试', '2024-04-15'),
(3, 1, 91, '期中考试', '2024-04-15'),
(3, 2, 95, '期中考试', '2024-04-15'),
(3, 4, 88, '期中考试', '2024-04-15'),
(4, 1, 85, '期中考试', '2024-04-15'),
(4, 2, 90, '期中考试', '2024-04-15'),
(4, 5, 92, '期中考试', '2024-04-15');

-- 教师测试数据
INSERT IGNORE INTO employee (name, sn, phone, email, department_id, position) VALUES 
('张老师', 'T001', '13800138001', 'zhang@school.com', 1, '语文老师'),
('李老师', 'T002', '13800138002', 'li@school.com', 1, '数学老师'),
('王老师', 'T003', '13800138003', 'wang@school.com', 1, '英语老师'),
('赵老师', 'T004', '13800138004', 'zhao@school.com', 1, '物理老师'),
('刘老师', 'T005', '13800138005', 'liu@school.com', 1, '化学老师'),
('陈老师', 'T006', '13800138006', 'chen@school.com', 2, '班主任'),
('杨老师', 'T007', '13800138007', 'yang@school.com', 5, '办公室主任'),
('黄老师', 'T008', '13800138008', 'huang@school.com', 6, '信息技术老师');

-- 学籍状态测试数据
INSERT IGNORE INTO student_status (student_id, status, reason, operate_date, operator) VALUES 
(1, '入学', '新生入学', '2024-09-01', '管理员'),
(2, '入学', '新生入学', '2024-09-01', '管理员'),
(3, '入学', '新生入学', '2023-09-01', '管理员'),
(4, '转学', '转入本校', '2024-02-15', '管理员'),
(5, '入学', '新生入学', '2022-09-01', '管理员');

-- 角色测试数据
INSERT IGNORE INTO role (name, sn, description) VALUES 
('系统管理员', 'ADMIN', '系统最高权限'),
('教务管理员', 'EDU_ADMIN', '教务管理权限'),
('班主任', 'CLASS_TEACHER', '班级管理权限'),
('任课教师', 'TEACHER', '教学管理权限'),
('学生', 'STUDENT', '学生权限');

-- 权限测试数据
INSERT IGNORE INTO permission (name, sn, url, icon, type, parent_id) VALUES 
('学生管理', 'STUDENT', '/student/list', 'fa-users', 1, NULL),
('班级管理', 'CLASS', '/class/list', 'fa-building', 1, NULL),
('年级管理', 'GRADE', '/grade/list', 'fa-graduation-cap', 1, NULL),
('课程管理', 'COURSE', '/course/list', 'fa-book', 1, NULL),
('成绩管理', 'SCORE', '/score/list', 'fa-bar-chart', 1, NULL),
('教师管理', 'EMPLOYEE', '/employee/list', 'fa-user', 1, NULL),
('学籍管理', 'STUDENT_STATUS', '/studentStatus/list', 'fa-file-text', 1, NULL),
('角色管理', 'ROLE', '/role/list', 'fa-key', 1, NULL),
('权限管理', 'PERMISSION', '/permission/list', 'fa-shield', 1, NULL),
('数据字典', 'DICTIONARY', '/systemDictionary/list', 'fa-list', 1, NULL),
('课程表', 'SCHEDULE', '/schedule/list', 'fa-calendar', 1, NULL),
('考勤管理', 'ATTENDANCE', '/attendance/list', 'fa-clock-o', 1, NULL);

-- 数据字典测试数据
INSERT IGNORE INTO system_dictionary (type, type_name, key_value, value, sort_order) VALUES 
('gender', '性别', '男', '男', 1),
('gender', '性别', '女', '女', 2),
('exam_type', '考试类型', '期中考试', '期中考试', 1),
('exam_type', '考试类型', '期末考试', '期末考试', 2),
('exam_type', '考试类型', '月考', '月考', 3),
('exam_type', '考试类型', '周测', '周测', 4),
('status', '学籍状态', '入学', '入学', 1),
('status', '学籍状态', '转学', '转学', 2),
('status', '学籍状态', '毕业', '毕业', 3),
('attendance_status', '考勤状态', '出勤', '出勤', 1),
('attendance_status', '考勤状态', '迟到', '迟到', 2),
('attendance_status', '考勤状态', '早退', '早退', 3),
('attendance_status', '考勤状态', '请假', '请假', 4),
('attendance_status', '考勤状态', '旷课', '旷课', 5);

-- 课程表测试数据
INSERT IGNORE INTO schedule (class_id, course_id, teacher_id, day_of_week, period, classroom) VALUES 
(1, 1, 1, '周一', 1, '101'),
(1, 2, 2, '周一', 2, '101'),
(1, 3, 3, '周一', 3, '102'),
(1, 4, 4, '周二', 1, '101'),
(1, 5, 5, '周二', 2, '103'),
(2, 1, 1, '周一', 1, '102'),
(2, 2, 2, '周一', 2, '102'),
(2, 3, 3, '周三', 1, '102'),
(4, 1, 1, '周一', 4, '201'),
(4, 2, 2, '周二', 4, '201');

-- 考勤测试数据
INSERT IGNORE INTO attendance (student_id, attendance_date, status, reason) VALUES 
(1, '2024-01-08', '出勤', NULL),
(1, '2024-01-09', '迟到', '交通拥堵'),
(1, '2024-01-10', '出勤', NULL),
(2, '2024-01-08', '出勤', NULL),
(2, '2024-01-09', '请假', '生病'),
(3, '2024-01-08', '早退', '家中有事'),
(3, '2024-01-09', '出勤', NULL),
(4, '2024-01-08', '旷课', NULL),
(4, '2024-01-09', '出勤', NULL),
(5, '2024-01-08', '出勤', NULL);
