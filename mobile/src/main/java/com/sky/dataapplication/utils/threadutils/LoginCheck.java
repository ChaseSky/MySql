package com.sky.dataapplication.utils.threadutils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.sky.dataapplication.utils.db.dao.UserInfoDAO;
import com.sky.dataapplication.view.user.UserActivity;

/**
 * Created by DESKTOP-SKY on 2017/8/9.
 */

public class LoginCheck implements Runnable{

    private String user_name;
    private String user_id;
    private String user_pwd;

    public static final int LOGIN_STR = 1;

    private int ch;

    private Context context;
    private static Handler handler = new Handler();

    public void login(){
        if (new UserInfoDAO().login(user_name, user_pwd)) {
            Intent i = new Intent(context, UserActivity.class);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Toast.makeText(context, "登录成功!", Toast.LENGTH_SHORT).show();
            context.startActivity(i);
            //重置用户名 密码字符串记录
            user_name = "";
            user_pwd = "";
//                        return true;
        } else {
            Toast.makeText(context, "登录失败!", Toast.LENGTH_SHORT).show();
//                        return false;
        }
    }

    @Override
    public void run() {
        switch (ch){
            case LOGIN_STR:
                login();
                break;
            default:
                Log.i("login","找不到指令！");
                break;
        }
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
}
