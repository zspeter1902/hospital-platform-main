package cn.common.resp.biz;

import pro.skywalking.req.platform.base.BaseResp;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.*;

/**
 * 支付数据信息返回数据封装类
 * @title: PayDataResp.java
 * @author Singer
 * @date 2022/9/27 11:13
 */
@Data
public class PayDataResp extends BaseResp implements Serializable {

    private static final long serialVersionUID = 4545106970553184529L;

    /**
     * 业务主键ID->"payDataId"
     */
    private String payDataId;

    /**
     * 关联的支付订单号
     */
    private String outTradeNo;

    /**
     * 支付数据
     */
    private String payData;

}
