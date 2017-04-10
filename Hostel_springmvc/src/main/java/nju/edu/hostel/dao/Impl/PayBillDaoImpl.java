package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.PayBillDao;
import nju.edu.hostel.model.PayBill;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import static nju.edu.hostel.util.Constants.DEFAULT_NUM_OF_DATA;


/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class PayBillDaoImpl implements PayBillDao {
    @Autowired
    BaseDao baseDao;

    private String baseHql_vip="SELECT DISTINCT paybill FROM PayBill as paybill,LiveBill as livebill,LiveDetail as detail" +
            " WHERE paybill.liveBill.id = livebill.id "+
            " AND livebill.id=detail.liveBill.id ";
    private String baseHql_hostel="SELECT paybill FROM PayBill as paybill WHERE ";
    private String hqlTail =" ORDER BY paybill.createDate DESC";
    private String hostelColumnName=" paybill.hostel.id ";
    private String vipColumnName="  detail.vip.id ";
    private String restrict_uncounted=" paybill.counted = false ";

    @Override
    public PayBill get(int id) {
        return baseDao.getEntity(PayBill.class,id);
    }

    @Override
    public PayBill load(int id) {
        return baseDao.loadProxy(PayBill.class,id);
    }

    @Override
    public int add(PayBill payBill) throws Exception {
        return baseDao.save(payBill);
    }

    @Override
    public ResultMessage update(PayBill payBill) {
        return baseDao.update(payBill);
    }

    @Override
    public List<PayBill> getAllByHostelId(int hostelId){
        String hql=baseHql_hostel+hostelColumnName+" = "+hostelId+ hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }


    @Override
    public List<PayBill> getRecentByHostelId(int hostelId) {
        String hql=baseHql_hostel+hostelColumnName+" = "+hostelId+ hqlTail;
        return baseDao.getByHql_paging(PayBill.class,hql,0,DEFAULT_NUM_OF_DATA);
    }

    @Override
    public List<PayBill> getByHostelId_Date(int hostelId, long start, long end){
        String hql=baseHql_hostel+hostelColumnName+" = "+hostelId+
                " AND paybill.createDate BETWEEN "+start+" AND "+end+
                hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }
    @Override
    public List<PayBill> getAllVipPayBillsByHostelId(int hostelId){
        String hql=baseHql_vip+" AND "+hostelColumnName+" = "+hostelId+
                " AND "+vipColumnName+">0"+hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }
    @Override
    public List<PayBill> getAllByVipId(int vipId){
        String hql=baseHql_vip+" AND "+vipColumnName+" = "+vipId+ hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }

    @Override
    public List<PayBill> getRecentByVipId(int vipId) {
        String hql=baseHql_vip+" AND "+vipColumnName+" = "+vipId+ hqlTail;
        return baseDao.getByHql_paging(PayBill.class,hql,0,DEFAULT_NUM_OF_DATA);
    }


    @Override
    public List<PayBill> getByVipId_Date(int vipId, long start, long end) {
        String hql=baseHql_vip+" AND "+vipColumnName+" = "+vipId+
                " AND paybill.createDate BETWEEN "+start+" AND "+end+
                hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }


    @Override
    public List<PayBill> getAllUncounted() {
        String hql=baseHql_hostel+restrict_uncounted+hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }

    @Override
    public List<PayBill> getAllUncountedByHostel(int hostelId) {
        String hql=baseHql_hostel+
                hostelColumnName+" = "+hostelId+" AND "+
                restrict_uncounted+
                hqlTail;
        return baseDao.getByHql(PayBill.class,hql);
    }
}
