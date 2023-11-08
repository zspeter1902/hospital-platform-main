package cn.common.api.biz;

import cn.common.api.BaseApiController;
import cn.common.req.biz.platform.PayDataAddReq;
import cn.common.req.biz.platform.PayDataReq;
import cn.common.req.biz.platform.PayDataUpdateReq;
import cn.common.resp.biz.PayDataResp;
import cn.common.service.biz.PayDataService;
import pro.skywalking.req.base.BaseDeleteReq;
import pro.skywalking.resp.base.ApiResponse;
import pro.skywalking.resp.page.Pagination;
import pro.skywalking.anon.ApiLog;
import pro.skywalking.interceptor.NeedLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Singer
 * @packageName cn.common.api.controller
 * @Description: 支付数据信息前端控制器
 * @date 2021-04-23
 */
@RestController
@RequestMapping(value = "api/v1/payData")
@Slf4j
public class PayDataController extends BaseApiController {

    @Resource
    private PayDataService payDataService;

    /**
     * 新增支付数据信息
     * @author: Singer
     * @date 2021/9/27
     * @param addReq 新增Req
     */
    @PostMapping(value = "/addItem")
    @ApiLog(value = "新增信息")
    @NeedLogin()
    public ApiResponse addItem(@RequestBody @Valid PayDataAddReq addReq){
        payDataService.addItem(addReq);
        return apiResponse();
    }

    /**
     * 导出支付数据信息数据
     * @author: Singer
     * @date 2022/3/21
     * @param pageReq
     * @return java.util.List
    */
    @PostMapping(value = "/exportData")
    @ApiLog(value = "支付数据信息数据导出")
    @NeedLogin()
    public void exportData(@RequestBody(required = false) @Valid PayDataReq pageReq){
        payDataService.exportData(pageReq);
    }

    /**
     * 主键ID集合批量删除支付数据信息
     * @author: Singer
     * @date 2021/2/2
     * @param req 需要被删除的信息
     */
    @PostMapping(value = "/batchDeleteItem")
    @ApiLog(value = "根据主键ID集合批量删除")
    @NeedLogin()
    public ApiResponse batchDeleteItem(@RequestBody BaseDeleteReq req){
        payDataService.batchDeleteItem(req);
        return apiResponse();
    }

    /**
     * 更新支付数据信息
     * @author: Singer
     * @date 2022/09/20
     * @param updateReq 更新请求参数
     */
    @PutMapping(value = "/updateItem")
    @ApiLog(value = "更新信息")
    @NeedLogin()
    public ApiResponse updateItem(@RequestBody @Valid PayDataUpdateReq updateReq){
        payDataService.updateItem(updateReq);
        return apiResponse();
    }

    /**
     * 查询所有支付数据信息信息
     * @author: Singer
     * @date 2022/3/13
     * @param
     * @return java.util.List
     */
    @GetMapping(value = "/queryAllPayData")
    @ApiLog(value = "查询所有信息")
    @NeedLogin()
    public ApiResponse<List<PayDataResp>> queryAllPayData(){
        return apiResponse(payDataService.queryAllPayData());
    }

    /**
     * 分页查询支付数据信息
     * @author: Singer
     * @date 2021/9/27
     * @param  pageReq 分页查询Req
     * @return Pagination
     */
    @PostMapping(value = "/queryByPage")
    @ApiLog(value = "分页查询信息")
    @NeedLogin()
    public ApiResponse<Pagination<PayDataResp>> queryByPage(
        @RequestBody @Valid PayDataReq pageReq){
        return apiResponse(payDataService.queryByPage(pageReq));
    }
}
