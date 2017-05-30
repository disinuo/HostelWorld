package nju.edu.hostel.controller;

import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.vo.output.BookBillVO;
import nju.edu.hostel.vo.output.LiveBillVO;
import nju.edu.hostel.vo.output.PayBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by disinuo on 17/5/30.
 */
@Controller
@RequestMapping("/data/common")
@ResponseBody
public class publicDataController {
    @RequestMapping(value = "/getAllBookBills")
    public List<BookBillVO> getAllBookBillsByOthers(@RequestParam("hostelId")int hostelId){
        return BookBillVO.entityToVO(hostelService.getAllBookBills(hostelId));
    }
    @RequestMapping(value = "/geAllPayBills")
    public List<PayBillVO> getAllPayBillsByOthers(@RequestParam("hostelId")int hostelId){
        return PayBillVO.entityToVO(hostelService.getAllPayBills(hostelId));
    }

    @RequestMapping(value = "/getAllLiveBills")
    public List<LiveBillVO> getAllLiveBillsByOthers(@RequestParam("hostelId")int hostelId){
        return LiveBillVO.entityToVO(hostelService.getAllLiveBills(hostelId));
    }

    @Autowired
    HostelService hostelService;

}
