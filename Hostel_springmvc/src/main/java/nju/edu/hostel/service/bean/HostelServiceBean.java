package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.model.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class HostelServiceBean implements HostelService {
    @Autowired
    HostelDao hostelDao;
    @Override
    public ResultMessage add(String hostelName, String password) {
        return null;
    }

    @Override
    public ResultMessage delete(int hostelId) {
        return null;
    }

    @Override
    public ResultMessage requestManager(int hostelId) {
        return null;
    }

    @Override
    public Hostel getById(int hostelId) {
        return null;
    }

    @Override
    public ResultMessage update(Hostel hostel) {
        return null;
    }

    @Override
    public ResultMessage enrollPay(Paybill paybill) {
        return null;
    }

    @Override
    public ResultMessage liveIn(int hostelId, Livebill liveInBill) {
        return null;
    }

    @Override
    public ResultMessage depart(int hostelId, Livebill departBill) {
        return null;
    }

    @Override
    public ResultMessage addRoom(int hostelId, List<Room> rooms) {
        return null;
    }

    @Override
    public ResultMessage updateRoom(int hostelId, Room room) {
        return null;
    }

    @Override
    public List<Bookbill> getAllBookBills(int hostelId) {
        return null;
    }

    @Override
    public List<Paybill> getAllPayBills(int hostelId) {
        return null;
    }

    @Override
    public double getIncome(int hostelId) {
        return 0;
    }

    @Override
    public List<Livebill> getAllLiveBills(int hostelId) {
        return null;
    }

    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelDao.get();
    }
}
