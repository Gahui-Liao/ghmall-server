package com.gahui.ghmall.server.service.order.impl;

import com.gahui.ghmall.server.dao.OrderDao;
import com.gahui.ghmall.server.service.order.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 订单相关业务实现类
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderDao orderDao;

    @Override
    public PageInfo listOrderByAccountId(Integer accountId, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(orderDao.listOrderByAccountId(accountId));
    }
}
