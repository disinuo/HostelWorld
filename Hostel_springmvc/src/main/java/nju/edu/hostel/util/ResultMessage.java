package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/3/2.
 */
public enum ResultMessage {
    SUCCESS,

    FAILURE,
    /**
     * 晚于可操作的时间
     */
    LATE_TIME,
    /**
     * 注册时重名
     */
    DUPLICATE_NAME,
    /**
     * 原始密码错误
     */
    WRONG_ORIGINAL,
    /**
     * 没有权限
     */
    NO_AUTHORITY,
    /**
     *新密码未做改变
     */
    NOT_CHANGE,
    /**
     * 余额不足
     */
    NOT_ENOUGH_MONEY,
    /**
     * 积分不足
     */
    NOT_ENOUGH_SCORE,
    /**
     * 不存在
     */
    NOT_EXIST,
    /**
     * 密码错误
     */
    WRONG_PASSWORD,
    /**
     * 已经停卡
     */
    VIP_STATE_STOP,
    /**
     * 卡已被暂停
     */
    VIP_STATE_PAUSED,
    /**
     * 卡未激活
     */
    VIP_STATE_UNACTIVATED,

}
