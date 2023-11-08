package cn.common.req.biz.app.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信支付回调
 * @date 2023-02-24
 */
@Data
public class WxPayCallPack implements Serializable {

     /**
       * 通知ID
       */
    private String id;

    /**
     * 通知创建时间
     */
    @JSONField(name = "create_time")
    //@DateTimeFormat(pattern = "yyyy-MM-DDTHH:mm:ss+TIMEZONE")
    private String createTime;

    /**
     * 通知数据类型
     */
    @JSONField(name = "resource_type")
    private String resourceType;

    /**
     * 通知类型
     */
    @JSONField(name = "event_type")
    private String eventType;

    /**
     * 回调摘要
     */
    private String summary;

    /**
     * 通知数据
     */
    private WxPayCallPackResource resource;


}
