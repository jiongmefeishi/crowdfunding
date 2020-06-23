package com.zqt.crowd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zqtao
 * @description: 日期、时间格式校正工具
 * @Date: 2020/5/28 10:18
 * @version: 1.0
 */
public class DateUtil {

    /**
     * @return 返回当前系统时间
     */
    public static String getDate() {
        // 获取当前系统时间
        Date date = new Date();
        // 创建时间格式化对象
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
