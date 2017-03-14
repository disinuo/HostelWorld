package nju.edu.hostel.controller;

import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import static nju.edu.hostel.util.Constants.ROLE_MANAGER;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/boss")
public class ManagerViewController {
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ModelAndView showCountPage(HttpSession session){
        ModelAndView model=checkRole(session);         
        return model==null?( new ModelAndView("boss/countPage")):model;
    }
    @RequestMapping(value = "/checkRequestOpen")
    public ModelAndView showRequestsOpenPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/requestsOpenPage")):model;
    }
    @RequestMapping(value = "/checkRequestModify")
    public ModelAndView showRequestsModifyPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/requestsModifyPage")):model;
    }
    @RequestMapping(value="/analyse/hostel")
    public ModelAndView showAnalyseHostelsPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/analyseHostelsPage")):model;
    }
    @RequestMapping(value="/analyse/vip")
    public ModelAndView showAnalyseVipsPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/analyseVipsPage")):model;
    }
    @RequestMapping(value="/analyse/company")
    public ModelAndView showAnalyseCompanyPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/analyseCompanyPage")):model;
    }
    @RequestMapping(value="/analyse/hostel/liveBills")
    public ModelAndView showAnalyseHostelsLiveBillsPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/hostelDetailPage")):model;
    }
    @RequestMapping(value="/analyse/vip/detail")
    public ModelAndView showAnalyseVipsDetailPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("boss/vipDetailPage")):model;
    }
    public ModelAndView checkRole(HttpSession session){
        OnLineUserVO user=(OnLineUserVO) session.getAttribute("user");
        if(user==null||!user.getType().equals(ROLE_MANAGER)){
            return new ModelAndView("redirect:/login");
        }else{
            return null;
        }
    }
}
