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
        List<User> users=userDao.getByUserName(userName);
        if(users.size()!=0) return ResultMessage.DUPLICATE_NAME;
        int userId=0;
        if(c==Vip.class){
            Vip vip=new Vip();
            vip.setRealName(userName);
            try {
                userId=vipDao.add(vip);
            }catch (Exception e){
                e.printStackTrace();
                return ResultMessage.FAILURE;
            }
        }
        else if(c==Hostel.class){
            Hostel hostel=new Hostel();
            hostel.setName(userName);
            System.out.print("service---在注册客栈！"+hostel.getName());
            try {
                userId=hostelDao.add(hostel);
            }catch (Exception e){
                e.printStackTrace();
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
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }
    @Override
    public ResultMessage registerVIP(String userName, String password){
        return register(Vip.class,userName,password);
    }
    @Override
    public ResultMessage registerHostel(String userName,String password){
        return register(Hostel.class,userName,password);
    }
    @Override
    public ResultMessage delete(int userId) {
        //TODO delete
        return null;
    }

    @Override
    public ResultMessage modifyPassword(int id, String original,String password) {
        User user=userDao.get(id);
        if(!user.getPassword().equals(original)){
            return ResultMessage.WRONG_ORIGINAL;
        }
        if(original.equals(password)){
            return ResultMessage.NOT_CHANGE;
        }
        user.setPassword(password);
        return userDao.update(user);
    }

    @Override
    public ResultMessage modifyBankMoneyTo(int id, double money) {
        User user=userDao.get(id);
        user.setBankMoney(money);
        return userDao.update(user);
    }

    @Override
    public ResultMessage modifyBankMoneyBy(int id, double offset) {
        User user=userDao.get(id);
        user.setBankMoney(user.getBankMoney()+offset);
        return userDao.update(user);
    }

    @Override
    public User getById(int userId) {
        return userDao.get(userId);
    }

    @Override
    public User login(String userName, String password) {
        try{
            int userId=Integer.parseInt(userName);
            return userDao.get(userId);
        }catch (Exception e){
            return userDao.getByUserName(userName).get(0);
        }
    }
    @Override
    public ResultMessage checkUser(String userName,String password){

        List<User> users=userDao.getByUserName(userName);
        if(users==null||users.size()==0){
            try {
                int userId=Integer.parseInt(userName);
                User user=userDao.get(userId);
                if(user!=null){
                    if(user.getPassword().equals(password)){
                        return ResultMessage.SUCCESS;
                    }else{
                        return ResultMessage.WRONG_PASSWORD;
                    }
                }else {
                    return ResultMessage.NOT_EXIST;
                }
            }catch (Exception e){
                return ResultMessage.NOT_EXIST;
            }
        }
        User user=users.get(0);
        if(user.getPassword().equals(password)){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.WRONG_PASSWORD;
        }
    }
    @Override
    public ResultMessage checkUser(String userName){
        List<User> users=userDao.getByUserName(userName);
        if(users==null||users.size()==0){
            return ResultMessage.NOT_EXIST;
        }else return ResultMessage.SUCCESS;
    }
}
