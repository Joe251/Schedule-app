package com.bignerdranch.android.activitylauncher.SignUpUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.activitylauncher.R;
import com.bignerdranch.android.activitylauncher.Class.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by JoeCheung on 2017/11/6.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText mSEmail;
    private EditText mSUserName;
    private EditText mSPassword;
    private EditText mSVertify;
    private EditText mSAge;
    private Button mSignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");
        mSEmail = (EditText) findViewById(R.id.signupemail);
        mSAge = (EditText) findViewById(R.id.signupage);
        mSVertify = (EditText) findViewById(R.id.signupvertify);
        mSUserName = (EditText) findViewById(R.id.signupusername);
        mSPassword = (EditText) findViewById(R.id.signuppassword);
        mSignUpButton = (Button) findViewById(R.id.signupbttn);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = mSUserName.getText().toString();
                String user_password = mSPassword.getText().toString();
                String user_vertify = mSVertify.getText().toString();
                String user_email = mSEmail.getText().toString();
                String user_age = mSAge.getText().toString();
                if(user_name.length() == 0){
                    Toast.makeText(SignUpActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else if(user_password.length() == 0){
                    Toast.makeText(SignUpActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else if(user_vertify.length() == 0){
                    Toast.makeText(SignUpActivity.this,"请再次输入密码确认",Toast.LENGTH_LONG).show();
                    return;
                }else if(user_email.length() == 0){
                    Toast.makeText(SignUpActivity.this,"邮箱不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else if(user_age.length() == 0){
                    Toast.makeText(SignUpActivity.this,"年龄不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else if(!isEmail(user_email)){
                    Toast.makeText(SignUpActivity.this,"无效的邮箱",Toast.LENGTH_LONG).show();
                    return;
                }else if(isConSpeCharacters(user_password) || isConSpeCharacters(user_name)){
                    Toast.makeText(SignUpActivity.this,"用户名或密码中不得含有特殊字符",Toast.LENGTH_LONG).show();
                    return;
                }
                User myUser=new User();
                myUser.setUsername(user_name);
                myUser.setPassword(user_password);
                myUser.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User s, BmobException e) {
                        if(e==null){
                            Toast.makeText(SignUpActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }else{
                            Toast.makeText(SignUpActivity.this,"用户名已存在！",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
    private boolean isConSpeCharacters(String string) {
        if(string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0){
            //如果不包含特殊字符
            return false;
        }
        return true;
    }
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
