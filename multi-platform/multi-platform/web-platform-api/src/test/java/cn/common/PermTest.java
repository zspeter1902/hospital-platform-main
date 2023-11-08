package cn.common;

import cn.common.repository.entity.platform.*;
import cn.common.repository.repository.platform.*;
import cn.common.service.platform.AuthPermissionService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pro.skywalking.collection.CollectionUtils;
import pro.skywalking.constants.AuthConstants;
import pro.skywalking.enums.ErrorCode;
import pro.skywalking.exception.BusinessException;
import pro.skywalking.utils.BaseUtil;
import pro.skywalking.utils.SnowflakeIdWorker;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common
 * @Description: API测试
 * @date 2021-12-19
 */
@SpringBootTest(classes = WebPlatformApplication.class)
@ActiveProfiles("dev")
@Slf4j
//@Ignore
public class PermTest {

    @Resource
    private AuthUserRepository authUserRepository;

    @Resource
    private AuthPermissionRepository authPermissionRepository;

    @Resource
    private AuthRolePermissionRepository authRolePermissionRepository;

    @Resource
    private AuthUserRoleRepository authUserRoleRepository;

    @Resource
    private AuthRoleRepository authRoleRepository;

    @Resource
    private AuthPermissionService authPermissionService;

    static {
        System.setProperty("LOG_DIR","/Users/singer/logs");
    }


    /**
     *
     * @description: 根据名称删除权限信息权限信息
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     */
    @Test
    public void deleteByPath(){
        List<String> pathList = Lists.newArrayList();
        pathList.add("dataReport/cashEarningRecord/ItemList");
        authPermissionService.deletePermByPath(pathList);
    }

    /**
     *
     * @description: 生成权限信息
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     */
    @Test
    public void genAuth(){
        String entityName = "nursingService";
        String authName = "护士服务信息";

        List<AuthPermission> authPermissionList = Lists.newArrayList();

        AuthPermission dirPermission = returnDirAuth(authName, entityName);
        authPermissionList.add(dirPermission);
        String authPermissionId = dirPermission.getAuthPermissionId();
        //页面权限
        AuthPermission pagePermission = returnPageAuth(authName, entityName, authPermissionId);
        authPermissionList.add(pagePermission);
        String buttonParentId = pagePermission.getAuthPermissionId();
        List<PermissionData> permissionDataList = returnButtonAuthData(entityName, authName, buttonParentId);
        permissionDataList.stream().forEach(item -> {
            AuthPermission authPermission = returnButtonAuth(item);
            authPermissionList.add(authPermission);
        });
        authPermissionList.stream().forEach(item -> {
            authPermissionRepository.insert(item);
        });
    }

    /**
     *
     * @description: 返回页面权限
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     * @param data 权限数据
     * @return cn.common.repository.entity.platform.AuthPermission
     */
    public AuthPermission returnButtonAuth(PermissionData data){

        String permissionName = data.getPermissionName();
        String permissionPath = data.getPermissionPath();
        String parentId = data.getParentId();

        //1 页面 2 按钮 3 文件夹 4 其他资源
        Integer permissionType = 2;
        //排序
        Integer sortIndex = 10;

        //权限编码
        String permissionCode = permissionPath;

        //权限图标
        String permissionIcon = StrUtil.EMPTY;

        //备注
        String permissionRemark = permissionName;


        AuthPermission authPermission = new AuthPermission();

        authPermission.setParentId(parentId);
        authPermission.setPermissionType(permissionType);
        authPermission.setSortIndex(sortIndex);
        authPermission.setPermissionName(permissionName);
        authPermission.setPermissionPath(permissionPath);
        authPermission.setPermissionCode(permissionCode);
        authPermission.setPermissionIcon(permissionIcon);
        authPermission.setPermissionRemark(permissionRemark);

        try {
            String authUserId = StrUtil.EMPTY;
            BaseUtil.setFieldValueNotNull(authPermission);
            authPermission.setAuthPermissionId(SnowflakeIdWorker.uniqueMainId());
            authPermission.setOperatorId(authUserId);
        } catch (Exception e) {
            log.error("新增->设置为空的属性失败 {} , {} ",e.getMessage(),e);
            throw new BusinessException(ErrorCode.ERROR.getCode(),
                    ErrorCode.ERROR.getMessage());
        }
        return authPermission;
    }

    /**
     *
     * @description: 生成按钮权限数据
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     * @param entityName 权限英文名称
     * @param authName 权限名称
     * @param parentId 父级权限ID
     * @return
     */
    public List<PermissionData> returnButtonAuthData(String entityName,
                                                     String authName,
                                                     String parentId){

        PermissionData exportAuth = new PermissionData();
        exportAuth.setSortIndex(2);
        exportAuth.setPermissionName(authName+"导出");
        exportAuth.setPermissionPath(entityName+":export");

        PermissionData addAuth = new PermissionData();
        addAuth.setSortIndex(1);
        addAuth.setPermissionName(authName+"新增");
        addAuth.setPermissionPath(entityName+":add");

        PermissionData deleteBatchAuth = new PermissionData();
        deleteBatchAuth.setSortIndex(4);
        deleteBatchAuth.setPermissionName(authName+"批量删除");
        deleteBatchAuth.setPermissionPath(entityName+":batchDelete");

        PermissionData deleteOneAuth = new PermissionData();
        deleteOneAuth.setSortIndex(3);
        deleteOneAuth.setPermissionName(authName+"删除单个");
        deleteOneAuth.setPermissionPath(entityName+":deleteOne");

        PermissionData editAuth = new PermissionData();
        editAuth.setSortIndex(5);
        editAuth.setPermissionName(authName+"更新");
        editAuth.setPermissionPath(entityName+":edit");

        PermissionData viewAuth = new PermissionData();
        viewAuth.setSortIndex(7);
        viewAuth.setPermissionName(authName+"查看");
        viewAuth.setPermissionPath(entityName+":view");


        List<PermissionData> resultList = Lists.newArrayList();
        resultList.add(exportAuth);
        resultList.add(addAuth);
        resultList.add(deleteBatchAuth);
        resultList.add(deleteOneAuth);
        resultList.add(editAuth);
        resultList.add(viewAuth);

        resultList.stream().forEach(item -> {
            item.setParentId(parentId);
        });

        return resultList;
    }

    public List<AuthPermission> returnButtonAuth(){
        return Lists.newArrayList();
    }

    /**
     *
     * @description: 返回页面权限
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     * @param permissionName 权限名称
     * @param permissionPath 前端路径
     * @param parentId 父级权限
     * @return cn.common.repository.entity.platform.AuthPermission
     */
    public AuthPermission returnPageAuth(String permissionName,
                                         String permissionPath,
                                         String parentId){
        //1 页面 2 按钮 3 文件夹 4 其他资源
        Integer permissionType = 1;
        //排序
        Integer sortIndex = 10;

        //前端页面的名称
        String itemList = "/ItemList";

        permissionPath = permissionPath + itemList;

        //权限编码
        String permissionCode = permissionPath;

        //权限图标
        String permissionIcon = "el-icon-s-promotion";

        //备注
        String permissionRemark = permissionName;


        AuthPermission authPermission = new AuthPermission();

        authPermission.setParentId(parentId);
        authPermission.setPermissionType(permissionType);
        authPermission.setSortIndex(sortIndex);
        authPermission.setPermissionName(permissionName);
        authPermission.setPermissionPath(permissionPath);
        authPermission.setPermissionCode(permissionCode);
        authPermission.setPermissionIcon(permissionIcon);
        authPermission.setPermissionRemark(permissionRemark);

        try {
            String authUserId = StrUtil.EMPTY;
            BaseUtil.setFieldValueNotNull(authPermission);
            authPermission.setAuthPermissionId(SnowflakeIdWorker.uniqueMainId());
            authPermission.setOperatorId(authUserId);
        } catch (Exception e) {
            log.error("新增->设置为空的属性失败 {} , {} ",e.getMessage(),e);
            throw new BusinessException(ErrorCode.ERROR.getCode(),
                    ErrorCode.ERROR.getMessage());
        }
        return authPermission;
    }

    /**
     *
     * @description:
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     * @param permissionName 权限名称
     * @param permissionPath 前端路径
     * @return cn.common.repository.entity.platform.AuthPermission
     */
    public AuthPermission returnDirAuth(String permissionName,
                                        String permissionPath){
        //父级权限ID
        String parentId = AuthConstants.TOP_NODE_ID;
        //1 页面 2 按钮 3 文件夹 4 其他资源
        Integer permissionType = 3;
        //排序
        Integer sortIndex = 10;

        //权限编码
        String permissionCode = permissionPath;

        //权限图标
        String permissionIcon = "el-icon-s-promotion";

        //备注
        String permissionRemark = permissionName;


        AuthPermission authPermission = new AuthPermission();

        authPermission.setParentId(parentId);
        authPermission.setPermissionType(permissionType);
        authPermission.setSortIndex(sortIndex);
        authPermission.setPermissionName(permissionName);
        authPermission.setPermissionPath(permissionPath);
        authPermission.setPermissionCode(permissionCode);
        authPermission.setPermissionIcon(permissionIcon);
        authPermission.setPermissionRemark(permissionRemark);

        try {
            String authUserId = StrUtil.EMPTY;
            BaseUtil.setFieldValueNotNull(authPermission);
            authPermission.setAuthPermissionId(SnowflakeIdWorker.uniqueMainId());
            authPermission.setOperatorId(authUserId);
        } catch (Exception e) {
            log.error("新增->设置为空的属性失败 {} , {} ",e.getMessage(),e);
            throw new BusinessException(ErrorCode.ERROR.getCode(),
                    ErrorCode.ERROR.getMessage());
        }
        return authPermission;
    }

    /**
     *
     * @description: 生成父级文件夹
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     * @param
     * @return
     */
    @Test
    public void genDirAuth(){
        //父级权限ID
        String parentId = AuthConstants.TOP_NODE_ID;
        //1 页面 2 按钮 3 文件夹 4 其他资源
        Integer permissionType = 3;
        //排序
        Integer sortIndex = 10;
        //权限名称
        String permissionName = "";

        //前端路径
        String permissionPath = "";

        //权限编码
        String permissionCode = permissionPath;

        //权限图标
        String permissionIcon = "el-icon-s-promotion";

        //备注
        String permissionRemark = permissionName;


        AuthPermission authPermission = new AuthPermission();

        authPermission.setParentId(parentId);
        authPermission.setPermissionType(permissionType);
        authPermission.setSortIndex(sortIndex);
        authPermission.setPermissionName(permissionName);
        authPermission.setPermissionPath(permissionPath);
        authPermission.setPermissionCode(permissionCode);
        authPermission.setPermissionIcon(permissionIcon);
        authPermission.setPermissionRemark(permissionRemark);

        try {
            String authUserId = StrUtil.EMPTY;
            BaseUtil.setFieldValueNotNull(authPermission);
            authPermission.setAuthPermissionId(SnowflakeIdWorker.uniqueMainId());
            authPermission.setOperatorId(authUserId);
        } catch (Exception e) {
            log.error("新增->设置为空的属性失败 {} , {} ",e.getMessage(),e);
            throw new BusinessException(ErrorCode.ERROR.getCode(),
                    ErrorCode.ERROR.getMessage());
        }
        authPermissionRepository.insert(authPermission);
    }


    /**
     *
     * @description: 查询所有
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/15
     * @param
     * @return
     */
    @Test
    public void testQueryAll(){
        List<AuthUser> authUserList = authUserRepository.selectList(new LambdaQueryWrapper<>());
        System.out.printf(JSON.toJSONString(authUserList));
    }


    /**
     * 给所有的角色-用户建立关系
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2021/12/19
     */
    @Test
    public void initAllAuthUSerRole(){
        List<AuthUserRole> authUserRoleList =  Lists.newArrayList();
        List<AuthRole> authRoleList =
                authRoleRepository.selectList(new LambdaQueryWrapper<>());
        if(CollectionUtils.isEmpty(authRoleList)){
            return;
        }
        List<AuthUser> authUserList =
                authUserRepository.selectList(new LambdaQueryWrapper<>());
        if(CollectionUtils.isEmpty(authUserList)){
            return;
        }
        authUserList.stream().forEach(authUser -> {
            authRoleList.stream().forEach(authRole -> {
                AuthUserRole authUserRole = new AuthUserRole();
                authUserRole.setAuthUserId(authUser.getAuthUserId());
                authUserRole.setAuthRoleId(authRole.getAuthRoleId());
                authUserRole.setOperatorId(authUser.getAuthUserId());
                authUserRoleList.add(authUserRole);
            });
        });
        if(!CollectionUtils.isEmpty(authUserRoleList)){
            authUserRoleList.stream().forEach(item -> {
                authUserRoleRepository.insert(item);
            });
        }
    }

    /**
     * 给所有的权限-角色建立关系
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2021/12/19
     */
    @Test
    public void initRolePermission(){
        List<AuthRolePermission> rolePermissionList = Lists.newArrayList();
        List<AuthRole> authRoleList =
                authRoleRepository.selectList(new LambdaQueryWrapper<>());
        if(CollectionUtils.isEmpty(authRoleList)){
            return;
        }
        List<AuthPermission> authPermissionList = authPermissionRepository.selectList(new LambdaQueryWrapper<>());
        if(CollectionUtils.isEmpty(authPermissionList)){
            return;
        }
        authRoleList.stream().forEach(role -> {
            authPermissionList.stream().forEach(perm -> {
                AuthRolePermission authRolePermission = new AuthRolePermission();
                authRolePermission.setAuthRoleId(role.getAuthRoleId());
                authRolePermission.setAuthPermissionId(perm.getAuthPermissionId());
                authRolePermission.setOperatorId("1");
                rolePermissionList.add(authRolePermission);
            });
        });

        if(!CollectionUtils.isEmpty(rolePermissionList)){
            rolePermissionList.stream().forEach(item -> {
                authRolePermissionRepository.insert(item);
            });
        }
    }

}
