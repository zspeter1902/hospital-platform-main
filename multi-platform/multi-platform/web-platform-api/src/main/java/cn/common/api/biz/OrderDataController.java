package cn.common.api.biz;

import cn.common.api.BaseApiController;
import cn.common.req.biz.platform.OrderDataAddReq;
import cn.common.req.biz.platform.OrderDataReq;
import cn.common.req.biz.platform.OrderDataUpdateReq;
import cn.common.resp.biz.OrderDataResp;
import cn.common.service.biz.OrderDataService;
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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Singer
 * @packageName cn.common.api.controller
 * @Description: 订单信息前端控制器
 * @date 2021-04-23
 */
@RestController
@RequestMapping(value = "api/v1/orderData")
@Slf4j
public class OrderDataController extends BaseApiController {

    @Resource
    private OrderDataService orderDataService;

    /**
     * 新增订单信息
     * @author: Singer
     * @date 2021/9/27
     * @param addReq 新增Req
     */
    @PostMapping(value = "/addItem")
    @ApiLog(value = "新增信息")
    @NeedLogin()
    public ApiResponse addItem(@RequestBody @Valid OrderDataAddReq addReq){
        orderDataService.addItem(addReq);
        return apiResponse();
    }

    /**
     * 导出订单信息数据
     * @author: Singer
     * @date 2022/3/21
     * @param pageReq
     * @return java.util.List
    */
    @PostMapping(value = "/exportData")
    @ApiLog(value = "订单信息数据导出")
    @NeedLogin()
    public void exportData(@RequestBody(required = false) @Valid OrderDataReq pageReq){
        orderDataService.exportData(pageReq);
    }

    /**
     * 主键ID集合批量删除订单信息
     * @author: Singer
     * @date 2021/2/2
     * @param req 需要被删除的信息
     */
    @PostMapping(value = "/batchDeleteItem")
    @ApiLog(value = "根据主键ID集合批量删除")
    @NeedLogin()
    public ApiResponse batchDeleteItem(@RequestBody BaseDeleteReq req){
        orderDataService.batchDeleteItem(req);
        return apiResponse();
    }

    /**
     * 更新订单信息
     * @author: Singer
     * @date 2022/09/20
     * @param updateReq 更新请求参数
     */
    @PutMapping(value = "/updateItem")
    @ApiLog(value = "更新信息")
    @NeedLogin()
    public ApiResponse updateItem(@RequestBody @Valid OrderDataUpdateReq updateReq){
        orderDataService.updateItem(updateReq);
        return apiResponse();
    }

    /**
     * 查询单个的订单数据
     * @author: Singer
     * @date 2022/3/13
     * @param outTradeNo 交易订单号
     * @return java.util.List
     */
    @GetMapping(value = "/queryByTradeNo")
    @ApiLog(value = "查询单个的订单数据")
    @NeedLogin()
    public ApiResponse<OrderDataResp> queryByTradeNo(@RequestParam(name = "outTradeNo")
                                                     String outTradeNo){
        return apiResponse(orderDataService.queryByTradeNo(outTradeNo));
    }

    /**
     * 查询所有订单信息信息
     * @author: Singer
     * @date 2022/3/13
     * @param
     * @return java.util.List
     */
    @GetMapping(value = "/queryAllOrderData")
    @ApiLog(value = "查询所有信息")
    @NeedLogin()
    public ApiResponse<List<OrderDataResp>> queryAllOrderData(){
        return apiResponse(orderDataService.queryAllOrderData());
    }

    /**
     * 分页查询订单信息
     * @author: Singer
     * @date 2021/9/27
     * @param  pageReq 分页查询Req
     * @return Pagination
     */
    @PostMapping(value = "/queryByPage")
    @ApiLog(value = "分页查询信息")
    @NeedLogin()
    public ApiResponse<Pagination<OrderDataResp>> queryByPage(
        @RequestBody @Valid OrderDataReq pageReq){
        return apiResponse(orderDataService.queryByPage(pageReq));
    }
}
