package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.HostelDao;
import nju.edu.hostel.dao.RequestDao;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.util.RequestState;
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
public class ManagerServiceBean implements ManagerService {
    @Override
    public List<RequestOpen> getOpenRequests(){
        return requestDao.getOpenRequestByRestrictEqual("state", RequestState.UNCHECKED.toString());
    }

    @Override
    public List<RequestModify> getModifyRequests(){
        return requestDao.getModifyRequestByRestrictEqual("state", RequestState.UNCHECKED.toString());
    }
    @Override
    public ResultMessage updateOpenRequest(RequestOpen request){
        RequestState requestState=RequestState.strToRequestState(request.getState());
        if(requestState==RequestState.DENIED){//拒绝申请
            return requestDao.updateOpenRequest(request);
        }else if(requestState==RequestState.APPROVED){//同意申请
            Hostel hostel=request.getHostel();
            hostel.setPermitted(true);
            ResultMessage msg1=hostelDao.update(hostel);
            ResultMessage msg2=requestDao.updateOpenRequest(request);
            if(msg1==ResultMessage.SUCCESS&&msg2==ResultMessage.SUCCESS){
                return ResultMessage.SUCCESS;
            }else {
                return ResultMessage.FAILURE;
            }
        }else {//没审核。。。
            return ResultMessage.SUCCESS;
        }
    }
    @Override
    public ResultMessage updateModifyRequest(RequestModify request){
        RequestState requestState=RequestState.strToRequestState(request.getState());
        if(requestState==RequestState.DENIED){//拒绝修改请求
            return requestDao.updateModifyRequest(request);
        }else if(requestState==RequestState.APPROVED){//同意修改请求
            ResultMessage msg1=hostelDao.update(request.getHostelNew());
            ResultMessage msg2=requestDao.updateModifyRequest(request);
            if(msg1==ResultMessage.SUCCESS&&msg2==ResultMessage.SUCCESS){
                return ResultMessage.SUCCESS;
            }else {
                return ResultMessage.FAILURE;
            }
        }else {//没审核。。。
            return ResultMessage.SUCCESS;
        }
    }
    @Override
    public List<PayBill> count(int managerId, String bankPassword) {
        return null; //TODO
    }

    @Override
    public List<BookBill> getAllBookBills() {
        return null; //TODO
    }

    @Override
    public List<PayBill> getAllPayBills() {
        return null; //TODO
    }

    @Override
    public List<PayBill> getAllPayBillsOfVIP() {
        return null; //TODO
    }

    @Override
    public List<LiveBill> getAllLiveBills() {
        return null; //TODO
    }

    @Override
    public List<LiveBill> getAllLiveBillsByHostel(int hostelId) {
        return null; //TODO
    }
    @Autowired
    HostelDao hostelDao;
    @Autowired
    RequestDao requestDao;
}
