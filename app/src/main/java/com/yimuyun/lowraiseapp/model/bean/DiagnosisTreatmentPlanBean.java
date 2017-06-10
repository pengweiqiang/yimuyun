package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:40
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DiagnosisTreatmentPlanBean {

    /**
     * id : 1
     * treatmentPlan : 生病治疗
     * createTime : 1496647607000
     * updateTime : 1496647607000
     * enterpriseId : 1
     */

    private int id;
    private String treatmentPlan;
    private long createTime;
    private long updateTime;
    private int enterpriseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
