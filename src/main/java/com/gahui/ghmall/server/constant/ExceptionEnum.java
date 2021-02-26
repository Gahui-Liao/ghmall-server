package com.gahui.ghmall.server.constant;

/**
 * @description: 异常枚举类
 * @author: Gahui
 * @since: 2021/2/5
 **/
public enum ExceptionEnum {
    /**
     * 网络异常
     */
    NET("exc_net", "网络异常"),
    /**
     * 缓存异常
     */
    CACHE("exc_cache","缓存异常"),
    /**
     * 数据库异常
     */
    DB("exc_db", "数据库异常"),
    /**
     * 业务逻辑异常
     */
    BIZ("exc_biz", "业务逻辑异常"),
    /**
     * 其他异常，如：空指针
     */
    OTH("exc_oth", "未知异常");

    /**
     * 异常编码
     */
    private String code;

    /**
     * 异常描述
     */
    private String desc;

    ExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据异常编码获取异常描述
     * @param code 异常编码
     * @return 异常描述
     */
    public static String getDescByCode(String code){
        for(ExceptionEnum ee : ExceptionEnum.values()){
            if(ee.getCode().equals(code)){
                return ee.desc;
            }
        }
        return ExceptionEnum.OTH.getDesc();
    }
}
