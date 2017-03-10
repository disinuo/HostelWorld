package nju.edu.hostel.dao;

import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.UnBookBill;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/10.
 */
public interface UnBookBillDao {
    public UnBookBill get(int id);
    public UnBookBill load(int id);
    public List<UnBookBill> getByRestrictEqual(String column, Object value);
    public List<UnBookBill> getByRestrictEqual(Map<String,Object> map);

    public int add(UnBookBill unBookBill)throws Exception;
    public ResultMessage update(UnBookBill unBookBill);
}
