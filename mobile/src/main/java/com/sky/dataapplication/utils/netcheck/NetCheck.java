package com.sky.dataapplication.utils.netcheck;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by DESKTOP-SKY on 2017/8/8.
 */

public class NetCheck{

    private static ConnectivityManager connectivityManager;//用于判断是否有网络 

    public static boolean getNetCheck(Context context){
        //获取当前网络的连接服务
        connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取活动的网络连接信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        //当前没有已激活的网络连接（表示用户关闭了数据流量服务，也没有开启WiFi等别的数据服务）  
        if (info == null) {
            Toast.makeText(context.getApplicationContext(), "请检查设备网络连接是否正常", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //当前有已激活的网络连接  
            return true;
        }

    }

}
