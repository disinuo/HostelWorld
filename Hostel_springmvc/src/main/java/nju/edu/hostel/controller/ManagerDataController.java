package nju.edu.hostel.controller;

import nju.edu.hostel.model.RequestModify;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.vo.output.HostelVO;
import nju.edu.hostel.vo.output.RequestModifyVO;
import nju.edu.hostel.vo.output.RequestOpenVO;
import nju.edu.hostel.vo.output.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by disinuo on 17/3/13.
 */
@Controller
@RequestMapping("/data/manager")
@ResponseBody
public class ManagerDataController {
    @Autowired
    ManagerService managerService;
    @Autowired
    HostelService hostelService;

    @RequestMapping(value = "/getOpenRequests")
    public List<RequestOpenVO> getOpenRequests(){
        return RequestOpenVO.entityToVO(managerService.getOpenRequests());
    }
    @RequestMapping(value = "/getModifyRequests")
    public List<RequestModifyVO> getModifyRequests(){
        return RequestModifyVO.entityToVO(managerService.getModifyRequests());
    }
    @RequestMapping(value = "/getHostelList")
    public List<HostelVO> getHostelList(){
        return HostelVO.entityToVO(hostelService.getAllPermittedHostels());
    }
    @RequestMapping(value = "/getVipList")
    public List<VipVO> getViplList(){
        return VipVO.entityToVO(managerService.getAllVips());
    }


}
