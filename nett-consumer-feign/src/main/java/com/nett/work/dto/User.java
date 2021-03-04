package com.nett.work.dto;

import java.io.Serializable;
 
/**
 * @author lzh
 * create 2019-09-18-22:32
 */
public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private int uid;
    private String userName;
    private String passWord;
    private int salary;
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public User(int uid, String userName, String passWord, int salary) {
        super();
        this.uid = uid;
        this.userName = userName;
        this.passWord = passWord;
        this.salary = salary;
    }
    public User() {
        super();
    }

}