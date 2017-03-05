package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class UserServiceBean implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public ResultMessage add(String userName, String password) {
        //TODO
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(int userId) {
        return null;
    }

    @Override
    public ResultMessage update(User user) {
        return null;
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public List<User> getByType(String type) {
        return null;
    }

    @Override
    public User login(String userName, String password) {
        System.out.print("UserServiceBean-login "+userName+"  "+password);
        String[] keys={"userName","password"};
        Object[] values={userName,password};
        List<User> ans=userDao.findByColunms(keys,values);
        if(ans==null) {
            return null;
        }
        else {
            return ans.get(0);
        }
    }
}
