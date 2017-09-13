package com.sky.dataapplication.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.sky.dataapplication.utils.encrypt.EncryptString.hexStr2Str;

/**
 * Created by DESKTOP-SKY on 2017/8/1.
 */
public class DBManager {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://116.196.107.247:3306/db_android";
    private static final String USER_NAME = hexStr2Str("616E64726F6964");
    private static final String USER_PWD = hexStr2Str("61646D696E313233");

    private static Connection conn;

    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        try{
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection(URL,USER_NAME,USER_PWD);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn,Statement stmt){
        close(conn,stmt,null);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
