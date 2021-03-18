package com.gahui.ghmall.server.controller;

import com.gahui.ghmall.server.annotation.GhAuth;
import com.gahui.ghmall.server.service.goods.GoodsService;
import com.gahui.ghmall.server.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 商品相关controller
 * @author: Gahui
 * @since: 2021/3/17
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;

    @GhAuth
    @GetMapping("/detail/{goodsId}")
    public ResponseVo getGoodsById(@PathVariable("goodsId") Integer goodsId) {
        return ResponseVo.success(goodsService.getGoodsById(goodsId));
    }

    @GetMapping("/recommend/detail/{pageNum}/{pageSize}")
    public ResponseVo listRecommendGoods(@PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("pageSize") Integer pageSize) {
        return ResponseVo.success(goodsService.listRecommendGoods(pageNum, pageSize));

    }


}
