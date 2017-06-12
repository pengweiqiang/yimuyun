package com.yimuyun.lowraiseapp.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/6/10 21:04
 * @email pengweiqiang64@163.com
 * @description 用户个人信息
 * @Version
 */

public class Personnel implements Serializable{

    /**
     * id : 1
     * enterpriseId : 1
     * name : 小明
     * sex : 0
     * dateOfBirth : null
     * phoneNumber : 18010001010
     * position : 1,2
     * imamPhoto : null
     * maritalStatus : null
     * documentType : null
     * addressId : null
     * type : null
     * createTime : null
     * updateTime : null
     */

    private int id;
    private int enterpriseId;
    private String name;
    private String sex;
    private String dateOfBirth;
    private String phoneNumber;
    private String position;
    private String imamPhoto;
    private String maritalStatus;
    private String documentType;
    private String addressId;
    private String type;
    private String createTime;
    private String updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImamPhoto() {
        return imamPhoto;
    }

    public void setImamPhoto(String imamPhoto) {
        this.imamPhoto = imamPhoto;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
