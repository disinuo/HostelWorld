package nju.edu.hostel.controller;

import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.model.VipMoneyRecord;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.vo.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by disinuo on 17/3/12.
 */
@Controller
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
@RequestMapping(value = "/data/vip")//,produces = "text/html;charset=UTF-8")
@ResponseBody
public class VipDataController {
    private final String CREATE_DATE="createDate";
    private final String LIVEIN_DATE="liveInDate";
    private final String CHECKOUT_DATE="checkOutDate";
    @Autowired
    VIPService vipService;
    @RequestMapping(value = "/getAllBookList")
    public List<BookBillVO> getAllBookList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getAllBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList/week")
    public List<BookBillVO> getRecentWeekBookList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getRecentWeekBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList/month")
    public List<BookBillVO> getRecentMonthBookList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getRecentMonthBookBills(id));
    }
    @RequestMapping(value = "/getRecentBookList/year")
    public List<BookBillVO> getRecentYearBookList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getRecentYearBookBills(id));
    }

    @RequestMapping(value = "/getRecentBookList")
    public List<BookBillVO> getRecentBookList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getDefaultNumBookBills(id));
    }
    @RequestMapping(value = "/getBookList")
    public List<BookBillVO> getBookList(HttpSession session,String dateType,String start,String end){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        switch (dateType){
            case CREATE_DATE:
                return BookBillVO.entityToVO(vipService.getBookBills_createDate(id,start,end));
            case LIVEIN_DATE:
                return BookBillVO.entityToVO(vipService.getBookBills_liveInDate(id,start,end));
            case CHECKOUT_DATE:
                return BookBillVO.entityToVO(vipService.getBookBills_checkOutDate(id,start,end));
        }
        return null;
    }

    @RequestMapping(value = "/getHostelList")
    public List<HostelVO> getHostelList(){
        return HostelVO.entityToVO(vipService.getAllPermittedHostels());
    }

    @RequestMapping(value = "/getLiveList")
    public List<LiveBillVO> getLiveList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return LiveBillVO.entityToVO(vipService.getAllLiveBills(id));
    }
    @RequestMapping(value = "/getPayList")
    public List<PayBillVO> getPayList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(vipService.getAllPayBills(id));
    }
    @RequestMapping(value = "/getMoneyRecord")
    public List<MoneyRecordVO> getMoneyRecord(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return MoneyRecordVO.entityToVO_vip(vipService.getAllMoneyRecords(id));
    }

    @RequestMapping(value = "/getInfo")
    public VipVO getVipInfo(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return new VipVO(vipService.getById(id));
    }
}
