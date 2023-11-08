require('./check-versions')(); // 检查 Node 和 npm 版本
const config = require('../config');
if (!process.env.NODE_ENV) {
    process.env.NODE_ENV = JSON.parse(config.dev.env.NODE_ENV)
}
const opn = require('opn')
const path = require('path');
const express = require('express');
const webpack = require('webpack');
const proxyMiddleware = require('http-proxy-middleware');
const webpackConfig = require('./webpack.dev.conf');

// default port where dev server listens for incoming traffic
const port = process.env.PORT || config.dev.port;
// automatically open browser, if not set will be false
const autoOpenBrowser = !!config.dev.autoOpenBrowser;
// Define HTTP proxies to your custom API backend
// https://github.com/chimurai/http-proxy-middleware
const proxyTable = config.dev.proxyTable;

const app = express();
const compiler = webpack(webpackConfig);

const devMiddleware = require('webpack-dev-middleware')(compiler, {
    publicPath: webpackConfig.output.publicPath,
    quiet: true
});

const hotMiddleware = require('webpack-hot-middleware')(compiler, {
    log: () => {
    }
});

// force page reload when html-webpack-plugin template changes
compiler.plugin('compilation', (compilation) => {
    compilation.plugin('html-webpack-plugin-after-emit', function (data, cb) {
        hotMiddleware.publish({action: 'reload'});
        cb()
    })
});

// compiler.apply(new DashboardPlugin());

// proxy api requests
Object.keys(proxyTable).forEach((context) => {
    let options = proxyTable[context]
    if (typeof options === 'string') {
        options = {target: options}
    }
    app.use(proxyMiddleware(options.filter || context, options))
});

// handle fallback for HTML5 history API
app.use(require('connect-history-api-fallback')());

// serve webpack bundle output
app.use(devMiddleware);

// enable hot-reload and state-preserving
// compilation error display
app.use(hotMiddleware);

// serve pure static assets
const staticPath = path.posix.join(config.dev.assetsPublicPath, config.dev.assetsSubDirectory);
app.use(staticPath, express.static('./static'));

const url = 'http://localhost:' + port
devMiddleware.waitUntilValid(() => {
    console.log('>>>>>>>>>>>>>>>>>>>>>>项目运行于:' + url + '<<<<<<<<<<<<<<<<<<')
    console.log('\n系统访问地址为:'+url)
    console.log('\n请打开浏览器查看')
    console.log('%c关闭时请不要直接关闭窗口，需要按下Ctrl+C关闭NodeJS。','color:#ff0033;')
    console.log('%c否则下次启动会出现端口占用而无法启动，请特别注意。','color:#ff0033;')
});

module.exports = app.listen(port, (err) => {
    if (err) {
        console.log(err);
        return
    }

    // when env is testing, don't need open it
    if (autoOpenBrowser && process.env.NODE_ENV !== 'testing') {
        opn(url)
    }
});
