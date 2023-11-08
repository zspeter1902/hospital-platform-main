package cn.common.req.biz;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * APP用户其他信息新增Req
 * @title: AppUserDataAddReq.java
 * @author Singer
 * @date 2023/3/27 11:13
 */
@Data
public class AppUserDataAddReq implements Serializable {

    private static final long serialVersionUID = 4437952348941660948L;

    /**
     * 主键ID->ID
     */
    private Long id;

    /**
     * 业务主键ID->"appUserDataId"
     */
    private String appUserDataId;

    /**
     * APP用户ID
     */
    @NotEmpty(message = "APP用户ID->不可为空")
    private String authAppUserId;

    /**
     * 姓名
     */
    @NotEmpty(message = "姓名->不可为空")
    private String realName;

    /**
     * 身份证号
     */
    @NotEmpty(message = "身份证号->不可为空")
    private String identityCard;

    /**
     * 性别
     */
    @NotEmpty(message = "性别->不可为空")
    private String gender;

}
