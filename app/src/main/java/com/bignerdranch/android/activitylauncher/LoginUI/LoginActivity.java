package com.bignerdranch.android.activitylauncher.LoginUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.activitylauncher.R;
import com.bignerdranch.android.activitylauncher.SignUpUI.SignUpActivity;
import com.bignerdranch.android.activitylauncher.Class.User;
import com.bignerdranch.android.activitylauncher.UserUI.UserActivity;

public class LoginActivity extends AppCompatActivity{
    private EditText mLUserName;
    private EditText mLPassWord;
    private Button mLoginButton;
    private TextView mSignUpText;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bmob.initialize(this,"13a239d3c56cb13559e2a9eedabf9490");
        setContentView(R.layout.activity_login);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        mLUserName = (EditText) findViewById(R.id.loginusername);
        mLPassWord = (EditText)findViewById(R.id.loginpassword);
        mLPassWord.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction()==KeyEvent.ACTION_DOWN)&&(keyCode == KeyEvent.KEYCODE_ENTER)){
                    mLoginButton.performClick();
                    return true;
                }
                return false;
            }
        });
        mLoginButton = (Button)findViewById(R.id.loginbttn);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.show();
                String userName = mLUserName.getText().toString();
                String passWord = mLPassWord.getText().toString();
                if(userName.isEmpty()||passWord.isEmpty()){
                    mProgress.dismiss();
                    Toast toast = Toast.makeText(LoginActivity.this,"密码或账号不能为空",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP , 0, 50);
                    toast.show();
                    return;
                }
                User tempUser = new User();
                tempUser.setUsername(userName);
                tempUser.setPassword(passWord);
                tempUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        mProgress.dismiss();
                        if(e==null){
                            Toast toast = Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP , 0, 50);
                            toast.show();
                            finish();
                            Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                            startActivity(intent);

                        }else{
                            Toast toast = Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP , 0, 50);
                            toast.show();
                        }
                    }
                });
            }
        });
        mSignUpText = (TextView) findViewById(R.id.signup);
        mSignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            }
        });

    }
}
