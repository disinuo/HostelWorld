package nju.edu.hostel.service;

import nju.edu.hostel.model.*;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;

/**
 * Created by disinuo on 17/3/2.
 */
public interface UserService {
    /**
     * 系统注册新用户，包括会员、旅馆分店
     * 若用户名已存在则不能注册
     * @param userName
     * @param password
     * @return DUPLICATE_NAME,FAILURE,SUCCESS
     */
    public ResultMessage register(Class<?> c,String userName, String password);

    /**
     * 注册新会员
     * @param userName
     * @param password
     * @return DUPLICATE_NAME,FAILURE,SUCCESS
     */
    public ResultMessage registerVIP(String userName, String password);

    public ResultMessage registerHostel(String userName,String password);

    /**
     * 系统删除用户，包括会员、旅馆分店
     * @param userId
     * @return
     */
    public ResultMessage delete(int userId);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return WRONG_ORIGINAL,NOT_CHANGE,FAILURE,SUCCESS
     */
    public ResultMessage modifyPassword(int id,String original,String password);

    /**
     * 将银行卡余额改至money
     * @param id
     * @param money
     * @return SUCCESS,FAILURE
     */
    public ResultMessage modifyBankMoneyTo(int id,double money);

    /**
     * offset可以为正/负。正就是加钱~负是扣钱
     * @param id
     * @param offset
     * @return SUCCESS,FAILURE
     */
    public ResultMessage modifyBankMoneyBy(int id,double offset);
    /**
     * 通过用户ID获取用户数据
     * @param userId
     * @return
     */
    public User getById(int userId);

    /**
     * 通过用户种类获取用户列表，种类有会员、旅馆分店、总经理
     * @param type
     * @return
     */
    public List<User> getByType(String type);

    /**
     * 用户通过用户名,密码登录，
     * 若用户存在返回该用户类型，否则返回一个特殊的用户类型（UNEXIST）
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName, String password);

    /**
     * 通过用户id,password验证用户情况
     * @param userName
     * @param password
     * @return NOT_EXIST,SUCCESS,WRONG_PASSWORD
     */
    public ResultMessage checkUser(String userName,String password);

    /**
     * 只检查用户是否存在
     * @param userName
     * @return NOT_EXIST,SUCCESS
     */
    public ResultMessage checkUser(String userName);
}
