package nju.edu.hostel.controller;

import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.vo.output.HostelVO;
import nju.edu.hostel.vo.output.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/getRooms")
    public List<RoomVO> getRooms(@RequestParam("hostelId")int hostelId){
        return RoomVO.entityToVO(hostelService.getAllRooms(hostelId));
    }
    @RequestMapping(value = "/getHostel")
    public HostelVO getHostel(@RequestParam("hostelId")int hostelId){
        return new HostelVO(hostelService.getById(hostelId));
    }
    @RequestMapping(value = "/getRoom")
    public RoomVO getRoomById(@RequestParam("roomId")int roomId){
        return new RoomVO(hostelService.getRoomById(roomId));
    }
}
