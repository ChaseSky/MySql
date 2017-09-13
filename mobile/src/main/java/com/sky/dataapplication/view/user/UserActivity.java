package com.sky.dataapplication.view.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sky.dataapplication.utils.db.dao.UserInfoDAO;
import com.sky.dataapplication.utils.db.dto.UserInfoDTO;
import com.sky.dataapplication.view.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DESKTOP-SKY on 2017/8/8.
 */

public class UserActivity extends AppCompatActivity {
    private ListView user_list;
    private List<UserInfoDTO> users;
    private String[] names;
    private String[] ids;
    private String[] lvs;
    private String[] exps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        initList();
    }

    private void initList(){
        user_list = (ListView) findViewById(R.id.user_list);
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        users = new UserInfoDAO().queryUsers();
        for(int i=0;i<users.size();i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("UserID", "用户ID："+users.get(i).getUser_id());
            map.put("UserName", "用户名："+users.get(i).getUser_name());
            map.put("UserLv", "权限等级："+users.get(i).getPer_lv()+"");
            map.put("UserExp", "备注："+users.get(i).getUser_exp());
            mylist.add(map);
        }
        //生成适配器，数组===ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this,
                mylist,//数据来源
                R.layout.user_list_item,//ListItem的XML实现
                //动态数组与ListItem对应的子项
                new String[] {"UserID", "UserName", "UserLv", "UserExp"},
                //ListItem的XML文件里面的两个TextView ID
                new int[] {R.id.user_id_item,R.id.user_name_item,R.id.user_lv_item,R.id.user_exp_item});
        user_list.setAdapter(mSchedule);


    }



}
