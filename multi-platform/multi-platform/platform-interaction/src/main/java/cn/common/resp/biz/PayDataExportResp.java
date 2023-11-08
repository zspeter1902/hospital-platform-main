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
 * 支付数据信息数据导出请求封装类
 * @title: PayDataExportResp.java
 * @author Singer
 * @date
*/
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class PayDataExportResp implements Serializable {

    private static final long serialVersionUID = 2327612035432134598L;

    /**
     * 关联的支付订单号
     */
    @NotEmpty(message = "关联的支付订单号->不可为空")
    @ExcelProperty(value = "关联的支付订单号")
    private String outTradeNo;

    /**
     * 支付数据
     */
    @NotEmpty(message = "支付数据->不可为空")
    @ExcelProperty(value = "支付数据")
    private String payData;

}
