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
public class WxPhoneData implements Serializable {

    private static final long serialVersionUID = -6231289158258925392L;

    /**
      * 手机号
      */
    @JSONField(name = "phoneNumber")
    private String phoneNumber;

    /**
     * 没有区号的手机号
     */
    @JSONField(name = "purePhoneNumber")
    private String purePhoneNumber;


    /**
     * 国家代码
     */
    @JSONField(name = "countryCode")
    private String countryCode;

}
