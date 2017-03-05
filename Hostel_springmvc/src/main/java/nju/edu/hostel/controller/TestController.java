package nju.edu.hostel.controller;

import nju.edu.hostel.model.Hostel;
import nju.edu.hostel.model.User;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by disinuo on 17/3/4.
 */

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    HostelService hostelService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        User user= userService.getById(1000000);

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("user", user);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }
    @RequestMapping(value = "/admin/rooms", method = RequestMethod.GET)
    public String getHostels(ModelMap modelMap) {
        // 查询user表中所有记录
        List<Hostel> hostels=hostelService.getAllPermittedHostels();
        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("hostelList", hostels);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/rooms";
    }
}