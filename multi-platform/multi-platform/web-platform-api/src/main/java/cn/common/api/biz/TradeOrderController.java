package cn.common.api.biz;

import cn.common.api.BaseApiController;
import cn.common.req.biz.platform.TradeOrderAddReq;
import cn.common.req.biz.platform.TradeOrderReq;
import cn.common.req.biz.platform.TradeOrderUpdateReq;
import cn.common.resp.biz.TradeOrderResp;
import cn.common.service.biz.TradeOrderService;
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
 * @Description: 交易订单信息前端控制器
 * @date 2021-04-23
 */
@RestController
@RequestMapping(value = "api/v1/tradeOrder")
@Slf4j
public class TradeOrderController extends BaseApiController {

    @Resource
    private TradeOrderService tradeOrderService;

    /**
     * 新增交易订单信息
     * @author: Singer
     * @date 2021/9/27
     * @param addReq 新增Req
     */
    @PostMapping(value = "/addItem")
    @ApiLog(value = "新增信息")
    @NeedLogin()
    public ApiResponse addItem(@RequestBody @Valid TradeOrderAddReq addReq){
        tradeOrderService.addItem(addReq);
        return apiResponse();
    }

    /**
     * 导出交易订单信息数据
     * @author: Singer
     * @date 2022/3/21
     * @param pageReq
     * @return java.util.List
    */
    @PostMapping(value = "/exportData")
    @ApiLog(value = "交易订单信息数据导出")
    @NeedLogin()
    public void exportData(@RequestBody(required = false) @Valid TradeOrderReq pageReq){
        tradeOrderService.exportData(pageReq);
    }

    /**
     * 主键ID集合批量删除交易订单信息
     * @author: Singer
     * @date 2021/2/2
     * @param req 需要被删除的信息
     */
    @PostMapping(value = "/batchDeleteItem")
    @ApiLog(value = "根据主键ID集合批量删除")
    @NeedLogin()
    public ApiResponse batchDeleteItem(@RequestBody BaseDeleteReq req){
        tradeOrderService.batchDeleteItem(req);
        return apiResponse();
    }

    /**
     * 更新交易订单信息
     * @author: Singer
     * @date 2022/09/20
     * @param updateReq 更新请求参数
     */
    @PutMapping(value = "/updateItem")
    @ApiLog(value = "更新信息")
    @NeedLogin()
    public ApiResponse updateItem(@RequestBody @Valid TradeOrderUpdateReq updateReq){
        tradeOrderService.updateItem(updateReq);
        return apiResponse();
    }

    /**
     * 查询所有交易订单信息信息
     * @author: Singer
     * @date 2022/3/13
     * @param
     * @return java.util.List
     */
    @GetMapping(value = "/queryAllTradeOrder")
    @ApiLog(value = "查询所有信息")
    @NeedLogin()
    public ApiResponse<List<TradeOrderResp>> queryAllTradeOrder(){
        return apiResponse(tradeOrderService.queryAllTradeOrder());
    }

    /**
     * 分页查询交易订单信息
     * @author: Singer
     * @date 2021/9/27
     * @param  pageReq 分页查询Req
     * @return Pagination
     */
    @PostMapping(value = "/queryByPage")
    @ApiLog(value = "分页查询信息")
    @NeedLogin()
    public ApiResponse<Pagination<TradeOrderResp>> queryByPage(
        @RequestBody @Valid TradeOrderReq pageReq){
        return apiResponse(tradeOrderService.queryByPage(pageReq));
    }
}
