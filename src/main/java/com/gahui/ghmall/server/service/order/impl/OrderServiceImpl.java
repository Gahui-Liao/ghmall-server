package com.gahui.ghmall.server.service.order.impl;

import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.constant.CacheEnum;
import com.gahui.ghmall.server.constant.ExceptionEnum;
import com.gahui.ghmall.server.constant.OrderItemStatusEnum;
import com.gahui.ghmall.server.constant.OrderStatusEnum;
import com.gahui.ghmall.server.dao.CartItemDao;
import com.gahui.ghmall.server.dao.OrderDao;
import com.gahui.ghmall.server.dao.OrderItemDao;
import com.gahui.ghmall.server.dto.GoodsDto;
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
import java.util.*;

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
    CartItemDao cartItemDao;

    @Override
    public PageInfo listOrderByAccountId(Integer accountId, Integer pageNum, Integer pageSize) {
        if (accountId == null) {
            return null;
        }
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
    public String accept(AcceptOrderVo acceptOrderVo) {
        if (acceptOrderVo == null) {
            return null;
        }
        Integer orderId = sequenceCacheService.getSeqIdByEnum(CacheEnum.ORDER);
        String orderCode = this.getOrderCode(orderId);
        Long orderAmount = this.getOrderAmount(acceptOrderVo);
        this.insertOrder(acceptOrderVo.getAccountId(), orderId, orderCode, orderAmount);
        this.insertOrderItem(orderId, orderCode, acceptOrderVo);
        return orderCode;
    }

    /**
     * 事务控制
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String acceptCart(AcceptOrderVo acceptOrderVo) {
        if (acceptOrderVo == null) {
            return null;
        }
        Set<Integer> goodsIdSet = new HashSet<>(16);
        for (OrderItemVo orderItemVo : acceptOrderVo.getOrderItemList()) {
            Integer goodsId = orderItemVo.getGoodsId();
            goodsIdSet.add(goodsId);
        }
        Integer accountId = acceptOrderVo.getAccountId();
        cartItemDao.batchDeleteCartItem(accountId, new ArrayList<>(goodsIdSet));
        return this.accept(acceptOrderVo);
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
     * 获取订单交易额
     *
     * @param acceptOrderVo 前端入参
     * @return long
     */
    private Long getOrderAmount(AcceptOrderVo acceptOrderVo) {
        List<OrderItemVo> orderItemVoList = acceptOrderVo.getOrderItemList();
        if (orderItemVoList == null || orderItemVoList.size() <= 0) {
            return null;
        }
        Map<Integer, Integer> idNumMap = new HashMap<>(16);
        for (OrderItemVo orderItemVo : orderItemVoList) {
            Integer goodsId = orderItemVo.getGoodsId();
            Integer goodsNum = orderItemVo.getGoodsNum();
            if (idNumMap.get(goodsId) != null) {
                idNumMap.put(goodsId, idNumMap.get(goodsId) + goodsNum);
            } else {
                idNumMap.put(goodsId, goodsNum);
            }
        }
        List<GoodsDto> goodsDtoList = goodsService.listGoodsByIdList(new ArrayList<>(idNumMap.keySet()));
        long amount = 0L;
        for (GoodsDto goodsDto : goodsDtoList) {
            if (idNumMap.get(goodsDto.getGoodsId()) > goodsDto.getGoodsStock()) {
                throw new GhmallException(ExceptionEnum.BIZ.getCode(), "商品库存不足！");
            }
            if (!idNumMap.keySet().contains(goodsDto.getGoodsId())) {
                idNumMap.remove(goodsDto.getGoodsId());
            }
            // 减库存，价格累加
            if (this.updateGoodsStock(goodsDto.getGoodsId(), goodsDto.getGoodsStock(), idNumMap.get(goodsDto.getGoodsId())) != 1) {
                throw new GhmallException(ExceptionEnum.BIZ.getCode(), "商品库存不足！");
            }
            if (goodsDto.getGoodsPrice() == null) {
                throw new GhmallException(ExceptionEnum.BIZ.getCode(), "商品价格为空！");
            }
            amount += goodsDto.getGoodsPrice() * idNumMap.get(goodsDto.getGoodsId());
        }
        // 如果前端入参非法，更改入参
        this.getNewOrderItemVo(idNumMap, acceptOrderVo);
        return amount;
    }

    /**
     * 如果前端入参需要做修改，则更新参数
     *
     * @param idNumMap      goodsId&goodsNum的map
     * @param acceptOrderVo 前端入参
     */
    private void getNewOrderItemVo(Map<Integer, Integer> idNumMap, AcceptOrderVo acceptOrderVo) {
        if (idNumMap == null || acceptOrderVo == null || acceptOrderVo.getOrderItemList() == null) {
            return;
        }
        if (idNumMap.size() == acceptOrderVo.getOrderItemList().size()) {
            return;
        }
        List<OrderItemVo> orderItemVoList = new ArrayList<>();
        for (Integer goodsId : idNumMap.keySet()) {
            OrderItemVo itemVo = new OrderItemVo();
            itemVo.setGoodsId(goodsId);
            itemVo.setGoodsNum(idNumMap.get(goodsId));
            orderItemVoList.add(itemVo);
        }
        acceptOrderVo.setOrderItemList(orderItemVoList);
    }

    private int updateGoodsStock(Integer goodsId, Integer goodsStock, Integer reduceNum) {
        return goodsService.updateGoodsStockByIdAndNum(goodsId, goodsStock, reduceNum);
    }
}
