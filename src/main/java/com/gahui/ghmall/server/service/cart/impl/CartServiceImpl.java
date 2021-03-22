package com.gahui.ghmall.server.service.cart.impl;

import com.gahui.ghmall.server.cache.SequenceCacheService;
import com.gahui.ghmall.server.constant.CacheEnum;
import com.gahui.ghmall.server.constant.CartItemTypeEnum;
import com.gahui.ghmall.server.dao.CartDao;
import com.gahui.ghmall.server.dao.CartItemDao;
import com.gahui.ghmall.server.dto.CartItemDto;
import com.gahui.ghmall.server.entity.GhCart;
import com.gahui.ghmall.server.entity.GhCartItem;
import com.gahui.ghmall.server.service.cart.CartService;
import com.gahui.ghmall.server.vo.AcceptCartVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 购物车相关业务接口实现类
 * @author: Gahui
 * @since: 2021/3/22
 **/
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Resource
    CartDao cartDao;

    @Resource
    CartItemDao cartItemDao;

    @Resource
    SequenceCacheService sequenceCacheService;

    @Override
    public PageInfo listCartItemByAccountId(Integer accountId, Integer pageNum, Integer pageSize) {
        if (accountId == null) {
            return null;
        }
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(cartItemDao.listCartItemByAccountId(accountId));
    }

    @Override
    public int accept(AcceptCartVo acceptCartVo) {
        if (acceptCartVo == null) {
            return 0;
        }
        if (acceptCartVo.getType().equals(CartItemTypeEnum.NEW.getValue())) {
            return this.detailNew(acceptCartVo.getAccountId(), acceptCartVo.getGoodsId(), acceptCartVo.getGoodsNum());
        }
        if (acceptCartVo.getType().equals(CartItemTypeEnum.INC.getValue())) {
            return this.cartInc(acceptCartVo.getCartItemId(), acceptCartVo.getGoodsNum());
        }
        if (acceptCartVo.getType().equals(CartItemTypeEnum.DEC.getValue())) {
            return this.cartDec(acceptCartVo.getCartItemId(), acceptCartVo.getGoodsNum());
        }
        if (acceptCartVo.getType().equals(CartItemTypeEnum.DEL.getValue())) {
            return this.cartDel(acceptCartVo.getCartItemId());
        }
        return 0;
    }

    /**
     * 详情页新增购物项
     *
     * @param accountId 账户标识
     * @param goodsId   商品标识
     * @param goodsNum  商品数量
     * @return 1：成功，其他：失败
     */
    private int detailNew(Integer accountId, Integer goodsId, Integer goodsNum) {
        if (accountId == null || goodsId == null || goodsNum == null) {
            return 0;
        }
        Integer cartId = sequenceCacheService.getSeqIdByEnum(CacheEnum.CART);
        if (cartId == null) {
            cartId = sequenceCacheService.getSeqIdByEnum(CacheEnum.CART);
            GhCart ghCart = new GhCart();
            ghCart.setCartId(cartId);
            ghCart.setAccountId(accountId);
            cartDao.insertSelective(ghCart);
        }
        CartItemDto cartItemDto = cartItemDao.getCartItemByCartIdAndGoodsId(cartId, goodsId);
        if (cartItemDto == null) {
            Integer cartItemId = sequenceCacheService.getSeqIdByEnum(CacheEnum.CART_ITEM);
            GhCartItem ghCartItem = new GhCartItem();
            ghCartItem.setCartItemId(cartItemId);
            ghCartItem.setCartId(cartId);
            ghCartItem.setGoodsId(goodsId);
            ghCartItem.setGoodsNum(goodsNum);
            return cartItemDao.insertSelective(ghCartItem);
        }
        GhCartItem ghCartItem = new GhCartItem();
        ghCartItem.setCartItemId(cartItemDto.getCartItemId());
        ghCartItem.setGoodsNum(cartItemDto.getGoodsNum() + goodsNum);
        return cartItemDao.updateByPrimaryKeySelective(ghCartItem);
    }

    /**
     * 增加购物项数量
     *
     * @param cartItemId 购物项标识
     * @param goodsNum   商品数量
     * @return 1：成功，其他：失败
     */
    private int cartInc(Integer cartItemId, Integer goodsNum) {
        if (cartItemId == null || goodsNum == null) {
            return 0;
        }
        return cartItemDao.increaseCartItem(cartItemId, goodsNum);
    }

    /**
     * 减少购物项数量
     *
     * @param cartItemId 购物项标识
     * @param goodsNum   商品数量
     * @return 1：成功，其他：失败
     */
    private int cartDec(Integer cartItemId, Integer goodsNum) {
        if (cartItemId == null || goodsNum == null) {
            return 0;
        }
        return cartItemDao.decreaseCartItem(cartItemId, goodsNum);
    }

    /**
     * 删除购物项
     *
     * @param cartItemId 购物项标识
     * @return 1：成功，其他：失败
     */
    private int cartDel(Integer cartItemId) {
        return cartItemDao.deleteByPrimaryKey(cartItemId);
    }
}
