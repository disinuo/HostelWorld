package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.BookDao;
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
    BookDao bookDao;
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
        return vipDao.getById(vipId);
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
        return null;
    }

    @Override
    public List<LiveBill> getAllLiveBills(int vipId) {
        return null;
    }

    @Override
    public ResultMessage scoreToMoney(int vipId, double score) {
        return null;
    }
}
