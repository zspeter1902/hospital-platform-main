package cn.common.wxPay;

import cn.common.constants.BizConstants;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import pro.skywalking.configuration.redis.RedisRepository;
import pro.skywalking.utils.BaseUtil;

import javax.annotation.Resource;
import java.io.IOException;


/**
  * 微信V3支付配置类
  * @author: create by singer - Singer email:singer-coder@qq.com
  * @date 2020/12/29
  */
@Configuration
//存在appId配置才进行注入
@ConditionalOnProperty(
        prefix = "baseConstant.wechatPay",
        name = "appId"
)
@Slf4j
public class WxV3PayConfig {

    @Resource
    private WxPayV3PayConstants wxPayV3PayConstants;

    @Resource
    private RedisRepository redisRepository;

     /**
       *
       * @description: JS的支付APISerivce
       * @author: create by singer - Singer email:singer-coder@qq.com
       * @date 2023/2/24
       * @param
       * @return com.wechat.pay.java.service.payments.jsapi.JsapiService
       */
    @Bean("jsapiService")
    public JsapiService initWxJsPayService(){

        ClassPathResource apiClientPrivateKeyResource = new ClassPathResource("wxV3PayCert/apiclient_key.pem");
        JsapiService service = null;
        try {
            //私钥缓存Key
            String privateKeyReidisKey = BizConstants.WX_PAY_SIGN_PRIVATE_KEY_CACHE_PREFIX +
                    wxPayV3PayConstants.getAppId();
            //此处需要缓存私钥
            String privateKey = BaseUtil.readFileContent(apiClientPrivateKeyResource.getFile());
            privateKey = privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            redisRepository.set(privateKeyReidisKey,privateKey);
            String apiV3key = wxPayV3PayConstants.getApiV3Key();
            String merchantSerialNumber = wxPayV3PayConstants.getMchSerialNo();
            String mchId = wxPayV3PayConstants.getMchId();
            String privateKeyPath = apiClientPrivateKeyResource.getFile().getAbsolutePath();

            String path = apiClientPrivateKeyResource.getPath();
            Config config =
                    new RSAAutoCertificateConfig.Builder()
                            .merchantId(mchId)
                            .privateKeyFromPath(privateKeyPath)
                            .merchantSerialNumber(merchantSerialNumber)
                            .apiV3Key(apiV3key)
                            .build();
            service = new JsapiService.Builder().config(config).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return service;
    }

}
