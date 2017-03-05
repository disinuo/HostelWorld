package nju.edu.hostel.service.bean;

import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class ManagerServiceBean implements ManagerService {
    @Override
    public ResultMessage approve(int hostelId, Boolean ifApprove) {
        return null;
    }

    @Override
    public List<PayBill> count(int managerId, String bankPassword) {
        return null;
    }

    @Override
    public List<BookBill> getAllBookBills() {
        return null;
    }

    @Override
    public List<PayBill> getAllPayBills() {
        return null;
    }

    @Override
    public List<PayBill> getAllPayBillsOfVIP() {
        return null;
    }

    @Override
    public List<LiveBill> getAllLiveBills() {
        return null;
    }

    @Override
    public List<LiveBill> getAllLiveBillsByHostel(int hostelId) {
        return null;
    }
}
