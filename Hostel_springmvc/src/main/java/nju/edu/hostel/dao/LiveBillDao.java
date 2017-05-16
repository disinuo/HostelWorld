package nju.edu.hostel.dao;

import nju.edu.hostel.model.LiveBill;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/3.
 */
public interface LiveBillDao {
    public LiveBill get(int id);
    public LiveBill load(int id);
    public List<LiveBill> getByRestrictEqual(String column, Object value);
    public List<LiveBill> getByRestrictEqual(Map<String,Object> map);

    public int add(LiveBill liveBill)throws Exception;
    public ResultMessage update(LiveBill liveBill);

    public List<LiveBill> getByHostelId(int hostelId);
    public List<LiveBill> getByVipId(int hostelId);

}
