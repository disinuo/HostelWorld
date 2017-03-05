package nju.edu.hostel.controller;

import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.User;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.vo.OnLineUserVO;
import nju.edu.hostel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "/login")
    public ModelAndView showLoginPage( ModelMap model) {
        return new ModelAndView("login","command",new UserVO());
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(@ModelAttribute UserVO userVO,ModelMap model){
        System.out.println(userVO.getUserName());
        System.out.println(userVO.getPassword());
        User user=userService.login(userVO.getUserName(),userVO.getPassword());
        if(user==null){
            return new ModelAndView("notExist","command",user);

        }else {//user exists
            OnLineUserVO onLineUserVO=new OnLineUserVO(user.getId(),user.getUserName(),user.getType());
            model.addAttribute(onLineUserVO);
            return DisPatcher.roleToHomePage(onLineUserVO.getType());

        }

    }
}
