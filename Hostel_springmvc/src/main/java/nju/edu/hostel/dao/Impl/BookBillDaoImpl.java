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
        return baseDao.getByRestrictEqualDESC(BookBill.class,column,value,"id");
    }
    @Override
    public List<BookBill> getByRestrictEqual(Map<String, Object> map) {

        return baseDao.getByRestrictEqualDESC(BookBill.class,map,"id");

    }

    @Override
    public List<BookBill> getByHostelId(int hostelId){
        String hql="SELECT bill FROM BookBill as bill" +
                " WHERE bill.room.hostel.id = "+hostelId+" ORDER BY bill.id DESC";
        return baseDao.getByHql(BookBill.class,hql);
    }
    @Override
    public List<BookBill> getByVipId(int vipId){
        String hql="SELECT bill FROM BookBill as bill" +
                " WHERE bill.vip.id = "+vipId+" ORDER BY bill.id DESC";
        return baseDao.getByHql(BookBill.class,hql);
    }

    @Override
    public List<BookBill> getValidByVipId(int vipId){
        String hql="SELECT bill FROM BookBill as bill" +
                " WHERE bill.vip.id = "+vipId+" AND bill.state>-1 ORDER BY bill.id DESC";
        return baseDao.getByHql(BookBill.class,hql);
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
