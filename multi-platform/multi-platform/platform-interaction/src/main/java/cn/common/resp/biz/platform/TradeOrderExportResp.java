package cn.common.resp.biz.platform;

import pro.skywalking.validation.NotEmpty;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.*;

/**
 * 交易订单信息数据导出请求封装类
 * @title: TradeOrderExportResp.java
 * @author Singer
 * @date
*/
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class TradeOrderExportResp implements Serializable {

    private static final long serialVersionUID = 5004488152800472131L;

    /**
     * 关联的物品ID
     */
    @NotEmpty(message = "关联的物品ID->不可为空")
    @ExcelProperty(value = "关联的物品ID")
    private String itemId;

    /**
     * APP用户ID
     */
    @NotEmpty(message = "APP用户ID->不可为空")
    @ExcelProperty(value = "APP用户ID")
    private String authAppUserId;

    /**
     * 订单类型 系统自定义
     */
    @NotEmpty(message = "订单类型 系统自定义->不可为空")
    @ExcelProperty(value = "订单类型 系统自定义")
    private String orderType;

    /**
     * 订单备注
     */
    @NotEmpty(message = "订单备注->不可为空")
    @ExcelProperty(value = "订单备注")
    private String orderRemark;

    /**
     * 系统内部的支付订单号
     */
    @NotEmpty(message = "系统内部的支付订单号->不可为空")
    @ExcelProperty(value = "系统内部的支付订单号")
    private String outTradeNo;

    /**
     * 订单价格
     */
    @NotEmpty(message = "订单价格->不可为空")
    @ExcelProperty(value = "订单价格")
    private BigDecimal orderAmount;

    /**
     * 支付方式 aliPay/支付宝  weChatPay/微信 free/免费
     */
    @NotEmpty(message = "支付方式 aliPay/支付宝  weChatPay/微信 free/免费->不可为空")
    @ExcelProperty(value = "支付方式 aliPay/支付宝  weChatPay/微信 free/免费")
    private String payType;

    /**
     * 订单简略信息(通过JSON格式存储)
     */
    @NotEmpty(message = "订单简略信息(通过JSON格式存储)->不可为空")
    @ExcelProperty(value = "订单简略信息(通过JSON格式存储)")
    private String extraData;

    /**
     * 订单状态
     */
    @NotEmpty(message = "订单状态->不可为空")
    @ExcelProperty(value = "订单状态")
    private String orderStatus;

}
