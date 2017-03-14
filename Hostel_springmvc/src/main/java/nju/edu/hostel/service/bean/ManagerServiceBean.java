package nju.edu.hostel.service.bean;

import nju.edu.hostel.dao.*;
import nju.edu.hostel.service.HostelService;
import nju.edu.hostel.service.ManagerService;
import nju.edu.hostel.service.UserService;
import nju.edu.hostel.util.RequestState;
import nju.edu.hostel.util.ResultMessage;
import nju.edu.hostel.model.*;
import nju.edu.hostel.vo.output.RequestModifyVO;
import nju.edu.hostel.vo.output.RequestOpenVO;
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

    /**
     *
     private int id;
     private String hostel_img;
     private String hostel_phone;
     private String hostel_address;
     private String hostel_name;
     private RequestState state;
     */
    @Override
    public ResultMessage updateOpenRequest(int requestId,String requestState){
        RequestState state=RequestState.strToRequestState(requestState);
        RequestOpen request=requestDao.getOpenRequest(requestId);
        request.setState(state.toString());
        if(state==RequestState.DENIED){//拒绝申请
            return requestDao.updateOpenRequest(request);
        }else if(state==RequestState.APPROVED){//同意申请
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
    public ResultMessage updateModifyRequest(int requestId,String requestState){
        RequestState state=RequestState.strToRequestState(requestState);
        RequestModify request=requestDao.getModifyRequest(requestId);
        if(state==RequestState.DENIED){//拒绝修改请求
            return requestDao.updateModifyRequest(request);
        }else if(state==RequestState.APPROVED){//同意修改请求
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
    public ResultMessage count(int managerId, String bankPassword) {
        User manager=userDao.get(managerId);
        if(!manager.getBankPassword().equals(bankPassword)){
            return ResultMessage.WRONG_PASSWORD;//银行卡密码错误
        }else {//结算到今天为止的
            ResultMessage msg;
            List<Hostel> hostels=hostelService.getAllPermittedHostels();
            //经理结算的总金额
            double allMoneyToPay=0;
            for(Hostel hostel:hostels){
                //获取客栈的`未结算金额`
                double moneyShouldBePaid=hostel.getMoneyUncounted();
                //给客栈加钱
                msg=userService.modifyBankMoneyBy(hostel.getId(),moneyShouldBePaid);
                if(msg!=ResultMessage.SUCCESS) return msg;
                //计算累计结算的钱数，最后一起从总经理的银行卡上扣除
                allMoneyToPay+=moneyShouldBePaid;
                //将客栈的`未结算金额`置为0
                hostel.setMoneyUncounted(0);
                //更新客栈信息
                msg=hostelDao.update(hostel);
                if(msg!=ResultMessage.SUCCESS) return msg;
            }
            if(allMoneyToPay==0){
                return ResultMessage.NO_NEED_COUNT;
            }
            msg=userService.modifyBankMoneyBy(managerId,-allMoneyToPay);
            if(msg!=ResultMessage.SUCCESS) return msg;
//       -------到这里其实客栈和总经理之间的金钱交易就结束了，不过还要更新一下被结算的账单的状态orz，而且这一步会比较慢。。。
//            为了总经理可以查看某个客栈的结算细节--账单信息，必须得更新这个状态位~！
            List<PayBill> payBills=payBillDao.getByRestrictEqual("counted",false);
            for(PayBill payBill:payBills){
                payBill.setCounted(true);
                msg=payBillDao.update(payBill);
                if(msg!=ResultMessage.SUCCESS) return msg;
            }
            return ResultMessage.SUCCESS;
        }

    }


    @Override
    public List<Hostel> getAllPermittedHostels(){
        return hostelService.getAllPermittedHostels();
    }
    @Override
    public List<Vip> getAllVips(){
        return vipDao.getAll();
    }
    @Autowired
    private HostelDao hostelDao;
    @Autowired
    private PayBillDao payBillDao;
    @Autowired
    private VIPDao vipDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RequestDao requestDao;
    @Autowired
    private HostelService hostelService;
    @Autowired
    private UserService userService;
}
