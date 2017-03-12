package nju.edu.hostel.controller;

import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.vo.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by disinuo on 17/3/12.
 */
@Controller
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
@RequestMapping(value = "/data/vip")//,produces = "text/html;charset=UTF-8")
@ResponseBody
public class VipDataController {
    @Autowired
    VIPService vipService;
    @RequestMapping(value = "/getBookList")
    public List<BookBillVO> getBookList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getAllBookBills(id));
    }

    @RequestMapping(value = "/getHostelList")
    public List<HostelVO> getHostelList(){
        return HostelVO.entityToVO(vipService.getAllPermittedHostels());
    }

    @RequestMapping(value = "/getLiveList")
    public List<LiveBillVO> getLiveList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return LiveBillVO.entityToVO(vipService.getAllLiveBills(id));
    }
    @RequestMapping(value = "/getPayList")
    public List<PayBillVO> getPayList(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return PayBillVO.entityToVO(vipService.getAllPayBills(id));
    }

}
