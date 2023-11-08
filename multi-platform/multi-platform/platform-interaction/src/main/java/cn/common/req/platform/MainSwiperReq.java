package cn.common.req.platform;

import lombok.Data;
import pro.skywalking.req.base.BasePageReq;

import java.io.Serializable;

/**
 * 首页轮播图分页查询请求封装类
 * @title: MainSwiperReq.java
 * @author Singer
 * @date 2022/9/27 11:13
 */
@Data
public class MainSwiperReq extends BasePageReq implements Serializable {

    private static final long serialVersionUID = -2324623505775530124L;

    /**
     * 业务主键ID->"mainSwiperId"
     */
    private String mainSwiperId;

    /**
     * 图片地址
     */
    private String mainUrl;

    /**
     * 标题
     */
    private String mainTitle;

    /**
     * 跳转地址
     */
    private String routerUrl;

    /**
     * 关联ID
     */
    private String itemId;

}
