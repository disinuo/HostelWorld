package nju.edu.hostel.service.bean;

import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
public class ManagerServiceBean implements ManagerService {
    @Override
    public ResultMessage approve(int hostelId, Boolean ifApprove) {
        return null;
    }

    @Override
    public List<Paybill> count(int managerId, String bankPassword) {
        return null;
    }

    @Override
    public List<Bookbill> getAllBookBills() {
        return null;
    }

    @Override
    public List<Paybill> getAllPayBills() {
        return null;
    }

    @Override
    public List<Paybill> getAllPayBillsOfVIP() {
        return null;
    }

    @Override
    public List<Livebill> getAllLiveBills() {
        return null;
    }

    @Override
    public List<Livebill> getAllLiveBillsByHostel(int hostelId) {
        return null;
    }
}
