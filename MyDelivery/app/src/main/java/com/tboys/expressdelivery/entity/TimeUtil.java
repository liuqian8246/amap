package com.tboys.expressdelivery.entity;


import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by Dell on 2016/3/4.
 */
public class TimeUtil {

    public static String DateFormat(Date date){

        String time = (String) DateFormat.format("yyyyMMddHHmmss", date.getTime());
        return time;
    }
}
