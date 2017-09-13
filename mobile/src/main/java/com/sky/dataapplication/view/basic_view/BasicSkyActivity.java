package com.sky.dataapplication.view.basic_view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Starr on 2017/8/8.
 */

public class BasicSkyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBarBg();
    }

    private void setBarBg(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().setBackgroundDrawableResource(R.mipmap.window_bg);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
