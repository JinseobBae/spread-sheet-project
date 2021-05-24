const path = require('path')

module.exports = {
    runtimeCompiler: true,
    outputDir : path.resolve(__dirname, "../" + "main/resources/static"),
    devServer : {
        proxy : {
            '/' : {
                target : 'http://localhost:9000',
                ws : true,
                changeOrigin : true
            },
        }
    }
}