
-- ----------------------------
-- Records of t_cms_article
-- ----------------------------
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('1', '1', '2019-03-09 16:24:58', '1', '2019-05-10 13:22:49', 'enilu', '<div id=\"articleContent\" class=\"content\">\n<div class=\"ad-wrap\">\n<p style=\"margin: 0 0 10px 0;\">一般我们都有这样的需求：我需要知道库中的数据是由谁创建，什么时候创建，最后一次修改时间是什么时候，最后一次修改人是谁。guns-lite最新代码已经实现该需求，具体实现方式网上有很多资料，这里做会搬运工，将guns-lite的实现步骤罗列如下：%%</p>\n</div>\n<p>在Spring jpa中可以通过在实体bean的属性或者方法上添加以下注解来实现上述需求@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy。</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>@CreatedDate 表示该字段为创建时间时间字段，在这个实体被insert的时候，会设置值</p>\n</li>\n<li>\n<p>@CreatedBy 表示该字段为创建人，在这个实体被insert的时候，会设置值</p>\n</li>\n<li>\n<p>@LastModifiedDate 最后修改时间 实体被update的时候会设置</p>\n</li>\n<li>\n<p>@LastModifiedBy 最后修改人 实体被update的时候会设置</p>\n</li>\n</ul>\n<h2>使用方式</h2>\n<h3>实体类添加注解</h3>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>首先在实体中对应的字段加上上述4个注解</p>\n</li>\n<li>\n<p>在guns-lite中我们提取了一个基础实体类BaseEntity，并将对应的字段添加上述4个注解,所有需要记录维护信息的表对应的实体都集成该类</p>\n</li>\n</ul>\n<pre>import&nbsp;org.springframework.data.annotation.CreatedBy;\nimport&nbsp;org.springframework.data.annotation.CreatedDate;\nimport&nbsp;org.springframework.data.annotation.LastModifiedBy;\nimport&nbsp;org.springframework.data.annotation.LastModifiedDate;\nimport&nbsp;javax.persistence.Column;\nimport&nbsp;javax.persistence.GeneratedValue;\nimport&nbsp;javax.persistence.Id;\nimport&nbsp;javax.persistence.MappedSuperclass;\nimport&nbsp;java.io.Serializable;\nimport&nbsp;java.util.Date;\n@MappedSuperclass\n@Data\npublic&nbsp;abstract&nbsp;class&nbsp;BaseEntity&nbsp;implements&nbsp;Serializable&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;@Id\n&nbsp;&nbsp;&nbsp;&nbsp;@GeneratedValue\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Long&nbsp;id;\n&nbsp;&nbsp;&nbsp;&nbsp;@CreatedDate\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"create_time\",columnDefinition=\"DATETIME&nbsp;COMMENT&nbsp;\'创建时间/注册时间\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Date&nbsp;createTime;\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"create_by\",columnDefinition=\"bigint&nbsp;COMMENT&nbsp;\'创建人\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;@CreatedBy\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Long&nbsp;createBy;\n&nbsp;&nbsp;&nbsp;&nbsp;@LastModifiedDate\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"modify_time\",columnDefinition=\"DATETIME&nbsp;COMMENT&nbsp;\'最后更新时间\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Date&nbsp;modifyTime;\n&nbsp;&nbsp;&nbsp;&nbsp;@LastModifiedBy\n&nbsp;&nbsp;&nbsp;&nbsp;@Column(name&nbsp;=&nbsp;\"modify_by\",columnDefinition=\"bigint&nbsp;COMMENT&nbsp;\'最后更新人\'\")\n&nbsp;&nbsp;&nbsp;&nbsp;private&nbsp;Long&nbsp;modifyBy;\n}</pre>\n<h3>实现AuditorAware接口返回操作人员的id</h3>\n<p>配置完上述4个注解后，在jpa.save方法被调用的时候，时间字段会自动设置并插入数据库，但是CreatedBy和LastModifiedBy并没有赋值 这两个信息需要实现AuditorAware接口来返回操作人员的id</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>首先需要在项目启动类添加@EnableJpaAuditing 注解来启用审计功能</p>\n</li>\n</ul>\n<pre>@SpringBootApplication\n@EnableJpaAuditing\npublic&nbsp;class&nbsp;AdminApplication&nbsp;extends&nbsp;WebMvcConfigurerAdapter&nbsp;{\n&nbsp;//省略\n}</pre>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>然后实现AuditorAware接口返回操作人员的id</p>\n</li>\n</ul>\n<pre>@Configuration\npublic&nbsp;class&nbsp;UserIDAuditorConfig&nbsp;implements&nbsp;AuditorAware&lt;Long&gt;&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;@Override\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;Long&nbsp;getCurrentAuditor()&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ShiroUser&nbsp;shiroUser&nbsp;=&nbsp;ShiroKit.getUser();\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(shiroUser!=null){\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;shiroUser.getId();\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;null;\n&nbsp;&nbsp;&nbsp;&nbsp;}\n}</pre>\n</div>', 'guns-lite 将所有表增加维护人员和维护时间信息', '1', '1');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('2', '1', '2019-03-09 16:24:58', '1', '2019-03-23 23:12:16', 'enilu.cn', '<div id=\"articleContent\" class=\"content\">\n<div class=\"ad-wrap\">\n<p style=\"margin: 0 0 10px 0;\"><a style=\"color: #a00; font-weight: bold;\" href=\"https://my.oschina.net/u/3985214/blog/3018099?tdsourcetag=s_pcqq_aiomsg\" target=\"_blank\" rel=\"noopener\" data-traceid=\"news_detail_above_text_link_1\" data-tracepid=\"news_detail_above_text_link\">开发十年，就只剩下这套架构体系了！ &gt;&gt;&gt;</a>&nbsp;&nbsp;<img style=\"max-height: 32px; max-width: 32px;\" src=\"https://www.oschina.net/img/hot3.png\" align=\"\" /></p>\n</div>\n<h3>国际化</h3>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>guns-admin-vuejs实现国际化了，不好意思guns-admin暂未实现国际化，后续也会考虑实现。</p>\n</li>\n<li>\n<p>不了解上面两个的区别的同学可以再回顾下这个<a href=\"http://www.enilu.cn/guns-lite/base/guns-admin-vuejs.html\">文档</a></p>\n</li>\n<li>\n<p>guns-admin-vuejs实现国际化的方式参考vue-element-admin的&nbsp;<a href=\"https://panjiachen.github.io/vue-element-admin-site/zh/guide/advanced/i18n.html\" target=\"_blank\" rel=\"noopener\">官方文档</a>,这里不再赘述,强烈建议你先把文档读了之后再看下面的内容。</p>\n</li>\n</ul>\n<h3>默认约定</h3>\n<p>针对网站资源进行国际园涉及到的国际化资源的管理维护，这里给出一些guns-admin-vuejs的资源分类建议，当然，你也可以根据你的实际情况进行调整。</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>src/lang/为国际化资源目录,目前提供了英文（en.js）和中文(zh.js)的两种语言实现。</p>\n</li>\n<li>\n<p>目前资源语言资源文件中是json配置主要有以下几个节点：</p>\n</li>\n<ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\">\n<li>\n<p>route 左侧菜单资源</p>\n</li>\n<li>\n<p>navbar 顶部导航栏资源</p>\n</li>\n<li>\n<p>button 公共的按钮资源，比如：添加、删除、修改、确定、取消之类等等</p>\n</li>\n<li>\n<p>common 其他公共的资源，比如一些弹出框标题、提示信息、label等等</p>\n</li>\n<li>\n<p>login 登录页面资源</p>\n</li>\n<li>\n<p>config 参数管理界面资源</p>\n</li>\n</ul>\n<li>\n<p>目前针对具体的页面资源只做了登录和参数管理两个页面，其他具体业务界面仅针对公共的按钮做了国际化，你可以参考config页面资源进行配置进行进一步配置：/src/views/cfg/</p>\n</li>\n<li>\n<p>如果你有其他资源在上面对应的节点添加即可，针对每个页面特有的资源以页面名称作为几点进行维护，这样方便记忆和维护，不容易出错。</p>\n</li>\n</ul>\n<h3>添加新的语言支持</h3>\n<p>如果英文和中文两种语言不够，那么你可以通过下面步骤添加语言支持</p>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>在src/lang/目录下新增对应的资源文件</p>\n</li>\n<li>\n<p>在src/lang/index.js中import对应的资源文件</p>\n</li>\n<li>\n<p>在src/lang/index.js中的messages变量中加入新的语言声明</p>\n</li>\n<li>\n<p>在src/components/LangSelect/index.vue的语言下拉框中增加新的语言选项</p>\n</li>\n</ul>\n<h3>演示地址</h3>\n<ul class=\" list-paddingleft-2\">\n<li>\n<p>vue版本后台管理&nbsp;<a href=\"http://106.75.35.53:8082/vue/#/login\" target=\"_blank\" rel=\"noopener\">http://106.75.35.53:8082/vue/#/login</a></p>\n</li>\n<li>CMS内容管理系统的h5前端demo:<a href=\"http://106.75.35.53:8082/mobile/#/index\" target=\"_blank\" rel=\"noopener\">http://106.75.35.53:8082/mobile/#/index</a></li>\n</ul>\n</div>', 'guns-lite1.0.1 发布，增加国际化和定时任务管理功能', '1', '2');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('3', '1', '2019-03-09 16:24:58', '1', '2019-04-28 17:39:52', 'enilu.cn', '<div class=\"content\" id=\"articleContent\">\r\n                        <div class=\"ad-wrap\">\r\n                                                    <p style=\"margin:0 0 10px 0;\"><a data-traceid=\"news_detail_above_text_link_1\" data-tracepid=\"news_detail_above_text_link\" style=\"color:#A00;font-weight:bold;\" href=\"https://my.oschina.net/u/3985214/blog/3018099?tdsourcetag=s_pcqq_aiomsg\" target=\"_blank\">开发十年，就只剩下这套架构体系了！ &gt;&gt;&gt;</a>&nbsp;&nbsp;<img src=\"https://www.oschina.net/img/hot3.png\" align=\"\" style=\"max-height: 32px; max-width: 32px;\"></p>\r\n                                    </div>\r\n                        <p>guns-lite使用的Spring Boot从1.5.1升级到2.1.1</p><p>下面为升级过程</p><ul class=\" list-paddingleft-2\"><li><p>版本升级</p><pre>&lt;spring.boot.version&gt;2.1.1.RELEASE&lt;/spring.boot.version&gt;\r\n&lt;springframework.version&gt;5.1.3.RELEASE&lt;springframework.version&gt;</pre></li><li><p>配置增加</p><pre>spring.main.allow-bean-definition-overriding=true\r\nspring.jpa.hibernate.use-new-id-generator-mappings=false</pre></li></ul><ul class=\" list-paddingleft-2\"><li><p>审计功能调整，调整后代码:</p><pre>@Configuration\r\npublic&nbsp;class&nbsp;UserIDAuditorConfig&nbsp;implements&nbsp;AuditorAware&lt;Long&gt;&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;@Override\r\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;Optional&lt;Long&gt;&nbsp;getCurrentAuditor()&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ShiroUser&nbsp;shiroUser&nbsp;=&nbsp;ShiroKit.getUser();\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(shiroUser!=null){\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;Optional.of(shiroUser.getId());\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;null;\r\n&nbsp;&nbsp;&nbsp;&nbsp;}\r\n}</pre></li><li><p>repository调整</p></li><ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\"><li><p>之前的 delete(Long id)方法没有了，替换为：deleteById(Long id)</p></li><li><p>之前的 T findOne(Long id)方法没有了，替换为：		</p><pre>Optional&lt;T&gt;&nbsp;optional&nbsp;=&nbsp;***Repository.findById(id);\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if&nbsp;(optional.isPresent())&nbsp;{\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;optional.get();\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;null;</pre></li></ul><li><p>随着这次Spring Boot的升级也顺便做了一些其他内容的调整和重构</p></li><ul class=\" list-paddingleft-2\" style=\"list-style-type: square;\"><li><p>springframework也从4.3.5.RELEASE升级到5.1.3.RELEASE</p></li><li><p>为减小复杂度service去掉接口和实现类的结构，基础功能的service直接使用实现类；当然具体业务中如果有需求你也可以这没用</p></li><li><p>去掉了一些暂时用不到的maven依赖</p></li><li><p>完善了基础功能的审计功能(之前有介绍审计功能的实现翻番，后续会专门发一篇文档来说明审计功能在系统总的具体用法，当然聪明的你看代码就知道了)</p></li></ul></ul>\r\n                    </div>', 'guns-lite 升级 Spring Boot 到 2.1.1 版本', '1', '1');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('4', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:34:21', 'enilu.cn', 'H5通用官网系统', 'H5通用官网系统', '2', '17');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('5', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:34:36', 'enilu.cn', 'H5通用论坛系统', 'H5通用论坛系统', '2', '18');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('6', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:38:33', 'enilu.cn', '官网建设方案', '官网建设方案', '3', '19');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('7', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:39:48', 'enilu.cn', '论坛建设方案', '论坛建设方案', '3', '22');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('8', '1', '2019-03-09 16:24:58', '1', '2019-04-28 17:39:52', 'enilu.cn', '案例1', '案例1', '4', '3');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('9', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:39:11', 'enilu.cn', '案例2', '案例2', '4', '20');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('14', '1', '2019-03-09 16:24:58', '1', '2019-04-28 00:39:25', 'test5', '<p>aaaaa<img class=\"wscnph\" src=\"http://127.0.0.1:8082/file/download?idFile=12\" /></p>', 'test5', '4', '21');
INSERT INTO `t_cms_article` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `author`, `content`, `title`, `id_channel`, `img`) VALUES ('15', '1', '2019-04-28 17:39:52', '1', '2019-05-05 15:36:57', 'enilu', '<p><img class=\"wscnph\" src=\"http://127.0.0.1:8082/file/download?idFile=24\" /></p>', 'test', '1', '25');

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
INSERT INTO `t_cms_contacts` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `email`, `mobile`, `remark`, `user_name`) VALUES ('1', null, null, null, null, 'test@qq.com', '15011111111', '测试联系，哈哈哈', '张三');

-- ----------------------------
-- Records of t_snow_product
-- ----------------------------

-- ----------------------------
-- Records of t_sys_cfg
-- ----------------------------
INSERT INTO `t_sys_cfg` (`id`, `cfg_name`, `cfg_value`, `cfg_desc`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', 'system.app.name', 'guns-lite', '应用名称update by 2019-03-27 11:47:04', null, null, '2019-04-15 21:36:07', '1');
INSERT INTO `t_sys_cfg` (`id`, `cfg_name`, `cfg_value`, `cfg_desc`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('2', 'system.file.upload.path', 'D:\\data\\guns-lite\\runtime\\upload', '系统默认上传文件路径', null, null, '2019-04-15 21:36:17', '1');

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
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('105', 'system', '0', '[0],', '系统管理', 'fa-cog', '/system', '4', '1', '1', null, '1', '1', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('106', 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', '1', '2', '1', null, '1', null, null, null, '2019-04-16 18:59:16', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('107', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', '', '/mgr/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('108', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', '', '/mgr/edit', '2', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('109', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', null, '/mgr/delete', '3', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('110', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', null, '/mgr/reset', '4', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('111', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', null, '/mgr/freeze', '5', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('112', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', null, '/mgr/unfreeze', '6', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('113', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', null, '/mgr/setRole', '7', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('114', 'role', 'system', '[0],[system],', '角色管理', null, '/role', '2', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('115', 'role_add', 'role', '[0],[system],[role],', '添加角色', null, '/role/add', '1', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('116', 'role_edit', 'role', '[0],[system],[role],', '修改角色', null, '/role/edit', '2', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('117', 'role_remove', 'role', '[0],[system],[role],', '删除角色', null, '/role/remove', '3', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('118', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', null, '/role/setAuthority', '4', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('119', 'menu', 'system', '[0],[system],', '菜单管理', null, '/menu', '4', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('120', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', null, '/menu/add', '1', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('121', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', null, '/menu/edit', '2', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('122', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', null, '/menu/remove', '3', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('128', 'log', 'system', '[0],[system],', '业务日志', null, '/log', '6', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('131', 'dept', 'system', '[0],[system],', '部门管理', null, '/dept', '3', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('132', 'dict', 'system', '[0],[system],', '字典管理', null, '/dict', '4', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('133', 'loginLog', 'system', '[0],[system],', '登录日志', null, '/loginLog', '6', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('134', 'log_clean', 'log', '[0],[system],[log],', '清空日志', null, '/log/delLog', '3', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('135', 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', null, '/dept/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('136', 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', null, '/dept/update', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('137', 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', null, '/dept/delete', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('138', 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', null, '/dict/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('139', 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', null, '/dict/update', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('140', 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', null, '/dict/delete', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('150', 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', '4', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('151', 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('152', 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', '4', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('153', 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('154', 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', '6', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('155', 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', '4', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('156', 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('157', 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', '6', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('159', 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', '3', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('160', 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('161', 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', '2', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('162', 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('163', 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', '6', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('164', 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', '7', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('165', 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', '8', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('166', 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', '9', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('167', 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', '10', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('198', 'cfg', 'system', '[0],[system],', '参数管理', '', '/cfg', '10', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('199', 'cfg_add', 'cfg', '[0],[system],[cfg],', '添加系统参数', '', '/cfg/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('200', 'cfg_update', 'cfg', '[0],[system],[cfg],', '修改系统参数', '', '/cfg/update', '2', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('201', 'cfg_delete', 'cfg', '[0],[system],[cfg],', '删除系统参数', '', '/cfg/delete', '3', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('202', 'task', 'system', '[0],[system],', '任务管理', '', '/task', '11', '2', '1', '', '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('203', 'task_add', 'task', '[0],[system],[task],', '添加任务', '', '/task/add', '1', '3', '0', '', '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('204', 'task_update', 'task', '[0],[system],[task],', '修改任务', '', '/task/update', '2', '3', '0', '', '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('205', 'task_delete', 'task', '[0],[system],[task],', '删除任务', '', '/task/delete', '3', '3', '0', '', '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('206', 'cms', '0', '[0],', 'CMS管理', '', '/cms', '5', '1', '1', null, '1', null, null, null, '2019-03-11 22:25:38', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('207', 'channel', 'cms', '[0],[cms],', '栏目管理', null, '/channel', '1', '2', '1', null, '1', null, '2019-03-11 22:29:55', '1', '2019-03-11 22:29:55', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('208', 'article', 'cms', '[0],[cms],', '文章管理', null, '/article', '2', '2', '1', null, '1', null, '2019-03-11 22:30:18', '1', '2019-03-11 22:30:18', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('209', 'banner', 'cms', '[0],[cms],', 'banner管理', null, '/banner', '3', '2', '1', null, '1', null, '2019-03-11 22:30:52', '1', '2019-03-11 22:30:52', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('210', 'contacts', 'cms', '[0],[cms],', '联系管理', null, '/contacts', '4', '2', '1', null, '1', null, '2019-03-18 19:45:38', '1', '2019-03-18 19:45:38', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('211', 'fileMgr', 'cms', '[0],[cms],', '文件管理', null, '/fileMgr', '5', '2', '1', null, '1', null, '2019-03-19 10:25:06', '1', '2019-03-19 10:25:06', '1');
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('214', 'task_log', 'task', '[0],[system],[task],', '任务日志', null, '/taskLog', '4', '3', '1', null, '1', null, null, null, null, null);
INSERT INTO `t_sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('215', 'editArticle', 'article', '[0],[cms],[article]', '编辑文章', null, '/article/edit', '1', '3', '1', null, '1', null, '2019-03-11 22:30:18', '1', '2019-03-11 22:30:18', '1');

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------
INSERT INTO `t_sys_notice` (`id`, `title`, `type`, `content`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', '世界', '10', '欢迎使用guns-lite后台管理系统', '2017-01-11 08:53:20', '1', '2019-01-08 23:30:58', '1');

-- ----------------------------
-- Records of t_sys_operation_log
-- ----------------------------
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('76', '业务日志', '编辑文章', '1', 'cn.enilu.guns.api.controller.cms.ArticleMgrController', 'save', '2019-05-10 13:22:49', '成功', '名称=null;;;');
INSERT INTO `t_sys_operation_log` (`id`, `logtype`, `logname`, `userid`, `classname`, `method`, `create_time`, `succeed`, `message`) VALUES ('77', '业务日志', '编辑文章', '1', 'cn.enilu.guns.api.controller.cms.ArticleMgrController', 'save', '2019-05-10 13:31:09', '成功', '名称=null;;;');

-- ----------------------------
-- Records of t_sys_relation
-- ----------------------------
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('281', '105', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('282', '106', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('283', '107', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('284', '108', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('285', '109', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('286', '110', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('287', '111', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('288', '112', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('289', '113', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('290', '165', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('291', '166', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('292', '167', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('293', '114', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('294', '115', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('295', '116', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('296', '117', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('297', '118', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('298', '162', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('299', '163', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('300', '164', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('301', '119', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('302', '120', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('303', '121', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('304', '122', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('305', '150', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('306', '151', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('307', '128', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('308', '134', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('309', '159', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('310', '131', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('311', '135', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('312', '136', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('313', '137', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('314', '152', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('315', '153', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('316', '154', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('317', '132', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('318', '138', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('319', '139', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('320', '140', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('321', '155', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('322', '156', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('323', '157', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('324', '133', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('325', '160', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('326', '161', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('327', '198', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('328', '199', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('329', '200', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('330', '201', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('331', '202', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('332', '203', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('333', '204', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('334', '205', '3');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('534', '105', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('535', '106', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('536', '107', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('537', '108', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('538', '109', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('539', '110', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('540', '111', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('541', '112', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('542', '113', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('543', '165', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('544', '166', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('545', '167', '2');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('547', '105', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('548', '106', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('549', '107', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('550', '108', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('551', '109', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('552', '110', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('553', '111', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('554', '112', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('555', '113', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('556', '165', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('557', '166', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('558', '167', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('559', '114', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('560', '115', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('561', '116', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('562', '117', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('563', '118', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('564', '162', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('565', '163', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('566', '164', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('567', '119', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('568', '120', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('569', '121', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('570', '122', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('571', '150', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('572', '151', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('573', '128', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('574', '134', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('575', '159', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('576', '131', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('577', '135', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('578', '136', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('579', '137', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('580', '152', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('581', '153', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('582', '154', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('583', '132', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('584', '138', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('585', '139', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('586', '140', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('587', '155', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('588', '156', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('589', '157', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('590', '133', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('591', '160', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('592', '161', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('593', '198', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('594', '199', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('595', '200', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('596', '201', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('597', '202', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('598', '203', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('599', '204', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('600', '205', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('605', '206', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('606', '207', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('607', '208', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('608', '209', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('609', '210', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('610', '211', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('611', '214', '1');
INSERT INTO `t_sys_relation` (`id`, `menuid`, `roleid`) VALUES ('612', '215', '1');

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` (`id`, `num`, `pid`, `name`, `deptid`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', '1', '0', '超级管理员', '24', 'administrator', '1', null, null, null, null);
INSERT INTO `t_sys_role` (`id`, `num`, `pid`, `name`, `deptid`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('2', '1', '1', '开发人员', '25', 'developer', null, null, null, null, null);
INSERT INTO `t_sys_role` (`id`, `num`, `pid`, `name`, `deptid`, `tips`, `version`, `create_time`, `create_by`, `modify_time`, `modify_by`) VALUES ('3', '3', '0', 'test', '24', '测试', null, null, null, null, null);

-- ----------------------------
-- Records of t_sys_task
-- ----------------------------
INSERT INTO `t_sys_task` (`id`, `name`, `job_group`, `job_class`, `note`, `cron`, `data`, `exec_at`, `exec_result`, `disabled`, `create_time`, `create_by`, `concurrent`, `modify_time`, `modify_by`) VALUES ('1', '测试任务', 'default', 'cn.enilu.guns.service.task.job.HelloJob', '测试任务\n            \n            \n            \n            ', '0 47 11 * * ?', '{\n\"appname\": \"guns-lite\",\n\"version\":1\n}\n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            ', '2019-03-27 11:47:00', '执行成功', '0', '2018-12-28 09:54:00', '1', '0', '2019-03-27 11:47:11', '-1');

-- ----------------------------
-- Records of t_sys_task_log
-- ----------------------------

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` (`id`, `avatar`, `account`, `password`, `salt`, `name`, `birthday`, `sex`, `email`, `phone`, `roleid`, `deptid`, `status`, `create_time`, `version`, `create_by`, `modify_time`, `modify_by`) VALUES ('-1', null, 'system', null, null, '应用系统', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sys_user` (`id`, `avatar`, `account`, `password`, `salt`, `name`, `birthday`, `sex`, `email`, `phone`, `roleid`, `deptid`, `status`, `create_time`, `version`, `create_by`, `modify_time`, `modify_by`) VALUES ('1', null, 'admin', '6ab1f386d715cfb6be85de941d438b02', '8pgby', '管理员', '2017-05-05 00:00:00', '2', 'eniluzt@qq.com', null, '1', '27', '1', '2016-01-29 08:49:53', '25', null, '2019-03-20 23:45:24', '1');
INSERT INTO `t_sys_user` (`id`, `avatar`, `account`, `password`, `salt`, `name`, `birthday`, `sex`, `email`, `phone`, `roleid`, `deptid`, `status`, `create_time`, `version`, `create_by`, `modify_time`, `modify_by`) VALUES ('45', null, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', '1', '', '', '1,2,', '24', '1', '2017-12-04 22:24:02', null, null, '2019-01-09 23:06:09', '1');
INSERT INTO `t_sys_user` (`id`, `avatar`, `account`, `password`, `salt`, `name`, `birthday`, `sex`, `email`, `phone`, `roleid`, `deptid`, `status`, `create_time`, `version`, `create_by`, `modify_time`, `modify_by`) VALUES ('46', null, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', '1', '', '', '1', '24', '1', '2017-12-04 22:24:24', null, null, null, null);
INSERT INTO `t_sys_user` (`id`, `avatar`, `account`, `password`, `salt`, `name`, `birthday`, `sex`, `email`, `phone`, `roleid`, `deptid`, `status`, `create_time`, `version`, `create_by`, `modify_time`, `modify_by`) VALUES ('47', null, 'developer', '4552805b07a4bf92ce1cea0373aab868', 'vscp9', '开发人员', '2017-12-31 00:00:00', '1', 'eniluzt@qq.com', '', '2,', '25', '1', '2018-09-13 17:21:02', null, null, '2019-01-09 23:05:51', '1');

-- ----------------------------
-- Records of t_test_boy
-- ----------------------------
INSERT INTO `t_test_boy` (`id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `age`, `birthday`, `has_girl_friend`, `name`) VALUES ('1', null, null, null, null, '18', '2000-01-01', '1', '张三');

-- ----------------------------
-- Records of t_test_girl
-- ----------------------------
