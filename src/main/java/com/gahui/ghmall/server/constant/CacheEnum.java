package com.gahui.ghmall.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 缓存类枚举
 * @author: Gahui
 * @since: 2021/3/18
 **/
@Getter
@AllArgsConstructor
public enum CacheEnum {
    /**
     * 缓存枚举
     */
    ACCOUNT("accountSeq#", "gh_account", "account_id", "accoutId#"),
    ORDER("orderSeq#", "gh_order", "order_id", "orderId"),
    ORDER_ITEM("orderItemSeq#", "gh_order_item", "order_item_id", "orderItemId#");

    private String seqPrefix;

    private String tableName;

    private String columnName;

    private String idPrefix;
}
