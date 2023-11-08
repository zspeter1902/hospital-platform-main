package cn.common.resp.biz.app.wxpay.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信支付回调解密数据
 * @date 2023-02-24
 */
@Data
public class WxCallBackPayer implements Serializable {

    private static final long serialVersionUID = 7414372218465142717L;

    /**
     * 支付者的openId
     */
    @JSONField(name = "openid")
    private String openId;

}
