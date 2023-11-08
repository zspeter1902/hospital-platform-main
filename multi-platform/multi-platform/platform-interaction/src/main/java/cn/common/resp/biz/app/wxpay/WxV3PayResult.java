package cn.common.resp.biz.app.wxpay;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common.resp.biz.app.wxpay
 * @Description:  微信统一下单请求返回结果封装
 * @date 2023-02-24
 */
@Data
public class WxV3PayResult implements Serializable {

    private static final long serialVersionUID = -7816762279703795747L;

    /**
     * 微信预支付ID
     */
    @JSONField(name = "prepay_id")
    private String prePayId;
}
