package cn.common.service.biz;

import cn.common.req.biz.app.wxPay.WxOrderReq;
import cn.common.req.biz.app.callback.WxPayCallPack;
import cn.common.resp.biz.app.wxpay.WxMiniPayRespApp;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信支付相关服务方法
 * @date 2023-02-23
 */
public interface WxPayService {

    /**
     * 微信支付回调
     * @description:
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/24
     * @param req
     * @return
     */
    void wxV3PayCallBack(WxPayCallPack req);

    /**
     *
     * @description: 微信下单
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/23
     * @param req
     * @return cn.common.resp.biz.app.wxpay.WxMiniPayResp
     */
    WxMiniPayRespApp wxOrder(WxOrderReq req);

    /**
     * SHA256withRSA签名
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/4
     * @param content 内容
     * @param charset 编码
     * @return String
     */
    String signByPcks8Sha256WithRsa(String content, String charset);

    /**
     * 设置微信下单请求参数并且返回请求后的数据
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2022/1/29
     * @param wxOrderReq 微信下单请求参数
     * @param outTradeNo 外部订单号
     * @param openId 支付用户的OpenId
     * @return cn.common.resp.biz.app.wxpay.WxV3PayResult
     */
    PrepayResponse handleWxOrder(WxOrderReq wxOrderReq,
                                 String outTradeNo,
                                 String openId);




}
