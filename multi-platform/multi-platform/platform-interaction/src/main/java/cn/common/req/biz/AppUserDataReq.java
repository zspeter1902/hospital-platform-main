package cn.common.req.biz;

import lombok.Data;
import pro.skywalking.req.base.BasePageReq;

import java.io.Serializable;

/**
 * APP用户其他信息分页查询请求封装类
 * @title: AppUserDataReq.java
 * @author Singer
 * @date 2023/3/27 11:13
 */
@Data
public class AppUserDataReq extends BasePageReq implements Serializable {

    private static final long serialVersionUID = 8729969479681811190L;

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
