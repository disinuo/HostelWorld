package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.dao.UserDao;
import nju.edu.hostel.dao.VIPDao;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Override
    public ResultMessage add(String vipName, String password) {

        return null;
    }

    @Override
    public ResultMessage delete(int vipId) {
        return null;
    }

    @Override
    public ResultMessage activate(int vipId) {
        return null;
    }

    @Override
    public ResultMessage topUp(double money, int vipId, String bankPassword) {
        System.out.println("In VIPServiceBean--topUp");
        System.out.println("VIP 充值金额"+money+" id:"+vipId+" 密码:"+bankPassword);
        return null;
    }

    @Override
    public ResultMessage pause(int vipId) {
        return null;
    }

    @Override
    public ResultMessage restore(int vipId) {
        return null;
    }

    @Override
    public ResultMessage stop(int vipId) {
        return null;
    }

    @Override
    public Vip getById(int vipId) {
        return vipDao.get(vipId);
    }

    @Override
    public ResultMessage update(Vip vip) {
        return null;
    }

    @Override
    public ResultMessage book(BookBill bookBill) {
        return null;
    }

    @Override
    public ResultMessage unbook(int vipId, int bookId) {
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
        return null;
    }

    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelDao.getByRestrictEqual("permitted",true);
    }
}
