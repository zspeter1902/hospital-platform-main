package cn.common.resp.biz;

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
 * 订单信息数据导出请求封装类
 * @title: OrderDataExportResp.java
 * @author Singer
 * @date
*/
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class OrderDataExportResp implements Serializable {

    private static final long serialVersionUID = -4617711302789130526L;

    /**
     * 订单交易订单ID
     */
    @NotEmpty(message = "订单交易订单ID->不可为空")
    @ExcelProperty(value = "订单交易订单ID")
    private String tradeOrderId;

    /**
     * 订单的支付订单号
     */
    @NotEmpty(message = "订单的支付订单号->不可为空")
    @ExcelProperty(value = "订单的支付订单号")
    private String outTradeNo;

    /**
     * APP用户ID
     */
    @NotEmpty(message = "APP用户ID->不可为空")
    @ExcelProperty(value = "APP用户ID")
    private String authAppUserId;

    /**
     * 商品ID
     */
    @NotEmpty(message = "商品ID->不可为空")
    @ExcelProperty(value = "商品ID")
    private String itemId;

    /**
     * 商品快照数据
     */
    @NotEmpty(message = "商品快照数据->不可为空")
    @ExcelProperty(value = "商品快照数据")
    private String itemData;

    /**
     * 购买数量
     */
    @NotEmpty(message = "购买数量->不可为空")
    @ExcelProperty(value = "购买数量")
    private Integer itemNum;

    /**
     * 订单备注
     */
    @NotEmpty(message = "订单备注->不可为空")
    @ExcelProperty(value = "订单备注")
    private String orderRemark;

}
