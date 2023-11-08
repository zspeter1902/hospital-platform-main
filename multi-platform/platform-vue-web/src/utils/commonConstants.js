import baseUtils from './baseUtils'
import Api from "@/services";
//当前长整时间
export const CURRENT_LONG_TIME = 'api/v1/platform/currenLongTime'
//顶级节点
export const TOP_MENU_NODE = '0'
//登录界面路由
export const LOGIN_PAGE = '/LoginPage'
//需要重新登陆的Code 由后端决定
export const NEED_LOGIN_CODE = [
  '501',
  '0000007',
  '0000012',
  '0000011',
  '0000013',
  '0000008',
]
//登录请求地址
export const LOGIN_REQUEST_URL = '/authUserLogin'
//权限类型
export const permissionTypeOptions = [
  {
    'text':'页面',
    'value': 1
  },
  {
    'text':'按钮',
    'value': 2
  },
  {
    'text':'文件夹',
    'value': 3
  },
  {
    'text':'其他资源',
    'value': 4
  },
]
//是和否选项
export const yesOrNoOptions = [
  {
    'text':'正常',
    'value': 1
  },
  {
    'text':'禁用',
    'value': 2
  },
]
//用户状态
export const authStatusOptions = [
  {
    'text':'正常',
    'value': 1
  },
  {
    'text':'禁用',
    'value': 2
  },
]
//用户性别类型
export const authUserGenderOptions = [
  {
    'text':'男性',
    'value':1
  },
  {
    'text':'女性',
    'value':2
  },
]
//是和否类型
export const booleanOptions = [
  {
    'text':'否',
    'value':false
  },
  {
    'text':'是',
    'value':true
  },
]
//是否查询所有
export const needAllStatusOptions = [
  {
    'text':'是',
    'value':'true'
  },
  {
    'text':'否',
    'value':'false'
  },
]
//物品是否热门
export const itemHotStatusOptions = [
  {
    'text':'热门',
    'value':'HOT'
  },
  {
    'text':'非热门',
    'value':'UN_HOT'
  },
]
//APP的板块名称
export const itemStatusOptions = [
  {
    'text':'上架',
    'value':'ITEM_STATUS_ON'
  },
  {
    'text':'下架',
    'value':'ITEM_STATUS_OFF'
  },
]
//APP的板块名称
export const panelOptions = [
  {
    'text':'主页',
    'value':'MAIN'
  },
  {
    'text':'页面内',
    'value':'PAGE'
  },
]
export const sketchColumns = [
  {
    title: '简略信息',
    dataIndex: 'sketchContent',
    align:'center',
  }
]
export const tagColumns = [
  {
    title: '简略信息',
    dataIndex: 'tagContent',
    align:'center',
  }
]
//当前地图中心点
export const platformMapCenter = [
  104.068823, 30.657167
]
//分页查询对比条件
export const contrastFactorOption = [
  {
    'text':'等于',
    'value':'EQUALS',
  },
  {
    'text':'大于',
    'value':'GREATER_THAN',
  },
  {
    'text':'大于等于',
    'value':'GREATER_THAN_OR_EQUAL_TO',
  },
  {
    'text':'小于等于',
    'value':'LESS_THAN_OR_EQUAL_TO',
  },
  {
    'text':'小于',
    'value':'LESS_THAN',
  }
]
export const dataBeginAndEndTimeFormat = 'YYYY-MM' //时间格式
export const methodHandler = {
  async yearOption(){
    let optionArray = new Array();
    for(let index = 1900;index<=2100;index++) {
      let option = {
        'text': index,
        'value': index
      }
      optionArray.push(option);
    }
    return optionArray
  },
  async currenLongTime(){
    let result = await getRequest(CURRENT_LONG_TIME,{});
    let data = result.data;
    if(data){
      return data
    }else{
      return ''
    }
  },
  //所有角色
  async allRoleOptions(){
    let result = await Api.queryAllAuthRole({

    }).then((res) => {
      if (res.success) {
        return res.data
      }else{
        this.$message.error(res.message || res.msg || "服务器异常");
      }
    })
    let data = result;
    //console.log('data:'+JSON.stringify(data))
    let resultArray = new Array();
    if(data && data.length>0){
      data.map(item => {
        let option = {
          'text':item.roleName,
          'value':item.authRoleId
        }
        resultArray.push(option);
      })
    }
    return resultArray;
  },
  //根据类型查询系统字典信息
  async queryPlatformDictByType(dictType){
    let result = await Api.queryDictByType({
      dictType:dictType
    }).then((res) => {
      if (res.success) {
        return res.data
      }else{
        this.$message.error(res.message || res.msg || "服务器异常");
      }
    })
    let data = result;
    //console.log('data:'+JSON.stringify(data))
    let resultArray = new Array();
    if(data && data.length>0){
      data.map(item => {
        let option = {
          'text':item.dictValue,
          'value':item.platformDictId
        }
        resultArray.push(option);
      })
    }
    return resultArray;
  },
  async authUserOptions(){
    let result = await Api.queryAllAuthUser({

    }).then((res) => {
      if (res.success) {
        return res.data
      }else{
        this.$message.error(res.message || res.msg || "服务器异常");
      }
    })
    let data = result;
    //console.log('data:'+JSON.stringify(data))
    let resultArray = new Array();
    if(data && data.length>0){
      data.map(item => {
        let option = {
          'text':item.userName,
          'value':item.authUserId
        }
        resultArray.push(option);
      })
    }
    return resultArray;
  },
}
export default {
  LOGIN_PAGE:LOGIN_PAGE,
  LOGIN_REQUEST_URL:LOGIN_REQUEST_URL,
  NEED_LOGIN_CODE:NEED_LOGIN_CODE,
  TOP_MENU_NODE:TOP_MENU_NODE,
  permissionTypeOptions:permissionTypeOptions,
  authStatusOptions:authStatusOptions,
  yesOrNoOptions:yesOrNoOptions,
  authUserGenderOptions:authUserGenderOptions,
  booleanOptions:booleanOptions,
  platformMapCenter:platformMapCenter,
  contrastFactorOption:contrastFactorOption,
  dataBeginAndEndTimeFormat:dataBeginAndEndTimeFormat,
  authUserOptions:methodHandler.authUserOptions,
  queryPlatformDictByType:methodHandler.queryPlatformDictByType,
  yearOption:methodHandler.yearOption,
  allRoleOptions:methodHandler.allRoleOptions,
  currenLongTime:methodHandler.currenLongTime,
  sketchColumns:sketchColumns,
  tagColumns:tagColumns,
  panelOptions:panelOptions,
  itemStatusOptions:itemStatusOptions,
  itemHotStatusOptions:itemHotStatusOptions,
  needAllStatusOptions:needAllStatusOptions,
  handleImageUrl:methodHandler.handleImageUrl,
};

