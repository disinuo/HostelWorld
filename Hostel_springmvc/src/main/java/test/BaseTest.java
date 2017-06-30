package test;
/**
 * Created by disinuo on 17/3/9.
 */
import org.json.JSONObject;
public class BaseTest {
    public static void main(String[]args){

//        long date= 1496467396484L;//2017-6-3 13:23
//        int hour=DateHandler.getFieldFromLong(Calendar.HOUR_OF_DAY,date);
//        System.out.println(hour);
//        long today=new Date().getTime();
//        System.out.println(DateHandler.longToStr_withTime(today));

    }
    public static JSONObject str2JsonObj(String str){
        return new JSONObject(str);

    }



}