package nju.edu.hostel.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Room;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static nju.edu.hostel.util.Constants.ROLE_HOSTEL;

/**
 * Created by disinuo on 17/3/5.
 */
@Controller
@RequestMapping("/hostel")
public class HostelViewController {
    @RequestMapping(value = "/rooms")
    public ModelAndView showRooms(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?(new ModelAndView("hostel/roomListPage")):model;
    }
    @RequestMapping(value = "/modifyInfo")
    public ModelAndView showUpdatePage(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?(new ModelAndView("hostel/modifyInfoPage")):model;
    }

    @RequestMapping(value = "/enrollPay",method = RequestMethod.GET)
    public ModelAndView showEnrollPayPage(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?( new ModelAndView("hostel/enrollPayPage")):model;
    }
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public ModelAndView showPayPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/payPage")):model;
    }
    @RequestMapping(value = "/liveIn",method = RequestMethod.GET)
    public ModelAndView showLiveInPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/liveInPage")):model;
    }
    @RequestMapping(value = "/depart",method = RequestMethod.GET)
    public ModelAndView showDepartPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/liveOutPage")):model;
    }
    @RequestMapping(value = "/addRoom",method = RequestMethod.GET)
    public ModelAndView showAddRoomPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/addRoomPage")):model;
    }
    @RequestMapping(value = "/modifyRoom",method = RequestMethod.GET)
    public ModelAndView showModifyRoomPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/modifyRoomPage")):model;
    }

    @RequestMapping(value="/bookBills")
    public ModelAndView showAnalyseHostelsPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/bookBillsPage")):model;
    }
    @RequestMapping(value="/payBills")
    public ModelAndView showAnalyseVipsPage(HttpSession session){
        ModelAndView model=checkRole(session);

        return model==null?( new ModelAndView("hostel/payBillsPage")):model;
    }
    @RequestMapping(value="/liveBills")
    public ModelAndView showAnalyseCompanyPage(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?( new ModelAndView("hostel/liveBillsPage")):model;
    }

    public ModelAndView checkRole(HttpSession session){
        OnLineUserVO user=(OnLineUserVO) session.getAttribute("user");
        if(user==null||!user.getType().equals(ROLE_HOSTEL)){
            return new ModelAndView("redirect:/login");
        }else{
            return null;
        }
    }

    @Autowired
    HostelService hostelService;
}
