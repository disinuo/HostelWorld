package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.LiveBillDao;
import nju.edu.hostel.model.LiveBill;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class LiveBillDaoImpl implements LiveBillDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public LiveBill get(int id) {
        return baseDao.getEntity(LiveBill.class,id);
    }

    @Override
    public LiveBill load(int id) {
        return baseDao.loadProxy(LiveBill.class,id);
    }

    @Override
    public List<LiveBill> getByRestrictEqual(String column, Object value) {

        return baseDao.getByRestrictEqualDESC(LiveBill.class,column,value,"id");
    }

    @Override
    public List<LiveBill> getByRestrictEqual(Map<String, Object> map) {

        return baseDao.getByRestrictEqualDESC(LiveBill.class,map,"id");

    }
    @Override
    public List<LiveBill> getByHostelId(int hostelId){
        String hql="SELECT bill FROM LiveBill as bill" +
                " WHERE bill.room.hostel.id = "+hostelId+" ORDER BY bill.id DESC";
        return baseDao.getByHql(LiveBill.class,hql);
    }
    @Override
    public List<LiveBill> getLivingByHostelId(int hostelId){
        String hql="SELECT bill FROM LiveBill as bill" +
                " WHERE bill.room.hostel.id = "+hostelId+" AND bill.inHostel = true ORDER BY bill.id DESC";
        return baseDao.getByHql(LiveBill.class,hql);
    }
    @Override
    public List<LiveBill> getByVipId(int vipId){
        String hql="SELECT bill FROM LiveBill as bill" +
                " WHERE bill.vip.id = "+vipId+" ORDER BY bill.id DESC";
        return baseDao.getByHql(LiveBill.class,hql);
    }
    @Override
    public int add(LiveBill liveBill) throws Exception {
        return baseDao.save(liveBill);
    }

    @Override
    public ResultMessage update(LiveBill liveBill) {
        return baseDao.update(liveBill);
    }
}
