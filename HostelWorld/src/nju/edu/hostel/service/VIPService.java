package nju.edu.hostel.service;

import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.LiveBill;
import nju.edu.hostel.model.PayBill;
import nju.edu.hostel.model.VIP;
import nju.edu.hostel.util.ResultMessage;

import java.util.List;

/**
 * Created by disinuo on 17/3/2.
 */
public interface VIPService {
    /**
     * 注册新会员
     * @param vipName
     * @param password
     * @return
     */
    public ResultMessage add(String vipName, String password);

    /**
     * 删除会员
     * @param vipId
     * @return
     */
    public ResultMessage delete(int vipId);

    /**
     * 激活会员账号，会员卡状态由【未激活】改为【正常】
     * @param vipId
     * @return
     */
    public ResultMessage activate(int vipId);

    /**
     * 会员充值，需要会员id和银行卡支付密码
     在这个方法里判断会员卡是否为【未激活】、【暂停】、【停止】（停止则不能交费）
     * @param money
     * @param vipId
     * @param bankPassword
     * @return
     */
    public ResultMessage topUp (double money,int vipId,String bankPassword);

    /**
     * 激活一年后，会员卡余额不足则该会员卡状态改为【暂停】，能查记录，但不能预订、结账
     * @param vipId
     * @return
     */
    public ResultMessage pause(int vipId);

    /**
     * 会员状态由【暂停】恢复到【正常】
     * @param vipId
     * @return
     */
    public ResultMessage restore(int vipId);

    /**
     * 暂停一年后，会员一直未支付到规定余额，则该会员卡状态改为【停止】，
     * 能查记录，但不能预订、结账，且不可恢复
     * 【前置】暂停满一年，卡余额不足或用户发出停卡请求
     * @param vipId
     * @return
     */
    public ResultMessage stop(int vipId);

    /**
     * 根据会员ID获得会员数据：
     姓名、身份证号、头像、会员卡余额、消费总金额、会员等级、积分
     * @param vipId
     * @return
     */
    public VIP getById(int vipId);

    /**
     *修改会员的基本信息
     * @param vip
     * @return
     */
    public ResultMessage update(VIP vip);

    /**
     * 会员预订房间（可能会余额不足），
     考虑会员级别不同，预订费变化
     * @param bookBill
     * @return
     */
    public ResultMessage book (BookBill bookBill);

    /**
     * 会员取消预订。bookId是预定订单id，会在预订订单生成时自动生成
     * 【前置】会员有未结账的预订记录
     * @param vipId
     * @param bookId
     * @return
     */
    public ResultMessage unbook(int vipId,int bookId);

    /**
     * 获取本人预订数据，包括预订和取消预订
     比如 2016-12-02 预订 标间 入住时间2016-12-10
     2晚 358元+图片
     2016-12-02 取消预订 标间 入住时间2016-12-10   2晚 358元+图片
     2016-12-02 预订 豪华总统套房 入住时间2016-12-10   2晚 2288元+图片
     * @param vipId
     * @return
     */
    public List<BookBill> getAllBookBills(int vipId);

    /**
     * 获取本人消费数据
     比如
     2016-12-02 预订房间 标间[含房间详情]-20元
     2016-12-02 取消预订 标间[含房间详情]+20元
     2016-12-02 预订房间 豪华总统套房[含房间详情]-20元
     2016-12-10 消费 豪华总统套房[含房间详情]-2288元
     * @param vipId
     * @return
     */
    public List<PayBill> getAllPayBills(int vipId);

    /**
     * 获取本人住店数据，包含入店、离店信息
     比如
     2016-12-10 入店 豪华总统套房
     2016-12-10 离店 豪华总统套房
     * @param vipId
     * @return
     */
    public List<LiveBill> getAllLiveBills(int vipId);

    /**
     * 会员将指定的积分数换成会员卡余额
     要注意用户只能填小于自己积分总值的积分数
     * @param vipId
     * @param score
     * @return
     */
    public ResultMessage scoreToMoney(int vipId,double score);

}
