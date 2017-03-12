package nju.edu.hostel.util;

import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.web.servlet.ModelAndView;

import static nju.edu.hostel.util.Constants.ROLE_HOSTEL;
import static nju.edu.hostel.util.Constants.ROLE_MANAGER;
import static nju.edu.hostel.util.Constants.ROLE_VIP;

/**
 * Created by disinuo on 17/3/5.
 */

public class DisPatcher {
    public static ModelAndView roleToHomePage(String role){
        switch (role){
            case ROLE_VIP:
                return new ModelAndView("redirect:/vip/hostels");
            case ROLE_HOSTEL:return new ModelAndView("hostel/index");
            case ROLE_MANAGER:return new ModelAndView("manager/index");
            default: return new ModelAndView("404");
        }
    }

    /**
     * 未登录则返回登录界面的ModelAndView
     * 已登录则返回null
     * @param user
     * @return
     */
    public static ModelAndView checkLoggedIn(OnLineUserVO user){
        if(user==null) return new ModelAndView("redirect:/login");
        else return null;
    }
}
