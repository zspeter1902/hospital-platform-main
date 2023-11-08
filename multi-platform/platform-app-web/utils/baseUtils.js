import apiRequest from '@/utils/apiRequest.js'
import apiConstants from '@/utils/ApiConstants.js'
import authUtils from '@/utils/authUtils.js'
import bizConstants from '@/utils/bizConstants.js'
//正则
const numberPattern = /^(\-|\+)?\d+(\.\d+)?$/; //正数、负数、和小数
//正整数
const positiveNumberPattern = /(^[1-9]\d*$)/; 
const idCardNumberPattern = /^(\d{15}|\d{17}[\dxX])$/; //身份证号码正则
const cn = /^[\u4e00-\u9fa5]{0,}$/; //中文
const phoneNumberPattern = /^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\d{8}$/ //手机号正则
export const isNull = (object) => {
	if(object === '' || object === null || object === undefined){
		return true;
	}else if(typeof object === 'object'){
		let keys = Object.values(object)
		if(keys.length > 0){
		  return false;
		}
		return true
	}else{
		return false;
	}
}
export const baseUtil = {
	//产生随机数
	randomString(){
	   return Math.random().toString(36).slice(-8)
	 },
	//产生一个范围内的随机数
	async randomNum (max, min) {
	  const num = Math.floor(Math.random() * (max - min + 1)) + min
	  return num
	},
	async log(param){
		console.log(param)
	},
	//根据数据字典类型查询数据字典
	async queryDictByType(dictType){
		let data = {
			dictType:dictType
		}
		let params = {
			data:data,
			...apiConstants.QUERY_DICT_BY_TYPE
		}
		let headers = {}
		const result = await apiRequest.getRequest(
			params
		)
		if(!isNull(result)){
			let respList = new Array();
			result.map((item) => {
				let dictItem = {
					text:item.dictValue,
					value:item.platformDictId
				}
				respList.push(dictItem);
			})
			return respList;
		}else{
			return [];
		}
	},
	//查询单个字典信息
	async querySingleDictByType(dictType){
		let data = {
			dictType:dictType
		}
		let params = {
			data:data,
			...apiConstants.QUERY_SINGLE_DICT_BY_TYPE
		}
		let headers = {}
		const result = await apiRequest.getRequest(
			params
		)
		console.log('查询单个字典信息:'+JSON.stringify(result))
		if(!isNull(result)){
			return result.dictValue
		}else{
			return '';
		}
	},
	//是否为空
	isNull(object){
		return isNull(object);
	},
	//是否是正整数
	isPositiveNumber(value){
		if(isNull(value)){
		  return false
		}
		if(!positiveNumberPattern.test(value)){
		  return false;
		}else{
		  return true;
		}
		return false;
	},
	//是否是数字字符串
	isNumberString(value){
		if(isNull(value)){
		  return false
		}
		if(!numberPattern.test(value)){
		  return false;
		}else{
		  return true;
		}
		return false;
	},
	//从数组里面删除一个数据
	deleteItemFromArray(array,item){
		let index = array.indexOf(item);
		array.splice(index, 1)	
	},
	isIdCardNumber(value){
		return this.matchExp(idCardNumberPattern,value);
	},
	//校验正则
	matchExp(pattern,value){
		if(isNull(value) || isNull(pattern)){
		  return false
		}
		if(!pattern.test(value)){
		  return false;
		}else{
		  return true;
		}
		return false;
	},
	//简化uni.navigateTo,用JSON格式传递参数
	navigateByJSONObj(url,params){
		uni.navigateTo({
			url:url+'?item='+ encodeURIComponent(JSON.stringify(params)),
		})
	},
	//跳转
	navigateTo(url){
		uni.navigateTo({
			url:url
		})
	},
	logJSON(params){//按照JSON格式打印参数
		console.log(JSON.stringify(params))
	},
	log(params){//按照原来格式打印参数
		console.log(params)
	},
	dateFormat(fmt, date){//格式化时间
		let ret;
		const opt = {
		    "Y+": date.getFullYear().toString(),        // 年
		    "m+": (date.getMonth() + 1).toString(),     // 月
		    "d+": date.getDate().toString(),            // 日
		    "H+": date.getHours().toString(),           // 时
		    "M+": date.getMinutes().toString(),         // 分
		    "S+": date.getSeconds().toString()          // 秒
		    // 有其他格式化字符需求可以继续添加，必须转化成字符串
		};
		for (let k in opt) {
		    ret = new RegExp("(" + k + ")").exec(fmt);
		    if (ret) {
		        fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
		    };
		};
		return fmt;
	},
	setStorageSync(key,params){//同步存储某个数据
		let result = uni.setStorageSync(key,params)
		return result;
	},
	getStorageSync(key){//同步拿到某个缓存数据
		let result = uni.getStorageSync(key)
		return result;
	},
	removeStorageSync(key){//同步删除某个缓存数据
		uni.removeStorageSync(key)
	},
}
export default baseUtil;