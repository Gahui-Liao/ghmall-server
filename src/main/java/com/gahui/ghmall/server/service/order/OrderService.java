package com.gahui.ghmall.server.service.order;

import com.gahui.ghmall.server.dto.OrderDetailDto;
import com.gahui.ghmall.server.vo.AcceptOrderVo;
import com.github.pagehelper.PageInfo;

/**
 * @description: 订单相关业务接口
 * @author: Gahui
 * @since: 2021/3/18
 **/
public interface OrderService {

    /**
     * 分页查询订单
     *
     * @param accountId 账户标识
     * @param pageNum   页号
     * @param pageSize  页大小
     * @return page
     */
    PageInfo listOrderByAccountId(Integer accountId, Integer pageNum, Integer pageSize);

    /**
     * 根据订单标识获取订单详情
     *
     * @param orderId 订单标识
     * @return dto
     */
    OrderDetailDto getOrderDetailById(Integer orderId);

    /**
     * 生成订单
     *
     * @param acceptOrderVo 前端传入订单参数
     * @return 1：成功，其他：失败
     */
    int accept(AcceptOrderVo acceptOrderVo);
}
