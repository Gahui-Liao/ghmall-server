package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description: 用户Dto
 * @author: Gahui
 * @since: 2021/3/16
 **/
@Getter
@Setter
public class UserDto {

    private Integer userId;

    private Integer accountId;

    private String userNickname;

    private String userAvatar;

    private String userEmail;

    private String userPhone;

    private Date userBirth;

    private Byte userGender;
}
