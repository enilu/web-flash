# 开发资源
本节会整理提供一些助于开发的相关资源文档，在开发过程中学会恰当的里用这些资源将会使开发过程如虎添翼，事半功倍！。


## 前端

### 阿里巴巴矢量图库
- [https://iconfont.cn](https://iconfont.cn)
- 这个站点是阿里巴巴维护的一个图标站点，里面有极其丰富的图标，如果开发过程中需要一些图标而又不想麻烦设计师，那么可以来这里找找，应该不会让你失望。
- 具体用法为：在该网站搜索对应的图标后下载其svg文件放到本系统的flash-vue-admin/src/icons/svg/目录下即可。
- 本项目中用到这里的图标主要有两个地方：
    - 菜单配置中配置菜单的icon，名称和/src/icons/svg/中的图标名称一致即可，不要svg后缀名
    - 在vue界面使用图标可以通过如下形式,icon-class名称和svg文件名一致：
        ```html
        <svg-icon  icon-class="documentation" ></svg-icon>
        ```
    

### vue-element-admin
- [https://panjiachen.gitee.io/vue-element-admin-site/zh/](https://panjiachen.gitee.io/vue-element-admin-site/zh/)
- flash-vue-admin使用了vue-element-admin作为基础框架，基于flash-vue-admin开发之前强烈建议先看下vue-element-admin的官方文档

### vux.li
- [https://vux.li](https://vux.li)
- flash-vue-h5使用vux作为基础组件库，vux.li是一个用户很多的移动端vuejs的组件库，开发h5过程中请多参考该文档

## vuejs
- [https://vuejs.org](https://vuejs.org)
- vuejs官方文档，不说了，你懂得

## 后端

## Spring Boot
- [https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/content/](https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/content/) 文档基于Spring Boot1.4.1的英文文档翻译
- [https://www.breakyizhan.com/springboot/3028.html](https://www.breakyizhan.com/springboot/3028.html) 文档基于Spring Boot2.0.1的英文文档翻译
- 本系统基于Spring Boot2.1.1开发，所以建议开发过程中多翻翻第二个在线文档，第一个文档可以作为参考。
