package com.sky.dataapplication.view.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sky.dataapplication.view.R;
import com.sky.dataapplication.view.user.UserActivity;

/**
 * Created by DESKTOP-SKY on 2017/8/10.
 */

public class AdminActivity extends AppCompatActivity {

    private Button userListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        userListBtn = (Button) findViewById(R.id.userListBtn);
        userListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, UserActivity.class);
                startActivity(i);
            }
        });
    }


}
