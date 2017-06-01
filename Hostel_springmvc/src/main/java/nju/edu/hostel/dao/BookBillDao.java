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

    public int add(BookBill bookBill)throws Exception;
    public ResultMessage update(BookBill bookBill);

    /**
     * 返回默认前n条
     * @param vipId
     * @return
     */


/**
 *==================== 维度 ==========================
 */
    /**
        ------ Date -------
     */
    public List<BookBill> getAllByVipId(int vipId);
    public List<BookBill> getRecentByVipId(int vipId);
    public List<BookBill> getByVip_Date(int vipId, long start, long end);


    public List<BookBill> getAllValidByVipId(int vipId);
    public List<BookBill> getRecentValidByVipId(int vipId);
    public List<BookBill> getValidByVip_Date(int vipId, long start, long end);


    public List<BookBill> getAllByHostelId(int hostelId);
    public List<BookBill> getRecentByHostelId(int hostelId);
    public List<BookBill> getByHostel_Date(int hostelId, long start, long end);

/**
 - 地区：省、市。
 - 时间：年、月、周（比如一周内周几顾客流量比较大）、日(每一天的高流量时段)
 - 客户：年龄、性别
 - 价格

province  city

 * [默认]0：预订未入住
 * -1：已取消
 * 1： 已入住
    private int state=0;
 */
}
