import apiRequest from "./apiRequest.js"
import baseUtils from "./baseUtils.js"
export const platformName = '微信小程序'
export const LOGIN_PAGE = '/pages-auth/loginPage'
export const MAIN_PAGE = '/pages/mainPage/mainPage'
export const OSS_URL = 'https://skywalking-web.oss-cn-chengdu.aliyuncs.com/'
//export const BASE_URL = 'https://www.skywalking.pro/multiport-app-api'
export const BASE_URL = 'http://localhost:9002'
//上传文件的地址
export const UPLOAD_FILE_URL = BASE_URL+'/api/platformFile/uploadFile'
//用户性别类型
export const genderOptions = [
  {
    text:'男性',
    value:1
  },
  {
    text:'女性',
    value:2
  },
]
//订单状态
export const orderStatusOptions = [
    {
		text:'未支付',
		value: 'NOT_PAY'
    },
	{
		text:'支付成功',
		value: 'PAY_SUCCESS'
	},
	{
		text:'订单关闭',
		value: 'CLOSED'
	},
]
export default {
	BASE_URL:BASE_URL,
	OSS_URL:OSS_URL,
	platformName:platformName,
	MAIN_PAGE:MAIN_PAGE,
	LOGIN_PAGE:LOGIN_PAGE,
	UPLOAD_FILE_URL:UPLOAD_FILE_URL,
	orderStatusOptions:orderStatusOptions,
	genderOptions:genderOptions,
}