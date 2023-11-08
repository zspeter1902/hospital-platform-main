//-----------------公用API start-------------------
//账户密码登录
export const AUTH_USER_LOGIN = {
	url: 'api/v1/authAppUser/userLogin',
	needToken: false
}
//绑定用户的微信openId
export const BIND_WX_OPEN_ID = {
	url: 'api/v1/authAppUser/bindWxOpenId',
	needToken: false
}
//绑定用户的微信手机号 此处因为先执行了BIND_WX_OPEN_ID 所以需要Token
export const BIND_WX_PHONE = {
	url: 'api/v1/authAppUser/bindWxPhone',
	needToken: true
}
//用户名密码注册
export const USER_RRG = {
	url: 'api/v1/authAppUser/userReg',
	needToken: false
}
//退出登录
export const AUTH_USER_LOGOUT = {
	url: 'api/v1/authAppUser/logOut',
	needToken: true
}
//查询当前用户的信息
export const CURRENT_USER_META = {
	url: 'api/v1/authAppUser/currentUserMeta',
	needToken: true
}
//查询当前用户的信息
export const UPDATE_AUTH_USER = {
	url: 'api/v1/authAppUser/updateAppAuthUser',
	needToken: true
}
//校验Token是否有效
export const VERIFY_TOKEN = {
	url: 'api/v1/authAppUser/verifyToken',
	needToken: true
}
//查询首页轮播图
export const QUERY_ALL_MAIN_SWIPER = {
	url: 'api/v1/mainSwiper/queryAllMainSwiper',
	needToken: false
}
//上传文件
export const UPLOAD_FILE = {
	url: 'api/platformFile/uploadFile',
	needToken: true
}
//删除文件
export const DELETE_FILE = {
	url: 'api/platformFile/deleteFileByFileName',
	needToken: true
}
//查询数据字典
export const QUERY_DICT_BY_TYPE = {
	url: 'api/v1/platform/queryDictByType',
	needToken: false
}
//下单
export const CREATE_ORDER = {
	url: 'api/v1/orderData/createOrder',
	needToken: true
}
//查询订单详情
export const QUERY_TRADE_ORDER_BY_NO = {
	url: 'api/v1/tradeOrder/queryByOrderNo',
	needToken: true
}
//查询订单列表
export const QUERY_ORDER_LIST = {
	url: 'api/v1/tradeOrder/queryOrderList',
	needToken: true
}
//查询订单信息
export const QUERY_ORDER_DATA = {
	url: 'api/v1/orderData/queryByTradeNo',
	needToken: true
}
//-----------------公用API end-------------------

//-----------------业务API start-------------------

//-----------------业务API end-------------------
export default {
	//公用API
	AUTH_USER_LOGIN:AUTH_USER_LOGIN,
	BIND_WX_OPEN_ID:BIND_WX_OPEN_ID,
	BIND_WX_PHONE:BIND_WX_PHONE,
	USER_RRG:USER_RRG,
	AUTH_USER_LOGOUT:AUTH_USER_LOGOUT,
	UPLOAD_FILE:UPLOAD_FILE,
	DELETE_FILE:DELETE_FILE,
	CURRENT_USER_META:CURRENT_USER_META,
	UPDATE_AUTH_USER:UPDATE_AUTH_USER,
	VERIFY_TOKEN:VERIFY_TOKEN,
	QUERY_ALL_MAIN_SWIPER:QUERY_ALL_MAIN_SWIPER,
	QUERY_TRADE_ORDER_BY_NO:QUERY_TRADE_ORDER_BY_NO,
	QUERY_ORDER_LIST:QUERY_ORDER_LIST,
	QUERY_ORDER_DATA:QUERY_ORDER_DATA,
	//公用API
	//-----------------业务API start-------------------
	
	//-----------------业务API end-------------------
};
