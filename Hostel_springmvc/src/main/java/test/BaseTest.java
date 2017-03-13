package test;
/**
 * Created by disinuo on 17/3/9.
 */


import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.NumberFormatter;

/**
 * Created by disinuo on 17/3/5.
 */

public class BaseTest {
    public static void main(String[]args){
//        System.out.println(Long.MAX_VALUE);
//        Date d=new Date(1489121762376L);
//        System.out.println(NumberFormatter.saveOneDecimal(4.575748));
//        System.out.println(NumberFormatter.saveOneDecimal(4.334));
//        System.out.println(NumberFormatter.saveOneDecimal(4.854));
        System.out.println(DateHandler.strToLong("2017-02-22"));
//        System.out.println(DateHandler.strToLong("2017-03-11"));
//        System.out.println(DateHandler.strToLong("2017-03-10")-DateHandler.strToLong("2017-03-11"));
//        System.out.println(DateHandler.dayToMilliSecond(1));
//        System.out.println(DateHandler.milliSecondToDay(DateHandler.strToLong("2017-03-10")-DateHandler.strToLong("2017-03-11")));
//        System.out.println(DateHandler.strToLong("2017-03-10"));
//
//        System.out.println(DateHandler.longToStr(DateHandler.addDay(1489075200000L,5)));


//       int year = 2017;
//        int month = 2;
//        int day = 20;
//
//        String date = year + "/" + month + "/" + day;
//        Date utilDate = null;
//
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//            utilDate = formatter.parse(date);
//
//            System.out.println("utilDate:" + utilDate);
//        } catch (ParseException e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//        }
//        System.out.println(utilDate.getTime());
    }



    public int testFinal()throws Exception{
        int x=0;
        try {
            x++;
            return x;
        }catch (Exception e){
            System.out.println("In catch");
            throw e;
        }finally {
            System.out.println("In finally!");
        }
    }


}