package nju.edu.hostel.util;

import org.hibernate.annotations.Immutable;

import java.util.*;

/**
 * Created by disinuo on 17/3/4.
 */
public class Constants {

    public static final String ROLE_VIP="vip";
    public static final String ROLE_HOSTEL="hostel";
    public static final String ROLE_MANAGER="manager";
    public static final int NUM_OF_MONTHS=12;


    public static final int REGIONTYPE_PROVINCE=0;
    public static final int REGIONTYPE_CITY=1;

    public static final int MANAGER_ID=666;
    /**
     * 加载大量数据时默认显示前DEFAULT_NUM_OF_DATA条
     */
    public static final int DEFAULT_NUM_OF_DATA=20;
    /**
     * 激活`DAY_OF_NORMAL_TO_PAUSE`天后，会员卡余额最小值，低于此，卡会被暂停
     * 暂停的时候（没有超过一年），一旦一次性充值大于此，卡会被复原
     */
    public static final double MONEY_LEAST=100;
    /**
     * 一次性充值此值以上会激活会员卡
     */
    public static final double MONEY_ACTIVATE=1000;
    /**
     * 预订扣费
     */
    public static final double MONEY_BOOK=20;

    /**
     * 积分与消费金额的转换比例
     * 即 1积分可兑换？钱
     */
    public static final double RATE_SCORE_TO_MONEY=0.01;
    /**
     * 消费金额与积分的转换比例
     * 即 1元钱可兑换?积分
     */
    public static final double RATE_MONEY_TO_SCORE=10;
    /**
     * 会员从激活开始，经过?天后 余额不足`MONEY_LEAST`会自动暂停
     */
    public static final int DAY_OF_NORMAL_TO_PAUSE=365;
    /**
     * 会员从暂停开始，经过?天后 余额不足`MONEY_LEAST`会自动暂停
     */
    public static final int DAY_OF_PAUSE_TO_STOP=365;

    public static final String[] VIP_LEVEL={
          "老铁","青铜","白银","黄金","铂金","钻石","王者","荣耀","至尊"
    };
    /**
     *会员等级与消费折扣的转换
     */
    public static final double VIP_LEVEL_TO_DISCOUNT(int level){
        if(level<=2){
            return 0.95;
        }else if(level<=4){
            return 0.85;
        }else if(level<=7){
            return 0.7;
        }else {
            return 0.5;
        }
    }

    /**
     *会员累计消费金额与等级的转换
     */
    public static final int VIP_MONEY_TO_LEVEL(double money){
        if(money<=100){
            return 0;
        }else if(money<=300){
            return 1;
        }else if(money<=500){
            return 2;
        }else if(money<=800){
            return 3;
        }else if(money<=1000){
            return 4;
        }else if(money<=1500){
            return 5;
        }else if(money<=1800){
            return 6;
        }else if(money<=5000){
            return 7;
        }else{
            return 8;
        }
    }
    //【vip的年龄段：<18,18~30,30~50,>50】

    public static final String[] AGE_RANGE={
            "<18岁",
            "18~30岁",
            "30~50岁",
            ">=50岁"
    };
    public static final String[] DAY_OF_WEEK={
      "周日","周一","周二","周三","周四","周五","周六"
    };
    public static final String[] ROOMPRICE_RANGE={
            "<100", "100~200","200~300","300~500","500~700","700~1000",">1000"
    };
    public static final String[] HOUR_OF_DAY_RANGE={
        "0:00~2:00","2:00~4:00","4:00~6:00","6:00~8:00","8:00~10:00","10:00~12:00","12:00~14:00","14:00~16:00","16:00~18:00","18:00~20:00","20:00~22:00","22:00~0:00"
    };
    public static String AGE_TO_RANGE(int age){
        if(age<18) return AGE_RANGE[0];
        else if(age<30) return AGE_RANGE[1];
        else if(age<50) return AGE_RANGE[2];
        else return AGE_RANGE[3];
    }
    public static String ROOM_PRICE_TO_RANGE(double price){
        if(price<100) return ROOMPRICE_RANGE[0];
        if(price<200) return ROOMPRICE_RANGE[1];
        if(price<300) return ROOMPRICE_RANGE[2];
        if(price<500) return ROOMPRICE_RANGE[3];
        if(price<700) return  ROOMPRICE_RANGE[4];
        if(price<1000) return  ROOMPRICE_RANGE[5];
        else  return  ROOMPRICE_RANGE[6];
    }
    public static String HOUR_TO_RANGE(int hour){
        int index=HOUR_TO_RANGE_INDEX(hour);
        return HOUR_OF_DAY_RANGE[index];
    }
    public static int HOUR_TO_RANGE_INDEX(int hour){
        int index=hour/2;
        int size=HOUR_OF_DAY_RANGE.length;
        if(index>=size) return size-1;
        return index;
    }
    public static double DO_DIVIDE(double x, double y){
        if(y==0) return 0;
        return x/y;
    }
//========= Create Constant Map ===================================
    public static Map CREATE_VIP_LEVEL_MAP(){
        Map<String,Integer> map=new LinkedHashMap<>();
        for(String level:VIP_LEVEL){
            map.put(level,0);
        }
        return map;
    }
    public static Map CREATE_DATE_MAP(int dateType){
        switch (dateType){
            case Calendar.MONTH:return CREATE_MONTH_MAP();
            case Calendar.WEDNESDAY:return CREATE_WEEK_MAP();
            case Calendar.YEAR:return CREATE_YEAR_MAP();
            case Calendar.HOUR:return CREATE_DAY_MAP();
            default:return CREATE_WEEK_MAP();
        }
    }
    public static Map<String,Double> CREATE_DAY_MAP(){
        Map<String,Double> map=new LinkedHashMap<>();
        for(int i=0;i<7;i++){
            for(int j=0;j<HOUR_OF_DAY_RANGE.length;j++){
                map.put(i+""+j,0.0);
            }
        }

        return map;

    }

    public static Map CREATE_YEAR_MAP(){
        Map map=new LinkedHashMap();
        int year=DateHandler.GET_CURRENT_YEAR();
        for(int i=2;i>=0;i--){
            map.put((year-i)+"年",0.0);
        }
        return map;
    }
    public static Map CREATE_MONTH_MAP(){
        Map map=new LinkedHashMap();
        for(int i=0;i<12;i++){
            map.put(DateHandler.dateFieldToShow(Calendar.MONTH,i),0.0);
        }
        return map;
    }
    public static Map CREATE_WEEK_MAP(){
        Map map=new LinkedHashMap();

        for(int i=1;i<=7;i++){
            map.put(DateHandler.dateFieldToShow(Calendar.WEDNESDAY,i),0.0);
        }
        return map;
    }
    public static Map<String,Double> CREATE_AGE_MAP(){
        Map<String,Double> map=new LinkedHashMap<>();
        for(String range:AGE_RANGE){
            map.put(range,0.0);
        }
        return map;
    }
    public static Map<String,Double> CREATE_ROOM_PRICE_MAP(){
        Map<String,Double> map=new LinkedHashMap<>();
        for(String range: ROOMPRICE_RANGE){
            map.put(range,0.0);

        }
        return map;
    }
//========= End Of Create Constant Map ===================================


}
