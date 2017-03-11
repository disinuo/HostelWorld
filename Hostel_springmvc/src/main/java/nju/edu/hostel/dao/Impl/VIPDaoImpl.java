package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.dao.VIPDao;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.User;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class VIPDaoImpl implements VIPDao {
    @Autowired
    BaseDao baseDao;
    @Override
    public Vip get(int id) {
        return baseDao.getEntity(Vip.class,id);
    }

    @Override
    public Vip load(int id) {
        return baseDao.loadProxy(Vip.class,id);

    }
    @Override
    public List<Vip> getAll(){
        return baseDao.getAll(Vip.class);
    }

    @Override
    public List<Vip> getByRestrictEqual(String column, Object value) {
        return baseDao.getByRestrictEqual(Vip.class,column,value);
    }

    @Override
    public List<Vip> getByRestrictEqual(Map<String, Object> map) {
        return baseDao.getByRestrictEqual(Vip.class,map);
    }

    @Override
    public int add(Vip vip) throws Exception{
        return baseDao.save(vip);
    }

    @Override
    public ResultMessage update(Vip vip) {
        return baseDao.update(vip);
    }

}
