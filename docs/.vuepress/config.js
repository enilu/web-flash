module.exports = {
    title: 'web-flash',
    description: '使用web-flash快速构建web应用程序',
    base: '/',
    head: [
        ['link', {rel: 'shortcut icon', type: "image/x-icon", href: './favicon.ico'}],
        ['script', {}, `
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          hm.src = "https://hm.baidu.com/hm.js?2e49fcf058af23545d0f4f943a872044";
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        `]
    ],
    evergreen: true,
    editLinkText: '在 GitHub 上编辑此页',
    port: 8081,
    ga: 'G-JW505HKR1M',
    themeConfig: {
        repo: 'enilu/web-flash',
        docsDir: 'docs',
        editLinks: true,
        editLinkText: '编辑此页面！',
        nav: [
            {text: '指南', link: '/guide/base/preface'},
            {text: '生态系统',
                items:[
                    {text:"项目",items:[
                            {text:'code-generator',link:'/ecosystem/code-generator.html'},
                            {text:'IDEA code plugin',link:'https://gitee.com/enilu/webflash-generator'},
                            {text:'database-doc-generator',link:'/ecosystem/database-doc-generator.html'},
                            {text:'material-admin(单体版本)',link:'https://enilu.gitee.io/material-admin'},
                            {text:'微服务版(spring cloud)',link:'/ecosystem/spring-cloud'}
                        ]
                    },
                    {text:'帮助',items:[
                            {text: '在线资源',link:'/guide/other/resource'},
                            {text:'QQ群(752844606)',link:'https://jq.qq.com/?_wv=1027&k=qWkkox36'},
                            {text:'作者blog',link:'http://blog.enilu.cn'},
                            {text:'FAQ',link:'/guide/other/faq'},
                            {text:'Change Log',link:'/guide/other/changeLog'}
                        ]},

                ]
            },
            {text: '博客', link: '/blog/dictSelect'},
            {text: '捐赠', link: '/donate'},
            {text: 'Gitee', link: 'https://gitee.com/enilu/web-flash'},


        ],
        sidebar: {
            '/guide': [
                {
                    title: '准备工作',
                    collapsable: false,
                    children: [
                        '/guide/base/preface',
                        '/guide/base/jdkAndMaven',
                        '/guide/base/modules'
                    ]
                },
                {
                    title: '快速开始',
                    collapsable: false,
                    children: [
                        '/guide/quickstart/quickstart',
                        '/guide/quickstart/clone',
                        '/guide/quickstart/initDb',
                        '/guide/quickstart/config',
                        '/guide/quickstart/startup'
                    ]
                },
                {
                    title: '基础',
                    collapsable: false,
                    children: [
                        '/guide/feature/modules',
                        '/guide/feature/menu',
                        '/guide/feature/dict',
                        '/guide/feature/log',
                        '/guide/feature/permissionMgrVue',
                        '/guide/feature/monitor',
                        '/guide/feature/sysConfig',
                        '/guide/feature/fileMgr',
                        '/guide/feature/exportExcel',
                        '/guide/feature/messageCenter',
                        '/guide/feature/cms.md'

                    ]
                },
                {
                    title: '进阶',
                    collapsable: false,
                    children: [
                        '/guide/action/i18n',
                        '/guide/action/cache',
                        '/guide/action/task',
                        '/guide/action/jpaauditing.md',
                        '/guide/action/validator',
                        '/guide/action/workflow',
                        '/guide/action/deploy'
                    ]
                },
                {
                    title: '其他',
                    collapsable: false,
                    children: [
                        '/guide/other/faq',
                        '/guide/other/changeLog',
                        '/guide/other/resource'
                    ]
                }
            ],
            '/blog': [
                {
                    title: '博客',
                    collapsable: false,
                    children: [
                        '/blog/dictSelect'
                    ]
                }
                ]
        }

    }
}
