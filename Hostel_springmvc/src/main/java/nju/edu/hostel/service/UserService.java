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
     * @return
     */
    public ResultMessage register(Class<?> c,String userName, String password);

    /**
     * 注册新会员
     * @param userName
     * @param password
     * @return
     */
    public ResultMessage register(String userName, String password);

    /**
     * 系统删除用户，包括会员、旅馆分店
     * @param userId
     * @return
     */
    public ResultMessage delete(int userId);

    /**
     * 系统更新用户，包括会员、旅馆分店
     * @param user
     * @return
     */
    public ResultMessage update(User user);

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
}
