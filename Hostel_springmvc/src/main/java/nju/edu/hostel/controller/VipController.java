package nju.edu.hostel.controller;

import nju.edu.hostel.model.*;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.vo.OnLineUserVO;
import nju.edu.hostel.vo.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by disinuo on 17/3/5.
 */
@Controller
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
@RequestMapping("/vip")
public class VipController {
    @Autowired
    VIPService vipService;

    ModelAndView resOfCheckLoggedIn;

    private ModelAndView checkLoggedIn(OnLineUserVO user){
        if(resOfCheckLoggedIn==null){
            resOfCheckLoggedIn=DisPatcher.checkLoggedIn(user);
        }
        return resOfCheckLoggedIn;
    }


    @RequestMapping(value = "/bookList")
    public ModelAndView showBookList(@ModelAttribute("vip") VipVO vipVO,
                                     ModelMap model){
        System.out.println("/BookList: VIPController---showBookList ");
//        ModelAndView mo= checkLoggedIn(user);
//        if(mo==null){
            int id=vipVO.getId();
            List<BookBill> bookBills=vipService.getAllBookBills(id);
            model.addAttribute("bookBills",bookBills);
            return new ModelAndView("vip/bookListPage");
//        }else return mo;

    }
    @RequestMapping(value = "/rooms")
    public ModelAndView showRooms(@ModelAttribute OnLineUserVO user,
                                  ModelMap model){
        ModelAndView mo= checkLoggedIn(user);
        if(mo==null){
            int id=user.getId();
            Vip vip=vipService.getById(id);
            VipVO vipVO=new VipVO(vip);
            List<Room> rooms=vipService.getAllRooms();
            model.addAttribute("rooms",rooms);
            model.addAttribute("vip",vipVO);
            return new ModelAndView("vip/roomListPage");
        }else return mo;

    }

    @RequestMapping(value = "/payList")
    public ModelAndView showPayList(@ModelAttribute("vip") VipVO vipVO,
                                    ModelMap model){
        int id=vipVO.getId();
        List<PayBill> payBills=vipService.getAllPayBills(id);
        model.addAttribute("payBills",payBills);
        return new ModelAndView("vip/payListPage");
    }
    @RequestMapping(value = "/liveList")
    public ModelAndView showLiveList(@ModelAttribute("vip") VipVO vipVO,
                                     ModelMap model){
        List<LiveBill> liveBills=vipService.getAllLiveBills(vipVO.getId());
        model.addAttribute("liveBills",liveBills);
        return new ModelAndView("vip/LiveListPage");
    }
}
