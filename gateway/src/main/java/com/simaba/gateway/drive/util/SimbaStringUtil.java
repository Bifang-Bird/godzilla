package com.simaba.gateway.drive.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <辛巴字符串工具类> <br>
 *
 * @author SIMBA_00126
 * @Date Created in 13:04 2019/8/12
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SimbaStringUtil {

    /**
     * 判断对象是否为空
     * @param s 对象
     * @return true：为空 false ：不为空
     */
    public static boolean isEmptyObject(Object s) {
        return (s == null) || ("".equals(s));
    }

    /**
     * 判断多个对象是否为空
     * @param s
     * @return true：为空 false ：不为空
     */
    public static boolean isEmpty(String... s){
        boolean flag = true;
        if(s!=null){
            for(Object str : s){
                if(isEmptyObject(str)){
                    flag = true;
                    break;
                }
                flag = false;
            }
        }
        return flag;
    }

    public static long getTimeLong(String ct) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long ct_long = 0;
        if (ct != null && ct.length() > 0) {
            ct_long = sdf.parse(ct).getTime() / 1000;
        }
        return ct_long;
    }

    public static String stampToDate(long s){
        String res;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s*1000);
        res = sdf.format(date);
        return res;
    }

    public static long getSystemTime() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Long ct= SimbaStringUtil.getTimeLong(df.format(new Date()));
        return ct;
    }


    public static void getSystemDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));
    }

    public static final String parseTime(long time, String pattern) {
        long temptime = time;
        String temppatt = pattern;
        if (temptime < 0) {
            temptime = 0;
        }
        if (temppatt == null) {
            temppatt = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        return new SimpleDateFormat(temppatt).format(new Date(temptime));
    }
}
