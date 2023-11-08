import Api from "@/services";
//通用审核状态 1:待审核 2:审核通过 3:审核拒绝
export const auditStatusOption = [
    {
        'text':'待审核',
        'value':1
    },
    {
        'text':'审核通过',
        'value':2
    },
    {
        'text':'审核拒绝',
        'value':3
    },
]
export const methodHandler = {
    //根据类型查询系统字典信息
    async dictOptions(dictType,handler){
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
        let resultArray = new Array();
        if(data && data.length>0){
            data.map(item => {
                /*let option = {
                    'text':item.dictKey,
                    'value':item.dictValue
                }*/
                let option = handler(item)
                resultArray.push(option);
            })
        }
        return resultArray;
    },
}

export default {
    auditStatusOption:auditStatusOption,
    dictOptions:methodHandler.dictOptions,
}
