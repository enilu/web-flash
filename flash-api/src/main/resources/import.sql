-- ----------------------------
-- Records of t_sys_cfg
-- ----------------------------
INSERT INTO `t_sys_cfg` VALUES ('1', null, null, '1', '2019-04-15 21:36:07', '应用名称update by 2019-03-27 11:47:04', 'system.app.name', 'web-flash');
INSERT INTO `t_sys_cfg` VALUES ('2', null, null, '1', '2019-04-15 21:36:17', '系统默认上传文件路径', 'system.file.upload.path', '/data/web-flash/runtime/upload');
INSERT INTO `t_sys_cfg` VALUES ('3', null, null, '1', '2019-04-15 21:36:17', '腾讯sms接口appid', 'api.tencent.sms.appid', '需要去申请咯');
INSERT INTO `t_sys_cfg` VALUES ('4', null, null, '1', '2019-04-15 21:36:17', '腾讯sms接口appkey', 'api.tencent.sms.appkey', '需要去申请咯');
INSERT INTO `t_sys_cfg` VALUES ('5', null, null, '1', '2019-04-15 21:36:17', '腾讯sms接口签名参数', 'api.tencent.sms.sign', '需要去申请咯');
INSERT INTO `t_sys_cfg` VALUES ('6', null, null, '1', '2019-04-15 21:36:17', '阿里云sms接口accesskey', 'api.aliyun.sms.access.key.id', '需要去申请咯');
INSERT INTO `t_sys_cfg` VALUES ('7', null, null, '1', '2019-04-15 21:36:17', '阿里云sms接口access Secret', 'api.aliyun.sms.access.secret', '需要去申请咯');
INSERT INTO `t_sys_cfg` VALUES ('8', null, null, '1', '2019-04-15 21:36:17', '阿里云sms接口地域id', 'api.aliyun.sms.region.id', '需要去申请咯');

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`,  `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', '1', '0', '[0],', '总公司', 'One Piece集团', null, null, null, null);
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`,  `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('2', '2', '1', '[0],[1],', '开发部', '开发部',  null, null, null, null);
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`,  `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('3', '3', '1', '[0],[1],', '运营部', '运营部',  null, null, null, null);
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`,  `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('4', '4', '1', '[0],[1],', '战略部', '战略部',  null, null, null, null);

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------

INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (16, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '状态', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (17, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '启用', '1', 16, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (18, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '禁用', '2', 16, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (29, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '性别', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (30, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '男', '1', 29, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (31, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '女', '2', 29, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (35, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '账号状态', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (36, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '启用', '1', 35, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (37, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '冻结', '2', 35, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (38, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '已删除', '3', 35, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (53, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '证件类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (54, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '身份证', '1', 53, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (55, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '护照', '2', 53, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (68, 1, '2019-1-13 14:18:21', 1, '2019-1-13 14:18:21', '是否', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (69, 1, '2019-1-13 14:18:21', 1, '2019-1-13 14:18:21', '是', '1', 68, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (70, 1, '2019-1-13 14:18:21', 1, '2019-1-13 14:18:21', '否', '0', 68, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (71, 1, '2020-7-18 21:43:41', 1, '2020-7-18 21:43:41', '日志类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (72, 1, '2020-7-18 21:43:41', 1, '2020-7-18 21:43:41', '业务日志', '1', 71, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (73, 1, '2019-1-13 14:18:21', 1, '2020-7-18 21:43:41', '异常日志', '2', 71, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (74, 1, '2021-7-24 22:27:00', 1, '2021-7-24 22:27:00', '工作流实例状态', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (75, 1, '2021-7-24 22:27:00', 1, '2021-7-24 22:27:00', '进行中', '0', 74, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (76, 1, '2021-7-24 22:27:00', 1, '2021-7-24 22:27:00', '成功', '1', 74, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (77, 1, '2021-7-24 22:27:00', 1, '2021-7-24 22:27:00', '失败', '2', 74, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (78, 1, '2023-12-14 22:37:24', 1, '2023-12-14 22:37:24', '消息类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (79, 1, '2023-12-14 22:37:24', 1, '2023-12-14 22:37:24', '短信', '0', 78, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (80, 1, '2023-12-14 22:37:25', 1, '2023-12-14 22:37:25', '邮件', '1', 78, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (81, 1, '2023-12-14 22:38:29', 1, '2023-12-14 22:38:29', '消息状态', '0', 0, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (82, 1, '2023-12-14 22:38:29', 1, '2023-12-14 22:38:29', '初始', '0', 81, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (83, 1, '2023-12-14 22:38:30', 1, '2023-12-14 22:38:30', '成功', '1', 81, NULL);
INSERT INTO `t_sys_dict` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `num`, `pid`, `tips`) VALUES (84, 1, '2023-12-14 22:38:30', 1, '2023-12-14 22:38:30', '失败', '2', 81, NULL);


-- ----------------------------
-- Records of t_sys_file_info
-- ----------------------------
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('1', '1', '2019-03-18 10:34:34', '1', '2019-03-18 10:34:34', 'banner1.png', '7e9ebc08-b194-4f85-8997-d97ccb0d2c2d.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('2', '1', '2019-03-18 10:54:04', '1', '2019-03-18 10:54:04', 'banner2.png', '756b9ca8-562f-4bf5-a577-190dcdd25c29.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('3', '1', '2019-03-18 20:09:59', '1', '2019-03-18 20:09:59', 'offcial_site.png', 'b0304e2b-0ee3-4966-ac9f-a075b13d4af6.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('4', '1', '2019-03-18 20:10:18', '1', '2019-03-18 20:10:18', 'bbs.png', '67486aa5-500c-4993-87ad-7e1fbc90ac1a.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('5', '1', '2019-03-18 20:20:14', '1', '2019-03-18 20:20:14', 'product.png', '1f2b05e0-403a-41e0-94a2-465f0c986b78.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('6', '1', '2019-03-18 20:22:09', '1', '2019-03-18 20:22:09', 'profile.jpg', '40ead888-14d1-4e9f-abb3-5bfb056a966a.jpg');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('7', '1', '2019-03-20 09:05:54', '1', '2019-03-20 09:05:54', '2303938_1453211.png', '87b037da-b517-4007-a66e-ba7cc8cfd6ea.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('8', null, null, null, null, 'login.png', '26835cc4-059e-4900-aff5-a41f2ea6a61d.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('9', null, null, null, null, 'login.png', '7ec7553b-7c9e-44d9-b9c2-3d89b11cf842.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('10', null, null, null, null, 'login.png', '357c4aad-19fd-4600-9fb6-e62aafa3df25.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('11', null, null, null, null, 'index.png', '55dd582b-033e-440d-8e8d-c8d39d01f1bb.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('12', null, null, null, null, 'login.png', '70507c07-e8bc-492f-9f0a-00bf1c23e329.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('13', null, null, null, null, 'index.png', 'cd539518-d15a-4cda-a19f-251169f5d1a4.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('14', null, null, null, null, 'login.png', '194c8a38-be94-483c-8875-3c62a857ead7.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('15', null, null, null, null, 'index.png', '6a6cb215-d0a7-4574-a45e-5fa04dcfdf90.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('16', '1', '2019-03-21 19:37:50', null, null, '测试文档.doc', 'd9d77815-496f-475b-a0f8-1d6dcb86e6ab.doc');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('17', '1', '2019-04-28 00:34:09', null, null, '首页.png', 'd5aba978-f8af-45c5-b079-673decfbdf26.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('18', '1', '2019-04-28 00:34:34', null, null, '资讯.png', '7e07520d-5b73-4712-800b-16f88d133db2.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('19', '1', '2019-04-28 00:38:32', null, null, '产品服务.png', '99214651-8cb8-4488-b572-12c6aa21f30a.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('20', '1', '2019-04-28 00:39:09', null, null, '67486aa5-500c-4993-87ad-7e1fbc90ac1a.png', '31fdc83e-7688-41f5-b153-b6816d5dfb06.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('21', '1', '2019-04-28 00:39:22', null, null, '67486aa5-500c-4993-87ad-7e1fbc90ac1a.png', 'ffaf0563-3115-477b-b31d-47a4e80a75eb.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('22', '1', '2019-04-28 00:39:47', null, null, '7e07520d-5b73-4712-800b-16f88d133db2.png', '8928e5d4-933a-4953-9507-f60b78e3ccee.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('23', null, '2019-04-28 17:34:31', null, null, '756b9ca8-562f-4bf5-a577-190dcdd25c29.png', '7d64ba36-adc4-4982-9ec2-8c68db68861b.png');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('24', null, '2019-04-28 17:39:43', null, null, 'timg.jpg', '6483eb26-775c-4fe2-81bf-8dd49ac9b6b1.jpg');
INSERT INTO `t_sys_file_info` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `original_file_name`, `real_file_name`) VALUES ('25', '1', '2019-05-05 15:36:54', null, null, 'timg.jpg', '7fe918a2-c59a-4d17-ad77-f65dd4e163bf.jpg');

-- ----------------------------
-- Records of t_sys_login_log
-- ----------------------------
INSERT INTO `t_sys_login_log` (`id`, `logname`, `userid`, `create_time`, `succeed`, `message`, `ip`) VALUES ('71', '登录日志', '1', '2019-05-10 13:17:43', '成功', null, '127.0.0.1');
INSERT INTO `t_sys_login_log` (`id`, `logname`, `userid`, `create_time`, `succeed`, `message`, `ip`) VALUES ('72', '登录日志', '1', '2019-05-12 13:36:56', '成功', null, '127.0.0.1');

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (1, 1, '2019-7-31 22:04:30', 1, '2023-12-4 17:54:20', 'system', 'layout', 0, 'system', 1, NULL, 1, '系统管理', 2, '0', '[0],', NULL, '/system');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (2, 1, '2019-7-31 22:04:30', 1, '2023-12-4 17:54:26', 'operationMgr', 'layout', 0, 'operation', 1, NULL, 1, '运维管理', 3, '0', '[0],', NULL, '/optionMgr');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (3, 1, '2019-7-31 22:04:30', 1, '2019-4-16 18:59:15', 'mgr', 'views/system/user/index', 0, 'user', 1, NULL, 2, '用户管理', 1, 'system', '[0],[system],', NULL, '/mgr');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (4, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrAdd', NULL, 0, NULL, 0, NULL, 3, '添加用户', 1, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (5, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrEdit', NULL, 0, NULL, 0, NULL, 3, '修改用户', 2, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (6, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrDelete', NULL, 0, NULL, 0, 0, 3, '删除用户', 3, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (7, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrReset', NULL, 0, NULL, 0, 0, 3, '重置密码', 4, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/reset');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (8, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'setRole', NULL, 0, NULL, 0, 0, 3, '分配角色', 5, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/setRole');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (9, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrUnfreeze', NULL, 0, NULL, 0, 0, 3, '解除冻结用户', 6, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/unfreeze');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (10, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrSetRole', NULL, 0, NULL, 0, 0, 3, '分配角色', 7, 'mgr', '[0],[system],[mgr],', NULL, '/mgr/setRole');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (11, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'role', 'views/system/role/index', 0, 'peoples', 1, 0, 2, '角色管理', 2, 'system', '[0],[system],', NULL, '/role');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (12, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleAdd', NULL, 0, NULL, 0, 0, 3, '添加角色', 1, 'role', '[0],[system],[role],', NULL, '/role/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (13, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleEdit', NULL, 0, NULL, 0, 0, 3, '修改角色', 2, 'role', '[0],[system],[role],', NULL, '/role/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (14, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleDelete', NULL, 0, NULL, 0, 0, 3, '删除角色', 3, 'role', '[0],[system],[role],', NULL, '/role/remove');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (15, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleSetAuthority', NULL, 0, NULL, 0, 0, 3, '配置权限', 4, 'role', '[0],[system],[role],', NULL, '/role/setAuthority');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (16, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menu', 'views/system/menu/index', 0, 'menu', 1, 0, 2, '菜单管理', 4, 'system', '[0],[system],', NULL, '/menu');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (17, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menuAdd', NULL, 0, NULL, 0, 0, 3, '添加菜单', 1, 'menu', '[0],[system],[menu],', NULL, '/menu/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (18, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menuEdit', NULL, 0, NULL, 0, 0, 3, '修改菜单', 2, 'menu', '[0],[system],[menu],', NULL, '/menu/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (19, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menuDelete', NULL, 0, NULL, 0, 0, 3, '删除菜单', 3, 'menu', '[0],[system],[menu],', NULL, '/menu/remove');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (20, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dept', 'views/system/dept/index', 0, 'dept', 1, NULL, 2, '部门管理', 3, 'system', '[0],[system],', NULL, '/dept');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (21, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dict', 'views/system/dict/index', 0, 'dict', 1, NULL, 2, '字典管理', 4, 'system', '[0],[system],', NULL, '/dict');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (22, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptEdit', NULL, 0, NULL, 0, NULL, 3, '修改部门', 1, 'dept', '[0],[system],[dept],', NULL, '/dept/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (23, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptDelete', NULL, 0, NULL, 0, NULL, 3, '删除部门', 1, 'dept', '[0],[system],[dept],', NULL, '/dept/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (24, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictAdd', NULL, 0, NULL, 0, NULL, 3, '添加字典', 1, 'dict', '[0],[system],[dict],', NULL, '/dict/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (25, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictEdit', NULL, 0, NULL, 0, NULL, 3, '修改字典', 1, 'dict', '[0],[system],[dict],', NULL, '/dict/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (26, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictDelete', NULL, 0, NULL, 0, NULL, 3, '删除字典', 1, 'dict', '[0],[system],[dict],', NULL, '/dict/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (27, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptList', NULL, 0, NULL, 0, NULL, 3, '部门列表', 5, 'dept', '[0],[system],[dept],', NULL, '/dept/list');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (28, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptDetail', NULL, 0, NULL, 0, NULL, 3, '部门详情', 6, 'dept', '[0],[system],[dept],', NULL, '/dept/detail');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (29, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictList', NULL, 0, NULL, 0, NULL, 3, '字典列表', 5, 'dict', '[0],[system],[dict],', NULL, '/dict/list');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (30, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictDetail', NULL, 0, NULL, 0, NULL, 3, '字典详情', 6, 'dict', '[0],[system],[dict],', NULL, '/dict/detail');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (31, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptAdd', NULL, 0, NULL, 0, NULL, 3, '添加部门', 1, 'dept', '[0],[system],[dept],', NULL, '/dept/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (32, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfg', 'views/system/cfg/index', 0, 'cfg', 1, NULL, 2, '参数管理', 10, 'system', '[0],[system],', NULL, '/cfg');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (33, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfgAdd', NULL, 0, NULL, 0, NULL, 3, '添加参数', 1, 'cfg', '[0],[system],[cfg],', NULL, '/cfg/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (34, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfgEdit', NULL, 0, NULL, 0, NULL, 3, '修改参数', 2, 'cfg', '[0],[system],[cfg],', NULL, '/cfg/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (35, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfgDelete', NULL, 0, NULL, 0, NULL, 3, '删除参数', 3, 'cfg', '[0],[system],[cfg],', NULL, '/cfg/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (36, 1, '2019-7-31 22:04:30', 1, '2020-7-25 18:08:05', 'task', 'views/system/task/index', 0, 'task', 1, NULL, 2, '任务管理', 11, 'system', '[0],[system],', NULL, '/task');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (37, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskAdd', NULL, 0, NULL, 0, NULL, 3, '添加任务', 1, 'task', '[0],[system],[task],', NULL, '/task/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (38, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskEdit', NULL, 0, NULL, 0, NULL, 3, '修改任务', 2, 'task', '[0],[system],[task],', NULL, '/task/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (39, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskDelete', NULL, 0, NULL, 0, NULL, 3, '删除任务', 3, 'task', '[0],[system],[task],', NULL, '/task/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (40, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskLog', 'views/system/task/taskLog', 1, 'task', 1, NULL, 2, '任务日志', 4, 'system', '[0],[system],', NULL, '/taskLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (41, 1, '2019-7-31 22:04:30', 1, '2019-6-2 10:25:31', 'log', 'views/operation/log/index', 0, 'log', 1, NULL, 2, '业务日志', 6, 'operationMgr', '[0],[operationMgr],', NULL, '/log');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (42, 1, '2019-7-31 22:04:30', 1, '2019-6-2 10:25:36', 'loginLog', 'views/operation/loginLog/index', 0, 'logininfor', 1, NULL, 2, '登录日志', 6, 'operationMgr', '[0],[operationMgr],', NULL, '/loginLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (43, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'logClear', NULL, 0, NULL, 0, NULL, 3, '清空日志', 3, 'log', '[0],[system],[log],', NULL, '/log/delLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (44, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'logDetail', NULL, 0, NULL, 0, NULL, 3, '日志详情', 3, 'log', '[0],[system],[log],', NULL, '/log/detail');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (45, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'loginLogClear', NULL, 0, NULL, 0, NULL, 3, '清空登录日志', 1, 'loginLog', '[0],[system],[loginLog],', NULL, '/loginLog/delLoginLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (46, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'loginLogList', NULL, 0, NULL, 0, NULL, 3, '登录日志列表', 2, 'loginLog', '[0],[system],[loginLog],', NULL, '/loginLog/list');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (49, 1, '2019-6-10 21:26:52', 1, '2023-12-5 14:31:28', 'messageMgr', 'layout', 0, 'message', 1, NULL, 1, '消息管理', 4, '0', '[0],', NULL, '/messageMgr');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (50, 1, '2019-6-10 21:27:34', 1, '2023-12-5 14:41:58', 'msg', 'views/message/message/index', 0, 'message', 1, NULL, 2, '历史消息', 1, 'messageMgr', '[0],[messageMgr],', NULL, '/msg');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (51, 1, '2019-6-10 21:27:56', 1, '2019-6-10 21:27:56', 'msgTpl', 'views/message/template/index', 0, 'template', 1, NULL, 2, '消息模板', 2, 'messageMgr', '[0],[messageMgr],', NULL, '/template');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (52, 1, '2019-6-10 21:28:21', 1, '2019-6-10 21:28:21', 'msgSender', 'views/message/sender/index', 0, 'sender', 1, NULL, 2, '消息发送者', 3, 'messageMgr', '[0],[messageMgr],', NULL, '/sender');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (53, 1, '2019-6-10 21:28:21', 1, '2023-12-5 14:47:38', 'msgClear', NULL, 0, '', 0, NULL, 3, '清空历史消息', 1, 'msg', '[0],[messageMgr],[msg],', NULL, '/message/clear');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (54, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgTplEdit', NULL, 0, NULL, 0, NULL, 3, '编辑模板', 1, 'msgTpl', '[0],[messageMgr],[msgTpl]', NULL, '/template/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (55, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgTplDelete', NULL, 0, NULL, 0, NULL, 3, '删除模板', 2, 'msgTpl', '[0],[messageMgr],[msgTpl]', NULL, '/template/remove');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (56, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgSenderEdit', NULL, 0, NULL, 0, NULL, 3, '编辑发送器', 1, 'msgSender', '[0],[messageMgr],[msgSender]', NULL, '/sender/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (57, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgSenderDelete', NULL, 0, NULL, 0, NULL, 3, '删除发送器', 2, 'msgSender', '[0],[messageMgr],[msgSender]', NULL, '/sender/remove');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (58, 1, '2020-12-24 22:16:41', 1, '2020-12-24 22:18:50', 'documentp', 'layout', 0, 'documentation', 1, NULL, 1, '在线文档', 6, '0', '[0],', NULL, '/documentdp');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (59, 1, '2020-12-24 22:18:32', 1, '2020-12-24 22:26:39', 'document', 'views/document/index', 0, 'documentation', 1, NULL, 2, '在线文档', 1, 'documentp', '[0],[documentp],', NULL, '/document');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (60, 1, '2021-7-21 19:46:59', 1, '2023-12-4 17:54:45', 'workFlow', 'layout', 0, 'workflow', 1, NULL, 1, '工作流', 7, '0', '[0],', NULL, '/workflow');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (61, 1, '2021-7-21 19:46:59', NULL, NULL, 'processDefinition', 'views/workflow/processDefinition/index', 0, 'processDefine', 1, NULL, 2, '流程定义', 1, 'workFlow', '[0],[workFlow],', NULL, '/workflow/processDefinition');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (62, 1, '2021-7-21 19:46:59', NULL, NULL, 'workFlowRequest', 'views/workflow/workFlowRequest/index', 0, 'skill', 1, NULL, 2, '流程列表', 2, 'workFlow', '[0],[workFlow],', NULL, '/workflow/request');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (63, 1, '2021-7-21 19:46:59', NULL, NULL, 'processDefinitionEdit', NULL, 0, NULL, 0, NULL, 1, '编辑流程', 3, 'processDefinition', '[0],[workFlow],[processDefinition],', NULL, '/workflow/process/definition/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (64, 1, '2021-7-24 21:53:56', NULL, NULL, 'workFlowRequestAdd', NULL, 0, NULL, 0, NULL, 3, '发起流程', 1, 'workFlowRequest', '[0],[workFlow],[workFlowRequest],', NULL, '/workflow/request/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (65, 1, '2021-7-24 21:53:56', NULL, NULL, 'workFlowTask', 'views/workflow/task/index', 0, 'workFlowTask', 1, NULL, 2, '代办任务', 3, 'workFlow', '[0],[workFlow],', NULL, '/workflow/request/task');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (66, 1, '2023-12-4 17:50:40', 1, '2023-12-9 23:34:13', 'home', 'views/home/index', 0, 'HomeFilled', 1, NULL, 1, '首页', 1, '0', '[0],', NULL, '/home');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `tips`, `url`) VALUES (67, 1, '2023-12-4 17:44:47', 1, '2023-12-4 18:05:52', 'profile', 'views/account/index', 1, 'user', 1, NULL, 2, '个人中心', 6, 'home', '[0],[home],', NULL, '/profile');

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------
INSERT INTO `t_sys_notice` (`id`, `title`, `type`, `content`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', '欢迎光临', '10', '欢迎使用web-flash后台管理系统', '2017-01-11 08:53:20', '1', '2019-01-08 23:30:58', '1');

-- ----------------------------
-- Records of t_sys_operation_log
-- ----------------------------
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('1', '业务日志', '添加参数', '1', 'cn.enilu.flash.api.controller.cms.ArticleMgrController', 'upload', '2019-05-10 13:22:49', '成功', '参数名称=system.app.name');
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('2', '业务日志', '修改参数', '1', 'cn.enilu.flash.api.controller.cms.ArticleMgrController', 'upload', '2019-06-10 13:31:09', '成功', '参数名称=system.app.name');
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('3', '业务日志', '编辑文章', '1', 'cn.enilu.flash.api.controller.cms.ArticleMgrController', 'upload', '2019-07-10 13:22:49', '成功', '参数名称=system.app.name');
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('4', '业务日志', '编辑栏目', '1', 'cn.enilu.flash.api.controller.cms.ArticleMgrController', 'upload', '2019-08-10 13:31:09', '成功', '参数名称=system.app.name');

-- ----------------------------
-- Records of t_sys_relation
-- ----------------------------
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (1, 1, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (2, 3, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (3, 4, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (4, 5, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (5, 6, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (6, 7, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (7, 8, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (8, 9, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (9, 10, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (10, 11, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (11, 12, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (12, 13, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (13, 14, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (14, 15, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (15, 16, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (16, 17, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (17, 18, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (18, 19, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (19, 20, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (20, 22, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (21, 23, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (22, 27, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (23, 28, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (24, 31, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (25, 21, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (26, 24, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (27, 25, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (28, 26, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (29, 29, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (30, 30, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (31, 32, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (32, 33, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (33, 34, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (34, 35, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (35, 36, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (36, 37, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (37, 38, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (38, 39, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (39, 40, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (40, 2, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (41, 41, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (42, 43, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (43, 44, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (44, 42, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (45, 45, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (46, 46, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (47, 49, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (48, 50, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (49, 53, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (50, 51, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (51, 54, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (52, 55, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (53, 52, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (54, 56, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (55, 57, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (56, 58, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (57, 59, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (58, 60, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (59, 61, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (60, 63, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (61, 62, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (62, 64, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (63, 65, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (64, 66, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (65, 67, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (66, 60, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (67, 61, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (68, 63, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (69, 62, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (70, 64, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (71, 65, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (72, 66, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (73, 67, 3);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (74, 1, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (75, 3, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (76, 4, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (77, 5, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (78, 6, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (79, 7, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (80, 8, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (81, 9, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (82, 10, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (83, 11, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (84, 12, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (85, 13, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (86, 14, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (87, 15, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (88, 16, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (89, 17, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (90, 18, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (91, 19, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (92, 20, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (93, 22, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (94, 23, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (95, 27, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (96, 28, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (97, 31, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (98, 21, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (99, 24, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (100, 25, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (101, 26, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (102, 29, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (103, 30, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (104, 32, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (105, 33, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (106, 34, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (107, 35, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (108, 36, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (109, 37, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (110, 38, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (111, 39, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (112, 40, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (113, 66, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (114, 67, 2);

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `deptid`, `name`, `num`, `pid`, `version`) VALUES (1, 1, '2021-7-25 00:55:26', 1, '2023-11-27 11:47:10', 'administrator', 1, '超级管理员', 1, 0, 1);
INSERT INTO `t_sys_role` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `deptid`, `name`, `num`, `pid`, `version`) VALUES (2, 1, '2021-7-25 00:55:26', 1, '2023-12-12 17:38:26', 'developer', 2, '系统管理员', 3, 1, NULL);
INSERT INTO `t_sys_role` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `deptid`, `name`, `num`, `pid`, `version`) VALUES (3, 1, '2021-7-25 00:55:26', 1, '2023-11-28 16:30:28', 'workflowAudit', 1, '审批员', 2, 0, NULL);


-- ----------------------------
-- Records of t_sys_task
-- ----------------------------
INSERT INTO `t_sys_task` (`id`, `name`, `job_group`, `job_class`, `note`, `cron`, `data`, `exec_at`, `exec_result`, `disabled`, `create_time`, `create_by`, `concurrent`, `modify_time`, `modify_by`) VALUES ('1', '测试任务', 'default', 'cn.enilu.flash.service.task.job.HelloJob', '测试任务,每30分钟执行一次', '0 0/30 * * * ?', '{\n\"appname\": \"web-flash\",\n\"version\":1\n}\n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            ', '2019-03-27 11:47:00', '执行成功', '0', '2018-12-28 09:54:00', '1', '0', '2019-03-27 11:47:11', '-1');



-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `account`, `avatar`, `birthday`, `deptid`, `email`, `name`, `password`, `phone`, `roleid`, `salt`, `sex`, `status`, `version`) VALUES (-1, NULL, '2016-1-29 08:49:53', 1, '2019-3-20 23:45:24', 'system', NULL, NULL, NULL, NULL, '应用系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `account`, `avatar`, `birthday`, `deptid`, `email`, `name`, `password`, `phone`, `roleid`, `salt`, `sex`, `status`, `version`) VALUES (1, NULL, '2016-1-29 08:49:53', 1, '2023-11-27 11:45:14', 'admin', NULL, '2020-8-1', 1, 'eniluzt@qq.com', '超级管理员', 'b5a51391f271f062867e5984e2fcffee', '15011112222', '1', '8pgby', 1, 1, 2);
INSERT INTO `t_sys_user` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `account`, `avatar`, `birthday`, `deptid`, `email`, `name`, `password`, `phone`, `roleid`, `salt`, `sex`, `status`, `version`) VALUES (2, NULL, '2018-9-13 17:21:02', 1, '2023-12-12 17:48:50', 'developer', NULL, '2017-12-18', 3, 'eniluzt@qq.com', '系统管理员', 'a7d02ce3a0d632d71a1498feb0e6e9e6', '15022222222', '2,', 'vscp9', 1, 1, NULL);
INSERT INTO `t_sys_user` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `account`, `avatar`, `birthday`, `deptid`, `email`, `name`, `password`, `phone`, `roleid`, `salt`, `sex`, `status`, `version`) VALUES (3, 1, '2021-7-25 00:58:20', 1, '2021-7-25 00:58:37', 'auditUser', NULL, '2021-7-21', 1, 'audit@qq.com', '审批人员（测试流程）', '068210f42bcfb9fa14291b9b0673b761', '15010101010', '3,', 'o050t', 1, 1, NULL);

-- ----------------------------
-- Records of t_test_boy
-- ----------------------------
INSERT INTO `t_test_boy` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `age`, `birthday`, `has_girl_friend`, `name`) VALUES ('1', null, null, null, null, '18', '2000-01-01', '1', '张三');


-- ----------------------------
-- Records of t_message_sender
-- ----------------------------
INSERT INTO `t_message_sender` VALUES ('1', '1', '2020-12-16 12:02:17', null, null, 'tencentSmsSender', '腾讯短信服务');
INSERT INTO `t_message_sender` VALUES ('2', '1', '2020-12-16 12:02:17', null, null, 'defaultEmailSender', '默认邮件发送器');
INSERT INTO `t_message_sender` VALUES ('3', '1', '2020-12-16 12:02:17', null, null, 'aliSmsSender', '阿里云短信服务');
-- ----------------------------
-- Records of t_message_template
-- ----------------------------
INSERT INTO `t_message_template` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `cond`, `content`, `id_message_sender`, `title`, `type`, `remote_tpl_code`) VALUES (1, NULL, NULL, NULL, NULL, 'REGISTER_CODE', '注册页面，点击获取验证码', '【腾讯云】校验码{1}，请于5分钟内完成验证，如非本人操作请忽略本短信。', 1, '注册验证码', '0', NULL);
INSERT INTO `t_message_template` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `cond`, `content`, `id_message_sender`, `title`, `type`, `remote_tpl_code`) VALUES (2, NULL, NULL, NULL, NULL, 'EMAIL_TEST', '测试发送', '你好:{1},欢迎使用{2}', 2, '测试邮件', '1', NULL);
INSERT INTO `t_message_template` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `cond`, `content`, `id_message_sender`, `title`, `type`, `remote_tpl_code`) VALUES (3, NULL, NULL, NULL, NULL, 'EMAIL_HTML_TEMPLATE_TEST', '测试发送模板邮件', '你好<strong>${userName}</strong>欢迎使用<font color=\"red\">${appName}</font>,这是html模板邮件', 2, '测试发送模板邮件', '1', NULL);
INSERT INTO `t_message_template` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `cond`, `content`, `id_message_sender`, `title`, `type`, `remote_tpl_code`) VALUES (4, NULL, NULL, NULL, NULL, 'ALI_SMS_CODE', '测试发送阿里云短信', '您的验证码${code}，该验证码5分钟内有效，请勿泄漏于他人！', 3, '阿里云短信验证码', '0', NULL);

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('1', null, '2019-06-10 21:20:16', null, null, '【腾讯云】校验码1032，请于5分钟内完成验证，如非本人操作请忽略本短信。', '15011112222', '2', 'REGISTER_CODE', '0');
