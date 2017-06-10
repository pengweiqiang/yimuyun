package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:36
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DrugBean {

    /**
     * id : 1
     * drugName : 免疫A
     * drugType : 1
     * createTime : 1496646986000
     * updateTime : 1496646986000
     * enterpriseId : 1
     */

    private int id;
    private String drugName;
    private String drugType;
    private long createTime;
    private long updateTime;
    private int enterpriseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
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
