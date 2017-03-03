package nju.edu.hostel.service;
import nju.edu.hostel.model.*;

import nju.edu.hostel.util.ResultMessage;

import java.util.List;

/**
 * Created by disinuo on 17/3/2.
 */
public interface ManagerService {

    /**
     * 总经理审批收到的开店申请
     第二个参数为true即同意，反之不同意
     * @param hostelId
     * @param ifApprove
     * @return
     */
    public ResultMessage approve(int hostelId, Boolean ifApprove);

    /**
     * 总经理结算
     根据数据库里没结算过的账单，根据账单的hostelId，
     系统自动将各客栈没结算过的账单金额加和，从总经理银行账户扣除，并加到各客栈银行账户中
     * @param managerId
     * @param bankPassword
     * @return
     */
    public List<Paybill> count(int managerId, String bankPassword);

    /**
     * 获取所有预订数据，包括预订和取消预订
     * 【前置】以总经理身份登录
     * @return
     */
    public List<Bookbill> getAllBookBills();

    /**
     * 获取所有消费数据
     *【前置】以总经理身份登录

     * @return
     */
    public List<Paybill> getAllPayBills();

    /**
     * 获取所有会员的消费数据
     * @return
     */
    public List<Paybill> getAllPayBillsOfVIP();

    /**
     * 获取所有住店数据
     * @return
     */
    public List<Livebill> getAllLiveBills();

    /**
     * 获取指定客栈的所以住店数据
     * @param hostelId
     * @return
     */
    public List<Livebill> getAllLiveBillsByHostel(int hostelId);

}
