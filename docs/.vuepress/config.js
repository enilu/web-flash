module.exports = {
    title: 'web-flash',
    description: '使用web-flash快速构建web应用程序',
    base: '/web-flash/',

    evergreen: true,
    editLinkText:'在 GitHub 上编辑此页',
    port: 8081,
    ga: 'UA-71886989-13',
    themeConfig: {
        // 假定是 GitHub. 同时也可以是一个完整的 GitLab URL
        repo: 'enilu/web-flash',
        docsDir: 'docs',
        editLinks: true,
        editLinkText: '编辑此页面！',
        nav: [
            {text: '文档', link: '/'},
            {text: '公告', link: 'https://www.oschina.net/p/web-flash'},
            {text: '案例',link:'https://github.com/enilu/web-flash/issues/4'},
            {text: '捐赠',link:'/donate'},
            {text: '资源',link:'/resource'},
            {text: '周边',
                items:[
                    {text: '代码生成',link:'/ecosystem/code-generator'},
                    {text: '数据库文档生成',link:'/ecosystem/database-doc-generator'},
                ]
            },
            {text: '项目主页', link: 'https://github.com/enilu/web-flash'},


        ],
        sidebar: [
            {
                title: '基本准备',
                collapsable: false,
                children: [
                    '/base/jdkAndMaven',
                    '/base/modules',
                    '/base/guns-admin-vuejs.md'
                ]
            },

            {
                title: '30分钟将本项目跑起来',
                collapsable: false,
                children: [
                    '/quickstart/quickstart',
                    '/quickstart/clone',
                    '/quickstart/initDb',
                    '/quickstart/config',
                    '/quickstart/startup'
                ]
            },
            {
                title: '开发第一个功能',
                collapsable: false,
                children: [
                    '/hello_guns/hello_guns',
                    '/hello_guns/create_table',
                    '/hello_guns/base',
                    '/hello_guns/list',
                    '/hello_guns/add',
                    '/hello_guns/delete',
                    '/hello_guns/update',
                    '/hello_guns/menuAndPermission'
                ]
            },
            {
                title: '基本功能介绍',
                collapsable: false,
                children: [
                    '/feature/modules',
                    '/feature/menu',
                    '/feature/dict',
                    '/feature/log',
                    '/feature/permissionMgr',
                    '/feature/permissionMgrVue',
                    '/feature/monitor',
                    '/feature/sysConfig',
                    '/feature/fileMgr'
                ]
            },{
                title: '进阶',
                collapsable: false,
                children: [
                    '/action/i18n',
                    '/action/sqlite',
                    '/action/cache',
                    '/action/task',
                    '/action/jpaauditing.md'

                ]
            },{
                title: '其他',
                collapsable: false,
                children:[
                    '/other/faq'
                ]
            }
        ]

    }
}
