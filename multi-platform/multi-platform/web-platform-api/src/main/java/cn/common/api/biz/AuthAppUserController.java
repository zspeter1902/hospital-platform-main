package cn.common.api.biz;

import cn.common.api.BaseApiController;
import cn.common.req.biz.platform.AuthAppUserAddReq;
import cn.common.req.biz.platform.AuthAppUserReq;
import cn.common.req.biz.platform.AuthAppUserUpdateReq;
import cn.common.resp.biz.platform.AuthAppUserResp;
import cn.common.service.biz.AuthAppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pro.skywalking.anon.ApiLog;
import pro.skywalking.interceptor.NeedLogin;
import pro.skywalking.req.base.BaseDeleteReq;
import pro.skywalking.resp.base.ApiResponse;
import pro.skywalking.resp.page.Pagination;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Singer
 * @packageName cn.common.api.controller
 * @Description: APP认证用户前端控制器
 * @date 2021-04-23
 */
@RestController
@RequestMapping(value = "api/v1/authAppUser")
@Slf4j
public class AuthAppUserController extends BaseApiController {

    @Resource
    private AuthAppUserService authAppUserService;

    /**
     * 新增APP认证用户
     * @author: Singer
     * @date 2021/9/27
     * @param addReq 新增Req
     */
    @PostMapping(value = "/addItem")
    @ApiLog(value = "新增信息")
    @NeedLogin()
    public ApiResponse addItem(@RequestBody @Valid AuthAppUserAddReq addReq){
        authAppUserService.addItem(addReq);
        return apiResponse();
    }

    /**
     * 导出APP认证用户数据
     * @author: Singer
     * @date 2022/3/21
     * @param pageReq
     * @return java.util.List
     */
    @PostMapping(value = "/exportData")
    @ApiLog(value = "APP认证用户数据导出")
    @NeedLogin()
    public void exportData(@RequestBody(required = false) @Valid AuthAppUserReq pageReq){
        authAppUserService.exportData(pageReq);
    }

    /**
     * 主键ID集合批量删除APP认证用户
     * @author: Singer
     * @date 2021/2/2
     * @param req 需要被删除的信息
     */
    @PostMapping(value = "/batchDeleteItem")
    @ApiLog(value = "根据主键ID集合批量删除")
    @NeedLogin()
    public ApiResponse batchDeleteItem(@RequestBody BaseDeleteReq req){
        authAppUserService.batchDeleteItem(req);
        return apiResponse();
    }

    /**
     * 更新APP认证用户
     * @author: Singer
     * @date 2022/09/20
     * @param updateReq 更新请求参数
     */
    @PutMapping(value = "/updateItem")
    @ApiLog(value = "更新信息")
    @NeedLogin()
    public ApiResponse updateItem(@RequestBody @Valid AuthAppUserUpdateReq updateReq){
        authAppUserService.updateItem(updateReq);
        return apiResponse();
    }

    /**
     * 查询所有APP认证用户信息
     * @author: Singer
     * @date 2022/3/13
     * @param
     * @return java.util.List
     */
    @GetMapping(value = "/queryAllAuthAppUser")
    @ApiLog(value = "查询所有信息")
    @NeedLogin()
    public ApiResponse<List<AuthAppUserResp>> queryAllAuthAppUser(){
        return apiResponse(authAppUserService.queryAllAuthAppUser());
    }

    /**
     * 分页查询APP认证用户
     * @author: Singer
     * @date 2021/9/27
     * @param  pageReq 分页查询Req
     * @return Pagination
     */
    @PostMapping(value = "/queryByPage")
    @ApiLog(value = "分页查询信息")
    @NeedLogin()
    public ApiResponse<Pagination<AuthAppUserResp>> queryByPage(
            @RequestBody @Valid AuthAppUserReq pageReq){
        return apiResponse(authAppUserService.queryByPage(pageReq));
    }
}
