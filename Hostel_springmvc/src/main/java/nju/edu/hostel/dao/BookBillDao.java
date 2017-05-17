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
    public List<BookBill> getByHostelId(int hostelId);
    public List<BookBill> getByVipId(int vipId);
    public List<BookBill> getValidByVipId(int vipId);
    }
