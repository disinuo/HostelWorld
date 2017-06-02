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

    public int add(LiveBill liveBill)throws Exception;
    public ResultMessage update(LiveBill liveBill);

    /**
     * All指的是时间维度上
     * @param vipId
     * @return
     */
    public List<LiveBill> getAllByVipId(int vipId);
    public List<LiveBill> getAllByHostelId(int hostelId);

    /**
     * 返回此酒店所有【会员入住】的记录
     * @param hostelId
     * @return
     */
    public List<LiveBill> getAllVipLiveInByHostel(int hostelId);

    /**
     * 返回默认前n条
     * @param vipId
     * @return
     */
    public List<LiveBill> getRecentByVipId(int vipId);
    public List<LiveBill> getRecentByHostelId(int hostelId);

    /**
     * 以入住时间为基准，返回时间段内的住房记录列表
     * @param vipId
     * @return
     */
    public List<LiveBill> getByVipId_Date(int vipId, long start, long end);
    public List<LiveBill> getByHostelId_Date(int hostelId, long start, long end);

    /**
     * 返回未离店的
     * @param hostelId
     * @return
     */
    public List<LiveBill> getNotOutByHostelId(int hostelId);

    /**
     * 返回未结账的
     * @param hostelId
     * @return
     */
    public List<LiveBill> getNotPaidByHostelId(int hostelId);

}
