package com.yimuyun.lowraiseapp.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/6/10 16:33
 * @email pengweiqiang64@163.com
 * @description 饲料
 * @Version
 */

public class FeedBean implements Serializable{

    /**
     * id : 1
     * manufacturer : 1
     * picture : 1
     * name : 饲料A
     * createTime : 1496642799000
     * updateTime : 1496642799000
     * enterpriseId : 1
     */

    private long id;
    private String manufacturer;
    private String picture;
    private String name;
    private long createTime;
    private long updateTime;
    private int enterpriseId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
