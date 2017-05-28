package nju.edu.hostel.dao;

import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface BookBillDao {
    public BookBill get(int id);
    public BookBill load(int id);
    public List<BookBill> getByRestrictEqual(String column, Object value);
    public List<BookBill> getByRestrictEqual(Map<String,Object> map);

    public int add(BookBill bookBill)throws Exception;
    public ResultMessage update(BookBill bookBill);

    /**
     * 返回默认前n条
     * @param vipId
     * @return
     */
    public List<BookBill> getAllByVipId(int vipId);
    public List<BookBill> getDefaultNumByVipId(int vipId);
    public List<BookBill> getByVip_createDate(int vipId,long start,long end);
    public List<BookBill> getByVip_liveInDate(int vipId,long start,long end);
    public List<BookBill> getByVip_checkOutDate(int vipId,long start,long end);

    public List<BookBill> getAllValidByVipId(int vipId);
    public List<BookBill> getDefaultNumOfValidByVipId(int vipId);
    public List<BookBill> getValidByVip_createDate(int vipId,long start,long end);
    public List<BookBill> getValidByVip_liveInDate(int vipId,long start,long end);
    public List<BookBill> getValidByVip_checkOutDate(int vipId,long start,long end);


    public List<BookBill> getAllByHostelId(int hostelId);
    public List<BookBill> getDefaultNumByHostelId(int hostelId);
    public List<BookBill> getByHostel_createDate(int hostelId,long start,long end);
    public List<BookBill> getByHostel_liveInDate(int hostelId,long start,long end);
    public List<BookBill> getByHostel_checkOutDate(int hostelId,long start,long end);


}
