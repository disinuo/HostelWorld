/**
 * Created by disinuo on 17/3/3.
 */
package nju.edu.hostel.controller;
import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.User;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.util.DisPatcher;
import nju.edu.hostel.vo.OnLineUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static nju.edu.hostel.util.Constants.ROLE_HOSTEL;
import static nju.edu.hostel.util.Constants.ROLE_MANAGER;
import static nju.edu.hostel.util.Constants.ROLE_VIP;

@Controller
@RequestMapping("/")
@SessionAttributes(types = {OnLineUserVO.class})
public class MainController {
    @RequestMapping(value = "/" ,method=RequestMethod.GET)
    public ModelAndView showHomePage(@ModelAttribute OnLineUserVO onLineUserVO){
        try {
            System.out.print("用户已登录");
            return DisPatcher.roleToHomePage(onLineUserVO.getType());

        }catch (Exception e){
            return new ModelAndView("redirect:/login");
        }
//        if(onLineUserVO!=null){
//            System.out.print("用户已登录");
//            return DisPatcher.roleToHomePage(onLineUserVO.getType());
//
//        }else {
//        }
    }

}