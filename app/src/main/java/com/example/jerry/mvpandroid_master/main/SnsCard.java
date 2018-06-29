package com.example.jerry.mvpandroid_master.main;

/**
 * Created by jerry on 2018/6/28.
 */
public class SnsCard {
    private String userName;

    public SnsCard(String userName) {
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
