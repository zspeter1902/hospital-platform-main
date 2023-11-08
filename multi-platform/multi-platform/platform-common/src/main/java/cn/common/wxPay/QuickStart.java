package cn.common.wxPay;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description:
 * @date 2023-02-24
 */
public class QuickStart {

    /** 商户号 */
    public static String merchantId = "1637448431";
    /** 商户API私钥路径 */
    public static String privateKeyPath = "/Users/singer/GitCode/multi-platform/multi-platform/web-app-api/src/main/resources/wxV3PayCert/apiclient_key.pem";
    /** 商户证书序列号 */
    public static String merchantSerialNumber = "2FB70AD1779C94982A62589E843B17F7EF30FB0E";
    /** 商户APIV3密钥 */
    public static String apiV3key = "b70f18e886e6349dbecdd1c8c895e073";

    public static void main(String[] args) {
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(merchantId)
                        .privateKeyFromPath(privateKeyPath)
                        .merchantSerialNumber(merchantSerialNumber)
                        .apiV3Key(apiV3key)
                        .build();
        JsapiService service = new JsapiService.Builder().config(config).build();
        // request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(100);
        request.setAmount(amount);
        request.setAppid("wx11e6d2f15669e1d9");
        request.setMchid(merchantId);
        request.setDescription("测试商品标题");
        request.setNotifyUrl("https://www.skywalking.pro/zmi-wx-api/api/v1/wx-mini/wxV3PayCallback");
        request.setOutTradeNo("BPy5KyLB3f1tVLB");
        Payer payer = new Payer();
        payer.setOpenid("oflIO5WgcPBPy5KyLB3f1tVLBCN8");
        request.setPayer(payer);
        PrepayResponse response = service.prepay(request);
        System.out.println(response.getPrepayId());
    }
}
