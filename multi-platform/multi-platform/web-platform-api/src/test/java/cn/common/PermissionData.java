package cn.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName hospital-platform-api
 * @Description: 权限数据
 * @date 2023-02-15
 */
@Data
public class PermissionData implements Serializable {

    private static final long serialVersionUID = 1402592067298747196L;

    /**
     * 上级权限id
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sortIndex;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 前端路径
     */
    private String permissionPath;


}
