package cn.common.wx;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 业务常量
 * @date 2023-02-03
 */
@Data
@Component
@ConfigurationProperties(
        prefix = "base-constant.wx-mini",
        ignoreUnknownFields = false
)
public class WxBizConstants {

    /**
     * 微信小程序的appId
     */
    private String appId;

    /**
     * 微信小程序的appSecret
     */
    private String appSecret;

}
