package cn.common.service.impl;

import cn.common.repository.entity.biz.AppUserData;
import cn.common.repository.repository.biz.AppUserDataRepository;
import cn.common.req.biz.AppUserDataAddReq;
import cn.common.req.biz.AppUserDataReq;
import cn.common.req.biz.AppUserDataUpdateReq;
import cn.common.resp.AppUserDataExportResp;
import cn.common.resp.AppUserDataResp;
import cn.common.service.biz.AppUserDataService;
import cn.common.service.platform.AuthUserService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pro.skywalking.collection.CollectionUtils;
import pro.skywalking.constants.BaseConstant;
import pro.skywalking.enums.ErrorCode;
import pro.skywalking.excel.ExportExcelHandler;
import pro.skywalking.exception.BusinessException;
import pro.skywalking.helper.PageBuilder;
import pro.skywalking.req.base.BaseDeleteReq;
import pro.skywalking.resp.page.Pagination;
import pro.skywalking.utils.BaseUtil;
import pro.skywalking.utils.CheckParam;
import pro.skywalking.utils.SnowflakeIdWorker;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Singer
 * @packageName cn.common.service
 * @Description: APP用户其他信息相关服务方法实现
 * @date 2023-04-12
 */
@Service("appUserDataService")
@Slf4j
public class AppUserDataServiceImpl implements AppUserDataService {
    @Resource
    private AppUserDataRepository appUserDataRepository;

    @Resource
    private MapperFacade mapperFacade;

    @Resource
    private BaseConstant baseConstant;

    @Resource
    private AuthUserService authUserService;

    @Resource
    private HttpServletResponse response;

    /**
     * 导出APP用户其他信息数据
     * @author: Singer
     * @date 2022/3/21
     * @param pageReq
     * @return java.util.List
     */
    @Override
    public void exportData(AppUserDataReq pageReq){
        log.info(">>>>>>>>>>>>>>>>>APP用户其他信息数据导出Req {} <<<<<<<<<<<<<<<<", JSON.toJSONString(pageReq));
        //构建查询条件
        LambdaQueryWrapper<AppUserData> pageWrapper = new LambdaQueryWrapper<>();
        setPageCriteria(pageWrapper,pageReq);
        pageWrapper.orderBy(true,false,AppUserData::getCreateTime);
        List<AppUserData> pageList = appUserDataRepository.selectList(pageWrapper);
        if (CollectionUtils.isEmpty(pageList)) {
            return;
        }
        List<AppUserDataExportResp> respList =
            mapperFacade.mapAsList(pageList, AppUserDataExportResp.class);
        try {
            String fileName = "导出信息.xlsx";
            ExportExcelHandler.setExportResponse(response,fileName);
            ExcelWriter excelWriter = null;
            try {
                // 这里 需要指定写用哪个class去写
                excelWriter = EasyExcel.write(response.getOutputStream(), AppUserDataExportResp.class).build();
                // 这里注意 如果同一个sheet只要创建一次
                WriteSheet writeSheet = EasyExcel.writerSheet("sheet").build();
                excelWriter.write(respList,writeSheet);
            } finally {
                // 关闭流
                if (excelWriter != null) {
                    excelWriter.finish();
                }
            }
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>>>>>>>>>>导出数据异常:{},{}<<<<<<<<<<<<<<<<<<<<<<",e.getMessage(),e);
            ExportExcelHandler.setExportErrorResponse(response);
            Map<String, String> map = Maps.newHashMap();
            try {
                response.getWriter().println(JSON.toJSONString(map));
            } catch (IOException ioException) {
                log.error(">>>>>>>>>>>>>>>>>导出数据发生异常:{},{}<<<<<<<<<<<<<<<<<",e.getMessage(),e);
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 新增APP用户其他信息
     * @author: Singer
     * @date 2021/9/27
     * @param addReq 新增APP用户其他信息Req
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addItem(AppUserDataAddReq addReq){
        log.info(">>>>>>>>>>>>>>>>>新增APP用户其他信息Req {} <<<<<<<<<<<<<<<<", JSON.toJSONString(addReq));
        AppUserData entity = mapperFacade.map(addReq, AppUserData.class);
        try {
            String authUserId = authUserService.currentAuthUserId();
            BaseUtil.setFieldValueNotNull(entity);
            entity.setAppUserDataId(SnowflakeIdWorker.uniqueMainId());
            entity.setOperatorId(authUserId);
        } catch (Exception e) {
            log.error("新增APP用户其他信息->设置为空的属性失败 {} , {} ",e.getMessage(),e);
            throw new BusinessException(ErrorCode.ERROR.getCode(),
                ErrorCode.ERROR.getMessage()+StrUtil.COLON+e.getMessage()+StrUtil.COLON+e);
        }
        appUserDataRepository.insert(entity);
    }

    /**
     * 批量删除APP用户其他信息信息
     * @author: Singer
     * @date 2021/2/2
     * @param req 需要被删除的APP用户其他信息信息
     */
    @Override
    public void batchDeleteItem(BaseDeleteReq req){
        List<String> mainIdList = req.getMainIdList();
        if(CollectionUtils.isEmpty(mainIdList)) {
            return;
        }
        List<AppUserData> entityList = appUserDataRepository.selectList(
            new LambdaQueryWrapper<AppUserData>().in(AppUserData::getAppUserDataId,mainIdList));
        entityList.stream().forEach(item -> {
            appUserDataRepository.deleteById(item);
        });
    }

    /**
     * 查询所有APP用户其他信息信息
     * @author: Singer
     * @date 2022/3/13
     * @param
     * @return java.util.List
     */
    @Override
    public List<AppUserDataResp> queryAllAppUserData(){
        List<AppUserData> entityList = appUserDataRepository.selectList(new LambdaQueryWrapper<>());
        if(CollectionUtils.isEmpty(entityList)){
            return Lists.newArrayList();
        }
        return mapperFacade.mapAsList(entityList,AppUserDataResp.class);
    }

    /**
     * 分页查询APP用户其他信息
     * @author: Singer
     * @date 2021/9/27
     * @param  pageReq 分页查询APP用户其他信息Req
     * @return Pagination
     */
    @Override
    public Pagination<AppUserDataResp> queryByPage(
        AppUserDataReq pageReq){
        log.info(">>>>>>>>>>>>>>>>>分页查询APP用户其他信息Req {} <<<<<<<<<<<<<<<<", JSON.toJSONString(pageReq));
        //构建查询条件
        LambdaQueryWrapper<AppUserData> pageWrapper = new LambdaQueryWrapper<>();
        setPageCriteria(pageWrapper,pageReq);
        pageWrapper.orderBy(true,false,AppUserData::getCreateTime);
        //开始分页
        Page<Object> page = PageHelper.startPage(pageReq.getCurrentPage(), pageReq.getItemsPerPage());
        List<AppUserData> pageList = appUserDataRepository.selectList(pageWrapper);
        if (CollectionUtils.isEmpty(pageList)) {
            return PageBuilder.buildPageResult(page,new ArrayList<>());
        }
        List<AppUserDataResp> respList =
            mapperFacade.mapAsList(pageList, AppUserDataResp.class);
        Integer startIndex = (pageReq.getItemsPerPage() * pageReq.getCurrentPage()) - pageReq.getItemsPerPage() + 1;
        AtomicInteger idBeginIndex = new AtomicInteger(startIndex);
        respList.stream().forEach(item -> {
            item.setId(Integer.valueOf(idBeginIndex.getAndIncrement()).longValue());
        });
        return PageBuilder.buildPageResult(page,respList);
    }

    /**
     * 设置分页条件
     * @author: Singer
     * @date 2021/5/31
     * @param pageWrapper 查询条件
     * @param pageReq 查询参数
     * @return
     */
    private void setPageCriteria(LambdaQueryWrapper<AppUserData> pageWrapper,
                        AppUserDataReq pageReq){

        if(!CheckParam.isNull(pageReq.getAuthAppUserId())){
            pageWrapper.like(AppUserData::getAuthAppUserId,pageReq.getAuthAppUserId());
        }

        if(!CheckParam.isNull(pageReq.getRealName())){
            pageWrapper.like(AppUserData::getRealName,pageReq.getRealName());
        }

        if(!CheckParam.isNull(pageReq.getIdentityCard())){
            pageWrapper.like(AppUserData::getIdentityCard,pageReq.getIdentityCard());
        }

        if(!CheckParam.isNull(pageReq.getGender())){
            pageWrapper.like(AppUserData::getGender,pageReq.getGender());
        }
    }

    /**
     * 更新APP用户其他信息
     * @author: Singer
     * @date 2022/09/20
     * @param updateReq 更新APP用户其他信息请求参数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateItem(AppUserDataUpdateReq updateReq){
        log.info(">>>>>>>>>>>>>>>>>更新APP用户其他信息请求参数 {} <<<<<<<<<<<<<<<<", JSON.toJSONString(updateReq));
        String mainId = updateReq.getAppUserDataId();
        AppUserData result = appUserDataRepository.selectOne(new LambdaQueryWrapper<AppUserData>()
                    .eq(AppUserData::getAppUserDataId,mainId));
        if (CheckParam.isNull(result)) {
            return;
        }
        setNeedUpdateItem(result,updateReq);
        appUserDataRepository.updateById(result);
    }

    /**
     * 设置需要更新的字段
     * @author: Singer
     * @date 2021/3/30
     * @param updateReq 更新参数
     * @param entity 实体
     */
    private void setNeedUpdateItem(AppUserData entity,
        AppUserDataUpdateReq updateReq){
        if(!CheckParam.isNull(updateReq.getAuthAppUserId())){
            entity.setAuthAppUserId(updateReq.getAuthAppUserId());
        }
        if(!CheckParam.isNull(updateReq.getRealName())){
            entity.setRealName(updateReq.getRealName());
        }
        if(!CheckParam.isNull(updateReq.getIdentityCard())){
            entity.setIdentityCard(updateReq.getIdentityCard());
        }
        if(!CheckParam.isNull(updateReq.getGender())){
            entity.setGender(updateReq.getGender());
        }
    }
}
