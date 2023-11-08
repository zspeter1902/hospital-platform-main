package cn.common.wxPay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
  * 微信V3支付配置参数类
 * 				此处如果是驼峰，需要把baseConstant变为base-constant
  * @author: create by singer - Singer email:singer-coder@qq.com
  * @date 2020/12/29
  */
@Data
@Component
@ConfigurationProperties(
		prefix = "base-constant.wechat-pay",
		ignoreUnknownFields = false)
public class WxPayV3PayConstants {

	/**
	 * 微信支付地域信息
	 */
    private String appId;

    /**
	 * 微信支付-商户号
	 */
    private String mchId;

	/**
	 * 微信支付api的V3的密钥
	 */
	private String apiV3Key;

	/**
	 * 微信支付V3的证书序列号
	 */
	private String mchSerialNo;

	/**
	 * 异步通知接口
	 */
	private String notifyUrl;

}
