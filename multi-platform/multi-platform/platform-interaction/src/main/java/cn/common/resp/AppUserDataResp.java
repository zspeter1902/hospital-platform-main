package cn.common.resp;

import lombok.Data;
import pro.skywalking.req.platform.base.BaseResp;

import java.io.Serializable;

/**
 * APP用户其他信息返回数据封装类
 * @title: AppUserDataResp.java
 * @author Singer
 * @date 2023/3/27 11:13
 */
@Data
public class AppUserDataResp extends BaseResp implements Serializable {

    private static final long serialVersionUID = -3966809327121581656L;

    /**
     * 业务主键ID->"appUserDataId"
     */
    private String appUserDataId;

    /**
     * APP用户ID
     */
    private String authAppUserId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 性别
     */
    private String gender;

}
