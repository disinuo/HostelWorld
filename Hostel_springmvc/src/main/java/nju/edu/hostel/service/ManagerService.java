package nju.edu.hostel.service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.edu.hostel.model.*;

import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.output.IncomeVO;
import nju.edu.hostel.vo.output.LiveInNumVO;
import nju.edu.hostel.vo.output.RequestModifyVO;
import nju.edu.hostel.vo.output.RequestOpenVO;

import java.util.List;
import java.util.Map;

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
     * @param requestId
     * @param requestState
     * @return FAILURE,SUCCESS
     */
    public ResultMessage updateOpenRequest(int requestId,String requestState);
    /**
     * 总经理审批收到的店信息更改申请
     * @param requestId
     * @param requestState
     * @return FAILURE,SUCCESS
     */
    public ResultMessage updateModifyRequest(int requestId,String requestState);

    /**
     * 总经理结算
     根据数据库里没结算过的账单，根据账单的hostelId，
     系统自动将各客栈没结算过的账单金额加和，从总经理银行账户扣除，并加到各客栈银行账户中
     * @param managerId
     * @param bankPassword
     * @return WRONG_PASSWORD,FAILURE,SUCCESS
     * //TODO 可考虑添加按客栈结算，就是总经理可以只给某个客栈结算~
     */
    public ResultMessage count(int managerId, String bankPassword);


    /**
     * 获取所有会员
     * 包含了每个会员的住店、预订、消费的所有记录
     */
    public List<Vip> getAllVips();

    /**
     * 获取所有客栈的收入金额
     * @return
     */
    public List<IncomeVO> getHostelIncomes();

    /**
     * 获取所有客栈的住店人数
     * @return
     */
    public List<LiveInNumVO> getLiveInNums();

    /**
     * 年度大盘点
     * 返回所有有效酒店的以下信息
     * 本年度：总收入、总住店人数、房间总数，酒店名称-ID
     */
    public JSONObject getSummaryNumOfAllHostels();

    /**
     * 统计各城市收入、住店人数
     * 返回的数据要放在地图里
     * @return
     * 本年度：
     *   {name: 城市名, value: 住店人数, income:收入}
     */
    public JSONObject getSummaryNumByCity();
    public List<BossMoneyRecord> getAllMoneyRecords();
}
