# guns-admin-vuejs

这里专门介绍下guns-admin-vuejs模块,
该模块提供了一个基于vue的纯静态后台管理系统的界面方案，

guns-admin-vuejs（前端）和guns-api（后端）搭配使用提供了和guns-admin一样的功能。

该模块克隆自：[vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)，感谢PanJiaChen，欢迎大家给他点赞。

PanJiaChen同学提供了全面的文档，开发过程可以参考这里：[vue-elment-admin官方文档](https://panjiachen.github.io/vue-element-admin-site/zh/)

本文档不再赘述，本文档主要提供基于guns-admin开发的流程和技巧，当然后续基于guns-admin-vuejs开发过程中的经验和问题也会总结在本文当中。
 
## 启动步骤
- 启动guns-api:运行cn.enilu.guns.api.ApiApplication类
- 启动guns-admin-vuejs，具体步骤参考如下：
```bash 
# 控制台进入guns-admin-vuejs

# Install dependencies
npm install

# 建议不要用cnpm  安装有各种诡异的bug 可以通过如下操作解决npm速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# Serve with hot reload at localhost:9528
npm run dev

# Build for production with minification
npm run build

# Build for production and view the bundle analyzer report
npm run build --report
```
