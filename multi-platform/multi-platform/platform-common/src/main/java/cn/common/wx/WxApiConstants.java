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
        prefix = "base-constant.wx-api",
        ignoreUnknownFields = false
)
public class WxApiConstants {

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     */
    private String wxAuthCode2SessionApi;

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     */
    private String userPhoneApi;

    /**
     * 拿到小程序全局唯一后台接口调用凭据（access_token）。调用绝大多数后台接口时都需使用 access_token
     */
    private String authAccessTokenApi;

    /**
     * 微信小程序生成页面二维码的请求链接
     */
    private String pageQrCodeUnLimit;

    public String getWxAuthCode2SessionApi() {
        return wxAuthCode2SessionApi;
    }

    public void setWxAuthCode2SessionApi(String wxAuthCode2SessionApi) {
        this.wxAuthCode2SessionApi = wxAuthCode2SessionApi;
    }

    public String getUserPhoneApi() {
        return userPhoneApi;
    }

    public void setUserPhoneApi(String userPhoneApi) {
        this.userPhoneApi = userPhoneApi;
    }

    public String getAuthAccessTokenApi() {
        return authAccessTokenApi;
    }

    public void setAuthAccessTokenApi(String authAccessTokenApi) {
        this.authAccessTokenApi = authAccessTokenApi;
    }

    public String getPageQrCodeUnLimit() {
        return pageQrCodeUnLimit;
    }

    public void setPageQrCodeUnLimit(String pageQrCodeUnLimit) {
        this.pageQrCodeUnLimit = pageQrCodeUnLimit;
    }
}
