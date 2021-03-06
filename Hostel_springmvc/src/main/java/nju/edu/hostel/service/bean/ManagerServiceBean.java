package nju.edu.hostel.service.bean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.edu.hostel.dao.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.*;
import nju.edu.hostel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static nju.edu.hostel.util.Constants.*;


/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class ManagerServiceBean implements ManagerService {

    @Override
    public List<RequestOpen> getOpenRequests(){
        return requestDao.getUncheckedOpenRequests();
    }

    @Override
    public List<RequestModify> getModifyRequests(){
        return requestDao.getUncheckedModifyRequests();
    }

    /**
     *
     private int id;
     private String hostel_img;
     private String hostel_phone;
     private String hostel_address;
     private String hostel_name;
     private RequestState state;
     */
    @Override
    public ResultMessage updateOpenRequest(int requestId,String requestState){
        RequestState state=RequestState.strToRequestState(requestState);
        RequestOpen request=requestDao.getOpenRequest(requestId);
        request.setState(requestState);
        if(state==RequestState.DENIED){//拒绝申请
            return requestDao.updateOpenRequest(request);
        }else if(state==RequestState.APPROVED){//同意申请
            Hostel hostel=request.getHostel();
            hostel.setPermitted(true);
            ResultMessage msg1=hostelDao.update(hostel);
            ResultMessage msg2=requestDao.updateOpenRequest(request);
            if(msg1==ResultMessage.SUCCESS&&msg2==ResultMessage.SUCCESS){
                return ResultMessage.SUCCESS;
            }else {
                return ResultMessage.FAILURE;
            }
        }else {//没审核。。。
            return ResultMessage.SUCCESS;
        }
    }
    @Override
    public ResultMessage updateModifyRequest(int requestId,String requestState){
        System.out.print("in service ---modifyRequest id="+requestId+"  "+requestState);
        RequestState state=RequestState.strToRequestState(requestState);
        RequestModify request=requestDao.getModifyRequest(requestId);
        request.setState(requestState);
        if(state==RequestState.DENIED){//拒绝修改请求
            return requestDao.updateModifyRequest(request);
        }else if(state==RequestState.APPROVED){//同意修改请求
            Hostel hostel=request.getHostelOriginal();
            hostel.setAddress(request.getNewAddress());
            hostel.setPhone(request.getNewPhone());
            hostel.setName(request.getNewName());
            ResultMessage msg1=hostelDao.update(hostel);
            ResultMessage msg2=requestDao.updateModifyRequest(request);
            if(msg1==ResultMessage.SUCCESS&&msg2==ResultMessage.SUCCESS){
                return ResultMessage.SUCCESS;
            }else {
                return ResultMessage.FAILURE;
            }
        }else {//没审核。。。
            return ResultMessage.SUCCESS;
        }
    }
    @Override
    public ResultMessage count(int managerId, String bankPassword) {
        int moneyType= MoneyType.MONEY_TYPE_COUNT.getCode();
        User manager=userDao.get(managerId);
        if(!manager.getBankPassword().equals(bankPassword)){
            return ResultMessage.WRONG_PASSWORD;//银行卡密码错误
        }else {//结算到今天为止的
            ResultMessage msg;
            List<Hostel> hostels=hostelService.getAllPermittedHostels();
            //经理结算的总金额
            double allMoneyToPay=0;
            long today=new Date().getTime();
            for(Hostel hostel:hostels){
                //获取客栈的`未结算金额`
                double moneyShouldBePaid=hostel.getMoneyUncounted();
                //给客栈加钱
                msg=userService.modifyBankMoneyBy(hostel.getId(),moneyShouldBePaid);
                if(msg!=ResultMessage.SUCCESS) return msg;
                //计算累计结算的钱数，最后一起从总经理的银行卡上扣除
                allMoneyToPay+=moneyShouldBePaid;
                //将客栈的`未结算金额`置为0
                hostel.setMoneyUncounted(0);
                //更新客栈信息
                msg=hostelDao.update(hostel);
                if(msg!=ResultMessage.SUCCESS) return msg;
                //这个酒店要被结算的钱>0，则记录一条该酒店的交易记录
                if(moneyShouldBePaid>0){
                    msg=hostelMoneyRecordDao.record(
                            hostel.getId(),
                            moneyShouldBePaid,
                            today,
                            moneyType
                    );
                    if(msg!=ResultMessage.SUCCESS) return msg;
                }

            }
            if(allMoneyToPay==0){
                return ResultMessage.NO_NEED_COUNT;
            }
            msg=userService.modifyBankMoneyBy(managerId,-allMoneyToPay);
            if(msg!=ResultMessage.SUCCESS) return msg;
            msg=bossMoneyRecordDao.record(
                    -allMoneyToPay,
                    today,
                    moneyType
            );
//       -------到这里其实客栈和总经理之间的金钱交易就结束了，不过还要更新一下被结算的账单的状态orz，而且这一步会比较慢。。。
//            为了总经理可以查看某个客栈的结算细节--账单信息，必须得更新这个状态位~！
            List<PayBill> payBills=payBillDao.getAllUncounted();
            for(PayBill payBill:payBills){
                payBill.setCounted(true);
                msg=payBillDao.update(payBill);
                if(msg!=ResultMessage.SUCCESS) return msg;
            }
            return ResultMessage.SUCCESS;
        }
    }

//============ Hostel =================
    /**
     * 年度大盘点
     * 返回所有有效酒店的以下信息
     * 本年度：总收入、总住店人数、房间总数，酒店名称-ID
     */
    @Override
    public JSONObject getSummaryNumOfAllHostels(){
        JSONArray jsonArray=new JSONArray();
        List<Hostel> hostels=hostelService.getAllPermittedHostels();
        for(Hostel hostel:hostels){
            int hostelId=hostel.getId();
            String hostelName=hostel.getName();
            //总收入
            double income=getAllIncomeThisYearByHostel(hostelId);
            //总住店人数
            int liveNum=getAllLiveInNumThisYearByHostel(hostelId);
            int roomNum=0;
            List<Room> rooms=hostelService.getAllValidRooms(hostelId);
            for(Room room:rooms) roomNum+=room.getTotalNum();
            jsonArray.add(new Object[]{NumberFormatter.saveOneDecimal(income),liveNum,roomNum,hostelName,hostelId});
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("year",DateHandler.GET_CURRENT_YEAR());
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
    private int getAllLiveInNumThisYearByHostel(int hostelId){
        int liveNum=0;
        List<LiveBill> liveBills=hostelService.getRecentYearLiveBills(hostelId);
        for(LiveBill bill:liveBills) liveNum+=bill.getNumOfPeople();
        return liveNum;
    }
    private double getAllIncomeThisYearByHostel(int hostelId){
        double income=0;
        List<PayBill> payBills=hostelService.getRecentYearPayBills(hostelId);
        for(PayBill bill:payBills) income+=bill.getMoney();
        return income;
    }
    /**
     * 统计各城市收入、住店人数
     * 返回的数据要放在地图里
     * @return
     * 本年度：
     *   {name: 城市名, value: 住店人数, income:收入}
     */
    @Override
    public JSONObject getSummaryNumByCity(){
        JSONObject jsonObject=new JSONObject();
        List<Hostel> hostels=hostelService.getAllPermittedHostels();
        Map<String,Integer> map_liveInNum=new HashMap();
        Map<String,Double> map_income=new HashMap();
        for(Hostel hostel:hostels){
            String city=hostel.getCity();
            int num_liveInNum=0;
            double num_income=0;
            if(map_liveInNum.containsKey(city)){
                num_liveInNum=map_liveInNum.get(city);
                num_income=map_income.get(city);
            }
            map_liveInNum.put(city,num_liveInNum+getAllLiveInNumThisYearByHostel(hostel.getId()));
            map_income.put(city,num_income+getAllIncomeThisYearByHostel(hostel.getId()));
        }
        JSONArray array=new JSONArray();
        for(String city:map_liveInNum.keySet()){
            array.add(new Object[]{city,map_liveInNum.get(city),NumberFormatter.saveOneDecimal(map_income.get(city))});
        }
        jsonObject.put("year",DateHandler.GET_CURRENT_YEAR());
        jsonObject.put("data",array);
        return jsonObject;
    }
//============ End Of Hostel ======================================

    private int getAllLiveInNumThisYearByVip(int vipId){
        int liveNum=0;
        List<LiveBill> liveBills=vipService.getRecentYearLiveBills(vipId);
        for(LiveBill bill:liveBills) liveNum++;
        return liveNum;
    }
    private double getAllExpenseThisYearByVip(int vipId){
        double income=0;
        List<PayBill> payBills=vipService.getRecentYearPayBills(vipId);
        for(PayBill bill:payBills) income+=bill.getMoney();
        return income;
    }
    /**
     * 返回所有会员的入住、消费、等级、状态情况
     *
     * 状态用颜色表示：红-停卡，黄-暂停，蓝-正常
     *
     * @return
     */
    @Override
    public JSONObject getAllVipLiveNumByCity() {
        JSONObject jsonObject=new JSONObject();
        List<Vip> vips=vipDao.getAll();


        Map<String,Integer> map_liveInNum=new HashMap();
        Map<String,Double> map_expense=new HashMap();
        for(Vip vip:vips){
            String city=vip.getCity();
            int num_liveInNum=0;
            double num_expense=0;
            if(map_liveInNum.containsKey(city)){
                num_liveInNum=map_liveInNum.get(city);
                num_expense=map_expense.get(city);
            }
            map_liveInNum.put(city,num_liveInNum+getAllLiveInNumThisYearByVip(vip.getId()));
            map_expense.put(city,num_expense+getAllExpenseThisYearByVip(vip.getId()));
        }
        JSONArray array=new JSONArray();
        for(String city:map_liveInNum.keySet()){
            array.add(new Object[]{
                    city,
                    map_liveInNum.get(city),
                    NumberFormatter.saveOneDecimal(map_expense.get(city))});
        }
        jsonObject.put("year",DateHandler.GET_CURRENT_YEAR());
        jsonObject.put("data",array);
        return jsonObject;
    }

    @Override
    public JSONObject getVipNumByLevel() {
        JSONObject jsonObject=new JSONObject();
        List<Vip> vips=vipDao.getAll();
        Map<String,Integer> map=CREATE_VIP_LEVEL_MAP();
        Map<String,Double> map_age=CREATE_AGE_MAP();
        for(Vip vip:vips){
            String level=VIP_LEVEL[vip.getLevel()];
            int num=map.get(level);
            map.put(level,++num);
            int currentYear=DateHandler.GET_CURRENT_YEAR();
            String age=AGE_TO_RANGE(currentYear-vip.getBirthYear());
            double num_age=map_age.get(age);
            map_age.put(age,++num_age);
        }
        jsonObject.put("total",vips.size());
        jsonObject.put("level",map);
        jsonObject.put("age",map_age);
        return jsonObject;
    }

    private Map getGuestNumByMonth_Helper(List<LiveBill> bills){
        Map map=new HashMap();
        int[] vipNumArray=new int[NUM_OF_MONTHS];
        int[] unvipNumArray=new int[NUM_OF_MONTHS];
        for(LiveBill bill:bills){
            int month=DateHandler.getFieldFromLong(Calendar.MONTH,bill.getDate());
            for(LiveDetail guest:bill.getLiveDetails()){
                if(guest.getVip()==null) unvipNumArray[month]++;
                else vipNumArray[month]++;
            }
        }
        map.put("vip",vipNumArray);
        map.put("unvip",unvipNumArray);
        return map;
    }
    /**
     * 近三年数据
     * @return
     */
    @Override
    public JSONObject getGuestLiveNumByMonth_3Years(){
        JSONObject ans=new JSONObject();
        int currentYear=DateHandler.GET_CURRENT_YEAR();
        //今年的1-1 0：0：0
        long startOfThisYear=DateHandler.yearToLong(currentYear);
        //去年
        long startOfSecondYear=DateHandler.add(startOfThisYear,Calendar.YEAR,-1);
        //前年
        long startOfFirstYear=DateHandler.add(startOfThisYear,Calendar.YEAR,-2);
        List<LiveBill> bills_firstYear=liveBillDao.getAllByDate(startOfFirstYear,startOfSecondYear);
        List<LiveBill> bills_secondYear=liveBillDao.getAllByDate(startOfSecondYear,startOfThisYear);
        List<LiveBill> bills_thirdYear=liveBillDao.getAllByDate(startOfThisYear,new Date().getTime());
        Map firstYear=getGuestNumByMonth_Helper(bills_firstYear);
        Map secondYear=getGuestNumByMonth_Helper(bills_secondYear);
        Map thirdYear=getGuestNumByMonth_Helper(bills_thirdYear);
        JSONObject vipNums=new JSONObject();
        JSONObject unvipNums=new JSONObject();


        vipNums.put(currentYear-2,firstYear.get("vip"));
        vipNums.put(currentYear-1,secondYear.get("vip"));
        vipNums.put(currentYear,thirdYear.get("vip"));
        unvipNums.put(currentYear-2,firstYear.get("unvip"));
        unvipNums.put(currentYear-1,secondYear.get("unvip"));
        unvipNums.put(currentYear,thirdYear.get("unvip"));

        ans.put("vip",vipNums);
        ans.put("unvip",unvipNums);
        return ans;

    }
    @Override
    public List<BossMoneyRecord> getAllMoneyRecords(){
        return bossMoneyRecordDao.getByBoss(MANAGER_ID);
    }

    @Autowired
    private HostelDao hostelDao;
    @Autowired
    private PayBillDao payBillDao;
    @Autowired
    private VIPDao vipDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private LiveBillDao liveBillDao;
    @Autowired
    private RequestDao requestDao;
    @Autowired
    HostelMoneyRecordDao hostelMoneyRecordDao;
    @Autowired
    BossMoneyRecordDao bossMoneyRecordDao;
    @Autowired
    private HostelService hostelService;
    @Autowired
    private UserService userService;
    @Autowired
    private VIPService vipService;
}
