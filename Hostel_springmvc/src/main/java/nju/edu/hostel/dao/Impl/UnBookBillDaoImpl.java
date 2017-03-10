package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.UnBookBillDao;
import nju.edu.hostel.model.UnBookBill;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/10.
 */
public class UnBookBillDaoImpl implements UnBookBillDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public UnBookBill get(int id) {
        return baseDao.getEntity(UnBookBill.class,id);
    }

    @Override
    public UnBookBill load(int id) {
        return baseDao.loadProxy(UnBookBill.class,id);
    }

    @Override
    public List<UnBookBill> getByRestrictEqual(String column, Object value) {
        return baseDao.getByRestrictEqual(UnBookBill.class,column,value);
    }

    @Override
    public List<UnBookBill> getByRestrictEqual(Map<String, Object> map) {
        return baseDao.getByRestrictEqual(UnBookBill.class,map);
    }

    @Override
    public int add(UnBookBill unBookBill) throws Exception {
        return baseDao.save(unBookBill);
    }

    @Override
    public ResultMessage update(UnBookBill unBookBill) {
        return baseDao.update(unBookBill);
    }
}
