# 文件管理

本系统了设计了一个简单的文件管理功能，用来做文件生产上传下载

## 功能
- 文件上传
- 文件下载
- 图片浏览

## 设计
- t_sys_file_info 表存放文件元信息，包括文件名称，uuid，文件存储路径
- 在全局参数管理中配置文件的存储目录：system.file.upload.path

## 实现

- 文件上传参考内容管理(CMS)模块中的banner管理和文章管理
- 文件下载使用url：/file/download?idFile=${fileId}
- 在线显示图片使用url:/file/getImgStream?idFile=${fileId}
- 具体实现参见：cn.enilu.flash.api.controller.FileController
