package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import nju.edu.hostel.model.*;

import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    BaseDao baseDao;

    @Override
    public User get(int id) {
        return baseDao.getEntity(User.class,id);
    }

    @Override
    public List<User> getByUserName(String name) {
        String hql="SELECT user FROM User as user WHERE user.userName= '"+name+"'";
        return baseDao.getByHql(User.class,hql);
    }

    @Override
    public User load(int id){
        return baseDao.loadProxy(User.class,id);
    }
    @Override
    public ResultMessage update(User user){
        return baseDao.update(user);
    }

    @Override
    public int add(User user) throws Exception{
        return baseDao.save(user);
    }

}
