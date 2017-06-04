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

import javax.servlet.http.HttpSession;
import java.util.List;

import static nju.edu.hostel.util.Constants.ROLE_VIP;

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
    public ModelAndView showHostels(HttpSession session){
         ModelAndView model=checkRole(session);
         return model==null?( new ModelAndView("vip/hostelListPage")):model;
    }

    @RequestMapping(value = "/rooms")
    public ModelAndView showValidRooms(HttpSession session){
         ModelAndView model=checkRole(session);
         return model==null?( new ModelAndView("vip/hostelDetailPage")):model;
    }

    @RequestMapping(value = "/moneyRecord")
    public ModelAndView showmoneyRecord(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/moneyRecordPage")):model;
    }
    @RequestMapping(value = "/analyze")
    public ModelAndView showAnalyze(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/analyzePage")):model;
    }


    @RequestMapping(value = "/bookList")
    public ModelAndView showBookList(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/bookListPage")):model;
    }

    @RequestMapping(value = "/payList")
    public ModelAndView showPayList(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/payListPage")):model;
    }
    @RequestMapping(value = "/liveList")
    public ModelAndView showLiveList(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/liveListPage")):model;
    }
    @RequestMapping(value = "/book" ,method = RequestMethod.GET)
    public ModelAndView showBookPage(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/bookPage")):model;
    }

    @RequestMapping(value = "/topUp",method = RequestMethod.GET)
    public ModelAndView showTopUpPage(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/topUpPage")):model;
    }
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public ModelAndView showModifyInfoPage(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/modifyInfoPage")):model;
    }
    @RequestMapping(value = "/convert",method = RequestMethod.GET)
    public ModelAndView showConvertScoreToMoneyPage(HttpSession session){
         ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("vip/convertScorePage")):model;
    }

    public ModelAndView checkRole(HttpSession session){
        OnLineUserVO user=(OnLineUserVO) session.getAttribute("user");
        if(user==null||!user.getType().equals(ROLE_VIP)){
            return new ModelAndView("redirect:/login");
        }else{
            return null;
        }
    }
}
