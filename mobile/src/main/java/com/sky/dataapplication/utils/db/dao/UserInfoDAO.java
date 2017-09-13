package com.sky.dataapplication.utils.db.dao;

import com.sky.dataapplication.utils.db.DBManager;
import com.sky.dataapplication.utils.db.dto.UserInfoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DESKTOP-SKY on 2017/8/7.
 */

public class UserInfoDAO {

    private final String QUERY_USERS = "select * from tb_users order by user_id asc";
    private final String QUERY_NAME_SQL="select user_name from tb_users where user_name=?";
    private final String QUERY_PWD_SQL="select user_pwd from tb_users where user_pwd=?";
    private final String QUERY_DEVICE_ID = "select user_device_id from tb_users where device=?";
    private final String QUERY_PER_LV = "select user_name,per_lv from tb_users where user_name=?";
    private final String INSERT_SQL = "insert into tb_users(user_id,user_name,user_pwd,user_exp,device_id,per_lv)" +
                                                "values (?,?,?,?,?,?)";

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    /**
    * 登录一重判断
    * */
    public boolean login(String name, String pwd) {
        boolean b = false;
        if (isQueryName(name)) {
            userLogin(name,pwd);
            b = true;
        }

        return b;
    }

    /**
     * 返回 0 则插入数据成功
     * 返回 1 则id已存在
     * 返回 2 则name已存在
     * 返回 3 则device_id已存在
     * 返回 4 则其它问题 注册失败
     *
     * @param user
     * @return
     */
    public int insertUserInfo(UserInfoDTO user) {
        int i = queryUserInfo(user, user.getUser_id(), user.getUser_name(), user.getDevice_id());
        if (i == 1 || i == 2 || i== 3) {
            return i;
        }
        try {
//            if (conn == null) {
            conn = DBManager.getConn();
//            }
            pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt.setString(1, user.getUser_id());
            pstmt.setString(2, user.getUser_name());
            pstmt.setString(3, user.getUser_pwd());
            pstmt.setString(4, user.getUser_exp());
            pstmt.setString(5, user.getDevice_id());
            pstmt.setInt(6, user.getPer_lv());
            int a = pstmt.executeUpdate();
            i = a > 0 ? 0 : 4;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    /**
    * 查询id true为存在
    * */
    private boolean queryId(int id) {
        return queryUserInfo(null,null);
    }
    /**
    * 查询用户名 true为存在
    * */
    private boolean isQueryName(String user_name) {
        return queryUserInfo(user_name,QUERY_NAME_SQL);
    }
    /**
    * 查询密码 true为存在
    * */
    private boolean isQueryPwd(String user_pwd) {
        return queryUserInfo(user_pwd,QUERY_PWD_SQL);
    }
    /**
    * 查询device_id  true为存在
    * */
    private boolean isDeviceId(String device_id){
        return queryUserInfo(device_id,QUERY_DEVICE_ID);
    }
    //值存在为true
    private boolean queryUserInfo(String name_pwd_devid, String sql) {
        boolean b = false;
        try {
            conn = DBManager.getConn();
            //TODO 查询语句
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name_pwd_devid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    /**
     * 返回 0 则信息不存在
     * 返回 1 则id已存在
     * 返回 2 则name已存在
     * 返回 3 则device_id已存在
     * @param user
     * @param id
     * @param name
     * @return
     */
    private int queryUserInfo(UserInfoDTO user, String id, String name,String device_id) {
        int i = 0;
        try {
            if (conn == null) {
                conn = DBManager.getConn();
            }
            String sql = "select user_id,user_name from tb_users where user_id=? or user_name=? or user_device_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUser_id());
            pstmt.setString(2, user.getUser_name());
            pstmt.setString(3, user.getDevice_id());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                    if (rs.getString("user_id").equals(id)) {
                        return 1;
                    } else if (rs.getString("user_name").equals(name)) {
                        return 2;
                    } else if(rs.getString("user_device_id").equals(device_id)){
                        return 3;
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    /**
     * -1则未查询到数据
     * 根据name查询对应记录中per_lv值
     * */
    public int queryUserPermissionLv(String user_name){
        int i = -1;
        try{
            conn = DBManager.getConn();
            pstmt = conn.prepareStatement(QUERY_PER_LV);
            pstmt.setString(1,user_name);
            rs = pstmt.executeQuery();
            if (rs.next()){
                i = rs.getInt("per_lv");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }
    public List<UserInfoDTO> queryUsers(){
        List<UserInfoDTO> userList = new ArrayList<UserInfoDTO>();
        try{
            conn = DBManager.getConn();
            pstmt = conn.prepareStatement(QUERY_USERS);
            rs = pstmt.executeQuery();
            while (rs.next()){
                userList.add(new UserInfoDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }
    /*
    * 判断用户名密码是否为同一条数据记录中数据
    * */
    private boolean userLogin(String user_name,String user_pwd) {
        boolean b = false;
        try {
            if (conn == null) {
                conn = DBManager.getConn();
            }
            String sql = "select user_name,user_pwd from tb_users where user_name=? or user_pwd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_name);
            pstmt.setString(2, user_pwd);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                b = true;
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    private boolean createTable(String userName){
        boolean b = false;
            try{


            }catch (Exception e){
                e.printStackTrace();
            }
        return b;
    }


}
