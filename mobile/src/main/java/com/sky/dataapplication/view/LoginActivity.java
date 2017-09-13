package com.sky.dataapplication.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sky.dataapplication.utils.db.dao.UserInfoDAO;
import com.sky.dataapplication.utils.netcheck.NetCheck;
import com.sky.dataapplication.view.admin.AdminActivity;
import com.sky.dataapplication.view.user.UserActivity;

/**
 * Created by DESKTOP-SKY on 2017/8/8.
 */

public class LoginActivity extends AppCompatActivity implements Runnable {

    private final int ADMIN_ACTIVITY = 10;

    private EditText userNameText;
    private EditText userPwdText;
    private Button sendBtn;
    private TextView zc;
    private ImageView imageView1;
    private LinearLayout loginLayout;

    public static LoginActivity lact;

    private Intent intent;

    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        String SerialNumber = android.os.Build.SERIAL;
        System.out.println(SerialNumber);
        lact = this;
        initControl();
    }


    private void initControl() {
        userNameText = (EditText) findViewById(R.id.userNameText);
        userPwdText = (EditText) findViewById(R.id.userPwdText);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        zc = (TextView) findViewById(R.id.registerUser);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
//        sendBtn.setOnClickListener(this);
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//
//        });

        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    public void loginClick(View view) {
//        Intent l = new Intent(LoginActivity.this, LoadActivity.class);
//        startActivity(l);
        UserInfoDAO udao = new UserInfoDAO();
        if (userNameText.getText().toString().isEmpty() && userPwdText.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "请输入用户名或密码！", Toast.LENGTH_SHORT).show();
//            LoadActivity.load.finish();
        } else {
            //判断设备网络连接状态 有网则true
            if (!NetCheck.getNetCheck(getApplicationContext())) {
                return;
            }
//            Thread t = new Thread(LoginActivity.this);
//            t.start();
            String user_name = userNameText.getText().toString();
            if (udao.login(user_name, userPwdText.getText().toString())) {
//                loginLayout.setOnClickListener(null);
                Intent i = null;
                int lv = udao.queryUserPermissionLv(user_name);
                if (lv == ADMIN_ACTIVITY) {
                    i = new Intent(LoginActivity.this, AdminActivity.class);
//                    LoadActivity.load.finish();
                    LoginActivity.this.startActivity(i);
                }
                    Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
//                loginLayout.setOnClickListener();
            } else {
                Toast.makeText(LoginActivity.this, "登录失败!", Toast.LENGTH_SHORT).show();
//                LoadActivity.load.finish();
            }
//            new Thread() {
//                @Override
//                public void run() {
//                    if (UserInfoDAO.login(userNameText.getText().toString(), userPwdText.getText().toString())) {
//                        Intent i = new Intent(LoginActivity.lact, UserActivity.class);
//                        Toast.makeText(LoginActivity.lact, "登录成功!", Toast.LENGTH_SHORT).show();
//                        LoginActivity.lact.startActivity(i);
//                    } else {
//                        Toast.makeText(LoginActivity.lact, "登录失败!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }.start();

//            LoginCheck l = new LoginCheck();
//            l.setUser_name(userNameText.getText().toString());
//            l.setUser_pwd(userPwdText.getText().toString());
//            l.setCh(LoginCheck.LOGIN_STR);
//            l.setContext(getApplicationContext());
////            String name = ;
////            String pwd = ;
//            new Thread(l).start();
//            Animation animation = new RotateAnimation(0, 359);
//            animation.setDuration(500);
//            animation.setRepeatCount(8);//动画的重复次数
//            animation.setFillAfter(true);//设置为true，动画转化结束后被应用
//            imageView1.startAnimation(animation);//开始动画 

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//
//                        }
//                    });
//                }
//            }).start();
        }
    }

    private void login() {
        String user_name = userNameText.getText().toString();
        if (new UserInfoDAO().login(user_name, userPwdText.getText().toString())) {
            Toast.makeText(LoginActivity.lact, "登录成功!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(LoginActivity.lact, UserActivity.class);
            LoginActivity.lact.startActivity(i);
        } else {
            Toast.makeText(LoginActivity.lact, "登录失败!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void run() {
        login();
    }
}
