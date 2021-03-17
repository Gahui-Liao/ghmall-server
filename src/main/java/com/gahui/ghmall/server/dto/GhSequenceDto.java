package com.gahui.ghmall.server.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 序列Dto
 * @author: Gahui
 * @since: 2021/3/17
 **/
@Getter
@Setter
public class GhSequenceDto {

    private Integer seqId;

    private Integer seqUsed;

    private Integer seqBegin;

    private Integer seqStep;

    private String tableName;

    private String columnName;
}
