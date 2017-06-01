package nju.edu.hostel.dao.Impl;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.dao.BookBillDao;
import nju.edu.hostel.model.BookBill;
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
public class BookBillDaoImpl implements BookBillDao {
    @Autowired
    BaseDao baseDao;
    private String baseHql="SELECT bill FROM BookBill as bill WHERE ";
    private String hqlTail=" ORDER BY bill.id DESC";

    @Override
    public BookBill get(int id) {
        return baseDao.getEntity(BookBill.class,id);
    }

    @Override
    public BookBill load(int id) {
        return baseDao.loadProxy(BookBill.class,id);
    }


    // ================== DATES========


    @Override
    public List<BookBill> getAllValidByVipId(int vipId){
        String hql=baseHql+" bill.vip.id = "+vipId+" AND bill.state>-1"+
                hqlTail;
        return baseDao.getByHql(BookBill.class,hql);
    }
    @Override
    public List<BookBill> getRecentValidByVipId(int vipId) {
        String hql=baseHql+" bill.vip.id = "+vipId+" AND bill.state>-1"+
                hqlTail;
        return baseDao.getByHql_paging(BookBill.class,hql,0,DEFAULT_NUM_OF_DATA);
    }
    @Override
    public List<BookBill> getValidByVip_Date(int vipId, long start, long end) {
        return getValidByVipHelper(vipId,"createDate",start,end);
    }


    @Override
    public List<BookBill> getAllByVipId(int vipId) {
        String hql=baseHql+" bill.vip.id = "+vipId+hqlTail;
        return baseDao.getByHql(BookBill.class,hql);
    }
    @Override
    public List<BookBill> getRecentByVipId(int vipId) {
        return getDefaultHelper("vip.id",vipId);
    }
    @Override
    public List<BookBill> getByVip_Date(int vipId, long start, long end) {
        return getHelper("vip.id",vipId,"createDate",start,end);
    }

    @Override
    public List<BookBill> getAllByHostelId(int hostelId) {
        String hql=baseHql+" bill.hostel.id = "+hostelId+hqlTail;
        return baseDao.getByHql(BookBill.class,hql);
    }
    @Override
    public List<BookBill> getRecentByHostelId(int hostelId) {
        return getDefaultHelper("hostel.id",hostelId);
    }
    @Override
    public List<BookBill> getByHostel_Date(int hostelId, long start, long end) {
        return getHelper("hostel.id",hostelId,"createDate",start,end);
    }
    @Override
    public int add(BookBill bookBill) throws Exception {
        return baseDao.save(bookBill);
    }

    @Override
    public ResultMessage update(BookBill bookBill) {
        return baseDao.update(bookBill);
    }



    private  List<BookBill> getHelper(String idType,int id,String dateType,long start,long end){
        String hql=baseHql+" bill."+idType+" = "+id+
                " AND bill."+dateType+" BETWEEN "+start+
                " AND "+end+
                hqlTail;
        return baseDao.getByHql(BookBill.class,hql);
    }
    private List<BookBill> getDefaultHelper(String idType,int id){
        String hql=baseHql+" bill."+idType+" = "+id+
                hqlTail;
        return baseDao.getByHql_paging(BookBill.class,hql,0,DEFAULT_NUM_OF_DATA);
    }
    private List<BookBill> getValidByVipHelper(int vipId,String dateType,long start,long end){
        String hql=baseHql+
                " bill.vip.id = "+vipId+
                " AND bill."+dateType+">="+start+"&&<"+end+
                hqlTail;
        return baseDao.getByHql(BookBill.class,hql);
    }

}
