package nju.edu.hostel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by disinuo on 17/3/10.
 */
public class DateHandler {
    public static Date longToDate(long time){
        return new Date(time);
    }

    public static String longToStr(long time){
        Date date=new Date(time);
        return formatter.format(date);
    }

    /**
     * 将毫秒转化成天数
     * @param milli
     * @return
     */
    public static double milliSecondToDay(long milli){
        double ans=milli/(1000*60*60*24);
        return ans;
    }

    public static long dayToMilliSecond(double day){
        long ans=(long)(day*24*60*60*1000);
        return ans;
    }
    public static long strToLong(String time){
        try {
            Date date=formatter.parse(time);
            return date.getTime();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return 0;
    }
    public static long add(long originalDate,int type,int num){
        Calendar helper=Calendar.getInstance();
        helper.setTimeInMillis(originalDate);
        helper.add(type,num);
        return helper.getTimeInMillis();
    }
    private static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args){
        long today=new Date().getTime();
        System.out.println(longToStr(DateHandler.add(today,Calendar.YEAR,-1)));
        System.out.println(longToStr(DateHandler.add(today,Calendar.MONTH,-1)));
        System.out.println(longToStr(DateHandler.add(today,Calendar.WEDNESDAY,-1)));
    }
}
