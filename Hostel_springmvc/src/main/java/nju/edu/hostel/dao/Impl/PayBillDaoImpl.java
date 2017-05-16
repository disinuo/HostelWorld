package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.PayBillDao;
import nju.edu.hostel.model.PayBill;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class PayBillDaoImpl implements PayBillDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public PayBill get(int id) {
        return baseDao.getEntity(PayBill.class,id);
    }

    @Override
    public PayBill load(int id) {
        return baseDao.loadProxy(PayBill.class,id);
    }

    @Override
    public List<PayBill> getByHostelId(int hostelId){
        String hql="SELECT bill FROM PayBill as bill" +
                " WHERE bill.liveBill.room.hostel.id = "+hostelId;
        return baseDao.getByHql(PayBill.class,hql);
    }
    @Override
    public List<PayBill> getByVipId(int vipId){
        String hql="SELECT bill FROM PayBill as bill" +
                " WHERE bill.liveBill.vip.id = "+vipId;
        return baseDao.getByHql(PayBill.class,hql);
    }
    @Override
    public List<PayBill> getByRestrictEqual(String column, Object value) {

        return baseDao.getByRestrictEqualDESC(PayBill.class,column,value,"id");
    }

    @Override
    public List<PayBill> getByRestrictEqual(Map<String, Object> map) {

        return baseDao.getByRestrictEqualDESC(PayBill.class,map,"id");

    }

    @Override
    public int add(PayBill payBill) throws Exception {
        return baseDao.save(payBill);
    }

    @Override
    public ResultMessage update(PayBill payBill) {
        return baseDao.update(payBill);
    }
}
