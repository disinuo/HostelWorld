package nju.edu.hostel.service;

import nju.edu.hostel.model.*;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.LiveOutVO;
import nju.edu.hostel.vo.input.PayVO;
import nju.edu.hostel.vo.input.RoomVO_input;
import nju.edu.hostel.vo.output.HostelVO;

import java.util.List;

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
    public ResultMessage update(int id,String name,String address,String phone);

    /**
     * 登记住户的结账
     包括会员的消费、非会员的消费
     非会员的线下消费也当做是交到总经理账户中了。
     所以总经理还是根据各个客栈的`未结算金额`进行【结算】
     * @param payVO
     * @return 实际顾客应支付的值or-1
     */
    public double enrollPay(PayVO payVO);

    /**
     * 会员选择用会员卡支付的时候，店员会选择这个服务。
     * 将enRollPay的返回值传过来
     * 给vipId的会员卡扣钱。收到的钱会加到总经理的账户。
     * 若余额不足店员应该提示顾客，以其他方式支付
     * @param vipId
     * @param money
     * @return NOT_ENOUGH_MONEY,SUCCESS,FAILURE
     */
    public ResultMessage vipPay(int vipId,double money);

    /**
     * 非会员结账的时候 或会员选择不用会员卡支付的时候
     * 店员要调用这个服务！
     * 收到的钱会加到总经理的账户
     * @param money
     * @return SUCCESS,FAILURE
     */
    public ResultMessage unVipPay(double money);

    /**
     * 登记住户的入店信息：
     住户真实名字，住户身份证号，入住日期，入住房间
     * @param liveInVO
     * @return SUCCESS,FAILURE
     */
    public ResultMessage liveIn(LiveInVO liveInVO);

    /**
     * 登记住户的离店信息:
     * 住户真实名字，住户身份证号，离店日期，入住房间
     * 【前置】该住户已住店，且未登记离店
     * @param liveOutVO
     * @return SUCCESS,FAILURE
     */
    public ResultMessage depart(LiveOutVO liveOutVO);

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
     * @param hostelId
     * @param roomVO
     * @return SUCCESS,FAILURE
     */
    public ResultMessage updateRoom(int hostelId, RoomVO_input roomVO);

    /**
     * 下市一个房间
     * @param roomId
     * @return
     */
    public ResultMessage invalidateRoom(int roomId);
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

    public int getLiveInNum(int hostelId);

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
     * 获得hostelId的客栈没有被结算的账单，
     * 给总经理看结算详情的时候用的~
     * 总经理的结算界面默认不显示这些，只显示各个客栈需要结算的金额
     * 要总经理点某一个客栈再显示这些详情
     * @param hostelId
     * @return
     */
    public List<PayBill> getAllUncountedPayBills(int hostelId);
}
