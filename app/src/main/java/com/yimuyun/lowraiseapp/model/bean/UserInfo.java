package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:05
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class UserInfo {
    private String phoneNumber;
    private Enterprise enterprise;
    private Personnel personnel;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
