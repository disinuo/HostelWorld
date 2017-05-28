package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.PayBillDao;
import nju.edu.hostel.model.LiveDetail;
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
                " WHERE bill.hostel.id = "+hostelId+" ORDER BY bill.id DESC";
        List<PayBill> bills=baseDao.getByHql(PayBill.class,hql);
        System.err.println("============================");
        System.err.println("in PayDAO  size= "+bills.size());
        for(PayBill payBill:bills){
            System.out.print(payBill.getId()+": ");
            List<LiveDetail> details=payBill.getLiveDetails();
            for(LiveDetail d:details){
                System.out.println(d.getId()+": "+d.getUserRealName());
            }

        }
        return bills;
    }
    @Override
    public List<PayBill> getByVipId(int vipId){
        String hql="SELECT paybill FROM PayBill as paybill,LiveBill as livebill,LiveDetail as detail" +
                " WHERE paybill.liveBill.id = livebill.id "+
                " AND livebill.id=detail.liveBill.id "+
                " AND detail.vip.id = "+vipId+" ORDER BY paybill.id DESC";
        List<PayBill> bills=baseDao.getByHql(PayBill.class,hql);

        return bills;
    }

    @Override
    public List<PayBill> getAllByVipId(int vipId) {
        return null;
    }

    @Override
    public List<PayBill> getRecentByVipId(int vipId) {
        return null;
    }

    @Override
    public List<PayBill> getRecentByVipId_Date(int vipId, long start, long end) {
        return null;
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
