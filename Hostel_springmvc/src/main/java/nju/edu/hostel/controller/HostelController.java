package nju.edu.hostel.controller;

import nju.edu.hostel.model.Room;
import nju.edu.hostel.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by disinuo on 17/3/5.
 */
@Controller
@RequestMapping("hostel")
public class HostelController {
    @Autowired
    HostelService hostelService;
    @RequestMapping(value = "/rooms")
    public ModelAndView showRooms(@RequestParam("hostelId")int hostelId, ModelMap model){
        List<Room> rooms=hostelService.getAllRooms(hostelId);
        model.addAttribute("rooms",rooms);
        return new ModelAndView("vip/hostelDetailPage",model);
    }
}
