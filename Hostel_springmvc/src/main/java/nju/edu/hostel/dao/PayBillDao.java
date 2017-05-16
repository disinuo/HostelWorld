package nju.edu.hostel.dao;

import nju.edu.hostel.model.PayBill;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface PayBillDao {
    public PayBill get(int id);
    public PayBill load(int id);
    public List<PayBill> getByRestrictEqual(String column, Object value);
    public List<PayBill> getByRestrictEqual(Map<String,Object> map);

    public int add(PayBill payBill)throws Exception;
    public ResultMessage update(PayBill payBill);
    public List<PayBill> getByHostelId(int hostelId);
    public List<PayBill> getByVipId(int hostelId);

}
