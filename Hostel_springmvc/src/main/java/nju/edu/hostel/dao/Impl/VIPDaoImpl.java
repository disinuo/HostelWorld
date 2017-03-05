package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.VIPDao;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class VIPDaoImpl implements VIPDao {
    @Autowired
    BaseDao baseDao;
    @Override
    public Vip getById(int id) {
        System.out.println("in VIPDaoImpl---getById ");
        Vip vip=baseDao.getById(Vip.class,id);
        return vip;
    }
}
