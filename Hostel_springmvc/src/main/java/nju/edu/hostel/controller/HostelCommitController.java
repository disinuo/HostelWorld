package nju.edu.hostel.controller;

import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.LiveOutVO;
import nju.edu.hostel.vo.input.PayVO;
import nju.edu.hostel.vo.input.RoomVO_input;
import nju.edu.hostel.vo.output.HostelVO;
import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/hostel")
@ResponseBody
public class HostelCommitController {
    @Autowired
    HostelService hostelService;

    @RequestMapping(value = "/requestManager",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String handleOpenRequest(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.requestManager(user.getId());
        System.out.print("in /requestManager "+msg);
        return msg.toShow();
    }

    @RequestMapping(value = "/modifyInfo",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String handlModifyRequest(HttpSession session,String name,String address,String phone){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.update(user.getId(),name,address,phone);
        return msg.toShow();
    }

    @RequestMapping(value = "/enrollPay",method = RequestMethod.POST)
    public double enrollPay(PayVO payVO){
        return hostelService.enrollPay(payVO);
    }
    @RequestMapping(value = "/pay",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String pay(int vipId,double money){
        ResultMessage msg;
        if(vipId==0){
            msg=hostelService.unVipPay(money);
        }else {
            msg=hostelService.vipPay(vipId,money);
        }
        return msg.toShow();
    }
    @RequestMapping(value = "/liveIn",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String liveIn(LiveInVO liveInVO){
        System.out.print("in hostelCommitController liveIn:"+liveInVO.getUserRealName());
        ResultMessage msg=hostelService.liveIn(liveInVO);
        return msg.toShow();
    }
    @RequestMapping(value = "/depart",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String depart(LiveOutVO liveOutVO){
        ResultMessage msg=hostelService.depart(liveOutVO);
        return msg.toShow();
    }
    @RequestMapping(value = "/addRoom",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String addRoom(RoomVO_input roomVO, HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.addRoom(user.getId(),roomVO);
        return msg.toShow();
    }
    @RequestMapping(value = "/addRooms",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String addRooms(List<RoomVO_input> rooms, HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.addRoom(user.getId(),rooms);
        return msg.toShow();
    }
    @RequestMapping(value = "/modifyRoom",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String modifyRoom(RoomVO_input roomVO, HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.updateRoom(user.getId(),roomVO);
        return msg.toShow();
    }
    @RequestMapping(value = "/invalidateRoom",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String invalidateRoom(@RequestParam("roomId") int roomId, HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.invalidateRoom(roomId);
        return msg.toShow();
    }



}
