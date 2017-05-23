package nju.edu.hostel.controller;

import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.vo.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/data/hostel")
@ResponseBody
public class HostelDataController {
    @Autowired
    HostelService hostelService;

    /**
     * 给别人用的
     * @param hostelId
     * @return
     */
    @RequestMapping(value = "/getValidRooms")
    public List<RoomVO> getValidRooms(@RequestParam("hostelId")int hostelId){
        return RoomVO.entityToVO(hostelService.getAllValidRooms(hostelId));
    }
    /**
     * 给别人用的
     * @param hostelId
     * @return
     */
    @RequestMapping(value = "/getHostel")
    public HostelVO getHostel(@RequestParam("hostelId")int hostelId){
        return new HostelVO(hostelService.getById(hostelId));
    }
//------------自己用的，hostel的id都从session中获得------------------

    @RequestMapping(value = "/getRooms")
    public List<RoomVO> getRooms(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return RoomVO.entityToVO(hostelService.getAllRooms(id));
    }


    @RequestMapping(value = "/getInfo")
    public HostelVO getHostelInfo(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return new HostelVO(hostelService.getById(id));
    }
/*   这个自己用，别人用 都行*/
    @RequestMapping(value = "/getRoom")
    public RoomVO getRoomById(@RequestParam("roomId")int roomId){
        return new RoomVO(hostelService.getRoomById(roomId));
    }

    @RequestMapping(value = "/getBookBills")
    public List<BookBillVO> getAllBookBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return BookBillVO.entityToVO(hostelService.getAllBookBills(id));
    }
    @RequestMapping(value = "/getBookBillById")
    public BookBillVO getAllBookBills(int billId){
        System.out.println("controller: billId= "+billId);
        return new BookBillVO(hostelService.getBookBillById(billId));
    }

    @RequestMapping(value = "/getPayBills")
    public List<PayBillVO> getAllPayBills(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        List<PayBillVO> vos=PayBillVO.entityToVO(hostelService.getAllPayBills(id));
        System.err.println("************  in hostelDatacontroller *********************");
        for(PayBillVO vo:vos){
            System.out.print(vo.getId()+": ");
            for(GuestVO g:vo.getGuestVOS())
                System.out.println(g.getUserRealName()+", "+g.getVipId() );

        }
        return vos;
    }
    @RequestMapping(value = "/getLiveBills")
    public List<LiveBillVO> getAllLiveBills(HttpSession session) {
        System.out.print("In Hostel controller! getAllLiveBills");

        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        return LiveBillVO.entityToVO(hostelService.getAllLiveBills(id));
    }



    @RequestMapping(value = "/others/getBookBills")
    public List<BookBillVO> getAllBookBillsByOthers(@RequestParam("hostelId")int hostelId){
        return BookBillVO.entityToVO(hostelService.getAllBookBills(hostelId));
    }
    @RequestMapping(value = "/others/getPayBills")
    public List<PayBillVO> getAllPayBillsByOthers(@RequestParam("hostelId")int hostelId){
        return PayBillVO.entityToVO(hostelService.getAllPayBills(hostelId));
    }

    @RequestMapping(value = "/others/getLiveBills")
    public List<LiveBillVO> getAllLiveBillsByOthers(@RequestParam("hostelId")int hostelId){
        return LiveBillVO.entityToVO(hostelService.getAllLiveBills(hostelId));
    }


    @RequestMapping(value = "/getIncome",produces = "text/html;charset=UTF-8")
    public String getIncome(HttpSession session){
        OnLineUserVO user=(OnLineUserVO)session.getAttribute("user");
        int id=user.getId();
        double ans=hostelService.getIncome(id);
        System.out.print("In CONTROLLER ! getValue= "+ans);
        return ans+"";
    }
}
