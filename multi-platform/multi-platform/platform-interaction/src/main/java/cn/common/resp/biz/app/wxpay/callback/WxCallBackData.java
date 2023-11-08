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
public class WxCallBackData implements Serializable {

    private static final long serialVersionUID = -2524451748776925653L;

    /**
     * 商户号
     */
    @JSONField(name = "mchid")
    private String mchId;

    /**
     * 应用ID
     */
    @JSONField(name = "appid")
    private String appId;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 微信支付订单号
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /**
     * 交易类型
     */
    @JSONField(name = "trade_type")
    private String tradeType;

    /**
     * 交易状态
     */
    @JSONField(name = "trade_state")
    private String tradeState;

    /**
     * 交易状态描述
     */
    @JSONField(name = "trade_state_desc")
    private String tradeStateDesc;

    /**
     * 付款银行
     */
    @JSONField(name = "bank_type")
    private String bankType;

    /**
     * 附加数据
     */
    @JSONField(name = "attach")
    private String attach;

    /**
     * 支付完成时间
     */
    @JSONField(name = "success_time")
    private String successTime;

    /**
     * 支付者
     */
    private WxCallBackPayer payer;

    /**
     * 订单金额
     */
    private WxCallBackAmount amount;
}
