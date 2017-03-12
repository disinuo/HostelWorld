package nju.edu.hostel.controller;

import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.vo.output.BookBillVO;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.output.VipVO;
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
@RequestMapping("/data/vip")
@ResponseBody
public class VipDataController {
    @Autowired
    VIPService vipService;
    @Autowired
    HostelService hostelService;
    @Autowired
    UserDao userDao;
    @RequestMapping(value = "/getBookList" ,method = RequestMethod.POST)
    public List<BookBillVO> getBookList(HttpSession session){
        System.out.println("/getBookList: VipDataController---getBookList ");
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(vipService.getAllBookBills(id));

    }


}
