module.exports = {
    title: 'web-flash',
    description: '使用web-flash快速构建web应用程序',
    base: '/web-flash/',
    head: [
        ['link', { rel: 'shortcut icon', type: "image/x-icon", href: './favicon.ico' }]
    ],
    evergreen: true,
    editLinkText:'在 GitHub 上编辑此页',
    port: 8081,
    ga: 'UA-71886989-13',
    themeConfig: {
        repo: 'enilu/web-flash',
        docsDir: 'docs',
        editLinks: true,
        editLinkText: '编辑此页面！',
        nav: [
            {text: '文档', link: '/'},
            {text: '功能',
                items:[
                    {text: '导出excel',link:'/feature/exportExcel'},
                    {text: '定时任务',link:'/action/task'},
                    {text: '缓存',link:'/action/cache'},
                    {text: '审计功能',link:'/action/jpaauditing'},
                ]
            },
            {text: '周边',
                items:[
                    {text: '代码生成',link:'/ecosystem/code-generator'},
                    {text: '数据库文档生成',link:'/ecosystem/database-doc-generator'},
                ]
            },
            {text: '捐赠',link:'/donate'},
            {text: '资源',link:'/resource'},

            {text: '项目主页', link: 'https://github.com/enilu/web-flash'},


        ],
        sidebar: [
            {
                title: '基本准备',
                collapsable: false,
                children: [
                    '/base/jdkAndMaven',
                    '/base/modules'
                ]
            },
            {
                title: '20分钟将本项目跑起来',
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
                title: '基本功能介绍',
                collapsable: false,
                children: [
                    '/feature/modules',
                    '/feature/menu',
                    '/feature/dict',
                    '/feature/log',
                    '/feature/permissionMgrVue',
                    '/feature/monitor',
                    '/feature/sysConfig',
                    '/feature/fileMgr',
                    '/feature/messageCenter'
                ]
            },{
                title: '进阶',
                collapsable: false,
                children: [
                    '/action/i18n',
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
