package com.gahui.ghmall.server.service.order.impl;

import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.constant.CacheEnum;
import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.constant.OrderItemStatusEnum;
import com.gahui.ghmall.server.constant.OrderStatusEnum;
import com.gahui.ghmall.server.dao.GoodsDao;
import com.gahui.ghmall.server.dao.OrderDao;
import com.gahui.ghmall.server.dao.OrderItemDao;
import com.gahui.ghmall.server.dto.OrderDetailDto;
import com.gahui.ghmall.server.dto.OrderDto;
import com.gahui.ghmall.server.entity.GhOrder;
import com.gahui.ghmall.server.entity.GhOrderItem;
import com.gahui.ghmall.server.exception.GhmallException;
import com.gahui.ghmall.server.service.goods.GoodsService;
import com.gahui.ghmall.server.service.order.OrderService;
import com.gahui.ghmall.server.vo.AcceptOrderVo;
import com.gahui.ghmall.server.vo.OrderItemVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 订单相关业务实现类
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private String PREFIX = "GH";

    @Resource
    OrderDao orderDao;

    @Resource
    OrderItemDao orderItemDao;

    @Resource
    SequenceCacheService sequenceCacheService;

    @Resource
    GoodsService goodsService;

    @Resource
    GoodsDao goodsDao;

    @Override
    public PageInfo listOrderByAccountId(Integer accountId, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(orderDao.listOrderByAccountId(accountId));
    }

    @Override
    public OrderDetailDto getOrderDetailById(Integer orderId) {
        if (orderId == null) {
            return null;
        }
        OrderDto orderDto = orderDao.getOrderById(orderId);
        OrderDetailDto detailDto = new OrderDetailDto();
        BeanUtils.copyProperties(orderDto, detailDto);
        detailDto.setOrderItemList(orderItemDao.listOrderItemByOrderId(orderId));
        return detailDto;

    }

    /**
     * 事务控制
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int accept(AcceptOrderVo acceptOrderVo) {
        if (acceptOrderVo == null) {
            return 0;
        }
        Integer orderId = sequenceCacheService.getSeqIdByEnum(CacheEnum.ORDER);
        String orderCode = this.getOrderCode(orderId);
        Long orderAmount = this.getOrderAmount(acceptOrderVo);
        return this.insertOrder(acceptOrderVo.getAccountId(), orderId, orderCode, orderAmount) &
                this.insertOrderItem(orderId, orderCode, acceptOrderVo);
    }

    /**
     * 获取订单编码，16位
     *
     * @param orderId 订单标识
     * @return str
     */
    private String getOrderCode(Integer orderId) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return PREFIX + format.format(date) + String.format("%06d", orderId);
    }

    /**
     * 获取价格，价格不应该用前端传过来的
     *
     * @param acceptOrderVo 前端入参
     * @return long
     */
    private long getOrderAmount(AcceptOrderVo acceptOrderVo) {
        long ans = 0;
        for (OrderItemVo orderItemVo : acceptOrderVo.getOrderItemList()) {
            this.validateGoods(orderItemVo.getGoodsId());
            ans += goodsService.getGoodsBasicById(orderItemVo.getGoodsId()).getGoodsPrice() * orderItemVo.getGoodsNum();
        }
        return ans;
    }

    /**
     * 数据保存到订单表
     *
     * @param accountId   账户标识
     * @param orderId     订单标识
     * @param orderCode   订单编码
     * @param orderAmount 订单交易额
     * @return 1：成功，其他：失败
     */
    private int insertOrder(Integer accountId, Integer orderId, String orderCode, Long orderAmount) {
        GhOrder order = new GhOrder();
        order.setAccountId(accountId);
        order.setOrderId(orderId);
        order.setOrderCode(orderCode);
        order.setOrderAmount(orderAmount);
        order.setOrderStatus(OrderStatusEnum.NEW.getValue());
        return orderDao.insertSelective(order);
    }

    /**
     * 数据保存到订单项表
     *
     * @param orderId       订单标识
     * @param orderCode     订单编码
     * @param acceptOrderVo 前端入参
     * @return 1：成功，其他：失败
     */
    private int insertOrderItem(Integer orderId, String orderCode, AcceptOrderVo acceptOrderVo) {
        if (acceptOrderVo == null) {
            return 0;
        }
        List<GhOrderItem> orderItemList = new ArrayList<>();
        int len = acceptOrderVo.getOrderItemList().size();
        for (int i = 0; i < len; i++) {
            GhOrderItem item = new GhOrderItem();
            item.setOrderItemId(sequenceCacheService.getSeqIdByEnum(CacheEnum.ORDER_ITEM));
            item.setOrderItemCode(orderCode + "_" + (i + 1));
            item.setOrderItemStatus(OrderItemStatusEnum.NEW.getValue());
            item.setOrderId(orderId);
            item.setGoodsId(acceptOrderVo.getOrderItemList().get(i).getGoodsId());
            item.setGoodsNum(acceptOrderVo.getOrderItemList().get(i).getGoodsNum());
            orderItemList.add(item);
        }
        return orderItemDao.batchInsertOrderItem(orderItemList);
    }

    /**
     * 校验商品是否存在
     *
     * @param goodsId 商品标识
     * @return int
     */
    private Integer validateGoods(Integer goodsId) {
        if (goodsId == null || goodsDao.validateGoodsById(goodsId) <= 0) {
            throw new GhmallException(ExceptionEnum.BIZ.getCode(), "商品不存在！");
        }
        return goodsId;
    }
}
