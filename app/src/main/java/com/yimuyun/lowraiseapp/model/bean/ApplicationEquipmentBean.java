package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:12
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class ApplicationEquipmentBean {


    /**
     * id : 1
     * equipmentId : 12
     * enterpriseId : 1
     * applicationId : 1
     * state : 13
     * personnelId : 1
     * isNewEnterprise : 1
     * documentNumber : 123124534dfadaad
     * documentTime : 1496648475000
     * createTime : 1496648475000
     * updateTime : 1496648475000
     */

    private int id;
    private int equipmentId;
    private int enterpriseId;
    private int applicationId;
    private int state;
    private int personnelId;
    private String isNewEnterprise;
    private String documentNumber;
    private long documentTime;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }

    public String getIsNewEnterprise() {
        return isNewEnterprise;
    }

    public void setIsNewEnterprise(String isNewEnterprise) {
        this.isNewEnterprise = isNewEnterprise;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public long getDocumentTime() {
        return documentTime;
    }

    public void setDocumentTime(long documentTime) {
        this.documentTime = documentTime;
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
}
