package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.*;
import nju.edu.hostel.vo.input.GuestInputVO;
import nju.edu.hostel.vo.input.LiveInVO;
import nju.edu.hostel.vo.input.RoomVO_input;
import nju.edu.hostel.vo.output.GuestVO;
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
    public ResultMessage update(int id,String descrip,String name,String address,String phone) {
        System.out.println("in service updateHostelInfo  ");
        System.out.print(id+" "+name+" "+address+" "+phone);
        //TODO 待测试
        RequestModify requestModify=new RequestModify();
        requestModify.setNewAddress(address);
        requestModify.setNewName(name);
        requestModify.setNewPhone(phone);
        requestModify.setHostelOriginal(hostelDao.get(id));
        requestModify.setNewDescrip(descrip);
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
        LiveBill liveBill= liveBillDao.get(liveBillId);
        List<LiveDetail> liveDetails=liveBill.getLiveDetails();
        Hostel hostel=liveBill.getHostel();

        //生成付款单
        PayBill payBill=new PayBill();
        payBill.setCreateDate(new Date().getTime());
        payBill.setLiveBill(liveBill);
        payBill.setHostel(hostel);

        //房间原价
        double moneyToPay=liveBill.getRoom().getPrice();
        //找到房价最低折扣
        int highestLevel=-1;
        for(LiveDetail detail:liveDetails) {
            Vip vip=detail.getVip();
            if(vip!=null) {
                highestLevel=getBigger(highestLevel,vip.getLevel());
            }
        }
        if(highestLevel>0) {//顾客里有会员
            double discount=VIP_LEVEL_TO_DISCOUNT(highestLevel);
            moneyToPay*=discount;

            double eachPay=moneyToPay/liveBill.getNumOfPeople();
            /*
             *消费要积分的！还要升级！
             */
            //会员算上这笔消费后的累计消费
            for(LiveDetail detail:liveDetails) {
                Vip vip=detail.getVip();
                if(vip!=null) {
                    //更新这个vip的各种属性
                    double vipPaidAll=vip.getMoneyPaid()+eachPay;
                    vip.setMoneyPaid(vipPaidAll);
                    vip.setScore(vip.getScore()+eachPay*RATE_MONEY_TO_SCORE);
                    vip.setLevel(VIP_MONEY_TO_LEVEL(vipPaidAll));
                    vipDao.update(vip);

                }
            }
        }
        //顾客里没有会员，直接生成账单
        payBill.setMoney(moneyToPay);

        try {
            payBillDao.add(payBill);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        try {
            //更新住店单状态为【已支付】
            liveBill.setPaid(true);
            liveBillDao.update(liveBill);

            //本酒店未结算总额
            double moneyUncounted=hostel.getMoneyUncounted();
            //本酒店人均消费额
            double avgExpense=hostel.getAvgExpense();
            //本酒店住店累计总人数
            long numOfPeople=hostel.getNumOfPeople();
            //本酒店顾客消费总金额
            double totalExpense=avgExpense*numOfPeople;

            moneyUncounted+=moneyToPay;
            totalExpense+=moneyToPay;
            //更新hostel信息
            hostel.setMoneyUncounted(moneyUncounted);
            numOfPeople+=liveBill.getNumOfPeople();
            avgExpense=totalExpense/numOfPeople;
            hostel.setAvgExpense(NumberFormatter.saveOneDecimal(avgExpense));
            hostel.setNumOfPeople(numOfPeople);

            hostelDao.update(hostel);
            return NumberFormatter.saveOneDecimal(moneyToPay);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public ResultMessage vipPay(int vipId,int hostelId,double money){
        ResultMessage msg=vipService.payMoney(vipId,money);
        if(msg!=ResultMessage.SUCCESS){
            return msg;
        }
        //生成这个vip的money record
        VipMoneyRecord vipMoneyRecord=new VipMoneyRecord();
        vipMoneyRecord.setDate(new Date().getTime());
        vipMoneyRecord.setMoney(-money);
        vipMoneyRecord.setVipId(vipId);
        vipMoneyRecord.setType(MoneyType.MONEY_TYPE_PAY.getCode());
        if(vipMoneyRecordDao.addNoId(vipMoneyRecord)==ResultMessage.SUCCESS)
           return unVipPay(hostelId,money);
        return ResultMessage.FAILURE;
    }
    @Override
    public ResultMessage unVipPay(int hostelId,double money){
        User manager=userDao.get(MANAGER_ID);
        manager.setBankMoney(manager.getBankMoney()+money);
        HostelMoneyRecord hostelMoneyRecord=new HostelMoneyRecord(
                hostelId,
                money,
                new Date().getTime(),
                MoneyType.MONEY_TYPE_PAY.getCode()
        );
        ResultMessage msg=hostelMoneyRecordDao.addNoId(hostelMoneyRecord);
        if(msg==ResultMessage.SUCCESS){
            msg=bossMoneyRecordDao.record(
                    money,
                    new Date().getTime(),
                    MoneyType.MONEY_TYPE_PAY.getCode()

            );
        }
        if(msg==ResultMessage.SUCCESS)
            return userDao.update(manager);
        return ResultMessage.FAILURE;
    }
    @Override
    public ResultMessage liveIn(LiveInVO liveInVO){
        int roomId=liveInVO.getRoomId();
        int bookBillId=liveInVO.getBookBillId();
        System.err.println("roomId= "+roomId+", bookId= "+bookBillId);
        List<GuestInputVO> guests=GuestInputVO.mapToVO(liveInVO.getGuests());
//
        LiveBill liveBill=new LiveBill();
        List<LiveDetail> liveDetails=new ArrayList<LiveDetail>();
        Room room=roomDao.get(roomId);
        Hostel hostel=room.getHostel();

        for(GuestInputVO guest:guests){
            LiveDetail detail=new LiveDetail();
            Vip vip=vipDao.get(guest.getVipId());
            if(vip!=null){
                detail.setVip(vip);
            }
            room.setVacantNum(room.getVacantNum()-1);
            roomDao.update(room);
            detail.setIdCard(guest.getIdCard());
            detail.setUserRealName(guest.getUserRealName());
            liveDetails.add(detail);
        }
        liveBill.setLiveDetails(liveDetails);
        liveBill.setDate(new Date().getTime());
        liveBill.setHostel(hostel);
        liveBill.setRoom(room);
        liveBill.setNumOfPeople(guests.size());
        try {
            //更新预订订单的状态

            BookBill bookBill=bookBillDao.get(bookBillId);
            if(bookBill!=null) {
                bookBill.setState(1);
                bookBillDao.update(bookBill);
                liveBill.setBookBill(bookBill);
            }
            liveBillDao.add(liveBill);

            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage checkOut(int liveBillId){
        //TODO 要更新 更新人均消费等，再想想
        LiveBill liveBill= liveBillDao.get(liveBillId);
        liveBill.setCheckOutDate((new Date()).getTime());
        liveBill.setInHostel(false);
        Room room=liveBill.getRoom();
        room.setVacantNum(room.getVacantNum()+1);
        if(liveBill.getBookBill()!=null){//有预订
            room.setBookedNum(room.getBookedNum()-1);
        }
        roomDao.update(room);
        try {
            liveBillDao.update(liveBill);
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
        //TODO 房间成本
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
//        room.setImg(roomVO.getImg());
        room.setPrice(roomVO.getPrice());
        room.setCapacity(roomVO.getCapacity());
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

        return bookBillDao.getByRestrictEqual("hostel.id",hostelId);
    }
    @Override
    public List<BookBill> getRecentBookBills(int hostelId){
        return bookBillDao.getRecentByHostelId(hostelId);
    }
    @Override
    public List<BookBill> getRecentWeekBookBills(int hostelId){
        long today=new Date().getTime();
        long start=DateHandler.add(today,Calendar.WEDNESDAY,-1);
        return bookBillDao.getByHostel_createDate(hostelId,start,today);
    }
    public List<BookBill> getRecentMonthBookBills(int hostelId){
        long today=new Date().getTime();
        long start=DateHandler.add(today,Calendar.MONTH,-1);
        return bookBillDao.getByHostel_createDate(hostelId,start,today);
    }
    public List<BookBill> getRecentYearBookBills(int hostelId){
        long today=new Date().getTime();
        long start=DateHandler.add(today,Calendar.YEAR,-1);
        return bookBillDao.getByHostel_createDate(hostelId,start,today);
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
        List<LiveBill> ans= liveBillDao.getAllByHostelId(hostelId);
        return ans;
    }
    @Override
    public List<LiveBill> getNotOutLiveBills(int hostelId){

        return liveBillDao.getNotOutByHostelId(hostelId);
    }
    @Override
    public List<LiveBill> getNotPaidLiveBills(int hostelId){
        return liveBillDao.getNotPaidByHostelId(hostelId);
    }
    @Override
    public LiveBill getLiveBillById(int billId){
        return liveBillDao.get(billId);
    }
    public List<LiveBill> getRecentLiveBills(int hostelId){
        return liveBillDao.getRecentByHostelId(hostelId);
    }
    public List<LiveBill> getRecentWeekLiveBills(int hostelId){
        long today=new Date().getTime();
        long start=DateHandler.add(today,Calendar.WEDNESDAY,-1);
        return liveBillDao.getRecentByHostelId_Date(hostelId,start,today);
    }
    public List<LiveBill> getRecentMonthLiveBills(int hostelId){
        long today=new Date().getTime();
        long start=DateHandler.add(today,Calendar.MONTH,-1);
        return liveBillDao.getRecentByHostelId_Date(hostelId,start,today);
    }
    public List<LiveBill> getRecentYearLiveBills(int hostelId){
        long today=new Date().getTime();
        long start=DateHandler.add(today,Calendar.YEAR,-1);
        return liveBillDao.getRecentByHostelId_Date(hostelId,start,today);
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
        List<Room> rooms= roomDao.getByRestrictEqual("hostel.id",hostelId);
        return refreshRoomValidity(rooms);
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

    @Override
    public  List<HostelMoneyRecord> getAllMoneyRecords(int hostelId){
        return hostelMoneyRecordDao.getByRestrictEqual("hostelId",hostelId);
    }
    //only return valid rooms( state = 0 )
    private List<Room> refreshRoomValidity(List<Room> rooms){
        Iterator<Room> itr=rooms.iterator();
        while (itr.hasNext()){
            Room room=itr.next();
            int state=countRoomState(room);
            room.setState(state);
            roomDao.update(room);
            if(state!=0){
                itr.remove();
            }

        }
        return rooms;
    }

    private int countRoomState(Room room){
        long today=new Date().getTime();
        if(room.getStartDate()>today) return 1;
        else if(room.getEndDate()<today) return -1;
        else return 0;
    }

    private int getBigger(int x,int y) {
        if(x>y) return x;
        else return y;
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
    HostelMoneyRecordDao hostelMoneyRecordDao;
    @Autowired
    BossMoneyRecordDao bossMoneyRecordDao;
    @Autowired
    VipMoneyRecordDao vipMoneyRecordDao;
    @Autowired
    VIPService vipService;

}
