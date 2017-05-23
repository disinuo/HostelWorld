package nju.edu.hostel.controller;

import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.PayVO;
import nju.edu.hostel.vo.input.RoomVO_input;
import nju.edu.hostel.vo.output.OnLineUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/hostel")
@ResponseBody
public class HostelCommitController {
    @Autowired
    HostelService hostelService;
    @Autowired
    ServletContext context;

    @RequestMapping(value = "/requestManager",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String handleOpenRequest(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.requestManager(user.getId());
        System.out.print("in /requestManager "+msg);
        return msg.toShow();
    }

    @RequestMapping(value = "/modifyInfo",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String handlModifyRequest(HttpSession session,String descrip,String name,String address,String phone){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.update(user.getId(),descrip,name,address,phone);
        return msg.toShow();
    }

    @RequestMapping(value = "/enrollPay",method = RequestMethod.POST)
    public double enrollPay(int liveBillId){
        return hostelService.enrollPay(liveBillId);
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
    public String liveIn(int bookBillId,int roomId,List<LiveInVO> liveInVOs){
        ResultMessage msg=hostelService.liveIn(bookBillId,roomId,liveInVOs);
        return msg.toShow();
    }
    @RequestMapping(value = "/checkOut",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String checkOut(int liveInId){
        ResultMessage msg=hostelService.checkOut(liveInId);
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
    public String modifyRoom(RoomVO_input roomVO, int roomId, HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.updateRoom(roomId,roomVO);
        return msg.toShow();
    }
    @RequestMapping(value = "/invalidateRoom",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String invalidateRoom(@RequestParam("roomId") int roomId, HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        ResultMessage msg=hostelService.invalidateRoom(roomId);
        return msg.toShow();
    }




    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        String path=getImgFolderPath()+"img/";
        System.out.println(file.getOriginalFilename());
        if (file.isEmpty()) {
            //TODO 反馈空文件错误
            return "";
        }
        try {
            byte[] bytes = file.getBytes();
            String path_str=path+(new Date()).getTime()+file.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(path_str), bytes);
            System.out.println(path_str);
            return path_str;

        } catch (IOException e) {
            //TODO TOO_LARGE,...
//            model.addAttribute("msg","something_wrong");
            e.printStackTrace();
        }
        return "";
    }


    private String getImgFolderPath(){
        String path=context.getRealPath("/resources");
        System.err.println("path= "+path);
        String[] temps=path.split("/");
        String imgPath="";
        for(int i=0;i<temps.length-4;i++){
            imgPath+=temps[i]+"/";
        }
        imgPath+="target/classes/static/img/";
        return imgPath;
    }

}
