package nju.edu.hostel.controller;

import static nju.edu.hostel.util.Constants.*;
import nju.edu.hostel.vo.output.OnLineUserVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@SessionAttributes(types = {VipVO.class,OnLineUserVO.class})
@RequestMapping(value = "/constants",produces = "text/html;charset=UTF-8")
@ResponseBody
public class ConstantsDataController {
    @RequestMapping(value = "/RATE_SCORE_TO_MONEY")
    public String getRateScoreToMoney(){return RATE_SCORE_TO_MONEY+"";}
    //TODO 返回常量
}
