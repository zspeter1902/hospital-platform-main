package cn.common.resp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * APP用户其他信息数据导出请求封装类
 * @title: AppUserDataExportResp.java
 * @author Singer
 * @date
*/
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppUserDataExportResp implements Serializable {

    private static final long serialVersionUID = 565535387212603451L;

    /**
     * APP用户ID
     */
    @NotEmpty(message = "APP用户ID->不可为空")
    @ExcelProperty(value = "APP用户ID")
    private String authAppUserId;

    /**
     * 姓名
     */
    @NotEmpty(message = "姓名->不可为空")
    @ExcelProperty(value = "姓名")
    private String realName;

    /**
     * 身份证号
     */
    @NotEmpty(message = "身份证号->不可为空")
    @ExcelProperty(value = "身份证号")
    private String identityCard;

    /**
     * 性别
     */
    @NotEmpty(message = "性别->不可为空")
    @ExcelProperty(value = "性别")
    private String gender;

}
