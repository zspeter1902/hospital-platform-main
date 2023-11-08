import baseUtils from '@/utils/baseUtils';
import apiConstants from '@/utils/ApiConstants.js'
import apiRequest from '@/utils/apiRequest.js';
import bizConstants from '@/utils/bizConstants.js';
import Vue from '@/main'
import uView from "@/components/uview-ui";
const TOKEN = "token"
const AUTH_USER_DATA = "AUTH_USER_DATA"
export const authUtils = {
	//设置登陆信息(token和登录后的信息)
	async setLoginToken(token,params){
		uni.setStorageSync(TOKEN,token)
		uni.setStorageSync(token,JSON.stringify(params))
	},
	//拿到登陆的Token
	async getLoginToken(){
		return uni.getStorageSync(TOKEN)
	},
	//拿到登陆后缓存的数据
	async getLoginData(){
		let token = uni.getStorageSync(TOKEN)
		if(!token){
			return null;
		}
		let data = uni.getStorageSync(token)
		if(!data){
			return null;
		}
		return JSON.parse(data)
	},
	//设置登陆后缓存的用户信息
	async setAuthUserData(params){
		uni.setStorageSync(AUTH_USER_DATA,JSON.stringify(params))
	},
	//拿到登陆后返回的用户信息
	async getAuthUserData(){
		let data = uni.getStorageSync(AUTH_USER_DATA)
		console.log('data:'+JSON.stringify(data))
		if(data){
			return JSON.parse(data)
		}else{
			return null;
		}
	},
	//同步存储某个数据
	setStorageSync(key,params){
		let result = uni.setStorageSync(key,params)
		return result;
	},
	//同步拿到某个缓存数据
	getStorageSync(key){
		let result = uni.getStorageSync(key)
		return result;
	},
	//同步删除某个缓存数据
	removeStorageSync(key){
		uni.removeStorageSync(key)
	},
	//跳转到首页
	toMainPage(time) {
		uni.showLoading({
			title: '正在进入主页'
		})
		setTimeout(() => {
			uni.hideLoading()
			uni.navigateTo({
				url: bizConstants.MAIN_PAGE
			})
		}, time)
	},
	//跳转到登录界面
	async toLoginPage(time) {
		uni.showLoading({
			title: '用户未登录-请登录'
		})
		setTimeout(() => {
			uni.hideLoading()
			uni.navigateTo({
				url: bizConstants.LOGIN_PAGE
			})
		}, time)
	},
	//判断是否登陆 如果没有登录，需要跳转到登录界面
	async verifyLogin(){
		let token = await this.getLoginToken();
		console.log('verifyLogin-token:'+token)
		let loginStatus = false;
		console.log('token:'+token)
		if(baseUtils.isNull(token)){
			uni.navigateTo({
				url: bizConstants.LOGIN_PAGE
			})
		}
		let params = {
			data: {
				
			},
			...apiConstants.VERIFY_TOKEN
		}
		console.log('开始校验Token:'+JSON.stringify(params))
		let verifyResult = await apiRequest.getRequest(params)
		console.log('校验Token结果:'+JSON.stringify(verifyResult))
		if(baseUtils.isNull(verifyResult)){
			uni.$u.toast('请登陆');
			uni.navigateTo({
				url: bizConstants.LOGIN_PAGE
			})
			return;
		}
		let verifyStatus = verifyResult.verifyStatus
		console.log('Token是否有效:'+verifyStatus)
		if(verifyStatus === false){
			uni.$u.toast('请登陆');
			console.log('token无效')
			uni.navigateTo({
				url: bizConstants.LOGIN_PAGE
			})
			return;
		}
	},
	//绑定微信的手机号
	async bindWxPhone(bindData){
		let params = {
			loading:true,
			data: {
				...bindData
			},
			...apiConstants.BIND_WX_PHONE
		}
		let loginData = await apiRequest.postRequest(params)
		console.log('绑定微信的手机号结果:'+JSON.stringify(loginData));
		if(baseUtils.isNull(loginData)){
			uni.$u.toast('绑定微信的手机号失败');
			return;
		}
		uni.$u.toast('绑定成功');
		let token = loginData.token;
		await this.setAuthUserData(loginData)
		await this.setLoginToken(token,loginData)
		uni.$u.route({
			type:'switchTab',
			url: bizConstants.MAIN_PAGE,
			params: {
				
			}
		})
	},
	//微信静默登陆
	async bindWxOpenId(){
	 	uni.login({
	 	  provider: 'weixin', //使用微信登录
	 	  success: async (data) => {
	 	    console.log('微信登陆票据:'+JSON.stringify(data));
			let code = data.code;
			if(!code){
				uni.$u.toast('发生网络错误');
				return;
			}
			let params = {
				loading:true,
				data: {
					code:code
				},
				...apiConstants.BIND_WX_OPEN_ID
			}
			let loginData = await apiRequest.postRequest(params)
			console.log('微信登陆注册结果:'+JSON.stringify(loginData));
			if(baseUtils.isNull(loginData)){
				uni.$u.toast('微信静默注册失败');
				return;
			}
			//uni.$u.toast('静默注册成功');
			let token = loginData.token;
			await this.setAuthUserData(loginData)
			await this.setLoginToken(token,loginData)
			uni.$u.route({
				type:'switchTab',
				url: bizConstants.MAIN_PAGE,
				params: {}
			})
	 	  }
	 	});
	},
	//判断是否登陆
	async isLogin(){
		let token = uni.getStorageSync(TOKEN)
		let loginStatus = false;
		if(token){
			let params = {
				data: {
					
				},
				...apiConstants.VERIFY_TOKEN
			}
			let verifyData = await apiRequest.getRequest(params)
			console.log('校验Token结果:'+JSON.stringify(verifyData))
			if(verifyData){
				let verifyStatus = verifyData.verifyStatus
				console.log('Token是否有效:'+verifyStatus)
				if(verifyStatus === true){
					loginStatus = true	
				}
			}
		}
		return loginStatus;
	},
}
export default authUtils;