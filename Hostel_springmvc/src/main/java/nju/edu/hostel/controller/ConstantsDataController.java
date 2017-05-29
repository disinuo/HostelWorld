package nju.edu.hostel.controller;

import static nju.edu.hostel.util.Constants.*;

import nju.edu.hostel.model.City;
import nju.edu.hostel.model.Province;
import nju.edu.hostel.service.OtherService;
import nju.edu.hostel.vo.output.CityVO;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.output.ProvinceVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping(value = "/constants")
@ResponseBody
public class ConstantsDataController {
    @RequestMapping(value = "/RATE_SCORE_TO_MONEY",produces = "text/html;charset=UTF-8")
    public String getRateScoreToMoney(){return RATE_SCORE_TO_MONEY+"";}
    //TODO 返回常量

    @RequestMapping(value = "/provinces")
    public List<Province> getAllProvinces(){
        return otherService.getAllProvince();
    }
    @RequestMapping(value = "/cities")
    public List<City> getCitiesByProvince(int provinceId){

        return otherService.getCitiesByProvince(provinceId);
    }


    @Autowired
    OtherService otherService;
}
