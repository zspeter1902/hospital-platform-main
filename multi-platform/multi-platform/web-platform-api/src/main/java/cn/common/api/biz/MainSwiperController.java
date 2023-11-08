package cn.common.api.biz;

import cn.common.api.BaseApiController;
import cn.common.req.platform.MainSwiperAddReq;
import cn.common.req.platform.MainSwiperReq;
import cn.common.req.platform.MainSwiperUpdateReq;
import cn.common.resp.platform.MainSwiperResp;
import cn.common.service.biz.platform.MainSwiperService;
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
 * @Description: 首页轮播图前端控制器
 * @date 2021-02-19
 */
@RestController
@RequestMapping(value = "api/v1/mainSwiper")
@Slf4j
public class MainSwiperController extends BaseApiController {

    @Resource
    private MainSwiperService mainSwiperService;

    /**
     * 新增首页轮播图
     * @author: Singer
     * @date 2021/9/27
     * @param addReq 新增Req
     */
    @PostMapping(value = "/addItem")
    @ApiLog(value = "新增信息")
    @NeedLogin()
    public ApiResponse addItem(@RequestBody @Valid MainSwiperAddReq addReq){
        mainSwiperService.addItem(addReq);
        return apiResponse();
    }

    /**
     * 主键ID集合批量删除首页轮播图
     * @author: Singer
     * @date 2021/2/2
     * @param req 需要被删除的信息
     */
    @PostMapping(value = "/batchDeleteItem")
    @ApiLog(value = "根据主键ID集合批量删除")
    @NeedLogin()
    public ApiResponse batchDeleteItem(@RequestBody BaseDeleteReq req){
        mainSwiperService.batchDeleteItem(req);
        return apiResponse();
    }

    /**
     * 更新首页轮播图
     * @author: Singer
     * @date 2022/09/20
     * @param updateReq 更新请求参数
     */
    @PutMapping(value = "/updateItem")
    @ApiLog(value = "更新信息")
    @NeedLogin()
    public ApiResponse updateItem(@RequestBody @Valid MainSwiperUpdateReq updateReq){
        mainSwiperService.updateItem(updateReq);
        return apiResponse();
    }

    /**
     * 查询所有首页轮播图信息
     * @author: Singer
     * @date 2022/3/13
     * @param
     * @return java.util.List
     */
    @GetMapping(value = "/queryAllMainSwiper")
    @ApiLog(value = "查询所有信息")
    @NeedLogin()
    public ApiResponse<List<MainSwiperResp>> queryAllMainSwiper(){
        return apiResponse(mainSwiperService.queryAllMainSwiper());
    }

    /**
     * 分页查询首页轮播图
     * @author: Singer
     * @date 2021/9/27
     * @param  pageReq 分页查询Req
     * @return Pagination
     */
    @PostMapping(value = "/queryByPage")
    @ApiLog(value = "分页查询信息")
    @NeedLogin()
    public ApiResponse<Pagination<MainSwiperResp>> queryByPage(
        @RequestBody @Valid MainSwiperReq pageReq){
        return apiResponse(mainSwiperService.queryByPage(pageReq));
    }
}
