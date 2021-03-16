package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 账户Dto
 * @author: Gahui
 * @since: 2021/3/16
 **/
@Getter
@Setter
public class GhAccountDto {

    private Integer accountId;

    private String accountName;

    private String accountPassword;

    private GhUserDto user;
}
