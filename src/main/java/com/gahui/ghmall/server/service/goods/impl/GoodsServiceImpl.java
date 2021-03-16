package com.gahui.ghmall.server.service.goods.impl;

import com.gahui.ghmall.server.dao.GhGoodsDao;
import com.gahui.ghmall.server.dto.GhGoodsDto;
import com.gahui.ghmall.server.service.goods.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 商品业务逻辑具体实现类
 * @author: Gahui
 * @since: 2021/3/15
 **/
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GhGoodsDao ghGoodsDao;

    @Override
    public GhGoodsDto getGoodsById(Integer goodsId) {
        return null;
    }

    @Override
    public PageInfo listRecommendGoods(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<GhGoodsDto> goodsList = ghGoodsDao.listRecommendGoods();
        return new PageInfo<>(goodsList);
    }
}
