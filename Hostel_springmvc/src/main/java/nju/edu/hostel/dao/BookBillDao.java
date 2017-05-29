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


/**
 *==================== 维度 ==========================
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
     * 以vip的地址或hostel的地址[province、city]为基准,返回值为value的所有bookbill
     * @param hostelId
     * @param whoseRegion：vip或hostel
     * @param regionType：province或city
     * @param value：该地值属性的值
     * @return
     */
    public List<BookBill> getByHostel_Region(int hostelId, int whoseRegion,int regionType,String value);
    /**
     * 以vip的地址或hostel的地址[province、city]为基准,返回一个map
     * 格式是 region:numOfBill
     * @param hostelId
     * @param whoseRegion：vip或hostel
     * @param regionType：province或city
     * @return
     */
    public Map<String,Integer> getByHostel_Region(int hostelId, int whoseRegion,int regionType);


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
