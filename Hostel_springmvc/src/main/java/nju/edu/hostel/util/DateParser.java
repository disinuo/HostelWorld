package nju.edu.hostel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by disinuo on 17/3/10.
 */
public class DateParser {
    public static Date longToDate(long time){
        return new Date(time);
    }

    public static String longToStr(long time){
        Date date=new Date(time);
        return formatter.format(date);
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
    private static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");

}
