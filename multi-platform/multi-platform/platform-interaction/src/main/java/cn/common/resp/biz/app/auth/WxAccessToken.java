package cn.common.resp.biz.app.auth;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common.resp.biz.app.auth
 * @Description: 微信的AccessToken
 * @date 2023-02-26
 */
@Data
public class WxAccessToken implements Serializable {

    private static final long serialVersionUID = -3460685024355362570L;

    /**
     * 拿到到的凭证
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    @JSONField(name = "access_token")
    private Integer expiresIn;

    /**
     * 错误码
     */
    @JSONField(name = "errcode")
    private Integer errCode;

    /**
     * 错误信息
     */
    @JSONField(name = "errmsg")
    private String errMsg;

}
