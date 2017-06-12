package com.yimuyun.lowraiseapp.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/6/10 21:40
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DiagnosisResultBean implements Serializable{


    /**
     * id : 1
     * enterpriseId : 1
     * diagnosisResult : 生病
     * createTime : 1496647607699
     * updateTime : 1496647607699
     */

    private long id;
    private int enterpriseId;
    private String diagnosisResult;
    private String createTime;
    private String updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}