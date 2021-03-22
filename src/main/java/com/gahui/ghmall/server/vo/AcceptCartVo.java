package com.gahui.ghmall.server.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 加购物车Vo
 * @author: Gahui
 * @since: 2021/3/22
 **/
@Getter
@Setter
public class AcceptCartVo {

    private Integer accountId;

    private Integer cartItemId;

    private Integer goodsId;

    private Integer goodsNum;

    /**
     * 操作类型
     */
    private Byte type;
}
