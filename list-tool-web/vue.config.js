module.exports = {
    pages: {
        listTool: {
            entry: "./src/views/listTool/listTool.js",
            template: "./src/views/listTool/listTool.html",
            filename: "listTool.html",
            title: "列表"
        },
        listConfig: {
            entry: "./src/views/config/listConfig.js",
            template: "./src/views/config/listConfig.html",
            filename: "listConfig.html",
            title: "列表配置"
        }
    },
    devServer: {
        proxy: {
            '/csb': {
                target: 'http://127.0.0.1:9004/',  // target host
                ws: true,  // proxy websockets
                changeOrigin: true,  // needed for virtual hosted sites
                pathRewrite: {
                    '^/csb': '/csb'  // rewrite path
                }
            },
        }
    }
}
