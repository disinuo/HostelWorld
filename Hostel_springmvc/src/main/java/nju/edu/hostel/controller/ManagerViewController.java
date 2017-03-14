package nju.edu.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/boss")
public class ManagerViewController {
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ModelAndView showCountPage(){
        return new ModelAndView("boss/countPage");
    }
    @RequestMapping(value = "/checkRequest")
    public ModelAndView showRequestsPage(){
        return new ModelAndView("boss/requestsPage");
    }
    @RequestMapping(value="/analyse/hostel")
    public ModelAndView showAnalyseHostelsPage(){
        return new ModelAndView("boss/analyseHostelsPage");
    }
    @RequestMapping(value="/analyse/vip")
    public ModelAndView showAnalyseVipsPage(){
        return new ModelAndView("boss/analyseVipsPage");
    }
    @RequestMapping(value="/analyse/company")
    public ModelAndView showAnalyseCompanyPage(){
        return new ModelAndView("boss/analyseCompanyPage");
    }
    @RequestMapping(value="/analyse/hostel/liveBills")
    public ModelAndView showAnalyseHostelsLiveBillsPage(){
        return new ModelAndView("boss/hostelLiveBillsPage");
    }


}
