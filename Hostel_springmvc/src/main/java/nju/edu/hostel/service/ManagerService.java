package nju.edu.hostel.service;
import nju.edu.hostel.model.*;

import nju.edu.hostel.util.ResultMessage;

import java.util.List;

/**
 * Created by disinuo on 17/3/2.
 */
public interface ManagerService {
    /**
     * 总经理点击【审批开店申请】or登陆的时候调用
     * 返回所有未审批的开店申请
     * @return
     */
    public List<RequestOpen> getOpenRequests();

    /**
     * 总经理点击【审批店信息修改申请】or登陆的时候调用
     * 返回所有未审批的店信息修改申请
     * @return
     */
    public List<RequestModify> getModifyRequests();
    /**
     * 总经理审批收到的开店申请
     * @param requestOpen
     * @return
     */
    public ResultMessage updateOpenRequest(RequestOpen requestOpen);
    /**
     * 总经理审批收到的店信息更改申请
     * @param requestModify
     * @return
     */
    public ResultMessage updateModifyRequest(RequestModify requestModify);

    /**
     * 总经理结算
     根据数据库里没结算过的账单，根据账单的hostelId，
     系统自动将各客栈没结算过的账单金额加和，从总经理银行账户扣除，并加到各客栈银行账户中
     * @param managerId
     * @param bankPassword
     * @return
     */
    public List<PayBill> count(int managerId, String bankPassword);

    /**
     * 获取所有预订数据，包括预订和取消预订
     * 【前置】以总经理身份登录
     * @return
     */
    public List<BookBill> getAllBookBills();

    /**
     * 获取所有消费数据
     *【前置】以总经理身份登录

     * @return
     */
    public List<PayBill> getAllPayBills();

    /**
     * 获取所有会员的消费数据
     * @return
     */
    public List<PayBill> getAllPayBillsOfVIP();

    /**
     * 获取所有住店数据
     * @return
     */
    public List<LiveBill> getAllLiveBills();

    /**
     * 获取指定客栈的所以住店数据
     * @param hostelId
     * @return
     */
    public List<LiveBill> getAllLiveBillsByHostel(int hostelId);

}
