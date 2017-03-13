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
            @ModelAttribute("vip")VipVO vipVO){
        bookVO.setVipId(vipVO.getId());
        ResultMessage msg=vipService.book(bookVO);
        return new ModelAndView("redirect:/vip/bookList");
    }
    @RequestMapping(value = "/unbook",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String unBook(@RequestParam("bookListId")int bookListId,
                         @ModelAttribute("vip")VipVO vipVO){
         return vipService.unbook(vipVO.getId(),bookListId).toShow();
    }
    @RequestMapping(value = "/topUp",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public ModelAndView topUp(@ModelAttribute("vip") VipVO vipVO,
                              ModelMap model,
                          TopUpVO topUpVO){
        ResultMessage resMssg=vipService.topUp(topUpVO.getMoney(),vipVO.getId(),topUpVO.getBankPassword());
        if(resMssg==ResultMessage.SUCCESS){
           model.addAttribute("message","充值成功");
        }else {
            model.addAttribute("message",resMssg.toShow());
        }
        return new ModelAndView("vip/topUpPage");
    }
    @RequestMapping(value = "/stopCard",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String stop(@ModelAttribute("vip") VipVO vipVO){
        ResultMessage msg=vipService.stop(vipVO.getId());
        return msg.toShow();
    }

    @RequestMapping(value = "/convert",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String convertScore(@ModelAttribute("vip") VipVO vipVO,
                               @RequestParam("score")double score){
        return vipService.scoreToMoney(vipVO.getId(),score).toShow();
    }
}
