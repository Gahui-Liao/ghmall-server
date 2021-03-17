package com.gahui.ghmall.server.entity;

import java.util.Date;

public class GhSequence {
    private Integer seqId;

    private Integer seqUsed;

    private Integer seqBegin;

    private Integer seqStep;

    private String tableName;

    private String columnName;

    private Byte effect;

    private Date createdTime;

    private Date modifiedTime;

    private String remark;

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public Integer getSeqUsed() {
        return seqUsed;
    }

    public void setSeqUsed(Integer seqUsed) {
        this.seqUsed = seqUsed;
    }

    public Integer getSeqBegin() {
        return seqBegin;
    }

    public void setSeqBegin(Integer seqBegin) {
        this.seqBegin = seqBegin;
    }

    public Integer getSeqStep() {
        return seqStep;
    }

    public void setSeqStep(Integer seqStep) {
        this.seqStep = seqStep;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Byte getEffect() {
        return effect;
    }

    public void setEffect(Byte effect) {
        this.effect = effect;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}