package nju.edu.hostel.service;

import net.sf.json.JSONArray;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import nju.edu.hostel.vo.input.BookVO;
import nju.edu.hostel.vo.output.DataVO;

import java.util.List;

/**
 * Created by disinuo on 17/3/2.
 */
public interface VIPService {
    //TODO 应该可以一次多定几个房间，可以选数量

    /**
     * 给LoginController提供的接口
     * 检查会员的【激活时间】【暂停卡时间】
     * 如果到期要改变状态
     *
     * 激活一年后，会员卡余额不足则该会员卡状态改为【暂停】，能查记录，但不能预订、结账
     *
     * 暂停一年后，会员一直未支付到规定余额，则该会员卡状态改为【停止】，
     * 能查记录，但不能预订、结账，且不可恢复
     */
    public void init(int vipId);

    /**
     * 增/减【会员卡】余额
     * offset为+是从会员卡里扣钱
     * 包括  用户预订扣钱，取消预订加钱，结账扣钱。
     * 设置为public是为了 比如客栈结账的时候，会员选择【会员卡支付】，
     * 客栈要负责在会员的卡扣钱
     * 此扣钱数不会加到会员的`moneyPaid`属性中
     * 因为，预订的钱不算moneyPaid，
     *      住店的钱在客栈使用`enrollPay`的时候已经加到会员的`moneyPaid`里了
     *
     * @return NOT_ENOUGH_MONEY,SUCCESS,FAILURE
     */
    public ResultMessage payMoney(int vipId,double offset);

    /**
     * 会员充值，需要会员id和银行卡支付密码
     * 从银行卡扣钱，会员卡加钱
     在这个方法里判断会员卡是否为【未激活】、【暂停】、【停止】（停止则不能交费）
     * @param money
     * @param vipId
     * @param bankPassword
     * @return WRONG_PASSWORD,NOT_ENOUGH_MONEY(银行卡余额不足)
     *         VIP_STATE_STOP(停卡，不能充值),FAILURE,SUCCESS
     */
    public ResultMessage topUp(double money, int vipId, String bankPassword);

    /**
     * 用户发出停卡请求
     * 能查记录，但不能预订、结账，且不可恢复
     * @param vipId
     * @return FAILURE,SUCCESS
     */
    public ResultMessage stop(int vipId);

    /**
     * 根据会员ID获得会员数据：
     姓名、身份证号、头像、会员卡余额、消费总金额、会员等级、积分
     * @param vipId
     * @return
     */
    public Vip getById(int vipId);

    /**
     *修改会员的基本信息
     * @param id,name,idCard
     * @return FAILURE,SUCCESS
     * //TODO 可能要细化接口
     */
    public ResultMessage update(int id,String name,String idCard);

    /**
     * 会员预订房间（可能会余额不足），
     考虑会员级别不同，预订费变化
     * @param bookVO
     * @return VIP_STATE_STOP,VIP_STATE_PAUSED,VIP_STATE_UNACTIVATED
     *         NOT_ENOUGH_MONEY,FAILURE,SUCCESS,EARLY_THAN_TODAY
     */
    public ResultMessage book(BookVO bookVO);

    /**
     * 会员取消预订。bookId是预定订单id，会在预订订单生成时自动生成
     * 【前置】会员有未结账的预订记录
     * @param vipId
     * @param bookId
     * @return NO_AUTHORITY,LATE_TIME,SUCESS,FAILURE;
     */
    public ResultMessage unbook(int vipId, int bookId);

    /**
     * 会员将指定的积分数换成会员卡余额
     要注意用户只能填小于自己积分总值的积分数
     * @param vipId
     * @param score
     * @return NOT_ENOUGH_SCORE,FAILURE,SUCCESS
     */
    public ResultMessage scoreToMoney(int vipId, double score);

    /**
     * 获取所有本人预订数据，包括预订和取消预订
     */
    public List<BookBill> getAllBookBills(int vipId);
    /**
     * 获取默认条数的本人预订数据，包括预订和取消预订
     */
    public List<BookBill> getRecentBookBills(int vipId);

    public List<BookBill> getValidBookBills(int vipId);
    public List<BookBill> getRecentWeekBookBills(int vipId);
    public List<BookBill> getRecentMonthBookBills(int vipId);
    public List<BookBill> getRecentYearBookBills(int vipId);
    /**
     * 获取本人住店数据
     * @param vipId
     * @return
     */
    public List<LiveBill> getAllLiveBills(int vipId);
    public List<LiveBill> getRecentLiveBills(int vipId);
    public List<LiveBill> getRecentWeekLiveBills(int vipId);
    public List<LiveBill> getRecentMonthLiveBills(int vipId);
    public List<LiveBill> getRecentYearLiveBills(int vipId);

    /**
     * 获取本人消费数据
     * @param vipId
     * @return
     */
    public List<PayBill> getAllPayBills(int vipId);

    public List<PayBill> getRecentPayBills(int vipId);
    public List<PayBill> getRecentWeekPayBills(int vipId);
    public List<PayBill> getRecentMonthPayBills(int vipId);
    public List<PayBill> getRecentYearPayBills(int vipId);
    /**
     * 返回所有通过总经理审批的客栈
     * @return
     */
    public List<Hostel> getAllPermittedHostels();

    public List<VipMoneyRecord> getAllMoneyRecords(int vipId);
//    ======= Analyze ===============================
    public List<DataVO> getLiveInNumByProvince(int vipId);
//    ======= End Of Analyze ===============================


}
