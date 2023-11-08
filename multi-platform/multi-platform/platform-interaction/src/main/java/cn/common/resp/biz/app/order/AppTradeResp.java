package cn.common.resp.biz.app.order;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 交易的订单Resp
 * @date 2023-02-26
 */
@Data
public class AppTradeResp implements Serializable {

    private static final long serialVersionUID = -6329426420118592594L;

    /**
     * 外部支付订单号
     */
    private String  outTradeNo;

    /**
     * 交易订单ID
     */
    private String tradeOrderId;

    /**
     * 交易物品ID
     */
    private String itemId;

}
