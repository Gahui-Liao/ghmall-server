package com.gahui.ghmall.server.controller;

import com.gahui.ghmall.server.service.order.OrderService;
import com.gahui.ghmall.server.vo.AcceptOrderVo;
import com.gahui.ghmall.server.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: 订单相关controller
 * @author: Gahui
 * @since: 2021/3/19
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping("/{accountId}/{pageNum}/{pageSize}")
    public ResponseVo listOrderByAccountId(@PathVariable("accountId") Integer accountId,
                                           @PathVariable("pageNum") Integer pageNum,
                                           @PathVariable("pageSize") Integer pageSize) {
        return ResponseVo.success(orderService.listOrderByAccountId(accountId, pageNum, pageSize));
    }

    @GetMapping("/{orderId}")
    public ResponseVo getOrderDetailById(@PathVariable("orderId") Integer orderId) {
        return ResponseVo.success(orderService.getOrderDetailById(orderId));
    }

    @PostMapping("/accept")
    public ResponseVo accept(@RequestBody AcceptOrderVo acceptOrderVo) {
        if (orderService.accept(acceptOrderVo) == 1) {
            return ResponseVo.success();
        }
        return ResponseVo.fail();
    }
}
