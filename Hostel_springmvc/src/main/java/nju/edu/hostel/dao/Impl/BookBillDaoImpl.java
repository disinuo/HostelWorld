package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.BookBillDao;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class BookBillDaoImpl implements BookBillDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public BookBill get(int id) {
        return baseDao.getEntity(BookBill.class,id);
    }

    @Override
    public BookBill load(int id) {
        return baseDao.loadProxy(BookBill.class,id);
    }

    @Override
    public List<BookBill> getByRestrictEqual(String column, Object value) {
        return baseDao.getByRestrictEqual(BookBill.class,column,value);
    }

    @Override
    public List<BookBill> getByRestrictEqual(Map<String, Object> map) {
        return baseDao.getByRestrictEqual(BookBill.class,map);
    }

    @Override
    public int add(BookBill bookBill) throws Exception {
        return baseDao.save(bookBill);
    }

    @Override
    public ResultMessage update(BookBill bookBill) {
        return baseDao.update(bookBill);
    }
}
