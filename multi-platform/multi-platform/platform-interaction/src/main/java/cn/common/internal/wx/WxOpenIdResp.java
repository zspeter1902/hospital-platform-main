package cn.common.internal.wx;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer email:singer-coder@qq.com
 * @projectName wx-mini-api
 * @Description: 微信
 * @date 2020-07-21
 */
@Data
public class WxOpenIdResp implements Serializable {

    private static final long serialVersionUID = 952294321853095714L;

    /**
     * 会话密钥
     */
    @JSONField(name = "session_key")
    private String sessionKey;

    /**
     * 用户唯一标识
     */
    @JSONField(name = "openid")
    private String openId;


    /**
     * 用户在开放平台的唯一标识符
     */
    @JSONField(name = "unionid")
    private String unionId;
}
