package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/5/2 16:53
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class UserBean {

    /**
     * uid : 1
     * phoneNumber : 18010181009
     * personnelId : 1
     * token : 2e53f0d9b84155c2daa1abd5735aad15
     */

    private long uid;
    private String phoneNumber;
    private int personnelId;
    private String token;

    private UserInfo userInfo;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
