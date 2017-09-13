package com.sky.dataapplication.utils.db.dto;

/**
 * Created by DESKTOP-SKY on 2017/8/7.
 */

public class UserInfoDTO {


    private String user_id;
    private String user_name;
    private String user_pwd;
    private String user_exp;
    private String device_id;
    private int per_lv;

    public UserInfoDTO(String user_id, String user_name, String user_pwd) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
    }

    public UserInfoDTO() {
    }

    public UserInfoDTO(String user_id, String user_name, String user_pwd, String user_exp, String device_id, int per_lv) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_exp = user_exp;
        this.device_id = device_id;
        this.per_lv = per_lv;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_exp() {
        return user_exp;
    }

    public void setUser_exp(String user_exp) {
        this.user_exp = user_exp;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getPer_lv() {
        return per_lv;
    }

    public void setPer_lv(int per_lv) {
        this.per_lv = per_lv;
    }
}
