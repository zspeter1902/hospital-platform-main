package cn.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author create by singer - Singer email:singer-coder@qq.com
 * @packageName pro.common.enums
 * @Description: 订单类型信息
 * @date 2023-02-24
 */
@Getter
@AllArgsConstructor
public enum OrderTypeEnum {

    /**
     * 活动信息
     */
    EVENT ("EVENT","正常");


    /**
     * 状态码
     */
    private String code;

    /**
     * 值
     */
    private String value;


    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
