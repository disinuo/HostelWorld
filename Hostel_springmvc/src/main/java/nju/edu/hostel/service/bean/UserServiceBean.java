package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.dao.VIPDao;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class UserServiceBean implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    VIPDao vipDao;
    @Autowired
    HostelDao hostelDao;
    @Override
    public ResultMessage register(Class<?> c,String userName, String password) {
        //TODO
        List users=userDao.getByRestrictEqual("userName",userName);
        if(users.size()!=0) return ResultMessage.DUPLICATE_NAME;
        int userId=0;
        if(c==Vip.class){
            Vip vip=new Vip();
            vip.setRealName(userName);
            try {
                userId=vipDao.add(vip);
            }catch (Exception e){
                return ResultMessage.FAILURE;
            }
        }
        if(c==Hostel.class){
            Hostel hostel=new Hostel();
            hostel.setName(userName);
            try {
                userId=hostelDao.add(hostel);
            }catch (Exception e){
                return ResultMessage.FAILURE;
            }
        }
        User user=new User();
        user.setType(c.getSimpleName().toLowerCase());
        user.setUserName(userName);
        user.setId(userId);
        user.setPassword(password);

        try {
            userDao.add(user);

        }catch (Exception e){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }
    @Override
    public ResultMessage register(String userName, String password){
        return register(Vip.class,userName,password);
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
        return userDao.get(userId);
    }

    @Override
    public List<User> getByType(String type) {
        return null;
    }

    @Override
    public User login(String userName, String password) {
        System.out.println("UserServiceBean-login "+userName+"  "+password);
        Map map=new HashMap<String,Object>();
        map.put("userName",userName);
        map.put("password",password);
        List<User> ans=userDao.getByRestrictEqual(map);
        if(ans==null||ans.size()==0) {
            return null;
        }
        else {
            return ans.get(0);
        }
    }
}
