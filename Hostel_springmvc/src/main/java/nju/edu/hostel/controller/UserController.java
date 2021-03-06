package nju.edu.hostel.controller;

import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.output.HostelVO;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.input.UserVO;
import nju.edu.hostel.vo.output.UserVO_output;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.*;

import static nju.edu.hostel.util.Constants.*;
/**
 * Created by disinuo on 17/3/4.
 */
@Controller
@RequestMapping(value = "")
public class UserController {
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
        session.removeAttribute("user");
        return new ModelAndView("redirect:/login");
    }
    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public ModelAndView showLoginPage( ModelMap model) {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(HttpSession session,UserVO userVO){
        String name=userVO.getUserName();
        String password=userVO.getPassword();
        ResultMessage msg=userService.checkUser(name,password);
        if(msg!=ResultMessage.SUCCESS){
            return new ModelAndView("/login");
        }else {//验证通过
            User user=userService.login(name,password);
            OnLineUserVO onLineUserVO=new OnLineUserVO(user.getId(),user.getUserName(),user.getType());
            session.setAttribute("user",onLineUserVO);
//            Cookie cookie=new Cookie("userId",user.getId()+"");
//            response.addCookie(cookie);
            if(user.getType().equals(ROLE_VIP)){
                vipService.init(user.getId());
            }
            return roleToHomePage(session,onLineUserVO);
        }
    }
    @RequestMapping(value = "/checkUser" ,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkUser(HttpServletResponse response, String name){
        ResultMessage msg=userService.checkUser(name);
        System.out.println(msg);
        return msg.toShow();
    }
    @RequestMapping(value = "/checkPassword",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkPassword(UserVO userVO){

        String name=userVO.getUserName();
        String password=userVO.getPassword();
        ResultMessage msg=userService.checkUser(name,password);
        return msg.toShow();
    }
    @RequestMapping("")
    public ModelAndView showMainPage(HttpSession session){
        OnLineUserVO user=(OnLineUserVO) session.getAttribute("user");
        if(user==null) return new ModelAndView("redirect:/login");
        else {
            return roleToHomePage(session,user);
        }
    }

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public ModelAndView showRegisterPage(ModelMap model){
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/registerVIP", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String handleRegisterVIPRequest (@ModelAttribute("user") UserVO userVO, RedirectAttributes attr, ModelMap model){
        System.out.println("Registering vip! "+userVO.getUserName());
        ResultMessage rmsg=userService.registerVIP(userVO.getUserName(),userVO.getPassword());
        System.out.println(" "+rmsg);
        if(rmsg==ResultMessage.FAILURE) {
            return "注册失败";
        }else {
            return rmsg.toShow();
        }
    }
    @RequestMapping(value = "/registerHostel", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String handleRegisterHostelRequest (@ModelAttribute("user") UserVO userVO, RedirectAttributes attr, ModelMap model){
        ResultMessage rmsg=userService.registerHostel(userVO.getUserName(),userVO.getPassword());
        if(rmsg==ResultMessage.FAILURE) {
           return "注册失败";
        }else {
            return rmsg.toShow();
        }
    }

    @RequestMapping(value = "/user/getInfo")
    @ResponseBody
    public UserVO_output getUserInfo(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return new UserVO_output(userService.getById(id));
    }




    public  ModelAndView roleToHomePage(HttpSession session,OnLineUserVO user){
        int id=user.getId();
        switch (user.getType()){
            case ROLE_VIP:
                vipService.init(id);
                return new ModelAndView("redirect:/vip/hostels");
            case ROLE_HOSTEL:
                ResultMessage initMsg=hostelService.init(id);
                Hostel hostel=hostelService.getById(id);
                HostelVO hostelVO=new HostelVO(hostel);
                session.setAttribute("hostel",hostelVO);
                return new ModelAndView("redirect:/hostel/rooms");
            case ROLE_MANAGER:
                return new ModelAndView("redirect:/boss/analyse/company");
            default: return new ModelAndView("404");
        }
    }
}
