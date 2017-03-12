package nju.edu.hostel.controller;

import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.input.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
    @Autowired
    ManagerService managerService;
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        //TODO 现在是假的注销！
        session.removeAttribute("vip");
        session.removeAttribute("user");
        return new ModelAndView("login","command",new UserVO());
    }
    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public ModelAndView showLoginPage( ModelMap model) {
        return new ModelAndView("login","user",new UserVO());
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(@ModelAttribute("user") UserVO userVO,ModelMap model){
        String name=userVO.getUserName();
        String password=userVO.getPassword();
        System.out.println(name);
        System.out.println(password);
        ResultMessage msg=userService.checkUser(name,password);
        if(msg!=ResultMessage.SUCCESS){
            model.addAttribute("message",msg.toShow());
            return new ModelAndView("/login");
        }else {//验证通过
            User user=userService.login(name,password);
            OnLineUserVO onLineUserVO=new OnLineUserVO(user.getId(),user.getUserName(),user.getType());
            model.addAttribute("user",onLineUserVO);
            if(user.getType().equals(ROLE_VIP)){
                vipService.init(user.getId());
            }
            return DisPatcher.roleToHomePage(onLineUserVO.getType());
        }
    }
}
