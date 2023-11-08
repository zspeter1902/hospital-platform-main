package cn.common.req.biz.app.wxPay;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common.req.biz.app
 * @Description: 微信支付下单请求对象
 * @date 2023-02-24
 */
@Data
public class WxOrderReq implements Serializable {

    private static final long serialVersionUID = 3433220696931879850L;

    /**
     * 订单物品的主键ID
     */
    @NotEmpty(message = "订单物品的ID->不可为空")
    private String itemId;

    /**
     * 订单总金额
     */
    @NotEmpty(message = "订单总金额->不可为空")
    private BigDecimal totalAmount;

    /**
     * 物品描述
     */
    @NotEmpty(message = "物品描述->不可为空")
    private String description;

    /**
     * 订单类型 系统自定义
     */
    private String orderType;

    /**
     * 订单备注
     */
    private String orderRemark;

}
