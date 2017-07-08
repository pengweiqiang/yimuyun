package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:10
 * @email pengweiqiang64@163.com
 * @description 品种
 * @Version
 */

public class Varieties {

    /**
     * id : 1
     * number : 0001
     * name : 大水牛
     * growthWeight : 123
     * createTime : 123123123123123
     * updateTime : 123123123123123
     */

    private int id;
    private String number;
    private String name;
    private String growthWeight;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrowthWeight() {
        return growthWeight;
    }

    public void setGrowthWeight(String growthWeight) {
        this.growthWeight = growthWeight;
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
