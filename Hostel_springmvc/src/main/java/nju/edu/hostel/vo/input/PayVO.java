package nju.edu.hostel.vo.input;

/**
 * Created by disinuo on 17/3/11.
 */
public class PayVO {
    private String userRealName;
    private String idCard;
    private double money;
    private int vipId=0;
    private int roomId;

    public PayVO(String userRealName, String idCard, double money, int vipId, int roomId) {
        this.userRealName = userRealName;
        this.idCard = idCard;
        this.money = money;
        this.vipId = vipId;
        this.roomId = roomId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
