package cn.common.resp.biz;

import lombok.Data;
import pro.skywalking.req.platform.base.BaseResp;

import java.io.Serializable;

/**
 * 订单信息返回数据封装类
 * @title: OrderDataResp.java
 * @author Singer
 * @date 2022/9/27 11:13
 */
@Data
public class OrderDataResp extends BaseResp implements Serializable {

    private static final long serialVersionUID = -8221899193921356984L;

    /**
     * 业务主键ID->"orderDataId"
     */
    private String orderDataId;

    /**
     * 订单交易订单ID
     */
    private String tradeOrderId;

    /**
     * 订单的支付订单号
     */
    private String outTradeNo;

    /**
     * APP用户ID
     */
    private String authAppUserId;

    /**
     * 商品ID
     */
    private String itemId;

    /**
     * 商品快照数据
     */
    private String itemData;

    /**
     * 购买数量
     */
    private Integer itemNum;

    /**
     * 订单备注
     */
    private String orderRemark;

}
