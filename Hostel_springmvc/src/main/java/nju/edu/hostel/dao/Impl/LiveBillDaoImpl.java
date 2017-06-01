package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.LiveBillDao;
import nju.edu.hostel.model.LiveBill;
import nju.edu.hostel.model.LiveDetail;
import nju.edu.hostel.util.ResultMessage;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import static nju.edu.hostel.util.Constants.DEFAULT_NUM_OF_DATA;


/**
 * Created by disinuo on 17/3/3.
 */
@Repository
public class LiveBillDaoImpl implements LiveBillDao {
    @Autowired
    BaseDao baseDao;
    private String baseHql_withDetail=
            "SELECT bill FROM LiveBill as bill,LiveDetail as detail"+
            " WHERE detail.liveBill.id=bill.id AND ";
    private String baseHql=
            "SELECT DISTINCT bill FROM LiveBill as bill WHERE ";
    private String hqlTail=" ORDER BY bill.id DESC";
    private static final String RESTRICT_NOT_OUT = " bill.inHostel=true ";
    private static final String RESTRICT_NOT_PAID = " bill.paid=false ";
    public static final String ENTITY_TYPE_VIP=" detail.vip.id ";
    public static final String ENTITY_TYPE_HOSTEL=" bill.hostel.id ";
    @Override
    public LiveBill get(int id) {
        return baseDao.getEntity(LiveBill.class,id);
    }

    @Override
    public LiveBill load(int id) {
        return baseDao.loadProxy(LiveBill.class,id);
    }


//============================================================
    @Override
    public List<LiveBill> getAllByVipId(int vipId) {
        String hql=baseHql_withDetail+ENTITY_TYPE_VIP+"="+vipId+hqlTail;
        System.err.println("HQL= "+hql);
        return baseDao.getByHql(LiveBill.class,hql);
    }

    @Override
    public List<LiveBill> getAllByHostelId(int hostelId) {
        String hql=baseHql+ENTITY_TYPE_HOSTEL+"="+hostelId+hqlTail;
        System.err.println("HQL= "+hql);
        return baseDao.getByHql(LiveBill.class,hql);
    }
    @Override
    public List<LiveBill> getRecentByVipId(int vipId){
        String hql=baseHql_withDetail+ENTITY_TYPE_VIP+"="+vipId+hqlTail;
        return baseDao.getByHql_paging(LiveBill.class,hql,0,DEFAULT_NUM_OF_DATA);
    }

    @Override
    public List<LiveBill> getRecentByHostelId(int hostelId) {
        String hql=baseHql+ENTITY_TYPE_HOSTEL+"="+hostelId+hqlTail;
        System.err.println("HQL= "+hql);
        return baseDao.getByHql_paging(LiveBill.class,hql,0,DEFAULT_NUM_OF_DATA);
    }

    @Override
    public List<LiveBill> getByVipId_Date(int vipId, long start, long end) {
        String hql=baseHql_withDetail+ENTITY_TYPE_VIP+"="+vipId+
                " AND bill.date BETWEEN "+start+" AND "+end+
                hqlTail;
        System.err.println("HQL= "+hql);
        return baseDao.getByHql(LiveBill.class,hql);
    }

    @Override
    public List<LiveBill> getByHostelId_Date(int hostelId, long start, long end) {
        String hql=baseHql+ENTITY_TYPE_HOSTEL+"="+hostelId+
                " AND bill.date BETWEEN "+start+" AND "+end+
                hqlTail;
        return baseDao.getByHql(LiveBill.class,hql);
    }

    @Override
    public List<LiveBill> getNotOutByHostelId(int hostelId) {
        String hql=baseHql+ENTITY_TYPE_HOSTEL+"="+hostelId+" AND "+RESTRICT_NOT_OUT+hqlTail;
        System.err.println("HQL= "+hql);
        return baseDao.getByHql(LiveBill.class,hql);

    }

    @Override
    public List<LiveBill> getNotPaidByHostelId(int hostelId) {
        String hql=baseHql+ENTITY_TYPE_HOSTEL+"="+hostelId+" AND "+RESTRICT_NOT_PAID+hqlTail;
        System.err.println("HQL= "+hql);
        return baseDao.getByHql(LiveBill.class,hql);
    }



    @Override
    public int add(LiveBill liveBill) throws Exception {
        int billId=baseDao.save(liveBill);
        List<LiveDetail> details=liveBill.getLiveDetails();
        for(LiveDetail detail:details){
            detail.setLiveBill(liveBill);
            baseDao.save(detail);
        }
        return billId;
    }

    @Override
    public ResultMessage update(LiveBill liveBill) {
        ResultMessage msg;
        List<LiveDetail> details=liveBill.getLiveDetails();
        for(LiveDetail detail:details){
            msg=baseDao.update(detail);
            if(msg==ResultMessage.FAILURE) return msg;
        }
        return baseDao.update(liveBill);
    }



}
