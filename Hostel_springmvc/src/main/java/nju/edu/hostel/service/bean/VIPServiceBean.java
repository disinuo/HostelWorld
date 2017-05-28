package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.DateHandler;
import nju.edu.hostel.util.MoneyType;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import nju.edu.hostel.util.VIPState;
import nju.edu.hostel.vo.input.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static nju.edu.hostel.util.Constants.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class VIPServiceBean implements VIPService{
    @Override
    public void init(int vipId){
        Vip vip=getById(vipId);
        VIPState vipState=VIPState.strToVipState(vip.getState());
        if(vipState==VIPState.UNACTIVATED||vipState==VIPState.STOP){
            //未激活or停卡 不涉及根据时间改变状态
            return;
        }else{
            long today=new Date().getTime();
            if(vipState==VIPState.PAUSED){
                //卡被暂停了。若时间超过一年 则变为stop
                long pauseDate=vip.getPauseDate();
                if(DateHandler.milliSecondToDay(today-pauseDate)>=DAY_OF_PAUSE_TO_STOP){
                    //暂停超过？天
                    vip.setState(VIPState.STOP.toString());
                    vipDao.update(vip);
                }
            }else if(vipState==VIPState.NORMAL){
                //卡是正常状态。检测激活时间
                long activateDate=vip.getActivateDate();
                double moneyLeft=vip.getMoneyLeft();
                double dayDifference=DateHandler.milliSecondToDay(today-activateDate);
                if(dayDifference>=DAY_OF_NORMAL_TO_PAUSE){
                    if(moneyLeft<MONEY_LEAST){
                        //激活超过?天,但不超过?1+?2天
                        //卡余额不足，状态变成暂停，并记录暂停时间
                        if(dayDifference<=(DAY_OF_NORMAL_TO_PAUSE+DAY_OF_PAUSE_TO_STOP)){
                            vip.setState(VIPState.PAUSED.toString());
                            vip.setPauseDate(activateDate+DateHandler.dayToMilliSecond(1));
                        }else {//激活超过?1+?2天，卡余额不足，直接停卡！
                            vip.setState(VIPState.STOP.toString());
                        }
                    }else {//余额还足~ 更新激活日期为当前时间
                        vip.setActivateDate(new Date().getTime());
                    }
                    vipDao.update(vip);
                }
            }
        }
    }

    @Override
    public ResultMessage topUp(double money, int vipId, String bankPassword) {
        System.out.println("VIP 充值金额"+money+" id:"+vipId+" 密码:"+bankPassword);
        User user=userDao.get(vipId);
        Vip vip=vipDao.get(vipId);
        //已停卡，则不能充值
        if(vip.getState().equals(VIPState.STOP.toString())){
            return ResultMessage.VIP_STATE_STOP;
        }
        //银行卡密码错误
        if(!user.getBankPassword().equals(bankPassword)){
            return ResultMessage.WRONG_PASSWORD;
        }
        //银行卡余额不足
        if(user.getBankMoney()<money){
            return ResultMessage.NOT_ENOUGH_MONEY;
        }
        //        ----真的可以开始充值啦！------
        ResultMessage msg=userService.modifyBankMoneyBy(vipId,-money);
        vip.setMoneyLeft(vip.getMoneyLeft()+money);
        //记录一个vip的充值记录
        VipMoneyRecord vipMoneyRecord = new VipMoneyRecord();
        vipMoneyRecord.setMoney(money);
        vipMoneyRecord.setType(MoneyType.MONEY_TYPE_TOPUP.getCode());
        vipMoneyRecord.setVipId(vipId);
        vipMoneyRecord.setDate(new Date().getTime());
        try {
            vipMoneyRecordDao.add(vipMoneyRecord);
        } catch (Exception e) {
            return ResultMessage.FAILURE;
        }
        //判断一些特殊情况
        if(msg==ResultMessage.SUCCESS){
            if(money>=MONEY_ACTIVATE){//一次性交费大于？，需要的话去激活
                activate(vip);
            }
            if(vip.getMoneyLeft()>=MONEY_LEAST){//累计交费大于？，需要的话去恢复
                restore(vip);
            }
            return vipDao.update(vip);
        }else {
            return ResultMessage.FAILURE;
        }
    }


    @Override
    public ResultMessage stop(int vipId) {
        Vip vip=vipDao.load(vipId);
        vip.setState(VIPState.STOP.toString());
        return vipDao.update(vip);
    }

    @Override
    public Vip getById(int vipId) {
        return vipDao.get(vipId);
    }

    @Override
    public ResultMessage update(int id,String name,String idCard){
        Vip vip=vipDao.get(id);
        vip.setRealName(name);
        vip.setIdCard(idCard);
        return vipDao.update(vip);
    }

    @Override
    public ResultMessage book(BookVO bookVO){
        Vip vip=getById(bookVO.getVipId());
        String vipState=vip.getState();
        if(vipState.equals(VIPState.STOP.toString())){
            return ResultMessage.VIP_STATE_STOP;
        }else if(vipState.equals(VIPState.PAUSED.toString())){
            return ResultMessage.VIP_STATE_PAUSED;
        }else if(vipState.equals(VIPState.UNACTIVATED.toString())) {
            return ResultMessage.VIP_STATE_UNACTIVATED;
        }else {//该会员有权利预订房间
            Room room=hostelService.getRoomById(bookVO.getRoomId());
            //房间已经订满
            if(room.getBookedNum()>=room.getTotalNum()){
                return ResultMessage.ROOM_FULL;
            }
            //房间可以订，
            long today=new Date().getTime();
            int moneyType=MoneyType.MONEY_TYPE_BOOK.getCode();

            //先从会员扣预订费
            ResultMessage msg=payMoney(vip.getId(),MONEY_BOOK);
            if(msg==ResultMessage.NOT_ENOUGH_MONEY){//会员卡余额不足，无法预订
                return msg;
            }else {//会员扣费成功
                //创建预订的交易记录--vip的
                VipMoneyRecord vipMoneyRecord=new VipMoneyRecord(
                        vip.getId(),
                        today,
                        -MONEY_BOOK,
                        moneyType
                );
                //更新房间信息
                room.setBookedNum(room.getBookedNum()+1);
                //生成预订订单
                BookBill bookBill=new BookBill();
                bookBill.setVip(vip);
                bookBill.setRoom(room);
                bookBill.setCheckOutDate(DateHandler.strToLong(bookVO.getCheckOutDate()));
                bookBill.setCreateDate(new Date().getTime());
                bookBill.setLiveInDate(DateHandler.strToLong(bookVO.getLiveInDate()));
                //创建预订的交易记录--酒店的的
                HostelMoneyRecord hostelMoneyRecord=new HostelMoneyRecord(
                        room.getHostel().getId(),
                        MONEY_BOOK,
                        today,
                        moneyType
                );
                msg=bossMoneyRecordDao.record(MONEY_BOOK,today,moneyType);
                if(msg==ResultMessage.FAILURE) return msg;
                try {
                    bookBillDao.add(bookBill);
                    vipMoneyRecordDao.add(vipMoneyRecord);
                    hostelMoneyRecordDao.add(hostelMoneyRecord);
                    return roomDao.update(room);
                } catch (Exception e) {
                    return ResultMessage.FAILURE;
                }
            }
        }
    }

    @Override
    public ResultMessage unbook(int vipId, int bookId) {
        BookBill bookBill=bookBillDao.get(bookId);
        long nowDate=new Date().getTime();
        if(bookBill.getVipId()!=vipId){//只能取消预订自己的订单
            return ResultMessage.NO_AUTHORITY;
        }else if(nowDate>=bookBill.getLiveInDate()){//超过了订单的入住时间
            return ResultMessage.LATE_TIME;
        }else {//可以取消
            long today=new Date().getTime();
            int moneyType=MoneyType.MONEY_TYPE_UNBOOK.getCode();
            Room room=bookBill.getRoom();
            room.setBookedNum(room.getBookedNum()-1);
            ResultMessage msg=roomDao.update(room);
            if(msg==ResultMessage.FAILURE) return msg;
            //退钱
            payMoney(vipId,-MONEY_BOOK);
            //使该预订订单失效！~~~
            bookBill.setState(-1);
            //创建预订的交易记录--vip的
            VipMoneyRecord vipMoneyRecord=new VipMoneyRecord(
                    vipId,
                    today,
                    MONEY_BOOK,
                    moneyType
            );
            HostelMoneyRecord hostelMoneyRecord=new HostelMoneyRecord(
                    room.getHostel().getId(),
                    -MONEY_BOOK,
                    today,
                    moneyType
            );
            msg=bossMoneyRecordDao.record(MONEY_BOOK,today,moneyType);
            if(msg==ResultMessage.FAILURE) return msg;
             ResultMessage msgVipR = vipMoneyRecordDao.addNoId(vipMoneyRecord);
             ResultMessage msgHostelR = hostelMoneyRecordDao.addNoId(hostelMoneyRecord);
             if(msgHostelR==ResultMessage.SUCCESS&&msgVipR==ResultMessage.SUCCESS) {
                 return bookBillDao.update(bookBill);
             }else return ResultMessage.FAILURE;
        }
    }

    @Override
    public List<BookBill> getAllBookBills(int vipId) {
        return bookBillDao.getAllByVipId(vipId);
    }
    @Override
    public List<BookBill> getDefaultNumBookBills(int vipId) {
        return bookBillDao.getDefaultNumByVipId(vipId);
    }
    @Override
    public List<BookBill> getBookBills_createDate(int vipId,String start,String end){
        return bookBillDao.getByVip_createDate(
                vipId,
                DateHandler.strToLong(start),
                DateHandler.strToLong(end)
        );
    }
    public List<BookBill> getBookBills_liveInDate(int vipId,String start,String end){
        return bookBillDao.getByVip_liveInDate(
                vipId,
                DateHandler.strToLong(start),
                DateHandler.strToLong(end)
        );
    }
    public List<BookBill> getBookBills_checkOutDate(int vipId,String start,String end){
        return bookBillDao.getByVip_checkOutDate(
                vipId,
                DateHandler.strToLong(start),
                DateHandler.strToLong(end)
        );
    }


    @Override
    public List<BookBill> getValidBookBills(int vipId){
        return bookBillDao.getAllValidByVipId(vipId);
    }
    @Override
    public List<PayBill> getAllPayBills(int vipId) {
        return payBillDao.getByVipId(vipId);
    }

    @Override
    public List<LiveBill> getAllLiveBills(int vipId) {
        return liveBillDao.getByVipId(vipId);
    }


    @Override
    public ResultMessage scoreToMoney(int vipId, double score) {
        Vip vip=getById(vipId);
        double originalScore=vip.getScore();
        if(originalScore<score){
            return ResultMessage.NOT_ENOUGH_SCORE;
        }else {
            double moneyGot=score*RATE_SCORE_TO_MONEY;
            vip.setMoneyLeft(vip.getMoneyLeft()+moneyGot);
            vip.setScore(originalScore-score);
            VipMoneyRecord vipMoneyRecord=new VipMoneyRecord(
                    vipId,
                    new Date().getTime(),
                    moneyGot,
                    MoneyType.MONEY_TYPE_SCORE_TO_MONEY.getCode()

            );
            //TODO
            if(vipMoneyRecordDao.addNoId(vipMoneyRecord)==ResultMessage.SUCCESS){
                return vipDao.update(vip);
            }else {
                return ResultMessage.FAILURE;
            }
        }
    }

    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelService.getAllPermittedHostels();
    }
    /**
     * 会员状态由【暂停】恢复到【正常】
     * 不会同步数据库！
     * @param vip
     * @return
     */
    private ResultMessage restore(Vip vip) {
        if(vip.getState().equals(VIPState.PAUSED.toString())){
            vip.setState(VIPState.NORMAL.toString());
            vip.setActivateDate(new Date().getTime());
            return ResultMessage.SUCCESS;
        }else if(vip.getState().equals(VIPState.STOP.toString())){
            //已停卡，不可恢复
            return ResultMessage.FAILURE;
        }
        //未激活不能【恢复】，正常无需恢复。
        return ResultMessage.FAILURE;
    }
    /**
     * 激活会员账号，会员卡状态由【未激活】改为【正常】
     * 不会同步数据库！
     * @param vip
     * @return
     */
    private ResultMessage activate(Vip vip) {
        if(vip.getState().equals(VIPState.UNACTIVATED.toString())){
            vip.setState(VIPState.NORMAL.toString());
            vip.setActivateDate((new Date()).getTime());
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage payMoney(int vipId,double money){
        Vip vip=vipDao.get(vipId);
        double moneyLeft=vip.getMoneyLeft();
        if(moneyLeft<money){//会员卡余额不足，提示用户充钱~
            return ResultMessage.NOT_ENOUGH_MONEY;
        }else {
            vip.setMoneyLeft(moneyLeft-money);
            return vipDao.update(vip);

        }
    }
    @Override
    public List<VipMoneyRecord> getAllMoneyRecords(int vipId){
        return vipMoneyRecordDao.getByRestrictEqual("vipId",vipId);
    }
    @Autowired
    VIPDao vipDao;
    @Autowired
    HostelDao hostelDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    BookBillDao bookBillDao;
    @Autowired
    PayBillDao payBillDao;
    @Autowired
    LiveBillDao liveBillDao;
    @Autowired
    VipMoneyRecordDao vipMoneyRecordDao;
    @Autowired
    HostelMoneyRecordDao hostelMoneyRecordDao;
    @Autowired
    BossMoneyRecordDao bossMoneyRecordDao;

    @Autowired
    UserService userService;
    @Autowired
    HostelService hostelService;
}
