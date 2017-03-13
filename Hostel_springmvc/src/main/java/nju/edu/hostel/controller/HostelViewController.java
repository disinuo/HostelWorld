package nju.edu.hostel.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.Room;
import nju.edu.hostel.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by disinuo on 17/3/5.
 */
@Controller
@RequestMapping("/hostel")
public class HostelViewController {
    @RequestMapping(value = "/rooms")
    public ModelAndView showRooms(){
        return new ModelAndView("hostel/roomListPage");
    }
    @RequestMapping(value = "/modifyInfo")
    public ModelAndView showUpdatePage(){
        return new ModelAndView("hostel/modifyInfoPage");
    }

    @RequestMapping(value = "/enrollPay",method = RequestMethod.GET)
    public ModelAndView showEnrollPayPage(){
        return new ModelAndView("hostel/enrollPayPage");
    }
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public ModelAndView showPayPage(){
        return new ModelAndView("hostel/payPage");
    }
    @RequestMapping(value = "/liveIn",method = RequestMethod.GET)
    public ModelAndView showLiveInPage(){
        return new ModelAndView("hostel/liveInPage");
    }
    @RequestMapping(value = "/depart",method = RequestMethod.GET)
    public ModelAndView showDepartPage(){
        return new ModelAndView("hostel/liveOutPage");
    }
    @RequestMapping(value = "/addRoom",method = RequestMethod.GET)
    public ModelAndView showAddRoomPage(){
        return new ModelAndView("hostel/addRoomPage");
    }
    @RequestMapping(value = "/modifyRoom",method = RequestMethod.GET)
    public ModelAndView showModifyRoomPage(){
        return new ModelAndView("hostel/modifyRoomPage");
    }
}
