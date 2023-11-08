package cn.common.resp.biz.app.wx;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer email:singer-coder@qq.com
 * @projectName wx-mini-api
 * @Description: 微信
 * @date 2020-07-21
 */
@Data
public class WxPhoneResp implements Serializable {

    private static final long serialVersionUID = 3744650710771465630L;

    /**
     * 错误码
     */
    @JSONField(name = "errcode")
    private int errCode;

    /**
     * 错误码
     */
    @JSONField(name = "errMsg")
    private String errmsg;


    /**
     * 手机数据
     */
    @JSONField(name = "phone_info")
    private WxPhoneData wxPhoneData;

}
