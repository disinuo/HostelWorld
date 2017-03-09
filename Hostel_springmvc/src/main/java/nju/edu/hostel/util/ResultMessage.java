package nju.edu.hostel.util;

/**
 * Created by disinuo on 17/3/2.
 */
public enum ResultMessage {
    SUCCESS,

    FAILURE,
    /**
     * 注册时重名
     */
    DUPLICATE_NAME,
    /**
     * 原始密码错误
     */
    WRONG_ORIGINAL,
    /**
     *新密码未做改变
     */
    NOT_CHANGE,
    /**
     * 不存在
     */
    NOT_EXIST,
    /**
     * 密码错误
     */
    WRONG_PASSWORD

}
