package nju.edu.hostel.controller;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.User;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.util.ResultMessage;
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
        List<User> users=userService.getByType(ROLE_HOSTEL);
        for(User user:users){
            System.out.println(user.getId()+" "+user.getUserName()+" "+user.getPassword());
        }
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
            return DisPatcher.roleToHomePage(onLineUserVO.getType());
        }

    }
}
