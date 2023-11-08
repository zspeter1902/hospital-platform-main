import baseUtils from '@/utils/baseUtils.js';
import authUtils from '@/utils/authUtils';
import Request from '@/utils/Request'
import bizConstants from '@/utils/bizConstants.js'
import Vue from '@/main'
export const httpToolKit = Request.httpToolKit
import uView from "@/components/uview-ui";
//请求失败展示的字符
export const SHOW_MESSAGE = `网络异常、请稍后再试试`;

//处理异常请求
export const handleError = async (error) => {
	uni.hideLoading();
	console.log('error :' + JSON.stringify(error))
	let errorData = error.data;
	let message = error.message
	if(baseUtils.isNull(message) && baseUtils.isNull(errorData)){
		uni.$u.toast(SHOW_MESSAGE);
		return errorData;
	}else{
		uni.$u.toast(message);
		return errorData;
	}
	return errorData;
}

//GET请求
export const getRequest = async (options) => { //普通的请求接口方法
	//此处可以局部修改配置，局部配置优先级高于全局配置
	let url = options.url
	let headers = {}
	let needToken = options.needToken
	if (needToken) {
		let token = await authUtils.getLoginToken()
		console.log('token:'+token)
		if (!baseUtils.isNull(token)) {
			headers.token = token
		} else {
			uni.showToast({
				title: '请登录',
				duration: 2000
			});
			uni.$u.route({
				url: bizConstants.LOGIN_PAGE,
				params: {
					
				}
			})
			return {};
		}
	}
	//是否显示请求中loading
	let loading = options.loading
	if(loading === true){
		uni.showLoading({
		    title: '请求中',
			mask: true
		});
	}
	let params = options.data
	let result = await httpToolKit.get(url, {
		params: params,
		/* 会加在url上 */
		header: headers,
		/* 会与全局header合并，如有同名属性，局部覆盖全局 */
		dataType: 'json',
		// 注：如果局部custom与全局custom有同名属性，则后面的属性会覆盖前面的属性，相当于Object.assign(全局，局部)
		custom: {
			auth: true
		}, // 可以加一些自定义参数，在拦截器等地方使用。比如这里我加了一个auth，可在拦截器里拿到，如果true就传token
		// #ifndef MP-ALIPAY
		responseType: 'text',
		// #endif
		// #ifdef H5 || APP-PLUS || MP-ALIPAY || MP-WEIXIN
		timeout: 60000, // H5(HBuilderX 2.9.9+)、APP(HBuilderX 2.9.9+)、微信小程序（2.10.0）、支付宝小程序
		// #endif
		// #ifdef APP-PLUS
		sslVerify: true, // 验证 ssl 证书 仅5+App安卓端支持（HBuilderX 2.3.3+）
		// #endif
		// #ifdef APP-PLUS
		firstIpv4: false, // DNS解析时优先使用ipv4 仅 App-Android 支持 (HBuilderX 2.8.0+)
		// #endif
		// #ifdef H5
		withCredentials: false, // 跨域请求时是否携带凭证（cookies）仅H5支持（HBuilderX 2.6.15+）
		// #endif
		// 返回当前请求的task, options。请勿在此处修改options。非必填
		getTask: (task, options) => {
			// 相当于设置超时时间500ms
			// setTimeout(() => {
			//   task.abort()
			// }, 500)
		},
		validateStatus: (statusCode) => { // statusCode 必存在。此处示例为全局默认配置。演示，非必填选项
			console.log('validateStatus:' + statusCode)
			return statusCode >= 200 && statusCode < 300
		}
	}).then(res => {
		return res.data
	}).catch(async (error) => {
		return await handleError(error);
	})
	uni.hideLoading();
	return result;
}
//DELETE请求
export const deleteRequest = async (options) => { //普通的请求接口方法
	console.log('删除方法请求参数:'+JSON.stringify(options))
	//此处可以局部修改配置，局部配置优先级高于全局配置
	let url = options.url
	let headers = {}
	let needToken = options.needToken
	if (needToken) {
		let token = await authUtils.getLoginToken();
		if (!baseUtils.isNull(token)) {
			headers.token = token
		} else {
			uni.showToast({
				title: '请登录',
				duration: 2000
			});
			uni.$u.route({
				url: bizConstants.LOGIN_PAGE,
				params: {}
			})
			return {};
		}
	}
	//是否显示请求中loading
	let loading = options.loading
	if(loading === true){
		uni.showLoading({
		    title: '请求中',
			mask: true
		});
	}
	let params = options.data
	let urlParams = options.urlData
	let result = await httpToolKit.request({
		url: options.url,
		method: 'DELETE', // 请求方法必须大写 [GET|POST|PUT|DELETE|CONNECT|HEAD|OPTIONS|TRACE]
		params: urlParams,
		data:params,
		/* 会加在url上 */
		header: headers,
		/* 会与全局header合并，如有同名属性，局部覆盖全局 */
		dataType: 'json',
		// 注：如果局部custom与全局custom有同名属性，则后面的属性会覆盖前面的属性，相当于Object.assign(全局，局部)
		custom: {
			auth: true
		}, // 可以加一些自定义参数，在拦截器等地方使用。比如这里我加了一个auth，可在拦截器里拿到，如果true就传token
		// #ifndef MP-ALIPAY
		responseType: 'text',
		// #endif
		// #ifdef H5 || APP-PLUS || MP-ALIPAY || MP-WEIXIN
		timeout: 60000, // H5(HBuilderX 2.9.9+)、APP(HBuilderX 2.9.9+)、微信小程序（2.10.0）、支付宝小程序
		// #endif
		// #ifdef APP-PLUS
		sslVerify: true, // 验证 ssl 证书 仅5+App安卓端支持（HBuilderX 2.3.3+）
		// #endif
		// #ifdef APP-PLUS
		firstIpv4: false, // DNS解析时优先使用ipv4 仅 App-Android 支持 (HBuilderX 2.8.0+)
		// #endif
		// #ifdef H5
		withCredentials: false, // 跨域请求时是否携带凭证（cookies）仅H5支持（HBuilderX 2.6.15+）
		// #endif
		// 返回当前请求的task, options。请勿在此处修改options。非必填
		getTask: (task, options) => {
			// 相当于设置超时时间500ms
			// setTimeout(() => {
			//   task.abort()
			// }, 500)
		},
		validateStatus: (statusCode) => { // statusCode 必存在。此处示例为全局默认配置。演示，非必填选项
			console.log('validateStatus:' + statusCode)
			return statusCode >= 200 && statusCode < 300
		}
	}).then(res => {
		return res.data
	}).catch(async (error) => {
		return await handleError(error);
	})
	uni.hideLoading();
	return result;
}
//POST请求
export const postRequest = async (options) => { //普通的请求接口方法
	//此处可以局部修改配置，局部配置优先级高于全局配置
	let url = options.url
	let headers = {}
	let needToken = options.needToken
	if (needToken) {
		let token = await authUtils.getLoginToken();
		if (!baseUtils.isNull(token)) {
			headers.token = token
		} else {
			uni.showToast({
				title: '请登录',
				duration: 2000
			});
			uni.$u.route({
				url: bizConstants.LOGIN_PAGE,
				params: {
					
				}
			})
			return {};
		}
	}
	//是否显示请求中loading
	let loading = options.loading
	if(loading === true){
		uni.showLoading({
		    title: '请求中',
			mask: true
		});
	}
	let params = options.data
	let urlParams = options.urlData
	// 局部修改配置，局部配置优先级高于全局配置
	let result = await httpToolKit.post(url, params, {
		params: urlParams,
		/* 会加在url上 */
		header: headers,
		/* 会与全局header合并，如有同名属性，局部覆盖全局 */
		dataType: 'json',
		// 注：如果局部custom与全局custom有同名属性，则后面的属性会覆盖前面的属性，相当于Object.assign(全局，局部)
		custom: {
			auth: true
		}, // 可以加一些自定义参数，在拦截器等地方使用。比如这里我加了一个auth，可在拦截器里拿到，如果true就传token
		// #ifndef MP-ALIPAY
		responseType: 'text',
		// #endif
		// #ifdef H5 || APP-PLUS || MP-ALIPAY || MP-WEIXIN
		timeout: 60000, // H5(HBuilderX 2.9.9+)、APP(HBuilderX 2.9.9+)、微信小程序（2.10.0）、支付宝小程序
		// #endif
		// #ifdef APP-PLUS
		sslVerify: true, // 验证 ssl 证书 仅5+App安卓端支持（HBuilderX 2.3.3+）
		// #endif
		// #ifdef APP-PLUS
		firstIpv4: false, // DNS解析时优先使用ipv4 仅 App-Android 支持 (HBuilderX 2.8.0+)
		// #endif
		// #ifdef H5
		withCredentials: false, // 跨域请求时是否携带凭证（cookies）仅H5支持（HBuilderX 2.6.15+）
		// #endif
		// 返回当前请求的task, options。请勿在此处修改options。非必填
		getTask: (task, options) => {
			// 相当于设置超时时间500ms
			// setTimeout(() => {
			//   task.abort()
			// }, 500)
		},
		validateStatus: (statusCode) => { // statusCode 必存在。此处示例为全局默认配置。演示，非必填选项
			console.log('validateStatus:' + statusCode)
			return statusCode >= 200 && statusCode < 300
		}
	}).then(res => {
		return res.data
	}).catch(async (error) => {
		return await handleError(error);
	})
	uni.hideLoading();
	return result;
}

//PUT请求
export const putRequest = async (options) => { //普通的请求接口方法
	//此处可以局部修改配置，局部配置优先级高于全局配置
	let url = options.url
	let headers = {}
	let needToken = options.needToken
	if (needToken) {
		let token = await authUtils.getLoginToken();
		if (!baseUtils.isNull(token)) {
			headers.token = token
		} else {
			uni.showToast({
				title: '请登录',
				duration: 2000
			});
			uni.$u.route({
				url: bizConstants.LOGIN_PAGE,
				params: {
					
				}
			})
			return {};
		}
	}
	//是否显示请求中loading
	let loading = options.loading
	if(loading === true){
		uni.showLoading({
		    title: '请求中',
			mask: true
		});
	}
	let params = options.data
	let urlParams = options.urlData
	// 局部修改配置，局部配置优先级高于全局配置
	let result = await httpToolKit.put(url, params, {
		params: urlParams,
		/* 会加在url上 */
		header: headers,
		/* 会与全局header合并，如有同名属性，局部覆盖全局 */
		dataType: 'json',
		// 注：如果局部custom与全局custom有同名属性，则后面的属性会覆盖前面的属性，相当于Object.assign(全局，局部)
		custom: {
			auth: true
		}, // 可以加一些自定义参数，在拦截器等地方使用。比如这里我加了一个auth，可在拦截器里拿到，如果true就传token
		// #ifndef MP-ALIPAY
		responseType: 'text',
		// #endif
		// #ifdef H5 || APP-PLUS || MP-ALIPAY || MP-WEIXIN
		timeout: 60000, // H5(HBuilderX 2.9.9+)、APP(HBuilderX 2.9.9+)、微信小程序（2.10.0）、支付宝小程序
		// #endif
		// #ifdef APP-PLUS
		sslVerify: true, // 验证 ssl 证书 仅5+App安卓端支持（HBuilderX 2.3.3+）
		// #endif
		// #ifdef APP-PLUS
		firstIpv4: false, // DNS解析时优先使用ipv4 仅 App-Android 支持 (HBuilderX 2.8.0+)
		// #endif
		// #ifdef H5
		withCredentials: false, // 跨域请求时是否携带凭证（cookies）仅H5支持（HBuilderX 2.6.15+）
		// #endif
		// 返回当前请求的task, options。请勿在此处修改options。非必填
		getTask: (task, options) => {
			// 相当于设置超时时间500ms
			// setTimeout(() => {
			//   task.abort()
			// }, 500)
		},
		validateStatus: (statusCode) => { // statusCode 必存在。此处示例为全局默认配置。演示，非必填选项
			console.log('validateStatus:' + statusCode)
			return statusCode >= 200 && statusCode < 300
		}
	}).then(res => {
		return res.data
	}).catch(async (error) => {
		return await handleError(error);
	})
	uni.hideLoading();
	return result;
}
//上传请求
export const uploadRequest = async (options) => {
	//此处可以局部修改配置，局部配置优先级高于全局配置
	let url = options.url
	let headers = {}
	let needToken = options.needToken
	if (needToken) {
		let token = await authUtils.getLoginToken();
		if (!baseUtils.isNull(token)) {
			headers.token = token
		} else {
			uni.showToast({
				title: '请登录',
				duration: 2000
			});
			uni.$u.route({
				url: bizConstants.LOGIN_PAGE,
				params: {
					
				}
			})
			return {};
		}
	}
	//是否显示请求中loading
	let loading = options.loading
	if(loading === true){
		uni.showLoading({
		    title: '请求中',
			mask: true
		});
	}
	let params = options.data
	let fileType = options.fileType
	let result = await httpToolKit.upload(url, {
		params: params,
		/* 会加在url上 */
		// #ifdef APP-PLUS || H5
		files: [], // 需要上传的文件列表。使用 files 时，filePath 和 name 不生效。App、H5（ 2.6.15+）
		// #endif
		// #ifdef MP-ALIPAY
		fileType: fileType, // 仅支付宝小程序，且必填。
		// #endif
		filePath: '', // 要上传文件资源的路径。
		// 注：如果局部custom与全局custom有同名属性，则后面的属性会覆盖前面的属性，相当于Object.assign(全局，局部)
		custom: { // 可以加一些自定义参数，在拦截器等地方使用。比如这里我加了一个auth，可在拦截器里拿到，如果true就传token
			auth: true,
		},
		name: 'file', // 文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
		// #ifdef H5 || APP-PLUS
		timeout: 60000, // H5(HBuilderX 2.9.9+)、APP(HBuilderX 2.9.9+)
		// #endif
		header: headers,
		/* 会与全局header合并，如有同名属性，局部覆盖全局 */
		formData: {}, // HTTP 请求中其他额外的 form data
		// 返回当前请求的task, options。请勿在此处修改options。非必填
		getTask: (task, options) => {
			// task.onProgressUpdate((res) => {
			//   console.log('上传进度' + res.progress);
			//   console.log('已经上传的数据长度' + res.totalBytesSent);
			//   console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
			//
			//   // 测试条件，取消上传任务。
			//   if (res.progress > 50) {
			//     uploadTask.abort();
			//   }
			// });
		},
		validateStatus: (statusCode) => { // statusCode 必存在。此处示例为全局默认配置。演示，非必填选项
			console.log('validateStatus:' + statusCode)
			return statusCode >= 200 && statusCode < 300
		}
	}).then(res => {
		return res.data
	}).catch(async (error) => {
		return await handleError(error);
	})
	uni.hideLoading();
	return result;
}
export default {
	getRequest: getRequest,
	postRequest: postRequest,
	uploadRequest: uploadRequest,
	deleteRequest: deleteRequest,
	putRequest: putRequest
};
