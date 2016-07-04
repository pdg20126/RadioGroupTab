package com.smyhvae.radiogrouptabdemo;

/**
 * Created by Administrator on 2016/7/4.
 */
public class User {
    private int _id;
    private String Loginusername;
    private String Loginuserpwd;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLoginusername() {
        return Loginusername;
    }

    public void setLoginusername(String loginusername) {
        Loginusername = loginusername;
    }

    public String getLoginuserpwd() {
        return Loginuserpwd;
    }

    public void setLoginuserpwd(String loginuserpwd) {
        Loginuserpwd = loginuserpwd;
    }
}
