package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/3/4.
 */
public class Constants {

    public static final String ROLE_VIP="vip";
    public static final String ROLE_HOSTEL="hostel";
    public static final String ROLE_MANAGER="manager";
    /**
     * 激活一年后，会员卡余额最小值，低于此，卡会被暂停
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
    public static double RATE_SCORE_TO_MONEY=0.01;
    /**
     * 消费金额与积分的转换比例
     * 即 1元钱可兑换?积分
     */
    public static double RATE_MONEY_TO_SCORE=10;
}
