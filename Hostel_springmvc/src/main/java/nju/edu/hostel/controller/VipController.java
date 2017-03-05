package nju.edu.hostel.controller;

import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.OnLineUserVO;
import nju.edu.hostel.vo.TopUpVO;
import nju.edu.hostel.vo.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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
    @Autowired
    HostelService hostelService;
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
        System.out.println("/bookList: VIPController---showBookList ");
//        ModelAndView mo= checkLoggedIn(user);
//        if(mo==null){
            int id=vipVO.getId();
            List<BookBill> bookBills=vipService.getAllBookBills(id);
            model.addAttribute("bookBills",bookBills);
            return new ModelAndView("vip/bookListPage");
//        }else return mo;

    }
    @RequestMapping(value = "/hostels")
    public ModelAndView showRooms(@ModelAttribute OnLineUserVO user,
                                  ModelMap model){
        ModelAndView mo= checkLoggedIn(user);
        if(mo==null){
            int id=user.getId();
            Vip vip=vipService.getById(id);
            VipVO vipVO=new VipVO(vip);
            List<Hostel>  hostels=vipService.getAllPermittedHostels();
            model.addAttribute("hostels",hostels);
            model.addAttribute("vip",vipVO);
            return new ModelAndView("vip/hostelListPage");
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
        return new ModelAndView("vip/liveListPage");
    }

    @RequestMapping(value = "/topUp",method = RequestMethod.GET)
    public ModelAndView getTopUpPage(@ModelAttribute("vip") VipVO vipVO,
                                     ModelMap model){
        model.addAttribute("topUp",new TopUpVO());
        return new ModelAndView("vip/topUpPage",model);
    }
    @RequestMapping(value = "/topUp",method = RequestMethod.POST)
    public ModelAndView topUp(@ModelAttribute("vip") VipVO vipVO,
                           @ModelAttribute("topUp")TopUpVO topUpVO,
                                     ModelMap model){
        ResultMessage resMssg=vipService.topUp(topUpVO.getMoney(),vipVO.getId(),topUpVO.getBankPassword());
        if(resMssg==ResultMessage.SUCCESS){
            model.addAttribute("message","充值成功");
        }else {
            model.addAttribute("message","充值失败");
        }
        return new ModelAndView("vip/topUpPage",model);
    }

    @RequestMapping(value = "/book" ,method = RequestMethod.GET)
    public ModelAndView showBookPage(@RequestParam("roomId") int roomId,
                                     ModelMap model){
        Room room=hostelService.getRoomById(roomId);
        model.addAttribute("room",room);
        model.addAttribute("bookBill",new BookBill());
        return new ModelAndView("vip/bookPage",model);
    }

    @RequestMapping(value = "/book" ,method = RequestMethod.POST)
    public ModelAndView book(
//            @ModelAttribute("bookBill")BookBill bookBill,
//                             @ModelAttribute("room")Room room,
                             @ModelAttribute("vip")VipVO vipVO,
                                     ModelMap model){

//        bookBill.setHostel(room.getHostel());
//        bookBill.setCreateDate(new Date());
//        bookBill.setRoom(room);
//        bookBill.setVip(vipService.getById(vipVO.getId()));
//        ResultMessage resMssg=vipService.book(bookBill);
//        model.addAttribute("message",resMssg);
        return new ModelAndView("redirect:/vip/bookList",model);
    }
}
