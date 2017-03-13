package nju.edu.hostel.controller;

import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static nju.edu.hostel.util.Constants.ROLE_HOSTEL;
import static nju.edu.hostel.util.Constants.ROLE_MANAGER;
import static nju.edu.hostel.util.Constants.ROLE_VIP;

/**
 * Created by disinuo on 17/3/5.
 */

public class DisPatcher {


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
