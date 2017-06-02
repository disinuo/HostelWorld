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
        long date= new Date().getTime();
        int hour=DateHandler.getFieldFromLong(Calendar.HOUR,date);
        System.out.println(hour);
    }


}