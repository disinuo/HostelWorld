package nju.edu.hostel.controller;

import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.input.TopUpVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by disinuo on 17/3/5.
 */
@Controller
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
@RequestMapping("/vip")
public class VipViewController {
    @Autowired
    VIPService vipService;
    @Autowired
    HostelService hostelService;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/hostels")
    public ModelAndView showHostels(@ModelAttribute("user") OnLineUserVO user,
                                    ModelMap model){
        return new ModelAndView("vip/hostelListPage");
    }

    @RequestMapping(value = "/rooms")
    public ModelAndView showValidRooms(){
        return new ModelAndView("vip/hostelDetailPage");
    }

    @RequestMapping(value = "/bookList")
    public ModelAndView showBookList(){
        return new ModelAndView("vip/bookListPage");
    }

    @RequestMapping(value = "/payList")
    public ModelAndView showPayList(){
//        int id=vipVO.getId();
//        List<PayBill> payBills=vipService.getAllPayBills(id);
//        model.addAttribute("payBills",payBills);
        return new ModelAndView("vip/payListPage");
    }
    @RequestMapping(value = "/liveList")
    public ModelAndView showLiveList(){
//        List<LiveBill> liveBills=vipService.getAllLiveBills(vipVO.getId());
//        model.addAttribute("liveBills",liveBills);
        return new ModelAndView("vip/liveListPage");
    }
    @RequestMapping(value = "/book" ,method = RequestMethod.GET)
    public ModelAndView showBookPage(){
        return new ModelAndView("vip/bookPage");
    }

    @RequestMapping(value = "/topUp",method = RequestMethod.GET)
    public ModelAndView showTopUpPage(){
        return new ModelAndView("vip/topUpPage");
    }
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView showModifyInfoPage(){
        return new ModelAndView("vip/modifyInfoPage");
    }
    @RequestMapping(value = "/convert",method = RequestMethod.GET)
    public ModelAndView showConvertScoreToMoneyPage(){
        return new ModelAndView("vip/convertScorePage");
    }

}
