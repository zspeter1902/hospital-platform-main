package cn.common.api.biz;

import cn.common.api.BaseApiController;
import cn.common.req.biz.app.auth.AuthAppUserLoginReq;
import cn.common.req.biz.app.auth.AuthAppUserReqReq;
import cn.common.req.biz.app.auth.WxMiniOpenIdReq;
import cn.common.req.biz.app.auth.WxMiniPhoneReq;
import cn.common.req.biz.platform.AuthAppUserUpdateReq;
import cn.common.resp.biz.app.auth.AuthAppLoginResp;
import cn.common.resp.biz.platform.AuthAppUserResp;
import cn.common.service.biz.AuthAppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pro.skywalking.anon.ApiLog;
import pro.skywalking.interceptor.NeedLogin;
import pro.skywalking.resp.app.token.TokenVerifyResp;
import pro.skywalking.resp.base.ApiResponse;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Singer
 * @packageName cn.common.api.controller
 * @Description: APP认证用户前端控制器
 * @date 2023-04-12
 */
@RestController
@RequestMapping(value = "api/v1/authAppUser")
@Slf4j
public class AuthAppUserController extends BaseApiController {

    @Resource
    private AuthAppUserService authAppUserService;

    /**
     * 退出登录
     *
     * @return pro.skywalking.resp.base.ApiResponse
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2021/12/19
     */
    @PostMapping("/logOut")
    @ApiLog(value = "退出登录")
    @NeedLogin
    public ApiResponse logOut() {
        authAppUserService.logOut();
        return apiResponse();
    }

    /**
     * 用户名密码登陆
     *
     * @param req 用户名密码登陆请求参数
     * @return
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/21
     */
    @PostMapping(value = "/userLogin")
    @ApiLog(value = "用户名密码登陆")
    @NeedLogin(value = false)
    public ApiResponse<AuthAppLoginResp> userLogin(@RequestBody @Valid
                                                   AuthAppUserLoginReq req) {
        return apiResponse(authAppUserService.userLogin(req));
    }

    /**
     * @param req
     * @return cn.common.resp.biz.app.auth.AuthAppLoginResp
     * @description: 绑定用户的微信手机号
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/25
     */
    @PostMapping(value = "/bindWxPhone")
    @ApiLog(value = "绑定用户的微信手机号")
    @NeedLogin()
    public ApiResponse<AuthAppLoginResp> bindWxPhone(@RequestBody @Valid
                                                     WxMiniPhoneReq req) {
        return apiResponse(authAppUserService.bindWxPhone(req));
    }

    /**
     * 用户注册
     *
     * @param req 用户名密码注册请求参数
     * @return
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/21
     */
    @PostMapping(value = "/userReg")
    @ApiLog(value = "用户注册")
    @NeedLogin(value = false)
    public ApiResponse<AuthAppLoginResp> userReg(@RequestBody @Valid
                                                 AuthAppUserReqReq req) {
        return apiResponse(authAppUserService.userReg(req));
    }

    /**
     * @param req
     * @return cn.common.resp.biz.app.auth.AuthAppLoginResp
     * @description: 绑定用户的微信openId
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/2/21
     */
    @PostMapping(value = "/bindWxOpenId")
    @ApiLog(value = "绑定用户的微信openId")
    @NeedLogin(value = false)
    public ApiResponse<AuthAppLoginResp> bindWxOpenId(@RequestBody @Valid
                                                      WxMiniOpenIdReq req) {
        return apiResponse(authAppUserService.bindWxOpenId(req));
    }

    /**
     * 验证Token
     *
     * @return java.lang.Boolean
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2022/1/21
     */
    @GetMapping("/verifyToken")
    @ApiLog(value = "验证Token")
    @NeedLogin(value = false)
    public ApiResponse<TokenVerifyResp> verifyToken() {
        return apiResponse(authAppUserService.verifyToken());
    }

    /**
     * 更新APP认证用户
     *
     * @param updateReq 更新请求参数
     * @author: Singer
     * @date 2022/09/20
     */
    @PutMapping(value = "/updateAppAuthUser")
    @ApiLog(value = "更新APP认证用户")
    @NeedLogin()
    public ApiResponse updateAppAuthUser(@RequestBody @Valid AuthAppUserUpdateReq updateReq) {
        authAppUserService.updateAppAuthUser(updateReq);
        return apiResponse();
    }

    /**
     * @param
     * @return cn.common.resp.biz.platform.AuthAppUserResp
     * @description: 查询当前APP用户的数据
     * @author: create by singer - Singer email:singer-coder@qq.com
     * @date 2023/4/21
     */
    @GetMapping(value = "/currentUserMeta")
    @ApiLog(value = "查询当前APP用户的数据")
    @NeedLogin()
    public ApiResponse<AuthAppUserResp> currentUserMeta() {
        return apiResponse(authAppUserService.currentUserMeta());
    }
}
