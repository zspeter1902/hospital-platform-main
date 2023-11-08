const path = require('path');
const utils = require('./utils');
const config = require('../config');
const vueLoaderConfig = require('./vue-loader.conf');

resolve = (dir) => {
    return path.join(__dirname, '..', dir)
}
const src = path.resolve(__dirname, '../src');
module.exports = {
    entry: {
        app: './src/main.js'
    },
    output: {
        path: config.build.assetsRoot,
        filename: '[name].js',
        publicPath: process.env.NODE_ENV !== 'dev' ?  config.build.assetsPublicPath: config.dev.assetsPublicPath
    },
    resolve: {
        extensions: ['.js', '.vue', '.json','.less','.svg'],
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            '@': resolve('src'),
            '~': resolve('src/components'),
            'src': resolve( 'src'),
            'assets': resolve( 'src/assets'),
            'components': resolve( 'src/components'),
            'views': resolve( 'src/views'),
            'styles': resolve( 'src/styles'),
            'api': resolve( 'src/api'),
            'utils': resolve( 'src/utils'),
            'store': resolve( 'src/store'),
            'router': resolve( 'src/router'),
            'mock': resolve( 'src/mock'),
            'vendor': resolve( 'src/vendor'),
            'static': resolve( 'static'),
            '@static': resolve('static'),
        }
    },
    externals: {
        jquery: 'jQuery'
    },
    module: {
        rules: [
            { test: /\.vue$/,
                loader: 'vue-loader',
                options: vueLoaderConfig
            },
            {
                test: /\.js$/,
                loader: 'babel-loader?cacheDirectory',
                //include: [resolve('src'), resolve('test')]
                include: [
                    //此处需要加上解析ElementUI、D3等，否则打包会报错
                    resolve('src'),
                    resolve('test'),
                    resolve('/node_modules/element-ui/src'),
                    resolve('/node_modules/element-ui/packages'),
                    resolve('/node_modules/d3-color/src'),
                    resolve('/node_modules/d3-interpolate/src'),
                    resolve('/node_modules/@antv/d3-color/src'),
                    resolve('/node_modules/@antv/d3-interpolate/src'),
                ]
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
                loader: 'url-loader',
                query: {
                    limit: 10000,
                    name: utils.assetsPath('img/[name].[hash:7].[ext]')
                }
            },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                loader: 'url-loader',
                query: {
                    limit: 10000,
                    name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
                }
            }
        ]
    },
}
