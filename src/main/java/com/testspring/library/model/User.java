package com.testspring.library.model;

public class User {

    private int user_id;
    private String user_log;
    private String user_pwd;

    public int getUser_id() {
        return user_id;
    }

    public String getUser_log() {
        return user_log;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_log(String user_log) {
        this.user_log = user_log;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_pwd() {

        return user_pwd;
    }

}
