package nju.edu.hostel.service;

import nju.edu.hostel.model.*;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.LiveInVO;
import nju.edu.hostel.vo.LiveOutVO;
import nju.edu.hostel.vo.RoomVO;

import java.util.List;

/**
 * Created by disinuo on 17/3/2.
 */
public interface HostelService {
    /**
     * 删除客栈
     * @param hostelId
     * @return
     */
    public ResultMessage delete(int hostelId);

    /**
     * 客栈向总经理发出开店申请（注意讨论该客栈是否已经通过审批）
     * @param hostelId
     * @return
     */
    public ResultMessage requestManager(int hostelId);

    /**
     * 根据客栈ID获得客栈数据：
     店名、地理位置、盈利总金额、营业时间
     * @param hostelId
     * @return
     */
    public Hostel getById(int hostelId);

    /**
     * 修改客栈的基本信息
     * @param hostel
     * @return
     */
    public ResultMessage update(Hostel hostel);

    /**
     * 登记住户的结账
     包括会员的消费、非会员的消费
     非会员的线下消费也当做是交到总经理账户中了。
     所以总经理还是根据记录在系统里的账单进行【结算】
     * @param payBill
     * @return
     */
    public ResultMessage enrollPay(PayBill payBill);

    /**
     * 登记住户的入店信息：
     住户真实名字，住户身份证号，入住日期，入住房间
     * @param liveInVO
     * @return
     */
    public ResultMessage liveIn(LiveInVO liveInVO);

    /**
     * 登记住户的离店信息:
     * 住户真实名字，住户身份证号，离店日期，入住房间
     * 【前置】该住户已住店，且未登记离店
     * @param liveOutVO
     * @return
     */
    public ResultMessage depart(LiveOutVO liveOutVO);

    /**
     * 客栈发布房间计划,只能发布自己客栈的房间计划
     * @param hostelId
     * @param roomVOs
     * @return
     */
    public ResultMessage addRoom(int hostelId, List<RoomVO> roomVOs);

    /**
     * 客栈发布房间计划,只能发布自己客栈的房间计划
     * @param hostelId
     * @param roomVO
     * @return
     */
    public ResultMessage addRoom(int hostelId, RoomVO roomVO);

    /**
     * 客栈更新房间计划（包括将该房间置为不可用，就是下架~），只能更新自己客栈的房间计划
     * @param hostelId
     * @param room
     * @return
     */
    public ResultMessage updateRoom(int hostelId, Room room);

    /**
     * 获取本店预订数据，包括预订和取消预订
     比如
     vip01 2016-12-02 预订 标间 入住时间2016-12-10
     2晚 358元+图片
     vip01 2016-12-02 取消预订 标间 入住时间2016-12-10   2晚 358元+图片
     vip01 2016-12-02 预订 豪华总统套房 入住时间2016-12-10   2晚 2288元+图片
     * @param hostelId
     * @return
     */
    public List<BookBill> getAllBookBills(int hostelId);

    /**
     * 获取本店财务情况
     比如
     vip01 2016-12-02 预订房间 标间[含房间详情]+20元
     vip01 2016-12-02 取消预订 标间[含房间详情]-20元
     vip01 2016-12-02 预订房间 豪华总统套房[含房间详情]+20元
     vip01 2016-12-10 消费 豪华总统套房[含房间详情]+2288元
     * @param hostelId
     * @return
     */
    public List<PayBill> getAllPayBills(int hostelId);

    /**
     * 获取本店总收入
     * @param hostelId
     * @return
     */
    public double getIncome(int hostelId);

    /**
     * 获取本店住店数据，包含入店、离店信息
     比如
     2016-12-10 vip01入店 豪华总统套房
     2016-12-10 vip01离店 豪华总统套房
     * @param hostelId
     * @return
     */
    public List<LiveBill> getAllLiveBills(int hostelId);

    /**
     * 得到该客栈的所有房间
     * @param hostelId
     * @return
     */
    public List<Room> getAllRooms(int hostelId);

    /**
     * 按照房间的id得到房间
     * @param roomId
     * @return
     */
    public Room getRoomById(int roomId);


}
