package cn.common;

import pro.skywalking.collection.CollectionUtils;
import cn.common.repository.entity.platform.AuthPermission;
import cn.common.repository.entity.platform.AuthRole;
import cn.common.repository.entity.platform.AuthRolePermission;
import cn.common.repository.entity.platform.AuthUser;
import cn.common.repository.entity.platform.AuthUserRole;
import cn.common.repository.repository.platform.AuthPermissionRepository;
import cn.common.repository.repository.platform.AuthRolePermissionRepository;
import cn.common.repository.repository.platform.AuthRoleRepository;
import cn.common.repository.repository.platform.AuthUserRepository;
import cn.common.repository.repository.platform.AuthUserRoleRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common
 * @Description: API测试
 * @date 2021-12-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebPlatformApplication.class)
@ActiveProfiles("dev")
@Slf4j
//@Ignore
public class ApiTest {

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
