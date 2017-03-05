package nju.edu.hostel.controller;

import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by disinuo on 17/3/4.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    VIPService vipService;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ModelAndView showRegisterPage(ModelMap model){
        return new ModelAndView("register","command",new UserVO());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView handleRegisterRequest (@ModelAttribute UserVO userVO, RedirectAttributes attr,ModelMap model){

        ResultMessage rmsg=userService.add(userVO.getUserName(),userVO.getPassword());
        if(rmsg==ResultMessage.SUCCESS){
            System.out.println("注册成功");
            attr.addFlashAttribute("userName",userVO.getUserName());
            attr.addFlashAttribute("password",userVO.getPassword());
            return new ModelAndView("redirect:/login");
        }else {
            System.out.println("注册失败");
            return new ModelAndView("notExist");
        }
    }
}
