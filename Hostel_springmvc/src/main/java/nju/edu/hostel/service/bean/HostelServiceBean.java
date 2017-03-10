package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.LiveInVO;
import nju.edu.hostel.vo.LiveOutVO;
import nju.edu.hostel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class HostelServiceBean implements HostelService {
    @Autowired
    HostelDao hostelDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    RequestDao requestDao;
    @Autowired
    VIPDao vipDao;
    @Autowired
    LiveBillDao liveBillDao;
    @Override
    public ResultMessage delete(int hostelId) {
         return null; //TODO 
    }

    @Override
    public ResultMessage requestManager(int hostelId) {
        Request request=new Request();
        request.setHostel(getById(hostelId));
        try {
            requestDao.add(request);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public Hostel getById(int hostelId) {
        return hostelDao.get(hostelId);
    }

    @Override
    public ResultMessage update(Hostel hostel) {
         return hostelDao.update(hostel);
    }

    @Override
    public ResultMessage enrollPay(PayBill payBill) {
//        TODO 如果顾客是会员 消费要积分的！还要升级！还可能优惠！
         return null;
    }

    @Override
    public ResultMessage liveIn(LiveInVO liveInVO){
        LiveBill liveBill=new LiveBill();
        if(liveInVO.getVipId()!=0){
            Vip vip=vipDao.get(liveInVO.getVipId());
            liveBill.setVip(vip);
        }
        Room room=roomDao.get(liveInVO.getRoomId());
        liveBill.setType(true);
        liveBill.setRoom(room);
        liveBill.setHostel(room.getHostel());
        liveBill.setIdCard(liveInVO.getIdCard());
        liveBill.setUserRealName(liveInVO.getUserRealName());
        liveBill.setDate(new Date().getTime());
        try {
            liveBillDao.add(liveBill);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage depart(LiveOutVO liveOutVO){
        LiveBill liveBill=new LiveBill();
        Vip vip=vipDao.get(liveOutVO.getVipId());
        Room room=roomDao.get(liveOutVO.getRoomId());
        liveBill.setType(false);
        liveBill.setRoom(room);
        liveBill.setHostel(room.getHostel());
        liveBill.setVip(vip);
        liveBill.setIdCard(liveOutVO.getIdCard());
        liveBill.setUserRealName(liveOutVO.getUserRealName());
        liveBill.setDate(new Date().getTime());
        try {
            liveBillDao.add(liveBill);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage addRoom(int hostelId,List<RoomVO> roomVOs){
        ResultMessage msg;
        for(RoomVO roomVO:roomVOs){
           msg=addRoom(hostelId,roomVO);
           if(msg==ResultMessage.FAILURE){
               return ResultMessage.FAILURE;
           }
        }
        return ResultMessage.SUCCESS;
    }
    @Override
    public ResultMessage addRoom(int hostelId,RoomVO roomVO){
        Room room=new Room();
        room.setHostel(hostelDao.get(hostelId));
        room.setName(roomVO.getName());
        room.setPrice(roomVO.getPrice());
        room.setImg(roomVO.getImg());
        try {
            roomDao.add(room);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;

    }
    @Override
    public ResultMessage updateRoom(int hostelId, Room room) {
        return roomDao.update(room);
    }
    @Override
    public List<BookBill> getAllBookBills(int hostelId) {
        return getById(hostelId).getBookBills();
    }

    @Override
    public List<PayBill> getAllPayBills(int hostelId) {

        return getById(hostelId).getPayBills();
    }

    @Override
    public double getIncome(int hostelId) {
        double total=0;
        List<PayBill> payBills=getAllPayBills(hostelId);
        for(PayBill payBill:payBills){
            total+=payBill.getMoney();
        }
        return total;
    }

    @Override
    public List<LiveBill> getAllLiveBills(int hostelId) {
        return getById(hostelId).getLiveBills();
    }

    @Override
    public List<Room> getAllRooms(int hostelId){
        return getById(hostelId).getRooms();
    }
    @Override
    public Room getRoomById(int roomId){
        return roomDao.get(roomId);
    }
}
