package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:11
 * @email pengweiqiang64@163.com
 * @description 牲畜
 * @Version
 */

public class LivestockBean {

    /**
     * id : 2
     * equipmentId : 2
     * number : 45da8cebb0fe5066
     * enterpriseId : 1
     * livestockMasterId : 1
     * type : 1
     * state : 00
     * initialWeight : 111
     * initialTime : 1506009600000
     * lairageWeight : 112
     * lairageTime : 1506096000000
     * birthplace : 北京
     * varietiesId : 1
     * sex : 1
     * isPregnancy : 2
     * picture : 1
     * createTime : 1496631829000
     * updateTime : 1496631829000
     */

    private int id;
    private int equipmentId;
    private String number;
    private int enterpriseId;
    private int livestockMasterId;
    private String type;
    private String state;
    private String initialWeight;
    private long initialTime;
    private String lairageWeight;
    private long lairageTime;
    private String birthplace;
    private int varietiesId;
    private String sex;
    private String isPregnancy;
    private String picture;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getLivestockMasterId() {
        return livestockMasterId;
    }

    public void setLivestockMasterId(int livestockMasterId) {
        this.livestockMasterId = livestockMasterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(String initialWeight) {
        this.initialWeight = initialWeight;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    public String getLairageWeight() {
        return lairageWeight;
    }

    public void setLairageWeight(String lairageWeight) {
        this.lairageWeight = lairageWeight;
    }

    public long getLairageTime() {
        return lairageTime;
    }

    public void setLairageTime(long lairageTime) {
        this.lairageTime = lairageTime;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public int getVarietiesId() {
        return varietiesId;
    }

    public void setVarietiesId(int varietiesId) {
        this.varietiesId = varietiesId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIsPregnancy() {
        return isPregnancy;
    }

    public void setIsPregnancy(String isPregnancy) {
        this.isPregnancy = isPregnancy;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
