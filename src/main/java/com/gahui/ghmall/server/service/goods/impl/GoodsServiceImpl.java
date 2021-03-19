package com.gahui.ghmall.server.service.goods.impl;

import com.gahui.ghmall.server.dao.GoodsDao;
import com.gahui.ghmall.server.dto.GoodsDetailDto;
import com.gahui.ghmall.server.dto.GoodsDto;
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
    GoodsDao goodsDao;

    @Override
    public GoodsDetailDto getGoodsDetailById(Integer goodsId) {
        return goodsDao.getGoodsDetailById(goodsId);
    }

    @Override
    public PageInfo listRecommendGoods(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(goodsDao.listRecommendGoods());
    }

    @Override
    public GoodsDto getGoodsBasicById(Integer goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }

    @Override
    public List<GoodsDto> listGoodsByIdList(List<Integer> goodsIdList) {
        return goodsDao.listGoodsByIdList(goodsIdList);
    }

    @Override
    public int updateGoodsStockByIdAndNum(Integer goodsId, Integer goodsStock, Integer reduceStock) {
        return goodsDao.updateGoodsStockByIdAndNum(goodsId, goodsStock, reduceStock);
    }


}
