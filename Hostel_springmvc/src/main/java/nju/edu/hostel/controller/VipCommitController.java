package nju.edu.hostel.controller;

import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.BookVO;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by disinuo on 17/3/12.
 */
@Controller
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
@RequestMapping("/vip")
public class VipCommitController {
    @Autowired
    VIPService vipService;

    @RequestMapping(value = "/book" ,method = RequestMethod.POST)
    public ModelAndView book(
            @ModelAttribute("bookBill")BookVO bookVO,
            @ModelAttribute("vip")VipVO vipVO
           ){
        System.out.println("/vip/book post roomId="+bookVO.getRoomId()+" 入："+
        bookVO.getLiveInDate()+" 出："+bookVO.getLiveOutDate());
        bookVO.setVipId(vipVO.getId());
        ResultMessage msg=vipService.book(bookVO);
        System.out.println(msg.toShow());

//        bookBill.setHostel(room.getHostel());
//        bookBill.setCreateDate(new Date());
//        bookBill.setRoom(room);
//        bookBill.setVip(vipService.get(vipVO.getId()));
//        ResultMessage resMssg=vipService.book(bookBill);
//        model.addAttribute("message",resMssg);
        return new ModelAndView("redirect:/vip/bookList");
    }
}
