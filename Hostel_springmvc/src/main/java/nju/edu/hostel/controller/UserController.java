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
    public ModelAndView handleLoginRequest(HttpSession session,HttpServletResponse response,UserVO userVO){
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
        System.out.println("in /checkUser post");
        ResultMessage msg=userService.checkUser(name);
        System.out.println(msg);
        return msg.toShow();
    }
    @RequestMapping(value = "/checkPassword",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkPassword(UserVO userVO){
        System.out.println("in /checkPassword post");

        String name=userVO.getUserName();
        String password=userVO.getPassword();
        System.out.println(name);
        System.out.println(password);
        ResultMessage msg=userService.checkUser(name,password);
        System.out.println(msg);
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
        return new ModelAndView("register","user",new UserVO());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView handleRegisterRequest (@ModelAttribute("user") UserVO userVO, RedirectAttributes attr, ModelMap model){
        ResultMessage rmsg=userService.register(userVO.getUserName(),userVO.getPassword());
        if(rmsg==ResultMessage.FAILURE) {
            System.out.println("注册失败");
            return new ModelAndView("404");
        }else {
            model.addAttribute("message",rmsg.toShow());
            return new ModelAndView("register");
        }
    }

    @RequestMapping(value = "/user/getInfo")
    @ResponseBody
    public UserVO_output getUserInfo(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        System.out.println("in User controller!!! userMoney= "+userService.getById(id).getBankMoney());
        return new UserVO_output(userService.getById(id));
    }




    public  ModelAndView roleToHomePage(HttpSession session,OnLineUserVO user){
        int id=user.getId();
        switch (user.getType()){
            case ROLE_VIP:
                vipService.init(id);//TODO 这个方法没测试过
                Vip vip=vipService.getById(id);
                VipVO vipVO=new VipVO(vip);
                session.setAttribute("vip",vipVO);
                return new ModelAndView("redirect:/vip/hostels");
            case ROLE_HOSTEL:
                ResultMessage initMsg=hostelService.init(id);
                Hostel hostel=hostelService.getById(id);
                HostelVO hostelVO=new HostelVO(hostel);
                session.setAttribute("hostel",hostelVO);
                return new ModelAndView("redirect:/hostel/rooms");
            case ROLE_MANAGER:
                return new ModelAndView("redirect:/boss/analyse/hostel");
            default: return new ModelAndView("404");
        }
    }
}
