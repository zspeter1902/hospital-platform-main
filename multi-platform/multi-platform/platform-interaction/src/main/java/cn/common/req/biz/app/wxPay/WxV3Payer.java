package cn.common.req.biz.app.wxPay;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common.resp.biz.app.wxpay
 * @Description: 微信V3支付->支付者信息
 * @date 2023-02-24
 */
@Data
public class WxV3Payer implements Serializable {


    private static final long serialVersionUID = -5474412935116675396L;


    /**
      * 支付者的openId
      */
    @JSONField(name = "openid")
    private String openId;

}
