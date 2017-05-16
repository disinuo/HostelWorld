package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.NumberFormatter;
import nju.edu.hostel.util.RequestState;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.PayVO;
import nju.edu.hostel.vo.input.RoomVO_input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static nju.edu.hostel.util.Constants.*;

import java.util.*;

//import static nju.edu.hostel.util.Constants.RATE_MONEY_TO_SCORE;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class HostelServiceBean implements HostelService {

    @Override
    public ResultMessage init(int hostelId){
        Hostel hostel=getById(hostelId);
        if(!hostel.getPermitted()){//店还没通过审核
            List<RequestOpen> requests=requestDao.getOpenRequestByRestrictEqual("hostel",hostel);
            if(requests==null||requests.size()==0){//客栈没提交过申请，提醒
                return ResultMessage.REMIND_REQUEST;
            }else {
                for(RequestOpen req:requests){//有一个拒绝的，就返回拒绝
                    if(req.getState().equals(RequestState.DENIED.toString())){
                        return ResultMessage.REQUEST_DENIED;
                    }
                }//否则返回 等待中
                return ResultMessage.REQUEST_UNCHECKED;
            }
        }
        return ResultMessage.SUCCESS;
    }
    @Override
    public ResultMessage delete(int hostelId) {
         return null; //TODO delete
    }

    @Override
    public ResultMessage requestManager(int hostelId) {
        RequestOpen requestOpen =new RequestOpen();
        requestOpen.setHostel(getById(hostelId));
        try {
            requestDao.addOpenRequest(requestOpen);
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
    public ResultMessage update(int id,String name,String address,String phone) {
        System.out.println("in service updateHostelInfo  ");
        System.out.print(id+" "+name+" "+address+" "+phone);
        //TODO 待测试
        RequestModify requestModify=new RequestModify();
        requestModify.setNewAddress(address);
        requestModify.setNewName(name);
        requestModify.setNewPhone(phone);
        requestModify.setHostelOriginal(hostelDao.get(id));
        try {

            requestDao.addModifyRequest(requestModify);
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
//        return hostelDao.update(hostel);
    }

    @Override
    public double enrollPay(PayVO payVO) {
        PayBill payBill=new PayBill();
        LiveBill liveBill=liveInBillDao.get(payVO.getLiveBillId());
        Room room=liveBill.getRoom();
        double moneyToPay=payVO.getMoney();
        payBill.setCreateDate(new Date().getTime());
        if(liveBill.getVip()!=null){//顾客是会员
            Vip vip=liveBill.getVip();
            //看会员级别~要打折的！
            int level=vip.getLevel();
            double discount=VIP_LEVEL_TO_DISCOUNT(level);
            moneyToPay*=discount;
            payBill.setMoney(moneyToPay);
            /*
             *消费要积分的！还要升级！
             */
            //会员算上这笔消费后的累计消费
            double vipPaidAll=vip.getMoneyPaid()+moneyToPay;
            vip.setMoneyPaid(vipPaidAll);
            vip.setScore(vip.getScore()+moneyToPay*RATE_MONEY_TO_SCORE);
            vip.setLevel(VIP_MONEY_TO_LEVEL(vipPaidAll));
            vipDao.update(vip);
        }else {//顾客不是会员，直接生成账单
            payBill.setMoney(moneyToPay);
        }
        try {
            payBillDao.add(payBill);
            Hostel hostel=room.getHostel();
            hostel.setMoneyUncounted(hostel.getMoneyUncounted()+moneyToPay);
            hostelDao.update(hostel);
            return NumberFormatter.saveOneDecimal(moneyToPay);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public ResultMessage vipPay(int vipId,double money){
        ResultMessage msg=vipService.payMoney(vipId,money);
        if(msg!=ResultMessage.SUCCESS){
            return msg;
        }
       return unVipPay(money);
    }
    @Override
    public ResultMessage unVipPay(double money){
        User manager=userDao.get(MANAGER_ID);
        manager.setBankMoney(manager.getBankMoney()+money);
        return userDao.update(manager);
    }
    @Override
    public ResultMessage liveIn(LiveInVO liveInVO){
        //TODO
        System.out.println("in service liveIn ");
        LiveBill liveBill=new LiveBill();
        if(liveInVO.getVipId()!=0){
            Vip vip=vipDao.get(liveInVO.getVipId());
            liveBill.setVip(vip);
        }
        Room room=roomDao.get(liveInVO.getRoomId());
        room.setOccupiedNum(room.getOccupiedNum()+1);
        liveBill.setRoom(room);
        liveBill.setIdCard(liveInVO.getIdCard());
        liveBill.setUserRealName(liveInVO.getUserRealName());
        liveBill.setDate(new Date().getTime());
        try {
            liveInBillDao.add(liveBill);
            roomDao.update(room);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage checkOut(int liveBillId){
        //TODO 要更新 已占用的房间数据，更新人均消费等，再想想
        LiveBill liveBill=liveInBillDao.get(liveBillId);
        Room room=liveBill.getRoom();
        liveBill.setCheckOutDate((new Date()).getTime());
        liveBill.setInHostel(false);
        room.setOccupiedNum(room.getOccupiedNum()-1);
        try {
            liveInBillDao.update(liveBill);
            roomDao.update(room);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage addRoom(int hostelId,List<RoomVO_input> roomVOs){
        ResultMessage msg;
        for(RoomVO_input roomVO:roomVOs){
           msg=addRoom(hostelId,roomVO);
           if(msg==ResultMessage.FAILURE){
               return ResultMessage.FAILURE;
           }
        }
        return ResultMessage.SUCCESS;
    }
    @Override
    public ResultMessage addRoom(int hostelId,RoomVO_input roomVO){
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
    public ResultMessage updateRoom(int roomId, RoomVO_input roomVO) {
//        TODO updateRoom!!!!
        Room room=roomDao.get(roomId);
        room.setName(roomVO.getName());
        room.setImg(roomVO.getImg());
        room.setPrice(roomVO.getPrice());
        return roomDao.update(room);
    }
    @Override
    public ResultMessage invalidateRoom(int roomId){
        Room room=roomDao.get(roomId);
        room.setValid(false);
        return roomDao.update(room);
    }
    @Override
    public List<BookBill> getAllBookBills(int hostelId) {

        return bookBillDao.getByHostelId(hostelId);
    }

    @Override
    public List<PayBill> getAllPayBills(int hostelId) {
        return payBillDao.getByHostelId(hostelId);//getByRestrictEqual("hostel.id",hostelId);
    }

    @Override
    public double getIncome(int hostelId) {
        double total=0;
        List<PayBill> payBills=getAllPayBills(hostelId);
        for(PayBill payBill:payBills){
            total+=payBill.getMoney();
        }
        return NumberFormatter.saveOneDecimal(total);
    }

    @Override
    public List<LiveBill> getAllLiveBills(int hostelId) {
        List<LiveBill> ans=liveInBillDao.getByHostelId(hostelId);
        return ans;
    }
    @Override
    public List<LiveBill> getNotOutLiveBills(int hostelId){
        //TODO 所有未离店的住店数据
        return null;
    }
    @Override
    public int getLiveInNum(int hostelId){
       return getAllLiveBills(hostelId).size();
    }
    @Override
    public List<Room> getAllRooms(int hostelId){
        return getById(hostelId).getRooms();
    }

    @Override
    public List<Room> getAllValidRooms(int hostelId){
        Map map=new HashMap<String,Object>();
        map.put("hostel.id",hostelId);
        map.put("valid",true);
        return roomDao.getByRestrictEqual(map);
    }
    @Override
    public Room getRoomById(int roomId){
        return roomDao.get(roomId);
    }
    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelDao.getByRestrictEqual("permitted",true);
    }
    @Override
    public List<PayBill> getAllUncountedPayBills(int hostelId){
        Map map = new HashMap<String,Object>();
        map.put("hostel.id",hostelId);
        map.put("counted",false);
        return payBillDao.getByRestrictEqual(map);
    }
//   ----------------------------------------
    @Autowired
    HostelDao hostelDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    RequestDao requestDao;
    @Autowired
    UserDao userDao;
    @Autowired
    VIPDao vipDao;
    @Autowired
    LiveBillDao liveInBillDao;
    @Autowired
    PayBillDao payBillDao;
    @Autowired
    BookBillDao bookBillDao;
    @Autowired
    VIPService vipService;
}
