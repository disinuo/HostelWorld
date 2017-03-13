/**
 * Created by disinuo on 17/3/3.
 */
package nju.edu.hostel.controller;
import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes(types = {OnLineUserVO.class})
public class MainController {
//    @RequestMapping(value = "/" ,method=RequestMethod.GET)
//    public ModelAndView showHomePage(@ModelAttribute("user") OnLineUserVO onLineUserVO){
//        try {
//            System.out.print("用户已登录");
//            return DisPatcher.roleToHomePage(onLineUserVO);
//
//        }catch (Exception e){
//            return new ModelAndView("redirect:/login");
//        }
//        if(onLineUserVO!=null){
//            System.out.print("用户已登录");
//            return DisPatcher.roleToHomePage(onLineUserVO.getType());
//
//        }else {
//        }
//    }

}