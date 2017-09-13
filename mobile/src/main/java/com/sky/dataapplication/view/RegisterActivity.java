package com.sky.dataapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sky.dataapplication.utils.db.dao.UserInfoDAO;
import com.sky.dataapplication.utils.db.dto.UserInfoDTO;

/**
 * Created by DESKTOP-SKY on 2017/8/7.
 */

public class RegisterActivity extends AppCompatActivity{
    //可退出程序状态
    private boolean isExit;

    private EditText etUserId;
    private EditText etUserName;
    private EditText etUserPwd;
    private EditText etUserExp;

    private String userId;
    private String userName;
    private String userPwd;
    private String userExp;
    private String userDeviceId;
    private int perLv;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        initControl();
    }


    private void initControl(){
        etUserId =(EditText) findViewById(R.id.et_id);
        etUserName =(EditText) findViewById(R.id.et_name);
        etUserPwd =(EditText) findViewById(R.id.et_pwd);
        etUserExp = (EditText) findViewById(R.id.et_exp);

    }


    public void regUserBtn(View view){
        userId = etUserId.getText().toString();
        userName = etUserName.getText().toString();
        userPwd = etUserPwd.getText().toString();
        userExp = etUserExp.getText().toString();
        userDeviceId = android.os.Build.SERIAL.length()==0?"无数据！":android.os.Build.SERIAL;
        perLv = 1;

        UserInfoDAO udao = new UserInfoDAO();
        int i = udao.insertUserInfo(new UserInfoDTO(userId,userName,userPwd,userExp,userDeviceId,perLv));
        if(i == 0){
            Toast.makeText(getApplication(),"注册成功！",Toast.LENGTH_SHORT);
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }

    }

    public void backClick(View view){

        this.finish();

    }

    @Override
    public void onBackPressed() {

            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                LoginActivity.lact.finish();
                this.finish();
                System.exit(0);
            }


//        返回方法↓
//        super.onBackPressed();
    }



}
