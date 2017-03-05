package nju.edu.hostel.controller;

import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.Vip;
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
@RequestMapping("/vip")
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
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
    public ModelAndView showBookList(@ModelAttribute OnLineUserVO user, ModelMap model){
        System.out.println("/BookList: VIPController---showBookList ");
        ModelAndView mo= checkLoggedIn(user);
        if(mo==null){
            int id=user.getId();
            System.out.println(user.getId());
            Vip vip=vipService.getById(id);

            VipVO vipVO=new VipVO(vip);
            List<BookBill> bookBills=vipService.getAllBookBills(id);
            model.addAttribute("vip",vipVO);
            model.addAttribute("bookBills",bookBills);
            return new ModelAndView("vip/bookListPage");
        }else return mo;

    }
    @RequestMapping(value = "/rooms")
    public ModelAndView showRooms(@ModelAttribute OnLineUserVO user, ModelMap model){
        ModelAndView mo= checkLoggedIn(user);
        if(mo==null){
            Vip vip=vipService.getById(user.getId());
            return new ModelAndView("vip/roomListPage");
        }else return mo;

    }

    @RequestMapping(value = "/payList")
    public ModelAndView showPayList(){

        return new ModelAndView("vip/payListPage");
    }
    @RequestMapping(value = "/liveList")
    public ModelAndView showLiveList(){
        return new ModelAndView("vip/LiveListPage");
    }
}
