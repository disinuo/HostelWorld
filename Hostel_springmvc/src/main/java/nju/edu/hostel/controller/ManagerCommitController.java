package nju.edu.hostel.controller;

import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/boss")
@ResponseBody
public class ManagerCommitController {
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/updateOpenRequest",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String updateOpenRequest(int id,String state){
        ResultMessage msg=managerService.updateOpenRequest(id,state);
        return msg.toShow();
    }
    @RequestMapping(value = "/updateModifyRequest",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String updateModifyRequest(int id,String state){
        ResultMessage msg=managerService.updateModifyRequest(id,state);
        return msg.toShow();
    }
    @RequestMapping(value = "/count",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String count(HttpSession session,String bankPassword){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=managerService.count(user.getId(),bankPassword);
        return msg.toShow();
    }
}
