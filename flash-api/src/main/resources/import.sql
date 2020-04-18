
-- ----------------------------
-- Records of t_cms_article
-- ----------------------------
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('1', '1', '2019-03-09 16:24:58', '1', '2019-05-10 13:22:49', 'enilu', '<div id=\"articleContent\" class=\"content\">\n<div class=\"ad-wrap\">\n<p style=\"margin: 0 0 10px 0;\">一般我们都有这样的需求：我需要知道库中的数据是由谁创建，什么时候创建，最后一次修改时间是什么时候，最后一次修改人是谁。web-flash最新代码已经实现该需求，具体实现方式网上有很多资料，这里做会搬运工，将web-flash的实现步骤罗列如下：%%</p>\n</div>\n<p>在Spring jpa中可以通过在实体bean的属性或者方法上添加以下注解来实现上述需求@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy。</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>@CreatedDate 表示该字段为创建时间时间字段，在这个实体被insert的时候，会设置值</p>\n</li>\n<li>\n<p>@CreatedBy 表示该字段为创建人，在这个实体被insert的时候，会设置值</p>\n</li>\n<li>\n<p>@LastModifiedDate 最后修改时间 实体被update的时候会设置</p>\n</li>\n<li>\n<p>@LastModifiedBy 最后修改人 实体被update的时候会设置</p>\n</li>\n</ul>\n<h2>使用方式</h2>\n<h3>实体类添加注解</h3>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>首先在实体中对应的字段加上上述4个注解</p>\n</li>\n<li>\n<p>在web-flash中我们提取了一个基础实体类BaseEntity，并将对应的字段添加上述4个注解,所有需要记录维护信息的表对应的实体都集成该类</p>\n</li>\n</ul>\n<pre>import&nbsp;org.springframework.data.annotation.CreatedBy;\nimport&nbsp;org.springframework.data.annotation.CreatedDate;\nimport&nbsp;org.springframework.data.annotation.LastModifiedBy;\nimport&nbsp;org.springframework.data.annotation.LastModifiedDate;\nimport&nbsp;javax.persistence.Column;\nimport&nbsp;javax.persistence.GeneratedValue;\nimport&nbsp;javax.persistence.Id;\nimport&nbsp;javax.persistence.MappedSuperclass;\nimport&nbsp;java.io.Serializable;\nimport&nbsp;java.util.Date;\n@MappedSuperclass\n@Data\npublic&nbsp;abstract&nbsp;class&nbsp;BaseEntity&nbsp;implements&nbsp;Serializable&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;@Id\n&nbsp;&nbsp;&nbsp;&nbsp;@GeneratedValue\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Long&nbsp;id;\n&nbsp;&nbsp;&nbsp;&nbsp;@CreatedDate\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"create_time\",columnDefinition=\"DATETIME&nbsp;COMMENT&nbsp;\'创建时间/注册时间\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Date&nbsp;createTime;\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"create_by\",columnDefinition=\"bigint&nbsp;COMMENT&nbsp;\'创建人\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;@CreatedBy\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Long&nbsp;createBy;\n&nbsp;&nbsp;&nbsp;&nbsp;@LastModifiedDate\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"modify_time\",columnDefinition=\"DATETIME&nbsp;COMMENT&nbsp;\'最后更新时间\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Date&nbsp;modifyTime;\n&nbsp;&nbsp;&nbsp;&nbsp;@LastModifiedBy\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"modify_by\",columnDefinition=\"bigint&nbsp;COMMENT&nbsp;\'最后更新人\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Long&nbsp;modifyBy;\n}</pre>\n<h3>实现AuditorAware接口返回操作人员的id</h3>\n<p>配置完上述4个注解后，在jpa.save方法被调用的时候，时间字段会自动设置并插入数据库，但是CreatedBy和LastModifiedBy并没有赋值 这两个信息需要实现AuditorAware接口来返回操作人员的id</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>首先需要在项目启动类添加@EnableJpaAuditing 注解来启用审计功能</p>\n</li>\n</ul>\n<pre>@SpringBootApplication\n@EnableJpaAuditing\npublic&nbsp;class&nbsp;AdminApplication&nbsp;extends&nbsp;WebMvcConfigurerAdapter&nbsp;{\n&nbsp;//省略\n}</pre>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>然后实现AuditorAware接口返回操作人员的id</p>\n</li>\n</ul>\n<pre>@Configuration\npublic&nbsp;class&nbsp;UserIDAuditorConfig&nbsp;implements&nbsp;AuditorAware&lt;Long&gt;&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;@Override\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;Long&nbsp;getCurrentAuditor()&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ShiroUser&nbsp;shiroUser&nbsp;=&nbsp;ShiroKit.getUser();\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(shiroUser!=null){\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;shiroUser.getId();\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;null;\n&nbsp;&nbsp;&nbsp;&nbsp;}\n}</pre>\n</div>', 'web-flash 将所有表增加维护人员和维护时间信息', '1', '1');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('2', '1', '2019-03-09 16:24:58', '1', '2019-03-23 23:12:16', 'enilu.cn', '<div id=\"articleContent\" class=\"content\">\n<div class=\"ad-wrap\">\n<p style=\"margin: 0 0 10px 0;\"><a style=\"color: #a00; font-weight: bold;\" href=\"https://my.oschina.net/u/3985214/blog/3018099?tdsourcetag=s_pcqq_aiomsg\" target=\"_blank\" rel=\"noopener\" data-traceid=\"news_detail_above_text_link_1\" data-tracepid=\"news_detail_above_text_link\">开发十年，就只剩下这套架构体系了！ &gt;&gt;&gt;</a>&nbsp;&nbsp;<img style=\"max-height: 32px; max-width: 32px;\" src=\"https://www.oschina.net/img/hot3.png\" align=\"\" /></p>\n</div>\n<h3>国际化</h3>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>web-flash实现国际化了.</p>\n</li>\n<li>\n<p>不了解上面两个的区别的同学可以再回顾下这个<a href=\"http://www.enilu.cn/web-flash/base/web-flash.html\">文档</a></p>\n</li>\n<li>\n<p>web-flash实现国际化的方式参考vue-element-admin的&nbsp;<a href=\"https://panjiachen.github.io/vue-element-admin-site/zh/guide/advanced/i18n.html\" target=\"_blank\" rel=\"noopener\">官方文档</a>,这里不再赘述,强烈建议你先把文档读了之后再看下面的内容。</p>\n</li>\n</ul>\n<h3>默认约定</h3>\n<p>针对网站资源进行国际园涉及到的国际化资源的管理维护，这里给出一些web-flash的资源分类建议，当然，你也可以根据你的实际情况进行调整。</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>src/lang/为国际化资源目录,目前提供了英文（en.js）和中文(zh.js)的两种语言实现。</p>\n</li>\n<li>\n<p>目前资源语言资源文件中是json配置主要有以下几个节点：</p>\n</li>\n<ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\">\n<li>\n<p>route 左侧菜单资源</p>\n</li>\n<li>\n<p>navbar 顶部导航栏资源</p>\n</li>\n<li>\n<p>button 公共的按钮资源，比如：添加、删除、修改、确定、取消之类等等</p>\n</li>\n<li>\n<p>common 其他公共的资源，比如一些弹出框标题、提示信息、label等等</p>\n</li>\n<li>\n<p>login 登录页面资源</p>\n</li>\n<li>\n<p>config 参数管理界面资源</p>\n</li>\n</ul>\n<li>\n<p>目前针对具体的页面资源只做了登录和参数管理两个页面，其他具体业务界面仅针对公共的按钮做了国际化，你可以参考config页面资源进行配置进行进一步配置：/src/views/cfg/</p>\n</li>\n<li>\n<p>如果你有其他资源在上面对应的节点添加即可，针对每个页面特有的资源以页面名称作为几点进行维护，这样方便记忆和维护，不容易出错。</p>\n</li>\n</ul>\n<h3>添加新的语言支持</h3>\n<p>如果英文和中文两种语言不够，那么你可以通过下面步骤添加语言支持</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>在src/lang/目录下新增对应的资源文件</p>\n</li>\n<li>\n<p>在src/lang/index.js中import对应的资源文件</p>\n</li>\n<li>\n<p>在src/lang/index.js中的messages变量中加入新的语言声明</p>\n</li>\n<li>\n<p>在src/components/LangSelect/index.vue的语言下拉框中增加新的语言选项</p>\n</li>\n</ul>\n<h3>演示地址</h3>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>vue版本后台管理&nbsp;<a href=\"http://106.75.35.53:8082/vue/#/login\" target=\"_blank\" rel=\"noopener\">http://106.75.35.53:8082/vue/#/login</a></p>\n</li>\n<li>CMS内容管理系统的h5前端demo:<a href=\"http://106.75.35.53:8082/mobile/#/index\" target=\"_blank\" rel=\"noopener\">http://106.75.35.53:8082/mobile/#/index</a></li>\n</ul>\n</div>', 'web-flash1.0.1 发布，增加国际化和定时任务管理功能', '1', '2');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('3', '1', '2019-03-09 16:24:58', '1', '2019-04-28 17:39:52', 'enilu.cn', '<div class=\"content\" id=\"articleContent\">\r\n                        <div class=\"ad-wrap\">\r\n                                                    <p style=\"margin:0 0 10px 0;\"><a data-traceid=\"news_detail_above_text_link_1\" data-tracepid=\"news_detail_above_text_link\" style=\"color:#A00;font-weight:bold;\" href=\"https://my.oschina.net/u/3985214/blog/3018099?tdsourcetag=s_pcqq_aiomsg\" target=\"_blank\">开发十年，就只剩下这套架构体系了！ &gt;&gt;&gt;</a>&nbsp;&nbsp;<img src=\"https://www.oschina.net/img/hot3.png\" align=\"\" style=\"max-height: 32px; max-width: 32px;\"></p>\r\n                                    </div>\r\n                        <p>web-flash使用的Spring Boot从1.5.1升级到2.1.1</p><p>下面为升级过程</p><ul class=\" list-paddingleft-2\"><li><p>版本升级</p><pre>&lt;spring.boot.version&gt;2.1.1.RELEASE&lt;/spring.boot.version&gt;\r\n&lt;springframework.version&gt;5.1.3.RELEASE&lt;springframework.version&gt;</pre></li><li><p>配置增加</p><pre>spring.main.allow-bean-definition-overriding=true\r\nspring.jpa.hibernate.use-new-id-generator-mappings=false</pre></li></ul><ul class=\" list-paddingleft-2\"><li><p>审计功能调整，调整后代码:</p><pre>@Configuration\r\npublic&nbsp;class&nbsp;UserIDAuditorConfig&nbsp;implements&nbsp;AuditorAware&lt;Long&gt;&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;@Override\r\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;Optional&lt;Long&gt;&nbsp;getCurrentAuditor()&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ShiroUser&nbsp;shiroUser&nbsp;=&nbsp;ShiroKit.getUser();\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(shiroUser!=null){\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;Optional.of(shiroUser.getId());\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;null;\r\n&nbsp;&nbsp;&nbsp;&nbsp;}\r\n}</pre></li><li><p>repository调整</p></li><ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\"><li><p>之前的 delete(Long id)方法没有了，替换为：deleteById(Long id)</p></li><li><p>之前的 T findOne(Long id)方法没有了，替换为：		</p><pre>Optional&lt;T&gt;&nbsp;optional&nbsp;=&nbsp;***Repository.findById(id);\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if&nbsp;(optional.isPresent())&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;optional.get();\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;null;</pre></li></ul><li><p>随着这次Spring Boot的升级也顺便做了一些其他内容的调整和重构</p></li><ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\"><li><p>springframework也从4.3.5.RELEASE升级到5.1.3.RELEASE</p></li><li><p>为减小复杂度service去掉接口和实现类的结构，基础功能的service直接使用实现类；当然具体业务中如果有需求你也可以这没用</p></li><li><p>去掉了一些暂时用不到的maven依赖</p></li><li><p>完善了基础功能的审计功能(之前有介绍审计功能的实现翻番，后续会专门发一篇文档来说明审计功能在系统总的具体用法，当然聪明的你看代码就知道了)</p></li></ul></ul>\r\n                    </div>', 'web-flash 升级 Spring Boot 到 2.1.1 版本', '1', '1');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('4', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:34:21', 'enilu.cn', 'H5通用官网系统', 'H5通用官网系统', '2', '17');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('5', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:34:36', 'enilu.cn', 'H5通用论坛系统', 'H5通用论坛系统', '2', '18');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('6', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:38:33', 'enilu.cn', '官网建设方案', '官网建设方案', '3', '19');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('7', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:39:48', 'enilu.cn', '论坛建设方案', '论坛建设方案', '3', '22');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('8', '1', '2019-03-09 16:24:58', '1', '2019-04-28 17:39:52', 'enilu.cn', '案例1', '案例1', '4', '3');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('9', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:39:11', 'enilu.cn', '案例2', '案例2', '4', '20');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('14', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:39:25', 'test5', '<p>aaaaa<img class=\"wscnph\" src=\"http://127.0.0.1:8082/file/download?idFile=12\" /></p>', 'IDEA的代码生成插件发布啦', '4', '21');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('15', '1', '2019-04-28 17:39:52', '1', '2019-05-05 15:36:57', 'enilu', '<p><img class=\"wscnph\" src=\"http://127.0.0.1:8082/file/download?idFile=24\" /></p>', '程序员头冷', '1', '25');

-- ----------------------------
-- Records of t_cms_banner
-- ----------------------------
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('1', '1', '2019-03-09 16:29:03', null, null, '不打开链接', 'javascript:', 'index', '1');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('2', '1', '2019-03-09 16:29:03', null, null, '打打开站内链接', '/contact', 'index', '2');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('3', '1', '2019-03-09 16:29:03', null, null, '打开外部链接', 'http://www.baidu.com', 'index', '6');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('4', '1', '2019-03-09 16:29:03', null, null, '不打开链接', 'javascript:', 'product', '1');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('5', '1', '2019-03-09 16:29:03', null, null, '打打开站内链接', '/contact', 'product', '2');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('6', '1', '2019-03-09 16:29:03', null, null, '打开外部链接', 'http://www.baidu.com', 'product', '6');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('7', '1', '2019-03-09 16:29:03', null, null, '不打开链接', 'javascript:', 'solution', '1');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('8', '1', '2019-03-09 16:29:03', null, null, '打打开站内链接', '/contact', 'solution', '2');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('9', '1', '2019-03-09 16:29:03', null, null, '打开外部链接', 'http://www.baidu.com', 'solution', '6');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('10', '1', '2019-03-09 16:29:03', null, null, '不打开链接', 'javascript:', 'case', '1');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('11', '1', '2019-03-09 16:29:03', null, null, '打打开站内链接', '/contact', 'case', '2');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('12', '1', '2019-03-09 16:29:03', null, null, '打开外部链接', 'http://www.baidu.com', 'case', '6');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('14', '1', '2019-03-09 16:29:03', null, null, '不打开链接', 'javascript:', 'news', '1');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('15', '1', '2019-03-09 16:29:03', null, null, '打打开站内链接', '/contact', 'news', '2');
INSERT INTO `t_cms_banner` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `title`, `url`, `type`, `id_file`) VALUES ('16', '1', '2019-03-09 16:29:03', null, null, '打开外部链接', 'http://www.baidu.com', 'news', '6');

-- ----------------------------
-- Records of t_cms_channel
-- ----------------------------
INSERT INTO `t_cms_channel` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `code`) VALUES ('1', null, null, '1', '2019-03-13 22:52:46', '动态资讯', 'news');
INSERT INTO `t_cms_channel` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `code`) VALUES ('2', null, null, '1', '2019-03-13 22:53:11', '产品服务', 'product');
INSERT INTO `t_cms_channel` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `code`) VALUES ('3', null, null, '1', '2019-03-13 22:53:37', '解决方案', 'solution');
INSERT INTO `t_cms_channel` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `name`, `code`) VALUES ('4', null, null, '1', '2019-03-13 22:53:41', '精选案例', 'case');

-- ----------------------------
-- Records of t_cms_contacts
-- ----------------------------
INSERT INTO `t_cms_contacts` VALUES ('1', null, '2019-07-31 17:44:27', null, '2019-07-31 17:44:27', 'test@qq.com', '15011111111', '测试联系，哈哈哈', '张三');

-- ----------------------------
-- Records of t_snow_product
-- ----------------------------

-- ----------------------------
-- Records of t_sys_cfg
-- ----------------------------
INSERT INTO `t_sys_cfg` VALUES ('1', null, null, '1', '2019-04-15 21:36:07', '应用名称update by 2019-03-27 11:47:04', 'system.app.name', 'web-flash');
INSERT INTO `t_sys_cfg` VALUES ('2', null, null, '1', '2019-04-15 21:36:17', '系统默认上传文件路径', 'system.file.upload.path', '/data/web-flash/runtime/upload');
INSERT INTO `t_sys_cfg` VALUES ('3', null, null, '1', '2019-04-15 21:36:17', '腾讯sms接口appid', 'api.tencent.sms.appid', '1400219425');
INSERT INTO `t_sys_cfg` VALUES ('4', null, null, '1', '2019-04-15 21:36:17', '腾讯sms接口appkey', 'api.tencent.sms.appkey', '5f71ed5325f3b292946530a1773e997a');
INSERT INTO `t_sys_cfg` VALUES ('5', null, null, '1', '2019-04-15 21:36:17', '腾讯sms接口签名参数', 'api.tencent.sms.sign', '需要去申请咯');

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('24', '1', '0', '[0],', '总公司', '总公司', '', null, null, null, null, null);
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('25', '2', '24', '[0],[24],', '开发部', '开发部', '', null, null, null, null, null);
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('26', '3', '24', '[0],[24],', '运营部', '运营部', '', null, null, null, null, null);
INSERT INTO `t_sys_dept` (`id`, `num`, `pid`, `pids`, `simplename`, `fullname`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('27', '4', '24', '[0],[24],', '战略部', '战略部', '', null, null, null, null, null);

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('16', '0', '0', '状态', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('17', '1', '16', '启用', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('18', '2', '16', '禁用', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('29', '0', '0', '性别', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('30', '1', '29', '男', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('31', '2', '29', '女', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('35', '0', '0', '账号状态', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('36', '1', '35', '启用', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('37', '2', '35', '冻结', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('38', '3', '35', '已删除', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('53', '0', '0', '证件类型', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('54', '1', '53', '身份证', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('55', '2', '53', '护照', null, null, null, null, null);
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('68', '0', '0', '是否', null, '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('69', '1', '68', '是', null, '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');
INSERT INTO `t_sys_dict` (`id`, `num`, `pid`, `name`, `tips`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('70', '0', '68', '否', null, '2019-01-13 14:18:21', '1', '2019-01-13 14:18:21', '1');

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
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (1, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'system', 'layout', 0, 'system', 1, 1, 1, '系统管理', 1, '0', '[0],', 1, NULL, '/system');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (2, 1, '2019-7-31 22:04:30', 1, '2019-3-11 22:25:38', 'cms', 'layout', 0, 'documentation', 1, NULL, 1, 'CMS管理', 3, '0', '[0],', 1, NULL, '/cms');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (3, 1, '2019-7-31 22:04:30', 1, '2019-6-2 10:09:09', 'operationMgr', 'layout', 0, 'operation', 1, NULL, 1, '运维管理', 2, '0', '[0],', 1, NULL, '/optionMgr');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (4, 1, '2019-7-31 22:04:30', 1, '2019-4-16 18:59:15', 'mgr', 'views/system/user/index', 0, 'user', 1, NULL, 2, '用户管理', 1, 'system', '[0],[system],', 1, NULL, '/mgr');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (5, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrAdd', NULL, 0, NULL, 0, NULL, 3, '添加用户', 1, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (6, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrEdit', NULL, 0, NULL, 0, NULL, 3, '修改用户', 2, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (7, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrDelete', NULL, 0, NULL, 0, 0, 3, '删除用户', 3, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (8, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrReset', NULL, 0, NULL, 0, 0, 3, '重置密码', 4, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/reset');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (9, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrFreeze', NULL, 0, NULL, 0, 0, 3, '冻结用户', 5, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/freeze');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (10, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrUnfreeze', NULL, 0, NULL, 0, 0, 3, '解除冻结用户', 6, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/unfreeze');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (11, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'mgrSetRole', NULL, 0, NULL, 0, 0, 3, '分配角色', 7, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/setRole');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (12, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'role', 'views/system/role/index', 0, 'peoples', 1, 0, 2, '角色管理', 2, 'system', '[0],[system],', 1, NULL, '/role');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (13, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleAdd', NULL, 0, NULL, 0, 0, 3, '添加角色', 1, 'role', '[0],[system],[role],', 1, NULL, '/role/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (14, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleEdit', NULL, 0, NULL, 0, 0, 3, '修改角色', 2, 'role', '[0],[system],[role],', 1, NULL, '/role/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (15, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleDelete', NULL, 0, NULL, 0, 0, 3, '删除角色', 3, 'role', '[0],[system],[role],', 1, NULL, '/role/remove');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (16, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'roleSetAuthority', NULL, 0, NULL, 0, 0, 3, '配置权限', 4, 'role', '[0],[system],[role],', 1, NULL, '/role/setAuthority');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (17, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menu', 'views/system/menu/index', 0, 'menu', 1, 0, 2, '菜单管理', 4, 'system', '[0],[system],', 1, NULL, '/menu');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (18, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menuAdd', NULL, 0, NULL, 0, 0, 3, '添加菜单', 1, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (19, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menuEdit', NULL, 0, NULL, 0, 0, 3, '修改菜单', 2, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/edit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (20, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'menuDelete', NULL, 0, NULL, 0, 0, 3, '删除菜单', 3, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/remove');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (21, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dept', 'views/system/dept/index', 0, 'dept', 1, NULL, 2, '部门管理', 3, 'system', '[0],[system],', 1, NULL, '/dept');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (22, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dict', 'views/system/dict/index', 0, 'dict', 1, NULL, 2, '字典管理', 4, 'system', '[0],[system],', 1, NULL, '/dict');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (23, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptEdit', NULL, 0, NULL, 0, NULL, 3, '修改部门', 1, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (24, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptDelete', NULL, 0, NULL, 0, NULL, 3, '删除部门', 1, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (25, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictAdd', NULL, 0, NULL, 0, NULL, 3, '添加字典', 1, 'dict', '[0],[system],[dict],', 1, NULL, '/dict/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (26, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictEdit', NULL, 0, NULL, 0, NULL, 3, '修改字典', 1, 'dict', '[0],[system],[dict],', 1, NULL, '/dict/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (27, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictDelete', NULL, 0, NULL, 0, NULL, 3, '删除字典', 1, 'dict', '[0],[system],[dict],', 1, NULL, '/dict/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (28, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptList', NULL, 0, NULL, 0, NULL, 3, '部门列表', 5, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/list');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (29, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptDetail', NULL, 0, NULL, 0, NULL, 3, '部门详情', 6, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/detail');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (30, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictList', NULL, 0, NULL, 0, NULL, 3, '字典列表', 5, 'dict', '[0],[system],[dict],', 1, NULL, '/dict/list');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (31, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'dictDetail', NULL, 0, NULL, 0, NULL, 3, '字典详情', 6, 'dict', '[0],[system],[dict],', 1, NULL, '/dict/detail');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (32, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deptAdd', NULL, 0, NULL, 0, NULL, 3, '添加部门', 1, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (33, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfg', 'views/system/cfg/index', 0, 'cfg', 1, NULL, 2, '参数管理', 10, 'system', '[0],[system],', 1, NULL, '/cfg');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (34, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfgAdd', NULL, 0, NULL, 0, NULL, 3, '添加系统参数', 1, 'cfg', '[0],[system],[cfg],', 1, NULL, '/cfg/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (35, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfgEdit', NULL, 0, NULL, 0, NULL, 3, '修改系统参数', 2, 'cfg', '[0],[system],[cfg],', 1, NULL, '/cfg/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (36, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'cfgDelete', NULL, 0, NULL, 0, NULL, 3, '删除系统参数', 3, 'cfg', '[0],[system],[cfg],', 1, NULL, '/cfg/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (37, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'task', 'views/system/task/index', 0, 'task', 1, NULL, 2, '任务管理', 11, 'system', '[0],[system],', 1, NULL, '/task');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (38, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskAdd', NULL, 0, NULL, 0, NULL, 3, '添加任务', 1, 'task', '[0],[system],[task],', 1, NULL, '/task/add');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (39, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskEdit', NULL, 0, NULL, 0, NULL, 3, '修改任务', 2, 'task', '[0],[system],[task],', 1, NULL, '/task/update');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (40, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskDelete', NULL, 0, NULL, 0, NULL, 3, '删除任务', 3, 'task', '[0],[system],[task],', 1, NULL, '/task/delete');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (41, 1, '2019-3-11 22:29:54', 1, '2019-3-11 22:29:54', 'channel', 'views/cms/channel/index', 0, 'channel', 1, NULL, 2, '栏目管理', 1, 'cms', '[0],[cms],', 1, NULL, '/channel');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (42, 1, '2019-3-11 22:30:17', 1, '2019-3-11 22:30:17', 'article', 'views/cms/article/index', 0, 'documentation', 1, NULL, 2, '文章管理', 2, 'cms', '[0],[cms],', 1, NULL, '/article');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (43, 1, '2019-3-11 22:30:52', 1, '2019-3-11 22:30:52', 'banner', 'views/cms/banner/index', 0, 'banner', 1, NULL, 2, 'banner管理', 3, 'cms', '[0],[cms],', 1, NULL, '/banner');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (44, 1, '2019-3-18 19:45:37', 1, '2019-3-18 19:45:37', 'contacts', 'views/cms/contacts/index', 0, 'contacts', 1, NULL, 2, '邀约管理', 4, 'cms', '[0],[cms],', 1, NULL, '/contacts');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (45, 1, '2019-3-19 10:25:05', 1, '2019-3-19 10:25:05', 'file', 'views/cms/file/index', 0, 'file', 1, NULL, 2, '文件管理', 5, 'cms', '[0],[cms],', 1, NULL, '/fileMgr');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (46, 1, '2019-3-11 22:30:17', 1, '2019-3-11 22:30:17', 'editArticle', 'views/cms/article/edit.vue', 0, 'articleEdit', 1, NULL, 2, '新建文章', 1, 'cms', '[0],[cms],', 1, NULL, '/cms/articleEdit');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (47, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'taskLog', 'views/system/taskLog/index', 1, 'task', 1, NULL, 2, '任务日志', 4, 'system', '[0],[system],', 1, NULL, '/taskLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (48, 1, '2019-7-31 22:04:30', 1, '2019-6-2 10:25:31', 'log', 'views/operation/log/index', 0, 'log', 1, NULL, 2, '业务日志', 6, 'operationMgr', '[0],[operationMgr],', 1, NULL, '/log');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (49, 1, '2019-7-31 22:04:30', 1, '2019-6-2 10:25:36', 'loginLog', 'views/operation/loginLog/index', 0, 'logininfor', 1, NULL, 2, '登录日志', 6, 'operationMgr', '[0],[operationMgr],', 1, NULL, '/loginLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (50, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'logClear', NULL, 0, NULL, 0, NULL, 3, '清空日志', 3, 'log', '[0],[system],[log],', 1, NULL, '/log/delLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (51, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'logDetail', NULL, 0, NULL, 0, NULL, 3, '日志详情', 3, 'log', '[0],[system],[log],', 1, NULL, '/log/detail');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (52, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'loginLogClear', NULL, 0, NULL, 0, NULL, 3, '清空登录日志', 1, 'loginLog', '[0],[system],[loginLog],', 1, NULL, '/loginLog/delLoginLog');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (53, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'loginLogList', NULL, 0, NULL, 0, NULL, 3, '登录日志列表', 2, 'loginLog', '[0],[system],[loginLog],', 1, NULL, '/loginLog/list');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (54, 1, '2019-6-2 10:10:20', 1, '2019-6-2 10:10:20', 'druid', 'views/operation/druid/index', 0, 'monitor', 1, NULL, 2, '数据库管理', 1, 'operationMgr', '[0],[operationMgr],', 1, NULL, '/druid');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (55, 1, '2019-6-2 10:10:20', 1, '2019-6-2 10:10:20', 'swagger', 'views/operation/api/index', 0, 'swagger', 1, NULL, 2, '接口文档', 2, 'operationMgr', '[0],[operationMgr],', 1, NULL, '/swagger');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (56, 1, '2019-6-10 21:26:52', 1, '2019-6-10 21:26:52', 'messageMgr', 'layout', 0, 'message', 1, NULL, 1, '消息管理', 4, '0', '[0],', 1, NULL, '/message');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (57, 1, '2019-6-10 21:27:34', 1, '2019-6-10 21:27:34', 'msg', 'views/message/message/index', 0, 'message', 1, NULL, 2, '历史消息', 1, 'messageMgr', '[0],[messageMgr],', 1, NULL, '/history');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (58, 1, '2019-6-10 21:27:56', 1, '2019-6-10 21:27:56', 'msgTpl', 'views/message/template/index', 0, 'template', 1, NULL, 2, '消息模板', 2, 'messageMgr', '[0],[messageMgr],', 1, NULL, '/template');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (59, 1, '2019-6-10 21:28:21', 1, '2019-6-10 21:28:21', 'msgSender', 'views/message/sender/index', 0, 'sender', 1, NULL, 2, '消息发送者', 3, 'messageMgr', '[0],[messageMgr],', 1, NULL, '/sender');
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (60, 1, '2019-6-10 21:28:21', 1, '2019-6-10 21:28:21', 'msgClear', NULL, 0, NULL, 1, NULL, 2, '清空历史消息', 3, 'messageMgr', '[0],[messageMgr],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (61, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgTplEdit', NULL, 0, NULL, 0, NULL, 3, '编辑消息模板', 1, 'msgTpl', '[0],[messageMgr],[msgTpl]', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (62, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgTplDelete', NULL, 0, NULL, 0, NULL, 3, '删除消息模板', 2, 'msgTpl', '[0],[messageMgr],[msgTpl]', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (63, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgSenderEdit', NULL, 0, NULL, 0, NULL, 3, '编辑消息发送器', 1, 'msgSender', '[0],[messageMgr],[msgSender]', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (64, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'msgSenderDelete', NULL, 0, NULL, 0, NULL, 3, '删除消息发送器', 2, 'msgSender', '[0],[messageMgr],[msgSender]', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (65, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'fileUpload', NULL, 0, NULL, 0, NULL, 3, '上传文件', 1, 'file', '[0],[cms],[file],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (66, 1, '2019-7-31 21:51:33', 1, '2019-7-31 21:51:33', 'bannerEdit', NULL, 0, NULL, 0, NULL, 3, '编辑banner', 1, 'banner', '[0],[cms],[banner],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (67, 1, '2019-7-31 21:51:33', 1, '2019-7-31 21:51:33', 'bannerDelete', NULL, 0, NULL, 0, NULL, 3, '删除banner', 2, 'banner', '[0],[cms],[banner],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (68, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'channelEdit', NULL, 0, NULL, 0, NULL, 3, '编辑栏目', 1, 'channel', '[0],[cms],[channel],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (69, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'channelDelete', NULL, 0, NULL, 0, NULL, 3, '删除栏目', 2, 'channel', '[0],[cms],[channel],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `code`, `component`, `hidden`, `icon`, `ismenu`, `isopen`, `levels`, `name`, `num`, `pcode`, `pcodes`, `status`, `tips`, `url`) VALUES (70, 1, '2019-7-31 22:04:30', 1, '2019-7-31 22:04:30', 'deleteArticle', NULL, 0, NULL, 0, NULL, 3, '删除文章', 2, 'article', '[0],[cms],[article]', 1, NULL, NULL);

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------
INSERT INTO `t_sys_notice` (`id`, `title`, `type`, `content`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', '世界', '10', '欢迎使用web-flash后台管理系统', '2017-01-11 08:53:20', '1', '2019-01-08 23:30:58', '1');

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
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (1, 42, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (2, 70, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (3, 46, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (4, 43, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (5, 67, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (6, 66, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (7, 33, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (8, 34, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (9, 36, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (10, 35, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (11, 41, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (12, 69, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (13, 68, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (14, 2, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (15, 44, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (16, 21, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (17, 32, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (18, 24, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (19, 29, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (20, 23, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (21, 28, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (22, 22, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (23, 25, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (24, 27, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (25, 31, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (26, 26, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (27, 30, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (28, 54, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (29, 45, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (30, 65, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (31, 48, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (32, 50, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (33, 51, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (34, 49, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (35, 52, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (36, 53, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (37, 17, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (38, 18, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (39, 20, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (40, 19, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (41, 56, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (42, 4, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (43, 5, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (44, 7, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (45, 6, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (46, 9, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (47, 8, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (48, 11, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (49, 10, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (50, 57, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (51, 60, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (52, 59, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (53, 64, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (54, 63, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (55, 58, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (56, 62, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (57, 61, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (58, 3, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (59, 12, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (60, 13, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (61, 15, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (62, 14, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (63, 16, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (64, 55, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (65, 1, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (66, 37, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (67, 38, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (68, 40, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (69, 39, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (70, 47, 1);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (128, 41, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (129, 42, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (130, 43, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (131, 44, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (132, 45, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (133, 46, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (134, 65, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (135, 66, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (136, 67, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (137, 68, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (138, 69, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (139, 70, 2);
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES (143, 2, 2);


-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', null, null, null, null, '24', '超级管理员', '1', '0', 'administrator', '1');
INSERT INTO `t_sys_role` VALUES ('2', null, null, null, null, '25', '网站管理员', '1', '1', 'developer', null);

-- ----------------------------
-- Records of t_sys_task
-- ----------------------------
INSERT INTO `t_sys_task` (`id`, `name`, `job_group`, `job_class`, `note`, `cron`, `data`, `exec_at`, `exec_result`, `disabled`, `create_time`, `create_by`, `concurrent`, `modify_time`, `modify_by`) VALUES ('1', '测试任务', 'default', 'cn.enilu.flash.service.task.job.HelloJob', '测试任务,每30分钟执行一次', '0 0/30 * * * ?', '{\n\"appname\": \"web-flash\",\n\"version\":1\n}\n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            ', '2019-03-27 11:47:00', '执行成功', '0', '2018-12-28 09:54:00', '1', '0', '2019-03-27 11:47:11', '-1');

-- ----------------------------
-- Records of t_sys_task_log
-- ----------------------------

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------

INSERT INTO `t_sys_user` VALUES ('-1', null, null, null, null, 'system', null, null, null, null, '应用系统', null, null, null, null, null, null, null);
INSERT INTO `t_sys_user` VALUES ('1', null, '2016-01-29 08:49:53', '1', '2019-03-20 23:45:24', 'admin', null, '2017-05-05 00:00:00', '27', 'eniluzt@qq.com', '管理员', 'b5a51391f271f062867e5984e2fcffee', '15021222222', '1', '8pgby', '2', '1', '25');
INSERT INTO `t_sys_user` VALUES ('2', null, '2018-09-13 17:21:02', '1', '2019-01-09 23:05:51', 'developer', null, '2017-12-31 00:00:00', '25', 'eniluzt@qq.com', '网站管理员', 'fac36d5616fe9ebd460691264b28ee27', '15022222222', '2,', 'vscp9', '1', '1', null);

-- ----------------------------
-- Records of t_test_boy
-- ----------------------------
INSERT INTO `t_test_boy` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `age`, `birthday`, `has_girl_friend`, `name`) VALUES ('1', null, null, null, null, '18', '2000-01-01', '1', '张三');


-- ----------------------------
-- Records of t_message_sender
-- ----------------------------
INSERT INTO `t_message_sender` VALUES ('1', null, null, null, null, 'tencentSmsSender', ' 腾讯短信服务', null);
INSERT INTO `t_message_sender` VALUES ('2', null, null, null, null, 'defaultEmailSender', '默认邮件发送器', null);

-- ----------------------------
-- Records of t_message_template
-- ----------------------------
INSERT INTO `t_message_template` VALUES ('1', null, null, null, null, 'REGISTER_CODE', '注册页面，点击获取验证码', '【腾讯云】校验码{1}，请于5分钟内完成验证，如非本人操作请忽略本短信。', '1', '注册验证码', 0);
INSERT INTO `t_message_template` VALUES ('2', null, null, null, null, 'EMAIL_TEST', '测试发送', '你好:{1},欢迎使用{2}', '2', '测试邮件', 1);
INSERT INTO `t_message_template` VALUES ('3', null, null, null, null, 'EMAIL_HTML_TEMPLATE_TEST', '测试发送模板邮件', '你好<strong>${userName}</strong>欢迎使用<font color=\"red\">${appName}</font>,这是html模板邮件', '2', '测试发送模板邮件', 1);

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('1', null, '2019-06-10 21:20:16', null, null, '【腾讯云】校验码1032，请于5分钟内完成验证，如非本人操作请忽略本短信。', '15021592814', '2', 'REGISTER_CODE', '0');
