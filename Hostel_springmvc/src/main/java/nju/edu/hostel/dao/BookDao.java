package nju.edu.hostel.dao;

import nju.edu.hostel.model.BookBill;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
public interface BookDao {
    public List<BookBill> getBookBillsByVipId(int vipId);
}
