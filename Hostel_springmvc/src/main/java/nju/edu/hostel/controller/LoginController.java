package nju.edu.hostel.controller;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.Room;
import nju.edu.hostel.model.User;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.BookVO;
import nju.edu.hostel.vo.OnLineUserVO;
import nju.edu.hostel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import static nju.edu.hostel.util.Constants.ROLE_HOSTEL;
import static nju.edu.hostel.util.Constants.ROLE_MANAGER;
import static nju.edu.hostel.util.Constants.ROLE_VIP;

/**
 * Created by disinuo on 17/3/4.
 */
@Controller
@SessionAttributes(types = {OnLineUserVO.class})
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    VIPService vipService;
    @Autowired
    HostelService hostelService;
    @RequestMapping(value = "/login")
    public ModelAndView showLoginPage( ModelMap model) {
//        Vip vip=vipService.getById(1000000);
//        BookVO bookVO=new BookVO();
//        bookVO.setLiveInDate("2017-03-28");
//        bookVO.setRoomId(100002);
//        bookVO.setVipId(1000001);
//        Room room=hostelService.getRoomById(100001);


//        List<BookBill> bookBills=vipService.getAllBookBills(1000000);
        ResultMessage msg=hostelService.requestManager(9990004);
        System.out.println(msg);
//        resMssg=vipService.unbook(1000000,bookBills.get(3).getId());
//        System.out.println(resMssg);
//        resMssg=vipService.topUp(200,1000003,"bankroot");
//        System.out.println(resMssg);
//        resMssg=vipService.topUp(1000,1000002,"bankroot");
//        System.out.println(resMssg);
//        resMssg=vipService.topUp(200,1000004,"bankroot");
//        System.out.println(resMssg);
//        resMssg=userService.modifyBankMoneyTo(1000001,700);
//        System.out.println(resMssg);
//        List<User> users=userService.getByType(ROLE_HOSTEL);
//        for(User user:users){
//            System.out.println(user.getId()+" "+user.getUserName()+" "+user.getPassword());
//        }
        return new ModelAndView("login","command",new UserVO());
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(@ModelAttribute UserVO userVO,ModelMap model){
        String name=userVO.getUserName();
        String password=userVO.getPassword();
        System.out.println(name);
        System.out.println(password);
        ResultMessage resMssg=userService.checkUser(name,password);
        if(resMssg==ResultMessage.NOT_EXIST){
            return new ModelAndView("notExist");
        }else if(resMssg==ResultMessage.WRONG_PASSWORD){
            System.out.print("密码错误!");
            return new ModelAndView("login","command",userVO);
        }else {//验证通过
            User user=userService.login(name,password);
            OnLineUserVO onLineUserVO=new OnLineUserVO(user.getId(),user.getUserName(),user.getType());
            model.addAttribute(onLineUserVO);
            if(user.getType().equals(ROLE_VIP)){
                vipService.init(user.getId());
            }
            return DisPatcher.roleToHomePage(onLineUserVO.getType());
        }

    }
}
