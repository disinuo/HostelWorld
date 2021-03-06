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

    public int add(PayBill payBill)throws Exception;
    public ResultMessage update(PayBill payBill);

    public List<PayBill> getAllUncounted();
    public List<PayBill> getAllUncountedByHostel(int hostelId);
    public List<PayBill> getAllByHostelId(int hostelId);
    public List<PayBill> getAllVipPayBillsByHostelId(int hostelId);
    public List<PayBill> getRecentByHostelId(int hostelId);
    public List<PayBill> getByHostelId_Date(int hostelId, long start, long end);

    public List<PayBill> getAllByVipId(int vipId);
    public List<PayBill> getRecentByVipId(int vipId);
    public List<PayBill> getByVipId_Date(int vipId, long start, long end);


}
