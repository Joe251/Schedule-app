package com.bignerdranch.android.activitylauncher.Class;
import cn.bmob.v3.BmobUser;

/**
 * Created by JoeCheung on 2017/11/6.
 */

public class User extends BmobUser {
    private String memail;
    private Integer mage;

    public String getEmail() {
        return memail;
    }

    public void setEmail(String email) {
        memail = email;
    }

    public Integer getAge() {
        return mage;
    }

    public void setAge(Integer age) {
        mage = age;
    }
}
