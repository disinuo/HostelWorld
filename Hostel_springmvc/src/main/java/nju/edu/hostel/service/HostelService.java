package nju.edu.hostel.service;

import nju.edu.hostel.model.*;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.GuestInputVO;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.RoomVO_input;
import nju.edu.hostel.vo.output.DataVO;

import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/2.
 */
public interface HostelService {
//    TODO 等整个写好了之后  再加一下  各种单子的删、改
    /**
     * 初始化
     * 包括 若本登录的客栈未通过审批的话，
     * 如果该客栈有提交过申请，提示客栈申请的状态(拒绝、等待中)
     * 通过申请就不提示了！
     * 只是提示~！！
     * 具体比如如果总经理同意申请，是在总经理service那边改变的客栈permitted属性
     *
     * @return : REMIND_REQUEST,REQUEST_DENIED,REQUEST_UNCHECKED,SUCCESS;
     */
    public ResultMessage init(int hostelId);
    /**
     * 删除客栈
     * @param hostelId
     * @return
     */
    public ResultMessage delete(int hostelId);

    /**
     * 客栈向总经理发出开店申请（注意讨论该客栈是否已经通过审批）
     * @param hostelId
     * @return SUCCESS,FAILURE
     */
    public ResultMessage requestManager(int hostelId);

    /**
     * 根据客栈ID获得客栈数据：
     店名、地理位置、盈利总金额、营业时间
     * @param hostelId
     * @return SUCCESS,FAILURE
     */
    public Hostel getById(int hostelId);

    /**
     * 修改客栈的基本信息
     * 改完并没有保存，而是向总经理提交申请
     * 总经理同意店信息才会更新
     * @param
     * @return SUCCESS,FAILURE
     */
    public ResultMessage update(int id,String descrip,String name,String address,String phone);

    /**
     * 登记住户的结账
     包括会员的消费、非会员的消费
     非会员的线下消费也当做是交到总经理账户中了。
     所以总经理还是根据各个客栈的`未结算金额`进行【结算】
     * @param liveBillId
     * @return 实际顾客应支付的值or-1
     */
    public double enrollPay(int liveBillId);

    /**
     * 会员选择用会员卡支付的时候，店员会选择这个服务。
     * 将enRollPay的返回值传过来
     * 给vipId的会员卡扣钱。收到的钱会加到总经理的账户。
     * 若余额不足店员应该提示顾客，以其他方式支付
     * @param vipId
     * @param money
     * @param hostelId
     * @return NOT_ENOUGH_MONEY,SUCCESS,FAILURE
     */
    public ResultMessage vipPay(int vipId,int hostelId,double money);

    /**
     * 非会员结账的时候 或会员选择不用会员卡支付的时候
     * 店员要调用这个服务！
     * 收到的钱会加到总经理的账户
     * @param money
     * @param hostelId
     * @return SUCCESS,FAILURE
     */
    public ResultMessage unVipPay(int hostelId,double money);

    /**
     * 登记住户的入店信息：
     住户真实名字，住户身份证号，入住日期，入住房间
     * @param liveInVO
     * @return SUCCESS,FAILURE
     */
    public ResultMessage liveIn(LiveInVO liveInVO);

    /**
     * 办理住户离店
     * 【前置】该住户已住店，且未登记离店
     * @param liveBillId
     * @return SUCCESS,FAILURE
     */
    public ResultMessage checkOut(int liveBillId);

    /**
     * 客栈发布房间计划,只能发布自己客栈的房间计划
     * @param hostelId
     * @param roomVOs
     * @return SUCCESS,FAILURE
     */
    public ResultMessage addRoom(int hostelId, List<RoomVO_input> roomVOs);

    /**
     * 客栈发布房间计划,只能发布自己客栈的房间计划
     * @param hostelId
     * @param roomVO
     * @return SUCCESS,FAILURE
     */
    public ResultMessage addRoom(int hostelId, RoomVO_input roomVO);

    /**
     * 客栈更新房间计划（包括将该房间置为不可用，就是下架~），只能更新自己客栈的房间计划
     * @param roomId
     * @param roomVO
     * @return SUCCESS,FAILURE
     */
    public ResultMessage updateRoom(int roomId, RoomVO_input roomVO);

    /**
     * 下市一个房间
     * @param roomId
     * @return
     */
    public ResultMessage invalidateRoom(int roomId);
//==================== BookBill =============================================================
    public BookBill getBookBillById(int billId);

    /**
     * 获取本店预订数据，包括预订和取消预订
     */
    public List<BookBill> getAllBookBills(int hostelId);
    public List<BookBill> getRecentBookBills(int hostelId);
    public List<BookBill> getRecentWeekBookBills(int hostelId);
    public List<BookBill> getRecentMonthBookBills(int hostelId);
    public List<BookBill> getRecentYearBookBills(int hostelId);

    /**
     * 指标：
     *  - 订单总量
     *  - 有效订单率（未取消的/总量）
     *  - 订单入住率（入住了的/未取消的）（注意：分母是预定的入住日期已到的）
     *       注意！：没住不只是state=0，得是当前时间已经晚于预订的入住时间

     */

    /**
     * 维度：时间
     * @return year:numOfBill
     */
    // 范围：【所有订单】；分组标准【年】
    public List<DataVO> getNotCancelledBookNumByYear(int hostelId);
    public List<DataVO> getValidBookRateByYear(int hostelId);
    public List<DataVO> getLiveInBookRateByYear(int hostelId);
    // 范围：【今年】；分组标准【月份】
    public List<DataVO> getNotCancelledBookNumByMonth(int hostelId);
    public List<DataVO> getValidBookRateByMonth(int hostelId);
    public List<DataVO>  getLiveInBookRateByMonth(int hostelId);
    // 范围：【今年】；分组标准【星期几】
    public List<DataVO> getNotCancelledBookNumByWeek(int hostelId);
    public List<DataVO>  getValidBookRateByWeek(int hostelId);
    public List<DataVO>  getLiveInBookRateByWeek(int hostelId);
    /**
     * 维度：下单的vip的地区
     * 范围：【未取消的订单】
     * @return region:numOfBill
     */
    // 分组标准【vip的省或市】
    public List<DataVO>  getNotCancelledBookNumByVipRegion(int hostelId, int regionType);
    /**
     * 维度：下单的vip的年龄
     * 范围：【未取消的订单】
     * @return ageRange:numOfBill
     */
    // 分组标准【vip的年龄段：<18,18~30,30~50,>50】
    public List<DataVO>  getNotCancelledBookNumByVipAge(int hostelId);
    /**
     * 维度：下单的房型
     * 范围：【未取消的订单】
     * @return roomType:numOfBill
     */
    public List<DataVO>  getNotCancelledBookNumByRoomType(int hostelId);
    /**
     * 维度：下单的房型
     * 范围：【未取消的订单】
     * @return roomPrice:numOfBill
     */
    public List<DataVO>  getNotCancelledBookNumByRoomPrice(int hostelId);

//==================== End Of BookBill =============================================================

//==================== LiveBill =============================================================
    public LiveBill getLiveBillById(int billId);
    public List<LiveBill> getAllLiveBills(int hostelId);
    public List<LiveBill> getRecentLiveBills(int hostelId);
    public List<LiveBill> getRecentWeekLiveBills(int hostelId);
    public List<LiveBill> getRecentMonthLiveBills(int hostelId);
    public List<LiveBill> getRecentYearLiveBills(int hostelId);
    /**
     * 获取本店所有未离店的住店数据
     */
    public List<LiveBill> getNotOutLiveBills(int hostelId);
    /**
     * 获取本店所有未记账的住店数据
     */
    public List<LiveBill> getNotPaidLiveBills(int hostelId);


    public List<DataVO> getLiveInNumByYear();
    public List<DataVO> getLiveInNumByMonth();
    public List<DataVO> getLiveInNumByWeek();

    /**
     * 住店记录中是会员的占比
     * @return
     */
    public List<DataVO> getLiveInVipRateByYear();
    public List<DataVO> getLiveInVipRateByMonth();
    public List<DataVO> getLiveInVipRateByWeek();
    /**
     * 分组标准：一天内的【时段】
     */
    public List<DataVO> getLiveInNumByDay();
    public List<DataVO> getLiveInNumByVipRegion();
    public List<DataVO> getLiveInNumByVipAge();
    public List<DataVO> getLiveInNumByRoomType();
    public List<DataVO> getLiveInNumByRoomPrice();


//TODO 还要加接口---预订并入住了的会员，实际入住的日期区间与预订日期区间的比较

    /**
     * 此接口返回各顾客类型的入住量。
     * 维度：顾客类型。guestType就只有会员、非会员两种。
     * 范围：所有入住单
     */
    public List<DataVO> getLiveInNumByguestType();
    /**
     * 各种房型的空房率 每天
     */
    public List<DataVO> getVacantRateByRoomType();


//==================== End Of LiveBill =============================================================


//==================== PayBill =============================================================
    /**
     * 获取本店财务情况
     * @param hostelId
     * @return
     */
    public List<PayBill> getAllPayBills(int hostelId);
    public List<PayBill> getRecentPayBills(int hostelId);
    public List<PayBill> getRecentWeekPayBills(int hostelId);
    public List<PayBill> getRecentMonthPayBills(int hostelId);
    public List<PayBill> getRecentYearPayBills(int hostelId);
    public List<PayBill> getUncountedPayBills(int hostelId);

//==================== End Of PayBill =============================================================





    public int getTotalLiveInNum(int hostelId);
    public int getPresentLiveInNum(int hostelId);


    /**
     * 获取本店总收入
     * @param hostelId
     * @return
     */
    public double getIncome(int hostelId);
    /**
     * 得到该客栈的所有房间
     * @param hostelId
     * @return
     */
    public List<Room> getAllRooms(int hostelId);

    /**
     * 给会员用的接口~
     * 只获得现在在市场上的房间
     * @param hostelId
     * @return
     */
    public List<Room> getAllValidRooms(int hostelId);

    /**
     * 按照房间的id得到房间
     * @param roomId
     * @return
     */
    public Room getRoomById(int roomId);

    /**
     * 返回所有通过总经理审批的客栈
     * @return
     */
    public List<Hostel> getAllPermittedHostels();


    /**
     * 根据酒店的id返回所有交易记录
     * @return
     */
    public  List<HostelMoneyRecord> getAllMoneyRecords(int hostelId);
}
