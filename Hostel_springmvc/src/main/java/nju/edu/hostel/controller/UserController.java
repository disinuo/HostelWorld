package nju.edu.hostel.controller;

import nju.edu.hostel.model.User;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import static nju.edu.hostel.util.Constants.*;

/**
 * Created by disinuo on 17/3/4.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("index","user",new UserVO());
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView index(@ModelAttribute("user") UserVO userVO){
        System.out.println(userVO.getUserName());
        System.out.println(userVO.getPassword());
        User user=userService.login(userVO.getUserName(),userVO.getPassword());

        if(user==null){
            return new ModelAndView("notExist");

        }else {
            String String=user.getType();
            switch (String){
                case ROLE_VIP:return new ModelAndView("vip/index");
                case ROLE_HOSTEL:return new ModelAndView("hostel/index");
                case ROLE_MANAGER:return new ModelAndView("manager/index");
                default: return new ModelAndView("notExist");
            }
        }

    }

}
