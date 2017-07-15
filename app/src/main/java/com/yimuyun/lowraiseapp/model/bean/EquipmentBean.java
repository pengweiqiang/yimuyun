package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:12
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class EquipmentBean {

    /**
     * id : 1
     * equipmentNumber : E2003098911501010600DAE0
     * equipmentName : 耳标
     * type :
     * brand :
     * model :
     * state : 2
     * createTime : 123123123
     * updateTime : 123123123
     */

    private int id;
    private String equipmentNumber;
    private String equipmentName;
    private String type;
    private String brand;
    private String model;
    private String state;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
