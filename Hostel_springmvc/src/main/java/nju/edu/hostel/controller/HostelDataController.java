package nju.edu.hostel.controller;

import static nju.edu.hostel.util.Constants.*;

import net.sf.json.JSONObject;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.util.ControllerHelper;
import nju.edu.hostel.vo.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static nju.edu.hostel.util.Constants.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/data/hostel")
@ResponseBody
public class HostelDataController {
    @Autowired
    HostelService hostelService;

    /**
     * 给别人用的
     * @param hostelId
     * @return
     */
    @RequestMapping(value = "/getValidRooms")
    public List<RoomVO> getValidRooms(@RequestParam("hostelId")int hostelId){
        return RoomVO.entityToVO(hostelService.getAllValidRooms(hostelId));
    }
    /**
     * 给别人用的
     * @param hostelId
     * @return
     */
    @RequestMapping(value = "/getHostel")
    public HostelVO getHostel(@RequestParam("hostelId")int hostelId){
        return new HostelVO(hostelService.getById(hostelId));
    }
//------------自己用的，hostel的id都从session中获得------------------


    @RequestMapping(value = "/getRooms")
    public List<RoomVO> getRooms(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return RoomVO.entityToVO(hostelService.getAllRooms(id));
    }


    @RequestMapping(value = "/getInfo")
    public HostelVO getHostelInfo(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return new HostelVO(hostelService.getById(id));
    }
/*   这个自己用，别人用 都行*/
    @RequestMapping(value = "/getRoom")
    public RoomVO getRoomById(@RequestParam("roomId")int roomId){
        return new RoomVO(hostelService.getRoomById(roomId));
    }
//============= BookList========================================================
    @RequestMapping(value = "/getAllBookList")
    public List<BookBillVO> getAllBookList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return BookBillVO.entityToVO(hostelService.getAllBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList")
    public List<BookBillVO> getRecentBookList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return BookBillVO.entityToVO(hostelService.getRecentBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList/week")
    public List<BookBillVO> getRecentWeekBookList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return BookBillVO.entityToVO(hostelService.getRecentWeekBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList/month")
    public List<BookBillVO> getRecentMonthBookList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return BookBillVO.entityToVO(hostelService.getRecentMonthBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList/year")
    public List<BookBillVO> getRecentYearBookList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return BookBillVO.entityToVO(hostelService.getRecentYearBookBills(id));
    }
    @RequestMapping(value = "/getBookBillById")
    public BookBillVO getBookBillById(int billId){
        return new BookBillVO(hostelService.getBookBillById(billId));
    }
    //==== List<DataVO> =========
    @RequestMapping(value = "/getAllBookNum/year")
    public List<DataVO> getAllBookNumByYear(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByYear(id);
    }
    @RequestMapping(value = "/getAllBookNum/month")
    public List<DataVO> getAllBookNumByMonth(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByMonth(id);
    }
    @RequestMapping(value = "/getAllBookNum/week")
    public List<DataVO> getAllBookNumByWeek(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByWeek(id);
    }
    @RequestMapping(value = "/getAllBookNum/vipProvince")
    public List<DataVO> getAllBookNumByVipProvince(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByVipRegion(id,REGIONTYPE_PROVINCE);
    }
    @RequestMapping(value = "/getAllBookNum/vipCity")
    public List<DataVO> getAllBookNumByVipCity(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByVipRegion(id,REGIONTYPE_CITY);
    }
    @RequestMapping(value = "/getAllBookNum/vipAge")
    public List<DataVO> getNotCancelledBookNumByVipAge(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByVipAge(id);
    }
    @RequestMapping(value = "/getAllBookNum/roomType")
    public List<DataVO> getNotCancelledBookNumByRoomType(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByRoomType(id);
    }
    @RequestMapping(value = "/getAllBookNum/roomPrice")
    public List<DataVO> getNotCancelledBookNumByRoomPrice(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getNotCancelledBookNumByRoomPrice(id);
    }
    @RequestMapping(value = "/getValidBookRate/year")
    public List<DataVO> getValidBookRateByYear(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getValidBookRateByYear(id);
    }
    @RequestMapping(value = "/getValidBookRate/month")
    public List<DataVO> getValidBookRateByMonth(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getValidBookRateByMonth(id);
    }
    @RequestMapping(value = "/getValidBookRate/week")
    public List<DataVO> getValidBookRateByWeek(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getValidBookRateByWeek(id);
    }

    @RequestMapping(value = "/getLiveInBookRate/year")
    public List<DataVO> getLiveInBookRateByYear(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInBookRateByYear(id);
    }
    @RequestMapping(value = "/getLiveInBookRate/month")
    public List<DataVO> getLiveInBookRateByMonth(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInBookRateByMonth(id);
    }
    @RequestMapping(value = "/getLiveInBookRate/week")
    public List<DataVO> getLiveInBookRateByWeek(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInBookRateByWeek(id);
    }

//======================= End Of BookList ============================================
   
//======================= LiveBill ============================================
    @RequestMapping(value = "/getLiveBillById")
    public LiveBillVO getLiveBillById(int billId){
        System.out.println("controller: billId= "+billId);
        return new LiveBillVO(hostelService.getLiveBillById(billId));
    }
    @RequestMapping(value = "/getAllLiveList")
    public List<LiveBillVO> getAllLivList(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getAllLiveBills(id));
    }
    @RequestMapping(value = "/getRecentLiveList")
    public List<LiveBillVO> getRecentLiveList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getRecentLiveBills(id));
    }
    @RequestMapping(value = "/getRecentLiveList/week")
    public List<LiveBillVO> getRecentWeekLiveList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getRecentWeekLiveBills(id));
    }
    @RequestMapping(value = "/getRecentLiveList/month")
    public List<LiveBillVO> getRecentMonthLiveList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getRecentMonthLiveBills(id));
    }
    @RequestMapping(value = "/getRecentLiveList/year")
    public List<LiveBillVO> getRecentYearLiveList(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getRecentYearLiveBills(id));
    }
    @RequestMapping(value = "/getNotOutLiveBills")
    public List<LiveBillVO> getNotOutLiveBills(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getNotOutLiveBills(id));
    }

    @RequestMapping(value = "/getNotPaidLiveBills")
    public List<LiveBillVO> getNotPaidLiveBills(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return LiveBillVO.entityToVO(hostelService.getNotPaidLiveBills(id));
    }
    @RequestMapping(value = "/getLiveInNum/year")
    public List<DataVO> getLiveInNumByYear(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInNumByYear(id);
    }
    @RequestMapping(value = "/getLiveInVipRate/year")
    public List<DataVO> getLiveInVipRateByYear(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInVipRateByYear(id);
    }
    @RequestMapping(value = "/getLiveInNum/month")
    public List<DataVO> getLiveInNumByMonth(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInNumByMonth(id);
    }
    @RequestMapping(value = "/getLiveInVipRate/month")
    public List<DataVO> getLiveInVipRateByMonth(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInVipRateByMonth(id);
    }
    @RequestMapping(value = "/getLiveInNum/week")
    public List<DataVO> getLiveInNumByWeek(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInNumByWeek(id);
    }
    @RequestMapping(value = "/getLiveInNum/hour")
    public JSONObject getLiveInNumByHour(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        List<Object[]> data=hostelService.getLiveInNumByHour(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("x",HOUR_OF_DAY_RANGE);
        jsonObject.put("y",DAY_OF_WEEK);
        jsonObject.put("z",data);
        return jsonObject;
    }
    @RequestMapping(value = "/getLiveInNum/room/type")
    public List<DataVO> getLiveInNumByRoomType(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInNumByRoomType(id);
    }
    @RequestMapping(value = "/getLiveInNum/room/price")
    public List<DataVO> getLiveInNumByRoomPrice(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInNumByRoomPrice(id);
    }
    @RequestMapping(value = "/getLiveInNum/guest/type")
    public List<DataVO> getLiveInNumByguestType(HttpSession session){
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveInNumByGuestType(id);
    }
    @RequestMapping(value = "/getLiveInNum/guest/age")
    public List<DataVO> getLiveInNumByguestAge(HttpSession session) {
        int id= ControllerHelper.getUserIdFromSession(session);
        return hostelService.getLiveNumByGuestAge(id);
    }
//    @RequestMapping(value = "/getVacantRate/roomType")
//    public List<DataVO> getVacantRateByRoomType(HttpSession session){
//        int id= ControllerHelper.getUserIdFromSession(session);
//        return hostelService.getVacantRateByRoomType(id);
//    }


//======================= End Of LiveBill ============================================

//======================= PayBill ============================================
    @RequestMapping(value = "/getAllPayList")
    public List<PayBillVO> getAllPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(hostelService.getAllPayBills(id));
    }
    @RequestMapping(value = "/getRecentPayList")
    public List<PayBillVO> getRecentPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(hostelService.getRecentPayBills(id));
    }
    @RequestMapping(value = "/getRecentPayList/week")
    public List<PayBillVO> getRecentWeekPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(hostelService.getRecentWeekPayBills(id));
    }
    @RequestMapping(value = "/getRecentPayList/month")
    public List<PayBillVO> getRecentMonthPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(hostelService.getRecentMonthPayBills(id));
    }
    @RequestMapping(value = "/getRecentPayList/year")
    public List<PayBillVO> getRecentYearPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(hostelService.getRecentYearPayBills(id));
    }
    @RequestMapping(value = "/getUncountedPayList")
    public List<PayBillVO> getUncountedYearPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(hostelService.getUncountedPayBills(id));
    }
    @RequestMapping(value = "/getMoneyVipRate/year")
    public List<Object[]> getMoneyVipRateByYear(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getMoneyVipRateByYear(id);
    }
    @RequestMapping(value = "/getMoneyVipRate/month")
    public List<Object[]> getMoneyVipRateByMonth(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getMoneyVipRateByMonth(id);
    }
    @RequestMapping(value = "/getMoneyVipRate/week")
    public List<Object[]> getMoneyVipRateByWeek(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getMoneyVipRateByWeek(id);
    }
    @RequestMapping(value = "/getIncome/today")
    public double getIncomeToday(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getIncomeToday(id);
    }

    @RequestMapping(value = "/getIncomeAvg/today")
    public double getIncomeAvgToday(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getIncomeAvgToday(id);
    }
    @RequestMapping(value = "/getIncomeAvg/year")
    public List<DataVO> getIncomeAvgByYear(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getIncomeAvgByYear(id);
    }
    @RequestMapping(value = "/getIncomeAvg/month")
    public List<DataVO> getIncomeAvgByMonth(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getIncomeAvgByMonth(id);
    }
    @RequestMapping(value = "/getIncomeAvg/week")
    public List<DataVO> getIncomeAvgByWeek(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return hostelService.getIncomeAvgByWeek(id);
    }



    //======================= End Of PayBill ============================================
    @RequestMapping(value = "/getMoneyRecord")
    public List<MoneyRecordVO> getMoneyRecord(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return MoneyRecordVO.entityToVO_hostel(hostelService.getAllMoneyRecords(id));

    }



    @RequestMapping(value = "/getIncome",produces = "text/html;charset=UTF-8")
    public String getIncome(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        double ans=hostelService.getIncome(id);
        System.out.print("In CONTROLLER ! getValue= "+ans);
        return ans+"";
    }
}
