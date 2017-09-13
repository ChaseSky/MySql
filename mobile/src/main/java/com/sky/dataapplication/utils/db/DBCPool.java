package com.sky.dataapplication.utils.db;

import android.widget.Toast;

import com.sky.dataapplication.view.LoginActivity;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;

import static com.sky.dataapplication.utils.encrypt.EncryptString.hexStr2Str;

/**
 * Created by DESKTOP-SKY on 2017/8/7.
 */

public class DBCPool {

    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://183.57.38.89:3306/szy";
    private static final String USER_NAME="737A79";
    private static final String USER_PWD="737A79";

    private static BasicDataSource dbs;

    static {
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn=null;
        Toast.makeText(LoginActivity.lact,"请求连接",Toast.LENGTH_SHORT).show();
        try {
            Class.forName(DRIVER);
            dbs= new BasicDataSource();
            dbs.setDriverClassName(DRIVER);
            dbs.setUrl(URL);
            dbs.setUsername(hexStr2Str(USER_NAME));
            dbs.setPassword(hexStr2Str(USER_PWD));
            conn = dbs.getConnection();
            Toast.makeText(LoginActivity.lact,"链接成功",Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

//    public static void main(String[] args) {
//        Connection connection = DBPool.getConn();
//    }
}
