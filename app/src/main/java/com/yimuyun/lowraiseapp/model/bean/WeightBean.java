package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/11 09:44
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WeightBean {

    /**
     * id : 1
     * livestockId : 1
     * weighTime : 1493913600000
     * weighPhase : 普通
     * cultureProcess : 养殖
     * weight : 100
     * weighId : 1
     * createTime : 1496644564000
     * updateTime : 1496644564000
     */

    private int id;
    private int livestockId;
    private long weighTime;
    private String weighPhase;
    private String cultureProcess;
    private String weight;
    private int weighId;
    private long createTime;
    private long updateTime;

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

    public long getWeighTime() {
        return weighTime;
    }

    public void setWeighTime(long weighTime) {
        this.weighTime = weighTime;
    }

    public String getWeighPhase() {
        return weighPhase;
    }

    public void setWeighPhase(String weighPhase) {
        this.weighPhase = weighPhase;
    }

    public String getCultureProcess() {
        return cultureProcess;
    }

    public void setCultureProcess(String cultureProcess) {
        this.cultureProcess = cultureProcess;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getWeighId() {
        return weighId;
    }

    public void setWeighId(int weighId) {
        this.weighId = weighId;
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
