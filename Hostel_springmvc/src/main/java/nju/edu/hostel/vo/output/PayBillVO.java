package nju.edu.hostel.vo.output;

import nju.edu.hostel.model.PayBill;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/3/12.
 */


public class PayBillVO {
    private int id;
    private boolean counted;
    private double money;
    private String createDate;
    private int hostelId;
    private String hostelName;
    private String hostelAddress;

    private int roomId;
    private String roomImg;
    private String roomName;
    private double roomPrice;
    private int numOfPeople;

    private List<GuestVO> guestVOS;


    public PayBillVO(PayBill payBillEntity){
        this.id=payBillEntity.getId();
        this.counted=payBillEntity.getCounted();
        this.money=payBillEntity.getMoney();
        this.createDate= payBillEntity.getCreateDateStr();
        this.hostelId=payBillEntity.getHostel().getId();
        this.hostelAddress=
                payBillEntity.getHostel().getProvince()+" - "+
                payBillEntity.getHostel().getCity()+" - "+
                payBillEntity.getHostel().getAddress();
        this.hostelName=payBillEntity.getHostel().getName();
        this.roomId=payBillEntity.getRoom().getId();
        this.roomImg=payBillEntity.getRoom().getImg();
        this.roomName=payBillEntity.getRoom().getName();
        this.roomPrice=payBillEntity.getRoom().getPrice();

        this.guestVOS=GuestVO.entityToVO(payBillEntity.getLiveDetails());
        this.numOfPeople=payBillEntity.getNumOfPeople();
    }
    public static List<PayBillVO> entityToVO(List<PayBill> bills){
        List<PayBillVO> res=new ArrayList<PayBillVO>();
        for(PayBill bill:bills){
            res.add(new PayBillVO(bill));
        }
        return res;
    }
    public int getId() {
        return id;
    }

    public boolean isCounted() {
        return counted;
    }

    public double getMoney() {
        return money;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getHostelId() {
        return hostelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public String getHostelAddress() {
        return hostelAddress;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public String getRoomName() {
        return roomName;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public List<GuestVO> getGuestVOS() {
        return guestVOS;
    }
}
