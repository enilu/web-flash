# 文件管理

本系统了设计了一个文件管理功能，用来做文件的上传下载

## 功能
- 文件上传
- 文件下载
- 图片浏览

## 设计
- t_sys_file_info 表存放文件元信息，包括文件名称，uuid，文件存储路径
- 在"系统管理"->"参数管理"配置"系统默认上传文件路径"：system.file.upload.path

## 实现

- 文件上传参考内容管理(CMS)模块中的banner管理和文章管理
- 文件下载使用url：/file/download?idFile=${fileId}
- 在线显示图片使用url:/file/getImgStream?idFile=${fileId},如果需要获取图片的base64编码内容，可以通过url：/file/getImgBase64?idFile=${fileId}来获取
- 具体实现参见：cn.enilu.flash.api.controller.FileController
