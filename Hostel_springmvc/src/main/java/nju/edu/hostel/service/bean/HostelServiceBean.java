package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.RequestState;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.LiveOutVO;
import nju.edu.hostel.vo.input.PayVO;
import nju.edu.hostel.vo.input.RoomVO;
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
    public ResultMessage update(Hostel hostel) {
        //TODO 这里有问题，Request表只能存当前存在的hostel。。
        // 是否考虑新建一个存储hostel临时信息的表
        RequestModify requestModify=new RequestModify();
        requestModify.setHostelOriginal(getById(hostel.getId()));
        requestModify.setHostelNew(hostel);
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
        Room room=roomDao.get(payVO.getRoomId());
        double moneyToPay=payVO.getMoney();
        payBill.setRoom(room);
        payBill.setHostel(room.getHostel());
        payBill.setUserRealName(payVO.getUserRealName());
        payBill.setIdCard(payVO.getIdCard());
        payBill.setCreateDate(new Date().getTime());
        if(payVO.getVipId()!=0){//顾客是会员
            Vip vip=vipDao.get(payVO.getVipId());
            payBill.setVip(vip);
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
            return moneyToPay;
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
    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelDao.getByRestrictEqual("permitted",true);
    }
    @Override
    public List<PayBill> getAllUncountedPayBills(int hostelId){
        Map map = new HashMap<String,Object>();
        map.put("hostelId",hostelId);
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
    LiveBillDao liveBillDao;
    @Autowired
    PayBillDao payBillDao;
    @Autowired
    VIPService vipService;
}
