package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.dao.VIPDao;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import nju.edu.hostel.util.VIPState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static nju.edu.hostel.util.Constants.*;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class VIPServiceBean implements VIPService{
    @Autowired
    VIPDao vipDao;
    @Autowired
    HostelDao hostelDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    @Override
    public ResultMessage delete(int vipId) {
        //TODO
        return null;
    }


    @Override
    public ResultMessage topUp(double money, int vipId, String bankPassword) {
        System.out.println("In VIPServiceBean--topUp");
        System.out.println("VIP 充值金额"+money+" id:"+vipId+" 密码:"+bankPassword);
        User user=userDao.get(vipId);
        Vip vip=vipDao.get(vipId);
        //已停卡，则不能充值
        if(vip.getState().equals(VIPState.STOP.toString())){
            return ResultMessage.ALREADY_STOP;
        }
        if(!user.getBankPassword().equals(bankPassword)){
            return ResultMessage.WRONG_PASSWORD;
        }
        double bankMoney=user.getBankMoney();
        if(bankMoney<money){
            return ResultMessage.NOT_ENOUGH_MONEY;
        }
        //        ----真的可以开始充值啦！------
        ResultMessage msg=userService.modifyBankMoneyBy(vipId,-money);
        vip.setMoneyLeft(vip.getMoneyLeft()+money);
        if(msg==ResultMessage.SUCCESS){
            if(money>=MONEY_ACTIVATE){
                activate(vip);
            }
            if(money>=MONEY_LEAST){
                restore(vip);
            }
            return vipDao.update(vip);
        }else {
            return ResultMessage.FAILURE;
        }
    }
    public ResultMessage pause(int vipId) {
        Vip vip=vipDao.get(vipId);
        if(vip.getState().equals(VIPState.NORMAL.toString())){
            vip.setState(VIPState.PAUSED.toString());
            return vipDao.update(vip);
        }
        //未激活--不能暂停。已停卡--不能暂停。已暂停--不能暂停。。
        return ResultMessage.FAILURE;
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
    public ResultMessage update(Vip vip) {
        return vipDao.update(vip);
    }

    @Override
    public ResultMessage book(BookBill bookBill) {
        //TODO
        return null;
    }

    @Override
    public ResultMessage unbook(int vipId, int bookId) {
        //TODO

        return null;
    }

    @Override
    public List<BookBill> getAllBookBills(int vipId) {
        Vip vip=getById(vipId);
        return vip.getBookBills();
    }

    @Override
    public List<PayBill> getAllPayBills(int vipId) {

        Vip vip=getById(vipId);
        return vip.getPayBills();

    }

    @Override
    public List<LiveBill> getAllLiveBills(int vipId) {
        Vip vip=getById(vipId);
        return vip.getLiveBills();
    }

    @Override
    public ResultMessage scoreToMoney(int vipId, double score) {
        //TODO

        return null;
    }

    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelDao.getByRestrictEqual("permitted",true);
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
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.SUCCESS;
    }
}
