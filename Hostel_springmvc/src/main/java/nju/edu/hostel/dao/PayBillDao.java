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

    public List<PayBill> getAllUncounted();
    public List<PayBill> getAllUncountedByHostel(int hostelId);
    public List<PayBill> getAllByHostelId(int hostelId);
    public List<PayBill> getRecentByHostelId(int hostelId);
    public List<PayBill> getRecentByHostelId_Date(int hostelId,long start,long end);

    public List<PayBill> getAllByVipId(int vipId);
    public List<PayBill> getRecentByVipId(int vipId);
    public List<PayBill> getRecentByVipId_Date(int vipId,long start,long end);

    /**
     * 以入住时间为基准，返回时间段内的住房记录列表
     * @param vipId
     * @param start
     * @param end
     * @return
     */

}
