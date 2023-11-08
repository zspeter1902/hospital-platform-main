import baseUtils from '@/utils/baseUtils.js';
import authUtils from '@/utils/authUtils.js';
import bizConstants from '@/utils/bizConstants.js'
import Request from '@/components/luch-request/index.js'
import Vue from '@/main'
import uView from "@/components/uview-ui";
const httpToolKit = new Request();

/**
* @description 修改全局默认配置
* @param {Function}   
*/
httpToolKit.setConfig((config) => { /* config 为默认全局配置*/
    config.baseURL = bizConstants.BASE_URL; /* 根域名 */
	let headers = {
		
	}
	let token = authUtils.getLoginToken();
	if(token){
		headers.token = token	
	}
    config.header = headers
    return config
})
//在请求之前拦截
httpToolKit.interceptors.request.use((config) => { // 可使用async await 做异步操作
	config.header = {
	...config.header,
	}
	// 演示custom用处
	// if (config.custom.auth) {
	//   config.header.token = 'token'
	// }
	// if (config.custom.loading) {
	//  uni.showLoading()
	// }
	/**
	/* 演示
	if (!token) { // 如果token不存在，return Promise.reject(config) 会取消本次请求
		return Promise.reject(config)
	}
	**/
	return config
}, config => { // 可使用async await 做异步操作
  return Promise.reject(config)
})

httpToolKit.interceptors.response.use((response) => { /* 对响应成功做点什么 可使用async await 做异步操作*/
	//console.log('返回数据response:'+JSON.stringify(response))
	let data = response.data
	if(baseUtils.isNull(data)){
		uni.$u.toast(data.message);
		return Promise.reject(data)
	}
	let code = data.code;
	//如果是登录失效的话则跳到首页
	if(code == '0000013'){
		console.log('token无效:'+data.message)
		uni.$u.toast('Token失效，需要重新登录');
		uni.$u.route({
			url: bizConstants.LOGIN_PAGE,
			params: {
				
			}
		})
		return data;
	}
	if(code != 200){
		console.log(`code != 200`+data.message)
		uni.$u.toast(data.message);
		return Promise.reject(data)
	}
	return data;
},(error) => {
	console.log('发生网络错误:'+JSON.stringify(error))
	uni.$u.toast('发生网络错误');
	return Promise.reject(error)
})

export default {
	httpToolKit:httpToolKit,
};