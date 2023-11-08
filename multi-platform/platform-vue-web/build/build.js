require('./check-versions')();
const server = require('pushstate-server');
const opn = require('opn')
const ora = require('ora')
const rm = require('rimraf')
const path = require('path')
const chalk = require('chalk')
const webpack = require('webpack');
const config = require('../config');
const webpackConfig = require('./webpack.pro.conf');

console.log(process.env.NODE_ENV)

let spinner = ora('building for ' + process.env.NODE_ENV + '...')
spinner.start()


rm(path.join(config.build.assetsRoot, config.build.assetsSubDirectory), err => {
    if (err) throw err
    webpack(webpackConfig, function (err, stats) {
        spinner.stop()
        if (err) throw err
        process.stdout.write(stats.toString({
                colors: true,
                modules: false,
                children: false,
                chunks: false,
                chunkModules: false
            }) + '\n\n')

        console.log(chalk.cyan('  Build complete.\n'))
        if(process.env.npm_config_preview){
            server.start({
                port: 9528,
                directory: './dist',
                file: '/index.html'
            });
            console.log('> Listening at ' +  'http://localhost:9528' + '\n')
        }
    })
})
