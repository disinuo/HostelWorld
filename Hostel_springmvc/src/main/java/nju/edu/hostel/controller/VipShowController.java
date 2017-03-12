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
public class VipShowController {
    @Autowired
    VIPService vipService;
    @Autowired
    HostelService hostelService;
    @Autowired
    UserDao userDao;


    @RequestMapping(value = "/bookList")
    public ModelAndView showBookList(@ModelAttribute("vip") VipVO vipVO,
                                     ModelMap model){
        System.out.println("/bookList: VIPShowController---showBookList ");
        return new ModelAndView("vip/bookListPage");
    }
    @RequestMapping(value = "/hostels")
    public ModelAndView showRooms(@ModelAttribute("user") OnLineUserVO user,
                                  ModelMap model){
        int id=user.getId();
        Vip vip=vipService.getById(id);
        VipVO vipVO=new VipVO(vip);
        List<Hostel>  hostels=vipService.getAllPermittedHostels();
        model.addAttribute("hostels",hostels);
        model.addAttribute("vip",vipVO);
        return new ModelAndView("vip/hostelListPage");
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
//TODO
//        Test_Table test=new Test_Table();
//        test.setName("testaaa");
//        test.setBankMoney(0);
//        test.setId(666);
//        int id=testTableDao.addAndGetId(test);
//        User user=new User();
//        user.setUserName("GOYaaa");
//        user.setId(666);
//        user.setBankId("1111111111110000666");
//        userDao.add(user);
//        System.out.println("VipController--topUp---try test.add  ID= "+id);
        model.addAttribute("topUp",new TopUpVO());
        return new ModelAndView("vip/topUpPage",model);
    }


    @RequestMapping(value = "/topUp",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap topUp(@ModelAttribute("vip") VipVO vipVO,
                           @ModelAttribute("topUp")TopUpVO topUpVO,
                                     ModelMap model){
        ResultMessage resMssg=vipService.topUp(topUpVO.getMoney(),vipVO.getId(),topUpVO.getBankPassword());
        if(resMssg==ResultMessage.SUCCESS){
            model.addAttribute("message","充值成功");
        }else {
            model.addAttribute("message","充值失败");
        }
        return model;
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
//        bookBill.setVip(vipService.get(vipVO.getId()));
//        ResultMessage resMssg=vipService.book(bookBill);
//        model.addAttribute("message",resMssg);
        return new ModelAndView("redirect:/vip/bookList",model);
    }
}
