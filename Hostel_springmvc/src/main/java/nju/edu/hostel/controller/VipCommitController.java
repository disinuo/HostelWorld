package nju.edu.hostel.controller;

import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.BookVO;
import nju.edu.hostel.vo.input.TopUpVO;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
    public ModelAndView book(@ModelAttribute("bookBill")BookVO bookVO,
                             HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        bookVO.setVipId(user.getId());
        ResultMessage msg=vipService.book(bookVO);
        return new ModelAndView("redirect:/vip/bookList");
    }
    @RequestMapping(value = "/unbook",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String unBook(@RequestParam("bookListId")int bookListId,HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        return vipService.unbook(user.getId(),bookListId).toShow();
    }
    @RequestMapping(value = "/topUp",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String topUp(HttpSession session, TopUpVO topUpVO){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        System.out.println("在充值！ money="+topUpVO.getMoney()+" 密码是 "+topUpVO.getBankPassword());
        ResultMessage resMssg=vipService.topUp(topUpVO.getMoney(),user.getId(),topUpVO.getBankPassword());
        System.out.println(resMssg);
        return resMssg.toShow();
    }
    @RequestMapping(value = "/stopCard",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String stop(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=vipService.stop(user.getId());
        return msg.toShow();
    }

    @RequestMapping(value = "/convert",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String convertScore(HttpSession session,
                               @RequestParam("score")double score){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        return vipService.scoreToMoney(user.getId(),score).toShow();
    }
    @RequestMapping(value = "/modify",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyInfo(HttpSession session,String name,String idCard){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        return vipService.update(user.getId(),name,idCard).toShow();
    }
}
