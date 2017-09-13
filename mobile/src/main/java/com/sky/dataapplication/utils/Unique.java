package com.sky.dataapplication.utils;

/**
 * Created by DESKTOP-SKY on 2017/8/9.
 */

public final class Unique {

    private static final String SERIAL_NUMBER;

    static {
         SERIAL_NUMBER = android.os.Build.SERIAL;
    }

    private Unique(){}

    public final String getSERIAL_NUMBER(){
        return SERIAL_NUMBER;
    }

}
