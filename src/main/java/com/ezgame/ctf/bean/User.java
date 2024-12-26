package com.ezgame.ctf.bean;


import java.io.Serializable;

public class User implements Serializable {
    private String UserName;

    private String PassWord;

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getPassWord() {
        return this.PassWord;
    }

    public void setPassWord(String passWord) {
        this.PassWord = passWord;
    }

    public String toString() {
        return "User{UserName='" + this.UserName + '\'' + ", PassWord='" + this.PassWord + '\'' + '}';
    }
}
