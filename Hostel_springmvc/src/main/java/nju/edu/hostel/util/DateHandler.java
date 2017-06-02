package nju.edu.hostel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static nju.edu.hostel.util.Constants.*;


/**
 * Created by disinuo on 17/3/10.
 */
public class DateHandler {
    public static long fieldToLong(int dateType,int value){
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(0);
        c.set(dateType,value);
        return c.getTimeInMillis();
    }

    /**
     * DAY_OF_WEEK  周日开始，=1；周一 =2
     */
    public static int getFieldFromLong(int dateType,long date){
        Calendar helper=Calendar.getInstance();
        helper.setTimeInMillis(date);
        if(dateType==Calendar.WEDNESDAY) return helper.get(Calendar.DAY_OF_WEEK);
        return helper.get(dateType);
    }
    public static String dateFieldToShow(int dateType,int value){
        switch (dateType){
            case Calendar.YEAR:return yearToShow(value);
            case Calendar.WEDNESDAY:return dayOfWeekToShow(value);
            case Calendar.MONTH:return monthToShow(value);
            default:return value+"";
        }
    }
    public static String monthToShow(int value){
        return (value+1)+"月";
    }
    public static String dayOfWeekToShow(int value){
        return DAY_OF_WEEK[value-1];
        
    }
    public static String yearToShow(int year){
        return year+"年";
    }
    public static Date longToDate(long time){
        return new Date(time);
    }

    public static String longToStr_noTime(long time){
        Date date=new Date(time);
        return formatter_noTime.format(date);
    }
    public static String longToStr_withTime(long time){
        Date date=new Date(time);
        return formatter_withTime.format(date);
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
            Date date=formatter_noTime.parse(time);
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
    private static SimpleDateFormat formatter_noTime=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatter_withTime=new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public static void main(String[] args){
        long test1=strToLong("2017-4-5");
        System.out.println(longToStr_noTime(add(test1,Calendar.WEDNESDAY,1)));
        System.out.println(longToStr_noTime(add(test1,Calendar.WEDNESDAY,4)));

        Map<String,Integer> map=new HashMap<String,Integer>();
        int[] years={2016,2016,2017,2016,2016,2011,2011,2016,};
        for(int year:years){
            String yearStr=DateHandler.yearToShow(year);
            if(map.containsKey(yearStr)){
                int num=map.get(yearStr);
                map.put(yearStr,++num);
            }else {
                map.put(yearStr,1);
            }
        }
        System.out.println(map.size());
        for(String key:map.keySet()){
            System.out.println(key+" : "+map.get(key));
        }

    }
}
