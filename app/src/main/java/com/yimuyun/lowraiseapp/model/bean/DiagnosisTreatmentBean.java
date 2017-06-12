package com.yimuyun.lowraiseapp.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/6/10 21:39
 * @email pengweiqiang64@163.com
 * @description 诊疗记录
 * @Version
 */

public class DiagnosisTreatmentBean implements Serializable{

    /**
     * id : 1
     * livestockId : 1
     * time : 1502121600000
     * documentNumber : 4b99166b625fac63
     * personnelId : 1
     * treatmentPlanId : 1
     * symptoms : 1
     * result : 1
     * createTime : 1496648005000
     * updateTime : 1496648005000
     * dragId : 1
     */

    private int id;
    private int livestockId;
    private long time;
    private String documentNumber;
    private long personnelId;
    private String treatmentPlanId;
    private String symptoms;
    private String result;
    private long createTime;
    private long updateTime;
    private long dragId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivestockId() {
        return livestockId;
    }

    public void setLivestockId(int livestockId) {
        this.livestockId = livestockId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public long getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(long personnelId) {
        this.personnelId = personnelId;
    }

    public String getTreatmentPlanId() {
        return treatmentPlanId;
    }

    public void setTreatmentPlanId(String treatmentPlanId) {
        this.treatmentPlanId = treatmentPlanId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public long getDragId() {
        return dragId;
    }

    public void setDragId(long dragId) {
        this.dragId = dragId;
    }
}
