package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.dao.RoomDao;
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
    @Autowired
    RoomDao roomDao;
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
        return hostelDao.getById(hostelId);
    }

    @Override
    public ResultMessage update(Hostel hostel) {
        return null;
    }

    @Override
    public ResultMessage enrollPay(PayBill payBill) {
        return null;
    }

    @Override
    public ResultMessage liveIn(int hostelId, LiveBill liveInBill) {
        return null;
    }

    @Override
    public ResultMessage depart(int hostelId, LiveBill departBill) {
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
    public List<BookBill> getAllBookBills(int hostelId) {

        return getById(hostelId).getBookBills();
    }

    @Override
    public List<PayBill> getAllPayBills(int hostelId) {

        return getById(hostelId).getPayBills();
    }

    @Override
    public double getIncome(int hostelId) {
        return 0;
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
        return roomDao.getById(roomId);
    }
}
