package cn.common.req.platform;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * 首页轮播图实体
 * @title: MainSwiperUpdateReq.java
 * @author Singer
 * @date 2022/9/27 11:13
 */
@Data
public class MainSwiperUpdateReq implements Serializable {

    private static final long serialVersionUID = 8983829390039820016L;

    /**
     * 主键ID->ID
     */
    private Long id;

    /**
     * 业务主键ID->"mainSwiperId"
     */
    @NotEmpty(message = "业务主键ID->不可为空")
    private String mainSwiperId;


    /**
     * 图片地址
     */
     @NotEmpty(message = "图片地址->不可为空")
     private String mainUrl;

    /**
     * 标题
     */
    //@NotEmpty(message = "标题->不可为空")
     private String mainTitle;

    /**
     * 跳转地址
     */
    //@NotEmpty(message = "跳转地址->不可为空")
     private String routerUrl;

    /**
     * 关联ID
     */
    //@NotEmpty(message = "关联ID->不可为空")
     private String itemId;

}
