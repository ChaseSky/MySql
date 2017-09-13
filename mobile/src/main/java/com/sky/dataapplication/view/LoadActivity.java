package com.sky.dataapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DESKTOP-SKY on 2017/8/31.
 */

public class LoadActivity extends AppCompatActivity{
    public static final LoadActivity load = new LoadActivity();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_loading_activity);
    }
}
