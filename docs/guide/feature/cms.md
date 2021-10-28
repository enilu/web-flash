# CMS

## 简介
本系统集成了一个简单的CMS内容管理系统功能；主要包括两部分：
- 后台管理提供了栏目，文章，广告，邀约信息的维护，演示地址：[http://flashadmin.enilu.cn/banner](http://flashadmin.enilu.cn/banner)
- 前端提供了一个手机端的站点系统（flash-vue-h5),演示地址:[http://flash-mobile.enilu.cn/#/index](http://flash-mobile.enilu.cn/#/index)

<iframe 
    height=600 
    width=375
    src="http://flash-mobile.enilu.cn/#/index" 
    frameborder='allowfullscreen'>
</iframe>

- 另有uniapp版本，可以一次开发，打包多端app 项目地址[gitee](https://gitee.com/enilu/flash-uniapp),[github](https://github.com/enilu/flash-uniapp)
- app体验地址[http://fir.microapp.store/webflash](http://fir.microapp.store/webflash)
- H5演示：[http://flash-uniapp.enilu.cn](http://flash-uniapp.enilu.cn)：

<iframe 
    height=600 
    width=375
    src="http://flash-uniapp.enilu.cn" 
    frameborder='allowfullscreen'>
</iframe>

## 如何做二次开发
- 首先检查后台管理系统中的“系统管理”-“参数管理”中的参数：system.file.upload.path是否配置正确，系统初始化默认使用的是linux系统下的路径，如果用户用windows做开发，需要更改下该参数。该目录主要用于存储banner或者文章中的图片或者文件
- 然后根据自己需求进行定制化开发即可。
- CMS管理中有个文件管理的菜单，该页面的功能仅仅将通过栏目或者文章管理上传的图片，文件分页查询出来，并提供了下载功能，该页面并不提供上传功能。上传功能参见文章编辑或者banner编辑界面。
- 根据自己需要调整flash-vue-h5模块或者flash-uniapp项目
