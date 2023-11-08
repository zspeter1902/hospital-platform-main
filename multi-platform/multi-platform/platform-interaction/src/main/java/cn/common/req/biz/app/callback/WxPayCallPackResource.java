package cn.common.req.biz.app.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信支付Resource
 * @date 2023-02-24
 */
@Data
public class WxPayCallPackResource implements Serializable {

    private static final long serialVersionUID = 7743036652626728694L;

    /**
     * 原始类型
     */
    @JSONField(name = "original_type")
    private String originalType;

    /**
     * 加密算法类型
     */
    private String algorithm;

    /**
     * 数据密文
     */
    private String ciphertext;

    /**
     * 附加数据
     */
    @JSONField(name = "associated_data")
    private String associatedData;

    /**
     * 随机串
     */
    private String nonce;

}
