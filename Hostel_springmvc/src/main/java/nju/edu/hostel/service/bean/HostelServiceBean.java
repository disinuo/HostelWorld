package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DateHandler;
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
    public double enrollPay(int liveBillId) {
        System.out.println("In service---- id="+liveBillId+",money=");
        PayBill payBill=new PayBill();
        LiveBill liveBill= liveBillDao.get(liveBillId);
        Room room=liveBill.getRoom();
        double moneyToPay=liveBill.getRoom().getPrice();
        payBill.setCreateDate(new Date().getTime());
        payBill.setLiveBill(liveBill);

        if(liveBill.getVip()!=null){//顾客是会员
            Vip vip=liveBill.getVip();
            //看会员级别~要打折的！
            int level=vip.getLevel();
            double discount=VIP_LEVEL_TO_DISCOUNT(level);
            moneyToPay*=discount;
            /*
             *消费要积分的！还要升级！
             */
            //会员算上这笔消费后的累计消费
            double vipPaidAll=vip.getMoneyPaid()+moneyToPay;
            vip.setMoneyPaid(vipPaidAll);
            vip.setScore(vip.getScore()+moneyToPay*RATE_MONEY_TO_SCORE);
            vip.setLevel(VIP_MONEY_TO_LEVEL(vipPaidAll));
            vipDao.update(vip);
        }
        //顾客不是会员，直接生成账单
        payBill.setMoney(moneyToPay);

        try {
            liveBill.setPaid(true);
            liveBillDao.update(liveBill);
            payBillDao.add(payBill);
            Hostel hostel=room.getHostel();
            hostel.setMoneyUncounted(hostel.getMoneyUncounted()+moneyToPay);

            double avgExpense=hostel.getAvgExpense();
            int numOfPeople=hostel.getNumOfPeople();
            avgExpense=(avgExpense*numOfPeople+moneyToPay)/(numOfPeople+1);
            hostel.setAvgExpense(NumberFormatter.saveOneDecimal(avgExpense));
            hostel.setNumOfPeople(numOfPeople+1);

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
        System.out.println("in service liveIn ");
        LiveBill liveBill=new LiveBill();
        if(liveInVO.getVipId()!=0){
            Vip vip=vipDao.get(liveInVO.getVipId());
            liveBill.setVip(vip);
        }
        Room room=roomDao.get(liveInVO.getRoomId());
        room.setVacantNum(room.getVacantNum()-1);
        liveBill.setRoom(room);
        liveBill.setIdCard(liveInVO.getIdCard());
        liveBill.setUserRealName(liveInVO.getUserRealName());
        liveBill.setDate(new Date().getTime());
        try {
            BookBill bookBill=bookBillDao.get(liveInVO.getBookBillId());
            if(bookBill!=null) {
                bookBill.setState(1);
                bookBillDao.update(bookBill);
                liveBill.setBookBill(bookBill);
            }
            liveBillDao.add(liveBill);
            roomDao.update(room);
            //更新预订订单的状态

            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage checkOut(int liveBillId){
        //TODO 要更新 更新人均消费等，再想想
        LiveBill liveBill= liveBillDao.get(liveBillId);
        Room room=liveBill.getRoom();
        liveBill.setCheckOutDate((new Date()).getTime());
        liveBill.setInHostel(false);
        room.setVacantNum(room.getVacantNum()+1);
        try {
            liveBillDao.update(liveBill);
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
        room.setCapacity(roomVO.getCapacity());
        room.setVacantNum(roomVO.getTotalNum());
        room.setDescrip(roomVO.getDescrip());
        room.setTotalNum(roomVO.getTotalNum());
        room.setStartDate(DateHandler.strToLong(roomVO.getStartDate()));
        room.setEndDate(DateHandler.strToLong(roomVO.getEndDate()));

        room.setState(countRoomState(room));
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
        room.setCapacity(roomVO.getCapacity());
        room.setVacantNum(roomVO.getCapacity());
        room.setDescrip(roomVO.getDescrip());
        room.setTotalNum(roomVO.getTotalNum());
        room.setStartDate(DateHandler.strToLong(roomVO.getStartDate()));
        room.setEndDate(DateHandler.strToLong(roomVO.getEndDate()));

        room.setState(countRoomState(room));
        return roomDao.update(room);
    }
    @Override
    public ResultMessage invalidateRoom(int roomId){
        Room room=roomDao.get(roomId);
        room.setState(-1);
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
        List<LiveBill> ans= liveBillDao.getByHostelId(hostelId);
        return ans;
    }
    @Override
    public List<LiveBill> getNotOutLiveBills(int hostelId){
        return liveBillDao.getLivingByHostelId(hostelId);
    }
    @Override
    public int getTotalLiveInNum(int hostelId){
       return getAllLiveBills(hostelId).size();
    }
    @Override
    public int getPresentLiveInNum(int hostelId){return getNotOutLiveBills(hostelId).size();}

    @Override
    public List<Room> getAllRooms(int hostelId){
        List<Room> rooms= getById(hostelId).getRooms();
        Iterator<Room> itr=rooms.iterator();
        while (itr.hasNext()){
            Room room=itr.next();
            room.setState(countRoomState(room));
            roomDao.update(room);
        }
        return rooms;
    }

    @Override
    public List<Room> getAllValidRooms(int hostelId){
        Map map=new HashMap<String,Object>();
        map.put("hostel.id",hostelId);
        map.put("state",0);
        List<Room> rooms= roomDao.getByRestrictEqual(map);
        return refreshRoomValidity(rooms);
    }

    private List<Room> refreshRoomValidity(List<Room> rooms){
        Iterator<Room> itr=rooms.iterator();
        while (itr.hasNext()){
            Room room=itr.next();
            int state=countRoomState(room);
            if(state!=0){
                room.setState(state);
                roomDao.update(room);
                itr.remove();
            }

        }
        System.out.println("service refresh: rooms.size= "+rooms.size());
        return rooms;
    }

    private int countRoomState(Room room){
        long today=new Date().getTime();
        System.out.println("today= "+today+", start="+room.getStartDate()+",endDate="+room.getEndDate());
        if(room.getStartDate()>today) return 1;
        else if(room.getEndDate()<today) return -1;
        else return 0;
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

    @Override
    public BookBill getBookBillById(int billId){
        return bookBillDao.get(billId);
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
    BookBillDao bookBillDao;
    @Autowired
    VIPService vipService;

}
