package nju.edu.hostel.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.edu.hostel.model.BossMoneyRecord;
import nju.edu.hostel.model.RequestModify;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.vo.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/data/boss")
@ResponseBody
public class ManagerDataController {


    @RequestMapping(value = "/getVipNum/level")
    public JSONObject getViplList() {
        return managerService.getVipNumByLevel();
    }
    @RequestMapping(value = "/getVipSummary/city")
    public JSONObject getAllVipLiveNumByCity(){
        return managerService.getAllVipLiveNumByCity();
    }

    @RequestMapping(value = "/getSummaryNumOfAllHostels")
    public JSONObject getSummaryNumOfAllHostels() {
        return managerService.getSummaryNumOfAllHostels();
    }

    @RequestMapping(value = "/getSummaryNumByCity")
    public JSONObject getSummaryNumByCity() {
        return managerService.getSummaryNumByCity();
    }

    @RequestMapping(value = "/getOpenRequests")
    public List<RequestOpenVO> getOpenRequests() {
        return RequestOpenVO.entityToVO(managerService.getOpenRequests());
    }

    @RequestMapping(value = "/getModifyRequests")
    public List<RequestModifyVO> getModifyRequests() {
        return RequestModifyVO.entityToVO(managerService.getModifyRequests());
    }

    @RequestMapping(value = "/getHostelList")
    public List<HostelVO> getHostelList() {
        return HostelVO.entityToVO(hostelService.getAllPermittedHostels());
    }


    @RequestMapping(value = "/getMoneyRecord")
    public List<MoneyRecordVO> getMoneyRecord(HttpSession session) {
        return MoneyRecordVO.entityToVO_boss(managerService.getAllMoneyRecords());
    }

    @RequestMapping(value = "/hostel/getLiveList")
    public List<LiveBillVO> getHostelLveList(int hostelId) {
        return LiveBillVO.entityToVO(hostelService.getAllLiveBills(hostelId));
    }

    @RequestMapping(value = "/hostel/getTotalLiveInNum")
    public int getHostelTotalLiveNumber(int hostelId) {
        return hostelService.getTotalLiveInNum(hostelId);
    }

    @RequestMapping(value = "/hostel/getPresentLiveInNum")
    public int getHostelPresentLiveNumber(int hostelId) {
        return hostelService.getPresentLiveInNum(hostelId);
    }

    @RequestMapping(value = "/vip/getPayList")
    public List<PayBillVO> getPayList(int vipId) {
        return PayBillVO.entityToVO(vipService.getAllPayBills(vipId));
    }

    @RequestMapping(value = "/vip/getBookList")
    public List<BookBillVO> getBookList(int vipId) {
        return BookBillVO.entityToVO(vipService.getValidBookBills(vipId));
    }

    @Autowired
    ManagerService managerService;
    @Autowired
    HostelService hostelService;
    @Autowired
    VIPService vipService;
}
