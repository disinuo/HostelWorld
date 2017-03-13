package nju.edu.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/manager")
public class ManagerViewController {
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ModelAndView showCountPage(){
        return new ModelAndView("manager/countPage");
    }
    @RequestMapping(value = "/checkRequest")
    public ModelAndView showRequestsPage(){
        return new ModelAndView("manager/requestsPage");
    }
    @RequestMapping(value="/analyse/hostel")
    public ModelAndView showAnalyseHostelsPage(){
        return new ModelAndView("manager/analyseHostelsPage");
    }
    @RequestMapping(value="/analyse/vip")
    public ModelAndView showAnalyseVipsPage(){
        return new ModelAndView("manager/analyseVipsPage");
    }
    @RequestMapping(value="/analyse/company")
    public ModelAndView showAnalyseCompanyPage(){
        return new ModelAndView("manager/analyseCompanyPage");
    }


}
