package test;
/**
 * Created by disinuo on 17/3/9.
 */



import nju.edu.hostel.util.DateHandler;

import java.util.*;

/**
 * Created by disinuo on 17/3/5.
 */

public class BaseTest {
    public static void main(String[]args){
        long date= 1496467396484L;//2017-6-3 13:23
        int hour=DateHandler.getFieldFromLong(Calendar.HOUR_OF_DAY,date);
        System.out.println(hour);
        long today=new Date().getTime();
        System.out.println(DateHandler.longToStr_withTime(today));
    }


}